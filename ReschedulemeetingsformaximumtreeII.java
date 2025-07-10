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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int maximizeFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;

        List<int[]> meetings = new ArrayList<>();
        meetings.add(new int[]{0, 0});
        for (int i = 0; i < n; i++) {
            meetings.add(new int[]{startTime[i], endTime[i]});
        }
        meetings.add(new int[]{eventTime, eventTime});

        int[] freeGaps = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            freeGaps[i] = meetings.get(i + 1)[0] - meetings.get(i)[1];
        }

        int[] prefixMaxGap = new int[n + 1];
        if (n >= 0) {
            prefixMaxGap[0] = freeGaps[0];
            for (int i = 1; i <= n; i++) {
                prefixMaxGap[i] = Math.max(prefixMaxGap[i - 1], freeGaps[i]);
            }
        }
       
        int[] suffixMaxGap = new int[n + 1];
        if (n >= 0) {
             suffixMaxGap[n] = freeGaps[n];
            for (int i = n - 1; i >= 0; i--) {
                suffixMaxGap[i] = Math.max(suffixMaxGap[i + 1], freeGaps[i]);
            }
        }
       
        int overallMaxFreeTime = (n >= 0) ? prefixMaxGap[n] : 0;

        for (int k = 1; k <= n; k++) {
            int currentMeetingDuration = meetings.get(k)[1] - meetings.get(k)[0];

            int maxFreeFromPrefixPart = 0;
            if (k - 2 >= 0) {
                maxFreeFromPrefixPart = prefixMaxGap[k - 2];
            }

            int maxFreeFromSuffixPart = 0;
            if (k + 1 <= n) {
                maxFreeFromSuffixPart = suffixMaxGap[k + 1];
            }

            int maxOfUnaffectedGaps = Math.max(maxFreeFromPrefixPart, maxFreeFromSuffixPart);

            int mergedGapSize = meetings.get(k + 1)[0] - meetings.get(k - 1)[1];
            int freeTimeInMergedSlotIfUsed = mergedGapSize - currentMeetingDuration;
            
            int potentialMaxFreeTimeForThisMove = Math.max(maxOfUnaffectedGaps, Math.max(0, freeTimeInMergedSlotIfUsed));
            
            overallMaxFreeTime = Math.max(overallMaxFreeTime, potentialMaxFreeTimeForThisMove);
        }

        return overallMaxFreeTime;
    }
}