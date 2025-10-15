/**
 * Problem Statement: Adjacent Increasing Subarrays Detection II
 * -------------------------------------------------------------
 * Given an array nums of n integers, find the maximum value of k 
 * for which there exist two adjacent subarrays of length k each, 
 * such that both subarrays are strictly increasing.
 * * Specifically, we need to find the maximum k such that there is 
 * an index 'a' where:
 * 1. Subarray nums[a..a + k - 1] is strictly increasing.
 * 2. Subarray nums[a + k..a + 2*k - 1] is strictly increasing.
 * 3. Both subarrays have length k.
 * * Example:
 * Input: nums = [2, 5, 7, 8, 9, 2, 3, 4, 3, 1]
 * Output: 3
 * Explanation: k=3 is valid with a=2.
 * Subarray 1 (start=2, length=3): [7, 8, 9] (Strictly Increasing)
 * Subarray 2 (start=5, length=3): [2, 3, 4] (Strictly Increasing)
 * * Constraints:
 * 2 <= nums.length <= 2 * 10^5
 */
/**
     * Optimal Solution: Dynamic Programming (Pre-calculation)
     * --------------------------------------------------------
     * The problem requires finding the maximum k such that two adjacent 
     * subarrays of length k are strictly increasing. The split point 
     * between the two subarrays is at index `i` (the first subarray 
     * ends at `i-1` and the second starts at `i`).
     * * 1. **Define DP Arrays:**
     * - `left[i]`: Maximum length of a strictly increasing subarray 
     * ending at index `i`.
     * - `right[i]`: Maximum length of a strictly increasing subarray 
     * starting at index `i`.
     * * 2. **Calculate `left` array (Forward Pass):**
     * - `left[0] = 1`
     * - For `i > 0`:
     * - If `nums[i] > nums[i - 1]`, the increasing sequence is extended: 
     * `left[i] = left[i - 1] + 1`.
     * - Otherwise, the sequence is broken, it starts over: 
     * `left[i] = 1`.
     * * 3. **Calculate `right` array (Backward Pass):**
     * - `right[n - 1] = 1`
     * - For `i < n - 1`:
     * - If `nums[i] < nums[i + 1]`, the increasing sequence is extended: 
     * `right[i] = right[i + 1] + 1`.
     * - Otherwise, the sequence is broken, it starts over: 
     * `right[i] = 1`.
     * * 4. **Find Maximum k:**
     * - The two adjacent subarrays meet at index `i`. 
     * - First subarray (length `k1`) ends at `i - 1`. The max possible `k1` is `left[i - 1]`.
     * - Second subarray (length `k2`) starts at `i`. The max possible `k2` is `right[i]`.
     * - The condition for the split at index `i` is: `k = k1 = k2`.
     * - The maximum valid length `k` for this split is `min(left[i - 1], right[i])`.
     * - We iterate through all possible split points `i` from 1 to `n - 1` and find the 
     * maximum of `min(left[i - 1], right[i])`.
     * * - The loop runs from `i = 1` to `n - 1`.
     * `max_k = max(max_k, min(left[i - 1], right[i]))`.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of the array `nums`.
 * - Calculating the `left` array takes O(N) time (single forward pass).
 * - Calculating the `right` array takes O(N) time (single backward pass).
 * - Finding the maximum `k` takes O(N) time (single pass over the split points).
 * - Total time complexity is O(N) + O(N) + O(N) = O(N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the length of the array `nums`.
 * - We use two auxiliary arrays, `left` and `right`, each of size N, 
 * to store the pre-calculated maximum lengths.
 * - This gives a space complexity of O(N) + O(N) = O(N).
 */
// Optimal Solution in Java-
import java.util.*;

class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int[] left = new int[n];
        int[] right = new int[n];
        
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1))
                left[i] = left[i - 1] + 1;
            else
                left[i] = 1;
        }
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums.get(i) < nums.get(i + 1))
                right[i] = right[i + 1] + 1;
            else
                right[i] = 1;
        }
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            ans = Math.max(ans, Math.min(left[i], right[i + 1]));
        }
        return ans;
    }
}