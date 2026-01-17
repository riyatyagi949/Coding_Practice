/**
 * PROBLEM STATEMENT: 3047. Find the Largest Area of Square Inside Two Rectangles
 * --------------------------------------------------------------------------------
 * You are given two 2D integer arrays bottomLeft and topRight where:
 * - bottomLeft[i] = [ai, bi] (bottom-left coordinate of the ith rectangle)
 * - topRight[i] = [ci, di] (top-right coordinate of the ith rectangle)
 * * You need to find the maximum area of a square that can fit inside the 
 * intersecting region of AT LEAST TWO rectangles.
 * * If no two rectangles intersect, return 0.
 * * Example:
 * Input: bottomLeft = [[1,1],[2,2],[3,1]], topRight = [[3,3],[4,4],[6,6]]
 * Output: 1
 * Explanation: Rectangles 0 and 1 intersect with width 1 and height 1. 
 * The largest square inside is 1x1, area = 1.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: BRUTE FORCE PAIRWISE INTERSECTION
 * --------------------------------------------------------------------------------
 * 1. Intersection Logic: 
 * To find the intersection of two rectangles (i and j):
 * - The intersection's Left X is max(bottomLeft[i][0], bottomLeft[j][0])
 * - The intersection's Right X is min(topRight[i][0], topRight[j][0])
 * - The intersection's Bottom Y is max(bottomLeft[i][1], bottomLeft[j][1])
 * - The intersection's Top Y is min(topRight[i][1], topRight[j][1])
 * * 2. Dimensions:
 * - Width = Right X - Left X
 * - Height = Top Y - Bottom Y
 * * 3. Square Constraint:
 * A square must fit inside this width and height. The largest side length 
 * of such a square is min(width, height).
 * * 4. Algorithm:
 * Since n is up to 1000, an O(N^2) approach is perfectly acceptable. 
 * We iterate through every pair (i, j) of rectangles, calculate the 
 * intersection dimensions, find the possible square side, and keep 
 * track of the maximum side found.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N^2)
 * - We use nested loops to compare every pair of rectangles exactly once.
 * - N = 1000, so N^2 = 1,000,000 operations, which fits within the 1s time limit.
 * * Space Complexity: O(1)
 * - We only use a few variables to track the maximum side and current dimensions.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        int maxSide = 0;

        for (int i = 0; i < n; i++) {
         for (int j = i + 1; j < n; j++) {
            int topRightX   = Math.min(topRight[i][0], topRight[j][0]);
            int bottomLeftX = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
            int width = topRightX - bottomLeftX;

            int topRightY   = Math.min(topRight[i][1], topRight[j][1]);
            int bottomLeftY = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
            int height = topRightY - bottomLeftY;

            int side = Math.min(width, height);
            maxSide = Math.max(maxSide, side);
            }
        }
         return 1L * maxSide * maxSide;
    }
}


