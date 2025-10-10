/**
 * Problem Statement:
 * Given an array `energy` representing the energy gained from `n` magicians and an integer `k`.
 * You must choose a starting magician (index `i`) and then jump to the next magician at index `i + k`,
 * repeating the process until the next jump is out of bounds. The total energy gained is the sum of
 * energies from the chosen path.
 * Return the maximum possible total energy you can gain across all possible starting points.
 *
 * Optimal Approach (Dynamic Programming / Suffix Sum Modification):
 * The problem asks for the maximum sum of elements separated by a fixed distance `k` starting from any index.
 * The paths are:
 * - Start at `i`: `energy[i] + energy[i+k] + energy[i+2k] + ...`
 *
 * This problem has optimal substructure, making it suitable for Dynamic Programming (DP).
 * Let `dp[i]` be the maximum energy gained by starting a path at magician `i`.
 *
 * The recurrence relation is:
 * 1. Base Case: If `i + k` is out of bounds (i.e., `i + k >= n`), then the path ends at `i`.
 * `dp[i] = energy[i]`
 * 2. Recursive Step: If `i + k` is within bounds, the maximum energy starting at `i` is the energy from `i` plus the maximum energy from the next valid starting point, `i + k`.
 * `dp[i] = energy[i] + dp[i + k]`
 *
 * We can compute the `dp` array efficiently by iterating backward from the end of the array.
 * - The last `k` elements (`n-k` to `n-1`) are the base cases, where the path ends immediately. For these, `dp[i] = energy[i]`.
 * - For indices `i` from `n - k - 1` down to `0`, we apply the recursive step: `dp[i] = energy[i] + dp[i + k]`.
 *
 * By utilizing the existing `energy` array (or a copy of it, as done in the provided code) to store the DP values, we perform the calculation in-place, which is essentially calculating a modified suffix sum for each of the `k` possible starting residues modulo `k`.
 *
 * After computing all `dp[i]` values, the answer is the maximum value in the entire `dp` array.
 *
 * Time Complexity: O(n). We iterate backward through the array once to compute all DP values.
 * Space Complexity: O(n) due to the use of a copy of the `energy` array (`dp`). If the solution modified the input `energy` array directly, the space complexity would be O(1) auxiliary space (O(n) total, as the input size dominates). The provided solution uses O(n) space for the cloned array.
 */
// Optimal Solution in Java-
import java.util.*;

class Solution {
  public int maximumEnergy(int[] energy, int k)
   {
    int[] dp = energy.clone();
    for (int i = energy.length - 1 - k; i >= 0; --i)
      dp[i] += dp[i + k];
      
    return Arrays.stream(dp).max().getAsInt();
  }
}