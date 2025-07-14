// Problem Statement:
// You are given a binary string s consisting only of characters '0' and '1'. Your task is to split this string into the minimum number of non-empty substrings such that:
// Each substring represents a power of 5 in decimal (e.g., 1, 5, 25, 125, ...).
// No substring should have leading zeros.
// Return the minimum number of such pieces the string can be divided into.
// Note: If it is not possible to split the string in this way, return -1.

// Approach:
// This problem can be solved using dynamic programming. We want to find the minimum number of substrings, which suggests a DP approach where dp[i] represents the minimum number of pieces required to split the prefix of length i (s[0...i-1]).

// First, we need a way to check if a binary string represents a power of 5. We can precompute a set of binary strings that represent powers of 5 up to a certain length (since s.size() <= 30, the maximum power of 5 we need to consider is 5^12, which is 1220703125, whose binary representation has 31 bits).
// A more efficient way to check is to convert the binary substring to a long decimal and then check if it's a power of 5. A number 'n' is a power of 5 if log base 5 of 'n' is an integer, or more simply, if we keep dividing 'n' by 5 until it becomes 1 (and all intermediate divisions are clean, i.e., remainder is 0).

// Let dp[i] be the minimum number of cuts needed for the prefix s[0...i-1].
// Initialize dp[0] = 0 (empty string needs 0 cuts).
// Initialize all other dp[i] to infinity.

// Iterate i from 1 to s.length():
//   Iterate j from 0 to i-1:
//     Consider the substring s[j...i-1].
//     If s[j] is '0', it's an invalid substring (leading zero) unless the substring is just "0", but "0" is not a power of 5. So, skip if s[j] is '0'.
//     Convert the substring s[j...i-1] to a decimal number.
//     Check if this decimal number is a power of 5.
//     If it is a power of 5 and dp[j] is not infinity:
//       dp[i] = min(dp[i], dp[j] + 1).

// Finally, dp[s.length()] will contain the minimum number of pieces. If it's still infinity, return -1.

// Time Complexity:
// The outer loop runs `s.length()` times.
// The inner loop runs `s.length()` times.
// Inside the inner loop, converting a substring to a decimal number takes O(length of substring) time, which is at most O(s.length()).
// Checking if a number is a power of 5 takes O(log_5(N)) time, where N is the decimal value. Since N can be up to 2^30, log_5(2^30) is approximately 30 * log_5(2) which is a small constant (around 13). So, this check is effectively constant time.
// Total time complexity: O(s.length() * s.length() * s.length()) = O(N^3), where N is the length of the string.
// Given N <= 30, N^3 is 30^3 = 27000, which is well within limits.

// Space Complexity:
// O(s.length()) for the dp array.

import java.util.*;

class Solution {
    public int cuttingBinaryString(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                String sub = s.substring(j, i);
                if (sub.length() > 1 && sub.charAt(0) == '0') {
                    continue;
                }
                
                // Convert binary substring to long
                long decimalValue = 0;
                try {
                    decimalValue = Long.parseLong(sub, 2);
                } catch (NumberFormatException e) {
                    // This should ideally not happen for valid binary substrings within length limits
                    continue; 
                }

                // Check if it's a power of 5
                if (isPowerOfFive(decimalValue)) {
                    if (dp[j] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }

        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }

    private boolean isPowerOfFive(long n) {
        if (n <= 0) {
            return false;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}