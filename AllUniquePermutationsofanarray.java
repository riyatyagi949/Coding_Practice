/**
 * Problem Statement:
 * Given an array arr[] that may contain duplicates, find all possible distinct permutations of the array.
 * The output should be a list of lists, where each inner list is a unique permutation, and the final list of permutations should be sorted lexicographically.
 *
 * Optimal Approach: Backtracking with Pruning
 *
 * The problem requires generating all *distinct* permutations. A standard backtracking (or recursion) approach generates all permutations, but duplicates will appear
 * if the input array has repeated elements.
 *
 * To ensure distinct permutations are generated and to satisfy the "sorted order" requirement, we use the following steps:
 * 1. Sort the input array `arr` initially. This helps in two ways:
 * a. It makes it easy to handle duplicates (pruning).
 * b. It ensures that the permutations are generated in lexicographically sorted order naturally.
 * 2. Use a boolean array, `used[]`, to keep track of which elements have already been included in the current permutation path. This is standard for permutation generation.
 * 3. Implement the backtracking function (e.g., `solve`):
 * - Base Case: If the current permutation (`currentList`) has the same size as the original array, add it to the result list (`ans`).
 * - Recursive Step: Iterate through the sorted array `arr`.
 * - **Pruning (Handling Duplicates):** This is the key. To avoid generating duplicate permutations, we only process an element `arr[i]` if:
 * a. It hasn't been used yet (`used[i] == false`).
 * b. OR, if the current element is a duplicate of the previous element (`arr[i] == arr[i-1]`), the previous element **must** have already been used/processed (`used[i-1] == true`).
 * However, for clarity and effectiveness in a typical sorted backtracking approach, the condition is usually:
 * If `arr[i] == arr[i-1]` AND `used[i-1] == false`, we **skip** `arr[i]`.
 * Why? If `used[i-1]` is false, it means we are *starting a new permutation path* and the previous identical element `arr[i-1]` was not chosen. To avoid generating the same permutation later by choosing `arr[i]` now, we must wait until `arr[i-1]` has been used and its branch is complete. Skipping `arr[i]` in this case ensures that among a group of identical elements, only the first one is considered to start a path.
 * - If the element passes the check:
 * - Mark `arr[i]` as used (`used[i] = true`).
 * - Add `arr[i]` to `currentList`.
 * - Recurse: `solve(...)`.
 * - Backtrack: Remove `arr[i]` from `currentList` and mark `used[i] = false`.
 *
 * Time Complexity: O(n * n!), where `n` is the number of elements in the array.
 * The number of unique permutations can be up to $n!$, and for each permutation, it takes $O(n)$ time to create the list and add it to the result.
 * Space Complexity: O(n) for the auxiliary space used by the `used` array and the recursion stack depth. The space required to store the results is $O(n * \text{number of unique permutations})$.
 */

//  Optimal Solution in Java - 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

