/**
 * Problem Statement: Smallest Missing Non-negative Integer After Operations (Maximum MEX)
 * --------------------------------------------------------------------------------------
 * You are given a 0-indexed integer array 'nums' and an integer 'value'.
 * In one operation, you can add or subtract 'value' from any element of 'nums' any number of times.
 * The MEX (Minimum Excluded) of an array is the smallest missing non-negative integer.
 * The task is to return the maximum possible MEX of 'nums' after applying the mentioned operation 
 * any number of times.
 * * Example:
 * Input: nums = [1,-10,7,13,6,8], value = 5
 * Output: 4 
 * (The array can be transformed to [1, 0, 2, 3, 6, 8], which has MEX 4).
 */
/**
     * Optimal Solution: Using Modulo and Frequency Counting (Greedy Approach)
     * ----------------------------------------------------------------------
     * * Key Insight:
     * Applying the operation (add or subtract 'value') to a number 'num' changes 'num' 
     * to 'num + k * value' for any integer k. This means that after any number of operations, 
     * the new number will be congruent to the original number modulo 'value'.
     * In other words, (new_num) % value == (num) % value.
     * * Since we want to achieve the smallest non-negative integers 0, 1, 2, ..., (MEX - 1), 
     * we must check if we can form these numbers.
     * * A non-negative integer 'm' (where 0 <= m < MEX) can be formed from a number 'num' 
     * if and only if:
     * m % value == num % value
     * * The strategy is greedy: check if we can form 0, then 1, then 2, and so on, until we 
     * find the first integer 'mex' that cannot be formed.
     * * Algorithm:
     * 1. **Count Remainders:** Calculate the frequency of `(num % value + value) % value` 
     * for every `num` in `nums`. We use `(num % value + value) % value` to ensure 
     * the remainder is always in the range [0, value - 1], even for negative numbers.
     * 2. **Greedy Check:** Start checking for potential MEX values from `mex = 0`.
     * 3. **Target Remainder:** For the target number `mex`, its required remainder is 
     * `rem = mex % value`.
     * 4. **Check Availability:** If `count[rem]` (the count of numbers that can be transformed 
     * to `mex`) is greater than 0, it means we have a number in `nums` that can be 
     * transformed to `mex`. We "use" one of these numbers by decrementing `count[rem]` 
     * and move to the next target: `mex++`.
     * 5. **Failure Condition:** If `count[rem]` is 0, we cannot form the number `mex`. 
     * This `mex` is the smallest missing non-negative integer, so we return it.
     * 6. The loop continues indefinitely in terms of `mex`, but practically terminates when 
     * a required remainder count hits zero.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N + M), where N is the length of 'nums' and M is the maximum possible MEX.
 * * Step 1 (Counting Remainders): O(N) to iterate through all elements in 'nums'.
 * * Step 2 (Greedy Check): The loop for 'mex' runs until MEX is found. Since the total 
 * count of all remainders in the map is N, the loop can run at most N + 'value' times 
 * before a remainder count hits zero (it runs 'mex' times, and 'mex' can be at most N + 'value' 
 * in a very specific worst-case scenario, but practically it's bounded by N, as each successful 
 * step consumes one element). A tighter bound is O(N + max(mex)). Since the max MEX is 
 * at most N + 'value', and the map operations are O(1) average time, the complexity is 
 * dominated by O(N).
 * * The overall complexity is O(N) in practice, as the number of successful MEX increments
 * cannot exceed N (the number of available elements).
 * * Space Complexity Analysis:
 * --------------------------
 * O(V), where V is the value of 'value'.
 * * The HashMap 'count' stores the frequencies of remainders. Since the remainder is 
 * always in the range [0, value - 1], the map will have at most 'value' entries.
 */
// Optimal Solution in Java -
import java.util.*;

class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        Map<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            int rem = ((num % value) + value) % value;
            count.put(rem, count.getOrDefault(rem, 0) + 1);
        }

        int mex = 0;
        while (true)
         {
            int rem = mex % value;
            if (count.getOrDefault(rem, 0) > 0)
             {
                count.put(rem, count.get(rem) - 1);
                mex++;
            } 
            else {
                return mex;
            }
        }
    }
}