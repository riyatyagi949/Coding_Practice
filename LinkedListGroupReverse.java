/*
Problem Statement:
Given the head of a singly linked list and an integer k, reverse every k nodes in the linked list. If the number of nodes is not a multiple of k, the remaining nodes at the end should also be reversed.

Approach:
The problem requires reversing the linked list in groups of a specified size k. This can be solved iteratively. We can traverse the linked list and, for each group of k nodes, reverse that sublist.
The key is to handle the connections between the reversed groups. We'll need pointers to keep track of the previous group's tail and the current group's head.
1.  **Iterative Reversal:** We'll iterate through the list, processing one group of k nodes at a time.
2.  **Pointers for Reversal:** Inside the loop for each group, we'll use three pointers: `prev`, `current`, and `next_node` (or `next`). `prev` will point to the previous node, `current` to the current node, and `next_node` will temporarily store the next node to prevent losing the rest of the list.
3.  **Group Tracking:** We'll also need a way to connect the reversed groups. A `dummy` node can simplify this, acting as the head of the new list. We'll have a `prev_tail` pointer that points to the last node of the previously reversed group.
4.  **Handling Leftovers:** The problem states that if the last group has fewer than k nodes, it should also be reversed. Our iterative approach naturally handles this, as the last remaining nodes will be processed as a final group.
5.  **Algorithm Steps:**
    * Create a dummy node and point its `next` to the head of the list.
    * Initialize `prev_tail` to the dummy node.
    * Iterate while there are nodes left to process.
    * Inside the loop, find the end of the current k-sized group. Count the nodes to ensure there are enough. If there are fewer than k nodes left, the rest of the list should still be reversed as a single group.
    * Store a reference to the head of the current group to be reversed (`current_head`).
    * Reverse the k nodes (or fewer if at the end of the list) from `current_head`. The reversal process will involve `prev`, `current`, and `next_node` pointers.
    * After reversing the group, connect the `prev_tail` (which was the end of the previous group) to the new head of the reversed group (`prev`, after the reversal loop).
    * Connect the original head of the reversed group (`current_head`) to the node that comes after the reversed group. This will be `next_node` from the reversal process.
    * Update `prev_tail` to be `current_head` for the next iteration.
    * Return `dummy.next`.

Time Complexity:
O(N), where N is the number of nodes in the linked list. We traverse the list exactly once. Each node is visited and its `next` pointer is updated a constant number of times.

Space Complexity:
O(1). We are performing the reversal in-place and only using a few extra pointers, which require constant space.

Optimal Solution in Java:
*/

class Node
{
    int data;
    Node next;
    Node(int key)
    {
        data = key;
        next = null;
    }
}
class Solution {
    public Node reverseKGroup(Node head, int k) {
        if (head == null || k == 1) 
        return head;

        Node dummy = new Node(0);
        dummy.next = head;
        Node prevGroupEnd = dummy;
        Node curr = head;

        while (curr != null) {
            Node groupStart = curr;
            int count = 0;
            Node temp = curr;
            while (temp != null && count < k) {
                temp = temp.next;
                count++;
            }

            Node prev = temp, next = null;
            Node node = groupStart;
            while (count-- > 0) {
                next = node.next;
                node.next = prev;
                prev = node;
                node = next;
            }
            prevGroupEnd.next = prev;
            prevGroupEnd = groupStart;
            curr = temp;
        }
        return dummy.next;
    }
}


