/**
 * Problem Statement: K-th Smallest in BST
 * ---------------------------------------
 * Given the root of a Binary Search Tree (BST) and an integer k, 
 * the task is to find the k-th smallest element in the BST. 
 * If there is no k-th smallest element present (i.e., the number of nodes is less than k), 
 * return -1.
 *
 * Example:
 * Input: root = [20, 8, 22, 4, 12, N, N, N, N, 10, 14], k = 3
 * Output: 10
 * Explanation: The sorted elements are 4, 8, 10, 12, 14, 20, 22. The 3rd smallest is 10.
 *
 * Constraints:
 * 1 <= number of nodes, k <= 10^4
 */
/**
     * Optimal Solution: Inorder Traversal (DFS)
     * ----------------------------------------
     * The property of a Binary Search Tree (BST) is that an **Inorder Traversal** * (Left -> Root -> Right) visits the nodes in **non-decreasing (sorted) order**.
     * * The optimal approach is to perform a modified Inorder Traversal:
     * 1. Traverse the left subtree (smaller elements).
     * 2. Visit the root node: Increment the 'count'.
     * 3. Check if 'count' has reached 'k'. If it has, the current node's value is the 
     * k-th smallest element. Store the value and stop the traversal immediately 
     * to avoid unnecessary visits.
     * 4. Traverse the right subtree (larger elements).
     * * By stopping the traversal as soon as the k-th element is found, we can potentially 
     * save time compared to traversing the entire tree and storing all elements.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(H + k), where H is the height of the BST, and k is the target rank.
 * - In the best and average case (balanced BST), H = O(log N). Complexity is **O(log N + k)**.
 * - In the worst case (skewed BST), H = O(N). Complexity is **O(N)**.
 * * We visit at most 'k' nodes and travel down a path of length 'H' to reach the k-th node. 
 * Since the traversal is stopped as soon as the k-th element is found, we don't necessarily 
 * visit all N nodes. The worst case is when k = N (we traverse the whole tree).
 * * Space Complexity Analysis:
 * --------------------------
 * O(H), where H is the height of the BST.
 * This space is consumed by the recursion stack for the DFS traversal.
 * - In a balanced BST, O(log N).
 * - In a skewed BST, O(N).
 */
// Optimal Solution in Java -