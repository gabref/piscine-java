public class Egg implements Runnable {
	private int count;
	private final ThreadQueue queue;
	private boolean running = false;

	public Egg(int count, ThreadQueue queue) {
		this.count = count + 1;
		this.queue = queue;
	}

	@Override
	public void run() {
		running = true;
		consume();
	}

	public void consume() {
		int i = 0;
		while (running && i < count) {
			if (queue.isEmpty()) {
				try {
					queue.waitIsNotEmpty();
					System.out.println("Egg");
				} catch (InterruptedException e) {
					System.err.println("Error while waiting to Consume messages");
					break;
				}
			}
			queue.remove();
			i++;
			if (!running) {
				break ;
			}
		}
	}

	public void stop() {
		running = false;
		queue.notifyIsNotEmpty();
	}
}
