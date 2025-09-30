/**
 * Problem Statement:
 * Given a 0-indexed integer array `nums` of digits (0-9), the triangular sum is the value of the single element remaining after a repeated process.
 * In each step, a new array `newNums` is created with length one less than the current array. For each index `i`, `newNums[i]` is calculated as
 * `(nums[i] + nums[i+1]) % 10`. The current array `nums` is replaced by `newNums`, and the process repeats until the array has only one element.
 * Return the triangular sum.
 *
 * Optimal Approach (Simulation with In-Place Modification):
 * The most straightforward and efficient approach for the given constraints (N <= 1000) is to directly **simulate** the process described.
 *
 * 1. **Iterative Reduction**: The process is repeated until the array size is 1. This means we'll have `n-1` steps. The outer loop will control the size of the array being processed.
 * 2. **In-Place Modification**: Instead of creating a `newNums` array in each step, we can reuse the current `nums` array to save space. Since `newNums` has length `n-1`, the results of the current step can be stored in the first `n-1` positions of the original `nums` array. This is because the values at indices `i+1` to `n-1` are no longer needed for the current step's calculation.
 * 3. **Inner Loop**: The inner loop calculates the new elements for the current step: `nums[i] = (nums[i] + nums[i + 1]) % 10`. This calculation uses the original values of `nums[i]` and `nums[i+1]` and overwrites the no-longer-needed `nums[i]` for the next round.
 * 4. **Termination**: The process continues until the effective size of the array (`size`) is reduced to 1. The final answer will be the single element at `nums[0]`.
 *
 * This simulation correctly models the problem and is efficient enough given the small constraint on the array length ($N \le 1000$).
 *
 * Time Complexity: O(n^2), where $n$ is the length of `nums`. The outer loop runs $n-1$ times, and the inner loop runs from $n-1$ down to 1 times. The total number of additions is proportional to $(n-1) + (n-2) + \dots + 1 = \frac{(n-1)n}{2}$, which is $O(n^2)$.
 *
 * Space Complexity: O(1) (or O(n) depending on how the input is handled). Since we modify the input array in place, we use no extra space proportional to $n$ for new arrays, resulting in $O(1)$ auxiliary space.
 */
// Optimal Solution in Java -