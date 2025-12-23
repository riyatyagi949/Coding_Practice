/**
 * PROBLEM STATEMENT: 2054. Two Best Non-Overlapping Events
 * ---------------------------------------------------------------------------------------
 * You are given a 0-indexed 2D integer array of events where events[i] = [startTime_i, endTime_i, value_i].
 * Each event provides a certain 'value'. You can choose at most TWO non-overlapping events 
 * to attend such that the sum of their values is maximized.
 * * Non-overlapping condition:
 * If you attend an event ending at time 't', the next event must start at or after 't + 1'.
 * * Constraints:
 * - 2 <= events.length <= 10^5
 * - 1 <= startTime_i <= endTime_i <= 10^9
 * - 1 <= value_i <= 10^6
 * ---------------------------------------------------------------------------------------
 */
/**
 * OPTIMAL SOLUTION: Sorting + Suffix Maximum + Binary Search
 * ---------------------------------------------------------------------------------------
 * The core challenge is efficiently finding the best "second" event for every "first" event.
 * * 
 * * ALGORITHM STEPS:
 * 1. SORTING: Sort the events array based on their start times. This allows us to use binary 
 * search to find compatible events that start after a current event ends.
 * * 2. SUFFIX MAX ARRAY: Create an array 'suffixMax' where suffixMax[i] stores the maximum 
 * value among all events from index 'i' to the end of the sorted array. 
 * This helps us quickly pick the highest-value event available in the future.
 * * 3. ITERATION & BINARY SEARCH: For each event 'e':
 * - Assume 'e' is our first event.
 * - Use Binary Search to find the first event index 'j' such that events[j].startTime > e.endTime.
 * - If such a 'j' exists, the best second event is suffixMax[j].
 * - Calculate total value: e.value + (j < n ? suffixMax[j] : 0).
 * - Update the global maximum answer.
 * ---------------------------------------------------------------------------------------
 */
/**
 * COMPLEXITY ANALYSIS
 * ---------------------------------------------------------------------------------------
 * TIME COMPLEXITY: $O(N \log N)$
 * - Sorting the events array takes $O(N \log N)$.
 * - Building the suffix maximum array takes $O(N)$.
 * - Iterating through N events and performing a binary search for each takes $O(N \log N)$.
 * * SPACE COMPLEXITY: $O(N)$
 * - We store a suffixMax array of size $N+1$.
 * - Depending on the sort implementation, the recursion stack for sorting may take $O(\log N)$.
 * ---------------------------------------------------------------------------------------
 */
// Code - 
class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int n = events.length;
        int[] f = new int[n + 1];

        for (int i = n - 1; i >= 0; --i) {
            f[i] = Math.max(f[i + 1], events[i][2]);
        }
        int ans = 0;
        for (int[] e : events) 
        {
            int v = e[2];
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (events[mid][0] > e[1]) {
                    right = mid;
                } 
                else {
                    left = mid + 1;
                }
            }
            if (left < n) {
                v += f[left];
            }
            ans = Math.max(ans, v);
        }
        return ans;
    }
}
