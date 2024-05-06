public class Sum {
	private int currentSum;

	Sum() {
		currentSum = 0;
	}

	public synchronized void add(int value) {
		currentSum += value;
	}

	public int getSum() {
		return currentSum;
	}
}
