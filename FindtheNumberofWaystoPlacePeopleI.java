// Problem Statement:
// You are given a 2D array points of size n x 2 representing integer coordinates of some points on a 2D plane, where points[i] = [xi, yi].
// Count the number of pairs of points (A, B), where
// A is on the upper left side of B, and
// there are no other points in the rectangle (or line) they make (including the border).
// Return the count.

// Approach:
// The problem asks us to find the number of pairs of points (A, B) such that A is in the upper-left quadrant relative to B, and the rectangle formed by A and B contains no other points.
// A point A(x1, y1) is on the upper-left side of point B(x2, y2) if x1 <= x2 and y1 >= y2.
// The rectangle formed by A and B is defined by the corners (x1, y1), (x1, y2), (x2, y1), and (x2, y2).
// A point C(x3, y3) is inside or on the border of this rectangle if x1 <= x3 <= x2 and y2 <= y3 <= y1.

// We can use a brute-force approach. We can iterate through all possible pairs of points (A, B) and check if they satisfy the two given conditions.
// Let's iterate through all points A as the potential upper-left point and all points B as the potential lower-right point.
// A point A is at index i, and a point B is at index j.

// Condition 1: A is on the upper left side of B.
// Let points[i] = [x_A, y_A] and points[j] = [x_B, y_B].
// The condition A is on the upper left side of B means x_A <= x_B and y_A >= y_B.
// Note that the problem statement and examples also imply that A and B must be distinct points.

// Condition 2: No other points in the rectangle.
// For a chosen pair (A, B) that satisfies Condition 1, we need to check if any other point C (at index k, where k != i and k != j) lies within the rectangle formed by A and B.
// Let C be at [x_C, y_C]. The rectangle is defined by x_A and x_B (as horizontal boundaries) and y_B and y_A (as vertical boundaries).
// A point C is in the rectangle if x_A <= x_C <= x_B and y_B <= y_C <= y_A.
// If we find any such point C, the pair (A, B) is not valid, and we move to the next pair.
// If we iterate through all other points and find no such C, the pair (A, B) is valid, and we increment our count.

// The algorithm would be as follows:
// 1. Initialize a counter `count` to 0.
// 2. Iterate through each point `i` from 0 to n-1, representing point A.
// 3. Iterate through each point `j` from 0 to n-1, representing point B.
// 4. If i == j, continue to the next iteration.
// 5. Check if points[i] is on the upper left side of points[j].
//    Let x_A = points[i][0], y_A = points[i][1].
//    Let x_B = points[j][0], y_B = points[j][1].
//    If x_A <= x_B and y_A >= y_B:
//       This pair satisfies the first condition. Now check the second.
//       Initialize a boolean `isValidPair` to true.
//       Iterate through each point `k` from 0 to n-1.
//       If k == i or k == j, continue.
//       Let x_C = points[k][0], y_C = points[k][1].
//       Check if C is inside or on the border of the rectangle defined by A and B.
//       If x_A <= x_C <= x_B and y_B <= y_C <= y_A:
//          Set `isValidPair` to false and break from this inner loop.
//       If after checking all other points, `isValidPair` is still true, it means the rectangle is empty.
//       Increment `count`.
// 6. Return `count`.

// Time Complexity:
// The algorithm uses three nested loops. The outermost loop runs n times, the second loop runs n times, and the innermost loop runs n times.
// This results in a time complexity of O(n^3).
// Given the constraint n <= 50, O(50^3) = O(125000), which is efficient enough.

// Space Complexity:
// The space complexity is O(1) as we only use a few variables to store the loop indices and the count.

// Optimal  Solution in Java-

import java.util.*;
class Solution {
  public int numberOfPairs(int[][] points) {
    int ans = 0;

    Arrays.sort(points, Comparator.comparingInt((int[] point) -> point[0])
                            .thenComparingInt((int[] point) -> - point[1]));

    for (int i = 0; i < points.length; ++i) {
      int maxY = Integer.MIN_VALUE;
      for (int j = i + 1; j < points.length; ++j)
        if (points[i][1] >= points[j][1] && points[j][1] > maxY) {
          ++ans;
          maxY = points[j][1];
        }
    }

    return ans;
  }
}