// Problem Statement:
// Given a string `word` representing Alice's typed output and a positive integer `k`,
// find the total number of possible original strings Alice might have intended to type,
// with a length of at least `k`. Return the answer modulo 10^9 + 7.

// Approach:
// 1. Compress the `word`: Group consecutive identical characters into (character, count) blocks.
//    For example, "aaabbc" becomes blocks with lengths [3, 2, 1].
// 2. Calculate Total Unconstrained Ways: The total number of distinct original strings
//    possible without any length constraint is the product of the lengths of these blocks.
//    (e.g., for [3, 2, 1] it's 3 * 2 * 1 = 6 ways). This is because for each block of `n` identical characters,
//    there are `n` choices for how many times that character was originally typed.
// 3. Calculate Ways with Length Less Than `k` (L < k): Use dynamic programming.
//    `dp[j]` stores the number of ways to form an original string of exact length `j`.
//    Initialize `dp[0] = 1` (for an empty string).
//    Iterate through each block length. For each block, update `dp` values:
//    `next_dp[current_length]` is the sum of `dp[prev_length]` for all `prev_length`
//    such that `prev_length + l = current_length` and `1 <= l <= block_length`.
//    This summation can be optimized using prefix sums of `dp` values.
//    We only need to track `dp` values up to `k-1`.
// 4. Final Result: Subtract the number of ways to get strings of length less than `k`
//    from the total unconstrained ways. Ensure all operations are done modulo 10^9 + 7.

// Time Complexity:
// O(N + M * k), where N is `word.length()`, M is the number of distinct character blocks (at most N), and k is the given integer.
// In the worst case, M can be N (e.g., "ababab..."), leading to O(N * k).

// Space Complexity:
// O(N + k), where O(N) is for storing `blockLengths` (at most N elements) and O(k) for DP arrays.

class Solution {
    public int possibleStringCount(String word, int k) {
        int MOD = 1_000_000_007;

        List<Integer> blockLengths = new ArrayList<>();
        if (word.isEmpty()) {
            return 0;
        }

        int currentBlockLength = 1;
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                currentBlockLength++;
            } else {
                blockLengths.add(currentBlockLength);
                currentBlockLength = 1;
            }
        }
        blockLengths.add(currentBlockLength);

        long totalWays = 1;
        for (int bLen : blockLengths) {
            totalWays = (totalWays * bLen) % MOD;
        }

        int[] dp = new int[k];
        dp[0] = 1;

        for (int block_length : blockLengths) {
            int[] next_dp = new int[k];
            
            int[] prefix_sum_dp = new int[k + 1];
            for (int i = 1; i <= k; i++) {
                prefix_sum_dp[i] = (prefix_sum_dp[i - 1] + dp[i - 1]) % MOD;
            }

            for (int j = 1; j < k; j++) {
                int start_idx_in_prev_dp = Math.max(0, j - block_length);
                int end_idx_in_prev_dp = j - 1;

                if (start_idx_in_prev_dp <= end_idx_in_prev_dp) {
                    long num_ways_to_get_j = (prefix_sum_dp[end_idx_in_prev_dp + 1] - prefix_sum_dp[start_idx_in_prev_dp] + MOD) % MOD;
                    next_dp[j] = (int) num_ways_to_get_j;
                }
            }
            dp = next_dp;
        }

        long totalWaysLessThanK = 0;
        for (int j = 0; j < k; j++) {
            totalWaysLessThanK = (totalWaysLessThanK + dp[j]) % MOD;
        }

        return (int)((totalWays - totalWaysLessThanK + MOD) % MOD);
    }
}