/**
 * Problem Statement: Get Minimum Squares (Lagrange's Four-Square Theorem)
 * -----------------------------------------------------------------------
 * Given a positive integer n, find the minimum number of perfect squares 
 * (square of an integer) that sum up to n.
 * * Note: Every positive integer can be expressed as a sum of square numbers 
 * (1 is a perfect square).
 * * Examples:
 * Input: n = 100, Output: 1 (10^2)
 * Input: n = 6, Output: 3 (1^2 + 1^2 + 2^2)
 * * Constraints:
 * 1 <= n <= 10^4
 *//**
     * Optimal Solution: Dynamic Programming
     * -------------------------------------
     * This problem has optimal substructure and overlapping subproblems, making 
     * it suitable for Dynamic Programming.
     * * Let dp[i] be the minimum number of perfect squares that sum up to 'i'.
     * * * Recurrence Relation:
     * dp[i] = 1 + min(dp[i - j*j])  for all j*j <= i
     * * The idea is to iterate through all perfect squares j*j less than or equal to i, 
     * assume one of those squares is used in the final sum, and then recursively 
     * find the minimum squares needed for the remaining value (i - j*j).
     * * * Base Case: dp[0] = 0 (Zero squares needed for sum zero).
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N * sqrt(N)), where N is the input integer.
 * - The outer loop runs N times (i from 1 to N).
 * - The inner loop runs sqrt(i) times (j from 1 to sqrt(i)).
 * - The total number of operations is approximately the sum of sqrt(i) for i=1 to N, 
 * which is mathematically bounded by O(N * sqrt(N)).
 *
 * * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the input integer.
 * - This space is required to store the DP table of size N+1.
 * * * Note on Alternative: Lagrange's Four-Square Theorem guarantees the answer is 
 * * always 1, 2, 3, or 4. For N <= 10^4, this DP approach is fast and general.
 */
// Optimal Solution in Java - 
class Solution {
    public int minSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0; 

        for (int i = 1; i <= n; i++) {
            dp[i] = i; 
            for (int j = 1; j * j <= i; j++) 
            {
                int square = j * j;
                dp[i] = Math.min(dp[i], 1 + dp[i - square]);
            }
        }
         return dp[n];
    }
}
