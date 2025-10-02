/**
 * Problem Statement:
 * Given two integers 'n' (the target sum) and 'k' (the number of elements), the task is to find all unique combinations
 * of 'k' distinct numbers from the range [1, 9] that sum up exactly to 'n'.
 *
 * Optimal Approach: Backtracking (Depth First Search - DFS)
 * This is a classic combinatorial problem best solved using the backtracking technique. Backtracking allows us to explore
 * all possible combinations of numbers systematically while enforcing the two main constraints:
 * 1. The combination must have exactly 'k' numbers.
 * 2. The sum of the numbers in the combination must equal 'n'.
 *
 * The DFS function will maintain the current combination being built, the number of elements already chosen, the current sum,
 * and the next number to consider.
 *
 * State for the recursive call:
 * - 'currentList': The list of numbers chosen so far.
 * - 'targetSum': The remaining sum needed (initially 'n').
 * - 'remainingCount': The number of elements still needed (initially 'k').
 * - 'startNum': The smallest number to consider in the current iteration (to ensure uniqueness and avoid duplicates).
 *
 * Recursive Steps:
 * 1. Base Case:
 * - If `targetSum == 0` and `remainingCount == 0`: A valid combination is found. Add `currentList` to the result.
 * - If `targetSum < 0` or `remainingCount == 0` (but `targetSum != 0`): This path is invalid. Stop.
 *
 * 2. Recursive Call:
 * - Iterate from `startNum` to 9:
 * a. Choose the current number `i`.
 * b. Add `i` to `currentList`.
 * c. Recursively call DFS with:
 * - New target sum: `targetSum - i`.
 * - New remaining count: `remainingCount - 1`.
 * - New start number: `i + 1` (This is crucial to ensure each number is used at most once and combinations are unique, as we only move forward in the number sequence).
 * d. Backtrack: Remove `i` from `currentList` before the next iteration to reset the state.
 *
 * Pruning/Optimization:
 * - The loop can stop earlier than 9. If the current number `i` is too large such that even with the smallest remaining numbers,
 * the sum will exceed the target, we can stop. A simpler and often sufficient check is to ensure that `targetSum >= i`.
 * - The iteration loop for `i` goes from `startNum` up to `9 - remainingCount + 1`. This is because we need at least `remainingCount - 1` more numbers
 * after `i` to complete the combination. The smallest possible sum of the next `remainingCount - 1` numbers is `(remainingCount - 1) * (i + 1)`.
 * For simplicity and safety, iterating up to 9 works fine, but the combination constraint `i <= 9` is sufficient for this problem size.
 *
 * Time Complexity: O(C(9, k) * k).
 * - The number of valid combinations C(9, k) is small (at most C(9, 4) = 126).
 * - For each potential combination, we perform work proportional to `k` (adding/removing elements from the list).
 * - Overall, the complexity is dominated by the number of valid paths, which is much better than the worst-case O(9^k) due to heavy pruning.
 * Space Complexity: O(k) for storing the current combination list and the depth of the recursion stack. O(Total Valid Combinations * k) for the final result list.
 */
// Optimal Solution in Java - 