/**
 * Problem Statement: Find Minimum Operations to Make All Elements Divisible by Three
 * ----------------------------------------------------------------------------------
 * You are given an integer array 'nums'. In one operation, you can add or subtract 1 
 * from any element of 'nums'.
 * Return the minimum number of operations required to make all elements of 'nums' 
 * divisible by 3.
 *
 * Constraints:
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 *//**
     * Optimal Solution: Greedy Approach based on Modulo 3
     * ---------------------------------------------------
     * The operations on each element are independent. For any number 'num', we want to 
     * find the smallest number of additions/subtractions of 1 required to make it divisible by 3.
     * This minimum number is determined entirely by the remainder r = num % 3.
     * * The possible remainders and the minimum operations required are:
     * 1. Remainder r = 0: `num` is already divisible by 3. **Operations: 0**
     * 2. Remainder r = 1: `num` must be changed to the nearest multiple of 3.
     * - Option A: Subtract 1 (to reach num-1). e.g., 4 -> 3. **Operations: 1**
     * - Option B: Add 2 (to reach num+2). e.g., 4 -> 6. Operations: 2.
     * - Minimum operations is 1.
     * 3. Remainder r = 2: `num` must be changed to the nearest multiple of 3.
     * - Option A: Subtract 2 (to reach num-2). e.g., 5 -> 3. Operations: 2.
     * - Option B: Add 1 (to reach num+1). e.g., 5 -> 6. **Operations: 1**
     * - Minimum operations is 1.
     *
     * In summary, if `num % 3` is non-zero, the minimum operations required is always 1.
     * The overall minimum operations is simply the count of elements that are NOT divisible by 3.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of the array 'nums'.
 * - The algorithm iterates through the array exactly once. 
 * - Each iteration involves a constant number of operations (modulo, comparison, addition).
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - Only a few variables (`totalOperations`, `remainder`, `num`) are used, regardless of the input size.
 */
// Optimal Solution in Java - 

class Solution {
    public int minimumOperations(int[] nums) {
        int ops = 0;
        for(int num : nums)
        {
            int r = num % 3;
            if(r != 0) 
            ops += 1;  
        }
        return ops;
    }
}