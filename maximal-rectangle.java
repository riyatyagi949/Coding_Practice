/**
 * PROBLEM STATEMENT: 85. Maximal Rectangle
 * --------------------------------------------------------------------------------
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest 
 * rectangle containing only 1's and return its area.
 *
 * * Example 1:
 * Input: matrix = [["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle has an area of 6.
 *
 * * Constraints:
 * rows == matrix.length
 * cols == matrix[i].length
 * 1 <= rows, cols <= 200
 * matrix[i][j] is '0' or '1'.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: HISTOGRAM-BASED REDUCTION
 * --------------------------------------------------------------------------------
 * This problem can be reduced to the "Largest Rectangle in Histogram" problem.
 * * * Core Logic:
 * 1. We treat each row as the base of a histogram.
 * 2. For each column, we track the consecutive height of '1's ending at the current row.
 * 3. If matrix[row][col] is '1', heights[col] increases by 1.
 * 4. If matrix[row][col] is '0', heights[col] is reset to 0 (since a rectangle must 
 * be contiguous).
 * 5. After updating heights for each row, we calculate the largest rectangle in that 
 * specific histogram using a Monotonic Stack.
 *
 * * Monotonic Stack (Largest Rectangle in Histogram):
 * - Maintain a stack of indices with increasing heights.
 * - When a smaller height is encountered, pop the stack and calculate area with the 
 * popped height as the minimum height of the rectangle.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(R * C)
 * - We iterate through each row (R).
 * - For each row, we update the heights array (C) and run the histogram logic (C).
 * - Total: O(R * C).
 *
 * Space Complexity: O(C)
 * - heights array of size C.
 * - Stack used in the histogram calculation stores at most C indices.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
import java.util.Stack;
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0)
         return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] heights = new int[cols];
        int maxArea = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == '1')
                    heights[c]++;
                else
                    heights[c] = 0;
            }
            maxArea = Math.max(maxArea, largestRectangle(heights));
        }
         return maxArea;
    }
    private int largestRectangle(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, height * width);
            }
            stack.push(i);
        }
        return max;
    }
}


