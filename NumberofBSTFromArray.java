/**
 * Problem Statement: Number of BST From Array
 * -------------------------------------------
 * You are given an integer array arr[] containing distinct elements.
 * The task is to return a new array where the i-th element denotes the number of 
 * unique Binary Search Trees (BSTs) that can be formed when the element arr[i] 
 * is chosen as the root.
 *
 * Example:
 * Input: arr[] = [2, 1, 3]
 * Output: [1, 2, 2]
 * Explanation: 
 * - If 2 is the root: left subtree has {1}, right subtree has {3}. #BST = 1 * 1 = 1.
 * - If 1 is the root: left subtree has {}, right subtree has {2, 3}. #BST = 1 * 2 = 2.
 * - If 3 is the root: left subtree has {2, 1}, right subtree has {}. #BST = 2 * 1 = 2.
 *
 * Constraints:
 * 1 <= arr.size() <= 6 (Very small N)
 * 1 <= arr[i] <= 15 (Very small value range)
 */
/**
     * Optimal Solution: Dynamic Programming (DP) based on Catalan Numbers
     * -----------------------------------------------------------------
     * This problem is a variation of counting unique BSTs. If an element `r` is chosen as the root
     * of a subtree containing a set of numbers `S`, all elements in `S` smaller than `r` must go 
     * to the left subtree, and all elements larger than `r` must go to the right subtree.
     * The total number of BSTs with root `r` is:
     * #BST(root=r) = (#BST possible for Left Subtree) * (#BST possible for Right Subtree)
     * * The number of unique BSTs that can be formed by 'n' elements is given by the n-th Catalan number, 
     * where 'n' is the number of elements in the subtree.
     * * * * Algorithm Steps:
     * 1. **Sort the Array:** Sort the input array `arr` to easily determine the elements 
     * that fall into the left and right subtrees of a chosen root.
     * 2. **Pre-calculate DP:** Use Dynamic Programming to compute `dp[i]`, the number of unique 
     * BSTs that can be formed using a contiguous sequence of `i` elements (this is the i-th Catalan number).
     * `dp[0] = 1` (empty tree)
     * `dp[i] = sum(dp[j] * dp[i - 1 - j])` for `j` from 0 to `i-1`.
     * The max size of the sequence is `N` (arr.length), which is at most 6.
     * 3. **Calculate Results:** Iterate through the *original* array `arr`. For each element `arr[i]`:
     * a. Find its position/index `p` in the *sorted* array.
     * b. The number of elements to the left is `p`.
     * c. The number of elements to the right is `N - 1 - p`.
     * d. The number of BSTs rooted at `arr[i]` is: `dp[p] * dp[N - 1 - p]`.
     * 4. **Map Results:** To ensure we return the result array in the order of the *original* array, 
     * we first map the sorted position to the number of BSTs, and then retrieve the results 
     * for the original order.
     * * Given the constraints (N <= 6), this approach is highly efficient.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N^2), where N is the length of the array arr.
 * - Sorting the array takes O(N log N).
 * - The DP calculation takes O(N^2) time (nested loops up to N).
 * - The final loop to calculate results takes O(N) time (Hash Map lookups are O(1) average).
 * - Since N is very small (N <= 6), O(N^2) dominates and is extremely fast.
 * * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the length of the array arr.
 * - O(N) for the sorted copy of the array.
 * - O(N) for the DP array 'dp'.
 * - O(N) for the 'positionMap' HashMap.
 * - O(N) for the final 'result' array.
 * - The total auxiliary space is O(N).
 */
// Optimal Solution in Java-
import java.util.*;