/**
 * Problem: 231. Power of Two
 * Given an integer n, return true if it is a power of two. Otherwise, return false.
 * An integer n is a power of two, if there exists an integer x such that n == 2x.
 */
// Approach: Bit Manipulation
// A number is a power of two if it is positive and has exactly one bit set to 1 in its binary representation.
// For example, 1 (0001), 2 (0010), 4 (0100), 8 (1000).
// The key insight is that for any positive power of two 'n', the expression 'n & (n-1)' will always be 0.
// This is because subtracting 1 from a power of two 'n' flips the rightmost set bit to 0 and all the bits to its right to 1.
// The AND operation then cancels out all bits, resulting in 0.
// For example, if n = 8 (1000 in binary), n-1 = 7 (0111 in binary). 8 & 7 = 0.
// The solution first checks if n is greater than 0. Then, it uses the bitwise AND trick to check if it's a power of two.

// Time Complexity: O(1)
// Space Complexity: O(1)

// Optimal Solution - 
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
}