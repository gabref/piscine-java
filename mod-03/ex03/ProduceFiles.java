import java.io.FileNotFoundException;

public class ProduceFiles implements Runnable {
	private final ThreadQueue queue;
	private boolean running = false;
	private UrlFiles urlFiles;

	public ProduceFiles(ThreadQueue queue) {
		this.queue = queue;
		urlFiles = new UrlFiles("files_urls.txt");
	}

	public void run() {
		running = true;
		produce();
	}

	public void produce() {
		int i = 0;
		try {
			urlFiles.init();
			while (running) {
				if (queue.isFull()) {
					try {
						queue.waitIsNotFull();
					} catch (InterruptedException e) {
						System.err.println("Error while waiting to Produce messages");
						break;
					}
				}
				if (!running) {
					break;
				}
				String nextUrl = urlFiles.getNextLine();
				if (nextUrl == null)
					break ;
				queue.add(new FilesDownload(i++, nextUrl));
			}
			urlFiles.close();
		} catch (Exception e) {
			System.err.println("Error - exiting: " + e.getMessage());
			System.exit(1);
		}
	}

	public void stop() {
		running = false;
		queue.notifyIsNotFull();
	}
}
