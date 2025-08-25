// Problem Statement:
// Given an array arr[] of positive integers and an integer k. You are allowed to perform at most k operations,
// where in each operation, you can increment any one element of the array by 1.
// Determine the maximum possible median of the array that can be achieved after performing at most k such operations.

// Approach:
// The key insight is that to maximize the median, we should increase the elements that are less than or equal to the median.
// This is because increasing elements larger than the median doesn't help increase the median itself.
// The optimal strategy is to make all elements in the median's "neighborhood" equal to the median.
//
// We can use a binary search approach on the answer (the maximum possible median). The possible range for the median is from the smallest element in the sorted array to some large value (e.g., max element + k).
//
// For a given potential median value `x`, we need to check if it's possible to make the median of the array at least `x` using at most `k` operations.
//
// 1. Sort the input array `arr`.
// 2. The binary search will be on the range `[min_element, max_element + k]`.
// 3. For each `mid` value in the binary search, we check if it's a valid median.
// 4. To check if `mid` is a valid median, we can iterate through all possible median positions in the sorted array.
// 5. Let the array size be `n`. The median is at index `n/2` (0-indexed) for an odd length, and it's the average of elements at indices `n/2 - 1` and `n/2` for an even length. To maximize the median, we should target the element at index `n/2`.
// 6. We iterate from the median index `i = n/2` up to the last element of the array `j = n - 1`.
// 7. For each subarray `arr[0...i]`, we try to make `arr[0...i]` all equal to `arr[i]`. The cost would be `arr[i] * (i + 1) - sum(arr[0...i])`. This is incorrect.
//
// A better check function:
// To check if a value `x` can be a median:
// 1. Sort the array.
// 2. The median element will be at index `(n-1)/2` (for 0-indexed array) or `(n)/2` for even length. To maximize the median, we should consider the right side of the median.
// 3. Let's fix the median position at index `i` of the sorted array. The value of the median will be `arr[i]`.
// 4. We can increase all elements from `arr[0]` to `arr[i]` to `arr[i]`. The cost for this is `arr[i] * (i + 1) - prefixSum[i]`. This is not the correct approach as we can only increase elements.
//
// Correct binary search check function:
// 1. Sort the array.
// 2. The median will be at index `n/2` (0-indexed). Let this index be `midIndex`.
// 3. To make the median `arr[midIndex]` a larger value, we need to increase all elements from `arr[0]` to `arr[midIndex]`.
// 4. Let's iterate through each element `arr[i]` from the median to the end of the array. Let's try to make `arr[i]` the new median.
// 5. For each `arr[i]`, we need to increase the elements `arr[0], ..., arr[i]` to `arr[i]`.
// 6. The cost for this is the sum of differences: `(arr[i] - arr[0]) + (arr[i] - arr[1]) + ... + (arr[i] - arr[i])`.
// 7. This can be calculated as `arr[i] * (i + 1) - sum(arr[0]...arr[i])`. We can use a prefix sum array to calculate this efficiently.
// 8. We iterate `i` from `n/2` to `n-1`. For each `i`, we calculate the cost and if `cost <= k`, we update our answer with `arr[i]`.
//
// This approach is a bit slow. Let's reconsider.
//
// A more efficient approach using sliding window:
// 1. Sort the array `arr`.
// 2. We want to find the largest median. The median is the element at `arr[(n-1)/2]` after sorting.
// 3. Let's try to make a subarray `arr[j...i]` have the same value `arr[i]`.
// 4. We use a sliding window. The window will represent the elements we want to increase to the median value.
// 5. The window `[j, i]` represents the elements from `arr[j]` to `arr[i]`. We want to make these elements equal to `arr[i]`.
// 6. The cost is `(i - j + 1) * arr[i] - (prefixSum[i] - prefixSum[j-1])`.
// 7. We expand the window from the right (`i++`) and shrink from the left (`j++`) if the cost exceeds `k`.
// 8. The median of a subarray `arr[j...i]` is at index `j + (i - j)/2`.
// 9. We need the median of the *entire* array to be at least `arr[i]`. This means the new median index is `(n-1)/2`.
// 10. A better sliding window approach:
//      a. Sort the array.
//      b. The median is at `(n-1)/2`. We need to increase elements from `0` to `(n-1)/2` to be at least `arr[(n-1)/2]`.
//      c. Let's iterate through the possible medians from `(n-1)/2` to `n-1`.
//      d. Let `i` be the index of the potential median.
//      e. We need to increase elements `arr[0], ..., arr[i]` to `arr[i]`.
//      f. The cost is `(i + 1) * arr[i] - prefixSum[i]`.
//      g. We can use a sliding window to find the maximum `i` such that `(i - j + 1) * arr[i] - (prefixSum[i] - prefixSum[j-1]) <= k`.
//
// The most efficient and correct approach combines sorting with a sliding window.
// 1. Sort the array `arr`.
// 2. The median of the original array is at index `n/2`. We want to find the maximum possible median.
// 3. To increase the median, we need to increase the elements at and before the median index.
// 4. Let `mid = n/2`. We want to make `arr[0], arr[1], ..., arr[mid]` equal to some value `x >= arr[mid]`.
// 5. To maximize `x`, we can make all elements `arr[0], ..., arr[mid]` equal to `arr[mid]`. The cost is `arr[mid] * (mid + 1) - sum(arr[0]...arr[mid])`.
// 6. If `cost <= k`, we can potentially increase the median further.
//
// Let's use a two-pointer sliding window on the sorted array.
// `left` pointer at 0, `right` pointer at `(n-1)/2`. `current_sum = sum(arr[0...right])`.
// We expand the window to the right `right++`.
// The cost to make `arr[left...right]` all equal to `arr[right]` is `arr[right] * (right - left + 1) - current_sum`.
// If this cost exceeds `k`, we shrink the window from the left `left++`, and update `current_sum`.
// The median of the whole array will be `arr[i]` if we have a window of size `i-j+1` and this window contains the median element.
//
// A more streamlined sliding window:
// 1. Sort the array.
// 2. Initialize `l = 0`, `currentSum = 0`, `ans = 0`.
// 3. Iterate with a right pointer `r` from 0 to `n-1`.
// 4. Add `arr[r]` to `currentSum`.
// 5. The size of the current window is `len = r - l + 1`.
// 6. The cost to make all elements in the window equal to `arr[r]` is `arr[r] * len - currentSum`.
// 7. If `cost > k`, we need to shrink the window from the left.
// 8. While `arr[r] * len - currentSum > k`:
//      `currentSum -= arr[l]`
//      `l++`
//      `len--`
// 9. After the while loop, the current window `[l, r]` is the largest possible valid window.
// 10. If the median of the entire array `(n-1)/2` is within this window, we can update the answer.
// 11. The median element of the entire array is `arr[(n-1)/2]`. The median of the current window `[l, r]` is `arr[l + (r-l)/2]`.
// 12. We can simply track the size of the valid window. The maximum size of a valid window that includes the median and elements to its left will give us the maximum median.
//
// Correct logic with sliding window:
// 1. Sort the array.
// 2. The median is at index `m = n / 2`.
// 3. We want to find the largest value `X` such that we can make the median at least `X` with at most `k` operations.
// 4. We can use a sliding window of size `(n+1)/2` (or `n/2 + 1` for even length) to cover the median and elements to its left.
// 5. The cost is `arr[r] * window_size - sum(window)`.
// 6. We can slide a window of size `(n+1)/2` over the array. For each window, the potential median is the last element of the window. We calculate the cost to make all elements in the window equal to this potential median. If the cost is less than or equal to `k`, this is a possible median value. We want to find the maximum of these.
// 7. Let's use `l` and `r` pointers. `l` is the start of the window, `r` is the end.
// 8. Iterate `r` from `(n-1)/2` to `n-1`.
// 9. The window is `arr[l...r]` where `l` is such that `r - l + 1 = (n+1)/2`.
// 10. `l = r - (n+1)/2 + 1`.
// 11. The cost is `arr[r] * (r-l+1) - (prefixSum[r] - prefixSum[l-1])`.
// 12. We can calculate this for each `r` and find the maximum `arr[r]` for which `cost <= k`.
//
// This is the correct approach. Let's write the code for it.
// We need prefix sums for efficient cost calculation.

