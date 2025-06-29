// Problem Statement:
// Given an array arr[] and an integer k, divide the array into k contiguous subarrays such that the maximum sum among these subarrays is minimized. Find this minimum possible maximum sum.

// Approach:
// This problem can be solved using binary search on the answer.
// The minimum possible value for the maximum sum is the maximum element in the array (when k = arr.size()).
// The maximum possible value for the maximum sum is the sum of all elements in the array (when k = 1).
// We can binary search within this range [max_element, sum_of_all_elements].
// For a given `mid` value (which represents a potential maximum sum), we check if it's possible to divide the array into `k` or fewer subarrays such that the sum of each subarray does not exceed `mid`.
// The `check` function iterates through the array, keeping track of the current subarray sum. If the current sum exceeds `mid`, we increment the subarray count and start a new subarray with the current element.
// If the number of subarrays required is less than or equal to `k`, it means `mid` is a possible maximum sum, and we try to find a smaller `mid` (by setting `high = mid - 1`).
// If the number of subarrays required is greater than `k`, it means `mid` is too small, and we need a larger `mid` (by setting `low = mid + 1`).

// Time Complexity: O(N log(Sum - Max)), where N is the length of the array, Sum is the total sum of elements, and Max is the maximum element in the array.
// The binary search performs log(Sum - Max) iterations. In each iteration, the `check` function takes O(N) time.

// Space Complexity: O(1)

// Optimal Solution:
class Solution {
    public int splitArray(int[] arr, int k) {
        int max = 0, sum = 0;
        for (int num : arr) {
            max = Math.max(max, num);
            sum += num;
        }

        int low = max, high = sum;
        int result = sum;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(arr, k, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    private boolean isPossible(int[] arr, int k, int maxSum) {
        int count = 1, currentSum = 0;

        for (int num : arr) {
            if (currentSum + num > maxSum) {
                count++;
                currentSum = num;
                if (count > k) return false;
            } else {
                currentSum += num;
            }
        }

        return true;
    }
};
