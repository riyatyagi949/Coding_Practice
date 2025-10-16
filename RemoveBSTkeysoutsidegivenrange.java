/**
 * Problem Statement: Remove BST keys outside given range
 * -----------------------------------------------------
 * Given the root of a Binary Search Tree (BST) and two integers l and r, 
 * remove all the nodes whose values lie outside the range [l, r].
 * The modified tree must also be a valid BST.
 *
 * Example:
 * Input: root = [6, -13, 14, N, -8, 13, 15, N, N, 7], l = -10, r = 13
 * Output: [6, -8, 13, N, N, 7]
 * Nodes removed: -13 (too small), 14, 15 (too large)
 */
/**
     * Optimal Solution: Recursive Post-Order Traversal (Pruning)
     * ---------------------------------------------------------
     * The strategy is to use a recursive function that processes the tree from 
     * the bottom up (similar to post-order traversal) and returns the root of the 
     * modified subtree. This allows the parent node to correctly update its child pointers.
     * * For each node:
     * 1. **Recursively process children:** First, prune the left and right subtrees 
     * by setting `root.left = removekeys(root.left, l, r)` and 
     * `root.right = removekeys(root.right, l, r)`.
     * 2. **Process current node (root):** After the children are clean, check the 
     * current node's value (`root.data`).
     * * a. **If root.data < l:** The current node is too small. Since it's a BST, 
     * all nodes in its left subtree are also too small (and already pruned). 
     * The valid nodes might only exist in the **right** subtree. 
     * We return `root.right` (effectively deleting `root` and promoting its 
     * right child/subtree to its position).
     *
     * b. **If root.data > r:** The current node is too large. All nodes in its 
     * right subtree are also too large (and already pruned). 
     * The valid nodes might only exist in the **left** subtree. 
     * We return `root.left` (effectively deleting `root` and promoting its 
     * left child/subtree to its position).
     *
     * c. **If l <= root.data <= r:** The current node is within the range. 
     * Its children have already been correctly pruned and attached. 
     * We simply return `root`.
     * * This method maintains the BST property because:
     * - When a node is too small, its right child is promoted, and the right child's 
     * value is greater than the deleted node, maintaining the ordering for the 
     * new parent.
     * - When a node is too large, its left child is promoted, and the left child's 
     * value is smaller than the deleted node, maintaining the ordering.
     */
    
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the total number of nodes in the BST.
 * Every node in the tree is visited exactly once to check its value and potentially 
 * update a parent's pointer. The operation at each node is O(1).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(H), where H is the height of the BST.
 * This space is consumed by the recursion stack frames.
 * - In a balanced BST, H = O(log N).
 * - In a highly skewed BST (worst case), H = O(N).
 */
// Optimal Solution in Java - 