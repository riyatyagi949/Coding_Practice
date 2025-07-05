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

class Solution {
    public int findLucky(int[] arr) {
        int[] freq = new int[501]; 
        
        for (int num : arr) {
            freq[num]++;
        }
        for (int i = 500; i >= 1; i--) {
            if (freq[i] == i) {
                return i;
            }
        }
        return -1; 
    }
}
