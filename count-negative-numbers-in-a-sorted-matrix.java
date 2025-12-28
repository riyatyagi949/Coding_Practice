/**
 * PROBLEM STATEMENT: 1351. Count Negative Numbers in a Sorted Matrix
 * --------------------------------------------------------------------------------
 * Given a m x n matrix 'grid' which is sorted in non-increasing order both 
 * row-wise and column-wise, return the number of negative numbers in the grid.
 * * Example 1:
 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives numbers in the matrix.
 * * Example 2:
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 * * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 100
 * - -100 <= grid[i][j] <= 100
 * --------------------------------------------------------------------------------
 * * OPTIMAL SOLUTION: STAIRCASE SEARCH (O(m + n))
 * --------------------------------------------------------------------------------
 * Since the matrix is sorted in non-increasing order (descending) both row-wise 
 * and column-wise, we can use the "Staircase Search" pattern.
 * * We start at the Top-Right corner (row 0, last column) or Bottom-Left.
 * Let's start at the Top-Right (0, n-1):
 * 1. If grid[row][col] is negative:
 * - Because the column is sorted descending, every element BELOW this one 
 * in the current column must also be negative.
 * - Number of negatives in this column = (total rows - current row index).
 * - Add this count to the total and move LEFT (col--) to check the next column.
 * 2. If grid[row][col] is non-negative:
 * - Because the row is sorted descending, we need to find smaller numbers. 
 * Moving left makes numbers larger or equal, so we must move DOWN (row++).
 * * This approach traverses the matrix like a staircase, ensuring we visit at 
 * most m rows and n columns.
 * --------------------------------------------------------------------------------
 * * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(m + n)
 * - We start at (0, n-1). In each step of the while loop, we either decrement 'col' 
 * or increment 'row'. 
 * - The maximum number of steps is the number of rows plus the number of columns.
 * * Space Complexity: O(1)
 * - We only use a few integer variables (row, col, count) regardless of the 
 * input matrix size.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -
class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int row = 0;
        int col = n - 1;
        int count = 0;

        while (row < m && col >= 0)
         {
          if (grid[row][col] < 0)
             {
             count += (m - row);
             col--;
            }
         else {
             row++;
            }
        }
        return count;
    }
}


