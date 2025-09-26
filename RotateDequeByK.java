/**
 * Problem Statement:
 * You are given a deque (double-ended queue) `dq` of `n` non-negative integers. You are also given an integer `type` (1 for right rotation, 2 for left) and a positive integer `k`.
 * The task is to rotate the deque circularly by `k` positions according to the specified `type`.
 *
 * Optimal Approach:
 * A naive approach would be to perform the rotation `k` times, one element at a time. However, this is inefficient, especially when `k` is large. For a deque of size `n`, rotating it `k` times is equivalent to rotating it `k % n` times. This is because every `n` rotations, the deque returns to its original state.
 *
 * The optimal approach leverages this property to reduce the number of operations.
 *
 * 1. Calculate the effective number of rotations: `k = k % n`. This ensures that we only perform the necessary number of rotations, which will be at most `n-1`.
 * 2. Check the rotation `type`:
 * - **Left Rotation (type = 2):** To rotate left by `k` positions, we need to move the first `k` elements to the back of the deque. We can achieve this by looping `k` times and in each iteration, removing the element from the front (`dq.removeFirst()`) and adding it to the back (`dq.addLast()`).
 * - **Right Rotation (type = 1):** To rotate right by `k` positions, we need to move the last `k` elements to the front of the deque. We can do this by looping `k` times and in each iteration, removing the element from the back (`dq.removeLast()`) and adding it to the front (`dq.addFirst()`).
 *
 * Both `removeFirst()`, `addLast()`, `removeLast()`, and `addFirst()` operations on a deque have a time complexity of O(1). Therefore, the overall complexity of this approach is determined by the number of effective rotations, which is `k % n`.
 *
 * Time Complexity: O(k % n), which simplifies to O(min(k, n)). Since the constraints on `k` and `n` are up to 10^5, this is highly efficient.
 * Space Complexity: O(1) as the rotations are performed in-place on the deque, and no extra data structures are used.
 */
import java.util.Deque;

