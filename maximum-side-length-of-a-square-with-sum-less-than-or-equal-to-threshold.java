/**
 * PROBLEM STATEMENT: 1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
 * ------------------------------------------------------------------------------------------------
 * Given a m x n matrix 'mat' and an integer 'threshold', return the maximum side-length of a 
 * square with a sum less than or equal to threshold. If no such square exists, return 0.
 *
 * Example 1:
 * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * Output: 2
 * * Constraints:
 * - m, n <= 300
 * - mat[i][j] <= 10^4
 * - threshold <= 10^5
 * ------------------------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: 2D Prefix Sum + Binary Search
 * ------------------------------------------------------------------------------------------------
 * 1. 2D Prefix Sum:
 * To calculate the sum of any sub-rectangle in O(1), we precompute a prefix sum matrix:
 * prefix[i][j] = mat[i][j] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1]
 * * 2. Binary Search on Answer:
 * The side length of the square ranges from 1 to min(m, n). 
 * If a square of side 'k' exists with sum <= threshold, then a square of side 'k-1' 
 * might also exist. This monotonic property allows us to Binary Search for the 
 * maximum possible side length.
 * * 3. Range Sum Query:
 * Sum(r1, c1, r2, c2) = prefix[r2][c2] - prefix[r1-1][c2] - prefix[r2][c1-1] + prefix[r1-1][c1-1]
 * ------------------------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * ------------------------------------------------------------------------------------------------
 * Time Complexity: O(M * N + log(min(M, N)) * M * N)
 * - M*N to build the prefix sum.
 * - log(min(M, N)) steps in binary search, each step takes O(M*N) to check all possible squares.
 * * Space Complexity: O(M * N)
 * - To store the 2D prefix sum matrix.
 * ------------------------------------------------------------------------------------------------
 */
// Optimal Solution in Java-

class Solution {
    private boolean check(int side, int rows, int cols,  int threshold, int[][] prefix) {
        if (side == 0)
         return true;

        for (int i = 0; i + side - 1 < rows; i++) {
            for (int j = 0; j + side - 1 < cols; j++) {

                int r2 = i + side - 1;
                int c2 = j + side - 1;

                int sum = prefix[r2][c2];
                if (i > 0) sum -= prefix[i - 1][c2];
                if (j > 0) sum -= prefix[r2][j - 1];

                if (i > 0 && j > 0) 
                sum += prefix[i - 1][j - 1];

                if (sum <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
    public int maxSideLength(int[][] mat, int threshold) {
        int rows = mat.length;
        int cols = mat[0].length;

        int[][] prefix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
            prefix[i][j] = mat[i][j]+ (i > 0 ? prefix[i - 1][j] : 0) + (j > 0 ? prefix[i][j - 1] : 0) - (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0);
            }
        }
        int lo = 1, hi = Math.min(rows, cols);
        int result = 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(mid, rows, cols, threshold, prefix)) {
                result = mid;
                lo = mid + 1;
            } 
            else {
                hi = mid - 1;
            }
        }
         return result;
    }
}

