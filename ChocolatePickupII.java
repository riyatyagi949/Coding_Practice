/**
 * Problem Statement: Chocolate Pickup II (Equivalent to LeetCode 741: Cherry Pickup)
 * ----------------------------------------------------------------------------------
 * Given an N x N matrix mat[][], find the maximum number of chocolates a robot can collect 
 * by starting at (0, 0), moving to (n-1, n-1), and then returning to (0, 0).
 *
 * * Movement Constraints:
 * - Forward (0,0) to (n-1, n-1): Only Right (i, j+1) or Down (i+1, j).
 * - Backward (n-1, n-1) to (0, 0): Only Left (i, j-1) or Up (i-1, j).
 * - Blocked cells (-1) cannot be visited.
 * - Collected chocolates are removed (mat[i][j] becomes 0 for next visit).
 * - If no valid path exists, return 0.
 *
 * Constraints: 1 <= n <= 50, -1 <= mat[i][j] <= 100
 *//**
     * Optimal Solution: Two-Robot Dynamic Programming (Memoization)
     * -------------------------------------------------------------
     * The problem of finding a forward path and then a backward path with resource
     * depletion is equivalent to finding two non-overlapping paths from (0, 0) to (n-1, n-1).
     *
     * * State Definition:
     * We track two robots moving simultaneously from (0, 0) to (n-1, n-1). Since they 
     * both take the same number of steps (r + c) to reach any cell, we only need three 
     * independent variables for the state:
     * - r1: Row of Robot 1
     * - c1: Column of Robot 1
     * - r2: Row of Robot 2
     * The column of Robot 2 is implicitly determined by the total number of steps, 
     * k = r1 + c1. Thus, c2 = k - r2 = (r1 + c1) - r2.
     * State: dp[r1][c1][r2]
     *
     * * Transition (4 possibilities for the next step):
     * Both robots move one step (Down or Right).
     * 1. (R1 Down, R2 Down): dfs(r1 + 1, c1, r2 + 1)
     * 2. (R1 Down, R2 Right): dfs(r1 + 1, c1, r2) [r2 is row, next c2 is c2+1]
     * 3. (R1 Right, R2 Down): dfs(r1, c1 + 1, r2 + 1)
     * 4. (R1 Right, R2 Right): dfs(r1, c1 + 1, r2)
     *
     * * Base Case:
     * If Robot 1 reaches (n-1, n-1), return the chocolate at that cell.
     *
     * * Collection Logic:
     * - Collect mat[r1][c1].
     * - If the robots are NOT on the same cell (r1 != r2 or c1 != c2), collect mat[r2][c2] as well.
     * - **Crucially, the chocolate is NOT actually set to 0 in the grid.** The memoization 
     * handles the "collected once" constraint because the DP state `dp[r1][c1][r2]` implicitly 
     * defines all previous collection points. If R1 and R2 visit the same cell, the collection 
     * is only counted once for that state.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N^3), where N is the size of the square matrix (N x N).
 * - The time complexity is determined by the number of unique states in the DP table.
 * - The state is defined by (r1, c1, r2). Since c2 is dependent, we have three independent
 * variables, each ranging from 0 to N-1.
 * - Total states = N * N * N = N^3.
 * - Each state computation involves O(1) arithmetic and O(1) recursive calls/memoization.
 * - Overall Complexity: O(N^3). (With N <= 50, N^3 is around 125,000, which is highly efficient).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N^3), where N is the size of the matrix.
 * - This space is required to store the memoization table `dp[N][N][N]`.
 * - The recursion depth is at most 2*N - 2 (the path length), which is negligible compared to the DP table size.
 */
// Optimal Solution in Java -
class Solution {
    int n;
    int[][] grid;
    Integer[][][] dp;

    public int chocolatePickup(int[][] mat)
    {
        this.n = mat.length;
        this.grid = mat;
        this.dp = new Integer[n][n][n];
        int res = Math.max(0, dfs(0, 0, 0));
        return res;
    }
    private int dfs(int r1, int c1, int r2)
    {
        int c2 = r1 + c1 - r2;
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1)
            return Integer.MIN_VALUE;

        if (r1 == n - 1 && c1 == n - 1)
            return grid[r1][c1];

        if (dp[r1][c1][r2] != null)
            return dp[r1][c1][r2];

        int chocolates = grid[r1][c1];
        if (r1 != r2 || c1 != c2)
            chocolates += grid[r2][c2];

        int ans = Math.max(
            Math.max(dfs(r1 + 1, c1, r2 + 1), dfs(r1 + 1, c1, r2)), 
            Math.max(dfs(r1, c1 + 1, r2 + 1), dfs(r1, c1 + 1, r2))
        );
        chocolates += ans;
        dp[r1][c1][r2] = chocolates;
        return chocolates;
    }
}
