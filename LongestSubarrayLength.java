/**
 * Problem Statement:
 * You are given an array of integers `arr[]`. Your task is to find the length of the longest subarray such that all the elements of the subarray
 * are smaller than or equal to the length of the subarray.
 *
 * Example:
 * Input: arr[] = [1, 2, 3]
 * Output: 3
 * Explanation: The longest subarray is [1, 2, 3] itself, which has a length of 3. All elements (1, 2, 3) are <= 3.
 *
 * Optimal Approach:
 * The problem requires finding the longest subarray `arr[i...j]` such that for every element `arr[k]` in this subarray, `arr[k] <= (j - i + 1)`.
 * This condition is equivalent to `max(arr[i...j]) <= (j - i + 1)`.
 *
 * A brute-force approach would be to check every possible subarray, which would be inefficient with a time complexity of O(n^2).
 * A more optimal approach is to use a monotonic stack to efficiently find the nearest greater element on both the left and right sides for each element `arr[i]`.
 *
 * For each element `arr[i]`, we can find the longest subarray `arr[l...r]` where `arr[i]` is the maximum element.
 * - `l` is the index of the nearest element to the left of `i` that is greater than `arr[i]` (Previous Greater Element or PGE).
 * - `r` is the index of the nearest element to the right of `i` that is greater than `arr[i]` (Next Greater Element or NGE).
 *
 * The length of this subarray is `r - l - 1`. The key insight is that if the maximum element `arr[i]` in this subarray is less than or equal to the subarray's length (`arr[i] <= r - l - 1`), then all other elements in the subarray, which are smaller than or equal to `arr[i]`, must also be less than or equal to the length.
 *
 * The algorithm proceeds as follows:
 * 1. Find the index of the Next Greater Element (NGE) for each element in the array. This can be done in O(n) time using a monotonic stack.
 * 2. Find the index of the Previous Greater Element (PGE) for each element. This also takes O(n) time using a monotonic stack.
 * 3. Iterate through the array from `i = 0` to `n-1`. For each element `arr[i]`:
 * - The length of the subarray where `arr[i]` is the maximum is `nge[i] - pge[i] - 1`.
 * - Check if `arr[i] <= nge[i] - pge[i] - 1`.
 * - If the condition holds, update the maximum length found so far.
 * 4. Return the maximum length found. If no such subarray exists, the initial value of 0 is returned.
 *
 * Time Complexity: O(n). We iterate through the array three times: once for PGE, once for NGE, and once to check the condition. Each of these loops is linear.
 * Space Complexity: O(n) to store the `nge` and `pge` arrays, as well as the stack.
 */

import java.util.Stack;

class Solution {
    /**
     * Finds the index of the next greater element to the right for each element.
     * @param arr The input array.
     * @param nge An array to store the results.
     * @param n The size of the array.
     */
    static void findnge(int arr[], int nge[], int n) {
        Stack<Integer> st = new Stack<>();
        // Iterate from right to left to find the next greater element.
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements from the stack that are smaller than or equal to the current element.
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            // If the stack is empty, there is no greater element to the right.
            if (st.isEmpty()) {
                nge[i] = n;
            } else {
                // The top of the stack is the index of the next greater element.
                nge[i] = st.peek();
            }
            // Push the current index onto the stack.
            st.push(i);
        }
    }

    /**
     * Finds the index of the previous greater element to the left for each element.
     * @param arr The input array.
     * @param pge An array to store the results.
     * @param n The size of the array.
     */
    static void findpge(int arr[], int pge[], int n) {
        Stack<Integer> st = new Stack<>();
        // Iterate from left to right to find the previous greater element.
        for (int i = 0; i < n; i++) {
            // Pop elements from the stack that are smaller than or equal to the current element.
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop();
            }
            // If the stack is empty, there is no greater element to the left.
            if (st.isEmpty()) {
                pge[i] = -1;
            } else {
                // The top of the stack is the index of the previous greater element.
                pge[i] = st.peek();
            }
            // Push the current index onto the stack.
            st.push(i);
        }
    }

    /**
     * Main method to find the length of the longest subarray with the given condition.
     * @param arr The input array.
     * @return The length of the longest valid subarray.
     */
    public static int longestSubarray(int[] arr) {
        int n = arr.length;
        int nge[] = new int[n];
        int pge[] = new int[n];
        
        // Find the next and previous greater elements for all elements.
        findnge(arr, nge, n);
        findpge(arr, pge, n);
        
        int ans = 0;
        
        // Iterate through the array to check the condition for each element.
        for (int i = 0; i < n; i++) {
            int l = pge[i];
            int r = nge[i];
            
            // Calculate the length of the subarray where arr[i] is the maximum.
            int length = r - l - 1;
            
            // Check if the maximum element is less than or equal to the subarray's length.
            if (arr[i] <= length) {
                // If the condition is met, update the answer with the maximum length found.
                ans = Math.max(ans, length);
            }
        }
        
        return ans;
    }
}