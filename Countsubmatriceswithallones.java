// Problem Statement:
// Given an m x n binary matrix mat, return the number of submatrices that have all ones.

// Approach:
// A dynamic programming approach is used. We first compute a 2D array, `heights`, where `heights[i][j]` stores the number of consecutive ones vertically, ending at `mat[i][j]`. This is done by iterating through the matrix and, if `mat[i][j]` is 1, setting `heights[i][j] = heights[i-1][j] + 1` (or 1 if on the first row). If `mat[i][j]` is 0, `heights[i][j]` is 0.
// Once the `heights` array is populated, we iterate through it. For each cell `(i, j)`, if `mat[i][j]` is 1, we count all submatrices with `(i, j)` as their bottom-right corner.
// To do this, we fix the bottom-right corner at `(i, j)` and extend to the left. The height of a submatrix is limited by the minimum height of the columns we are currently considering. We iterate from `k = j` down to `0`, and for each step, we find the minimum height among `heights[i][k]` and the heights of columns to its right up to `j`. This minimum height dictates how many new submatrices of width `j - k + 1` can be formed. The sum of these minimum heights across all `k` values gives the total number of submatrices ending at `(i, j)`.

// Time Complexity:
// The overall time complexity is O(m * n^2) due to the nested loops. The first pass to build the `heights` matrix takes O(m * n). The second part iterates through each cell and performs an inner loop up to n times, leading to O(m * n^2).

// Space Complexity:
// The space complexity is O(m * n) to store the `heights` matrix.

// Optimal Solution - 

class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] heights = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    heights[i][j] = (i > 0) ? heights[i - 1][j] + 1 : 1;
                } 
                else {
                    heights[i][j] = 0;
                }
            }
        }

        int totalCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    int minHeight = heights[i][j];
                    for (int k = j; k >= 0; k--) {
                        minHeight = Math.min(minHeight, heights[i][k]);
                        totalCount += minHeight;
                    }
                }
            }
        }
        return totalCount;
    }
}