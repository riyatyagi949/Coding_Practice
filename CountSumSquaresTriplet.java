/**
 * Problem Statement: Count Square Sum Triples
 * -------------------------------------------
 * A square triple (a, b, c) is a set of three integers where a^2 + b^2 = c^2.
 * Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.
 *
 * Constraints:
 * 1 <= n <= 250
 */
/**
     * Optimal Solution: Brute-Force with Optimization (O(N^2))
     * --------------------------------------------------------
     * Since the constraint N is very small (N <= 250), an O(N^2) solution is highly efficient
     * and the simplest to implement. We iterate through all possible pairs of 'a' and 'b' 
     * within the range [1, n] and check if a valid integer 'c' also exists within the range [1, n] 
     * such that a^2 + b^2 = c^2.
     *
     * * Algorithm Steps:
     * 1. Initialize a counter `count` to 0.
     * 2. Iterate through all possible values of `a` from 1 to n.
     * 3. Inside, iterate through all possible values of `b` from 1 to n.
     * 4. Calculate `c_squared = a * a + b * b`.
     * 5. Find the integer square root of `c_squared`: `c = (int)Math.sqrt(c_squared)`.
     * 6. Check Validity:
     * a. Ensure `c` is within the constraint: `c <= n`.
     * b. Ensure `c` is a perfect square root: `c * c == c_squared`.
     * 7. If both checks pass, the triple (a, b, c) is valid. Increment `count`.
     * 8. Return `count`.
     *
     * Note: This method naturally counts (3, 4, 5) and (4, 3, 5) separately, which is required 
     * by the problem statement where 'a' and 'b' are distinct variables.
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N^2), where N is the input integer.
 * - The solution uses two nested loops, both running from 1 to N.
 * - Inside the loops, operations like multiplication and square root calculation are O(1).
 * - Since N is at most 250, N^2 is 62,500, which is extremely fast.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1) auxiliary space.
 * - The solution uses only a few integer variables for loop counters and calculations.
 */
// Code -