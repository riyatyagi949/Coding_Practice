/**
 * Problem Statement: Minimum Operations to Convert All Elements to Zero
 * ---------------------------------------------------------------------
 * You are given an array 'nums' of non-negative integers.
 * In one operation, you can select a subarray [i, j] and set all occurrences of 
 * the minimum non-negative integer in that subarray to 0.
 * Return the minimum number of operations required to make all elements 0.
 *
 * Constraints: 1 <= n <= 10^5, 0 <= nums[i] <= 10^5
 *//**
     * Optimal Solution: Monotonic Stack (or simply counting increases)
     * ----------------------------------------------------------------
     * This problem is mathematically equivalent to the "Minimum Number of Increments 
     * on Subarrays to Form a Target Array" (LeetCode 1526).
     *
     * * Key Insight:
     * 1. **Operation Effect:** When an operation is performed on a subarray [i, j] 
     * using the minimum value 'min_val', it effectively *reduces* all occurrences 
     * of 'min_val' in that range to 0. This operation must be performed **exactly once**
     * for every distinct non-zero value that is the *first* (globally) minimum 
     * of some subarray required to reduce other values.
     * 2. **Greedy Strategy:** To minimize operations, we must use the largest 
     * possible subarray in each step.
     * 3. **Equivalence:** Consider the transformation of making an array `nums` 
     * entirely zero. This is the same cost as *forming* `nums` from an array of 
     * all zeros using an "increment subarray by 1" operation.
     * 4. **Counting Increases:** The minimum number of operations required to 
     * form an array is equal to the **sum of all positive differences** between 
     * adjacent elements: `sum(max(0, nums[i] - nums[i-1]))`. This is because a new 
     * subarray operation must start whenever the current value `nums[i]` is strictly 
     * greater than the previous value `nums[i-1]`.
     *
     * * The Monotonic Stack Approach (as used in the provided template):
     * The monotonic stack efficiently tracks the "active" minimums that are currently 
     * defining the necessary operations. The provided solution uses a stack to implicitly
     * calculate the same result as the "counting increases" method.
     * - The stack stores numbers such that `stack.peek() <= num`.
     * - When `stack.peek() > num`: An operation covering `stack.peek()` is complete 
     * (its "height" is capped by `num`), so we pop it.
     * - When `stack.isEmpty() || stack.peek() < num`: A new operation (a new "height") 
     * is needed, as `num` is a new maximum relative to the values that came before it. 
     * We increment `ans` and push `num`.
     * - `ans` ultimately counts the number of times a new, higher level (a new operation) 
     * must be initiated.
     *
     * * Simpler implementation (Counting Increases):
     * * int operations = nums[0];
     * * for (int i = 1; i < nums.length; i++) {
     * * if (nums[i] > nums[i-1]) {
     * * operations += (nums[i] - nums[i-1]);
     * * }
     * * }
     * * return operations;
     *
     * The template provided implements the logic using a Monotonic Stack which also works 
     * for this equivalent problem. The stack implementation:
     * - Initializes `stack` with 0 to handle the first element correctly.
     * - `ans` tracks the total number of new operations started.
     */

// Code 

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int minOperations(int[] nums) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (final int num : nums) {
            while (!stack.isEmpty() && stack.peek() > num) {
                stack.pop();
            }
            if (stack.isEmpty() || stack.peek() < num) {
                ans++;
                stack.push(num);
            }
        }
         return ans;
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of 'nums'.
 * - The algorithm iterates through the array once (O(N)).
 * - Each element is pushed onto the stack at most once and popped at most once. 
 * - The overall time complexity for the stack operations is O(N).
 * - Total time complexity: O(N).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the length of 'nums'.
 * - In the worst case (e.g., nums = [1, 2, 3, 4, 5]), all elements are pushed onto 
 * the stack, requiring O(N) space.
 */