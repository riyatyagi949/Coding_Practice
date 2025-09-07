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

class Solution {
    // Definition for singly-linked list.
    public static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node mergeKList(Node[] arr, int N) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        // Create a min-heap
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.data - b.data);

        // Add the head of each list to the min-heap
        for (Node node : arr) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        // Create a dummy node to build the result list
        Node dummy = new Node(0);
        Node tail = dummy;

        // Process the heap until it's empty
        while (!minHeap.isEmpty()) {
            // Get the smallest node from the heap
            Node minNode = minHeap.poll();

            // Append it to the result list
            tail.next = minNode;
            tail = tail.next;

            // If the extracted node has a next, add it to the heap
            if (minNode.next != null) {
                minHeap.add(minNode.next);
            }
        }

        return dummy.next;
    }
}