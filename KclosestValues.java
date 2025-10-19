/**
 * Problem Statement: K Closest Values in BST
 * ------------------------------------------
 * Given the root of a Binary Search Tree (BST), a target value (double or int), 
 * and an integer k, find the k values in the BST that are closest to the target.
 * * Closeness is determined by the minimum absolute difference from the target.
 * * Tie-breaker rule: If two values have the same absolute difference, the smaller value 
 * is chosen first.
 *
 * Example:
 * Input: root = [20, 8, 22, 4, 12, N, N, N, N, 10, 14], target = 17, k = 3
 * Output: [14, 20, 12]
 * (Sorted BST: 4, 8, 10, 12, 14, 20, 22. Closest to 17 are 14, 20, 12).
 * * Constraints:
 * 1 <= number of nodes, k <= 10^4
 */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the total number of nodes in the BST.
 * - Step 1 (Inorder Traversal): O(N) to visit all nodes and build the sorted list.
 * - Step 2 (Finding Index): O(N) in the linear search used here. (If we used binary search
 * to find the start point, it would be O(log N)).
 * - Step 3 (Two Pointers): O(K), as we perform K comparisons and list accesses. Since K <= N,
 * this is at most O(N).
 * - Overall complexity: O(N) + O(N) + O(K) = O(N).
 * * * Alternative (Using Priority Queue): O(N log K) to traverse all N nodes, inserting into 
 * a min-heap (PriorityQueue) of size K based on the difference, or O(N log N) if target 
 * is an integer and we use a custom comparator on a standard sort. The two-pass O(N) solution 
 * is asymptotically better than the Priority Queue approach.
 * 
 * * * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the total number of nodes in the BST.
 * - O(N) space is required to store the `sortedList` of all node values.
 * - O(H) space is required for the recursion stack during the Inorder Traversal, where 
 * H is the height of the tree (H <= N).
 * - O(K) space is required for the `result` list.
 * - The dominant term is O(N).
 */
// Optimal Solution in Java-
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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
    public ArrayList<Integer> getKClosest(Node root, int target, int k) {
        ArrayList<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; 
            }
            return b[0] - a[0];
        });
       for (int val : inorderList) {
            int diff = Math.abs(val - target);
            maxHeap.offer(new int[]{diff, val});

            if (maxHeap.size() > k)
            {
                maxHeap.poll();
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            result.add(maxHeap.poll()[1]);
        }
        return result;
    }
    private void inorderTraversal(Node root, ArrayList<Integer> list) {
        if (root == null) return;
        inorderTraversal(root.left, list);
        list.add(root.data);
        inorderTraversal(root.right, list);
    }
}
