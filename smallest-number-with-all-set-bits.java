/**
 * Problem Statement: Smallest Number With All Set Bits
 * ----------------------------------------------------
 * You are given a positive integer 'n'.
 * Return the smallest number 'x' (x >= n) such that the binary representation of 'x' 
 * consists only of set bits (i.e., 'x' is of the form 2^k - 1).
 *
 * Constraints: 1 <= n <= 1000
 *//**
     * Optimal Solution: Bit Manipulation
     * ----------------------------------
     * The required numbers (with all set bits) are sequential powers of 2 minus 1:
     * 1 (2^1 - 1), 3 (2^2 - 1), 7 (2^3 - 1), 15 (2^4 - 1), etc.
     * The goal is to find the smallest number x from this sequence such that x >= n.
     *
     * This is equivalent to finding the smallest power of 2, P = 2^k, such that 
     * P - 1 >= n, or P > n.
     * * The strategy is to find the smallest power of two P that is strictly greater than n.
     * The answer will then be P - 1.
     *
     * 1. Find H: The value of the Most Significant Bit (MSB) in 'n'.
     * 2. Calculate P: The next power of two after H (P = H * 2). P is guaranteed 
     * to be the smallest power of 2 strictly greater than n.
     * 3. Return P - 1.
     *
     * Example: n = 10 (1010)
     * 1. H = Integer.highestOneBit(10) = 8 (1000)
     * 2. P = 8 << 1 = 16
     * 3. Result = 16 - 1 = 15 (1111), which is the correct answer.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(1) (Constant Time)
 * - The time complexity is dominated by the built-in bit manipulation function 
 * `Integer.highestOneBit()`, which runs in constant time.
 * - Alternatively, the iterative approach is also O(1) because the loop runs 
 * a maximum of 10 times since the constraint n <= 1000 (and 2^10 - 1 = 1023).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - The algorithm uses only a few integer variables.
 */
// Optimal Solution in Java -

class Solution {
    public int smallestNumber(int n)
     {
        int x = 1;
        while (x < n)
         {
            x = (x << 1) | 1;  
        }
        return x;
    }
}
