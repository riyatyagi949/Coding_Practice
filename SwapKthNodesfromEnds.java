/*
Problem Statement:
Given the head of a singly linked list and an integer k. Swap the kth node (1-based index) from the beginning and the kth node from the end of the linked list. Return the head of the final formed list and if it's not possible to swap the nodes return the original list.

Approach:
A simple approach is to first find the length of the linked list. Once we have the length, say 'n', the kth node from the end will be the (n - k + 1)th node from the beginning.
We can use a single pass to find the length of the list. Then, we can use another pass to find the kth node from the beginning and the (n - k + 1)th node from the beginning. Once we have both nodes, we can simply swap their data values.
This approach requires two passes over the linked list. The first pass to count the nodes and the second pass to find the two nodes to swap.

A more optimal approach can be done in a single pass. We can use two pointers, let's call them 'first' and 'second'. We also need a third pointer, 'kthNodeFromBeginning', to find the kth node from the beginning.
1. We initialize 'first' and 'kthNodeFromBeginning' to the head.
2. We move 'kthNodeFromBeginning' k-1 steps forward. This will place it at the kth node.
3. We then initialize 'second' to the head.
4. Now, we move both 'first' and 'second' pointers simultaneously until 'first' reaches the end of the list. When 'first' is at the last node, 'second' will be at the (n - k)th node from the beginning, which is the (k+1)th node from the end. No, this logic is incorrect.
Correct single pass approach:
1. Initialize two pointers, 'front' and 'back', both starting at the head.
2. Initialize a pointer 'kthNodeFromBeginning' to null.
3. Traverse the list with 'front' to find the kth node from the beginning. In this process, we move 'front' k-1 times.
4. If at any point 'front' becomes null, it means k is greater than the list size, so no swap is possible.
5. Once we are at the kth node, we set 'kthNodeFromBeginning' to 'front'.
6. Now, we start moving a 'back' pointer from the head, and simultaneously continue moving the 'front' pointer to the end of the list.
7. When 'front' reaches the end of the list (i.e., 'front.next' is null), 'back' will be at the kth node from the end.
8. Once we have both 'kthNodeFromBeginning' and 'back' pointers, we can swap their data values.

Time Complexity:
O(N), where N is the number of nodes in the linked list. We traverse the list once.

Space Complexity:
O(1), as we only use a few extra pointers.

Optimal Solution:
*/
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = head;
        ListNode second = head;
        ListNode kthNodeFromBeginning = null;

        for (int i = 1; i < k; i++) {
            if (first != null) {
                first = first.next;
            } else {
                return head;
            }
        }
        
        if (first == null) {
            return head;
        }

        kthNodeFromBeginning = first;

        while (first.next != null) {
            first = first.next;
            second = second.next;
        }

        int temp = kthNodeFromBeginning.val;
        kthNodeFromBeginning.val = second.val;
        second.val = temp;

        return head;
    }

    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}