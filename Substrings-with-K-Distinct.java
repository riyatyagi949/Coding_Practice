/**
 * PROBLEM STATEMENT: Substrings with Exactly K Distinct Characters
 * --------------------------------------------------------------------------------
 * Given a string 's' consisting of lowercase characters and an integer 'k', 
 * you have to count all possible substrings that have exactly k distinct characters.
 *
 * * Example 1:
 * Input: s = "abc", k = 2
 * Output: 2
 * Explanation: Valid substrings are ["ab", "bc"].
 *
 * * Example 2:
 * Input: s = "aba", k = 2
 * Output: 3
 * Explanation: Valid substrings are ["ab", "ba", "aba"].
 *
 * * Constraints:
 * 1 <= s.size() <= 10^6
 * 1 <= k <= 26 (Limited by lowercase English alphabet)
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SLIDING WINDOW (TWO POINTERS)
 * --------------------------------------------------------------------------------
 * Finding "exactly K" directly with a single sliding window is difficult because 
 * the window doesn't shrink in a way that helps count all valid internal segments.
 *
 * * Mathematical Insight:
 * Exactly(K) = AtMost(K) - AtMost(K - 1)
 *
 * 1. "AtMost(K)" calculates the total number of substrings where the count of 
 * distinct characters is <= K.
 * 2. If we subtract the count of substrings where distinct characters are <= K-1,
 * we are left with exactly the substrings that have exactly K distinct characters.
 *
 * * Sliding Window Mechanism for AtMost(K):
 * - Expand 'right' pointer and update frequency.
 * - If unique characters > K, shrink 'left' until unique characters <= K.
 * - For every 'right' pointer, the number of valid substrings ending at 'right' 
 * is (right - left + 1).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n)
 * - We traverse the string twice (once for atMostK(s, k) and once for atMostK(s, k-1)).
 * - Each pointer (left and right) moves from 0 to n exactly once.
 * - Array-based frequency map access is O(1).
 *
 * Space Complexity: O(1)
 * - We use a frequency array of size 26 (fixed size), regardless of string length.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -
class Solution {
    public int countSubstr(String s, int k) {
        return atMostK(s, k) - atMostK(s, k - 1);
    }
  private int atMostK(String s, int k) {
        if (k < 0)
        return 0;
        
        int[] freq = new int[26];
        int left = 0, distinct = 0;
        int count = 0;
        
        for (int right = 0; right < s.length(); right++) {
            int r = s.charAt(right) - 'a';
            if (freq[r] == 0) 
            distinct++;
            freq[r]++;
            
            while (distinct > k) {
                int l = s.charAt(left) - 'a';
                freq[l]--;
                if (freq[l] == 0) distinct--;
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }
}


