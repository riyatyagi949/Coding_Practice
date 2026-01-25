/**
 * PROBLEM STATEMENT: 1984. Minimum Difference Between Highest and Lowest of K Scores
 * --------------------------------------------------------------------------------
 * You are given a 0-indexed integer array 'nums', where nums[i] represents the 
 * score of the ith student. You are also given an integer k.
 * * Pick the scores of any k students from the array so that the difference between 
 * the highest and the lowest of the k scores is minimized.
 * * Return the minimum possible difference.
 * * Example:
 * Input: nums = [9, 4, 1, 7], k = 2
 * Output: 2
 * Explanation: 
 * - Sorted nums: [1, 4, 7, 9]
 * - Possible k=2 windows: [1,4] (diff 3), [4,7] (diff 3), [7,9] (diff 2).
 * - Minimum difference is 2.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SORTING + SLIDING WINDOW
 * --------------------------------------------------------------------------------
 * 1. Sorting: 
 * To minimize the difference between the maximum and minimum of any k elements, 
 * those k elements should be as close to each other as possible in value. 
 * Sorting the array ensures that elements with the smallest differences are adjacent.
 * * 2. Sliding Window:
 * Once the array is sorted, any k-sized group of students chosen to minimize 
 * the range will be a contiguous subarray.
 * - The minimum element of a window starting at index 'i' will be nums[i].
 * - The maximum element of that same window will be nums[i + k - 1].
 * * 3. Algorithm:
 * - Sort the array 'nums'.
 * - Use a sliding window of size k.
 * - Calculate the difference: nums[i + k - 1] - nums[i].
 * - Iterate through the array and keep track of the minimum difference found.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N)
 * - Sorting the array takes O(N log N).
 * - The sliding window pass takes O(N) since we visit each element once.
 * * Space Complexity: O(1) or O(log N)
 * - O(1) auxiliary space if we ignore the space used by the sorting algorithm.
 * - Standard sorting implementations (like Dual-Pivot Quicksort) use O(log N) space.
 * --------------------------------------------------------------------------------
 */

// Optimal Solution in Java-

import java.util.Arrays;

class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
          if (k == 1) 
          return 0;
      
        Arrays.sort(nums);

        int minDifference = Integer.MAX_VALUE;
        int i = 0;
        int j = k - 1;

        while (j < n) {
            int currentDifference = nums[j] - nums[i];
            if (currentDifference < minDifference)
            {
              minDifference = currentDifference;
            }
            i++;
            j++;
        }
      return minDifference;
    }
}
