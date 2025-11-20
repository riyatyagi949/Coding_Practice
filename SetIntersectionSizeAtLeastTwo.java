/**
 * Problem Statement: Set Intersection Size At Least Two
 * -----------------------------------------------------
 * Given a 2D integer array 'intervals' where intervals[i] = [starti, endi], 
 * representing all integers from starti to endi inclusively.
 * A containing set 'nums' is an array where each interval from 'intervals' 
 * must have at least two integers in 'nums' (i.e., |interval[i] intersect nums| >= 2).
 * Return the minimum possible size of a containing set 'nums'.
 *
 * Constraints:
 * 1 <= intervals.length <= 3000
 * 0 <= starti < endi <= 10^8
 *//**
     * Optimal Solution: Greedy Algorithm
     * ----------------------------------
     * This problem is a variation of the classic interval covering problem. To minimize
     * the size of the final set, we must choose two points for each interval in a way
     * that those points simultaneously cover the two-point requirement for as many
     * subsequent intervals as possible.
     *
     * * Greedy Strategy:
     * 1. **Custom Sorting:** The standard greedy approach for intervals is to sort by end time. 
     * Here, we use a custom sort to prioritize processing. Sort intervals primarily by their 
     * **end point** (ascending). If two end points are the same, sort by their **start point** * (descending). This ensures that if multiple intervals end at the same place, we handle
     * the one that starts latest first, as it is the most constrained.
     * 2. **Track the Last Two Points:** We only need to track the two largest points added 
     * to the set that intersect the current interval. Let these two points be `p1` and `p2`, 
     * where `p1 < p2`.
     * 3. **Process and Select:** Iterate through the sorted intervals [s, e]:
     * - **Case 1: Fully Covered:** If the current interval [s, e] is already covered by 
     * the two largest points, `p1` and `p2`, that were placed for a *previous* interval 
     * (i.e., `s <= p1` and `s <= p2`), then we do nothing.
     * - **Case 2: One Point Missing:** If the interval is partially covered 
     * (i.e., `s <= p2` but `s > p1`), it means we have one point in the intersection 
     * (p2) but need one more. To maximize future coverage, we choose the next necessary 
     * point as large as possible: `e`. We update the two points to be the newly added 
     * point and the previously existing point: `p1 = p2`, `p2 = e`.
     * - **Case 3: Two Points Missing (New Set):** If neither `p1` nor `p2` covers the interval 
     * (i.e., `s > p2`), we must add two new points to satisfy the condition for this interval. 
     * Again, to maximize future coverage, we choose the two largest points possible within 
     * the current interval: `e - 1` and `e`. We update the two points: `p1 = e - 1`, `p2 = e`.
     *
     * We initialize `p1` and `p2` to be -1 (or any value smaller than min constraint 0).
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N log N), where N is the number of intervals.
 * - The time complexity is dominated by the custom sort of the intervals array, which takes O(N log N).
 * - The subsequent single loop iterates N times, performing only O(1) checks and assignments inside.
 * - Overall complexity: O(N log N).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1) auxiliary space, or O(log N) to O(N) depending on the sort implementation.
 * - The algorithm uses only a few integer variables (`p1`, `p2`, `minSize`) and performs the sort 
 * in place or with minimal extra space (standard for sorting primitives).
 */
// Optimal Solution in Java -
import java.util.Arrays;

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        int n = 0;
        long[] endStartPairs = new long[intervals.length];

        for (int[] interval : intervals) {
            endStartPairs[n] = -interval[0] & 0xFFFFFFFFL;
            endStartPairs[n++] |= (long) (interval[1]) << 32;
        }
        Arrays.sort(endStartPairs);

        int min = -2;
        int max = -1;
        int curStart;
        int curEnd;
        int res = 0;

        for (long endStartPair : endStartPairs) {
            curStart = -(int) endStartPair;
            curEnd = (int) (endStartPair >> 32);

            if (curStart <= min) {
                continue;
            }
            if (curStart <= max) {
                res += 1;
                min = max;
            }
             else {
                res += 2;
                min = curEnd - 1;
            }
            max = curEnd;
        }
        return res;
    }
}