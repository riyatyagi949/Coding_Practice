// Problem Statement
// Given two positive integers n and x. Return the number of ways n can be expressed as the sum of the xth power of unique positive integers, in other words, the number of sets of unique integers [n1, n2, ..., nk] where n = n1^x + n2^x + ... + nk^x. Since the result can be very large, return it modulo 10^9 + 7.

// Approach
// This problem is a variation of the classic knapsack problem and can be solved using dynamic programming. We want to find the number of subsets of unique numbers whose xth powers sum up to n.

// Let's define a DP array, dp, of size n + 1, where dp[i] stores the number of ways to form the sum i. We initialize dp[0] to 1, as there is one way to achieve a sum of 0 (by selecting no numbers).

// We can iterate through all possible unique positive integers i starting from 1. For each i, we calculate its xth power, which is power_val = i^x. If power_val is greater than n, we can stop since no larger powers will fit.

// For each power_val, we update our dp array. To ensure that each number's xth power is used at most once, we iterate backward from j = n down to power_val. The update rule is dp[j] = (dp[j] + dp[j - power_val]) % MOD. This means the number of ways to form sum j is the sum of two possibilities:

// Ways to form sum j without using power_val. This is dp[j] from the previous iteration.

// Ways to form sum j by including power_val. This is the number of ways to form the remaining sum j - power_val, which is dp[j - power_val].

// The modulo operation 10^9 + 7 is applied at each addition to prevent integer overflow. The final answer will be stored in dp[n].

// Time and Space Complexity
// Time Complexity: The outer loop runs approximately n^(1/x) times, and the inner loop runs n times. Thus, the time complexity is O(n).

// Space Complexity: The space complexity is O(n) to store the DP array.

// Optimal Solution

class Solution {
    public int numberOfWays(int n, int x) {
        int MOD = 1000000007;
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; ; i++) {
            int powerVal = (int) Math.pow(i, x);
            if (powerVal > n) {
                break;
            }

            for (int j = n; j >= powerVal; j--) {
                dp[j] = (dp[j] + dp[j - powerVal]) % MOD;
            }
        }

        return dp[n];
    }
}
