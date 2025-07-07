// Problem Statement:
// Given an array of events `events[i] = [startDay_i, endDay_i]`, where each event `i` starts at `startDay_i` and ends at `endDay_i`.
// You can attend an event on any day `d` such that `startDay_i <= d <= endDay_i`. You can only attend one event per day.
// Return the maximum number of events you can attend.

// Approach:
// Sort events by their start days. Use a min-priority queue to keep track of available events, prioritizing those that end earliest. Iterate through days, adding events that start on the current day to the priority queue and removing those that have already ended. If events are available, attend the one that ends soonest.

// Time Complexity:
// O(N log N), where N is the number of events (due to sorting and priority queue operations).

// Space Complexity:
// O(N) for the priority queue.

// Optimal Solution:
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Collections; // Not explicitly used but good for general import awareness

class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int eventsAttended = 0;
        int eventIndex = 0;
        int n = events.length;
        int maxDay = 0;

        for (int[] event : events) {
            maxDay = Math.max(maxDay, event[1]);
        }
        for (int day = 1; day <= maxDay; day++) {
            while (eventIndex < n && events[eventIndex][0] == day) {
                minHeap.offer(events[eventIndex][1]);
                eventIndex++;
            }
             while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }
            if (!minHeap.isEmpty()) {
                minHeap.poll();
                eventsAttended++;
            }
        }
        return eventsAttended;
    }
}