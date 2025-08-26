// Problem Statement:
// Given two strings s1 and s2, check if s1 is a subsequence of s2.
// A subsequence is a sequence that can be derived from another sequence by deleting some elements without changing the order of the remaining elements.

// Approach:
// We can use a two-pointer approach. We'll use one pointer for string s1 (let's call it `i`) and another for string s2 (let's call it `j`).
// We iterate through both strings simultaneously. If the characters at the current pointers s1[i] and s2[j] match, we increment the pointer for s1. We always increment the pointer for s2, regardless of a match.
// This is because we are looking for the characters of s1 in s2 in the correct order. If we find a match, we move on to the next character in s1. If not, we just continue searching in s2.
// After iterating through s2, if our pointer for s1 has reached the end of s1 (i.e., `i` is equal to the length of s1), it means we have found all the characters of s1 in s2 in the correct order.
// Therefore, s1 is a subsequence of s2.

// Time Complexity:
// O(N), where N is the length of string s2. We iterate through s2 at most once.

// Space Complexity:
// O(1), as we are only using a few pointers and no extra data structures.

// Optimal Solution:
class Solution {
    public boolean isSubSequence(String s1, String s2) {
        int i = 0; // pointer for s1
        int j = 0; // pointer for s2

        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == s1.length();
    }
}