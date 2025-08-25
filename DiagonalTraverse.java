/**
 * Problem Statement:
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 *
 * Example 1:
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,4,7,5,3,6,8,9]
 *
 * Example 2:
 * Input: mat = [[1,2],[3,4]]
 * Output: [1,2,3,4]
 *
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 *
 * Approach:
 * We can traverse the matrix diagonally. We will use a flag to switch between going up-right and down-left.
 * We can maintain two pointers, `row` and `col`, for our current position.
 *
 * We will iterate until we have visited all `m * n` elements.
 *
 * If we are moving up-right:
 * - We add the current element `mat[row][col]` to our result list.
 * - We move to the next position: `row--`, `col++`.
 * - If we hit the top boundary (`row < 0`), we adjust our position and change direction.
 * - If we haven't hit the right boundary (`col < n`), we increment `row` to `0` and keep `col`.
 * - If we have hit the right boundary (`col == n`), we increment `row` by `2` and decrement `col` by `1` to move to the next valid position, and then change direction.
 * - If we hit the right boundary (`col == n`), we adjust our position and change direction.
 * - We increment `row` and decrement `col` by `1`.
 * - We then switch the direction flag.
 *
 * If we are moving down-left:
 * - We add the current element `mat[row][col]` to our result list.
 * - We move to the next position: `row++`, `col--`.
 * - If we hit the bottom boundary (`row == m`), we adjust our position and change direction.
 * - We decrement `col` by `2` and increment `row` by `1`.
 * - If we hit the left boundary (`col < 0`), we adjust our position and change direction.
 * - If we haven't hit the bottom boundary (`row < m`), we keep `row` and increment `col` to `0`.
 * - If we have hit the bottom boundary (`row == m`), we decrement `col` by `2` and increment `row` by `1` to move to the next valid position, and then change direction.
 * - We then switch the direction flag.
 *
 * This approach handles all edge cases by carefully checking boundaries and adjusting the pointers and direction.
 *
 * Time Complexity:
 * O(m * n), as we visit each element of the matrix exactly once.
 *
 * Space Complexity:
 * O(m * n), to store the result array.
 */

//  Optimal  Solution - 
class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        int row = 0, col = 0;
        boolean goingUp = true;

        for (int i = 0; i < m * n; i++) {
            result[i] = mat[row][col];

            if (goingUp) {
                if (row == 0 && col < n - 1) {
                    col++;
                    goingUp = false;
                } 
                else if (col == n - 1) {
                    row++;
                    goingUp = false;
                } 
                else {
                    row--;
                    col++;
                }
            } 
            else {
                if (col == 0 && row < m - 1) {
                    row++;
                    goingUp = true;
                } 
                else if (row == m - 1) {
                    col++;
                    goingUp = true;
                } 
                else {
                    row++;
                    col--;
                }
            }
        }
        return result;
    }
}