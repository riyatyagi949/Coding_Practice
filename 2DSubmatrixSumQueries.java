/**
 * Problem Statement: 2D Submatrix Sum Queries
 * --------------------------------------------
 * Given a 2D integer matrix mat[][] and a list of queries queries[][],
 * answer a series of submatrix sum queries. Each query is [r1, c1, r2, c2], 
 * representing the top-left (r1, c1) and bottom-right (r2, c2) coordinates of the submatrix.
 * The task is to return a list of integers, where each element is the sum of the elements 
 * within the specified submatrix for the corresponding query.
 *
 * Constraints:
 * 1 ≤ n × m, q ≤ 10^5 (Total elements and total queries are up to 10^5)
 * 0 ≤ mat[i][j] ≤ 10^4
 */
/**
     * Optimal Solution: 2D Prefix Sum (or Summed Area Table)
     * -------------------------------------------------------
     * This problem requires answering many sum queries quickly. A naive approach of 
     * iterating over the submatrix for each query would take O(N*M*Q), which is too slow.
     * The optimal solution is to pre-compute a **2D Prefix Sum Array (PS)**, which 
     * allows any submatrix sum to be calculated in O(1) time. 
     *
     * * 1. Pre-computation (PS[i][j]):
     * PS[i][j] stores the sum of all elements in the rectangle from (0, 0) to (i, j).
     * The recurrence relation to compute PS[i][j] efficiently is:
     * PS[i][j] = mat[i][j] + PS[i-1][j] + PS[i][j-1] - PS[i-1][j-1]
     * (The terms PS[i-1][j] and PS[i][j-1] cover the required areas, but the 
     * overlap PS[i-1][j-1] is counted twice, hence it's subtracted once).
     *
     * * 2. Query Calculation (Sum of submatrix (r1, c1) to (r2, c2)):
     * The sum of the target submatrix is derived using inclusion-exclusion:
     * Sum(r1, c1, r2, c2) = PS[r2][c2]                      // Total area (0,0) to (r2,c2)
     * - PS[r1 - 1][c2]                  // Exclude area above
     * - PS[r2][c1 - 1]                  // Exclude area to the left
     * + PS[r1 - 1][c1 - 1]              // Include back the double-subtracted top-left corner
     *
     * * This approach splits the overall complexity into a single O(N*M) pre-computation 
     * * step and O(1) time per query.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N*M + Q), where N is the number of rows, M is the number of columns, and Q is the number of queries.
 * - **Pre-computation:** O(N*M) to build the 2D prefix sum array.
 * - **Queries:** O(Q) total, as each query is answered in O(1) time.
 * - This is highly efficient, especially when Q is large.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N*M), where N is the number of rows and M is the number of columns.
 * - This space is required to store the auxiliary 2D Prefix Sum array (PS).
 */
// Code - 
import java.util.*;

class Solution {
    public ArrayList<Integer> prefixSum2D(int[][] mat, int[][] queries) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] ps = new int[n][m];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++) 
            {
                ps[i][j] = mat[i][j];

                if (i > 0) ps[i][j] += ps[i - 1][j];
                if (j > 0) ps[i][j] += ps[i][j - 1];
                if (i > 0 && j > 0) ps[i][j] -= ps[i - 1][j - 1];
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {
            int r1 = q[0];
            int c1 = q[1];
            int r2 = q[2];
            int c2 = q[3];

            int sum = ps[r2][c2];

            if (r1 > 0) sum -= ps[r1 - 1][c2];
            if (c1 > 0) sum -= ps[r2][c1 - 1];
            if (r1 > 0 && c1 > 0) sum += ps[r1 - 1][c1 - 1];

            ans.add(sum);
        }
        return ans;
    }
}

