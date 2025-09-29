/**
 * Problem Statement:
 * Given an array `arr` of integers and two integers `a` and `b`, find the maximum possible sum of a contiguous subarray
 * whose length is at least `a` and at most `b`.
 *
 * Constraints:
 * 1 <= arr.size() <= 10^5
 * -10^5 <= arr[i] <= 10^5
 * 1 <= a <= b <= arr.size()
 *
 * Optimal Approach: Sliding Window with Prefix Sums and Deque (Monotonic Queue)
 *
 * This problem is a variation of the Maximum Subarray Sum problem with a length constraint.
 * We are looking for: max( sum(arr[i] to arr[j]) ) such that a <= (j - i + 1) <= b.
 *
 * The sum of a subarray arr[i...j] can be efficiently calculated using **Prefix Sums**:
 * sum(arr[i...j]) = prefixSum[j] - prefixSum[i-1]
 * where prefixSum[k] = arr[0] + arr[1] + ... + arr[k].
 *
 * The length constraint translates to:
 * a <= j - i + 1 <= b
 * i.e., j - b + 1 <= i <= j - a + 1
 *
 * To maximize `prefixSum[j] - prefixSum[i-1]`, we need to find the **minimum** possible value for `prefixSum[i-1]` for a fixed `j`.
 *
 * Let `k = i - 1`. The goal is to find:
 * max_j ( prefixSum[j] - min_k( prefixSum[k] ) )
 *
 * The valid range for `k` (the index before the start of the subarray) is:
 * j - b <= k <= j - a
 *
 * We can iterate `j` from `a-1` (the end of the first valid subarray of length `a`) to `n-1`.
 * For each `j`, we need to find the minimum `prefixSum[k]` where `k` is in the range `[j - b, j - a]`.
 *
 * A **Sliding Window Minimum** is the perfect tool for this, which can be implemented using a **Monotonic Deque** (Double-Ended Queue).
 *
 * Steps:
 * 1. **Calculate Prefix Sums:** Create `prefixSum` array of size `n+1`, where `prefixSum[i]` stores the sum of `arr[0]` to `arr[i-1]`. `prefixSum[0] = 0`.
 * 2. **Initialize:** `maxSum` to the minimum possible long value, and an empty Deque (to store indices `k`).
 * 3. **Iterate `j`:** Loop through the `prefixSum` array from index `1` to `n`.
 * - **Window Expansion (Add to Deque):** For a current `j`, the potential starting index of the subarray is `i-1 = j-a`. This means the index `k = j - a` becomes a candidate for the minimum prefix sum.
 * - If `j - a >= 0`, push the index `j - a` onto the deque, maintaining its monotonic (increasing prefix sums) property by removing larger elements from the back.
 * - **Window Contraction (Remove from Deque):** Remove any indices `k` from the front of the deque that fall outside the valid window range: $k < j - b$.
 * - **Calculate Maximum Sum:** If the deque is not empty, the index at the front (`dq.peekFirst()`) holds the index `k` corresponding to the minimum `prefixSum[k]` in the valid range `[j-b, j-a]`.
 * - `currentSum = prefixSum[j] - prefixSum[dq.peekFirst()]`
 * - Update `maxSum = max(maxSum, currentSum)`
 *
 * The use of a Monotonic Deque ensures that finding the minimum in the sliding window is an O(1) operation.
 *
 * Time Complexity: O(n). We calculate prefix sums in O(n). Each index is added to and removed from the deque at most once, making the loop O(n).
 * Space Complexity: O(n) for the prefix sum array and the deque, which in the worst case can store up to `n` indices.
 */
// Optimal Solution in Java -

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int maxSubarrSum(int[] arr, int a, int b) {
        int n = arr.length;
        long[] prefix = new long[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + arr[i];
        }
        
        long ans = Long.MIN_VALUE;
        Deque<Integer> dq = new ArrayDeque<>();
        
        for (int r = a - 1; r < n; r++) {
            int lMin = r - b + 1;
            int lMax = r - a + 1;
            
            if (lMax >= 0) {
                while (!dq.isEmpty() && prefix[dq.peekLast()] >= prefix[lMax]) {
                    dq.pollLast();
                }
                dq.offerLast(lMax);
            }
            
            while (!dq.isEmpty() && dq.peekFirst() < lMin) {
                dq.pollFirst();
            }
                if (!dq.isEmpty()) {
                ans = Math.max(ans, prefix[r + 1] - prefix[dq.peekFirst()]);
            }
        }
        return (int) ans;
    }
}


