import java.util.Arrays;

public class Program {
	public static void main(String[] args) {
		try {
			System.out.println("Create User galves-f");
			User galves = new User("galves-f", -100);
			User naruto = new User("naruto", 800);
			User minato = new User("minato", 700);

			Transaction t1 = new Transaction(naruto, minato, Transaction.Category.debit, -120);
			Transaction t2 = new Transaction(naruto, galves, Transaction.Category.credit, 100);
			Transaction t3 = new Transaction(minato, naruto, Transaction.Category.credit, 50);
			Transaction t4 = new Transaction(minato, galves, Transaction.Category.credit, 77);
			Transaction t5 = new Transaction(galves, naruto, Transaction.Category.debit, -100);
			Transaction t6 = new Transaction(galves, minato, Transaction.Category.debit, -30);
			t1.makeTransaction();
			t2.makeTransaction();
			t3.makeTransaction();
			t4.makeTransaction();
			t5.makeTransaction();
			t6.makeTransaction();

			TransactionsLinkedList list = new TransactionsLinkedList();
			list.addTransaction(t1);
			list.addTransaction(t2);
			list.addTransaction(t3);
			list.addTransaction(t4);
			list.addTransaction(t5);
			list.addTransaction(t6);

			System.out.println("Add transaction to list");
			list.printLinkedList();

			System.out.println("Remove a middle transaction");
			list.removeTransactionById(t3.getUUID());
			list.printLinkedList();

			System.out.println("remove the first and last");
			list.removeTransactionById(t1.getUUID());
			list.removeTransactionById(t6.getUUID());
			list.printLinkedList();

			System.out.println("remove all by last");
			list.removeTransactionById(t5.getUUID());
			list.removeTransactionById(t4.getUUID());
			list.removeTransactionById(t2.getUUID());
			list.printLinkedList();

			System.out.println("Add again a couple of transactions");
			list.addTransaction(t1);
			list.addTransaction(t2);
			list.addTransaction(t3);
			list.printLinkedList();

			System.out.println("remove all by first");
			list.removeTransactionById(t1.getUUID());
			list.removeTransactionById(t2.getUUID());
			list.removeTransactionById(t3.getUUID());
			list.printLinkedList();

			System.out.println("Add transaction list");
			list.addTransaction(t1);
			list.addTransaction(t2);
			list.addTransaction(t3);
			list.addTransaction(t4);
			list.addTransaction(t5);
			list.addTransaction(t6);
			list.printLinkedList();

			System.out.println("To array");
			System.out.println(Arrays.toString(list.toArray()));

		} catch (Exception e) {
			System.out.println("An Exception occurred\n========================");
			System.out.println(e.getMessage());
		}
	}

	static String printUserOneline(User user) {
		return user.getIdentifier() + " - " + user.getName();
	}

	static void printUser(User user) {
		System.out.println(user.getIdentifier() + " - " + user.getName() + " has\t$" + user.getBalance() + " in the bank");
	}

	static void printTransaction(Transaction t) {
		System.out.println("\nTransaction: ");
		System.out.println("-> from:\t" + t.getSender().getIdentifier() + " - " + t.getSender().getName());
		System.out.println("-> to:\t\t" +  t.getRecipient().getIdentifier() + " - " + t.getRecipient().getName());
		System.out.println("-> value:\t$ " + t.getAmount());
	}
}
