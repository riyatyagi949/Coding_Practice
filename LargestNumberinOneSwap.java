// Problem Statement:
// Given a string s, return the lexicographically largest string that can be obtained by swapping at most one pair of characters in s.

// Approach:
// To get the lexicographically largest string, we should try to place the largest possible digit at the leftmost position where a swap can improve the string.
// We iterate through the string from left to right. For each character `s[i]`, we find the largest character `s[j]` to its right (i.e., j > i) that is greater than `s[i]`. If there are multiple occurrences of the largest character, we should swap with the rightmost one to ensure the resulting string is as large as possible.
// If we find such a character `s[j]`, we swap `s[i]` and `s[j]` and return the new string. This is because we have made the most significant change to get a lexicographically larger string (i.e., we are changing the most significant digit).
// If we iterate through the entire string and don't find a pair to swap that would make the string lexicographically larger, it means the string is already in a non-increasing order (or all characters are the same), and no single swap can improve it. In this case, we return the original string.

// Time Complexity: O(n^2)
// The outer loop iterates from 0 to n-1. The inner loop, which finds the largest character to the right, also takes O(n) time. This results in a nested loop structure, giving a time complexity of O(n^2).

// Space Complexity: O(n)
// We are converting the string to a character array to perform the swap, which requires O(n) space.

// Optimal Solution:
