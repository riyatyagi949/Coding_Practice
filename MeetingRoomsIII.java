// Problem Statement:
// Given `n` rooms and a list of `meetings = [start, end]`, assign meetings to rooms.
// Rules:
// 1. Use the lowest-numbered available room.
// 2. If no room is available, delay the meeting until a room frees up. The delayed meeting keeps its original duration.
// 3. When a room frees up, prioritize meetings with earlier original start times.
// Return the room number that hosted the most meetings. If tied, return the lowest room number.

// Approach:
// Sort meetings by start time. Use two priority queues:
// 1. `availableRooms`: Min-heap of room numbers (0 to n-1), representing empty rooms.
// 2. `occupiedRooms`: Min-heap of `[finishTime, roomNumber]`, representing busy rooms, sorted by finish time then room number.
// Maintain a `meetingCount` array for each room.
// For each meeting:
//   - Release rooms from `occupiedRooms` to `availableRooms` if their finish time is <= current meeting's start time.
//   - If `availableRooms` is not empty, assign the meeting to the lowest numbered room. Update `meetingCount` and add to `occupiedRooms`.
//   - If `availableRooms` is empty, take the room that frees up earliest from `occupiedRooms`. Delay the current meeting to start when that room frees up, maintaining its duration. Update `meetingCount` and add back to `occupiedRooms`.
// Finally, find the room with the maximum `meetingCount`, breaking ties with the lowest room number.

// Time Complexity: O(M log M + M log N) where M is the number of meetings and N is the number of rooms.
// Space Complexity: O(N)

import java.util.*;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        int[] roomUsage = new int[n];  
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.offer(i); 
        }
        PriorityQueue<long[]> ongoingMeetings = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Integer.compare((int) a[1], (int) b[1]);
        });
        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];

            while (!ongoingMeetings.isEmpty() && ongoingMeetings.peek()[0] <= start) {
                availableRooms.offer((int) ongoingMeetings.poll()[1]);
            }
            if (!availableRooms.isEmpty()) {
                int room = availableRooms.poll();
                ongoingMeetings.offer(new long[]{end, room});
                roomUsage[room]++;
            } 
            else {
                long[] earliest = ongoingMeetings.poll();
                long newStart = earliest[0];
                int room = (int) earliest[1];
                long duration = end - start;
                long newEnd = newStart + duration;
                ongoingMeetings.offer(new long[]{newEnd, room});
                roomUsage[room]++;
            }
        }
     int maxMeetings = 0, resultRoom = 0;
        for (int i = 0; i < n; i++) {
            if (roomUsage[i] > maxMeetings) {
                maxMeetings = roomUsage[i];
                resultRoom = i;
            }
        }
        return resultRoom;
    }
}
