/**
 * Problem Statement:
 * Design a SpecialQueue data structure that supports standard queue operations (enqueue, dequeue, getFront)
 * and two additional operations: getMin() and getMax(), both in O(1) time.
 * The queue will handle a sequence of queries, where each query is an operation.
 *
 * Optimal Approach:
 * A standard queue (e.g., `java.util.Queue` or `LinkedList`) provides O(1) time complexity for `enqueue`, `dequeue`, and `getFront`.
 * To achieve O(1) time complexity for `getMin()` and `getMax()`, we need to maintain the minimum and maximum elements separately.
 * A simple approach of iterating through the queue to find the min/max would be O(n), which is not efficient.
 *
 * The optimal solution involves using **two additional deques** (double-ended queues) to store the minimum and maximum elements in a specific order.
 * Let's call them `minDeque` and `maxDeque`.
 *
 * The main idea is that when we add an element to the main queue, we also update the `minDeque` and `maxDeque`.
 * - **For `enqueue(x)`:**
 * - Add `x` to the rear of the main queue.
 * - **Update `minDeque`:** While `minDeque` is not empty and its last element is greater than or equal to `x`, remove the last element. This ensures the `minDeque` always stores elements in non-decreasing order. Then, add `x` to the rear of `minDeque`.
 * - **Update `maxDeque`:** While `maxDeque` is not empty and its last element is less than or equal to `x`, remove the last element. This ensures the `maxDeque` always stores elements in non-increasing order. Then, add `x` to the rear of `maxDeque`.
 *
 * - **For `dequeue()`:**
 * - Get the front element of the main queue to be removed, let's call it `removed_x`.
 * - Remove the front element from the main queue.
 * - **Update `minDeque`:** If the front element of `minDeque` is equal to `removed_x`, remove it.
 * - **Update `maxDeque`:** If the front element of `maxDeque` is equal to `removed_x`, remove it.
 *
 * - **For `getFront()`:**
 * - Simply return the front element of the main queue.
 *
 * - **For `getMin()`:**
 * - The minimum element is always at the front of `minDeque`. Return `minDeque.peekFirst()`.
 *
 * - **For `getMax()`:**
 * - The maximum element is always at the front of `maxDeque`. Return `maxDeque.peekFirst()`.
 *
 * This design ensures that the front of `minDeque` and `maxDeque` always correspond to the minimum and maximum elements in the current main queue, respectively.
 * Each operation takes O(1) time on average. While a single `enqueue` operation might involve multiple `removeLast()` calls, each element is added and removed from the deques at most twice (once for the min-deque and once for the max-deque) over its lifetime in the main queue, so the amortized time complexity is O(1).
 *
 * Time Complexity: O(1) for all operations on average (amortized time).
 * Space Complexity: O(n) in the worst case, where n is the number of elements in the queue, as the deques can store all elements of the main queue.
 */
// Optimal Solution in Java - 

import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;

