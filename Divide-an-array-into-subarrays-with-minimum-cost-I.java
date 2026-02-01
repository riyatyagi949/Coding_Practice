/**
 * PROBLEM STATEMENT: 3010. Divide an Array Into Subarrays With Minimum Cost I
 * --------------------------------------------------------------------------------
 * You are given an array of integers 'nums' of length n.
 * The cost of an array is defined as the value of its first element.
 * * Task: Divide 'nums' into 3 disjoint contiguous subarrays.
 * Return the minimum possible sum of the costs of these 3 subarrays.
 * * Key Rule:
 * - The first subarray MUST start at index 0. Therefore, nums[0] is always part 
 * of the total cost.
 * - You need to pick two other indices (i and j, where 0 < i < j < n) to be the 
 * start of the second and third subarrays.
 * - The goal is to minimize: nums[0] + nums[i] + nums[j].
 * * Example:
 * Input: nums = [1,2,3,12]
 * Output: 6 (1 + 2 + 3)
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Single Pass for Two Smallest Values
 * --------------------------------------------------------------------------------
 * 1. The first cost is fixed as nums[0].
 * 2. To minimize the total cost, we simply need to find the two smallest values 
 * in the remainder of the array (from index 1 to n-1).
 * 3. We can find these two smallest values (secondMin and thirdMin) in a single 
 * pass through the array.
 * 4. The result is: nums[0] + secondMin + thirdMin.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We iterate through the array starting from index 1 exactly once.
 * Space Complexity: O(1)
 * - We only use a constant amount of extra space for tracking the minimums.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-

class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;
      
        int firstMin = nums[0];
        
        int secondMin = Integer.MAX_VALUE;
        int thirdMin = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            if (nums[i] < secondMin) {
                thirdMin = secondMin;
                secondMin = nums[i];
            } 
            else if (nums[i] < thirdMin) {
                thirdMin = nums[i];
            }
        }
        return firstMin + secondMin + thirdMin;
    }
}
