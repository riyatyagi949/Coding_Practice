// 
//  * Problem Statement:
//  * Given a binary array `nums`, you should delete one element from it.
//  * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
//  * Return 0 if there is no such subarray.
//  *
//  * Example 1:
//  * Input: nums = [1,1,0,1]
//  * Output: 3
//  *
//  * Example 2:
//  * Input: nums = [0,1,1,1,0,1,1,0,1]
//  * Output: 5
//  *
//  * Example 3:
//  * Input: nums = [1,1,1]
//  * Output: 2
//  *
//  * Approach:
//  * We can use a sliding window approach to solve this problem efficiently. The goal is to find the longest subarray of 1's after deleting at most one 0. This can be viewed as finding the longest subarray with at most one 0.
//  *
//  * We'll use two pointers, `left` and `right`, to define our sliding window. We'll also maintain a count of zeros within the current window.
//  *
//  * 1. Initialize `left` to 0, `maxLen` to 0, and `zeroCount` to 0.
//  * 2. Iterate with `right` from 0 to `nums.length - 1`.
//  * 3. Inside the loop, if `nums[right]` is 0, increment `zeroCount`.
//  * 4. While `zeroCount` is greater than 1, we need to shrink the window from the left.
//  * - If `nums[left]` is 0, decrement `zeroCount`.
//  * - Increment `left`.
//  * 5. After the `while` loop, the current window `(left, right)` has at most one 0. The length of this subarray is `right - left + 1`. We update `maxLen` with the maximum length found so far.
//  * `maxLen = Math.max(maxLen, right - left + 1)`.
//  * 6. After the loop completes, `maxLen` will hold the length of the longest subarray with at most one zero. Since we must delete exactly one element, we need to subtract 1 from this length. The result is `maxLen - 1`.
//  *
//  * Note on edge cases:
//  * - If the array contains all 1's, the sliding window will cover the entire array, and `maxLen` will be `nums.length`. After deleting one element, the result should be `nums.length - 1`. The `maxLen - 1` logic handles this correctly.
//  * - If the array contains no 1's or only a single 1, `maxLen - 1` might result in a negative or zero value. The problem states we must return 0 if there is no such subarray. A subarray of 1's must be non-empty. After deleting one element, if the longest subarray of 1's is 0, we should return 0. The `maxLen - 1` correctly handles this if the array contains at least one 1. The result will be `0 - 1 = -1` which isn't right, so we need to be careful. Let's revisit.
//  *
//  * The length we want is the total number of 1's in the window. The window length is `right - left + 1`, and this includes at most one 0. The number of 1's is `(right - left + 1) - zeroCount`. Since `zeroCount` is at most 1, this represents the count of 1's. When `zeroCount` is 1, `(right - left + 1) - 1` is the length of the new subarray. When `zeroCount` is 0, we have to delete a 1, so the length is `(right - left + 1) - 1`. So, `right - left` is always the length of the longest subarray of 1's. Wait, the `maxLen` we're tracking is the length of the window with at most one zero. Let's track the length of the `1`s directly.
//  *
//  * Let's refine the approach:
//  *
//  * 1. Initialize `left = 0`, `zeroCount = 0`, and `maxLength = 0`.
//  * 2. Iterate `right` from 0 to `nums.length - 1`.
//  * 3. If `nums[right]` is 0, increment `zeroCount`.
//  * 4. While `zeroCount > 1`:
//  * - If `nums[left]` is 0, decrement `zeroCount`.
//  * - Increment `left`.
//  * 5. `maxLength = Math.max(maxLength, right - left)`. This gives the length of the 1's in the current window, because we're essentially calculating `(right - left + 1) - 1` if a 0 is present, or `(right - left)` if no 0 is present but we still must delete one element.
//  * 6. After the loop, return `maxLength`.
//  *
//  * Example: `nums = [1,1,1]`.
//  * `left=0`, `right=0`: `nums[0]=1`. `maxLength = max(0, 0-0)=0`.
//  * `left=0`, `right=1`: `nums[1]=1`. `maxLength = max(0, 1-0)=1`.
//  * `left=0`, `right=2`: `nums[2]=1`. `maxLength = max(1, 2-0)=2`.
//  *
//  * The final `maxLength` will be 2. This seems correct.
//  *
//  * The first approach `maxLen - 1` is also correct if we handle the case where the entire array consists of 1's. Let's stick with the `right - left` approach, as it's more robust.
//  *
//  * Time Complexity: O(N), where N is the number of elements in `nums`. We iterate through the array once with the `right` pointer, and the `left` pointer also moves forward at most N times.
//  *
//  * Space Complexity: O(1), as we only use a few variables for pointers and counts.
//  *

// Optimal Solution - 
class Solution {
    public int longestSubarray(int[] nums) {
        int left = 0, zeros = 0, max = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeros++;
            while (zeros > 1) {
                if (nums[left] == 0) zeros--;
                left++;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}
