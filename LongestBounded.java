/**
 * Problem Statement:
 * Given an array of positive integers `arr[]` and a non-negative integer `x`, the task is to find the longest sub-array
 * where the absolute difference between any two elements is not greater than `x`.
 * The condition for a valid subarray `arr[i..j]` is: `max(arr[i..j]) - min(arr[i..j]) <= x`.
 * If multiple such subarrays exist, return the one that starts at the smallest index.
 *
 * Optimal Approach: Sliding Window with Monotonic Deques
 * Since we are looking for the longest subarray satisfying a condition, the Sliding Window technique is optimal.
 * The core challenge is efficiently maintaining the maximum and minimum elements within the current window `arr[left..right]` in O(1) time (amortized).
 *
 * 1. Data Structures: Use two Monotonic Deques (implemented with `LinkedList` in Java):
 * - `maxDeque`: Stores elements in decreasing order. The front element is the current maximum.
 * - `minDeque`: Stores elements in increasing order. The front element is the current minimum.
 *
 * 2. Window Expansion (Right Pointer `right`):
 * - When expanding the window by adding `arr[right]`, maintain the monotonicity of both deques by popping elements from the back that violate the order (smaller for `maxDeque`, larger for `minDeque`).
 * - Push `arr[right]` to the back of both deques.
 *
 * 3. Condition Check and Window Contraction (Left Pointer `left`):
 * - Check the condition: `maxDeque.peekFirst() - minDeque.peekFirst() > x`.
 * - If the condition is violated, contract the window by incrementing `left`.
 * - Before moving `left`, remove the element `arr[left]` from the front of the deques, but only if it is the current maximum or minimum (i.e., if it is at the front of its respective deque).
 *
 * 4. Result Tracking:
 * - After any contraction (or immediately after expansion if the condition holds), the current window `arr[left..right]` is valid.
 * - Track the maximum length found so far (`maxLength`) and the corresponding start and end indices (`resultStart`, `resultEnd`).
 * - The tiebreaker (smallest starting index) is naturally handled because we only update the result if a *greater* length is found, and the loop processes subarrays from left to right.
 *
 * Time Complexity: O(n), where n is the length of the array. Although there are nested loops, each element is added to the deques once and removed from the deques at most once. The left and right pointers also traverse the array at most once. This results in amortized O(1) operations per element.
 * Space Complexity: O(n) in the worst case, for storing all array elements in the deques (e.g., if the array is entirely sorted) and for storing the result subarray.
 */
// Optimal Solution in Java -

import java.util.*;

