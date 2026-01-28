/**
 * PROBLEM STATEMENT: 3651. Minimum Cost Path with Teleportations
 * --------------------------------------------------------------------------------
 * You are given an m x n 2D integer array 'grid' and an integer 'k'. 
 * You start at (0, 0) and aim to reach (m - 1, n - 1).
 * * Moves available:
 * 1. Normal move: Move right or down. Cost = grid[destination_cell].
 * 2. Teleportation: Move from (i, j) to (x, y) if grid[x][y] <= grid[i][j].
 * Cost = 0. You can use teleportation at most k times.
 * * Return the minimum total cost to reach (m - 1, n - 1).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: DYNAMIC PROGRAMMING WITH LAYERS
 * --------------------------------------------------------------------------------
 * This problem is solved using Dynamic Programming where we track the minimum cost 
 * to reach the destination using 't' teleports (where 0 <= t <= k).
 * * 1. DP Definition:
 * dp[i][j] represents the minimum cost to reach (m-1, n-1) starting from (i, j) 
 * with the remaining teleportation budget.
 * * 2. Direction of Computation:
 * Since moves are Right/Down or Teleport (to a cell with <= value), we compute 
 * from the target (m-1, n-1) backwards to the start (0, 0).
 * * 3. Handling Teleportation:
 * To teleport from (i, j) for 0 cost, we need to find min(dp[x][y]) among all 
 * cells where grid[x][y] <= grid[i][j].
 * We use an auxiliary array 'prev' where prev[v] stores the minimum DP value 
 * of any cell with grid value 'v'. We then perform a prefix-min sweep on 
 * 'prev' so that prev[v] = min(dp[x][y] for all grid[x][y] <= v).
 * * 4. Layered Iteration:
 * - Layer 0: Standard grid DP (only Right/Down moves).
 * - Layer 1 to k: For each cell, the new cost is min(Right_cost, Down_cost, Teleport_cost).
 * Teleport_cost for cell (i, j) is simply prev[grid[i][j]] calculated from the 
 * previous layer's results.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(k * (m * n + max_grid_value))
 * - m, n <= 80, k <= 10.
 * - For each k, we iterate over the grid (m*n) and the value range (max_grid_value = 10^4).
 * - Total operations approx: 10 * (6400 + 10000) = 164,000, which is very efficient.
 * * Space Complexity: O(m * n + max_grid_value)
 * - dp table of size m*n.
 * - prev array of size max_grid_value.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -

import java.util.Arrays;

 class Solution {
    public int minCost(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int max = -1;
        int[][] dp = new int[n][m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                max = Math.max(grid[i][j], max);
                if (i == n - 1 && j == m - 1) {
                    continue;
                }
                if (i == n - 1) {
                    dp[i][j] = grid[i][j + 1] + dp[i][j + 1];
                }
                 else if (j == m - 1) {
                    dp[i][j] = grid[i + 1][j] + dp[i + 1][j];
                } 
                else {
                    dp[i][j] =
                            Math.min(grid[i + 1][j] + dp[i + 1][j], grid[i][j + 1] + dp[i][j + 1]);
                }
            }
        }
        int[] prev = new int[max + 1];
        Arrays.fill(prev, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                prev[grid[i][j]] = Math.min(prev[grid[i][j]], dp[i][j]);
            }
        }
        for (int i = 1; i <= max; i++) {
            prev[i] = Math.min(prev[i], prev[i - 1]);
        }

        for (int tr = 1; tr <= k; tr++) {
            for (int i = n - 1; i >= 0; i--) {
                for (int j = m - 1; j >= 0; j--) {
                    if (i == n - 1 && j == m - 1) {
                        continue;
                    }

                    dp[i][j] = prev[grid[i][j]];
                    if (i == n - 1) {
                        dp[i][j] = Math.min(dp[i][j], grid[i][j + 1] + dp[i][j + 1]);
                    } 
                    else if (j == m - 1) {
                        dp[i][j] = Math.min(dp[i][j], grid[i + 1][j] + dp[i + 1][j]);
                    } 
                    else {
                        dp[i][j] = Math.min(dp[i][j], grid[i + 1][j] + dp[i + 1][j]);
                        dp[i][j] = Math.min(dp[i][j], grid[i][j + 1] + dp[i][j + 1]);
                    }
                }
            }
            Arrays.fill(prev, Integer.MAX_VALUE);
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    prev[grid[i][j]] = Math.min(prev[grid[i][j]], dp[i][j]);
                }
            }
            for (int i = 1; i <= max; i++) {
                prev[i] = Math.min(prev[i], prev[i - 1]);
            }
        }
        return dp[0][0];
    }
}

