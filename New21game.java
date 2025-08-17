/**
 * Problem Statement:
 * Alice plays a game where she starts with 0 points and draws numbers from [1, maxPts] until her score is k or more.
 * The goal is to find the probability that her final score is n or less.
 *
 * Approach:
 * This problem can be solved using dynamic programming. Let dp[i] be the probability of having a score of i.
 * The base case is dp[0] = 1, as Alice starts with 0 points with a probability of 1.
 * We want to calculate the probabilities for scores from 1 to n.
 *
 * The state transition is as follows:
 * To reach a score i (where i <= n), Alice must have had a score of j (where k-maxPts <= j < k) and then drawn a number (i-j).
 * The probability of reaching score i is the sum of probabilities of reaching previous scores j and then drawing the necessary number.
 * Since each draw from [1, maxPts] is equally likely with a probability of 1/maxPts, the total probability for dp[i] is:
 * dp[i] = (dp[i-1] + dp[i-2] + ... + dp[i-maxPts]) / maxPts
 *
 * This direct calculation would be O(n * maxPts), which is too slow.
 * We can optimize this using a sliding window sum.
 * Let `W` be the sum of probabilities of the last `maxPts` scores (i.e., `W = dp[i-1] + dp[i-2] + ... + dp[i-maxPts]`).
 * Then, `dp[i] = W / maxPts`.
 *
 * We can maintain this sum `W` in O(1) time at each step.
 * `W_new = W_old + dp[i-1] - dp[i-maxPts-1]`
 *
 * The main logic is:
 * 1. Initialize a `dp` array of size `n + 1`. `dp[0] = 1`.
 * 2. If `k` is 0 or `n >= k + maxPts`, the probability is 1.0. This handles edge cases. If `k` is 0, she stops immediately at 0 points, which is <= n. If `n >= k + maxPts`, any score she gets will be less than `k+maxPts`, so it's guaranteed she will stop at a score <= n, given that the maximum score she can get to is `k-1 + maxPts` which is less than or equal to `n+maxPts-1`. In general, it's easier to think that if `n >= k+maxPts`, all scores in the range `[k, k+maxPts-1]` are valid final scores that are `<= n`.
 * 3. Initialize a sliding window sum `s` with `dp[0]`.
 * 4. Iterate from `i = 1` to `n`.
 * 5. `dp[i] = s / maxPts`.
 * 6. If `i < k`, add `dp[i]` to the sliding window sum `s`.
 * 7. If `i >= maxPts`, subtract `dp[i-maxPts]` from `s`.
 * 8. The final answer is the sum of probabilities for scores from `k` to `n`, because Alice only stops drawing when her score is `k` or more.
 *
 * Time Complexity: O(n), as we iterate through the scores from 1 to n once.
 * Space Complexity: O(n), to store the `dp` array.
 */

//Optimal Solution - 

class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0 || n >= k + maxPts) {
            return 1.0;
        }

        double[] dp = new double[n + 1];
        dp[0] = 1.0;
        double s = 1.0;
        double ans = 0.0;

        for (int i = 1; i <= n; i++) {
            dp[i] = s / maxPts;
            if (i < k) {
                s += dp[i];
            }
             else {
                ans += dp[i];
            }
            if (i >= maxPts) {
                s -= dp[i - maxPts];
            }
        }
        return ans;
    }
}