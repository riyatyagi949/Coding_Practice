/**
 * Problem Statement: Median of BST
 * --------------------------------
 * You are given the root of a Binary Search Tree (BST), and the task is to find its median.
 * * Let the nodes of the BST, when written in ascending order (Inorder Traversal), be 
 * represented as V1, V2, V3, ..., Vn, where n is the total number of nodes.
 * * - If the number of nodes (n) is even: return V(n/2) (the n/2-th smallest value).
 * - If the number of nodes (n) is odd: return V((n+1)/2) (the (n+1)/2-th smallest value).
 * * Example:
 * Input: root = [20, 8, 22, 4, 12, N, N, N, N, 10, 14]
 * Inorder: 4, 8, 10, 12, 14, 20, 22. n = 7 (odd). Median is V(4) = 12.
 * * Constraints:
 * 1 <= number of nodes <= 10^5
 */
/**
     * Optimal Solution: Two-Pass Iterative Inorder Traversal
     * ------------------------------------------------------
     * The median is the k-th smallest element, where k depends on the total number of nodes (N).
     * Since an Inorder Traversal of a BST visits nodes in ascending order, we can find the 
     * k-th smallest element by stopping the traversal after visiting k nodes.
     * * This solution uses an iterative Inorder Traversal (using a stack) for two distinct passes:
     * * 1. Pass 1 (Count): Perform a full Inorder Traversal to count the total number of nodes (N).
     * 2. Calculate Rank (k): Determine the target rank (k) based on N (N/2 or (N+1)/2).
     * 3. Pass 2 (Search): Perform a second Inorder Traversal, keeping a running count of 
     * visited nodes. Stop and return the node value when the count reaches k.
     * * *Alternative Optimal (O(1) Space):* Morris Traversal can combine counting and searching 
     * into a single O(N) time pass with O(1) auxiliary space, avoiding the recursion stack or 
     * explicit stack structure. However, the two-pass iterative approach is often simpler to implement.
     */
  /**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the total number of nodes in the BST.
 * - Pass 1 (Counting): O(N), as every node is visited exactly once.
 * - Pass 2 (Searching): O(K), where K is the median rank (K <= N). In the worst case, 
 * we visit all N nodes (e.g., K=N).
 * - Overall complexity: O(N) + O(K) = O(N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(H), where H is the height of the BST.
 * - This space is used by the auxiliary Stack for the iterative Inorder Traversal.
 * - For a balanced BST, H = O(log N).
 * - For a skewed BST (worst case), H = O(N).
 * * Note: If using Morris Traversal, the auxiliary space complexity would be O(1).
 */
// Optimal Solution in Java -  

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
    private int countNodes(Node root) {
        int count = 0;
        Node curr = root;
        
        while (curr != null) {
            if (curr.left == null)
            {
                count++;
                curr = curr.right;
            } 
            else 
            {
                Node pre = curr.left;
                while (pre.right != null && pre.right != curr) 
                {
                    pre = pre.right;
                }
                
                if (pre.right == null) 
                {
                    pre.right = curr;
                    curr = curr.left;
                } 
                else
                {
                    pre.right = null;
                    count++;
                    curr = curr.right;
                }
            }
        }
        return count;
    }

    public int findMedian(Node root) {
        if (root == null)
        return 0;

        int count = countNodes(root);
        int currCount = 0;
        Node curr = root;
        Node prev = null;
        int median = 0;

        while (curr != null) 
        {
            if (curr.left == null) 
            {
                currCount++;

                if (count % 2 != 0 && currCount == (count + 1) / 2)
                    median = curr.data;
                else if (count % 2 == 0 && currCount == count / 2)
                    median = curr.data;

                curr = curr.right;
            } 
            else
            {
                Node pre = curr.left;
                while (pre.right != null && pre.right != curr)
                {
                    pre = pre.right;
                }

                if (pre.right == null)
                {
                    pre.right = curr;
                    curr = curr.left;
                } 
                else 
                {
                    pre.right = null;
                    currCount++;

                    if (count % 2 != 0 && currCount == (count + 1) / 2)
                        median = curr.data;
                    else if (count % 2 == 0 && currCount == count / 2)
                        median = curr.data;

                    curr = curr.right;
                }
            }
        }
        return median;
    }
}
