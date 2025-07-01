// Problem Statement:
// Given a string `word` representing the final output on Alice's screen. Alice might have pressed a key for too long at most once, causing a character to be typed multiple times. Return the total number of possible original strings Alice might have intended to type.

// Approach:
// The problem implies that we need to count unique strings that can be formed under two conditions:
// 1. The `word` itself (no key was pressed too long).
// 2. Exactly one block of consecutive identical characters in `word` originated from a shorter sequence in the original string due to a single "over-press" event.
// We parse the `word` into blocks of (character, count). We then iterate through each block. If a block has length `N > 1`, we generate `N-1` new strings by assuming the original count for that character was `1` to `N-1`, while keeping all other blocks unchanged. All generated strings are stored in a `HashSet` to ensure uniqueness.

// Time Complexity:
// O(L^2), where L is the length of the `word`.
// This is because parsing the string takes O(L). In the worst case (e.g., "aaaa..."), we might iterate L times in the inner loop, and each string construction can take O(L) time.

// Space Complexity:
// O(L^2) in the worst case.
// This is because the `HashSet` can store up to O(L) strings, and each string can have a maximum length of O(L). Therefore, the total space complexity for storing all unique strings is O(L * L) = O(L^2).

class Solution {
    public int possibleStringCount(String word) {
        int total = 1; 
        int i = 0, n = word.length();

        while (i < n) {
            int j = i;
            // Count the length of group of same characters
            while (j < n && word.charAt(i) == word.charAt(j)) {
                j++;
            }

            int len = j - i;

            // If this group has more than 1 character, we can remove 1..len-1 characters from it
            // These are possible original strings if this was the long-pressed group
            if (len > 1) {
                total += (len - 1);
            }

            i = j; // Move to next group
        }

        return total;
    }
}
