import java.util.LinkedList;
import java.util.Queue;

public class ThreadQueue {
	private final Queue<FilesDownload> queue = new LinkedList<>();
	private final int maxSize;
	private final Object IS_NOT_FULL = new Object();
	private final Object IS_NOT_EMPTY = new Object();

	ThreadQueue(int maxSize) {
		this.maxSize = maxSize;
	}

	public boolean isEmpty() {
		return queue.size() == 0;
	}

	public boolean isFull() {
		return maxSize == queue.size();
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

	public void add(FilesDownload f) {
		queue.add(f);
		notifyIsNotEmpty();
	}

	public FilesDownload remove() {
		FilesDownload f = queue.poll();
		notifyIsNotFull();
		return f;
	}
}
