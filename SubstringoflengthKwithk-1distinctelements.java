// Problem Statement:
// Given a string s consisting only lowercase alphabets and an integer k. Find the count of all substrings of length k which have exactly k-1 distinct characters.

// Approach:
// We can use a sliding window approach to solve this problem. We maintain a window of size k and a frequency map (or an array of size 26 for lowercase alphabets) to store the count of characters within the current window.
// We iterate through the string, adding characters to the window from the right and removing characters from the left.
// For each window, we count the number of distinct characters. If the count of distinct characters is equal to k-1, we increment our result.

// Time Complexity:
// The time complexity will be O(N) where N is the length of the string `s`.
// This is because we iterate through the string once with our sliding window.
// Inside the loop, operations like adding/removing characters from the frequency map and counting distinct characters (by iterating through the map or a fixed-size array) take constant time as the alphabet size is fixed (26).

// Space Complexity:
// The space complexity will be O(1) because we use a fixed-size frequency array (of size 26) to store character counts, which does not depend on the input string size.

import java.util.HashMap;

class Solution {
    public int substrCount(String s, int k) {
        // Edge case
        if (s == null || s.length() < k) return 0;

        int count = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        // Initialize the first window of size k
        for (int i = 0; i < k; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        // Check the first window
        if (map.size() == k - 1) count++;

        // Slide the window
        for (int i = k; i < s.length(); i++) {
            char outChar = s.charAt(i - k);  // character going out
            char inChar = s.charAt(i);       // character coming in

            // Decrease frequency of outgoing char
            map.put(outChar, map.get(outChar) - 1);
            if (map.get(outChar) == 0) map.remove(outChar);  // remove if count is 0

            map.put(inChar, map.getOrDefault(inChar, 0) + 1);
            if (map.size() == k - 1) count++;
        }
         return count;
    }
}
