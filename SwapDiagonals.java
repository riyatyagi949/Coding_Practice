/**
 * Problem Statement: Swap Diagonals
 * --------------------------------
 * Given a square matrix mat[][], the task is to swap the elements of the 
 * major and minor diagonals.
 * * - Major Diagonal: Elements mat[i][i] (row index equals column index).
 * - Minor Diagonal: Elements mat[i][n - 1 - i] (sum of row and column indices equals n - 1).
 * The swap must occur between the elements of the major and minor diagonals *in the same row*.
 *
 * Example:
 * Input: mat[][] = [[0, 1, 2],
 * [3, 4, 5],
 * [6, 7, 8]]
 * Output: [[2, 1, 0], [3, 4, 5], [8, 7, 6]]
 * * Constraints:
 * 1 <= mat.size() <= 500
 */
/**
     * Optimal Solution: Single Pass Iteration
     * ----------------------------------------
     * Since the requirement is to swap the diagonal elements *in the same row*, we only 
     * need a single loop that iterates through each row of the matrix.
     * * For a matrix of size N x N and a current row index 'i':
     * 1. The element on the **Major Diagonal** is at index (i, i).
     * 2. The element on the **Minor Diagonal** is at index (i, N - 1 - i).
     * * The algorithm simply iterates from i = 0 to N-1 and swaps mat[i][i] with 
     * mat[i][n - 1 - i]. 
     * * Note on the center element:
     * - If N is odd, the center element mat[N/2][N/2] is part of BOTH diagonals.
     * - When i = N/2, both indices (i, i) and (i, N - 1 - i) point to the same center element.
     * - The swap operation mat[i][i] = mat[i][i] is redundant but harmless, and the 
     * optimal single-loop structure handles this case naturally without special checks.
     * * @param mat The square matrix to modify in-place.
     */
// Code -
class Solution {
    public void swapDiagonal(int[][] mat) {
        int n = mat.length;

        for (int i = 0; i < n; i++) {
            int temp = mat[i][i];
            mat[i][i] = mat[i][n - 1 - i];
            mat[i][n - 1 - i] = temp;
        }
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the number of rows/columns of the square matrix.
 * - The algorithm uses a single loop that iterates exactly N times.
 * - Inside the loop, only constant time O(1) operations (index calculation and swap) are performed.
 * - Since N <= 500, this is extremely fast.
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) auxiliary space.
 * - The operation is performed in-place on the input matrix.
 * - Only a few variables (n, i, temp) are used for storage.
 */
