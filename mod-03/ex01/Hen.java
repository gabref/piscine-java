public class Hen implements Runnable {
	private final int count;
	private final ThreadQueue queue;
	private boolean running = false;

	public Hen(int count, ThreadQueue queue) {
		this.count = count + 1;
		this.queue = queue;
	}
	public void run() {
		running = true;
		produce();
	}

	public void produce() {
		int i = 0;
		while (running && i < count) {
			if (queue.isFull()) {
				try {
					queue.waitIsNotFull();
					System.out.println("Hen");
				}catch (InterruptedException e) {
					System.err.println("Error while waiting to Produce messages");
					break;
				}
			}
			queue.add(new Message(i++));
			if (!running) {
				break;
			}
		}
	}

	public void stop() {
		running = false;
		queue.notifyIsNotFull();
	}
}
