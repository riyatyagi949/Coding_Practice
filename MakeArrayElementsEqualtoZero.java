/**
 * Problem Statement: Make Array Elements Equal to Zero
 * ---------------------------------------------------
 * You are given an integer array 'nums'.
 * Start by selecting a position 'curr' such that nums[curr] == 0, and a movement direction (left or right).
 * The process repeats:
 * - If 'curr' is out of bounds, stop.
 * - If nums[curr] == 0, move in the current direction.
 * - If nums[curr] > 0: decrement nums[curr] by 1, reverse direction, and take a step.
 * A selection (initial 'curr' and direction) is valid if all elements become 0.
 * Return the number of possible valid selections.
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * There is at least one element i where nums[i] == 0.
 *//**
     * Optimal Solution: Analytical Pattern Recognition (Prefix Sum Check)
     * -------------------------------------------------------------------
     * The complex movement pattern simplifies into a key requirement:
     *
     * * Pattern Insight:
     * When the traveler passes a non-zero element nums[i] in a certain direction, say Left-to-Right (L->R),
     * it decrements nums[i] and reverses. This means:
     * 1. To reduce nums[i] by 1, the traveler must pass *through* it once (L->R or R->L).
     * 2. The entire process of reducing nums[i] to 0 requires the traveler to pass through 'i' exactly nums[i] times.
     * 3. **Crucially, the traveler MUST exit the system by moving off one end.**
     *
     * * The Balance Condition (Prefix/Suffix Sum):
     * The total number of L->R traversals must equal the total number of R->L traversals, otherwise
     * the traveler would be trapped in an infinite loop or would exit early without zeroing all numbers.
     *
     * Consider the total number of operations (decrements) needed for all elements *to the left* of the
     * starting point 'curr' (index < curr), and those *to the right* (index > curr).
     *
     * Let L_sum = sum(nums[0]...nums[curr-1]) and R_sum = sum(nums[curr+1]...nums[n-1]).
     *
     * * Case 1: Initial Direction is **LEFT** (curr-1, curr-2, ...)
     * - The process immediately starts tackling the **Left** side.
     * - The Left side must be completely zeroed out (L_sum decrements) *and* the traveler must return to 'curr'
     * to eventually start clearing the Right side.
     * - **Condition:** The total work required on the left (L_sum) must *balance* the total work on the right (R_sum)
     * *plus* the single extra move required to finally exit the array.
     * - Specifically, L_sum must equal R_sum. If L_sum = R_sum, the final move to exit the array (e.g., left out of 0)
     * is guaranteed to happen after all numbers are zeroed.
     * - **The correct observation is simpler:** The total reduction *to the left* must equal the total reduction *to the right*.
     * Since every decrement reverses the direction, the net effect is that the total number of L->R passes must equal the total R->L passes.
     * L_sum and R_sum must be **EQUAL**.
     *
     * * Case 2: Initial Direction is **RIGHT** (curr+1, curr+2, ...)
     * - Symmetrical to Case 1. The total work required on the right (R_sum) must equal the total work on the left (L_sum).
     * - **Condition:** L_sum and R_sum must be **EQUAL**.
     *
     * * Final Condition:
     * A valid selection (curr, direction) is possible **if and only if** the sum of all elements to the left of 'curr' equals the sum of all elements to the right of 'curr'.
     *
     * Sum(nums[0]...nums[curr-1]) == Sum(nums[curr+1]...nums[n-1])
     *
     * The total count is simply twice the number of zero-indices that satisfy this balance condition.
     */
    /**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of 'nums'.
 * - The time complexity is dominated by the single pass required to calculate the total sum
 * and then the subsequent single pass using the prefix sum technique.
 * - Each element is visited and processed in O(1) time.
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) auxiliary space.
 * - We only use a few long and int variables (totalSum, leftSum, validSelections) whose
 * storage is independent of the input size N.
 */
// Optimal Solution in Java - 

class Solution {
    public int countValidSelections(int[] nums) {
        int count = 0, len = nums.length;
        int sum = 0;
        for (int num : nums) 
        sum += num;

        int halfSum = 0;
        for (int i = 0; i < len; i++) {
            halfSum += nums[i];
            if (nums[i] == 0)
             {
                if (2 * halfSum == sum)
                 {
                    count += 2;
                }
                 else if (Math.abs(sum - 2*halfSum) == 1) 
                 {
                    count++;
                } 
            }
        }
        return count;
    }
}
