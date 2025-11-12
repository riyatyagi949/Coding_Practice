/**
 * Problem Statement: 474. Ones and Zeroes
 * ---------------------------------------
 * Given an array of binary strings 'strs' and two integers 'm' and 'n'.
 * Return the size of the largest subset of 'strs' such that the total number 
 * of '0's in the subset is at most 'm' and the total number of '1's is at most 'n'.
 * * Constraints:
 * 1 <= strs.length <= 600
 * 1 <= m, n <= 100
 *//**
     * Optimal Solution: 0/1 Knapsack Dynamic Programming with Two Dimensions
     * ----------------------------------------------------------------------
     * This problem is equivalent to the 0/1 Knapsack problem where:
     * - Items: Each string in 'strs'.
     * - Item Weight 1: The count of '0's in the string (must be <= m).
     * - Item Weight 2: The count of '1's in the string (must be <= n).
     * - Item Value: Always 1 (we want to maximize the count of strings/subset size).
     * - Knapsack Capacity: Defined by the limits 'm' and 'n'.
     *
     * * DP State Definition:
     * dp[i][j] = The maximum size of a subset that can be formed using at most 
     * 'i' zeros and 'j' ones.
     *
     * * DP Transition:
     * For each string 's' with (zeros, ones) counts, we iterate the DP table 
     * backward (to ensure each string is used at most once, like 0/1 Knapsack).
     * dp[i][j] = max(
     * // 1. Don't include the current string 's'
     * dp[i][j], 
     * // 2. Include the current string 's' (if resources allow)
     * 1 + dp[i - zeros][j - ones]
     * )
     */
// Code -

class Solution{
    public int findMaxForm(String[] strs, int m, int n)
     {
        int[][] dp = new int[m + 1][n + 1];
        
        for (String s : strs)
         {
        int zeros = 0, ones = 0;
            for (char c : s.toCharArray())
             {
                if (c == '0')
                 {
                    zeros++;
                } 
                else {
                    ones++;
                }
            }
            for (int i = m; i >= zeros; i--)
             {
                for (int j = n; j >= ones; j--)
                 {
                        dp[i][j] = Math.max(dp[i][j], 1 + dp[i - zeros][j - ones]
                    );
                }
            }
        }
        return dp[m][n];
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(L * M * N + T), where:
 * - L is the number of strings in 'strs' (up to 600).
 * - M is the maximum number of zeros (up to 100).
 * - N is the maximum number of ones (up to 100).
 * - T is the time to count zeros/ones for all strings (T is bounded by L * max_str_length, L*100).
 * - The dominant part is the triple nested loop (L strings * M capacity * N capacity): O(L * M * N).
 * - Since L <= 600, M <= 100, N <= 100, the complexity is roughly 600 * 100 * 100 = 6,000,000 operations, which is efficient.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(M * N), where M is the maximum number of zeros and N is the maximum number of ones.
 * - This space is required to store the 2D DP table: `dp[m+1][n+1]`.
 * - Since M, N <= 100, the space complexity is O(100 * 100) = O(10000), which is minimal.
 */