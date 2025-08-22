/**
 * Problem Statement:
 * You are given a 2D binary array `grid`. Find a rectangle with horizontal and vertical sides with the smallest area, such that all the 1's in `grid` lie inside this rectangle.
 * Return the minimum possible area of the rectangle.
 *
 * Approach:
 * To find the minimum area rectangle that covers all the 1s, we need to determine the bounding box of all the 1s.
 * The bounding box is defined by the minimum and maximum row and column indices where a 1 is present.
 * 1. Initialize four variables:
 * - `minRow`: to store the smallest row index containing a 1. Initialize to a large value, like `grid.length`.
 * - `maxRow`: to store the largest row index containing a 1. Initialize to a small value, like -1.
 * - `minCol`: to store the smallest column index containing a 1. Initialize to a large value, like `grid[0].length`.
 * - `maxCol`: to store the largest column index containing a 1. Initialize to a small value, like -1.
 * 2. Iterate through the `grid` from top to bottom and left to right.
 * 3. If a `grid[i][j]` is 1, update the four variables:
 * - `minRow = Math.min(minRow, i)`
 * - `maxRow = Math.max(maxRow, i)`
 * - `minCol = Math.min(minCol, j)`
 * - `maxCol = Math.max(maxCol, j)`
 * 4. After iterating through the entire grid, the minimum area is calculated by the width and height of this bounding box.
 * - Height = `maxRow - minRow + 1`
 * - Width = `maxCol - minCol + 1`
 * - Area = Height * Width
 * 5. If no 1's were found (although the problem constraints state there is at least one 1), the area would be 0.
 *
 * Time Complexity:
 * O(m * n), where 'm' is the number of rows and 'n' is the number of columns in the grid. We iterate through each cell of the grid once to find the boundaries.
 *
 * Space Complexity:
 * O(1), as we only use a few variables to store the boundaries, regardless of the input size.
 */
class Solution {
    public int minimumArea(int[][] grid) {
        int minRow = grid.length;
        int maxRow = -1;
        int minCol = grid[0].length;
        int maxCol = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }
        if (maxRow == -1) {
            return 0;
        }
        int height = maxRow - minRow + 1;
        int width = maxCol - minCol + 1;
        return height * width;
    }
}