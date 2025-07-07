// Problem Statement:
// Given a circular integer array arr[], the task is to determine the next greater element (NGE) for each element in the array.
// The next greater element of an element arr[i] is the first element that is greater than arr[i] when traversing circularly. If no such element exists, return -1 for that position.

// Approach:
// The problem can be solved efficiently using a monotonic stack. Since the array is circular, we can simulate two traversals of the array by effectively doubling its length or by using the modulo operator.
// We iterate through the array from right to left (or from 2*n-1 down to 0 if we imagine a doubled array). For each element, we pop elements from the stack that are less than or equal to the current element.
// If the stack becomes empty after popping, it means there is no greater element, and we store -1. Otherwise, the top of the stack is the next greater element.
// Finally, we push the current element's index onto the stack. For circular arrays, we can iterate from 2*n - 1 down to 0, and use (i % n) to get the actual index in the original array.

// Time Complexity:
// O(N) where N is the size of the input array. Each element is pushed and popped from the stack at most once.

// Space Complexity:
// O(N) for the stack in the worst case (e.g., a decreasing array).

// Optimal Solution:
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public ArrayList<Integer> nextLargerElement(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));
        Stack<Integer> st = new Stack<>();

        for (int i = 2 * n - 1; i >= 0; i--) {
            int currentElement = arr[i % n];
            while (!st.isEmpty() && st.peek() <= currentElement) {
                st.pop();
            }
            if (st.isEmpty()) {
                result.set(i % n, -1);
            } else {
                result.set(i % n, st.peek());
            }
            st.push(currentElement);
        }
        return result;
    }
}