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
    public int countConsec(int n) {
        if (n < 2) return 0;

        int[] dp0 = new int[n + 1]; 
        int[] dp1 = new int[n + 1];
        dp0[1] = 1;
        dp1[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp0[i] = dp0[i - 1] + dp1[i - 1];
            dp1[i] = dp0[i - 1];
        }
        int validCount = dp0[n] + dp1[n];
        int total = 1 << n; 
        return total - validCount;
    }
}