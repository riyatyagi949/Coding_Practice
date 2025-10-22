/**
 * Problem Statement: Maximum Frequency of an Element After Performing Operations II
 * --------------------------------------------------------------------------------
 * You are given an integer array 'nums' and two integers 'k' and 'numOperations'.
 * You must perform an operation 'numOperations' times on 'nums', where in each operation:
 * 1. Select a unique index 'i' not previously selected.
 * 2. Add an integer 'x' in the range [-k, k] to nums[i].
 * Return the maximum possible frequency of any element in 'nums' after performing the operations.
 *
 * * Key Transformation: Find the largest subset of 'nums' that can be made equal
 * * to a single target value 'T', subject to two constraints:
 * * 1. |nums[i] - T| <= k for all chosen elements nums[i].
 * * 2. The number of non-T elements in the subset (which require an operation) must be <= numOperations.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 * 0 <= numOperations <= nums.length
 */
/**
     * Optimal Solution: Sliding Window with Prefix Sums (O(N log N))
     * ---------------------------------------------------------------
     * * 1. Sorting: We first sort 'nums'. This allows us to use a **Sliding Window**
     * [left, right] where the **target value 'T' must be one of the original numbers**
     * in the window to minimize the total required change cost. The optimal target T
     * for the window [left, right] is always the largest element, **nums[right]**.
     *
     * * 2. Maximum Change Constraint (|nums[i] - T| <= k):
     * Because the array is sorted, the largest difference in the window is nums[right] - nums[left].
     * If nums[right] - nums[left] > k, it's impossible to change nums[left] to nums[right]
     * in a single operation of size at most k. Thus, the window is invalid.
     * We **must** always maintain the invariant: `nums[right] - nums[left] <= k`.
     *
     * * 3. Operations Constraint (numOperations):
     * If the window [left, right] is valid (i.e., satisfies the k-constraint), the **minimum**
     * number of operations required to make all elements equal to nums[right] is:
     * `Cost = (nums[right] * window_size) - sum(nums[left]...nums[right])`.
     * However, the problem statement simplifies this: we only care about the **number of elements**
     * that require an operation (non-T elements).
     * Since we choose *one* element as the target T (nums[right]), the remaining `window_size - 1`
     * elements must be modified. We must ensure: `window_size - 1 <= numOperations`.
     * This means the max possible frequency is bounded by `numOperations + 1`.
     *
     * * *Algorithm Steps (Sliding Window):*
     * a. Sort `nums`.
     * b. Initialize `left = 0`, `currentSum = 0L`, and `maxFreq = 0`.
     * c. Iterate `right` from 0 to N-1:
     * - Add `nums[right]` to `currentSum`.
     * - The **Total Cost** to make all elements in `[left, right]` equal to `nums[right]` is:
     * `cost = (long)nums[right] * (right - left + 1) - currentSum`.
     * - Check **Constraint 1 (k-constraint)**: `nums[right] - nums[left] <= k`.
     * - Check **Constraint 2 (numOperations)**: The number of elements requiring a change
     * is `right - left`. This must be less than or equal to `numOperations`.
     *
     * d. **Shrinking/Maintenance:** The core constraint that determines the window size is:
     * The number of elements *needing to be changed* (i.e., elements whose original value
     * is not nums[right]) **AND** which are $\le$ nums[right] - k must be handled by the
     * $numOperations$ limit.
     * *The trick is to use the strongest constraint.*
     *
     * The simplest check is to ensure **both** constraints are maintained:
     * * Strongest K-Constraint: `nums[right] - nums[left] <= k`. (Handled by the first while loop).
     * * Operations Constraint: `(right - left) <= numOperations` $\implies$ `window_size <= numOperations + 1`.
     *
     * However, combining the two constraints yields the actual condition for this problem:
     * `while (nums[right] - nums[left] > k || (right - left) > numOperations)`
     * The problem statement implies that *if* $nums[right] - nums[left] \le k$, then the operation count
     * is the only remaining factor.
     *
     * *The correct, minimal check:*
     * We only need to ensure $nums[right] - nums[left] \le k$.
     * If this holds, then $nums[right] - nums[i] \le k$ for all $i \in [left, right]$.
     * The number of indices to change is $(right - left)$ and this must be $\le numOperations$.
     * We simplify by saying we can always shrink the window if it's too big, ensuring we never exceed the
     * `numOperations + 1` bound.
     *
     * **Final maintenance logic:**
     * Shrink the window if the $k$-constraint is violated OR if the operation count is violated.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N log N), where N is the length of 'nums'.
 * - The time complexity is dominated by the initial sorting of the 'nums' array, which takes O(N log N).
 * - The subsequent sliding window (two-pointer) iteration takes O(N) time, because both 'left' and 'right'
 * pointers only move forward, visiting each index at most once.
 * - Overall complexity: O(N log N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(N), for storing the sorted long array 'longNums' (or O(1) auxiliary space if sorting is done in-place).
 * - Since the input array has been copied to a long array, the space complexity is O(N).
 */ 
// Optimal Solution in Java -
import java.util.*;