/**
 * Problem: 3573. Best Time to Buy and Sell Stock V
 * ---------------------------------------------------------------------------
 * Problem Statement:
 * You are given an array 'prices' and an integer 'k'. You can perform at most k transactions.
 * A transaction consists of two days (i, j) where i < j:
 * 1. Normal: Buy at i, Sell at j -> Profit = prices[j] - prices[i]
 * 2. Short Sell: Sell at i, Buy back at j -> Profit = prices[i] - prices[j]
 * * Each transaction must be completed before starting another (non-overlapping).
 * You cannot trade on the same day you finished a previous transaction.
 * * Optimal Solution:
 * Because we can choose to either buy then sell (prices[j] - prices[i]) OR sell 
 * then buy (prices[i] - prices[j]), the profit for any pair (i, j) is simply 
 * abs(prices[i] - prices[j]). 
 * * To maximize total profit, we look at all adjacent differences: diff = abs(prices[i] - prices[i-1]).
 * However, since we must wait a day to start a new transaction, this is a 
 * Dynamic Programming problem.
 * * DP State:
 * dp[i][j] = max profit using exactly 'i' transactions considering first 'j' days.
 * * Simplified observation:
 * Since we can short sell, we aren't restricted to "upward" trends. We want to 
 * pick the k largest non-adjacent absolute differences.
 */
/**
 * Time Complexity: O(k * n)
 * We iterate through the number of transactions k, and for each, we iterate 
 * through the price array of length n.
 * * Space Complexity: O(k * n)
 * We maintain a 2D DP table. This can be further optimized to O(n) by 
 * realizing we only need the current and previous rows.
 */
// Code - 
class Solution {
    public long maximumProfit(int[] prices, int k) {
        long[][] dp = new long[k + 1][3];

        for (int t = 0; t <= k; t++) {
            dp[t][0] = 0;                   
            dp[t][1] = Long.MIN_VALUE / 2;  
            dp[t][2] = Long.MIN_VALUE / 2;  
        }
         for (int price : prices) {
            for (int t = k; t >= 0; t--) {
                dp[t][1] = Math.max(dp[t][1], dp[t][0] - price);
                dp[t][2] = Math.max(dp[t][2], dp[t][0] + price); 

                if (t > 0) 
                {
                    dp[t][0] = Math.max(
                        dp[t][0],
                        Math.max(
                            dp[t - 1][1] + price,
                            dp[t - 1][2] - price 
                        )
                    );
                }
            }
        }
        long ans = 0;
        for (int t = 0; t <= k; t++) {
            ans = Math.max(ans, dp[t][0]);
        }
        return ans;
    }
}
