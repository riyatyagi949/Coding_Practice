/**
 * Problem Statement:
 * Given a `triangle` array, find the minimum path sum from top to bottom.
 * You can only move to adjacent numbers in the row below. If you are at index `i` in the current row, you can move to either index `i` or `i + 1` in the next row.
 *
 * Optimal Approach (Dynamic Programming):
 * This problem can be efficiently solved using dynamic programming. We need to find the minimum sum to reach any given cell from the top.
 * A bottom-up approach is often cleaner and avoids recursion overhead.
 *
 * Let's define `dp[i][j]` as the minimum path sum to reach the cell at row `i` and column `j`.
 * The recurrence relation would be: `dp[i][j] = triangle[i][j] + min(dp[i-1][j], dp[i-1][j-1])`.
 * The base case is the top element: `dp[0][0] = triangle[0][0]`.
 * This would require O(N^2) space, where N is the number of rows.
 *
 * A more space-optimized approach is to use a bottom-up DP starting from the second-to-last row.
 * The logic is that the minimum path sum from a cell in row `i` to the bottom is the value of the cell itself plus the minimum path sum from its adjacent children in row `i+1`.
 *
 * Let's create a DP array, say `dp`, of size `n` (the number of rows). We initialize it with the values of the last row of the triangle.
 * Now, we iterate from the second-to-last row (`n-2`) up to the top row (`0`).
 * For each row `i`, and for each element `j` in that row:
 * The minimum path sum from this cell `triangle[i][j]` to the bottom is `triangle[i][j]` plus the minimum of the two adjacent elements in the DP array from the row below.
 * The adjacent elements in the DP array correspond to `dp[j]` and `dp[j+1]`.
 * We update `dp[j] = triangle[i][j] + min(dp[j], dp[j+1])`.
 *
 * After iterating through all the rows, the final minimum sum will be stored at `dp[0]`, which represents the minimum path sum from the top of the triangle.
 *
 * This approach works because when we are at row `i`, the `dp` array already holds the minimum path sums from row `i+1` to the bottom.
 * We are essentially collapsing the triangle, row by row, from the bottom up, until we are left with a single element at the top which represents the final minimum sum.
 *
 * Time Complexity: O(N^2), where N is the number of rows in the triangle. This is because we iterate through each element of the triangle once.
 * Space Complexity: O(N), where N is the number of rows. We use a single 1-D array of size N to store the DP values. The problem statement also asks for an O(N) space solution.
 */
// Optimal Solution in Java - 

import java.util.List;

