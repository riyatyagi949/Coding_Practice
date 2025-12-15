/**
 * Problem Statement: Number of Smooth Descent Periods of a Stock
 * --------------------------------------------------------------
 * Given an integer array 'prices' representing the daily stock price history.
 * A smooth descent period is one or more contiguous days where the price on 
 * each day is exactly 1 less than the price on the preceding day. 
 * The first day is exempted from this rule (meaning single-day periods are valid).
 * Return the total number of smooth descent periods.
 *
 * Example: prices = [3, 2, 1, 4]
 * Output: 7 (Periods: [3], [2], [1], [4], [3, 2], [2, 1], [3, 2, 1])
 *
 * Constraints:
 * 1 <= prices.length <= 10^5
 */
/**
     * Optimal Solution: Single Pass Traversal / Dynamic Programming / Counting Subarrays
     * ----------------------------------------------------------------------------------
     * This problem reduces to counting all possible contiguous subarrays within maximal 
     * smooth descent segments.
     *
     * 1. **Identify Segments:** The total number of smooth descent periods is the sum of 
     * the smooth descent periods found in each maximal contiguous segment.
     * 2. **Maximal Segment:** A maximal segment is a contiguous subarray [i, j] where 
     * `prices[k] == prices[k-1] - 1` for all `k` in `[i+1, j]`, and this condition fails 
     * at both ends (i-1 and j+1).
     * 3. **Counting Subarrays (Combinatorics):** If a maximal smooth descent segment has 
     * length `L`, it contains L * (L + 1) / 2 smooth descent subarrays.
     * - Example: For segment [3, 2, 1] (Length L=3):
     * - Length 1: [3], [2], [1] (3 periods)
     * - Length 2: [3, 2], [2, 1] (2 periods)
     * - Length 3: [3, 2, 1] (1 period)
     * - Total: 3 + 2 + 1 = 6. (Wait, the example says 7? Let's re-read the example: [3, 2, 1, 4] -> [3,2,1] has 6, plus [4] which has 1, plus [2] which has 1, plus [3] which has 1. Ah, the example counts ALL single-day periods in the original array).
     *
     * *Corrected Logic:* The problem asks for the sum of all smooth descent periods across the entire array.
     * * We can use a single-pass loop (Dynamic Programming / Two Pointers) to track the current 
     * * smooth descent length (`currentLength`) and accumulate the result.
     *
     * * Algorithm Steps:
     * 1. Initialize `totalPeriods = 0` (use a long to avoid overflow, as max periods can exceed 2^31).
     * 2. Initialize `currentLength = 1` (since every single day forms a period).
     * 3. Iterate from `i = 1` to `N-1`:
     * a. Check condition: If `prices[i] == prices[i-1] - 1`:
     * The smooth descent continues. Increment `currentLength`.
     * b. Check condition: Else (the descent is broken):
     * The smooth segment ends at `i-1`. The current number `prices[i]` starts a new segment of length 1.
     * Reset `currentLength = 1`.
     * 4. In every iteration, the current price `prices[i]` (or `prices[0]` if i=0) forms `currentLength` 
     * new smooth descent periods ending at index `i`.
     * - If `currentLength = 3` (e.g., [a, b, c]), the periods ending at `c` are [c], [b, c], [a, b, c].
     * - Thus, `totalPeriods += currentLength`.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of the 'prices' array.
 * - The algorithm involves a single pass (linear traversal) through the array.
 * - Each element is processed in O(1) time.
 * * Space Complexity Analysis:
 * --------------------------
 * O(1)
 * - The algorithm uses a fixed amount of extra space (variables like `totalPeriods`, 
 * `currentLength`, and loop indices), regardless of the input size N.
 */
// Code - 
class Solution {
    public long getDescentPeriods(int[] prices) {
        long count = 1;
        long len = 1;

        for (int i = 1; i < prices.length; i++)
         {
            if (prices[i - 1] - prices[i] == 1)
             {
                len++;
            } 
            else {
                len = 1;
            }
            count += len;
        }
        return count;
    }
}
