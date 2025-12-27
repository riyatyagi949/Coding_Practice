/**
 * PROBLEM STATEMENT: 2402. Meeting Rooms III
 * --------------------------------------------------------------------------------
 * You are given an integer 'n' representing 'n' rooms numbered from 0 to n-1.
 * You are also given a 2D integer array 'meetings' where meetings[i] = [start, end].
 * All start times are unique.
 * * Rules for Allocation:
 * 1. Each meeting takes place in the unused room with the lowest number.
 * 2. If no rooms are free, the meeting is delayed until a room becomes free. 
 * The duration remains the same.
 * 3. When a room becomes free, meetings with earlier original start times get priority.
 * * Goal: Return the room number that held the most meetings. If tied, return the 
 * lowest room number.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SIMULATION WITH PRIORITY QUEUES
 * --------------------------------------------------------------------------------
 * To solve this efficiently, we simulate the process using two Priority Queues:
 * 1. availableRooms (Min-Heap): Stores indices of currently free rooms.
 * 2. ongoingMeetings (Min-Heap): Stores [endTime, roomIndex] of active meetings, 
 * sorted primarily by endTime.
 * * Algorithm Steps:
 * 1. Sort the meetings by original start time.
 * 2. For each meeting:
 * a. Check 'ongoingMeetings' and move any rooms whose meetings have finished 
 * (endTime <= current meeting's start) into 'availableRooms'.
 * b. If a room is available:
 * Pick the lowest index room, set its new endTime, and record the usage.
 * c. If no room is available:
 * Wait for the meeting that ends earliest. The new start time for the current 
 * meeting becomes that room's endTime. Calculate new endTime using original duration.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(M log M + M log N)
 * - Sorting meetings: O(M log M) where M is number of meetings.
 * - Processing each meeting: M iterations. Inside each, we perform Heap operations 
 * (poll/add) which take O(log N) where N is the number of rooms.
 * Space Complexity: O(N + M)
 * - Priority Queues store at most N rooms.
 * - Sorting might take O(M) or O(log M) depending on implementation.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -
import java.util.*;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        int[] count = new int[n];
        long[] timer = new long[n];

        int itr = 0;

        while (itr < meetings.length) 
        {
          int[] curr = meetings[itr];
          int start = curr[0];
          int end = curr[1];
          long dur = end - start;

         int room = -1;
         long earliest = Long.MAX_VALUE;
         int earliestRoom = -1;

         for (int i = 0; i < n; i++)
          {
           if (timer[i] < earliest)
            {
              earliest = timer[i];
              earliestRoom = i;
          }
         if (timer[i] <= start) {
          room = i;
         break;
         }
     }
      if (room != -1)
       {
        timer[room] = end;
        count[room]++;
        } 
        else {
         timer[earliestRoom] += dur;
         count[earliestRoom]++;
        }
      itr++;
 }
  int max = 0, idx = 0;
    for (int i = 0; i < n; i++) 
        {
         if (count[i] > max) {
          max = count[i];
          idx = i;
            }
        }
     return idx;
    }
}
