/*
Problem Statement:
Given an integer array `nums` and a positive integer `k`, find the length of the longest valid subsequence `sub` such that `(sub[i] + sub[i+1]) % k` is constant for all valid `i`.

Approach:
This problem uses dynamic programming. We define `dp[last_rem][common_sum_rem]` as the maximum length of a valid subsequence where the last element has `last_rem` (`last_rem = last_element % k`), and all adjacent pairs sum to `common_sum_rem` (`common_sum_rem = (sub[j] + sub[j+1]) % k`).

We iterate through each `num` in `nums`. For each `num`, we calculate `curr_rem = num % k`.
Then, for every possible `common_sum_rem` (from `0` to `k-1`):
1. Determine `needed_prev_rem`: the remainder `prev_val % k` such that `(prev_val % k + curr_rem) % k == common_sum_rem`. This is `(common_sum_rem - curr_rem + k) % k`.
2. Update `dp[curr_rem][common_sum_rem]`:
   - If `dp[needed_prev_rem][common_sum_rem]` is greater than 0, it means we can extend an existing subsequence. The new length would be `dp[needed_prev_rem][common_sum_rem] + 1`.
   - If `dp[needed_prev_rem][common_sum_rem]` is 0, it means no such subsequence ending with `needed_prev_rem` exists for this `common_sum_rem`. In this case, `num` can start a new subsequence (length 1). The `+1` logic naturally covers this (`0+1=1`).
   - We take the maximum of the current `dp[curr_rem][common_sum_rem]` and this potential new length.
3. Keep track of the overall maximum length found. Since any single element is a valid subsequence, the minimum overall length is 1.

Time Complexity: O(N * K) - `N` iterations for numbers, `K` iterations for possible common sums.
Space Complexity: O(K^2) - for the `dp` table.
*/
class Solution {
    public int maxLength(int[] nums, int k) {
        int[][] dp = new int[k][k];
        int maxOverallLen = 1;

        for (int num : nums) {
            int currRem = num % k;
            for (int commonSumRem = 0; commonSumRem < k; commonSumRem++) {
                int neededPrevRem = (commonSumRem - currRem + k) % k;
                if (dp[neededPrevRem][commonSumRem] > 0) {
                    dp[currRem][commonSumRem] = Math.max(dp[currRem][commonSumRem], dp[neededPrevRem][commonSumRem] + 1);
                } else {
                    dp[currRem][commonSumRem] = Math.max(dp[currRem][commonSumRem], 1);
                }
                maxOverallLen = Math.max(maxOverallLen, dp[currRem][commonSumRem]);
            }
        }
        return maxOverallLen;
    }
}