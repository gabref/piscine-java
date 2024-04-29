import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		int primeCandidate = getUserInput();
		if (primeCandidate == -1)
			System.exit(-1);
		PrimeChecker pc = new PrimeChecker(primeCandidate);
		System.out.println(convertBooleanToString(pc.isPrime()) + " " + pc.getIterations());
	}

	static String convertBooleanToString(Boolean b) {
		if (b)
			return "true";
		else
			return "false";
	}

	static int getUserInput() {
		Scanner scanner = new Scanner(System.in);
		try {
			int candidate = scanner.nextInt();
			if (candidate < 2)
				throw new IllegalArgument("IllegalArgument");
			return candidate;
		} catch (IllegalArgument e) {
			System.out.println(e.getMessage());
			return -1;
		} catch(Exception e) {
			System.out.println("Input was not a int:");
			return -1;
		} finally {
			scanner.close();
		}
	}
}

class PrimeChecker {
	private int number;
	private int iterations;

	public PrimeChecker(int number) {
		this.number = number;
		this.iterations = 0;
	}

	private int sqrt(int n) {
		long start = 1;
		long end = (long) n;
		long ret = 0;
		long mid;

		while (start <= end) {
			mid = (start + end) / 2;
			if (mid * mid == n)
				return ((int) mid);
			else if (mid * mid < n) {
				start = mid + 1;
				ret = mid;
			} else {
				end = mid - 1;
			}
		}
		return (int)ret;
	}

	public Boolean isPrime() {
		int i = 2;
		int sqrt = this.sqrt(this.number);
		while (i <= sqrt) {
			this.iterations++;
			if (this.number % i++ == 0)
				return false;
		}
		return true;
	}

	public int getIterations() {
		return iterations;
	}
}

class IllegalArgument extends Exception {
	public IllegalArgument(String errorMessage) {
		super(errorMessage);
	}
}
