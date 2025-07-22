/*
Problem Statement:
You are given an integer array arr[]. Your task is to find the smallest positive number missing from the array.
Note: Positive number starts from 1. The array can have negative integers too.

Examples:
Input: arr[] = [2, -3, 4, 1, 1, 7]
Output: 3
Explanation: Smallest positive missing number is 3.

Input: arr[] = [5, 3, 2, 5, 1]
Output: 4
Explanation: Smallest positive missing number is 4.

Input: arr[] = [-8, 0, -1, -4, -3]
Output: 1
Explanation: Smallest positive missing number is 1.

Constraints:
1 <= arr.size() <= 10^5
-10^6 <= arr[i] <= 10^6
*/

/*
Approach:
The problem asks for the smallest positive missing number. This means we are looking for the smallest integer >= 1 that is not present in the array.

We can solve this problem using an in-place modification of the array. The idea is to use the array itself as a hash map.
We want to place each positive number `x` at index `x-1`. For example, if we find `1`, we try to place it at index `0`. If we find `2`, we try to place it at index `1`, and so on.

The algorithm proceeds as follows:
1. Iterate through the array from `i = 0` to `n-1`.
2. For each element `arr[i]`:
   a. Check if `arr[i]` is a positive number, and within the valid range (i.e., `1 <= arr[i] <= n`).
   b. Also, check if `arr[i]` is not already at its correct position (i.e., `arr[i] != arr[arr[i] - 1]`).
   c. If both conditions are true, swap `arr[i]` with `arr[arr[i] - 1]`. This ensures that `arr[i]` moves to its correct position. After the swap, we do not increment `i` because the new `arr[i]` (the element that was swapped into the current position) might also need to be placed correctly. So, we re-evaluate the element at the current `i`.
   d. If the conditions are not met (e.g., `arr[i]` is negative, zero, or greater than `n`, or already in place), then just move to the next element by incrementing `i`.

3. After the first pass, the array will be arranged such that if a number `k` is present and `1 <= k <= n`, it will be at `arr[k-1]`.
4. Iterate through the array again from `i = 0` to `n-1`.
5. The first index `i` for which `arr[i]` is not equal to `i + 1` indicates that `i + 1` is the smallest positive missing number.
6. If all numbers from `1` to `n` are present in their correct positions (i.e., `arr[i] == i + 1` for all `i`), then the smallest positive missing number is `n + 1`.

Example Walkthrough: arr = [2, -3, 4, 1, 1, 7], n = 6

Initial: [2, -3, 4, 1, 1, 7]

i = 0, arr[0] = 2.
   1 <= 2 <= 6 (true)
   arr[0] (2) != arr[2-1] (arr[1] which is -3) (true)
   Swap arr[0] and arr[1]: [ -3, 2, 4, 1, 1, 7 ]
   (i remains 0)

i = 0, arr[0] = -3.
   -3 is not positive or not in range [1,6].
   (i increments to 1)

i = 1, arr[1] = 2.
   1 <= 2 <= 6 (true)
   arr[1] (2) != arr[2-1] (arr[1] which is 2) (false, already in place relative to itself at this step, but wait, arr[arr[i]-1] is the value at the *target* index. So arr[1] should be 2. It is. But that's because we swapped. This is the crucial part: if arr[i] is already at its correct target index, we move on.)
   Let's refine the condition: `arr[i] > 0 && arr[i] <= n && arr[i] != i + 1` AND `arr[arr[i]-1] != arr[i]`. The second part `arr[arr[i]-1] != arr[i]` handles duplicates and already correctly placed values. If `arr[i]` is `k`, and `arr[k-1]` is also `k`, then `k` is already in its correct spot or a duplicate.
   Let's stick to the condition `arr[i] > 0 && arr[i] <= n && arr[i] != arr[arr[i]-1]`.
   arr[1] = 2.
   1 <= 2 <= 6 (true)
   arr[1] (2) != arr[2-1] (arr[1] which is 2) (false) -> This means arr[1] is already `2`. No swap needed for `2`.
   (i increments to 2)

i = 2, arr[2] = 4.
   1 <= 4 <= 6 (true)
   arr[2] (4) != arr[4-1] (arr[3] which is 1) (true)
   Swap arr[2] and arr[3]: [ -3, 2, 1, 4, 1, 7 ]
   (i remains 2)

i = 2, arr[2] = 1.
   1 <= 1 <= 6 (true)
   arr[2] (1) != arr[1-1] (arr[0] which is -3) (true)
   Swap arr[2] and arr[0]: [ 1, 2, -3, 4, 1, 7 ]
   (i remains 2)

i = 2, arr[2] = -3.
   -3 is not positive or not in range [1,6].
   (i increments to 3)

i = 3, arr[3] = 4.
   1 <= 4 <= 6 (true)
   arr[3] (4) != arr[4-1] (arr[3] which is 4) (false) -> 4 is in its correct spot (relative to index 3)
   (i increments to 4)

i = 4, arr[4] = 1.
   1 <= 1 <= 6 (true)
   arr[4] (1) != arr[1-1] (arr[0] which is 1) (false) -> 1 is already in its correct spot (at index 0). This is a duplicate. No swap.
   (i increments to 5)

i = 5, arr[5] = 7.
   7 is not <= 6.
   (i increments to 6)

End of first pass. Array: [1, 2, -3, 4, 1, 7]

Second pass to find missing number:
i = 0, arr[0] = 1. `arr[0] == 0 + 1` (true).
i = 1, arr[1] = 2. `arr[1] == 1 + 1` (true).
i = 2, arr[2] = -3. `arr[2] != 2 + 1` (true, -3 != 3).
   Smallest positive missing number is `i + 1 = 2 + 1 = 3`.

Return 3.

This approach works because we are placing positive numbers `k` (where `1 <= k <= n`) at their correct `k-1` index. Any number outside this range (negative, zero, or greater than `n`) cannot be a candidate for the smallest missing positive number, nor can they be used to mark presence within the `1..n` range. We effectively use the indices `0` to `n-1` to represent the presence of numbers `1` to `n`.

Time Complexity:
The first loop runs up to `n` times. Inside the loop, the `while` condition (or inner `if` with `continue` logic) ensures that each element is swapped at most a constant number of times to reach its correct position. An element `arr[i]` is only processed multiple times if it is swapped with another element. Each swap places at least one element closer to its correct position. The total number of swaps is at most `n`. Therefore, the first pass runs in O(N) time.
The second loop runs `n` times.
Overall time complexity: O(N).

Space Complexity:
We are performing in-place modifications to the input array. No additional data structures are used that grow with the input size.
Overall space complexity: O(1).
*/

import java.util.HashSet;

class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int left = 0, right = 0;
        int n = nums.length;
        int maxSum = 0, currentSum = 0;
        HashSet<Integer> set = new HashSet<>();

        while (right < n) {
            if (!set.contains(nums[right])) {
                set.add(nums[right]);
                currentSum += nums[right];
                maxSum = Math.max(maxSum, currentSum);
                right++;
            }
             else {
                set.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }
        }
        return maxSum;
    }
}
