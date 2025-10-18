/**
 * Problem Statement: Maximum Number of Distinct Elements After Operations
 * ----------------------------------------------------------------------
 * You are given an integer array 'nums' and an integer 'k'.
 * You are allowed to perform the following operation on each element of the array at most once:
 * Add an integer 'x' in the range [-k, k] to the element (nums[i] = nums[i] + x).
 * The task is to return the maximum possible number of distinct elements in 'nums' after 
 * performing the operations.
 *
 * Example:
 * Input: nums = [1,2,2,3,3,4], k = 2
 * Output: 6
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= 10^9
 */
/**
     * Optimal Solution: Greedy Strategy with Sorting
     * ----------------------------------------------
     * The goal is to maximize the number of distinct elements, which means we want to assign 
     * a unique target value 't' to each element 'x' in the original array, such that 
     * |t - x| <= k (i.e., t is in the range [x - k, x + k]).
     * * * The Greedy Principle:
     * To leave maximum space for subsequent elements to find a unique target, we should always 
     * choose the SMALLEST possible valid target value for the current element.
     * * * Algorithm Steps:
     * 1. Sort the input array 'nums' in non-decreasing order. This ensures that when we process 
     * an element 'x_i', we are minimizing the constraint on future elements 'x_j' (where j > i).
     * 2. Initialize 'lastUsedTarget' to a very small number (e.g., Long.MIN_VALUE or a value 
     * much less than the smallest possible element, like -k-1) to ensure the first element is placed.
     * 3. Iterate through the sorted array 'nums':
     * a. Calculate the earliest possible distinct target 't_candidate' for the current element 'x'.
     * 't_candidate' must satisfy two conditions:
     * i.  t_candidate > lastUsedTarget (to ensure distinctness)
     * ii. t_candidate >= x - k (to ensure validity)
     * Thus, t_candidate = max(lastUsedTarget + 1, x - k).
     * b. Check Feasibility: If t_candidate <= x + k, it means 'x' can reach a unique 
     * target 't_candidate' that is larger than all previously used targets.
     * c. If feasible, update lastUsedTarget = t_candidate and increment the distinct count.
     * 4. The final distinct count is the maximum possible MEX.
     * * * Note: Since 'nums[i]' and 'k' can be up to 10^9, the target values can exceed 
     * Integer.MAX_VALUE. Therefore, 'lastUsedTarget' must be a 'long'.
     */
   /**
 * Time Complexity Analysis:
 * -------------------------
 * O(N log N), where N is the length of 'nums'.
 * - The time complexity is dominated by the initial sorting of the 'nums' array, which takes O(N log N).
 * - The subsequent greedy iteration through the array takes O(N) time, as each element is processed in O(1).
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) auxiliary space, or O(log N) to O(N) depending on the language's implementation of the sorting algorithm.
 * - The algorithm uses only a few variables ('lastUsedTarget', 'distinctCount', etc.) regardless of the input size, resulting in O(1) auxiliary space complexity.
 */
// Optimal Solution in Java - 
import java.util.*;

class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        long last = Long.MIN_VALUE; 

        for (int num : nums) 
        {
            long left = (long) num - k;
            long right = (long) num + k;
            long candidate = Math.max(last + 1, left);

            if (candidate <= right) {
                count++;
                last = candidate;
            }
        }
        return count;
    }
}