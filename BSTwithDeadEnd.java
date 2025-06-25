/*
Problem: BST with Dead End

Given a Binary Search Tree (BST) containing unique positive integers greater than 0, determine whether the BST contains a dead end.
A dead end is a leaf node in the BST such that no new node can be inserted in the BST at or below this node while maintaining the BST property and the constraint that all node values must be > 0.

Approach:
The problem can be solved using a recursive approach where we traverse the BST and keep track of the minimum and maximum possible values that can be inserted in the current subtree while maintaining the BST property.
For any node, its left child must be less than the node's value, and its right child must be greater than the node's value.
When traversing to the left child, the maximum possible value for a new node becomes `node.data - 1`.
When traversing to the right child, the minimum possible value for a new node becomes `node.data + 1`.
The initial range for the root node is `min = 1` (since all node values must be > 0) and `max = Integer.MAX_VALUE`.

A dead end exists if, at any point during the traversal, the `min` and `max` values become equal. This signifies that there is no valid integer that can be inserted at that position, making it a dead end. This condition will only be met when we reach a potential leaf node where the range of possible insertion values has shrunk to a single integer (which is the current node's value, making insertion impossible).

Time Complexity: O(N), where N is the number of nodes in the BST. This is because we visit each node exactly once.
Space Complexity: O(H), where H is the height of the BST. This is due to the recursion stack. In the worst case (skewed tree), H can be N, leading to O(N) space. In the best case (balanced tree), H is log N, leading to O(log N) space.

Optimal Solution:
*/
class Solution {
    public boolean isDeadEnd(Node root) {
        return checkDeadEnd(root, 1, Integer.MAX_VALUE);
    }
    private boolean checkDeadEnd(Node node, int min, int max) {
        if (node == null)
            return false;
        if (min == max)
            return true;
        return checkDeadEnd(node.left, min, node.data - 1) ||
               checkDeadEnd(node.right, node.data + 1, max);
    }
}