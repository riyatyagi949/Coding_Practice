/**
 * Problem Statement: Sum of Nodes in BST Range
 * --------------------------------------------
 * Given the root of a Binary Search Tree (BST) and two integers l and r, 
 * the task is to find the sum of all node values that lie in the range [l, r], 
 * including both l and r.
 *
 * Examples:
 * 1. Input: root = [22, 12, 30, 8, 20], l = 10, r = 22
 * Output: 54 
 * (Nodes in range: {12, 20, 22}. Sum: 12 + 20 + 22 = 54)
 * 2. Input: root = [8, 5, 11, 3, 6, N, 20], l = 11, r = 15
 * Output: 11
 * (Nodes in range: {11}. Sum: 11)
 *
 * Constraints:
 * 0 <= number of nodes <= 10^4
 * 0 <= node->data <= 10^4
 * 0 <= l <= r <= 10^4
 */
/**
     * Optimal Solution: Recursive Depth-First Search (DFS) with BST Pruning
     * -------------------------------------------------------------------------
     * The efficiency of the solution relies on leveraging the Binary Search Tree (BST) property
     * to avoid traversing subtrees that cannot possibly contain nodes within the target range [l, r].
     * * * BST Pruning Rules (Applied at each node):
     * 1. Check current node: If (l <= root.val <= r), include root.val in the sum.
     * 2. Prune Right Subtree: If (root.val > l), we MUST traverse the LEFT subtree, because 
     * values in the left subtree (which are < root.val) might still be >= l. 
     * If (root.val < l), the entire left subtree is less than l and can be safely skipped.
     * 3. Prune Left Subtree: If (root.val < r), we MUST traverse the RIGHT subtree, because 
     * values in the right subtree (which are > root.val) might still be <= r.
     * If (root.val > r), the entire right subtree is greater than r and can be safely skipped.
     *
     * This selective traversal ensures we visit only the necessary path to the nodes within the range.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N) in the worst case, where N is the total number of nodes in the BST.
 * The worst case occurs when the range [l, r] is so wide that it requires visiting 
 * every node (e.g., l=0 and r=10^4).
 * * * A more precise complexity due to pruning is O(K + H), where K is the number of 
 * nodes in the range and H is the height of the tree. The algorithm only explores 
 * the branches required to reach the start and end of the range, plus all nodes 
 * between those points. Since K <= N and H <= N, the overall upper bound remains O(N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(H), where H is the height of the BST.
 * This space is consumed by the recursion stack used for the DFS traversal.
 * - For a balanced BST, H = O(log N).
 * - For a skewed BST (worst case), H = O(N).
 */
// Optimal Solution in Java - 

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
} 
class Solution {
    static int sum = 0;

    public static void transformTree(Node root) {
        sum = 0;
        helper(root);
    }
    private static void helper(Node root) {
        if (root == null)
        return;

        helper(root.right);

        int original = root.data;
        root.data = sum;
        sum += original;

        helper(root.left);
    }
}

