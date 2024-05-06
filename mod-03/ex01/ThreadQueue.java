import java.util.LinkedList;
import java.util.Queue;

public class ThreadQueue {
	private final Queue<Message> queue = new LinkedList<>();
	private final int maxSize;
	private int currentSize;
	private final Object IS_NOT_FULL = new Object();
	private final Object IS_NOT_EMPTY = new Object();

	ThreadQueue(int maxSize) {
		this.maxSize = maxSize;
		this.currentSize = 0;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public boolean isFull() {
		return maxSize == currentSize;
	}

	public void waitIsNotFull() throws InterruptedException {
		synchronized(IS_NOT_FULL) {
			IS_NOT_FULL.wait();
		}
	}

	public void notifyIsNotFull() {
		synchronized(IS_NOT_FULL) {
			IS_NOT_FULL.notify();
		}
	}

	public void waitIsNotEmpty() throws InterruptedException {
		synchronized(IS_NOT_EMPTY) {
			IS_NOT_EMPTY.wait();
		}
	}

	public void notifyIsNotEmpty() {
		synchronized(IS_NOT_EMPTY) {
			IS_NOT_EMPTY.notify();
		}
	}

	public void add(Message message) {
		currentSize++;
		queue.add(message);
		notifyIsNotEmpty();
	}

	public Message remove() {
		currentSize--;
		Message message = queue.poll();
		notifyIsNotFull();
		return message;
	}
}
