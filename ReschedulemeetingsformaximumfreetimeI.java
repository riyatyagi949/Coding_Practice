// Problem Statement:
// Given an event duration `eventTime`, `n` non-overlapping meetings `[startTime[i], endTime[i]]`,
// and an integer `k` (maximum meetings to reschedule).
// Reschedule meetings to maximize the longest continuous period of free time.
// Relative order and non-overlapping property must be maintained.

// Approach:
// The problem can be rephrased as maximizing the sum of a contiguous block of free time segments.
// The free time segments are:
// 1. Before the first meeting: `startTime[0]`.
// 2. Between meeting `i` and `i+1`: `startTime[i+1] - endTime[i]`.
// 3. After the last meeting: `eventTime - endTime[n-1]`.
// Store these `n+1` segments in an array `gapsOnly`.

// To combine `q-p+1` free segments (from `gapsOnly[p]` to `gapsOnly[q]`),
// we must effectively "move" the `q-p` meetings that originally separated them.
// The constraint is that we can move at most `k` meetings, so `(q - p)` must be `<= k`.

// This translates to a **sliding window problem**:
// 1. Initialize `maxFreeTime = 0`, `currentWindowSum = 0`, and `left = 0`.
// 2. Iterate `right` from `0` to `n` (inclusive, covering all `n+1` gaps).
// 3. Add `gapsOnly[right]` to `currentWindowSum`.
// 4. While the number of meetings "bridged" (`right - left`) is greater than `k`:
//    Subtract `gapsOnly[left]` from `currentWindowSum`.
//    Increment `left`.
// 5. Update `maxFreeTime` with `max(maxFreeTime, currentWindowSum)`.

// Time Complexity: O(N)
// Space Complexity: O(N)

class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;

        long[] gapsOnly = new long[n + 1];

        // Gap before the first meeting
        gapsOnly[0] = startTime[0];

        // Gaps between consecutive meetings
        for (int i = 0; i < n - 1; i++) {
            gapsOnly[i + 1] = startTime[i + 1] - endTime[i];
        }

        // Gap after the last meeting
        gapsOnly[n] = eventTime - endTime[n - 1];

        long maxFreeTime = 0;
        long currentWindowSum = 0;
        int left = 0;

        for (int right = 0; right <= n; right++) {
            currentWindowSum += gapsOnly[right];

            // (right - left) represents the number of meetings bridged to combine gapsOnly[left]...gapsOnly[right]
            while ((right - left) > k) {
                currentWindowSum -= gapsOnly[left];
                left++;
            }

            maxFreeTime = Math.max(maxFreeTime, currentWindowSum);
        }

        return (int) maxFreeTime;
    }
}