// Time Complexity: O(N log N) due to sorting, and O(N) for the sliding window/prefix sum calculation. So O(N log N) overall.
// Space Complexity: O(N) for the prefix sum array.
// An optimized approach avoids prefix sum array, using a running sum inside the sliding window.
// Time Complexity: O(N log N) due to sorting.
// Space Complexity: O(1) if we modify the input array, or O(N) if we create a new sorted array.

// Optimal  Solution - 

import java.util.Arrays;

class Solution {
    static boolean isPossible(int[] arr, int target, int k) {
        int n = arr.length;

        if (n % 2 == 1) {
         for (int i = n / 2; i < n; ++i) {
                if (arr[i] < target)
                    k -= (target - arr[i]);
            }
        }
        else {
           if (arr[n / 2] <= target) {
                k -= (target - arr[n / 2]);
                k -= (target - arr[n / 2 - 1]);
            }
            else {
                k -= (2 * target - (arr[n / 2] + arr[n / 2 - 1]));
            }
            for (int i = n / 2 + 1; i < n; ++i) {
                if (arr[i] < target)
                    k -= (target - arr[i]);
            }
        }
        return k >= 0;
    }
    static int maximizeMedian(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);

        int iniMedian = arr[n / 2];
        if (n % 2 == 0) {
            iniMedian += arr[n / 2 - 1];
            iniMedian /= 2;
        }

        int low = iniMedian, high = iniMedian + k;
        int bestMedian = iniMedian;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (isPossible(arr, mid, k)) {
                bestMedian = mid;
                low = mid + 1;
            } 
            else {
                high = mid - 1;
            }
        }
        return bestMedian;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5};
        int k = 3;
        int result = maximizeMedian(arr, k);
        System.out.println(result);
    }
}