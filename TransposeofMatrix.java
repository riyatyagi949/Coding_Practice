/**
 * Problem Statement: Transpose of Matrix
 * ---------------------------------------
 * You are given a square matrix of size n x n. Your task is to find the 
 * transpose of the given matrix. The transpose of a matrix is obtained by 
 * converting all the rows to columns and all the columns to rows.
 *
 * Example:
 * Input: mat[][] = [[1, 2], [9, -2]]
 * Output: [[1, 9], [2, -2]]
 *
 * Constraints:
 * 1 <= n <= 10^3
 * -10^9 <= mat[i][j] <= 10^9
 */
/**
     * Optimal Solution: Direct Mapping for Transpose
     * ----------------------------------------------
     * The transpose operation swaps the row and column indices. If the original 
     * matrix is M, the transposed matrix T is defined such that:
     * T[i][j] = M[j][i]
     *
     * Since the problem specifies returning a new matrix (implicitly by using ArrayLists 
     * in the provided structure), we simply create a new matrix (ArrayList of ArrayLists) 
     * and iterate through the original matrix M, populating the new matrix T by mapping 
     * M[j][i] to T[i][j].
     *
     * * Implementation Detail:
     * - The outer loop iterates over the rows of the NEW matrix (index 'i', from 0 to n-1).
     * - The inner loop iterates over the columns of the NEW matrix (index 'j', from 0 to n-1).
     * - Inside the inner loop, we access the element mat[j][i] from the original matrix 
     * and place it into the current row of the new matrix.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N^2), where N is the dimension of the square matrix.
 * - The algorithm uses two nested loops, both iterating N times. 
 * - Every element in the N x N matrix is visited exactly once.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N^2), where N is the dimension of the square matrix.
 * - This space is required to store the new resulting transposed matrix (res), 
 * which has N * N elements.
 *
 * Note: If the requirement was to perform the transpose **in-place** (for a square matrix 
 * only), the space complexity would be O(1) auxiliary space, but this solution uses O(N^2) 
 * to store the new result.
 */
// Java Code  - 
import java.util.ArrayList;

class Solution {
    public ArrayList<ArrayList<Integer>> transpose(int[][] mat)
    {
        int n = mat.length;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        
        for(int i=0;i<n;i++)
        {
            ArrayList<Integer> row = new ArrayList<>();
            for(int j=0;j<n;j++)
            {
                row.add(mat[j][i]);
            }
            res.add(row);
        }
        return res;
    }
}
