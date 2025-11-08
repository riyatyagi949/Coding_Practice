/**
 * Problem Statement: Number of paths in a matrix with k coins
 * -----------------------------------------------------------
 * You are given a matrix mat[][] of size n x m, where each of its cells contains 
 * some coins. Count the number of ways to collect exactly 'k' coins while moving 
 * from the top-left cell (0, 0) to the bottom-right cell (n-1, m-1).
 * From a cell (i, j), you can only move to cell (i+1, j) (down) or (i, j+1) (right).
 *
 * Constraints:
 * 1 <= k <= 100
 * 1 <= n, m <= 100
 *//**
     * Optimal Solution: Dynamic Programming (Memoization)
     * ----------------------------------------------------
     * This problem asks for the count of paths with a specific sum, which is a classic 
     * application of Dynamic Programming. Since the path length is fixed (n + m - 1 steps), 
     * we can define the DP state based on the current cell (i, j) and the **remaining sum** * required to reach 'k'.
     * * * State Definition: dp[i][j][sum]
     * dp[i][j][sum] stores the number of ways to reach the bottom-right cell (n-1, m-1) 
     * starting from cell (i, j) such that the total collected coins **from this point forward** * is exactly 'sum'.
     *
     * * Recurrence Relation:
     * dp[i][j][sum] = dp[i+1][j][sum - mat[i][j]] + dp[i][j+1][sum - mat[i][j]]
     * * * Base Cases:
     * 1. If (i, j) is out of bounds: return 0.
     * 2. If sum < 0: return 0 (overshot the target).
     * 3. If (i, j) is the bottom-right cell (n-1, m-1): return 1 if mat[i][j] == sum, else 0.
     *
     * The final answer is obtained by calling the helper function starting at (0, 0) 
     * with the target sum k: `helper(mat, 0, 0, k, dp)`.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N * M * K), where N is the number of rows, M is the number of columns, and K is the target coin sum.
 * - The time complexity is determined by the number of unique states in the DP table.
 * - The state is defined by (i, j, sum).
 * - i ranges from [0, N-1] (N possibilities).
 * - j ranges from [0, M-1] (M possibilities).
 * - sum ranges from [0, K] (K+1 possibilities).
 * - Since each state is computed exactly once in O(1) time (after recursive calls return), 
 * the total time is proportional to the number of states: O(N * M * K).
 * - Given N, M, K <= 100, N*M*K is about 1,000,000 operations, which is very fast.
 * * Space Complexity Analysis:
 * --------------------------
 * O(N * M * K)
 * - This space is required to store the DP table `dp[N][M][K+1]`.
 */
// Optimal Solution in Java - 

class Solution {
    public int numberOfPath(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        
        Integer[][][] dp = new Integer[n][m][k + 1];
        return helper(mat, 0, 0, k, dp);
    }
    private int helper(int[][] mat, int i, int j, int sum, Integer[][][] dp) 
    {
        int n = mat.length;
        int m = mat[0].length;
        
        if (i >= n || j >= m) 
        return 0;

        if (sum < 0) 
        return 0;

        if (i == n - 1 && j == m - 1) 
        {
            return (sum == mat[i][j]) ? 1 : 0;
        }

        if (dp[i][j][sum] != null)
        return dp[i][j][sum];

        int down = helper(mat, i + 1, j, sum - mat[i][j], dp);
        int right = helper(mat, i, j + 1, sum - mat[i][j], dp);

        dp[i][j][sum] = down + right;
        return dp[i][j][sum];
    }
}
