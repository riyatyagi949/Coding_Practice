/**
 * Problem Statement:
 * Given a binary array arr (0s and 1s) and an integer k, find the minimum number of operations to make the entire array consist of only 1s.
 * An operation involves selecting a contiguous subarray of length k and flipping all its bits (0 -> 1, 1 -> 0).
 * If it's not possible to achieve the goal, return -1.
 *
 * Optimal Approach: Greedy Algorithm with Difference Array / Sliding Window
 *
 * This problem can be solved efficiently using a greedy approach combined with a technique to track the cumulative effect of flips.
 * The key idea is that any '0' must be flipped to a '1'. Since we want the minimum number of flips, we should only perform a flip when necessary.
 *
 * 1. Greedy Strategy:
 * - Iterate through the array from left to right.
 * - If `arr[i]` is currently a '0', we MUST start a flip operation at index `i`. This is because any flip operation that starts *after* index `i` (i.e., at `i+1` or later) cannot affect `arr[i]`. Thus, the leftmost '0' must be fixed by a flip starting at its position.
 * - If a flip is started at index `i`, it affects elements from `arr[i]` to `arr[i + k - 1]`.
 *
 * 2. Efficiently Tracking Flips (Difference Array / Sliding Window):
 * - Naively performing the flip (modifying the array) for every '0' would be O(N*K), which is too slow.
 * - We use a variable, let's call it `isFlipped`, to track the cumulative number of times the current element `arr[i]` has been flipped by operations *covering* it. Since a flip operation toggles the bit, we only care about the parity (odd/even) of the total flips.
 *
 * - A boolean/integer array, or a Deque (as in the more complex but often taught approach), or a simple difference array/sliding window variable, can track the *change* in the flip state.
 *
 * - In this simplified $O(N)$ approach, we use:
 * - `flipCount`: The total number of flip operations performed.
 * - `currentFlips`: A counter for the number of active flips (flips that started at index $j \le i$ and end at $j + k - 1 \ge i$) affecting the current index $i$.
 * - `isFlippedStart`: A 'difference array' concept, using an auxiliary array or similar to mark the end of a flip. In the simplest $O(N)$ implementation, we can use the original array to mark the start of a flip operation.
 *
 * - **Simplified $O(N)$ Logic using the array for tracking:**
 * - We use the $k$-length window size to track active flips. A **Queue or Deque** tracks the indices where flips started.
 * - `flips`: A Queue/Deque storing the starting indices of all *active* flips (flips that affect the current index `i`).
 * - When moving from `i-1` to `i`, we first check the Queue: if the earliest flip started at `i-k`, it's now out of the window, so we remove it from the Queue. `currentFlips` is updated by the size of the Queue.
 * - We check the **effective state** of `arr[i]`: `effectiveValue = arr[i] XOR (flips.size() % 2)`.
 * - If `effectiveValue` is '0', we must flip:
 * - Increment `flipCount`.
 * - Add `i` to the `flips` Queue.
 * - **Crucial Check:** If a flip cannot be completed (i.e., $i + k > N$), it's impossible to fix the '0' at $i$, so return -1.
 *
 * 3. Final Check:
 * - The check for $i + k > N$ inside the loop handles the impossibility case correctly.
 *
 * Time Complexity: O(n). We iterate through the array once, and all Queue/Deque operations (add, remove, size) take O(1) time.
 * Space Complexity: O(k). In the worst case, the Deque can store up to $k$ indices (if $k$ flips are started consecutively).
 */

