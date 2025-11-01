/**
     * Problem Statement: Delete Nodes From Linked List Present in Array
     * ------------------------------------------------------------------
     * Given an array of integers 'nums' and the head of a linked list, 
     * return the head of the modified linked list after removing all nodes 
     * from the linked list whose value exists in 'nums'.
     *
     * Constraints:
     * N (nums.length) <= 10^5, M (List length) <= 10^5.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N + M), where N is the length of 'nums' and M is the length of the linked list.
 * - **Step 1 (HashSet creation):** O(N) time to iterate through 'nums' and insert N elements into the HashSet.
 * - **Step 2 (List traversal):** O(M) time to traverse the linked list once. Each lookup in the HashSet 
 * is O(1) on average.
 * - Total time complexity: O(N + M).
 * * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the length of 'nums'.
 * - This space is primarily used to store the values of 'nums' in the HashSet for quick lookups.
 * - The space used for the `dummy` node and the pointers (`current`, `walker`) is O(1) auxiliary space.
 * - Total space complexity: O(N).
 */
// Optimal Solution in Java -

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// Optimal Solution in Java -

class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        int max = -1;
        for(int num : nums )
        {
            max = num > max ? num : max;
        }
        boolean[] freq = new boolean[max+1];
        for(int num : nums) freq[num] = true;

        ListNode temp = new ListNode();
        ListNode current = temp;

        while(head != null)
        {
            if( head.val >= freq.length || freq[head.val] == false)
            {
                current.next = head;
                current = current.next;
            }
            head = head.next;
        }
        current.next = null;
        return temp.next;
    }
}