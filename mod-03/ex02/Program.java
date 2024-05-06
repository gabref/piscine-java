import java.util.Random;
import java.util.stream.IntStream;

public class Program {
	public final static int MAX_ARRAY_ELEMENTS = 2000000;
	public static void main(String[] args) {
		try {
			Arguments parsedArgs = parseArgs(args);
			int[] arr = generateRandomArray(parsedArgs.getArraySize());

			System.out.println("Sum: " + IntStream.of(arr).sum());

			int elementsPerThread = (int) Math.ceil((double) arr.length / parsedArgs.getThreadsCount());

			Thread[] threads = new Thread[parsedArgs.getThreadsCount()];
			Sum sum = new Sum();

			for (int i = 0; i < parsedArgs.getThreadsCount(); i++) {
				int start = i * elementsPerThread;
				int end = start + elementsPerThread - 1;
				threads[i] = new Thread(new ArraySummer(i + 1, arr, start, end, sum));
				threads[i].start();
			}
			for (int i = 0; i < parsedArgs.getThreadsCount(); i++)
				threads[i].join();

			System.out.println("Sum by threads: " + sum.getSum());

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	static int[] generateRandomArray(int arraySize) {
		int[] arr = new Random().ints(arraySize, -1000, 1001).toArray();
		return arr;
	}

	static Arguments parseArgs(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Error: wrong number of flags");
			System.err.println("Usage: java Program --arraySize=13 --threadsCount=3");
			System.exit(1);
		}
		String[] arraySizeSplit = args[0].split("=");
		String[] threadsCountSplit = args[1].split("=");
		if (!arraySizeSplit[0].equals("--arraySize") || !threadsCountSplit[0].equals("--threadsCount")) {
			System.err.println("Error: wrong flags");
			System.err.println("Usage: java Program --arraySize=13 --threadsCount=3");
			System.exit(1);
		}
		int arraySize = Integer.parseInt(arraySizeSplit[1]);
		int threadsCount = Integer.parseInt(threadsCountSplit[1]);

		if (arraySize > MAX_ARRAY_ELEMENTS)
			throw new Exception("arraySize too big");
		if (threadsCount > arraySize)
			throw new Exception("threadsCount cannot be bigger than array elements");
		return new Arguments(arraySize, threadsCount);
	}
}
