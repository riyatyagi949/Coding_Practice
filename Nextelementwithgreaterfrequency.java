/*
Problem Statement:
Given an array arr[] of integers, for each element, find the closest (distance wise) to its right that has a higher frequency than the current element. If no such element exists, return -1 for that position.

Approach:
1. First, calculate the frequency of each element in the input array. A HashMap can be used for this purpose to store element-frequency pairs.
2. Initialize a result array or ArrayList of the same size as the input array, filled with -1s. This will store the final answer.
3. Iterate through the array from right to left. For each element `arr[i]`:
    a. Use a monotonic stack to keep track of elements encountered so far from the right. The stack will store indices.
    b. While the stack is not empty and the frequency of the element at the top of the stack is less than or equal to the frequency of `arr[i]`, pop elements from the stack. This is because these elements cannot be the "next greater frequency" for `arr[i]` or for any element to its left that `arr[i]` could be a candidate for.
    c. If the stack is not empty after popping, the element at the top of the stack is the closest element to the right of `arr[i]` with a higher frequency. Store `arr[stack.peek()]` in the result for `arr[i]`.
    d. Push the current index `i` onto the stack.

Time Complexity:
O(N) - Two passes are made: one to calculate frequencies and another to iterate through the array with the stack. Each element is pushed and popped from the stack at most once.

Space Complexity:
O(N) - For the frequency map and the stack, in the worst case, they can store all unique elements and all elements respectively.
*/

import java.util.*;

class Solution {
    public ArrayList<Integer> findGreater(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n, -1));

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use stack to track next element with higher frequency
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            int currFreq = freq.get(arr[i]);

            // Remove elements from stack with frequency <= current
            while (!stack.isEmpty() && freq.get(stack.peek()) <= currFreq) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                result.set(i, stack.peek());
            }

            stack.push(arr[i]);
        }

        return result;
    }
}
