// Problem Statement:
// Given a string s consisting of lowercase English letters, for every character whose first and last occurrences are at different positions,
// calculate the sum of ASCII values of characters strictly between its first and last occurrence.
// Return all such non-zero sums (order does not matter).

// Approach:
// To solve this problem, we need to find the first and last occurrence of each character in the string.
// We can use two arrays, `firstOccurrence` and `lastOccurrence`, of size 26 (for 'a' to 'z').
// Initialize `firstOccurrence` with -1 for all characters to indicate they haven't been seen yet.
// Iterate through the string to populate `firstOccurrence` and `lastOccurrence` arrays.
// For each character `c` at index `i`:
//   If `firstOccurrence[c - 'a']` is -1, set `firstOccurrence[c - 'a'] = i`.
//   Always set `lastOccurrence[c - 'a'] = i`.
// After populating these arrays, iterate from 'a' to 'z'.
// For each character `ch`:
//   If `firstOccurrence[ch - 'a']` is not -1 and `firstOccurrence[ch - 'a'] != lastOccurrence[ch - 'a']`,
//   it means the character appears more than once at different positions.
//   Calculate the sum of ASCII values of characters strictly between its first and last occurrence.
//   Iterate from `firstOccurrence[ch - 'a'] + 1` to `lastOccurrence[ch - 'a'] - 1` and add the ASCII value of each character to a running sum.
//   If the calculated sum is non-zero, add it to a list of results.
// Finally, return the list of non-zero sums.

// Time Complexity:
// O(N) for iterating through the string to find first and last occurrences.
// O(N) in the worst case for iterating through the substrings to calculate sums (each character in the string is visited at most twice for sum calculation across all distinct characters).
// The outer loop for characters runs 26 times, which is a constant.
// Total Time Complexity: O(N), where N is the length of the string s.

// Space Complexity:
// O(1) for `firstOccurrence` and `lastOccurrence` arrays (size 26).
// O(K) for the result list, where K is the number of characters that satisfy the condition. K can be at most 26.
// Total Space Complexity: O(1).

import java.util.*;

class Solution {
    public ArrayList<Integer> asciirange(String s) {
        Map<Character, Integer> firstPos = new HashMap<>();
        Map<Character, Integer> lastPos = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            firstPos.putIfAbsent(ch, i);
            lastPos.put(ch, i);
        }

        for (char ch : firstPos.keySet()) {
            int start = firstPos.get(ch);
            int end = lastPos.get(ch);
            if (start < end - 1) {
                int sum = 0;
                for (int i = start + 1; i < end; i++) {
                    sum += s.charAt(i);
                }
                if (sum > 0) {
                    result.add(sum);
                }
            }
        }

        return result;
    }
}
