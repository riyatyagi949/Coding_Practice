/**
 * Problem Statement: Minimum Number of Increments on Subarrays to Form a Target Array
 * -----------------------------------------------------------------------------------
 * You are given an integer array 'target'. You start with an array 'initial' of the 
 * same size, with all elements initially zeros.
 * In one operation, you can choose any subarray from 'initial' and increment each 
 * value by one.
 * Return the minimum number of operations required to transform 'initial' into 'target'.
 *
 * Example 1: target = [1,2,3,2,1]
 * Output: 3
 * Example 2: target = [3,1,5,4,2]
 * Output: 7
 *
 * Constraints:
 * 1 <= target.length <= 10^5
 * 1 <= target[i] <= 10^5
 *//**
     * Optimal Solution: Greedy Approach (Difference Analysis)
     * --------------------------------------------------------
     * The minimum number of operations is achieved by realizing that every time a 
     * value *increases* from the previous element, a new set of operations *must* be 
     * started (or extended) to cover this new higher value.
     *
     * Consider two adjacent elements, target[i-1] and target[i].
     *
     * 1. If target[i] <= target[i-1]:
     * The operations that formed target[i-1] are sufficient to also cover target[i] 
     * up to target[i]'s value. No *new* operations are needed to reach target[i]'s 
     * height relative to target[i-1].
     *
     * 2. If target[i] > target[i-1]:
     * target[i] is 'target[i] - target[i-1]' units taller than target[i-1].
     * These 'target[i] - target[i-1]' extra units *must* come from operations that 
     * begin at index 'i' (or earlier) and extend *at least* to index 'i'.
     * Therefore, we must account for exactly **target[i] - target[i-1]** new operations 
     * that cover the difference in height.
     *
     * * Formalizing the Greedy Choice:
     * The total minimum operations is:
     * `target[0]` (The operations needed for the first element)
     * `+ Sum of all positive differences (target[i] - target[i-1]) for i > 0`.
     *
     * This works because any operation that contributes to `target[i-1]` and also extends 
     * to `target[i]` is accounted for by the previous count. We only need to add operations 
     * for the *incremental* height difference.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of the 'target' array.
 * - The solution involves a single pass (linear scan) over the array from index 1 to N-1.
 * - All calculations inside the loop are O(1).
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) auxiliary space.
 * - The algorithm uses only a single integer variable ('operations') to store the result,
 * regardless of the input size.
 */
// Optimal Solution in Java -
class Solution {
    public int minNumberOperations(int[] target) {
        int operations = target[0];

        for (int i = 1; i < target.length; i++)
         {
            if (target[i] > target[i - 1])
             {
                operations += target[i] - target[i - 1];
            }
        }
        return operations;
    }
}