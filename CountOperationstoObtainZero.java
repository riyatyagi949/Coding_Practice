/**
 * Problem Statement: Count Operations to Obtain Zero
 * --------------------------------------------------
 * You are given two non-negative integers num1 and num2.
 * In one operation, if num1 >= num2, you subtract num2 from num1; 
 * otherwise, you subtract num1 from num2.
 * The goal is to return the number of operations required to make either num1 = 0 or num2 = 0.
 *
 * Constraints: 0 <= num1, num2 <= 10^5
 *//**
     * Optimal Solution: Equivalence to Euclidean Algorithm (Division-Based)
     * ----------------------------------------------------------------------
     * The operation described:
     * If num1 >= num2, then num1 = num1 - num2.
     * If num2 > num1, then num2 = num2 - num1.
     *
     * This process is exactly the **subtraction-based Euclidean Algorithm** for finding
     * the Greatest Common Divisor (GCD). The number of operations is the number of 
     * subtraction steps until one number becomes zero.
     *
     * When one number (the larger one) is much greater than the smaller one, many
     * subtraction steps occur consecutively. For example, if (10, 2), we do 10-2, 8-2, 6-2, 4-2, 2-2.
     * This sequence of 'k' subtractions is equivalent to a single modulo operation:
     * num1 = num1 - k * num2  -> num1 = num1 % num2.
     * k is the number of operations, where k = num1 / num2 (integer division).
     *
     * * Algorithm Steps (Optimized):
     * 1. Initialize `operations = 0`.
     * 2. Loop while both `num1` and `num2` are greater than 0:
     * a. If `num1 >= num2`:
     * i. The number of subtractions required is `num1 / num2`. Add this to `operations`.
     * ii. Update `num1` using the modulo operation: `num1 = num1 % num2`.
     * b. Else (`num2 > num1`):
     * i. The number of subtractions required is `num2 / num1`. Add this to `operations`.
     * ii. Update `num2` using the modulo operation: `num2 = num2 % num1`.
     * 3. Return `operations`.
     *
     * * Edge Cases:
     * - If `num1` or `num2` is initially 0, the loop condition fails, and 0 is returned, which is correct.
     * - If `num1 == num2`, the operation count is 1 (e.g., 10-10=0). The division `10/10` yields 1, and the modulo `10%10` yields 0. This is handled correctly.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(log(min(num1, num2)))
 * - Since the subtraction process is optimized by using the modulo operator, the algorithm 
 * converges to the structure of the **Division-based Euclidean Algorithm**.
 * - The number of steps in the Euclidean algorithm is logarithmic with respect to the 
 * smaller of the two input numbers.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1)
 * - The algorithm uses a constant amount of extra space (a few integer variables) regardless 
 * of the input values.
 */
// Optimal Solution in Java -

class Solution {
    public int countOperations(int num1, int num2)
     {
        if (num1 == 0 || num2 == 0)
         {
            return 0;
        }
        int operations = 0;
        
        while (num1 > 0 && num2 > 0)
         {
            if (num1 >= num2)
             {
                int count = num1 / num2;
                operations += count;
                num1 %= num2;
            } 
            else 
            { 
                int count = num2 / num1;
                operations += count;
                num2 %= num1;
            }
        }
        return operations;
    }
}

