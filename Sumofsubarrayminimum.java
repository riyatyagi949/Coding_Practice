// Problem Statement:
// Given an array arr[] of positive integers, find the total sum of the minimum elements of every possible subarrays.
// Note: It is guaranteed that the total sum will fit within a 32-bit unsigned integer.

// Approach:
// This problem can be solved efficiently using a monotonic stack.
// The core idea is to find, for each element arr[i], how many subarrays it is the minimum element of.
// If arr[i] is the minimum element in a subarray, then all other elements in that subarray must be greater than or equal to arr[i].
// We can determine the count of such subarrays by finding the "next smaller element" (NSE) to the right and the "previous smaller element" (PSE) to the left for each element arr[i].

// Let's define:
// PSE[i]: The index of the first element to the left of arr[i] that is strictly smaller than arr[i]. If no such element exists, PSE[i] can be considered -1.
// NSE[i]: The index of the first element to the right of arr[i] that is less than or equal to arr[i]. If no such element exists, NSE[i] can be considered arr.length.
// We use less than or equal for NSE to handle duplicate elements correctly and avoid double-counting. If we used strictly less than for both PSE and NSE, and there were duplicates, say [3, 1, 1, 2], the middle '1' might not be counted as a minimum for any subarray if the first '1' takes precedence. By making one of the conditions (PSE or NSE) less than or equal, we ensure each element is uniquely responsible for its "minimum-ness" in subarrays.

// The number of subarrays for which arr[i] is the minimum element is (i - PSE[i]) * (NSE[i] - i).
// (i - PSE[i]) represents the number of choices for the left boundary of the subarray (including arr[i]).
// (NSE[i] - i) represents the number of choices for the right boundary of the subarray (including arr[i]).

// We can use a monotonic increasing stack to find both PSE and NSE.

// Steps:
// 1. Initialize two arrays, `left` and `right`, of the same size as `arr`.
//    `left[i]` will store the count of elements to the left of `arr[i]` (including `arr[i]`) such that `arr[i]` is the minimum in that range.
//    `right[i]` will store the count of elements to the right of `arr[i]` (including `arr[i]`) such that `arr[i]` is the minimum in that range.

// 2. Calculate `left` array:
//    Iterate through `arr` from left to right. Maintain a monotonic increasing stack of indices.
//    For each `arr[i]`:
//    While the stack is not empty and `arr[stack.peek()] > arr[i]`, pop elements from the stack.
//    If the stack is empty, it means `arr[i]` is the smallest element to its left, so `left[i] = i + 1`.
//    Else, `left[i] = i - stack.peek()`.
//    Push `i` onto the stack.

// 3. Calculate `right` array:
//    Clear the stack. Iterate through `arr` from right to left. Maintain a monotonic increasing stack of indices.
//    For each `arr[i]`:
//    While the stack is not empty and `arr[stack.peek()] >= arr[i]`, pop elements from the stack. (Note: use `>=` here to handle duplicates correctly. This ensures that for duplicate values, the leftmost one is considered the "primary" minimum for subarrays containing them, avoiding overcounting).
//    If the stack is empty, it means `arr[i]` is the smallest element to its right, so `right[i] = arr.length - i`.
//    Else, `right[i] = stack.peek() - i`.
//    Push `i` onto the stack.

// 4. Calculate the total sum:
//    Initialize `totalSum = 0`.
//    Iterate from `i = 0` to `arr.length - 1`.
//    `totalSum = (totalSum + (long)arr[i] * left[i] * right[i]) % (10^9 + 7)`.
//    Use `long` for intermediate calculation to prevent overflow before taking modulo. The modulo is `10^9 + 7` as commonly used in competitive programming for large sums.

// Time Complexity: O(N)
// We iterate through the array three times (once for `left`, once for `right`, and once for sum). Each element is pushed and popped from the stack at most once.

// Space Complexity: O(N)
// We use `left`, `right` arrays, and a stack, all of which can store up to N elements.

class Solution {
    public int sumSubMins(int[] arr) {
        int n = arr.length;
        long MOD = 1_000_000_007;

        int[] left = new int[n];
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                left[i] = i + 1;
            } else {
                left[i] = i - stack.peek();
            }
            stack.push(i);
        }
        int[] right = new int[n];
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                right[i] = n - i;
            } else {
                right[i] = stack.peek() - i;
            }
            stack.push(i);
        }
        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum = (totalSum + (long)arr[i] * left[i] * right[i]) % MOD;
        }
         return (int)totalSum;
    }
}