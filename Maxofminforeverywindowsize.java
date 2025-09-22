/**
 * Problem Statement:
 * You are given an integer array `arr[]`. The task is to find the maximum of minimums for every window size `k` where `1 <= k <= arr.size()`.
 *
 * For each window size `k`, you need to:
 * 1. Consider all contiguous subarrays (windows) of length `k`.
 * 2. Find the minimum element in each of these subarrays.
 * 3. Take the maximum among all these minimums.
 *
 * Return the results as an array, where the element at index `i` represents the answer for window size `i+1`.
 *
 * Optimal Approach:
 * A naive brute-force approach would be to iterate through all possible window sizes `k` and for each `k`, iterate through all windows to find the maximum of minimums. This would be too slow, with a time complexity of O(n^2).
 *
 * A more efficient and optimal approach involves using a monotonic stack. The core idea is to find, for each element `arr[i]`, the largest possible window size for which `arr[i]` is the minimum element.
 *
 * 1.  **Find Next Smaller and Previous Smaller Elements:**
 * - For each element `arr[i]`, we need to find the index of the first element to its left that is smaller than `arr[i]` (let's call it `left[i]`) and the index of the first element to its right that is smaller than `arr[i]` (let's call it `right[i]`).
 * - The subarray `arr[left[i]+1 ... right[i]-1]` is the largest contiguous subarray where `arr[i]` is the minimum element.
 * - The length of this window is `len = right[i] - left[i] - 1`.
 * - We can find both `left` and `right` arrays in O(n) time using a monotonic stack.
 * - To find `right`, we iterate from left to right.
 * - To find `left`, we iterate from right to left.
 *
 * 2.  **Populate the Result Array:**
 * - We create a result array `ans` of size `n` to store the answers for each window size.
 * - We iterate through the original array `arr`. For each element `arr[i]`, we calculate the largest window size `len = right[i] - left[i] - 1` for which it is the minimum.
 * - This means `arr[i]` is a potential candidate for the "max of minimums" for all window sizes up to `len`. Specifically, it can be the answer for window size `len`.
 * - We update `ans[len-1]` with `max(ans[len-1], arr[i])`.
 *
 * 3.  **Propagate Maximums:**
 * - The `ans` array currently holds the maximum of minimums for some window sizes, but not all. For example, if `ans[5]` is 100, it means there exists a window of size 6 whose minimum is 100. A window of size 5 is contained within this larger window, so its minimum must be at least 100 (or greater).
 * - Therefore, the `ans` array should be non-increasing from `k=n` down to `k=1`.
 * - We perform a backward pass on the `ans` array from `n-2` to `0`.
 * - For each index `i`, we update `ans[i] = max(ans[i], ans[i+1])`. This ensures that the property `ans[i] >= ans[i+1]` holds, correctly populating the final result.
 *
 * Time Complexity: O(n)
 * - Two passes with a monotonic stack to find `left` and `right` arrays take O(n).
 * - One pass to populate the `ans` array takes O(n).
 * - One backward pass to propagate the maximums takes O(n).
 * - Total time complexity is dominated by these linear scans, making it O(n).
 *
 * Space Complexity: O(n)
 * - We use auxiliary arrays for `left`, `right`, and `ans`, all of size `n`.
 */
// Optimal Solution in Java - 

import java.util.Arrays;
import java.util.Stack;
import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> maxOfMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];   
        int[] right = new int[n]; 

        Arrays.fill(left, -1);
        Arrays.fill(right, n);

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                left[i] = st.peek();
            }
            st.push(i);
        }

        st.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                right[i] = st.peek();
            }
            st.push(i);
        }

        int[] ans = new int[n + 1];
        Arrays.fill(ans, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            int len = right[i] - left[i] - 1;
            ans[len] = Math.max(ans[len], arr[i]);
        }
        for (int i = n - 1; i >= 1; i--) {
            ans[i] = Math.max(ans[i], ans[i + 1]);
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            res.add(ans[i]);
        }

        return res;
    }
}
