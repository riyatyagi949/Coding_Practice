/**
 * PROBLEM STATEMENT: 2943. Maximize Area of Square Hole in Grid
 * --------------------------------------------------------------------------------
 * You are given two integers n and m, and two integer arrays hBars and vBars.
 * - The grid has n + 2 horizontal bars and m + 2 vertical bars.
 * - These bars create 1 x 1 unit cells initially.
 * - hBars contains indices of horizontal bars you can remove.
 * - vBars contains indices of vertical bars you can remove.
 * * Find the maximum area of a SQUARE-shaped hole you can create by removing 
 * some of these bars.
 *
 * Example 1:
 * Input: n = 2, m = 1, hBars = [2,3], vBars = [2]
 * Output: 4
 * Explanation: Removing horizontal bar 2 and vertical bar 2 creates a 2x2 hole.
 *
 * Constraints:
 * - 1 <= n, m <= 10^9
 * - 1 <= hBars.length, vBars.length <= 100
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: LONGEST CONSECUTIVE SEQUENCE
 * --------------------------------------------------------------------------------
 * To form a large gap in one dimension (horizontal or vertical), we need to 
 * remove consecutive bars. 
 * - If we remove 'X' consecutive bars, the gap width becomes 'X + 1'.
 * - For a SQUARE hole, the side length 'S' is limited by the smaller of the two 
 * maximum gaps available in the horizontal and vertical directions.
 * * Algorithm:
 * 1. Sort hBars and vBars.
 * 2. Find the length of the longest consecutive sequence in hBars (maxH).
 * 3. Find the length of the longest consecutive sequence in vBars (maxV).
 * 4. The maximum possible gap side is Math.min(maxH + 1, maxV + 1).
 * 5. Return the square of this side length.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(H log H + V log V)
 * - Sorting hBars takes O(H log H) and sorting vBars takes O(V log V), where H 
 * and V are the lengths of the bars arrays.
 * - Finding the longest consecutive sequence takes linear time O(H + V).
 * - Given H, V <= 100, this is extremely efficient.
 * * Space Complexity: O(1) or O(log N)
 * - Auxiliary space is constant, though sorting uses O(log N) recursion stack.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -
import java.util.Arrays;

class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        int maxConsecutiveHBars = 1; 
        int maxConsecutiveVBars = 1; 

        int currConsecutiveHBars = 1;
        for (int i = 1; i < hBars.length; i++) {
            if (hBars[i] - hBars[i - 1] == 1) {
                currConsecutiveHBars++;
            } 
            else {
                currConsecutiveHBars = 1;
            }
            maxConsecutiveHBars = Math.max(maxConsecutiveHBars, currConsecutiveHBars);
        }
        int currConsecutiveVBars = 1;
        for (int i = 1; i < vBars.length; i++) {
            if (vBars[i] - vBars[i - 1] == 1) {
                currConsecutiveVBars++;
            } 
            else {
                currConsecutiveVBars = 1;
            }
            maxConsecutiveVBars = Math.max(maxConsecutiveVBars, currConsecutiveVBars);
        }
        int side = Math.min(maxConsecutiveHBars, maxConsecutiveVBars) + 1;
        
        return side * side;
    }
}


