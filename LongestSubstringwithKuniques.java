// Problem Statement:
// You are given a string s consisting only lowercase alphabets and an integer k. Your task is to find the length of the longest substring that contains exactly k distinct characters.
// Note: If no such substring exists, return -1.

// Approach:
// The problem can be solved using a sliding window approach. We maintain a window [i, j] and a frequency map (or an array) to store the count of characters within the current window.
// We expand the window by moving the right pointer 'j'. For each character encountered at 'j', we increment its count in the frequency map.
// We keep track of the number of distinct characters in the current window.
// If the number of distinct characters in the window is equal to k, we update our maximum length with the current window's length (j - i + 1).
// If the number of distinct characters exceeds k, we need to shrink the window from the left. We move the left pointer 'i' and decrement the count of the character at 'i' in the frequency map. If the count of a character becomes 0 after decrementing, it means that character is no longer in the window, so we decrease our distinct character count. We continue shrinking the window until the number of distinct characters is again less than or equal to k.
// If after iterating through the entire string, we haven't found any substring with exactly k distinct characters, it means no such substring exists, and we return -1. Otherwise, we return the maximum length found.

// Time Complexity: O(N)
// We iterate through the string with two pointers, 'i' and 'j', each traversing the string at most once. The operations within the loop (map updates, comparisons) take constant time because the character set is limited to 26 lowercase alphabets.
// Space Complexity: O(1)
// We use a frequency array of size 26 to store character counts, which is constant space.

class Solution {
    public int longestKSubstr(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
         // To store frequency of characters
        int distinctCount = 0; 
        // To store count of distinct characters in current window
        int maxLength = -1; 

        int i = 0; 

        for (int j = 0; j < n; j++) { 
            char currentChar = s.charAt(j);
            if (freq[currentChar - 'a'] == 0) {
                distinctCount++;
            }
            freq[currentChar - 'a']++;

            while (distinctCount > k) {
                char charToRemove = s.charAt(i);
                freq[charToRemove - 'a']--;
                if (freq[charToRemove - 'a'] == 0) {
                    distinctCount--;
                }
                i++;
            }
            if (distinctCount == k) {
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }
}