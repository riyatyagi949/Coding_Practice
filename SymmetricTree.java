// Problem Statement:
// Given the root of a binary tree, check whether it is symmetric, i.e., whether the tree is a mirror image of itself.
// A binary tree is symmetric if the left subtree is a mirror reflection of the right subtree.

// Approach:
// The problem can be solved by recursively checking if the left and right subtrees are mirror images of each other.
// Two trees are a mirror reflection of each other if:
// 1. Both are empty.
// 2. Both are non-empty and their root values are equal, and
//    the left subtree of one is a mirror reflection of the right subtree of the other, and
//    the right subtree of one is a mirror reflection of the left subtree of the other.

// We can define a helper function, `isMirror(node1, node2)`, that takes two nodes and checks if they are mirror images.
// The base cases for the `isMirror` function are:
// - If both `node1` and `node2` are null, they are symmetric, return true.
// - If only one of `node1` or `node2` is null, they are not symmetric, return false.
// - If the values of `node1` and `node2` are not equal, they are not symmetric, return false.
// Otherwise, recursively call `isMirror(node1.left, node2.right)` and `isMirror(node1.right, node2.left)`.
// The main `isSymmetric` function will call `isMirror` with `root.left` and `root.right`.

// Time Complexity:
// O(N), where N is the number of nodes in the tree. This is because we visit each node exactly once.

// Space Complexity:
// O(H), where H is the height of the tree. This is due to the recursion stack. In the worst case (skewed tree), H can be N, so O(N). In the best case (balanced tree), H is log N, so O(log N).

// Optimal Solution:
class Solution {
    public boolean isSymmetric(Node root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(Node left, Node right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        
        return (left.data == right.data)
            && isMirror(left.left, right.right)
            && isMirror(left.right, right.left);
    }
}