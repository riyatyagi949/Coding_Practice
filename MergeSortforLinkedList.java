/*
Problem Statement:
Given the head of a linked list, sort it using Merge Sort.

Approach:
Merge sort is a divide-and-conquer algorithm. For a linked list, this approach works very well.
1.  **Divide:** Split the linked list into two halves. To find the middle of the list, we can use the fast and slow pointer approach. The slow pointer will reach the middle when the fast pointer reaches the end. We will break the list into two parts at this middle point.
2.  **Conquer:** Recursively sort each of the two sub-lists. The base case for the recursion is when the list is empty or has only one node, as a single-node list is already sorted.
3.  **Combine:** Merge the two sorted sub-lists into a single sorted list. This can be done by creating a new dummy head and iteratively picking the smaller node from the two sub-lists and appending it to the merged list.

To implement this, we need a helper function to find the middle of the linked list and another helper function to merge two sorted linked lists.
- `findMiddle(head)`: This function takes the head of a linked list and returns the middle node. It uses two pointers, `slow` and `fast`. `fast` moves two steps at a time, and `slow` moves one step. When `fast` reaches the end (or null), `slow` will be at the middle.
- `merge(list1, list2)`: This function takes the heads of two sorted linked lists and merges them into a single sorted list. It uses a dummy node to simplify the logic of building the new list.

The main `sortList` function will be recursive:
- It handles the base case (list is null or has one node).
- It finds the middle of the list and splits it into two halves.
- It recursively calls `sortList` on both halves.
- It merges the two sorted halves using the `merge` helper function.

Time Complexity:
- O(N log N).
- The `log N` factor comes from the recursive splitting of the list (dividing it in half at each step).
- The `N` factor comes from the merging step, where we traverse all N nodes in the current sub-lists.

Space Complexity:
- O(log N).
- This is due to the recursive call stack. The depth of the recursion is `log N` because the list is halved at each step.

*/
// Optimal  Solution in Java - 

class Node {
    int data;
    Node next;

    Node(int key) {
        data = key;
        next = null;
    }
}

class Solution {
    public Node mergeSort(Node head) {
        if (head == null || head.next == null)
         return head;

        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        
        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }
    private Node getMiddle(Node head) {
        if (head == null)
        return head;

        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Node merge(Node left, Node right) {
        Node dummy = new Node(-1);
        Node temp = dummy;

        while (left != null && right != null) {
            if (left.data <= right.data) {
                temp.next = left;
                left = left.next;
            }
            else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }

        if (left != null) temp.next = left;
        if (right != null) temp.next = right;

        return dummy.next;
    }
}

