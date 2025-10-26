// Problem Description
// You are given an array of four integers, cards, with each number in the range [1, 9]. The goal is to determine if you can use these four numbers, along with the standard operators +, -, *, /, and parentheses, to form a mathematical expression that evaluates to 24.

// Important rules:

// Division (/) is real division, not integer division.

// The subtraction operator cannot be used as a unary operator (e.g., -1).

// Numbers cannot be concatenated (e.g., 1 and 2 cannot form 12).

// Approach
// The problem involves exploring all possible combinations of numbers, operators, and parentheses. Since we have a fixed, small number of cards (four), a backtracking or recursive depth-first search (DFS) approach is well-suited. The state of our recursion will be the current list of numbers we have to work with.

// Base Case: If the list of numbers has only one element, check if its value is approximately 24. We use a small epsilon value (e.g., 1e-6) for comparison because real division can introduce floating-point inaccuracies. If the single number is close to 24, we return true; otherwise, false.

// Recursive Step:

// Iterate through all pairs of numbers in the current list.

// For each pair (a and b), remove them from the list.

// Perform all six possible operations (+, -, *, /) on a and b.

// a + b

// a - b

// b - a

// a * b

// a / b (if b is not zero)

// b / a (if a is not zero)

// Add the result of each operation to a new list of numbers.

// Recursively call the function with this new, smaller list.

// If the recursive call returns true, it means we found a solution, so we can immediately return true to the caller.

// After exploring the possibilities for the pair, backtrack by restoring the original list of numbers. This is crucial for exploring other pairs.

// Optimization: To avoid redundant calculations and explore all valid permutations, we can handle commutative operations (+ and *) only once for each pair. For example, a + b is the same as b + a.

// Initial Call: Convert the input integer array cards into a list of doubles to handle real division accurately. Then, call the recursive helper function with this list.

// The total number of combinations is small. There are 4! permutations of the numbers. For each step, we pick two numbers and combine them, reducing the size of the list. This process continues until one number is left. The number of operations is also constant. This makes the recursive search feasible within time limits.

// Time and Space Complexity
// Time Complexity: The number of states is finite and small. At each step, we have n numbers. We choose 2, which is C(n, 2). For each pair, there are up to 6 operations. The number of numbers reduces with each recursive call. The complexity is roughly O(n!), which is acceptable for n=4.

// Space Complexity: The space complexity is dominated by the recursion stack, which goes as deep as the number of cards. For n=4, it's O(1).

// Optimal Solution-
class Solution {
    public boolean judgePoint24(int[] cards) {
        double[] nums = new double[cards.length];
        for (int i = 0; i < cards.length; i++) nums[i] = cards[i];
        return solve(nums);
    }

    private boolean solve(double[] nums) {
        int n = nums.length;
        if (n == 1) return Math.abs(nums[0] - 24) < 1e-6;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double[] next = new double[n - 1];
                int idx = 0;
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) next[idx++] = nums[k];
                }
                for (double val : compute(nums[i], nums[j])) {
                    next[n - 2] = val;
                    if (solve(next)) 
                    return true;
                }
            }
        }
        return false;
    }

    private double[] compute(double a, double b) {
        return new double[]{a + b, a - b, b - a, a * b, a / b, b / a};
    }
}
