
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
	private TransactionNode start;
	private TransactionNode tail;

	public TransactionsLinkedList() {
		start = null;
		tail = null;
	}

	public void addTransaction(Transaction t) {
		TransactionNode tmp = new TransactionNode(t);
		if (tail != null) {
			tmp.prev = tail;
			tail.next = tmp;
			tail = tmp;
		} else if (start != null && tail == null) {
			start.next = tmp;
			tmp.prev = start;
			tail = tmp;
		} else {
			start = tmp;
		}
	}

	public void removeTransactionById(UUID id) throws RuntimeException {
		Boolean found = false;
		if (start == null)
			throw new TransactionNotFoundException();
		else if (start.content.getUUID() == id) {
			found = true;
			if (tail != null) {
				if (start.next == tail) {
					start = tail;
					tail = null;
				} else {
					start.next.prev = null;
					start = start.next;
				}
			} else {
				start = null;
			}
		} else if (tail.content.getUUID() == id) {
			found = true;
			if (tail.prev == start)
				tail = null;
			else {
				tail = tail.prev;
				tail.next = null;
			}
		} else {
			TransactionNode current = start.next;
			while (current.next != tail) {
				if (current.content.getUUID() == id) {
					found = true;
					current.prev.next = current.next;
					current.next.prev = current.prev;
					break;
				}
				current = current.next;
			}
		}
		if (!found)
			throw new TransactionNotFoundException();
	}

	public Transaction[] toArray() {
		int size = getLinkedListSize();
		Transaction[] arr = new Transaction[size];
		TransactionNode current = start;
		for (int i = 0; i < size; i++) {
			arr[i] = current.content;
			current = current.next;
		}
		return arr;
	}

	public void printLinkedList() {
		TransactionNode current = start;
		while (current != null) {
			System.out.print(current.content.getAmount() + " -> ");
			current = current.next;
		}
		System.out.print("NULL\n");
	}

	private int getLinkedListSize() {
		TransactionNode current = start;
		int count = 0;
		while (current != null) {
			count++;
			current = current.next;
		}
		return count;
	}
}

class TransactionNode {
	public TransactionNode next;
	public TransactionNode prev;
	public Transaction content;

	public TransactionNode(Transaction content) {
		next = null;
		prev = null;
		this.content = content;
	}
}

class TransactionNotFoundException extends RuntimeException {
	public TransactionNotFoundException() {
		super();
	}
}
