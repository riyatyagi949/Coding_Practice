/**
 * Problem: 326. Power of Three
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 */
 /**
     * Approach:
     * A number 'n' is a power of three if and only if it is positive and the only prime factor is 3.
     * This means that if we repeatedly divide 'n' by 3, we should eventually get 1.
     * We can implement this with a loop. We first handle the edge cases where n <= 0.
     * For positive n, we can use a while loop that continues as long as n is divisible by 3 and n > 1.
     * In each iteration, we divide n by 3. If after the loop n is equal to 1, it means the original number was a power of three.
     *
     * A more optimal and elegant approach, which solves the problem without loops or recursion,
     * involves leveraging the properties of integer division and logarithms.
     * A number 'n' is a power of three if and only if it is positive and is a divisor of the largest
     * power of three that can be represented as an integer.
     * The largest possible integer value is 2^31 - 1. We can find the largest power of three less than this
     * value. 3^19 = 1162261467, and 3^20 is greater than the max integer value.
     * So, if n > 0 and 1162261467 % n == 0, then n must be a power of three.
     * This is because any number that is a power of three will be a divisor of a larger power of three.
     */
//    Time Complexity: O(log base 3 of n)
//    Space Complexity: O(1)
 
 class Solution {
          public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

       return n == 1;
      } 
 }


//  * Optimal Solution (without loops/recursion):
//  * The optimal solution leverages the fact that the largest power of three that can be stored in a signed 32-bit integer is 3^19 = 1162261467.
//  * Any positive integer n that is a power of three must be a divisor of this number.
//  *
//  * Time Complexity: O(1)
//  * Space Complexity: O(1)
//  */

 
class SolutionOptimal {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}