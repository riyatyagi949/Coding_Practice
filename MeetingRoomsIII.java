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

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.offer(i);
        }

        PriorityQueue<long[]> occupiedRooms = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            return Integer.compare((int) a[1], (int) b[1]);
        }); // {finishTime, roomNumber}

        int[] meetingCount = new int[n];

        for (int[] meeting : meetings) {
            long currentStart = meeting[0];
            long duration = meeting[1] - meeting[0];

            while (!occupiedRooms.isEmpty() && occupiedRooms.peek()[0] <= currentStart) {
                availableRooms.offer((int) occupiedRooms.poll()[1]);
            }

            if (!availableRooms.isEmpty()) {
                int room = availableRooms.poll();
                meetingCount[room]++;
                occupiedRooms.offer(new long[]{currentStart + duration, room});
            } else {
                long[] earliestFreeRoom = occupiedRooms.poll();
                long finishTime = earliestFreeRoom[0];
                int room = (int) earliestFreeRoom[1];

                meetingCount[room]++;
                occupiedRooms.offer(new long[]{finishTime + duration, room});
            }
        }

        int maxMeetings = 0;
        int resultRoom = -1;
        for (int i = 0; i < n; i++) {
            if (meetingCount[i] > maxMeetings) {
                maxMeetings = meetingCount[i];
                resultRoom = i;
            }
        }
        return resultRoom;
    }
}