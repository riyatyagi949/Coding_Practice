// Problem Statement:
// Given a number represented as a string s (which may be very large), check whether it is divisible by 13 or not.

// Approach:
// Since the number can be very large, we cannot convert it directly to an integer type.
// We need to perform modular arithmetic. We can process the string digit by digit
// and maintain the remainder when divided by 13.
// The idea is similar to how we manually perform division.
// For a number N represented as a string, let the digits be d_k d_{k-1} ... d_1 d_0.
// The value of N can be written as d_k * 10^k + ... + d_1 * 10^1 + d_0 * 10^0.
// We are interested in N % 13.
// We can iterate through the string from left to right.
// Let 'current_remainder' be the remainder of the number processed so far.
// When we encounter a new digit 'd', the new number formed is (current_number * 10 + d).
// So, the new remainder will be (current_remainder * 10 + d) % 13.
// Initialize current_remainder to 0.
// For each character 'c' in the string s:
//   Convert 'c' to its integer value (c - '0').
//   Update current_remainder = (current_remainder * 10 + (c - '0')) % 13.
// After processing all digits, if current_remainder is 0, then the number is divisible by 13.

// Time Complexity:
// O(L), where L is the length of the string s. We iterate through the string once.

// Space Complexity:
// O(1), as we only use a constant amount of extra space to store the remainder.

class Solution {
    int isDivisibleBy13(String s) {
        int remainder = 0;
        for (char c : s.toCharArray()) {
            remainder = (remainder * 10 + (c - '0')) % 13;
        }
        if (remainder == 0) {
            return 1; // True
        } else {
            return 0; // False
        }
    }
}