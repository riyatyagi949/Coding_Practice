// Problem Statement:
// Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.
// Return the largest lucky integer in the array. If there is no lucky integer, return -1.

// Approach:
// Count the frequency of each number using a frequency map (or array).
// Iterate from the largest possible number (500, based on constraints) down to 1.
// The first number encountered whose frequency matches its value is the largest lucky integer.
// If no such number is found, return -1.

// Time Complexity: O(N + M) where N is array length, M is max element value (500). Effectively O(N).
// Space Complexity: O(M) for frequency map. Effectively O(1).

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findLucky(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int largestLucky = -1;
        for (int i = 500; i >= 1; i--) {
            if (freqMap.containsKey(i) && freqMap.get(i) == i) {
                largestLucky = i;
                break;
            }
        }
        return largestLucky;
    }
}