// Problem Statement:
// Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1.
// The linked list holds the binary representation of a number.
// Return the decimal value of the number in the linked list.
// The most significant bit is at the head of the linked list.

// Approach:
// The problem asks us to convert a binary number represented by a linked list into its decimal equivalent.
// The most significant bit is at the head, meaning the linked list is ordered from left to right for the binary number.
// For example, if the linked list is 1 -> 0 -> 1, it represents the binary number "101", which is 5 in decimal.

// There are a couple of ways to approach this:

// Method 1: Convert to String then Parse
// 1. Traverse the linked list and append each node's value (0 or 1) to a StringBuilder.
// 2. Convert the StringBuilder to a String.
// 3. Use `Integer.parseInt(binaryString, 2)` to convert the binary string to its decimal integer value.

// Method 2: Iterative Calculation (Optimal)
// This method avoids string conversions and directly calculates the decimal value.
// The decimal value of a binary number can be calculated by iterating through its bits from left to right (most significant to least significant).
// For each bit, multiply the current decimal value by 2 (effectively shifting it left) and then add the current bit's value.
// For example, for "101":
// - Start `decimalValue = 0`
// - Node 1: `decimalValue = 0 * 2 + 1 = 1`
// - Node 0: `decimalValue = 1 * 2 + 0 = 2`
// - Node 1: `decimalValue = 2 * 2 + 1 = 5`

// This method is generally preferred for its efficiency as it avoids the overhead of string operations.

// Time Complexity:
// O(N), where N is the number of nodes in the linked list. We traverse the linked list once.

// Space Complexity:
// O(1), as we only use a few variables to store the decimal value.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        int decimalValue = 0;
        ListNode current = head;

        while (current != null) {
            decimalValue = (decimalValue << 1) | current.val; // Equivalent to decimalValue = decimalValue * 2 + current.val;
            current = current.next;
        }

        return decimalValue;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}