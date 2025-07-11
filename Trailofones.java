// Problem Statement:
// Given an integer n, count the number of binary strings of length n that contain at least one pair of consecutive 1's.

// Approach:
// Calculate total binary strings (2^n).
// Calculate strings without consecutive 1's using Fibonacci numbers (fib(n+2) where fib(0)=0, fib(1)=1).
// Subtract strings without consecutive 1's from the total.

// Time Complexity: O(n)
// Space Complexity: O(1)

// Optimal Solution:
class Solution {
    long fib(int n) {
        if (n <= 1) {
            return n;
        }
        long a = 0;
        long b = 1;
        for (int i = 2; i <= n; i++) {
            long c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public long numberOfConsecutiveOnes(int n) {
        long totalStrings = 1L << n;
        long stringsWithoutConsecutiveOnes = fib(n + 2);
        return totalStrings - stringsWithoutConsecutiveOnes;
    }
}