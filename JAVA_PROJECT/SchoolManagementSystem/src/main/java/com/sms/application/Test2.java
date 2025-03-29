package com.sms.application;

import java.util.Arrays;

public class Test2 {
	
	public static void main(String[] args) {
        int[] A = {1,1,2,2}; // Replace this with your array
        int k = 2; // Replace this with the number of elements you can select in each operation
        int operations = minOperations(A, k);
        System.out.println("Minimum number of operations: " + operations);
    }

    private static int minOperations(int[] A, int k) {
        int n = A.length;

        //Arrays.sort(A); // Sort the array in ascending order

        int minOperations = 0;

        // Iterate over the array from the beginning, selecting the first k elements in each iteration
        for (int i = 0; i < n; i += k) {
            int target = A[i]; // Target value to make the selected elements equal

            // Iterate over the selected elements, calculate and accumulate the operations needed
            for (int j = i; j < Math.min(i + k, n); j++) {
                minOperations += Math.abs(target - A[j]);
            }
        }

        return minOperations;
    }

}
