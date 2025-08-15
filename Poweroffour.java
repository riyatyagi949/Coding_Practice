// Problem Statement:
// Given an integer n, return true if it is a power of four. Otherwise, return false.
// An integer n is a power of four, if there exists an integer x such that n == 4^x.

// Approach:
// An integer n is a power of four if it is a positive integer, a power of two,
// and its only set bit is at an even position (0-indexed) from the right.
// A number is a power of two if it is positive and has only one set bit. This can be checked with the bitwise operation `(n > 0) && (n & (n - 1)) == 0`.
// To check if the single set bit is at an even position, we can use a bitmask. The bitmask `0x55555555` in hexadecimal represents `01010101...0101` in binary.
// The set bits in this mask are at positions 0, 2, 4, 6, etc.
// If n is a power of two and `(n & 0x55555555)` is equal to n, it means the single set bit of n
// is at one of the positions covered by the mask, which are the even positions.

// Time Complexity: O(1)
// The solution uses bitwise operations, which are constant time operations.

// Space Complexity: O(1)
// The solution does not use any extra space that scales with the input size.

class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        if ((n & (n - 1)) != 0) {
            return false;
        }
        return (n & 0x55555555) == n;
    }
}