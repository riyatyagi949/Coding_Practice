// Problem Statement:
// Given a string s, find the length of the longest periodic proper prefix of s.
// A periodic proper prefix is a non-empty prefix of s (but not the whole string)
// such that repeating this prefix enough times produces a string that starts with s.
// If no such prefix exists, return -1.

// Approach:
// This solution uses the Z-algorithm to find the length of the longest periodic proper prefix.
// The Z-algorithm computes a Z-array where z[i] is the length of the longest substring starting at s[i]
// that is also a prefix of s.
// A periodic proper prefix of length `k` exists if the suffix of s starting at index `k`
// is a prefix of s and has a length of at least `n-k`, where `n` is the length of s.
// This condition is checked by iterating from the end of the Z-array. For a potential prefix length `k`,
// we check if `z[k]` (the length of the matching prefix starting at `k`) is equal to or greater than `n-k`
// (the length of the suffix starting at `k`). The first `k` (from `n-1` down to 1) that satisfies this
// condition will give the length of the longest periodic proper prefix.

// Time Complexity: O(N), where N is the length of the string, as the Z-algorithm runs in linear time.
// Space Complexity: O(N), for storing the Z-array.

class Solution {
    int getLongestPrefix(String s) {
        int n = s.length();
        if (n <= 1) return -1;

        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) z[i] = Math.min(r - i + 1, z[i - l]);
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) z[i]++;
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        for (int k = n - 1; k >= 1; k--) {
            if (z[k] >= n - k) return k;
        }
        return -1;
    }
}

