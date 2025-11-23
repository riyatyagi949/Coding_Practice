/**
 * Problem Statement: Greatest Sum Divisible by Three
 * --------------------------------------------------
 * Given an integer array nums, return the maximum possible sum of elements of the 
 * array such that the sum is divisible by three.
 *
 * Constraints:
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 */
/**
     * Optimal Solution: Dynamic Programming (Specialized Modulo 3)
     * -----------------------------------------------------------
     * The final sum S must satisfy S % 3 == 0.
     * When building the sum, we only need to track the largest sum achieved so far 
     * for each possible remainder modulo 3: 0, 1, and 2.
     *
     * Let dp[r] be the maximum sum achievable using a subset of elements such that 
     * the sum's remainder when divided by 3 is 'r'.
     *
     * * Initialization:
     * dp[0] = 0 (Empty set has sum 0, and 0 % 3 = 0)
     * dp[1] = -1 (or Integer.MIN_VALUE, meaning unreachable)
     * dp[2] = -1 (meaning unreachable)
     *
     * * Transition (For each number 'x' in nums):
     * When considering a new number 'x', the new remainders depend on the old 
     * maximum sums:
     * 1. Old sum had remainder 'r_old'.
     * 2. New sum will have remainder 'r_new' = (r_old + x) % 3.
     * 3. The new maximum sum for 'r_new' is: 
     * dp_new[r_new] = max(dp_new[r_new], dp_old[r_old] + x)
     *
     * * Since the transition must use the old dp values and not the newly updated ones
     * * within the same iteration, we must use a temporary array/set for updates.
     *
     * * The size of the DP array is fixed (size 3), making this DP O(N).
     *
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of 'nums'.
 * - The algorithm iterates through the array once (N iterations).
 * - Inside the loop, there is a constant number of operations (3 transitions for the DP state).
 * - Total time complexity: O(N * 3) = O(N).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - The space is dominated by the fixed-size DP array (size 3) and the temporary array (size 3).
 * - This leads to O(3) or O(1) space complexity.
 */
// Optimal Solution in Java -

class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[3]; 

        for (int num : nums)
             {
            int[] temp = dp.clone();  

            for (int r = 0; r < 3; r++) 
                {
                int newSum = temp[r] + num;
                int newRemainder = newSum % 3;
                dp[newRemainder] = Math.max(dp[newRemainder], newSum);
            }
        }
         return dp[0];  
    }
}

