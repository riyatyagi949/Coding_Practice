/**
 * PROBLEM STATEMENT: 3047. Find the Largest Area of Square Inside Two Rectangles
 * --------------------------------------------------------------------------------
 * You are given n rectangles in a 2D plane with edges parallel to the x and y axis. 
 * Two 2D integer arrays are provided:
 * - bottomLeft[i] = [a_i, b_i] (Bottom-left coordinate)
 * - topRight[i] = [c_i, d_i] (Top-right coordinate)
 * * Task: Find the maximum area of a square that can fit inside the intersecting 
 * region of AT LEAST TWO rectangles. Return 0 if no such square exists.
 * * Example:
 * Input: bottomLeft = [[1,1],[2,2],[3,1]], topRight = [[3,3],[4,4],[6,6]]
 * Output: 1
 * Explanation: Intersection of Rect 0 and 1 has width 1 and height 1. 
 * Smallest dimension is 1, so max square area is 1^2 = 1.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Pairwise Intersection Check
 * --------------------------------------------------------------------------------
 * 1. To find the intersection of two rectangles (i and j):
 * - The intersection X-range is [max(x_left_i, x_left_j), min(x_right_i, x_right_j)]
 * - The intersection Y-range is [max(y_bottom_i, y_bottom_j), min(y_top_i, y_top_j)]
 * 2. Intersection Width (W) = min_X_right - max_X_left
 * 3. Intersection Height (H) = min_Y_top - max_Y_bottom
 * 4. If W > 0 and H > 0, the rectangles overlap.
 * 5. The largest square that fits in a W x H rectangle has a side length: S = min(W, H).
 * 6. We iterate through all pairs O(N^2) to find the global maximum S.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N^2)
 * - N is up to 10^3, so N^2 is 10^6, which fits comfortably within 1 second.
 * Space Complexity: O(1)
 * - Only a few variables are used to track dimensions and the maximum side.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
  public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxSide = 0;

   for (int i = 0; i < n; i++) {
       for (int j = i + 1; j < n; j++) {
           int w =  Math.min(topRight[i][0], topRight[j][0]) - Math.max(bottomLeft[i][0], bottomLeft[j][0]);
           int h =  Math.min(topRight[i][1], topRight[j][1]) - Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                int side = Math.min(w, h);
                maxSide = Math.max(maxSide, side);
            }
        }
        return maxSide * maxSide;
    }
}

