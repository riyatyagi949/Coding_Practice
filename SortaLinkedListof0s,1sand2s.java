/*
Problem Statement:
Given the head of a linked list where nodes can contain values 0s, 1s, and 2s only. Your task is to rearrange the list so that all 0s appear at the beginning, followed by all 1s, and all 2s are placed at the end.

Approach:
The problem can be solved efficiently in a single pass using the concept of three separate linked lists for 0s, 1s, and 2s. We can create dummy nodes for each of these lists to handle the head creation and list manipulation easily.

1.  Initialize three dummy nodes: `zeroHead`, `oneHead`, and `twoHead` for the three separate lists. Also, initialize three pointers, `zeroTail`, `oneTail`, and `twoTail` pointing to these dummy nodes. These tails will be used to append new nodes to their respective lists.
2.  Iterate through the original linked list starting from the head.
3.  For each node in the original list, check its data value.
    -   If the data is 0, append the node to the `zero` list by setting `zeroTail.next` to the current node and then moving `zeroTail` to the current node.
    -   If the data is 1, append the node to the `one` list.
    -   If the data is 2, append the node to the `two` list.
4.  After the loop finishes, all nodes will have been moved to their respective lists.
5.  Now, we need to concatenate these three lists in the correct order: 0s, then 1s, then 2s.
6.  First, connect the `zero` list to the `one` list. Set `zeroTail.next` to `oneHead.next`.
7.  Next, connect the `one` list to the `two` list. Set `oneTail.next` to `twoHead.next`.
8.  Finally, to avoid any cycles and ensure the list ends properly, set `twoTail.next` to `null`.
9.  The new head of the sorted linked list will be `zeroHead.next`. Return this as the result.

This approach requires only a single traversal of the linked list, making it highly efficient. It rearranges the nodes in-place by changing their `next` pointers, without needing to change the data values or create new nodes.

Time and Space Complexity:
-   **Time Complexity: O(N)** where N is the number of nodes in the linked list. We traverse the list once to segregate the nodes into three separate lists.
-   **Space Complexity: O(1)** as we are only using a few extra pointers and dummy nodes to manage the three lists. We are not using any extra space that scales with the size of the input list.

Optimal Solution in Java:
*/

class Node {
    int data;
    Node next;

    Node(int d)
    {
        data = d;
        next = null;
    }
}
class Solution {
    public Node segregate(Node head) {
        if (head == null || head.next == null) return head;

        Node zeroHead = new Node(-1), oneHead = new Node(-1), twoHead = new Node(-1);
        Node zero = zeroHead, one = oneHead, two = twoHead;

        Node curr = head;
        while (curr != null) {
            if (curr.data == 0) {
                zero.next = curr;
                zero = zero.next;
            } 
            else if (curr.data == 1) {
                one.next = curr;
                one = one.next;
            } 
            else {
                two.next = curr;
                two = two.next;
            }
            curr = curr.next;
        }

        zero.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        one.next = twoHead.next;
        two.next = null;

        return zeroHead.next;
    }
}

