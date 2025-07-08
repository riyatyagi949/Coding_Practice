/*
Problem Statement:
Given an array of events `events[i] = [startDayi, endDayi, valuei]` and an integer `k` (max events to attend), find the maximum total value. You can attend only one event at a time, and events cannot overlap (endDay is inclusive, so if an event ends on day X, the next can start on day X+1 or later).

Approach:
Use dynamic programming with memoization. Sort events by start day.
`memo[idx][count]` stores the max value from `events[idx]` onwards, attending at most `count` events.
For each event `events[idx]`:
1. Don't attend: `solve(idx + 1, count)`
2. Attend: `events[idx][2]` + `solve(next_event_idx, count - 1)`. `next_event_idx` is found using binary search for the first event starting strictly after `events[idx][1]`.

Time Complexity: O(N * K * logN)
Space Complexity: O(N * K)
*/

import java.util.*;

class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[1]));
        int n = events.length;

        int[][] dp = new int[n + 1][k + 1];
        int[] endTimes = new int[n];

        for (int i = 0; i < n; i++) {
            endTimes[i] = events[i][1];
        }
          for (int i = 1; i <= n; i++) {
            int[] event = events[i - 1];
            int start = event[0], end = event[1], val = event[2];
            int prevIndex = binarySearch(events, start);

            for (int j = 1; j <= k; j++) {
                // Max of: skipping current OR taking current + best from previous non-overlapping
                dp[i][j] = Math.max(dp[i - 1][j],
                                    dp[prevIndex + 1][j - 1] + val);
            }
        }

        return dp[n][k];
    }

    // Binary search to find last event that ends before startDay
    private int binarySearch(int[][] events, int startDay) {
        int left = 0, right = events.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (events[mid][1] < startDay) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }
}
