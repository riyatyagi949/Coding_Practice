/*
Problem Statement:
You are given the head of a doubly linked list. You have to reverse the doubly linked list and return its head.

Approach:
To reverse a doubly linked list, we need to iterate through the list and, for each node, swap its `next` and `prev` pointers. We will maintain a `current` pointer starting from the head. In each step, we'll store the `next` node in a temporary variable, then swap `current.next` and `current.prev`. After the swap, we move to the next node, which is now `current.prev` (since the pointers have been swapped). The new head of the reversed list will be the last node of the original list. We can track this by keeping a `last` pointer that points to the node we are currently processing. After the loop finishes, the `last` pointer will point to the new head.

Time Complexity:
O(N), where N is the number of nodes in the doubly linked list. We traverse the list once to reverse the pointers.

Space Complexity:
O(1), as we are only using a few extra pointers (current, next_node, and last) to perform the reversal, and the space required does not depend on the number of nodes.

Optimal Solution:
*/

class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}


class Solution {
    public Node reverse(Node head) {
        if (head == null || head.next == null) return head;

        Node curr = head;
        Node prevNode = null;

        while (curr != null) {
            prevNode = curr.prev;
            curr.prev = curr.next;
            curr.next = prevNode;
            curr = curr.prev; 
        }

        if (prevNode != null) {
            head = prevNode.prev; 
        }

        return head;
    }
}
