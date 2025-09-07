// Problem Statement:
// Given an array of n sorted linked lists of different sizes, merge all these lists into a single sorted linked list and return the head of the merged list.

// Approach:
// The most efficient way to merge k sorted linked lists is to use a min-heap (or priority queue). This approach allows us to always retrieve the smallest element among the heads of all the linked lists.

// The algorithm is as follows:
// 1. Create a min-heap (PriorityQueue in Java) to store the head nodes of all the linked lists. The priority queue should be configured to sort nodes based on their data value.
// 2. Iterate through the input array of linked lists. For each non-null linked list, add its head node to the min-heap.
// 3. Create a dummy node and a `tail` pointer to build the merged list. Initialize the `tail` to the dummy node.
// 4. While the min-heap is not empty:
//    a. Extract the node with the minimum value from the heap. Let's call this `minNode`.
//    b. Append `minNode` to the merged list by setting `tail.next = minNode`.
//    c. Move the `tail` pointer forward: `tail = tail.next`.
//    d. If `minNode` has a next node, add that next node to the min-heap.
// 5. After the loop, the `dummy.next` will be the head of the merged sorted linked list. Return `dummy.next`.

// This approach guarantees that we are always picking the smallest available node from any of the k lists, thus building the final sorted list correctly.

// Time Complexity:
// Let `k` be the number of linked lists and `N` be the total number of nodes across all lists.
// Building the initial heap takes O(k * log k).
// Each of the N nodes is inserted into and extracted from the heap exactly once. The heap operations (insertion and extraction) take O(log k) time.
// Therefore, the total time complexity is O(N * log k).

// Space Complexity:
// The space complexity is O(k) because the min-heap will store at most k nodes at any given time (one from each linked list).

// Optimal Solution in Java-
import java.util.PriorityQueue;


class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}
 class Solution {
    Node mergeKLists(Node[] arr) {
        if (arr == null || arr.length == 0) return null;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.data - b.data);

        for (Node head : arr) {
            if (head != null) {
                pq.offer(head);
            }
        }

        Node dummy = new Node(0);
        Node tail = dummy;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            tail.next = curr;
            tail = tail.next;

            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }

        return dummy.next;
    }
}
