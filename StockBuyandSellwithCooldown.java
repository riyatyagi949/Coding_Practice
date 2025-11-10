/**
 * Problem Statement: Stock Buy and Sell with Cooldown
 * ---------------------------------------------------
 * Given an array arr[], where arr[i] is the stock price on day 'i'. Find the 
 * maximum profit that can be made by buying and selling stocks, subject to a 
 * one-day cooldown constraint: after selling a stock, you cannot buy again on 
 * the next day.
 *
 * Example:
 * Input: arr[] = [0, 2, 1, 2, 3]
 * Output: 3 (Buy day 1 (0), Sell day 2 (2). Cooldown day 3. Buy day 4 (2), Sell day 5 (3). Profit: (2-0) + (3-2) = 3)
 *
 * Constraints:
 * 1 <= arr.size() <= 10^5
 * 1 <= arr[i] <= 10^4
 *//**
     * Optimal Solution: Dynamic Programming (State Machine/Optimized Space)
     * ----------------------------------------------------------------------
     * This problem is a classic Dynamic Programming problem solved by defining three 
     * mutually exclusive states for each day 'i':
     *
     * 1. **buy[i]:** Maximum profit ending on day 'i' where you **hold a stock** (must have bought today or held from yesterday).
     * 2. **sell[i]:** Maximum profit ending on day 'i' where you **do not hold a stock**, and you **sold today**.
     * 3. **cool[i]:** Maximum profit ending on day 'i' where you **do not hold a stock**, and you are in a **cooldown period** (meaning you sold yesterday or before).
     *
     * * Recurrence Relations:
     * - **buy[i]:** To hold stock on day 'i', you either:
     * - Held stock from day i-1: `buy[i-1]`
     * - Bought today (day i), which requires being in cooldown yesterday: `cool[i-1] - arr[i]`
     * - `buy[i] = max(buy[i-1], cool[i-1] - arr[i])`
     *
     * - **sell[i]:** To sell today (day i), you must have held stock yesterday:
     * - `sell[i] = buy[i-1] + arr[i]`
     *
     * - **cool[i]:** To be in cooldown on day 'i', you either:
     * - Were in cooldown yesterday: `cool[i-1]`
     * - Sold yesterday: `sell[i-1]`
     * - `cool[i] = max(cool[i-1], sell[i-1])`
     *
     * * Space Optimization:
     * Since `dp[i]` only depends on `dp[i-1]`, we can reduce the O(N) space complexity 
     * to O(1) by using three variables (`buy`, `sell`, `cool`) to store the previous 
     * day's states (`prevBuy`, `prevSell`, `prevCool`).
     */
// Code -

    class Solution { 
        public int maxProfit(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;

        int buy = -arr[0];
        int sell = 0;
        int cool = 0;

        for (int i = 1; i < n; i++) 
        {
            int prevBuy = buy;
            int prevSell = sell;
            int prevCool = cool;

            
            buy = Math.max(prevBuy, prevCool - arr[i]);
            sell = prevBuy + arr[i];
            cool = Math.max(prevCool, prevSell);
        }
        return Math.max(sell, cool);
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the number of days (length of arr[]).
 * - We iterate through the array exactly once, and each step involves constant time (O(1)) operations.
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - We only use a fixed number of variables (`buy`, `sell`, `cool`, `prevBuy`, etc.) 
 * regardless of the input size N.
 */