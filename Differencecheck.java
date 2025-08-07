/*
Problem Statement:
Given an array `arr[]` of time strings in 24-hour clock format "HH:MM:SS", return the minimum difference in seconds between any two time strings in the array. The clock wraps around at midnight, so the time difference between "23:59:59" and "00:00:00" is 1 second.

Approach:
1. Convert each time string "HH:MM:SS" into the total number of seconds from midnight (00:00:00).
2. Store these total seconds in an integer array.
3. Sort this array of seconds in ascending order.
4. Iterate through the sorted array and find the minimum difference between adjacent elements.
5. The clock wraps around, so also consider the difference between the first and last elements, which represents the time difference across midnight.
6. The minimum difference will be the smallest of all adjacent differences and the wrap-around difference.

Time Complexity:
O(N log N) due to sorting the array of time values.
Converting each string takes constant time, and iterating through the sorted array takes O(N).

Space Complexity:
O(N) to store the integer array of converted time values.
*/
import java.util.*;

class Solution {
    public int minDifference(String[] arr) {
        int n = arr.length;
        int[] seconds = new int[n];

        for (int i = 0; i < n; i++) {
            seconds[i] = toSeconds(arr[i]);
        }
        Arrays.sort(seconds);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            minDiff = Math.min(minDiff, seconds[i] - seconds[i - 1]);
        }
        int circularDiff = 86400 - seconds[n - 1] + seconds[0];
        minDiff = Math.min(minDiff, circularDiff);

        return minDiff;
    }
    private int toSeconds(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int s = Integer.parseInt(parts[2]);
        
        return h * 3600 + m * 60 + s;
    }
}
