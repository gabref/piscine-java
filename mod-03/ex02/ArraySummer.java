public class ArraySummer implements Runnable {
	private final int[] arr;
	private final int start;
	private int end;
	private final Sum sum;
	private final int id;

	ArraySummer(int id, int[] arr, int start, int end, Sum sum) {
		this.id = id;
		this.arr = arr;
		this.start = start;
		this.end = end;
		this.sum = sum;
	}

	@Override
	public void run() {
		int sum = sumSection();
		this.sum.add(sum);
		System.out.println("Thread " + id + ": from " + start + " to " + end + " is " + sum);
	}

	public int sumSection() {
		int sum = 0;
		int start = this.start;
		while (start < arr.length && start - 1 < end) {
			sum += arr[start++];
		}
		if (end > arr.length)
			end = arr.length - 1;
		return sum;
	}
}
