// Problem Statement:
// Given a 2D matrix mat[][] of size n x m, the task is to modify the matrix such that if mat[i][j] is 0, all the elements in the i-th row and j-th column are set to 0.
//
// Examples:
// Input:
// [[1, 2, 3],
//  [4, 0, 6],
//  [7, 8, 9]]
// Output:
// [[1, 0, 3],
//  [0, 0, 0],
//  [7, 0, 9]]
//
// Input:
// [[0, 1, 2, 0],
//  [3, 4, 5, 2],
//  [1, 3, 1, 5]]
// Output:
// [[0, 0, 0, 0],
//  [0, 4, 5, 0],
//  [0, 3, 1, 0]]
//
// Constraints:
// 1 <= n, m <= 500
// -2^31 <= mat[i][j] <= 2^31 - 1
//
// Approach:
// A naive approach would be to iterate through the matrix, and whenever a 0 is found at mat[i][j], set all elements in row i and column j to 0. However, this approach would cause a cascade of zeros, where a newly set zero might cause more rows and columns to be zeroed out. To avoid this, we need to first identify all the rows and columns that need to be zeroed out and then perform the modifications in a separate step.
//
// A more efficient approach is to use auxiliary arrays or the matrix itself to store which rows and columns should be zeroed.
//
// We can use two boolean arrays, `row` of size `n` and `col` of size `m`, to keep track of which rows and columns contain a zero.
// 1. First, iterate through the matrix. If `mat[i][j]` is 0, set `row[i]` to `true` and `col[j]` to `true`.
// 2. Then, iterate through the matrix again. If `row[i]` is `true` or `col[j]` is `true`, set `mat[i][j]` to 0.
//
// This approach has a time complexity of O(n*m) and a space complexity of O(n + m).
//
// An even more space-optimized approach (O(1) space, not counting the input matrix) can be achieved by using the first row and first column of the matrix as our auxiliary arrays.
// 1. First, determine if the first row or first column needs to be zeroed out by checking if they contain any zeros. Use two boolean variables `firstRowZero` and `firstColZero`.
// 2. Then, iterate through the rest of the matrix (from `mat[1][1]` to `mat[n-1][m-1]`). If `mat[i][j]` is 0, set `mat[i][0]` and `mat[0][j]` to 0. This uses the first row and first column as markers.
// 3. After marking, iterate through the matrix again (from `mat[1][1]` to `mat[n-1][m-1]`). If `mat[i][0]` is 0 or `mat[0][j]` is 0, set `mat[i][j]` to 0.
// 4. Finally, zero out the first row and first column if the initial check determined they should be.
//
// The following solution implements the O(1) space approach.
//
// Time Complexity: O(n*m), where n is the number of rows and m is the number of columns. We iterate through the matrix a constant number of times.
// Space Complexity: O(1), as we are using the matrix itself for marking, and a few constant space variables.

