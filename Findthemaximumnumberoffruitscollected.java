    /*
     * Problem Statement:
     * A dungeon grid of size n x n contains rooms with fruits. Three children start at corners (0, 0),
     * (0, n - 1), and (n - 1, 0) and must make exactly n - 1 moves to reach the final room (n - 1, n - 1).
     * The moves are specific for each child:
     * - Child 1 (from (0, 0)): moves from (i, j) to (i + 1, j + 1), (i + 1, j), or (i, j + 1).
     * - Child 2 (from (0, n - 1)): moves from (i, j) to (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
     * - Child 3 (from (n - 1, 0)): moves from (i, j) to (i - 1, j + 1), (i, j + 1), or (i + 1, j + 1).
     * Fruits are collected once, even if multiple children visit the same room. The goal is to find the maximum total fruits.
     *
     * Approach:
     * This problem can be decomposed into three independent pathfinding problems and then combined, as long as we
     * correctly handle the overlapping fruits. The key insight comes from analyzing the movement rules and the
     * "exactly n - 1 moves" constraint.
     *
     * 1.  **Analyze Child 1's Path:**
     * - Child 1 moves from (0, 0) to (n - 1, n - 1) in `n - 1` steps.
     * - A path from (0, 0) to (n - 1, n - 1) requires `n - 1` increments in the row index and `n - 1` increments in the column index.
     * - The moves are `(i + 1, j + 1)`, `(i + 1, j)`, and `(i, j + 1)`.
     * - A `(i + 1, j + 1)` move increments both row and column by 1.
     * - An `(i + 1, j)` move increments row by 1.
     * - An `(i, j + 1)` move increments column by 1.
     * - To achieve `n - 1` total increments for both row and column in `n - 1` moves, the child must make `n - 1` moves of type `(i + 1, j + 1)`.
     * - This means Child 1's path is fixed: `(0, 0) -> (1, 1) -> ... -> (n - 1, n - 1)`.
     * - We can simply calculate the sum of fruits along this path.
     *
     * 2.  **Analyze Child 2's Path:**
     * - Child 2 moves from (0, n - 1) to (n - 1, n - 1) in `n - 1` steps.
     * - The moves are `(i + 1, j - 1)`, `(i + 1, j)`, and `(i + 1, j + 1)`.
     * - Each move increments the row index by exactly 1.
     * - To reach row `n - 1` from row `0`, `n - 1` row increments are needed, which aligns perfectly with the `n - 1` moves.
     * - The column index must change from `n - 1` to `n - 1`, meaning the net change in column is 0.
     * - A dynamic programming approach can be used to find the maximum fruits for this path.
     * - Let `dp[i][j]` be the maximum fruits collected to reach room `(i, j)`.
     * - `dp[i][j] = fruits[i][j] + max(dp[i-1][j+1], dp[i-1][j], dp[i-1][j-1])`.
     * - This DP calculation is for a single path.
     *
     * 3.  **Analyze Child 3's Path:**
     * - Child 3 moves from (n - 1, 0) to (n - 1, n - 1) in `n - 1` steps.
     * - The moves are `(i - 1, j + 1)`, `(i, j + 1)`, and `(i + 1, j + 1)`.
     * - Each move increments the column index by exactly 1.
     * - To reach column `n - 1` from column `0`, `n - 1` column increments are needed, which aligns with the `n - 1` moves.
     * - The row index must change from `n - 1` to `n - 1`, a net change of 0.
     * - We can use DP to find the maximum fruits for this path.
     * - Let `dp[i][j]` be the maximum fruits collected to reach room `(i, j)`.
     * - `dp[i][j] = fruits[i][j] + max(dp[i+1][j-1], dp[i][j-1], dp[i-1][j-1])`.
     *
     * **Handling Overlaps:**
     * The three paths are distinct except at the start and end points. The start points are unique. The end point `(n - 1, n - 1)` is common. The fruits at `(n - 1, n - 1)` will be counted once. The problem states that if two or more children enter the same room, fruits are collected once. This means we should calculate the sum of fruits for each path and then subtract the fruits from any overlapping rooms.
     *
     * - Child 1's path is the main diagonal.
     * - Child 2's path moves towards the main diagonal from the top-right.
     * - Child 3's path moves towards the main diagonal from the bottom-left.
     *
     * The paths of Child 2 and Child 3 might intersect. The example solution provided calculates the total by summing the fruits from the three independent paths. This implies that the problem statement is a bit simplified, and the optimal paths of the three children do not intersect, or if they do, the solution presented assumes a simple way of handling it.
     *
     * Given the provided solution code, the approach is to:
     * 1. Calculate the fruits for Child 1's fixed path (`getTopLeft`).
     * 2. Calculate the maximum fruits for Child 2's path (`getTopRight`) using a DP approach.
     * 3. Calculate the maximum fruits for Child 3's path (`getBottomLeft`) using a DP approach.
     * 4. Sum the three results. The comment in the code suggests that the final point `(n-1, n-1)` is double-counted, so it should be subtracted, but the code provided has this part commented out. This implies that the `getTopLeft` function includes the final room, and the `getTopRight` and `getBottomLeft` also include it. However, a closer look at the DP calculation reveals that it starts from the initial positions and builds up, so the final fruit count is naturally included. The example's solution suggests that the `fruits` grid is not modified, so the fruits at intersections are counted multiple times and need to be subtracted.
     *
     * The provided solution is a simplified approach, which might be correct under the assumption that the three optimal paths do not intersect (other than at the final cell). The `getTopLeft` function calculates the fruits along the main diagonal. The `getTopRight` and `getBottomLeft` functions calculate the maximum fruits for a single path using dynamic programming, but they do not account for fruits already collected by other children. The provided solution adds these three path sums, assuming that the paths (except for the endpoint) are disjoint. This is likely due to the hard constraint of `n-1` moves.
     *
     * Time Complexity:
     * - `getTopLeft` is O(N).
     * - `getTopRight` and `getBottomLeft` use a nested loop with a constant number of inner loops, resulting in O(N^2).
     * - The overall time complexity is **O(N^2)**.
     *
     * Space Complexity:
     * - The DP tables `dp` for `getTopRight` and `getBottomLeft` are of size N x N.
     * - The overall space complexity is **O(N^2)**.
     */

