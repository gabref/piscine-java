package edu._42roma.numbers;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest extends Assert {
	private NumberWorker numberWorker;

	@BeforeEach
	void createNumberWorker() {
		numberWorker = new NumberWorker();
	}

	@ParameterizedTest(name = "{index} - {0} is a negative")
	@ValueSource(ints = { 0, 1, -200 })
	public void isPrimeForIncorrectNumbers(final int number) {
		assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(number));
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, 3, 5, 53, 191 })
	public void isPrimeForPrime(final int number) throws IllegalNumberException {
		assertTrue(numberWorker.isPrime(number));
	}

	@ParameterizedTest
	@ValueSource(ints = { 4, 8, 24, 265 })
	public void isPrimeForNotPrime(final int number) throws IllegalNumberException {
		assertFalse(numberWorker.isPrime(number));
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/data.csv", numLinesToSkip = 0)
	public void digitSum(int number, int sum) {
		assertEquals(numberWorker.digitsSum(number), sum);
	}
}
