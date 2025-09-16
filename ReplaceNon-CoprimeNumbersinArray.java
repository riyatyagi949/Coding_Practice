/**
 * Problem Statement:
 * Given an array of integers `nums`, repeatedly find two adjacent non-coprime numbers, delete them, and replace them with their LCM.
 * This process continues until no such pair is found. The goal is to return the final modified array.
 * Two numbers `x` and `y` are non-coprime if their Greatest Common Divisor (GCD) is greater than 1.
 *
 * Optimal Approach:
 * The problem involves repeatedly modifying the array based on adjacent elements. The crucial detail is that the order of operations doesn't
 * affect the final result. This suggests that we can process the numbers sequentially. A stack is an ideal data structure for this kind of problem.
 *
 * We can iterate through the input array `nums` and process each number. We maintain a result list (which can act as a stack).
 * For each number `curr` from `nums`:
 * 1. Push `curr` onto the stack.
 * 2. Check the top two elements of the stack. Let them be `a` (top) and `b` (second from top).
 * 3. While `a` and `b` are non-coprime (i.e., `gcd(a, b) > 1`):
 * - Pop both `a` and `b`.
 * - Calculate their LCM. The formula for LCM is `(a * b) / gcd(a, b)`.
 * - Push the LCM back onto the stack.
 * - Repeat the check with the new top two elements.
 *
 * This process ensures that by the time we move to the next number from `nums`, the stack is in a "stable" state where its top two elements are coprime.
 * When we add a new number, it might become non-coprime with the current top, triggering the merge process again. This continues until all adjacent non-coprime
 * numbers on the stack have been resolved.
 *
 * After iterating through all numbers in `nums`, the elements remaining in the stack form the final array.
 *
 * Helper functions needed:
 * - `gcd(a, b)`: A function to compute the Greatest Common Divisor of two numbers. The Euclidean algorithm is an efficient way to do this.
 * - `lcm(a, b)`: A function to compute the Least Common Multiple. This can be derived from the GCD: `(a * b) / gcd(a, b)`.
 *
 * Time Complexity: O(N * log(max_val)).
 * - Each number from `nums` is pushed onto the stack once.
 * - A number might be popped and re-pushed multiple times. A crucial observation is that each merge operation (`lcm` calculation) reduces the number of elements
 * on the stack by one. Since we start with `N` elements and can add up to `N` more, the total number of pushes/pops is proportional to `N`.
 * - The `gcd` and `lcm` calculations for numbers up to `10^8` take logarithmic time, `O(log(min(a, b)))`.
 * - The total complexity is roughly `O(N * log(max_val))` where `max_val` is the maximum possible value of the numbers in the array, which is `10^8`.
 *
 * Space Complexity: O(N) to store the elements in the stack, where `N` is the length of the input array.
 */
// Optimal Solution in Java - 
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        Stack<Integer> stack = new Stack<>();

        for (int curr : nums) {
            while (!stack.isEmpty()) {
                int top = stack.peek();
                int commonDivisor = gcd(top, curr);

                if (commonDivisor == 1) {
                    // Coprime, break the inner loop and push the current number.
                    break;
                }

                // Non-coprime, pop the top and replace with LCM.
                stack.pop();
                
                // Be careful with integer overflow for multiplication, use long for calculation.
                long lcm = ((long) top * curr) / commonDivisor;
                
                // The problem guarantees the final value fits in an integer, so we can cast it.
                curr = (int) lcm;
            }
            stack.push(curr);
        }

        return new ArrayList<>(stack);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}