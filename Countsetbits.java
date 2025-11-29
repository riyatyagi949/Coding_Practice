/**
 * Problem Statement: Count set bits
 * ---------------------------------
 * You are given a number n. Find the total count of set bits for all numbers 
 * from 1 to n (both inclusive).
 *
 * Example:
 * Input: n = 4
 * Output: 5
 * (1: 1 set bit, 2: 1 set bit, 3: 2 set bits, 4: 1 set bit. Total: 1+1+2+1 = 5)
 *
 * Constraints:
 * 1 <= n <= 10^8
 */
/**
     * Optimal Solution: Recursive/Iterative Bit Decomposition
     * ------------------------------------------------------
     * This solution uses a recursive structure to count set bits based on the highest 
     * power of 2, P = 2^x, where P <= n. The counting is broken down into three parts:
     * * 1. **Contribution from numbers 1 to P - 1 (i.e., 1 to 2^x - 1):**
     * In the range [0, 2^x - 1], every bit position (from 0 to x-1) is set exactly 
     * half the time (2^(x-1) times).
     * Total set bits = x * 2^(x-1). (This is calculated by x * (1 << (x - 1))).
     *
     * 2. **Contribution from the MSB in the range P to n:**
     * The MSB (at position x) is set for all numbers from P (2^x) up to n.
     * Total set bits from MSB = (n - P + 1). (This is calculated by (n - (1 << x) + 1)).
     *
     * 3. **Remaining Subproblem:**
     * The problem reduces to counting the set bits in the *remaining* part of the numbers 
     * in the range [P, n]. The remaining part is equivalent to solving the problem for 
     * n' = n - P. We recursively call the function with n = n'.
     * (This is calculated by n = n - (1 << x)).
     *
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(log N), where N is the input number.
 * - The main `while` loop runs at most $\lfloor \log_2 N \rfloor + 1$ times, as in each iteration, 
 * the value of `tempN` is reduced by its largest power of 2 (i.e., its Most Significant Bit). 
 * This is analogous to iterating through the set bits of N.
 * - The `highestPowerOf2` helper function is also O(log N) in its worst case, but the `while` 
 * loop runs only once per set bit of the input 'n', maintaining the overall logarithmic complexity.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - The solution uses only a few integer variables and does not rely on any data structures 
 * whose size depends on the input N.
 */
// Code - 

class Solution {
    public static int countSetBits(int n) {
        int count = 0;
        int x;

        while (n > 0) {
            x = highestPowerOf2(n);
            
            count += x * (1 << (x - 1));
            count += (n - (1 << x) + 1);

            n = n - (1 << x);
        }
        return count;
    }
    private static int highestPowerOf2(int n) {
        int x = 0;
        while ((1 << (x + 1)) <= n) 
        x++;
        
        return x;
    }
}
