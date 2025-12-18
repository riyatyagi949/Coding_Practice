/**
 * PROBLEM STATEMENT: 
 * Given prices and a trading strategy (-1 Buy, 0 Hold, 1 Sell), calculate the max profit.
 * You can modify ONE window of size k: first k/2 days become 'Hold' (0), last k/2 days 
 * become 'Sell' (1).
 * * OPTIMAL SOLUTION:
 * 1. Calculate the base profit without modifications.
 * 2. Use Prefix Sums to track the "Gain" of changing any index to 0 or 1.
 * 3. Sliding Window: Iterate through all possible start indices for the k-window 
 * and find the one that provides the maximum gain over the base profit.
 * * TIME COMPLEXITY: O(N) - We iterate through the arrays a few times linearly.
 * SPACE COMPLEXITY: O(N) - To store the prefix sum arrays.
 */
// Code - 
 class Solution {
    public long maxProfit(int[] p, int[] s, int k) {
        int n = p.length;
        long[] p1 = new long[n + 1];
        long[] p2 = new long[n + 1];
        for (int i = 0; i < n; i++) {
            p1[i + 1] = p1[i] + (long) s[i] * p[i];
            p2[i + 1] = p2[i] + p[i];
        }
        long max = 0;
        for (int i = 0; i <= n - k; i++) {
            int m = i + k / 2;
            int e = i + k;
            long op = p1[e] - p1[i];
            long np = p2[e] - p2[m];
            max = Math.max(max, np - op);
        }
        return p1[n] + max;
    }
 }
