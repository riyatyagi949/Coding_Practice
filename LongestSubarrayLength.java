/**
 * Problem Statement:
 * You are given an array of integers arr[]. Your task is to find the length of the longest subarray such that all the elements of the subarray are smaller than or equal to the length of the subarray.
 *
 * Optimal Approach:
 * The key to solving this problem efficiently is to first sort the array. Let's analyze the condition: all elements of a subarray must be less than or equal to its length.
 *
 * Let the sorted array be `sorted_arr`. If we consider a subarray formed by the first `i+1` elements of this sorted array, `sorted_arr[0...i]`, its length is `i+1`.
 * For this subarray to be valid, all its elements must be less than or equal to `i+1`.
 * Since the array is sorted, the largest element in this subarray is `sorted_arr[i]`. Therefore, we only need to check if `sorted_arr[i] <= i+1`.
 * If this condition holds, then every element in the subarray `sorted_arr[0...i]` is also less than or equal to `sorted_arr[i]`, and thus less than or equal to `i+1`.
 *
 * This leads to a simple greedy strategy:
 * 1. Sort the input array `arr` in non-decreasing order.
 * 2. Iterate through the sorted array from index `i = 0` to `n-1`.
 * 3. At each index `i`, check if the element `arr[i]` is less than or equal to `i+1` (the length of the subarray `arr[0...i]`).
 * 4. If the condition `arr[i] <= i+1` is true, it means the subarray from index `0` up to `i` is a valid subarray. We update our potential maximum length to `i+1`.
 * 5. Since we are looking for the *longest* such subarray, the last index `i` that satisfies the condition will give us the maximum possible length.
 *
 * This approach works because sorting allows us to check the condition for all elements in a prefix subarray by just checking the last element. The final `maxLength` will be the answer.
 *
 * Time Complexity: O(n log n), dominated by the sorting step. The subsequent linear scan takes O(n) time.
 * Space Complexity: O(1) for in-place sorting of primitive types.
 */
