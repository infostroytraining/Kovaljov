package ua.nure.infostroy.kovaljov.introduction;

public class MathUtils {

	/**
	 * Returns the greatest common divider of given two numbers
	 * 
	 * @param firstNumber
	 *            - positive number
	 * @param secondNumber
	 *            - positive number
	 * @return greatest common divider of two numbers
	 */
	public int getGreatestCommonDivider(int firstNumber, int secondNumber) {
		if (secondNumber == 0)
			return firstNumber;
		return getGreatestCommonDivider(secondNumber, firstNumber % secondNumber);
	}

	/**
	 * Returns sum of digits of the given number
	 * 
	 * @param number
	 *            - positive number
	 * @return the sum of digits of the given number
	 */
	public int getSumOfDigits(int number) {
		int result = 0;
		while (number != 0) {
			result += number % 10;
			number /= 10;
		}
		return result;
	}

	/**
	 * Checks if the given number is prime or not
	 * 
	 * @param number
	 * @return true - if number is prime, if not return false
	 */
	public boolean isPrime(int number) {
		if (number < 2) {
			return false;
		}
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns sum of row: 1! - 2! + 3! - 4! + 5! - ... + n!
	 * 
	 * @param n
	 *            - positive number
	 */

	private int factorial(int number) {
		if (number <= 1) {
			return 1;
		} else {
			return number * factorial(number - 1);
		}
	}

	public int getSumOfRow(int n) {
		int result = 0;
		for (int i = 1; i <= n; i++) {
			result = result - (int) Math.pow(-1, i) * factorial(i);
		}
		return result;
	}

	/**
	 * Returns Fibonacci series of a specified length
	 * 
	 * @param length
	 *            - the length of the Fibonacci series
	 * @return array filled with Fibonacci series
	 */

	private int getFibonacciItem(int number) {
		if (number <= 1) {
			return 1;
		} else {
			return getFibonacciItem(number - 1) + getFibonacciItem(number - 2);
		}
	}

	public int[] getFibonacciSeries(int length) {
		if (length < 0) {
			throw new IllegalArgumentException();
		}
		int[] result = new int[length];
		for (int i = 0; i < length; i++) {
			result[i] = getFibonacciItem(i);
		}
		return result;
	}

	/**
	 * Returns array with prime numbers
	 * 
	 * @param length
	 *            - the length of prime numbers series
	 * @return array filled with prime numbers
	 */
	private int[] sieve(int amount) {
		int[] digits = new int[amount];
		int index = 0;
		int digit = 1;
		while (index != amount) {
			if (isPrime(digit)) {
				digits[index] = digit;
				index++;
				digit++;
			} else {
				digit++;
			}
		}
		return digits;
	}

	public int[] getPrimeSeries(int length) {
		return sieve(length);
	}

	public static String toString(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for (Integer item : arr) {
			sb.append(item);
			sb.append("; ");
		}
		return sb.toString();
	}
}
