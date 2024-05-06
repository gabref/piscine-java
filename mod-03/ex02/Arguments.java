public class Arguments {
	private int arraySize;
	private int threadsCount;

	Arguments(int arraySize, int threadsCount) {
		this.arraySize = arraySize;
		this.threadsCount = threadsCount;
	}

	public int getArraySize() {
		return arraySize;
	}

	public int getThreadsCount() {
		return threadsCount;
	}
}
