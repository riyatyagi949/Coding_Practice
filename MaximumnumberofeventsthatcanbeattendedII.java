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
class Solution {
    private int[][] events;
    private int k;
    private int[][] memo;

    public int maxValue(int[][] events, int k) {
        java.util.Arrays.sort(events, (a, b) -> a[0] - b[0]); // Sort by start day
        this.events = events;
        this.k = k;
        int n = events.length;
        this.memo = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memo[i], -1); // Initialize memo table with -1
        }
        return solve(0, k);
    }

    private int solve(int idx, int count) {
        if (count == 0 || idx == events.length) {
            return 0; // Base case: no more events to attend or no more events available
        }
        if (memo[idx][count] != -1) {
            return memo[idx][count]; // Return memoized result
        }

        // Option 1: Don't attend current event
        int skipCurrent = solve(idx + 1, count);

        // Option 2: Attend current event
        int currentVal = events[idx][2];
        int nextIdx = findNextEvent(idx + 1, events[idx][1]); // Find next non-overlapping event
        int attendCurrent = currentVal + solve(nextIdx, count - 1);

        return memo[idx][count] = Math.max(skipCurrent, attendCurrent); // Memoize and return max
    }

    // Binary search for the first event whose start day is > `endDay`
    private int findNextEvent(int searchStartIdx, int endDay) {
        int low = searchStartIdx;
        int high = events.length - 1;
        int result = events.length; // Default to no next event found

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (events[mid][0] > endDay) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}