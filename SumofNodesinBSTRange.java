/**
 * Problem Statement: Sum of Nodes in BST Range.
 * --------------------------------------------
 * Given the root of a Binary Search Tree (BST) and two integers l and r, 
 * the task is to find the sum of all node values that lie in the range [l, r], 
 * including both l and r.
 *
 * Example:
 * Input: root = [22, 12, 30, 8, 20], l = 10, r = 22
 * Output: 54 (Nodes: 12, 20, 22 -> Sum: 12 + 20 + 22)
 *
 * Constraints:
 * 0 <= number of nodes <= 10^4
 * 0 <= node->data <= 10^4
 * 0 <= l <= r <= 10^4
 */
/**
     * Optimal Solution: Recursive Depth-First Search (DFS) leveraging BST property.
     * -------------------------------------------------------------------------
     * The standard approach is a simple DFS traversal, checking if each node's value 
     * falls within the [l, r] range.
     * * The optimal approach leverages the Binary Search Tree (BST) property to prune 
     * the search space, avoiding unnecessary traversal of entire subtrees.
     * * 1. If the current node is null, return 0.
     * 2. If node.val is greater than r:
     * - All nodes in the right subtree will also be greater than r.
     * - We only need to search the left subtree.
     * 3. If node.val is less than l:
     * - All nodes in the left subtree will also be less than l.
     * - We only need to search the right subtree.
     * 4. If node.val is within [l, r] (l <= node.val <= r):
     * - Add node.val to the total sum.
     * - Search both the left and right subtrees, as they might contain valid nodes.
     * * This pruning significantly reduces the number of nodes visited compared to a 
     * general binary tree traversal, especially in deep, balanced trees.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N) in the worst case, where N is the number of nodes in the BST.
 * The worst case occurs when the range [l, r] is very wide and includes all 
 * or almost all nodes, or when the search path touches every node (e.g., in a skewed tree).
 * * However, due to the pruning based on the BST property, the actual time complexity 
 * is O(K + H), where K is the number of nodes in the range, and H is the height 
 * of the tree. Since K is at most N, and H is at most N, the tightest *general* * upper bound is O(N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(H), where H is the height of the BST.
 * This space is consumed by the recursion stack.
 * - In a balanced BST, H = O(log N).
 * - In a highly skewed BST (worst case), H = O(N).
 */
// Optimal Solution - 

class Node {
    int data;
    Node left, right;
    Node(int val) {
        data = val;
        left = right = null;
    }
}
class Solution {
    public int nodeSum(Node root, int l, int r) {
        if (root == null) 
        return 0;
        
        if (root.data < l)
            return nodeSum(root.right, l, r);
        
        if (root.data > r)
            return nodeSum(root.left, l, r);
        
        return root.data + nodeSum(root.left, l, r) + nodeSum(root.right, l, r);
    }
}

