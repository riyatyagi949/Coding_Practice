// Problem Statement:
// Given a string s consisting of lowercase English letters and an integer k, your task is to remove exactly k characters from the string. The resulting string must be the largest possible in lexicographical order, while maintaining the relative order of the remaining characters.

// Approach:
// This problem can be solved using a greedy approach with a monotonic stack (or a similar logic without explicitly using a stack data structure, but rather building the result string directly).
// The idea is to iterate through the string and try to build the lexicographically largest string of length s.length() - k.
// We want to keep larger characters and remove smaller characters that appear before larger ones, if we still have deletions available.
// We can iterate through the string `s` character by character. For each character, we compare it with the last character added to our `result` string.
// If the current character is greater than the last character in `result` and we still have characters to delete (i.e., `k > 0`) and removing the last character from `result` will still allow us to form a string of the required length (s.length() - k), then we remove the last character from `result` and decrement `k`. We repeat this process until the condition is no longer met.
// After this, we add the current character to our `result` string if the `result` string has not yet reached its target length (s.length() - k).

// More formally:
// Let `result` be a StringBuilder to store our final string.
// We need to keep `n - k` characters, where `n` is the length of `s`.
// Iterate through the input string `s` from left to right.
// For each character `c` at index `i`:
// While `result` is not empty, `c` is greater than the last character in `result`, and the number of characters we need to remove is greater than 0, AND (the remaining characters in `s` from `i` to `n-1` plus the current `result.length()` is greater than or equal to `n-k`):
//     Remove the last character from `result`.
//     Decrement `k`.
// If `result.length()` is less than `n - k`:
//     Append `c` to `result`.
// After iterating through all characters, `result` will contain the lexicographically largest string.

// Time Complexity:
// The time complexity will be O(n), where n is the length of the string `s`.
// Each character from the input string `s` is processed once. In the worst case, a character might be pushed onto the `result` StringBuilder and then popped multiple times, but each character is pushed and popped at most once across the entire operation. Therefore, the total number of operations remains proportional to n.

// Space Complexity:
// The space complexity will be O(n) in the worst case, as the `result` StringBuilder can store up to `n - k` characters, which can be up to `n` characters if `k` is 0.

class Solution {
    public static String maxSubseq(String s, int k) {
        StringBuilder result = new StringBuilder();
        int n = s.length();
        int charsToKeep = n - k;

        for (int i = 0; i < n; i++) {
            char currentChar = s.charAt(i);
            while (result.length() > 0 && currentChar > result.charAt(result.length() - 1) && k > 0 &&
                   (result.length() + (n - 1 - i)) >= charsToKeep) {
                result.deleteCharAt(result.length() - 1);
                k--;
            }
            if (result.length() < charsToKeep) {
                result.append(currentChar);
            } else {
            }
        }
        return result.toString();
    }
}