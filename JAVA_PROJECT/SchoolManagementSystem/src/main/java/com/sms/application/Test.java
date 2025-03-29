package com.sms.application;

public class Test {

	public static void main(String[] args) {
		int N = 1; // Replace this with your desired integer N
		if (makeZero(N)) {
			System.out.println("It is possible to make N zero.");
		} else {
			System.out.println("It is not possible to make N zero.");
		}
	}

	private static boolean makeZero(int N) {
		if (N == 0) {
			return true;
		}

		if (N < 0) {
			return false;
		}

		// Try subtracting 3
		if (makeZero(N - 3)) {
			return true;
		}

		// Try subtracting 4
		if (makeZero(N - 4)) {
			return true;
		}

		// If neither 3 nor 4 leads to zero
		return false;
	}
}