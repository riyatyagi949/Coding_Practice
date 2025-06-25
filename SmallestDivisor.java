// Problem Statement:
// Given an integer array arr[] and an integer k (where k >= arr.length), find the smallest positive integer divisor such that the sum of the ceiling values of each element in arr[] divided by this divisor is less than or equal to k.

// Approach:
// The problem asks for the smallest positive integer divisor that satisfies a given condition. This indicates that we can use binary search on the possible values of the divisor.
// The range for the divisor would be from 1 to the maximum element in the array (inclusive).
// If a divisor `d` satisfies the condition (sum of ceiling divisions <= k), then any divisor smaller than `d` might or might not satisfy the condition, but any divisor larger than `d` will definitely satisfy the condition (or lead to a smaller sum, which also satisfies). This monotonicity allows binary search.

// We define a `check` function (isValid in the code) that takes the array, k, and a `divisor` as input.
// This function calculates the sum of `ceil(arr[i] / divisor)` for all elements in the array.
// The ceiling division `ceil(a/b)` can be calculated using integer arithmetic as `(a + b - 1) / b`.
// If the calculated sum is less than or equal to `k`, the `check` function returns true; otherwise, it returns false.

// The binary search algorithm works as follows:
// 1. Initialize `left` to 1 (the smallest possible divisor) and `right` to the maximum element in `arr` (the largest possible divisor we might need to check).
// 2. Initialize `ans` to `right` (a safe upper bound for the answer).
// 3. While `left <= right`:
//    a. Calculate `mid = left + (right - left) / 2`.
//    b. Call the `isValid` function with `mid` as the divisor.
//    c. If `isValid` returns true, it means `mid` is a possible answer, and we try to find a smaller divisor. So, we store `mid` in `ans` and set `right = mid - 1`.
//    d. If `isValid` returns false, it means `mid` is too small, and we need a larger divisor. So, we set `left = mid + 1`.
// 4. Return `ans`.

// Time Complexity:
// The time complexity is O(N log M), where N is the number of elements in `arr` and M is the maximum value in `arr`.
// The binary search performs `log M` iterations. In each iteration, the `isValid` function iterates through the `arr` array, which takes O(N) time.
// Finding the maximum element initially takes O(N) time.

// Space Complexity:
// The space complexity is O(1) as we only use a few extra variables.

// Optimal Solution:
class Solution {
    int smallestDivisor(int[] arr, int k) {
        int left = 1;
        int right = getMax(arr);
        int ans = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isValid(arr, k, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    private boolean isValid(int[] arr, int k, int divisor) {
        long sum = 0; 
        for (int num : arr) {
            sum += (num + divisor - 1) / divisor; 
        }
        return sum <= k;
    }
    private int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }
}