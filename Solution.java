// Problem Statement:
// Given two strings s and p. Find the smallest substring in s consisting of all the characters (including duplicates) of the string p. Return empty string in case no such substring is present.
// If there are multiple such substring of the same length found, return the one with the least starting index.

// Approach:
// The problem can be solved efficiently using the Sliding Window technique.
// 1. We first count the frequency of each character in the pattern string `p` and store it in a hash map or an array.
// 2. We use two pointers, `left` and `right`, to define the current window in the string `s`. The `right` pointer expands the window, and the `left` pointer contracts it.
// 3. We maintain a `matchCount` to keep track of how many characters from `p` have been found in the current window of `s` with the required frequency.
// 4. As we move the `right` pointer, we update the frequency map for characters in `s`. If a character at `s[right]` is part of `p` and its frequency in the current window matches the required frequency from `p`, we increment `matchCount`.
// 5. Once `matchCount` equals the total number of unique characters in `p`, we have found a valid window. We then try to optimize this window by moving the `left` pointer.
// 6. As we move the `left` pointer, we check if the character at `s[left]` is needed for the valid window. If its frequency in the window is greater than the required frequency from `p`, we can safely shrink the window.
// 7. We update the minimum length and the starting index of the smallest valid window found so far.
// 8. We repeat this process until the `right` pointer reaches the end of string `s`.
// This approach ensures that we check all possible substrings and find the smallest one in linear time.

// Time Complexity: O(N + M), where N is the length of `s` and M is the length of `p`.
// We iterate through the string `s` with two pointers, `left` and `right`, and the frequency map operations are constant time.
// Space Complexity: O(1) or O(K) where K is the size of the character set (26 for lowercase English letters).
// We use a frequency map to store character counts, which takes constant space as the alphabet size is fixed.

// Optimal Solution:
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String smallestWindow(String s, String p) {
        if (s == null || p == null || s.length() < p.length()) {
            return "";
        }

        Map<Character, Integer> pFreq = new HashMap<>();
        for (char c : p.toCharArray()) {
            pFreq.put(c, pFreq.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int startIndex = -1;
        int matchCount = 0;

        Map<Character, Integer> sFreq = new HashMap<>();

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            sFreq.put(rightChar, sFreq.getOrDefault(rightChar, 0) + 1);

            if (pFreq.containsKey(rightChar) && sFreq.get(rightChar).intValue() <= pFreq.get(rightChar).intValue()) {
                matchCount++;
            }

            while (matchCount == p.length()) {
                int currentLen = right - left + 1;
                if (currentLen < minLen) {
                    minLen = currentLen;
                    startIndex = left;
                }

                char leftChar = s.charAt(left);
                sFreq.put(leftChar, sFreq.get(leftChar) - 1);
                if (pFreq.containsKey(leftChar) && sFreq.get(leftChar).intValue() < pFreq.get(leftChar).intValue()) {
                    matchCount--;
                }
                left++;
            }
            right++;
        }

        if (startIndex == -1) {
            return "";
        }

        return s.substring(startIndex, startIndex + minLen);
    }
}