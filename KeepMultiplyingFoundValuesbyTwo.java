/**
 * Problem Statement: Keep Multiplying Found Values by Two
 * --------------------------------------------------------
 * Given an array of integers 'nums' and an integer 'original'.
 * Repeat the following process:
 * 1. If 'original' is found in 'nums', set 'original = 2 * original'.
 * 2. Otherwise, stop the process.
 * Return the final value of 'original'.
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * 1 <= nums[i], original <= 1000 (Initial constraints for values)
 */
/**
     * Optimal Solution: Using a HashSet for O(1) Lookups
     * ----------------------------------------------------
     * The process involves repeatedly searching for a value ('original') in the array. 
     * Since the search operation is performed many times, using a hash-based data structure 
     * to store the array elements is essential for efficiency.
     *
     * 1. **Preprocessing:** Store all elements of `nums` into a **HashSet** for O(1) average time complexity lookups.
     * 2. **Iteration:** Loop continuously while the current `original` value is present in the HashSet.
     * 3. **Multiplication:** If found, multiply `original` by 2 (`original *= 2`).
     * 4. **Termination:** The loop terminates as soon as `original` is *not* found in the set.
     *
     * This approach minimizes the cost of repeated searches, which is crucial given the iterative nature 
     * of the problem. We don't need to worry about large numbers because, even if the initial `original` 
     * is small (1), the maximum element in `nums` is 1000, meaning the process will stop when `original` 
     * exceeds 1000 (or slightly more, e.g., if the process reaches 512, it looks for 1024).
     */
 // Optimal Solution in Java -
 
 class Solution {
    public int findFinalValue(int[] nums, int original)
     {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        while (numSet.contains(original)) {
            original *= 2;
        }
        return original;
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N + log(M/original)), where N is the length of 'nums', and M is the maximum value in 'nums' (or the 
 * constraint limit, 1000).
 * - O(N): Building the HashSet by iterating through 'nums'.
 * - O(log(M/original)): The 'while' loop runs a maximum of `k` times, where $2^k \cdot \text{original} \approx M$. 
 * Solving for $k$ gives $k \approx \log_2(M / \text{original})$. Since the maximum value M is small (1000), 
 * the number of multiplications is very small (at most 10 iterations: $1 \rightarrow 2 \rightarrow ... \rightarrow 512 \rightarrow 1024$).
 * - Each loop iteration takes O(1) time (average) due to the HashSet lookup.
 * - Overall complexity is dominated by O(N).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the length of 'nums'.
 * - This space is required to store the elements of 'nums' in the HashSet.
 */
