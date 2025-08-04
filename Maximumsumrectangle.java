 // Problem Statement:
// Given a 2D matrix mat[][] with dimensions n×m. Find the maximum possible sum of any submatrix within the given matrix.

// Approach:
// The problem can be solved by reducing it to the 1D maximum subarray sum problem (Kadane's algorithm).
// We can iterate through all possible pairs of left and right columns. For each pair, we create a temporary 1D array representing the sum of elements in those columns for each row.
// This temporary array is then passed to Kadane's algorithm to find the maximum subarray sum, which corresponds to the maximum submatrix sum for the current column pair.
// We keep track of the overall maximum sum found across all column pairs.

// Time Complexity:
// The outer loops iterate through all possible pairs of left and right columns, which is O(m^2).
// Inside these loops, we iterate through all rows, which is O(n), to build the temporary sum array.
// Kadane's algorithm on the temporary array takes O(n) time.
// Thus, the total time complexity is O(m^2 * n).
// If we iterate through all possible pairs of top and bottom rows, the complexity would be O(n^2 * m). We take the minimum of these two.
// Therefore, the time complexity is O(min(n^2 * m, m^2 * n)).

// Space Complexity:
// We use a temporary 1D array to store the sum of elements for each row between the current left and right columns.
// This array has a size of n.
// Therefore, the space complexity is O(n).

// Optimal Solution:
class Solution {
    public int maximumSumRectangle(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int maxSum = Integer.MIN_VALUE;

        for (int left = 0; left < m; left++) {
            int[] temp = new int[n];
            for (int right = left; right < m; right++) {
                for (int i = 0; i < n; i++) {
                    temp[i] += mat[i][right];
                }
                maxSum = Math.max(maxSum, kadane(temp));
            }
        }
        return maxSum;
    }

    public int kadane(int[] arr) {
        int maxSoFar = Integer.MIN_VALUE;
        int currentMax = 0;
        for (int x : arr) {
            currentMax += x;
            if (currentMax > maxSoFar) {
                maxSoFar = currentMax;
            }
            if (currentMax < 0) {
                currentMax = 0;
            }
        }
        
        if (maxSoFar == Integer.MIN_VALUE) {
            int maxVal = Integer.MIN_VALUE;
            for(int x : arr) {
                maxVal = Math.max(maxVal, x);
            }
            return maxVal;
        }
        return maxSoFar;
    }
}

