/**
 * Problem Statement:
 * Given the root of a binary tree, find the maximum path sum. A path can start and end at any node in the tree.
 * A path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * It must contain at least one node and does not necessarily need to pass through the root.
 *
 * Optimal Approach (Post-order Traversal with Global Maximum):
 * This problem is solved effectively using a **post-order traversal (Depth First Search)** combined with a **global variable** to track the maximum sum found so far.
 *
 * Key Concepts:
 * 1.  **Maximum Path Sum through a Node (Total Path):** The path that defines the final answer can be a "V-shape" passing through a certain node `root`.
 * The total path sum through `root` is: `root.data + (max_gain_from_left_subtree) + (max_gain_from_right_subtree)`.
 * 2.  **Maximum Path Sum Extending Upwards (Max Gain):** For a recursive call to return a value to its parent, the path *must* be a "single chain" that continues upwards.
 * The maximum gain (or contribution) a node `root` can send to its parent is: `root.data + max(max_gain_from_left_subtree, max_gain_from_right_subtree)`.
 * If the maximum gain from a child subtree is negative, we should choose 0 (meaning we don't extend the path to that child) to maximize the result.
 *
 * Algorithm Steps:
 * 1.  Use a global variable (or a field in the class) `maxPathSum` initialized to the minimum possible integer value (`Integer.MIN_VALUE`) to store the final answer.
 * 2.  Define a recursive helper function, say `maxGain(node)`, which computes and returns the **maximum path sum that can start at the given `node` and extend *upwards* to its parent (i.e., a single chain)**.
 * 3.  Inside `maxGain(node)`:
 * a. **Base Case:** If `node` is `null`, return 0.
 * b. **Recursive Calls (Post-order):**
 * - Recursively calculate the maximum gain from the left child: `leftGain = maxGain(node.left)`.
 * - Recursively calculate the maximum gain from the right child: `rightGain = maxGain(node.right)`.
 * c. **Update Global Maximum (Total Path):** At the current node, we check for a potential total path sum that passes through `node` and uses both its left and right subtrees.
 * - `currentTotalPath = node.data + leftGain + rightGain`.
 * - Update the global maximum: `maxPathSum = Math.max(maxPathSum, currentTotalPath)`.
 * d. **Return Value (Max Gain for Parent):** Return the maximum single-chain path sum that can extend to the parent. We only consider the better of the two child paths.
 * - `return node.data + Math.max(leftGain, rightGain)`.
 *
 * By processing the "Total Path" check at every node and comparing it against the global maximum, we ensure that we consider *all* possible paths (including those that don't go up past the current node) in the tree.
 *
 * Time Complexity: O(n), where `n` is the number of nodes. We visit each node exactly once.
 * Space Complexity: O(h), where `h` is the height of the tree, for the recursion stack space. In the worst case (skewed tree), $h=n$, so $O(n)$. In the best/average case (balanced tree), $h = \log n$, so $O(\log n)$.
 */
// Optimal Solution in Java -

class Node{
    int data;
    Node left, right;
    Node(int d){
        data=d;
        left=right=null;
    }
}
class Solution {
    int maxSum; 
    int findMaxSum(Node root) 
    {
        maxSum = Integer.MIN_VALUE;
        maxPath(root);
        return maxSum;
    }
     int maxPath(Node node) 
     {
        if (node == null)
            return 0;
        
        int left = Math.max(0, maxPath(node.left));
        int right = Math.max(0, maxPath(node.right));
        
        maxSum = Math.max(maxSum, node.data + left + right);
        
        return node.data + Math.max(left, right);
    }
}

