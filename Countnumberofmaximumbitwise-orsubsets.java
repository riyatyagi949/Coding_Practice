/**
 * Problem: 2044. Count Number of Maximum Bitwise-OR Subsets
 * Approach:
 * We need to find the number of non-empty subsets with the maximum possible bitwise OR.
 * First, we can find the maximum possible bitwise OR by taking the OR of all elements in the `nums` array.
 * This is because the bitwise OR operation is monotonic, so the OR of any subset will be less than or equal to the OR of the entire array.
 * Let's call this maximum OR value `maxOr`.
 *
 * Once we have `maxOr`, the problem simplifies to finding the number of subsets whose bitwise OR is equal to `maxOr`.
 * This can be solved using a backtracking or recursive approach. We can iterate through the elements of the array and
 * for each element, we have two choices: either include it in the current subset or not.
 * We'll use a recursive function, say `backtrack(index, currentOr)`, where `index` is the current position in the array
 * and `currentOr` is the bitwise OR of the elements chosen so far.
 *
 * The base case for the recursion is when `index` reaches the end of the array. At this point, if `currentOr` equals `maxOr`,
 * we have found a valid subset, so we increment a counter.
 *
 * In the recursive step, we make two calls:
 * 1. Don't include the current element `nums[index]`: `backtrack(index + 1, currentOr)`.
 * 2. Include the current element `nums[index]`: `backtrack(index + 1, currentOr | nums[index])`.
 *
 * Since we are only interested in non-empty subsets, we need to handle the initial call carefully. A simple way is to
 * initialize the counter to 0, and start the recursion with an empty subset (e.g., `backtrack(0, 0)`).
 * However, this will also count the empty subset if `maxOr` is 0, which is not what we want.
 * A better way is to start the recursion with a specific element included, and then recurse from there.
 *
 * Alternatively, we can use a more direct backtracking approach.
 * We can have a function `countSubsets(index, currentOr)` that returns the count of subsets from `nums[index]` to the end
 * that, when ORed with `currentOr`, result in `maxOr`.
 *
 * Let's refine the backtracking. We can have a global `count` variable.
 * `dfs(index, currentOr)`:
 * - if `index == nums.length`:
 * - if `currentOr == maxOr`:
 * - `count++`
 * - `return`
 * - `dfs(index + 1, currentOr)` // Exclude current element
 * - `dfs(index + 1, currentOr | nums[index])` // Include current element
 *
 * This approach will count all subsets, including the empty one. So, if `maxOr` is the target, we will count the empty subset
 * if `maxOr` is 0. Since `nums[i] >= 1`, `maxOr` will be at least 1, so the empty set will not have `maxOr`.
 * Therefore, this approach works. We just need to subtract 1 from the final count if we want to exclude the empty set,
 * but the problem asks for non-empty subsets. The empty set has an OR of 0.
 * `maxOr` is the OR of all elements in `nums`. Since `nums[i] >= 1`, `maxOr` will also be `>= 1`. So, the empty set's OR (0) will
 * never equal `maxOr`. So, the backtracking above will not count the empty set.
 *
 * Time Complexity: O(2^N) where N is the number of elements in `nums`. For each element, we have two choices.
 * Space Complexity: O(N) for the recursion stack.
 */
class Solution {
    int maxOr;
    int count;
    int[] nums;

    public int countMaxOrSubsets(int[] nums) {
        this.nums = nums;
        maxOr = 0;
        for (int num : nums) {
            maxOr |= num;
        }
        count = 0;
        backtrack(0, 0);
        return count;
    }
    private void backtrack(int index, int currentOr) {
        if (index == nums.length) {
            if (currentOr == maxOr) {
                count++;
            }
            return;
        }
        backtrack(index + 1, currentOr);

        backtrack(index + 1, currentOr | nums[index]);
    }
}