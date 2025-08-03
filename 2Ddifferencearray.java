/**
 * Problem: 2D Difference Array
 * You are given a 2D integer matrix mat[][] of size n × m and a list of q operations opr[][]. Each operation is represented as an array [v, r1, c1, r2, c2], where v is the value to be added, (r1, c1) is the top-left cell of a submatrix, and (r2, c2) is the bottom-right cell of the submatrix (inclusive). For each of the q operations, add v to every element in the submatrix from (r1, c1) to (r2, c2). Return the final matrix after applying all operations.
 * 
 * * Approach:
 * The problem requires us to perform multiple range updates on a 2D matrix efficiently. A naive approach of iterating through each submatrix for every operation would result in a time complexity of O(q * n * m), which is too slow given the constraints.
 * We can use a 2D difference array to solve this problem more efficiently. A 2D difference array, let's call it `diff`, of the same size as the original matrix, helps us to perform range updates in O(1) time per operation.
 * The core idea of the 2D difference array is to represent the updates at specific points. For an update on a submatrix from (r1, c1) to (r2, c2) with value `v`, we perform the following operations on the `diff` array:
 * 1. `diff[r1][c1] += v`
 * 2. `diff[r1][c2 + 1] -= v` (if c2 + 1 is within bounds)
 * 3. `diff[r2 + 1][c1] -= v` (if r2 + 1 is within bounds)
 * 4. `diff[r2 + 1][c2 + 1] += v` (if both r2 + 1 and c2 + 1 are within bounds)
 * * After processing all `q` operations and updating the `diff` array, we can reconstruct the final matrix. The value at `mat[i][j]` will be the sum of all updates that affect this cell. We can compute this using a 2D prefix sum (or sum over rectangle) on the `diff` array.
 * We iterate through the `diff` array and for each cell `diff[i][j]`, we update it with `diff[i][j] += diff[i-1][j] + diff[i][j-1] - diff[i-1][j-1]` (handling boundary cases). This process gives us the cumulative sum of updates up to `(i, j)`.
 * Finally, the value of the original matrix at `mat[i][j]` is `mat[i][j] + diff[i][j]`.
 * 
 * * Algorithm steps:
 * 1. Initialize a `diff` matrix of size (n+1) x (m+1) with all zeros.
 * 2. Iterate through the `q` operations:
 * a. For each operation `[v, r1, c1, r2, c2]`, apply the updates to the `diff` matrix:
 * `diff[r1][c1] += v`
 * If `c2 + 1 < m`, `diff[r1][c2 + 1] -= v`
 * If `r2 + 1 < n`, `diff[r2 + 1][c1] -= v`
 * If `r2 + 1 < n` and `c2 + 1 < m`, `diff[r2 + 1][c2 + 1] += v`
 * 3. Create a result matrix `res` of size `n x m`, which is a deep copy of the original `mat`.
 * 4. Iterate through the result matrix `res` from `(0, 0)` to `(n-1, m-1)`:
 * a. Compute the prefix sum on the `diff` array:
 * If `i > 0`, `diff[i][j] += diff[i-1][j]`
 * If `j > 0`, `diff[i][j] += diff[i][j-1]`
 * If `i > 0` and `j > 0`, `diff[i][j] -= diff[i-1][j-1]`
 * b. Add the cumulative update to the result matrix:
 * `res[i][j] += diff[i][j]`
 * 5. Return the final `res` matrix.
 * 
 * * Time Complexity: O(n * m + q)
 * - O(q) to process all operations and update the difference array.
 * - O(n * m) to compute the prefix sums and update the original matrix.
 * - The total time complexity is dominated by the larger of O(n*m) and O(q), so O(n*m + q).
 * 
 * * Space Complexity: O(n * m)
 * - We need an additional 2D difference array of size O(n*m).
 */


       