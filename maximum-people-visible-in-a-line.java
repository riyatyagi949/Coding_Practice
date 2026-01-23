/**
 * PROBLEM STATEMENT: Maximum People Visible in a Line
 * --------------------------------------------------------------------------------
 * You are given an array arr[], where arr[i] represents the height of the ith 
 * person standing in a line.
 * * A person 'i' can see another person 'j' if:
 * 1. height[j] < height[i]
 * 2. There is no person 'k' standing between them such that height[k] >= height[i].
 * * Each person can see in both directions (front and back). 
 * Task: Find the maximum number of people that any single person can see, 
 * including themselves.
 * * Example:
 * Input: arr[] = [6, 2, 5, 4, 5, 1, 6]
 * Output: 6
 * Explanation: Person at index 0 (height 6) can see heights at indices 1, 2, 3, 4, 5. 
 * Including themselves, that is 6 people.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Monotonic Stack (Nearest Greater Element)
 * --------------------------------------------------------------------------------
 * A person 'i' can see all people to their left until they hit someone with 
 * height >= arr[i]. Similarly for the right side.
 * * 1. Left Visibility: For each index 'i', we need to find the nearest index 'L' 
 * to the left where arr[L] >= arr[i]. Every person between L and i (exclusive) 
 * is visible to 'i'.
 * 2. Right Visibility: For each index 'i', find the nearest index 'R' to the 
 * right where arr[R] >= arr[i]. Every person between i and R (exclusive) 
 * is visible to 'i'.
 * 3. Calculation:
 * - Left visible count = i - (index of nearest greater element on left) - 1.
 * - Right visible count = (index of nearest greater element on right) - i - 1.
 * - Total = Left + Right + 1 (self).
 * * We use a Monotonic Stack to find these "Nearest Greater Elements" in linear time.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We traverse the array twice (once for left, once for right).
 * - Each index is pushed and popped from the stack at most once.
 * - N is up to 10^4, so O(N) is highly efficient.
 * * Space Complexity: O(N)
 * - We use a stack to store indices, which can grow up to size N in the worst case.
 * - We use arrays 'left' and 'right' to store counts for each person.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -

import java.util.Stack;

class Solution {
    public int maxPeople(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];

        java.util.Stack<Integer> st = new java.util.Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) {
                st.pop();
            }
            left[i] = st.isEmpty() ? i : i - st.peek() - 1;
            st.push(i);
        }
        st.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) {
                st.pop();
            }
            right[i] = st.isEmpty() ? (n - i - 1) : (st.peek() - i - 1);
            st.push(i);
        }
        int maxVisible = 0;
        for (int i = 0; i < n; i++) {
            int total = left[i] + right[i] + 1;
            maxVisible = Math.max(maxVisible, total);
        }
        return maxVisible;
    }
}

