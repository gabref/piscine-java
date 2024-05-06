public class Program {
	public static void main(String[] args) {
		try {
			int count = getInput(args);

			ThreadQueue queue = new ThreadQueue(1);
			queue.add(new Message(0));

			Egg egg = new Egg(count, queue);
			Hen hen = new Hen(count, queue);
			Thread eggThread = new Thread(egg);
			Thread henThread = new Thread(hen);

			eggThread.start();
			henThread.start();
			eggThread.join();
			henThread.join();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private static int getInput(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Error: invalid args");
			System.err.println("Usage: java Program --count=50");
			System.exit(1);
		}
		String[] splitted = args[0].split("=");
		if (!splitted[0].equals("--count")) {
			System.err.println("Error: invalid flag");
			System.err.println("Usage: java Program --count=50");
			System.exit(1);
		}
		int count = Integer.parseInt(splitted[1]);
		return count;
	}
}
