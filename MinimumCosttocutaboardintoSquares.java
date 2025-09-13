/**
 * Problem Statement:
 * Given a board of size n x m and two arrays of costs, `x[]` for vertical cuts and `y[]` for horizontal cuts.
 * Find the minimum total cost to cut the board into n*m squares.
 *
 * Optimal Approach:
 * This problem can be solved using a greedy approach. The total cost of a cut depends on the number of pieces (segments)
 * it cuts through. For example, the first cut divides the board into 2 pieces, the second cut divides the board into 3 pieces, and so on.
 *
 * The cost of a vertical cut is `cost_x * (number of horizontal segments)`.
 * The cost of a horizontal cut is `cost_y * (number of vertical segments)`.
 *
 * To minimize the total cost, we should prioritize making the most expensive cuts when the number of segments is smallest.
 * This is because the number of segments increases with each cut. For example, if we have a cut with cost `c1` and another with `c2` (`c1 > c2`),
 * and we have `h` horizontal segments and `v` vertical segments, the costs for the next two cuts will be `c1 * h` and `c2 * h` for vertical cuts,
 * or `c1 * v` and `c2 * v` for horizontal cuts. The number of segments is the multiplier. To minimize the total cost, we should apply the largest
 * multipliers to the smallest costs, and conversely, the largest costs should be multiplied by the smallest number of segments.
 *
 * Therefore, the optimal strategy is to always perform the cut with the highest cost available, regardless of whether it's a horizontal or vertical cut.
 * This ensures that the largest costs are multiplied by the smallest number of segments.
 *
 * The algorithm is as follows:
 * 1. Sort both the vertical cut costs `x[]` and horizontal cut costs `y[]` in descending order.
 * 2. Use two pointers, one for each sorted array, starting from the beginning (highest cost).
 * 3. Keep track of the number of horizontal and vertical segments the board is currently divided into, initialized to 1.
 * 4. In a loop, compare the current highest vertical cut cost with the current highest horizontal cut cost.
 * 5. If the highest vertical cost is greater than or equal to the highest horizontal cost:
 * - Perform a vertical cut.
 * - Add `(current vertical cost * current horizontal segments)` to the total cost.
 * - Increment the number of vertical segments.
 * - Move the vertical cost pointer to the next element.
 * 6. Otherwise (highest horizontal cost is greater):
 * - Perform a horizontal cut.
 * - Add `(current horizontal cost * current vertical segments)` to the total cost.
 * - Increment the number of horizontal segments.
 * - Move the horizontal cost pointer to the next element.
 * 7. Repeat until all cuts are made (both pointers have reached the end of their arrays).
 * 8. If one array is exhausted, continue making cuts with the remaining costs of the other array.
 *
 * This greedy approach works because the decision at each step (which cut to make next) is independent of future decisions and leads to a globally optimal solution.
 *
 * Time Complexity: O(n log n + m log m) due to the sorting of the two cost arrays.
 * Space Complexity: O(1) if sorting is done in-place, or O(n + m) if auxiliary space is needed for sorting.
 */

//  Optimal  Solution in Java - 
import java.util.*;


class Solution {
    public static int minCost(int n, int m, int[] x, int[] y) {
        Arrays.sort(x);
        Arrays.sort(y);
        
        int i = x.length - 1; 
        int j = y.length - 1; 

        int horizontalSegments = 1, verticalSegments = 1;
        int cost = 0;
        
        while (i >= 0 && j >= 0) {
            if (x[i] > y[j]) {
                cost += x[i] * horizontalSegments;
                verticalSegments++;
                i--;
            } 
            else {
                cost += y[j] * verticalSegments;
                horizontalSegments++;
                j--;
            }
        }
            while (i >= 0) {
            cost += x[i] * horizontalSegments;
            verticalSegments++;
            i--;
        }
        while (j >= 0) {
            cost += y[j] * verticalSegments;
            horizontalSegments++;
            j--;
        }
        return cost;
    }
}


