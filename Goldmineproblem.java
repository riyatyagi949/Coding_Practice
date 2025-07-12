// Problem Statement:
// Given a gold mine `mat[][]` where each cell contains a positive integer representing the amount of gold.
// A miner can start from any row in the first column. From a given cell (row, col), the miner can move to:
// 1. (row - 1, col + 1) - diagonally up towards the right
// 2. (row, col + 1) - to the right
// 3. (row + 1, col + 1) - diagonally down towards the right
// Find the maximum amount of gold the miner can collect.

// Approach:
// This problem can be solved using dynamic programming. We want to find the maximum gold collected by reaching any cell in the last column.
// Let `dp[i][j]` represent the maximum gold collected to reach cell `(i, j)`.
// The base cases are the cells in the first column, `dp[i][0] = mat[i][0]`.

// For any other cell `(i, j)` where `j > 0`, the miner could have come from three possible cells in the previous column `(j-1)`:
// 1. `(i-1, j-1)` (diagonally up-left)
// 2. `(i, j-1)` (directly left)
// 3. `(i+1, j-1)` (diagonally down-left)

// So, `dp[i][j] = mat[i][j] + max(gold_from_previous_cells)`.
// We need to handle boundary conditions:
// - If `i-1` is out of bounds (i.e., `i=0`), the diagonal up-left path is not possible.
// - If `i+1` is out of bounds (i.e., `i=rows-1`), the diagonal down-left path is not possible.

// We can fill the `dp` table column by column, from left to right.
// The `dp` table will have the same dimensions as the input `mat`.

// Algorithm:
// 1. Initialize `rows = mat.length` and `cols = mat[0].length`.
// 2. Create a `dp` table of size `rows x cols`.
// 3. **Base Cases (First Column):** For each `i` from `0` to `rows-1`, set `dp[i][0] = mat[i][0]`.
// 4. **Fill DP Table (Columns 1 to cols-1):**
//    For each column `j` from `1` to `cols-1`:
//        For each row `i` from `0` to `rows-1`:
//            Initialize `maxGoldFromPrev = 0`.
//            a. **From directly left:** `maxGoldFromPrev = dp[i][j-1]`.
//            b. **From diagonally up-left:** If `i > 0`, `maxGoldFromPrev = max(maxGoldFromPrev, dp[i-1][j-1])`.
//            c. **From diagonally down-left:** If `i < rows-1`, `maxGoldFromPrev = max(maxGoldFromPrev, dp[i+1][j-1])`.
//            `dp[i][j] = mat[i][j] + maxGoldFromPrev`.
// 5. **Find Maximum Gold:** After filling the entire `dp` table, the maximum gold collected will be the maximum value in the last column of the `dp` table.
//    Initialize `maxGold = 0`.
//    For each `i` from `0` to `rows-1`, `maxGold = max(maxGold, dp[i][cols-1])`.
// 6. Return `maxGold`.

// Time Complexity:
// The algorithm iterates through each cell of the `rows x cols` matrix exactly once. For each cell, it performs constant time operations (comparisons and additions).
// Therefore, the time complexity is `O(rows * cols)`.
// Given constraints `rows, cols <= 500`, `500 * 500 = 250000` operations, which is efficient.

// Space Complexity:
// We use a `dp` table of the same size as the input matrix.
// Therefore, the space complexity is `O(rows * cols)`.
// This can be optimized to `O(rows)` if we only need the previous column's values to compute the current column. However, `O(rows * cols)` is acceptable given the constraints.
// Let's implement the `O(rows * cols)` solution first for clarity.

class Solution {
    static int goldMine(int n, int m, int mat[][]) {
        int[][] dp = new int[n][m];

        // Initialize the first column of dp table
        for (int i = 0; i < n; i++) {
            dp[i][0] = mat[i][0];
        }
        for (int j = 1; j < m; j++) { 
            for (int i = 0; i < n; i++) {
                int maxGoldFromPrev = 0;

                maxGoldFromPrev = dp[i][j - 1];
                if (i > 0) {
                    maxGoldFromPrev = Math.max(maxGoldFromPrev, dp[i - 1][j - 1]);
                }
                if (i < n - 1) {
                    maxGoldFromPrev = Math.max(maxGoldFromPrev, dp[i + 1][j - 1]);
                }
                dp[i][j] = mat[i][j] + maxGoldFromPrev;
            }
        }
        int maxGold = 0;
        for (int i = 0; i < n; i++) {
            maxGold = Math.max(maxGold, dp[i][m - 1]);
        }
         return maxGold;
    }
}