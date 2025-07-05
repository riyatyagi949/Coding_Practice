// Problem Statement:
// You are given an array arr[] of integers. Your task is to select any two indices i and j such that i < j.
// From the subarray arr[i...j], find the smallest and second smallest elements.
// Add these two numbers to get the score of that subarray.
// Your goal is to return the maximum score that can be obtained from any subarray of arr[] with length at least 2.

// Approach:
// This problem can be solved efficiently using a monotonic stack. The idea is to iterate through the array and maintain a stack of indices such that the elements at these indices are in increasing order. When processing an element `arr[i]`:
// 1. If `arr[i]` is smaller than `arr[stack.peek()]`, it means `arr[stack.peek()]` is the first element to its left that is larger than it. In this scenario, `arr[stack.peek()]` and `arr[i]` can be the two smallest elements in the subarray `arr[stack.peek() ... i]`. We pop `stack.peek()` and update the maximum score with `arr[stack.peek()] + arr[i]`. We repeat this while the stack is not empty and the top element is greater than `arr[i]`.
// 2. After popping, if the stack is not empty, `arr[stack.peek()]` is the previous smaller element for `arr[i]`. In this case, `arr[stack.peek()]` and `arr[i]` are also potential candidates for the two smallest elements in the subarray `arr[stack.peek() ... i]`. We update the maximum score with `arr[stack.peek()] + arr[i]`.
// 3. Finally, push `i` onto the stack.
// This ensures that all relevant pairs (smallest and second smallest within a subarray where no intermediate element is smaller than both) are considered.

// Time Complexity: O(N)
// Each element is pushed onto and popped from the stack at most once.

// Space Complexity: O(N)
// In the worst case (e.g., a strictly increasing array), the stack can hold all N elements.

class Solution {
    public int maxSum(int arr[]) {
        int maxScore = 0;
        java.util.Stack<Integer> stack = new java.util.Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                maxScore = Math.max(maxScore, arr[stack.pop()] + arr[i]);
            }
            if (!stack.isEmpty()) {
                maxScore = Math.max(maxScore, arr[stack.peek()] + arr[i]);
            }
            stack.push(i);
        }
        return maxScore;
    }
}