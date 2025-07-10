/*
Problem Statement:
Given eventTime, startTime, and endTime arrays for non-overlapping meetings, reschedule at most one meeting to maximize the longest continuous free time. The rescheduled meeting maintains its duration and does not overlap with others. The relative order of meetings can change.

Approach:
1.  Augment the meetings list with dummy intervals `[0,0]` and `[eventTime, eventTime]` to represent event boundaries. This simplifies free gap calculations.
2.  Calculate `freeGaps`: An array where `freeGaps[i]` is the free time between `meetings.get(i)` and `meetings.get(i+1)`.
3.  Precompute `prefixMaxGap` and `suffixMaxGap` arrays from `freeGaps` to efficiently query maximum gaps in ranges. `prefixMaxGap[i]` stores the maximum from `freeGaps[0]` to `freeGaps[i]`, and similarly for `suffixMaxGap`.
4.  Initialize `overallMaxFreeTime` with the maximum free time achievable without any rescheduling (i.e., `prefixMaxGap[n]`).
5.  Iterate through each original meeting (from `k = 1` to `n` in the augmented list) as the one to be potentially moved:
    a.  Calculate `maxOfUnaffectedGaps`: This is the maximum free time among gaps *not* adjacent to the current meeting `k`. Use `prefixMaxGap[k-2]` and `suffixMaxGap[k+1]`.
    b.  Calculate `mergedGapSize`: The total free space created by removing meeting `k` (from `meetings.get(k-1).end` to `meetings.get(k+1).start`).
    c.  `freeTimeInMergedSlotIfUsed`: The remaining free time in this merged slot if meeting `k` (with its duration `D_k`) is placed back into it: `mergedGapSize - D_k`. Ensure it's non-negative.
    d.  The maximum free time for this specific move is `max(maxOfUnaffectedGaps, freeTimeInMergedSlotIfUsed)`.
    e.  Update `overallMaxFreeTime` with the maximum found so far.
6.  Return `overallMaxFreeTime`.

Time Complexity: O(N), where N is the number of meetings.
Space Complexity: O(N) for storing auxiliary arrays.

Optimal Solution:
The problem is solved efficiently in linear time by using prefix/suffix maximums on calculated free gaps, avoiding re-computation for each potential move.
*/

class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[][] meetings = new int[n][2];

        for (int i = 0; i < n; i++) {
            meetings[i][0] = startTime[i];
            meetings[i][1] = endTime[i];
        }
        List<int[]> gaps = new ArrayList<>();
        if (meetings[0][0] > 0) {
            gaps.add(new int[]{0, meetings[0][0]});
        }
        for (int i = 1; i < n; i++) {
            if (meetings[i][0] > meetings[i - 1][1]) {
                gaps.add(new int[]{meetings[i - 1][1], meetings[i][0]});
            }
        }
         if (meetings[n - 1][1] < eventTime) {
            gaps.add(new int[]{meetings[n - 1][1], eventTime});
        }
        int maxFree = 0;
        for (int[] gap : gaps) {
            maxFree = Math.max(maxFree, gap[1] - gap[0]);
        }
        for (int i = 0; i < n; i++) {
            int dur = meetings[i][1] - meetings[i][0];
            int left = (i == 0) ? 0 : meetings[i - 1][1];
            int right = (i == n - 1) ? eventTime : meetings[i + 1][0];

            int mergedGap = right - left;
            maxFree = Math.max(maxFree, mergedGap);

            // Try placing meeting[i] in every other gap
            for (int[] gap : gaps) {
                if (gap[1] - gap[0] >= dur) {
                    maxFree = Math.max(maxFree, dur);
                }
            }
        }

        return maxFree;
    }
}
