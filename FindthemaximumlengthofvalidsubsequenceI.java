// Problem Statement:
// Given an integer array nums, find the length of the longest valid subsequence.
// A subsequence is valid if (sub[i] + sub[i+1]) % 2 is constant for all adjacent elements.

// Approach:
// The condition implies two possibilities for the subsequence:
// 1. All elements have the same parity (e.g., [odd, odd, odd] or [even, even, even]).
//    In this case, (sub[i] + sub[i+1]) % 2 == 0.
//    The longest such subsequence is simply the maximum of the count of even numbers and the count of odd numbers in the input array.
// 2. Elements alternate parity (e.g., [odd, even, odd] or [even, odd, even]).
//    In this case, (sub[i] + sub[i+1]) % 2 == 1.
//    We can find the longest alternating subsequence using dynamic programming with two states:
//    - `dp_even_end`: length of the longest alternating subsequence ending with an even number.
//    - `dp_odd_end`: length of the longest alternating subsequence ending with an odd number.
//    When an even number `num` is encountered, `dp_even_end` becomes `dp_odd_end + 1`.
//    When an odd number `num` is encountered, `dp_odd_end` becomes `dp_even_end + 1`.
//    The maximum of `dp_even_end` and `dp_odd_end` gives the length for this case.

// The overall answer is the maximum of the lengths from these two possibilities.

// Time Complexity: O(N), where N is the length of nums, due to a single pass.
// Space Complexity: O(1) for a few constant variables.

class Solution {
    public int maxLengthValidSubsequence(int[] nums) {
        int countEven = 0;
        int countOdd = 0;

        int dp_even_end = 0; 
        int dp_odd_end = 0;  
        // Longest alternating subsequence ending with an odd number

        for (int num : nums) {
            if (num % 2 == 0) {
                 // Current number is even
                countEven++;
                dp_even_end = dp_odd_end + 1; 
                // Extend an odd-ending alternating sequence
            } else { 
                // Current number is odd
                countOdd++;
                dp_odd_end = dp_even_end + 1;
                 // Extend an even-ending alternating sequence
            }
        }

        // Longest subsequence with same-parity elements
        int maxLenSameParity = Math.max(countEven, countOdd);

        // Longest subsequence with alternating-parity elements
        int maxLenAlternatingParity = Math.max(dp_even_end, dp_odd_end);

        return Math.max(maxLenSameParity, maxLenAlternatingParity);
    }
}