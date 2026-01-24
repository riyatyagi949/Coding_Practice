/**
 * PROBLEM STATEMENT: 1877. Minimize Maximum Pair Sum in Array
 * --------------------------------------------------------------------------------
 * The pair sum of a pair (a, b) is equal to a + b. The maximum pair sum is the 
 * largest pair sum in a list of pairs.
 * * You are given an array 'nums' of even length n. You need to pair up the 
 * elements of 'nums' into n/2 pairs such that:
 * 1. Each element of nums is in exactly one pair.
 * 2. The maximum pair sum is minimized.
 * * Return the minimized maximum pair sum after optimally pairing up the elements.
 * * Example 1:
 * Input: nums = [3,5,2,3]
 * Output: 7
 * Explanation: Pairs (3,3) and (5,2). Max sum = max(6, 7) = 7.
 * * Example 2:
 * Input: nums = [3,5,4,2,4,6]
 * Output: 8
 * Explanation: Pairs (3,5), (4,4), and (6,2). Max sum = max(8, 8, 8) = 8.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: GREEDY TWO-POINTER APPROACH
 * --------------------------------------------------------------------------------
 * To minimize the maximum sum, we should pair the smallest available number with 
 * the largest available number. This balances the sums across all pairs.
 * * Why this works:
 * If we pair the largest number with anything other than the smallest number, 
 * that specific pair sum will likely be larger than necessary, potentially 
 * increasing the global maximum. By "neutralizing" the largest values with the 
 * smallest ones, we keep the range of pair sums as tight as possible.
 * * Algorithm:
 * 1. Sort the array 'nums'.
 * 2. Use two pointers: 'i' starting at index 0 and 'j' starting at index n-1.
 * 3. While i < j:
 * a. Calculate the current pair sum: nums[i] + nums[j].
 * b. Update the global 'maxSum' if the current pair sum is larger.
 * c. Move 'i' forward and 'j' backward.
 * 4. Return the 'maxSum'.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N)
 * - Sorting the array takes O(N log N) time.
 * - The two-pointer traversal takes O(N) time.
 * - Total time: O(N log N).
 * * Space Complexity: O(log N) or O(1)
 * - Space complexity depends on the implementation of the sorting algorithm 
 * (typically O(log N) stack space for Dual-Pivot Quicksort in Java).
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-

import java.util.Arrays;

class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        int maxSum = 0;

        while (i < j) {
            maxSum = Math.max(maxSum, nums[i] + nums[j]);
            i++;
            j--;
        }
         return maxSum;
    }
}

