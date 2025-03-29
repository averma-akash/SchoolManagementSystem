package com.sms.application;

public class Test1 {

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 5, 6 }; // Replace this with your array
		if (existDistinctIndices(A)) {
			System.out.println(
					"There exist four distinct indices i, j, k, and l such that A[i] + A[j] is not equal to A[k] + A[l].");
		} else {
			System.out.println("No such distinct indices found.");
		}
	}

	private static boolean existDistinctIndices(int[] A) {
		int n = A.length;

		for (int i = 0; i < n - 3; i++) {
			for (int j = i + 1; j < n - 2; j++) {
				for (int k = j + 1; k < n - 1; k++) {
					for (int l = k + 1; l < n; l++) {
						if (A[i] + A[j] != A[k] + A[l]) {
							return true;
						}
					}
				}
			}
		}

		return false;

	}

}
