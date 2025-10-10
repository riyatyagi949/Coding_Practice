/**
 * Problem Statement:
 * Given the root of a binary tree, perform a zig-zag level order traversal.
 * The traversal should follow these rules:
 * - Level 1 (root level) and all odd-numbered levels are traversed from left-to-right.
 * - Level 2 and all even-numbered levels are traversed from right-to-left.
 * Return the list of node values in the order they are visited.
 *
 * Optimal Approach: Breadth-First Search (BFS) with Level Direction Control (Using a Deque or LinkedList)
 *
 * The problem is essentially a standard Level Order Traversal (BFS) with a twist: the order in which nodes are added to the result list alternates for each level.
 *
 * 1. BFS Structure: We use a Queue for standard BFS to process nodes level by level. We also need a way to track the current level's traversal direction.
 * 2. Direction Control: We can use a boolean flag, say `leftToRight`, initialized to `true`. This flag is flipped after processing each level.
 * 3. Collecting Nodes: For each level, we will collect the nodes into a temporary list. The direction flag determines *how* we add nodes to this temporary list.
 * - To simplify the process of adding nodes in both left-to-right and right-to-left order, we can use a `Deque` (Double-Ended Queue, implemented by `LinkedList` in Java) for the current level's values.
 * - If `leftToRight` is true (odd level): Add new nodes to the **end** of the `Deque` (`addLast`).
 * - If `leftToRight` is false (even level): Add new nodes to the **front** of the `Deque` (`addFirst`).
 * 4. Traversal Logic:
 * - Start by pushing the `root` onto the main BFS queue.
 * - While the queue is not empty, process all nodes at the current level (`levelSize`).
 * - Inside the level loop, dequeue a node, and based on the `leftToRight` flag, add its value to the front or back of the temporary `Deque`.
 * - Enqueue the node's left and right children for the next level.
 * - After processing all nodes at the level, append the contents of the temporary `Deque` to the final result list.
 * - Flip the `leftToRight` flag for the next level.
 *
 * This approach correctly captures the zig-zag order while maintaining the O(N) time complexity of a standard BFS.
 *
 * Time Complexity: O(N), where N is the number of nodes in the binary tree. Every node is visited and processed exactly once.
 * Space Complexity: O(N). In the worst case (a perfectly balanced tree), the queue can hold up to N/2 nodes, and the result list stores all N node values.
 */
// Optimal Solution in Java -
import java.util.*;

class Node {
    int data;
    Node left,right;
    Node(int d)
    {
        data=d;
        left=right=null;
    }
}

class Solution {
    ArrayList<Integer> zigZagTraversal(Node root)
    {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) 
        return result;
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.data);
                
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            
            if (!leftToRight) Collections.reverse(level);
            result.addAll(level);
            
            leftToRight = !leftToRight;
        }
        return result;
    }
}
