/*
Problem Statement:
2419. Longest Subarray With Maximum Bitwise AND

You are presented with an integer array named `nums`, which contains a certain number of integers. Your objective is to find the maximum possible bitwise AND value from all possible non-empty subarrays of this array. Once you've determined this maximum bitwise AND value (let’s call it `k`), you need to consider only the subarrays that produce this maximum value when performing a bitwise AND on all their elements. Among these subarrays, your goal is to find the length of the longest one.

To sum up, you must:
1. Calculate the maximum bitwise AND value for all possible subarrays.
2. Identify which subarrays yield this maximum value.
3. Determine the maximum length among those subarrays.

A subarray is defined as a contiguous sequence of elements within the original array.

Approach:
The core intuition here is understanding how the bitwise AND operation works. When you perform a bitwise AND between two numbers, the result will always be less than or equal to both numbers. This implies that for a subarray's bitwise AND to be maximized, all elements within that subarray must be equal to the maximum element present in the entire array.

Consider the maximum value `mx` in the `nums` array. If any subarray's bitwise AND is `mx`, then every element in that subarray must individually be `mx`. Why? Because if any element `x` in the subarray was less than `mx`, then `x & mx` would be less than `mx` (or equal if `x` happens to have all bits set that `mx` has, but typically it would be smaller). Consequently, the bitwise AND of the entire subarray would also be less than `mx`. The only way to achieve a bitwise AND of `mx` for a subarray is if all elements in that subarray are exactly `mx`.

Therefore, the problem simplifies to finding the maximum element in the entire array (`mx`) and then finding the **longest contiguous subarray** where all elements are equal to `mx`.

Steps:
1. Find the maximum value (`mx`) in the `nums` array.
2. Initialize `longest_length` to 0 to store the maximum length found so far.
3. Initialize `current_length` to 0 to track the length of the current contiguous sequence of `mx`.
4. Iterate through the `nums` array:
   a. If the current number `num` is equal to `mx`, increment `current_length`.
   b. If the current number `num` is not equal to `mx`, reset `current_length` to 0, as the contiguous sequence of `mx` is broken.
   c. After each step (whether `num == mx` or not), update `longest_length = Math.max(longest_length, current_length)` to keep track of the overall maximum length.
5. Return `longest_length`.

Time Complexity:
The time complexity is O(N), where N is the number of elements in the `nums` array. This is because we perform two passes: one pass to find the maximum element and another pass to iterate through the array to find the longest contiguous sequence of the maximum element. Both passes take linear time.

Space Complexity:
The space complexity is O(1) as we only use a few constant extra variables (`max_value`, `longest_length`, `current_length`).
*/

class Solution {
    public int longestSubarray(int[] nums) {
        int maxValue = 0;
        for (int num : nums) {
            if (num > maxValue) {
                maxValue = num;
            }
        }
        int longestLength = 0;
        int currentLength = 0;

        for (int number : nums) {
            if (number == maxValue) {
                currentLength++;
                longestLength = Math.max(longestLength, currentLength);
            }
             else {
                currentLength = 0;
            }
        }
        return longestLength;
    }
}