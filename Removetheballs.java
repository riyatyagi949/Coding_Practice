// Problem Statement:
// You are given two arrays, color and radius, representing a sequence of balls:
// color[i] is the color of the i-th ball.
// radius[i] is the radius of the i-th ball.
// If two consecutive balls have the same color and radius, remove them both. Repeat this process until no more such pairs exist.
// Return the number of balls remaining after all possible removals.

// Approach:
// The problem can be efficiently solved using two stacks, one for colors and one for radii.
// We iterate through the balls (color and radius arrays) from left to right.
// For each ball, we check if the top of the color stack matches the current ball's color AND the top of the radius stack matches the current ball's radius.
// If both conditions are true, it means we have a consecutive pair with the same color and radius. In this case, we pop elements from both stacks, effectively removing the pair.
// If the stacks are empty or the current ball does not form a removable pair with the top elements of the stacks, we push the current ball's color onto the color stack and its radius onto the radius stack.
// After iterating through all balls, the number of elements remaining in either stack (they will always have the same size) will be the number of balls left.

// Time Complexity:
// O(N), where N is the number of balls (length of the color or radius array).
// We iterate through the arrays once, and each push or pop operation on a stack takes O(1) time.

// Space Complexity:
// O(N) in the worst case.
// In the worst-case scenario (e.g., all balls are unique), all elements might be pushed onto the stacks.

// Optimal Solution:
class Solution {
    public int findLength(int[] color, int[] radius) {
        Stack<Integer> colorStack = new Stack<>();
        Stack<Integer> radiusStack = new Stack<>();
        for (int i = 0; i < color.length; i++) {
            if (!colorStack.isEmpty() && colorStack.peek() == color[i] && radiusStack.peek() == radius[i]) {
                colorStack.pop();
                radiusStack.pop();
            } else {
                colorStack.push(color[i]);
                radiusStack.push(radius[i]);
            }
        }
        return colorStack.size();
    }
}