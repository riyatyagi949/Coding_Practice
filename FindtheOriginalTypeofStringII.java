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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  private static final int MOD = 1_000_000_007;

  public int possibleStringCount(String word, int k) {
    List<Integer> groups = getConsecutiveLetters(word);

    final int totalCombinations =
        (int) groups.stream().mapToLong(Integer::longValue).reduce(1L, (a, b) -> a * b % MOD);
    if (k <= groups.size())
      return totalCombinations;

    int[] dp = new int[k];
    dp[0] = 1; 

    for (int i = 0; i < groups.size(); ++i) {
      int[] newDp = new int[k];
      int windowSum = 0;
      int group = groups.get(i);
      for (int j = i; j < k; ++j) {
        newDp[j] = (newDp[j] + windowSum) % MOD;
        windowSum = (windowSum + dp[j]) % MOD;
        if (j >= group)
          windowSum = (windowSum - dp[j - group] + MOD) % MOD;
      }
      dp = newDp;
    }

    final int invalidCombinations = Arrays.stream(dp).reduce(0, (a, b) -> (a + b) % MOD);
    return (totalCombinations - invalidCombinations + MOD) % MOD;
  }

  private List<Integer> getConsecutiveLetters(final String word) {
    List<Integer> groups = new ArrayList<>();
    int group = 1;
    for (int i = 1; i < word.length(); ++i)
      if (word.charAt(i) == word.charAt(i - 1)) {
        ++group;
      } else {
        groups.add(group);
        group = 1;
      }
    groups.add(group);
    return groups;
  }
}
