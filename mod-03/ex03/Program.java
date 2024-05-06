public class Program {
	private static final int MAX_FILE_QUEUE_ELEMENTS = 1000;

	public static void main(String[] args) {
		try {
			int threadsCount = getInput(args);

			ThreadQueue queue = new ThreadQueue(MAX_FILE_QUEUE_ELEMENTS);

			ProduceFiles producer = new ProduceFiles(queue);
			Thread[] consumers = new Thread[threadsCount];

			Thread producerThread = new Thread(producer);
			producerThread.start();
			for (int i = 0; i < consumers.length; i++) {
				ConsumeFiles consumer = new ConsumeFiles(i + 1, queue);
				consumers[i] = new Thread(consumer);
				consumers[i].start();
			}

			producerThread.join();
			for (int i = 0; i < consumers.length; i++)
				consumers[i].join();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private static int getInput(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Error: invalid args");
			System.err.println("Usage: java Program --threadsCount=50");
			System.exit(1);
		}
		String[] splitted = args[0].split("=");
		if (!splitted[0].equals("--threadsCount")) {
			System.err.println("Error: invalid flag");
			System.err.println("Usage: java Program --threadsCount=50");
			System.exit(1);
		}
		int count = Integer.parseInt(splitted[1]);
		return count;
	}
}
