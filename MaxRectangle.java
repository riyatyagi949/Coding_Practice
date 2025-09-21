/**
 * Problem Statement:
 * Given a 2D binary matrix `mat` containing 0s and 1s, find the maximum area of a rectangle that can be formed using only 1s.
 *
 * Optimal Approach:
 * This problem can be efficiently solved by reducing the 2D matrix problem to a series of 1D histogram problems.
 * The main idea is to iterate through each row of the matrix. For each row, we treat it as the base of a potential rectangle.
 * We can compute the height of a bar at each column `j` for the current row `i`. The height is the number of consecutive 1s immediately above `mat[i][j]`, including `mat[i][j]` itself.
 * If `mat[i][j]` is 1, the height is `heights[j] = heights[j] + 1` (where `heights[j]` is the height from the previous row).
 * If `mat[i][j]` is 0, the height is `heights[j] = 0`, as it breaks the sequence of 1s.
 *
 * After calculating the heights for the current row, we have a histogram. The problem then becomes finding the largest rectangle in this histogram, which can be solved using a monotonic stack.
 *
 * Algorithm for "Largest Rectangle in Histogram":
 * 1. Initialize an empty stack and `maxArea = 0`.
 * 2. Iterate through the `heights` array from left to right (including a virtual bar of height 0 at the end to process all remaining bars).
 * 3. For each bar at index `i`, while the stack is not empty and `heights[i]` is less than the height of the bar at the top of the stack:
 * - Pop the index `h` from the stack.
 * - The height of the bar being processed is `heights[h]`.
 * - The width of the rectangle is `i` if the stack is now empty, or `i - stack.peek() - 1` otherwise.
 * - The area is `heights[h] * width`. Update `maxArea` with the maximum.
 * 4. Push the current index `i` onto the stack.
 *
 * By applying this process for each row of the matrix and keeping track of the overall maximum area, we find the solution.
 *
 * Time Complexity: O(M * N), where M is the number of rows and N is the number of columns. We iterate through each cell of the matrix once. For each row, we compute the histogram and then find the largest rectangle in it, both of which take O(N) time.
 * Space Complexity: O(N) to store the heights of the histogram and the monotonic stack.
 */
import java.util.Stack;