import java.util.*;
   class Solution {
     public int maxCollectedFruits(int[][] fruits) {
        final int n = fruits.length;
        return getTopLeft(fruits) + getTopRight(fruits) + getBottomLeft(fruits) //
                - 2 * fruits[n - 1][n - 1];
    }

    private int getTopLeft(int[][] fruits) {
        final int n = fruits.length;
        int res = 0;
        for (int i = 0; i < n; ++i)
            res += fruits[i][i];
        return res;
    }

    private int getTopRight(int[][] fruits) {
        final int n = fruits.length;
        int[][] dp = new int[n][n];

        for(int[] row : dp) Arrays.fill(row, -1);
        dp[0][n - 1] = fruits[0][n - 1];

        for (int x = 0; x < n; ++x)
            for (int y = 0; y < n; ++y) {
                if (dp[x][y] == -1) 
                continue;
                
                int[] dr = {1, 1, 1};
                int[] dc = {-1, 0, 1};
                for(int i = 0; i < 3; i++){
                    int newX = x + dr[i];
                    int newY = y + dc[i];
                    
                    if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
                         if(newX == n-1 && newY == n-1){
                             dp[newX][newY] = Math.max(dp[newX][newY], dp[x][y] + fruits[newX][newY]);
                         } 
                         else if (newX < newY) {
                            dp[newX][newY] = Math.max(dp[newX][newY], dp[x][y] + fruits[newX][newY]);
                         }
                    }
                }
            }
        return dp[n - 1][n - 1];
    }
    private int getBottomLeft(int[][] fruits) {
        final int n = fruits.length;
        int[][] dp = new int[n][n];

        for(int[] row : dp) Arrays.fill(row, -1);
        dp[n - 1][0] = fruits[n - 1][0];
        
        for (int x = n-1; x >= 0; --x)
            for (int y = 0; y < n; ++y) {
                if (dp[x][y] == -1) 
                continue;
                
                int[] dr = {-1, 0, 1};
                int[] dc = {1, 1, 1};
                
                for(int i = 0; i < 3; i++){
                    int newX = x + dr[i];
                    int newY = y + dc[i];
                    
                    if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
                        if (newX == n-1 && newY == n-1){
                            dp[newX][newY] = Math.max(dp[newX][newY], dp[x][y] + fruits[newX][newY]);
                        } 
                        else if (newX > newY) {
                             dp[newX][newY] = Math.max(dp[newX][newY], dp[x][y] + fruits[newX][newY]);
                        }
                    }
                }
            }
        return dp[n - 1][n - 1];
    }
}