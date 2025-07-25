/*
Problem Statement:
You are given an integer array nums. You are allowed to delete any number of elements from nums without making it empty. After performing the deletions, select a subarray of nums such that all elements in the subarray are unique. The sum of the elements in the subarray is maximized. Return the maximum sum of such a subarray.

Approach:
This problem can be solved using a sliding window approach. We maintain a window [left, right] where the elements are unique. We use a set or a frequency map to keep track of the elements in the current window.
We expand the window by moving the right pointer. If the element at nums[right] is not in our set, we add it to the set, add it to the current sum, and update the maximum sum.
If the element at nums[right] is already in our set, it means we have a duplicate. To make the window unique again, we need to shrink the window from the left. We do this by removing elements from the left side (moving the left pointer) until the duplicate element is no longer in the window.
We repeat this process until the right pointer reaches the end of the array. The maximum sum found during this process is the answer.

Time Complexity: O(n)
Space Complexity: O(k) where k is the number of unique elements in the array. In the worst case, k can be n.
*/

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int left = 0;
        int currentSum = 0;
        int maxSum = 0;

        for (int right = 0; right < nums.length; right++) {
            while (set.contains(nums[right])) {
                currentSum -= nums[left];
                set.remove(nums[left]);
                left++;
            }
            
            currentSum += nums[right];
            set.add(nums[right]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}