/**
 * Problem Statement:
 * Given an array of points on the X-Y plane `points`, where `points[i] = [xi, yi]`, return the area of the largest triangle
 * that can be formed by any three different points. Answers within 10^-5 of the actual answer will be accepted.
 *
 * Optimal Approach:
 * Since the number of points `n` is very small (up to 50), the most direct and optimal approach for this constraint is to use a brute-force method.
 * A triangle is formed by three unique, non-collinear points. We can iterate through all possible combinations of three distinct points
 * (P1, P2, P3) and calculate the area of the triangle they form. We keep track of the maximum area found.
 *
 * The area of a triangle with vertices (x1, y1), (x2, y2), and (x3, y3) can be calculated using the **Shoelace Formula** (also known as the Surveyor's formula, or determinant method):
 * Area = 0.5 * |x1(y2 - y3) + x2(y3 - y1) + x3(y1 - y2)|
 * The absolute value is used because the determinant can be negative depending on the order of the vertices, but area is always positive.
 *
 * Algorithm Steps:
 * 1. Initialize a variable `maxArea` to 0.0.
 * 2. Use three nested loops to iterate through all unique combinations of three points (i, j, k) where $0 \le i < j < k < n$.
 * 3. Inside the inner loop, get the coordinates of the three points:
 * P1 = (points[i][0], points[i][1])
 * P2 = (points[j][0], points[j][1])
 * P3 = (points[k][0], points[k][1])
 * 4. Calculate the area of the triangle (P1, P2, P3) using the Shoelace Formula.
 * 5. Update `maxArea = max(maxArea, currentArea)`.
 * 6. After checking all triplets, return `maxArea`.
 *
 * Time Complexity: O(n^3). We use three nested loops, each iterating up to `n` times. Given $n \le 50$, $50^3 = 125,000$ operations, which is very fast.
 * Space Complexity: O(1). We use only a few constant-size variables for area calculation and iteration.
 */
// Optimal  Solution in Java -
class Solution {
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;
        int n = points.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    double area = Math.abs(
                        points[i][0] * (points[j][1] - points[k][1]) +
                        points[j][0] * (points[k][1] - points[i][1]) +
                        points[k][0] * (points[i][1] - points[j][1])
                    ) / 2.0;
                     maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
}