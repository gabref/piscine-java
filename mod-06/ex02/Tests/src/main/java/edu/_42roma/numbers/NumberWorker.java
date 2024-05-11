package edu._42roma.numbers;

public class NumberWorker {
	public NumberWorker() {
	}

	/*
	 * 2 > returns true or false
	 * 2 < throws IllegalNumberException
	 */
	public boolean isPrime(int number) throws IllegalNumberException {
		if (number < 2)
			throw new IllegalNumberException("Bruh, that's not a prime");
		double sqrt = Math.sqrt(number);
		for (int i = 2; i <= sqrt; i++)
			if (number % i == 0)
				return false;
		return true;
	}

	/*
	 * sum of digits of source numbers
	 */
	public int digitsSum(int number) {
		int sum = 0;
		number = Math.abs(number);
		for (; number > 0; number /= 10)
			sum += number % 10;
		return sum;
	}
}
