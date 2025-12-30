/**
 * PROBLEM STATEMENT: 840. Magic Squares In Grid
 * --------------------------------------------------------------------------------
 * A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 
 * such that each row, column, and both diagonals all have the same sum.
 * * Given a row x col grid of integers, return the number of 3 x 3 magic square 
 * subgrids present within the grid.
 * * Note:
 * 1. The numbers must be distinct and strictly in the range [1, 9].
 * 2. The grid itself may contain numbers up to 15.
 * * Example:
 * Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
 * Output: 1
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SLIDING WINDOW & MATHEMATICAL PRUNING
 * --------------------------------------------------------------------------------
 * To solve this efficiently, we iterate through every possible top-left corner (i, j)
 * of a 3x3 subgrid and verify if it satisfies the magic square properties.
 * * Key Mathematical Insights for 3x3 Magic Squares:
 * 1. Range: Must contain exactly the numbers 1 through 9.
 * 2. Magic Sum: The sum of numbers 1-9 is 45. Since there are 3 rows/cols, 
 * the magic sum MUST be 45 / 3 = 15.
 * 3. Center Element: In any 3x3 magic square with numbers 1-9, the center element 
 * MUST be 5. This is a powerful pruning condition to skip invalid subgrids.
 * 4. Distinctness: Use a boolean array or bitmask to ensure 1-9 are present exactly once.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(R * C)
 * - R = number of rows, C = number of columns.
 * - We iterate (R-2) * (C-2) times.
 * - For each iteration, we perform a constant number of checks (fixed 3x3 grid).
 * * Space Complexity: O(1)
 * - We use a fixed-size boolean array (size 10) for each subgrid check.
 * - No additional scaling space is required.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int count = 0;

        for (int i = 0; i <= rows - 3; i++) {
            for (int j = 0; j <= cols - 3; j++) {
                if (isMagic(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }
    private boolean isMagic(int[][] g, int r, int c) {
        if (g[r + 1][c + 1] != 5)
        return false;

        boolean[] seen = new boolean[10];
        for (int i = r; i < r + 3; i++)
         {
           for (int j = c; j < c + 3; j++)
            {
                int val = g[i][j];
                if (val < 1 || val > 9 || seen[val]) 
                return false;
                seen[val] = true;
            }
        }
        return
            g[r][c] + g[r][c + 1] + g[r][c + 2] == 15 &&
            g[r + 1][c] + g[r + 1][c + 1] + g[r + 1][c + 2] == 15 &&
            g[r + 2][c] + g[r + 2][c + 1] + g[r + 2][c + 2] == 15 &&

            g[r][c] + g[r + 1][c] + g[r + 2][c] == 15 &&
            g[r][c + 1] + g[r + 1][c + 1] + g[r + 2][c + 1] == 15 &&
            g[r][c + 2] + g[r + 1][c + 2] + g[r + 2][c + 2] == 15 &&

            g[r][c] + g[r + 1][c + 1] + g[r + 2][c + 2] == 15 &&
            g[r][c + 2] + g[r + 1][c + 1] + g[r + 2][c] == 15;
    }
}

