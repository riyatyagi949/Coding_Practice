/**
 * Problem Statement: Increment Submatrices by One
 * -----------------------------------------------
 * Given a positive integer 'n', we start with an n x n 0-indexed integer matrix 'mat' of zeroes.
 * We are also given a 2D integer array 'queries'. For each query [r1, c1, r2, c2], 
 * we must add 1 to every element in the submatrix defined by the top-left corner (r1, c1) 
 * and the bottom-right corner (r2, c2).
 * Return the final matrix 'mat' after performing all queries.
 *
 * Constraints:
 * 1 <= n <= 500
 * 1 <= queries.length <= 10^4
 */
/**
     * Optimal Solution: 2D Difference Array (or 2D Prefix Sum Inverse)
     * -----------------------------------------------------------------
     * A naive approach would iterate through every cell in the submatrix for each query, 
     * resulting in a time complexity of O(Q * N^2) in the worst case (Q is number of queries), 
     * which is too slow (10^4 * 500^2 ≈ 2.5 billion).
     *
     * The optimal solution uses the 2D Difference Array technique to handle range updates 
     * in O(1) time per query, followed by a 2D prefix sum calculation to get the final matrix.
     *
     * * The core idea is to mark the corners of the submatrix update:
     * * For an update in range [(r1, c1) to (r2, c2)], we perform four constant-time updates 
     * * in a difference matrix `diff[n+1][n+1]`:
     * 1. **Start Point:** Add +1 at `diff[r1][c1]`. (Starts the increment)
     * 2. **Row End:** Subtract -1 at `diff[r1][c2 + 1]`. (Cancels the increment for the rest of row r1)
     * 3. **Column End:** Subtract -1 at `diff[r2 + 1][c1]`. (Cancels the increment for the rest of column c1)
     * 4. **Corner Compensation:** Add +1 at `diff[r2 + 1][c2 + 1]`. (Compensates for the double subtraction at this corner)
     *
     * * After processing all queries (O(Q)), we calculate the actual matrix values using a 
     * * 2D Prefix Sum operation on the `diff` matrix (O(N^2)).
     * * `mat[r][c] = diff[r][c] + mat[r-1][c] + mat[r][c-1] - mat[r-1][c-1]` (where mat is the final matrix).
     */
// Code - 
class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n + 1][n + 1];

        for (int[] q : queries)
         {
            int r1 = q[0], c1 = q[1], r2 = q[2], c2 = q[3];
            diff[r1][c1] += 1;
            diff[r1][c2 + 1] -= 1;
            diff[r2 + 1][c1] -= 1;
            diff[r2 + 1][c2 + 1] += 1;
        }
        for (int i = 0; i < n; i++)
         {
            for (int j = 1; j < n; j++) 
            {
                diff[i][j] += diff[i][j - 1];
            }
        }
        for (int j = 0; j < n; j++)
         {
            for (int i = 1; i < n; i++) 
            {
                diff[i][j] += diff[i - 1][j];
            }
        }
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++)
         {
            for (int j = 0; j < n; j++)
             {
                ans[i][j] = diff[i][j];
            }
        }
        return ans;
    }
}
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(Q + N^2), where Q is the number of queries and N is the matrix dimension.
 * - Processing all queries using the difference array takes O(Q) time (O(1) per query).
 * - Calculating the final matrix using the 2D prefix sum takes O(N^2) time (visiting every cell once).
 * - Since N is up to 500 (N^2 = 250,000) and Q is up to 10^4, this is highly efficient.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N^2)
 * - We use an auxiliary difference matrix `diff` of size (N+1) x (N+1), which requires O(N^2) space.
 * - We also create the final result matrix `ans` of size N x N, also O(N^2).
 */