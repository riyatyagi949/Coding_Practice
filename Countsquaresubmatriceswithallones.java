// Problem Statement:
// Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

// Approach:
// This problem can be solved efficiently using dynamic programming. We can create a DP matrix of the same size as the input matrix.
// Let dp[i][j] represent the size of the largest square submatrix with all ones that has its bottom-right corner at (i, j).

// The recurrence relation is as follows:
// If matrix[i][j] is '0', then no square submatrix can end at this cell, so dp[i][j] = 0.
// If matrix[i][j] is '1', then a square of side 1 can be formed. To form a larger square, the cells to its top, left, and top-left must also be '1's.
// The size of the largest square ending at (i, j) is determined by the minimum of the sizes of the largest squares ending at the adjacent cells:
// - dp[i-1][j] (top)
// - dp[i][j-1] (left)
// - dp[i-1][j-1] (top-left)
// A square of size k at (i, j) requires squares of size k-1 at its top, left, and top-left.
// So, dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]).

// The total count of square submatrices with all ones is the sum of all values in the DP matrix. Each dp[i][j] value represents the number of new squares formed with (i, j) as the bottom-right corner. For example, if dp[i][j] = 3, it means a square of side 3 exists, which also contains a square of side 2 and a square of side 1. So a square of side 3 contributes 3 to the total count (one of size 1, one of size 2, and one of size 3).

// We can optimize the space by modifying the input matrix in place, which reduces the space complexity to O(1).
// The algorithm proceeds as follows:
// 1. Initialize a `count` variable to 0.
// 2. Iterate through the matrix starting from the second row and second column (i=1, j=1).
// 3. If matrix[i][j] is '1', update its value: matrix[i][j] = 1 + min(matrix[i-1][j], matrix[i][j-1], matrix[i-1][j-1]).
// 4. Sum up all the values in the modified matrix. This sum will be the total count of square submatrices.

// Time Complexity:
// O(m * n), where m is the number of rows and n is the number of columns. We iterate through the matrix once.

// Space Complexity:
// O(1) if we modify the input matrix in-place. O(m * n) if we use an auxiliary DP matrix.

// Optimal Solution:
class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1 && i > 0 && j > 0) {
                    matrix[i][j] = 1 + Math.min(matrix[i - 1][j], Math.min(matrix[i][j - 1], matrix[i - 1][j - 1]));
                }
                count += matrix[i][j];
            }
        }
        return count;
    }
}