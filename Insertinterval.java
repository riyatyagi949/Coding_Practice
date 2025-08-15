/*
 * Problem Statement:
 * Given a list of non-overlapping intervals sorted by their start times, and a new interval,
 * insert the new interval into the list. If the new interval overlaps with existing intervals,
 * merge them to maintain a list of non-overlapping, sorted intervals.
 *
 * Approach:
 * The problem can be solved by iterating through the sorted list of intervals and handling three cases:
 *
 * 1. Non-overlapping intervals before the new interval:
 * Add all intervals that end before the new interval starts (`interval[1] < newInterval[0]`)
 * to the result list. These intervals are guaranteed not to overlap with the new interval.
 *
 * 2. Overlapping intervals:
 * When we find an interval that overlaps with `newInterval`, we need to merge them.
 * An overlap occurs if `interval[0] <= newInterval[1]`.
 * We update `newInterval` by taking the minimum of the start times and the maximum of the end times
 * (`newInterval[0] = Math.min(newInterval[0], interval[0])`,
 * `newInterval[1] = Math.max(newInterval[1], interval[1])`).
 * This process continues for all overlapping intervals.
 *
 * 3. Non-overlapping intervals after the new interval:
 * After processing all overlapping intervals, the updated `newInterval` is finalized.
 * Any remaining intervals in the original list will not overlap with the new one.
 * We add the merged `newInterval` to the result list, and then append all remaining
 * intervals from the original list.
 *
 * A key observation is that since the input `intervals` list is sorted, we can iterate through it once.
 * First, we add all intervals that come before the `newInterval`.
 * Then, we merge `newInterval` with all subsequent overlapping intervals.
 * Finally, we add the merged interval and all remaining non-overlapping intervals that come after.
 *
 * Time Complexity: O(N)
 * We iterate through the list of N intervals once.
 *
 * Space Complexity: O(N)
 * We create a new list to store the result, which in the worst case could contain all N original intervals plus the new one.
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Add all intervals that come before the new interval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Merge with overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Add all intervals that come after the new interval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }
}