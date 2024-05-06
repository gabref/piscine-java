import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Random;

public class ConsumeFiles implements Runnable {
	private final ThreadQueue queue;
	private final int id;
	private boolean running = false;

	public ConsumeFiles(int id, ThreadQueue queue) {
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		running = true;
		consume();
	}

	public void consume() {
		while (running) {
			if (queue.isEmpty()) {
				try {
					queue.waitIsNotEmpty();
				} catch (InterruptedException e) {
					System.err.println("Error while waiting to Consume messages");
					break;
				}
			}
			if (!running) {
				break ;
			}
			FilesDownload file = queue.remove();
			downloadFile(file);
		}
	}

	private void downloadFile(FilesDownload file) {
		try {
			System.out.println("Thread-" + id + " start download file number " + file.getId());

			String url = file.getUrl();
			String outputFileName = url.substring(url.lastIndexOf('/') + 1);
			ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(url).openStream());
			FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);
			FileChannel fileChannel = fileOutputStream.getChannel();
			fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
			fileOutputStream.close();

			System.out.println("Thread-" + id + " finish download file number " + file.getId());
		} catch (Exception e) {
			System.err.println("Thread " + id + "; File " + id + " failed");
		}
	}

	public void stop() {
		running = false;
		queue.notifyIsNotEmpty();
	}
}
