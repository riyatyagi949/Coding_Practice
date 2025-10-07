/**
 * Problem Statement:
 * Given the root of a binary tree, return its Bottom View. The Bottom View is the set of nodes visible
 * when the tree is viewed from the bottom. If multiple nodes are at the same horizontal distance (HD),
 * the one that appears later in the level order traversal (i.e., lower in the tree) is the one visible
 * in the Bottom View.
 *
 * Optimal Approach (Level Order Traversal with Horizontal Distance):
 * The Bottom View can be determined using a Level Order Traversal (BFS) while keeping track of the
 * Horizontal Distance (HD) of each node from the root.
 *
 * 1. Horizontal Distance (HD):
 * - The root node is assigned an HD of 0.
 * - Moving to the left child decreases the HD by 1 (HD - 1).
 * - Moving to the right child increases the HD by 1 (HD + 1).
 *
 * 2. Data Structure:
 * - We use a **TreeMap** (or HashMap) to store the final result. The **key** of the map is the
 * Horizontal Distance (HD), and the **value** is the data of the node.
 * - A **Queue** is used for the Level Order Traversal, storing a pair of (Node, HD).
 *
 * 3. Logic (Level Order Traversal - BFS):
 * - Start BFS by adding the root node with HD 0 to the queue.
 * - In BFS, when a node is dequeued, we update the map:
 * - Since BFS processes nodes level by level, and for the same HD, the lower node is processed
 * *later*, simply placing a new node's data into the map with its HD as the key will **automatically**
 * overwrite any previously stored node for that HD. This ensures that the last node encountered
 * (which is the bottom-most/lowest node) for that HD is the one stored.
 * - Process children: Enqueue the left child with HD - 1 and the right child with HD + 1.
 *
 * 4. Final Result:
 * - After the BFS completes, the TreeMap contains the data of the bottom-most node for every unique HD.
 * - Because a TreeMap is used, the keys (HDs) are naturally sorted, ensuring the final output is ordered from left to right.
 * - Extract all the values from the TreeMap to get the final Bottom View list.
 *
 * Time Complexity: O(N log N), where N is the number of nodes.
 * - The BFS part is O(N).
 * - The TreeMap operations (insert/update) take O(log K) time, where K is the number of unique HDs (which is at most N).
 * - Therefore, the overall time complexity is dominated by the map operations: O(N log N).
 *
 * Space Complexity: O(N)
 * - O(W) for the queue, where W is the maximum width of the tree (in the worst case, W=N).
 * - O(N) for the TreeMap to store all unique HDs.
 */
// Optimal Solution in Java - 
import java.util.*;

// Definition of binary node .

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}


class Solution {
    static class Pair {
        Node node;
        int hd;
        Pair(Node n, int h) {
            node = n;
            hd = h;
        }
    }

    public ArrayList<Integer> bottomView(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null)
        return res;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            Node curr = p.node;
            int hd = p.hd;
            map.put(hd, curr.data);

            if (curr.left != null)
            q.add(new Pair(curr.left, hd - 1));
            if (curr.right != null) 
            q.add(new Pair(curr.right, hd + 1));
        }

        res.addAll(map.values());
        return res;
    }
}

