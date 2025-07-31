/*
Problem Statement:
Given an integer array `arr`, the problem asks us to find the total count of distinct bitwise ORs that can be formed by all its non-empty subarrays. A subarray is a contiguous part of the array. The bitwise OR of a single-element subarray is the element itself.

Approach:
The provided solution efficiently tackles this problem using a dynamic programming approach combined with `HashSet`s to manage distinct bitwise OR values. The key insight is that the number of distinct bitwise OR values that can be generated for subarrays ending at any given index `i` is surprisingly small, at most 32 (for standard 32-bit integers). This is because applying a bitwise OR operation can only turn bits from 0 to 1; it can never turn a 1 to a 0. Thus, each new distinct OR value must have at least one more bit set than the previous value in the sequence, and there are only a limited number of bits.

The algorithm progresses through the input array `arr` element by element, keeping track of the unique OR sums possible up to the current point.

Let's break down the components and logic:

1.  `Set<Integer> result = new HashSet<>();`: This `HashSet` will ultimately store all unique bitwise OR sums found across *all* non-empty subarrays of the input array. Its final size will be our answer.

2.  `Set<Integer> prev = new HashSet<>();`: This `HashSet` serves as a dynamic memory for the OR sums. At each iteration `i`, `prev` holds all distinct bitwise OR sums of subarrays that ended at the *previous* index (`i-1`).

3.  Outer Loop (`for (int num : arr)`): This loop iterates through each element `num` in the input array `arr`. In each iteration, `num` represents the current element `arr[i]`.

    a.  `Set<Integer> curr = new HashSet<>();`: Inside the loop, a new `HashSet` called `curr` is created. This set will accumulate all distinct bitwise OR sums of subarrays that *end* precisely at the current `num`.

    b.  `curr.add(num);`: The first and most basic subarray ending at `num` is the single-element subarray `[num]`. Its bitwise OR sum is `num` itself, so it's added to `curr`.

    c.  Inner Loop (`for (int x : prev)`): This loop is crucial for extending existing subarrays. For every unique OR sum `x` that was possible for a subarray ending at the *previous* index, we can form a new subarray by appending the current `num`. The bitwise OR sum for this extended subarray would be `x | num`.
        -   `curr.add(x | num);`: The newly calculated `x | num` is added to the `curr` set. Since `curr` is a `HashSet`, duplicate values are automatically handled.

    d.  `result.addAll(curr);`: Once the `curr` set has been fully populated with all distinct OR sums ending at the current `num`, all these sums are added to the global `result` set. This ensures that `result` always contains all unique ORs encountered so far.

    e.  `prev = curr;`: At the end of the current iteration, the `curr` set (containing OR sums ending at the current element) becomes the `prev` set for the *next* iteration. This is the core of the dynamic programming state transition.

4.  `return result.size();`: After iterating through all elements of `arr`, the `result` set will contain every distinct bitwise OR sum possible from any non-empty subarray. The size of this set is then returned as the final answer.

Example: `arr = [1, 1, 2]`

- Initial: `result = {}`, `prev = {}`

- **Iteration 1: `num = 1`**
    - `curr = {}`
    - `curr.add(1)` -> `curr = {1}`
    - Inner loop (on `prev`): `prev` is empty, so no iterations.
    - `result.addAll({1})` -> `result = {1}`
    - `prev = {1}`

- **Iteration 2: `num = 1`**
    - `curr = {}`
    - `curr.add(1)` -> `curr = {1}`
    - Inner loop (on `prev = {1}`):
        - `x = 1`: `curr.add(1 | 1)` which is `curr.add(1)`. `curr` remains `{1}`.
    - `result.addAll({1})` -> `result` remains `{1}`.
    - `prev = {1}`

- **Iteration 3: `num = 2`**
    - `curr = {}`
    - `curr.add(2)` -> `curr = {2}`
    - Inner loop (on `prev = {1}`):
        - `x = 1`: `curr.add(1 | 2)` which is `curr.add(3)`. `curr` becomes `{2, 3}`.
    - `result.addAll({2, 3})` -> `result` becomes `{1, 2, 3}`.
    - `prev = {2, 3}`

After loop: `result = {1, 2, 3}`.
Return `result.size()` which is `3`.

Time Complexity:
The outer loop runs `N` times, where `N` is the length of `arr`. Inside the outer loop, the inner loop iterates over `prev`. The crucial optimization here is that the size of `prev` (and `curr`) is bounded by `MaxBits`, which is the number of bits in an integer (e.g., 32 for a standard `int`). This is because applying bitwise OR only sets bits, so to get a new distinct value, at least one new bit must be set.
Thus, each iteration of the outer loop involves operations on sets of size at most `MaxBits`. `HashSet` operations (add, addAll, iteration) take average `O(1)` time.
Therefore, the total time complexity is `O(N * MaxBits)`. Since `MaxBits` is a constant, this simplifies to `O(N)`.

Space Complexity:
- `result` stores all distinct OR sums. In the worst case, this could theoretically be `O(N * MaxBits)`, if each element contributes many unique OR sums that are not duplicates of previous ones.
- `prev` and `curr` sets each store at most `MaxBits` elements.
Thus, the overall space complexity is `O(N * MaxBits)`, or more practically, `O(Total_Distinct_ORs)`.

Optimal Solution:
The provided solution is optimal for this problem. The use of `HashSet` to manage distinct values and the observation about the limited number of distinct OR sums at each step are key to its efficiency. Any approach that needs to calculate all possible subarrays and their ORs would be at least this complex, and likely much more so without the bitwise OR property.
*/

import java.util.*;

class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> result = new HashSet<>();
         // Stores all unique bitwise OR sums found across all subarrays
        Set<Integer> prev = new HashSet<>();  
         // Stores unique bitwise OR sums of subarrays ending at the previous index

        // Iterate through each number in the input array
        for (int num : arr) {
            Set<Integer> curr = new HashSet<>();             
            curr.add(num);

            for (int x : prev) {
                curr.add(x | num);
            }
            result.addAll(curr);
            prev = curr;
        }
        return result.size();
    }
}