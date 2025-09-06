// Problem Statement:
// Given the head of a linked list, determine whether the list contains a loop.
// If a loop is present, return the number of nodes in the loop, otherwise return 0.

// Approach:
// We can use Floyd's Cycle-Finding Algorithm, also known as the tortoise and hare algorithm.
// This algorithm uses two pointers, a 'slow' pointer and a 'fast' pointer.
// The slow pointer moves one step at a time, while the fast pointer moves two steps at a time.
// If there is a loop, the fast pointer will eventually catch up to the slow pointer.
// Once they meet, we know a loop exists.
// To find the length of the loop, we can keep one pointer (say, the slow pointer) at the meeting point.
// We then move a second pointer from the meeting point, counting the nodes until it returns to the meeting point.
// The count will be the length of the loop.
// If the pointers never meet, there is no loop, and we return 0.

// Time Complexity: O(N)
// The time complexity is O(N) because, in the worst case, the pointers traverse the entire list once to find the loop and then again to find the length of the loop.

// Space Complexity: O(1)
// The space complexity is O(1) as we only use a few extra pointers, regardless of the size of the linked list.

// Optimal Solution in Java:

class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}

class Solution {
    public int lengthOfLoop(Node head) {
        if (head == null)
        return 0;

        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return countLoopLength(slow);
            }
        }
        return 0; 
    }
    private int countLoopLength(Node nodeInLoop) {
        int count = 1;
        Node temp = nodeInLoop.next;

        while (temp != nodeInLoop) {
            count++;
            temp = temp.next;
        }

        return count;
    }
}
