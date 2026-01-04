/**
 * PROBLEM STATEMENT: 1390. Four Divisors
 * --------------------------------------------------------------------------------
 * Given an integer array nums, return the sum of divisors of the integers in that 
 * array that have exactly four divisors. If there is no such integer in the array, 
 * return 0.
 * * Example 1:
 * Input: nums = [21,4,7] -> Output: 32
 * Explanation: 
 * 21 has 4 divisors: 1, 3, 7, 21. Sum = 32.
 * 4 has 3 divisors: 1, 2, 4.
 * 7 has 2 divisors: 1, 7.
 * * Example 2:
 * Input: nums = [21,21] -> Output: 64
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Square Root Factorization
 * --------------------------------------------------------------------------------
 * To find the divisors of a number 'n', we only need to iterate up to the square 
 * root of 'n'. For every divisor 'd' found where d * d != n, there is a 
 * corresponding divisor 'n/d'.
 * * * Algorithm:
 * 1. Iterate through each number in the input array.
 * 2. For each number, find its divisors by iterating from 1 to sqrt(num).
 * 3. Track the count of divisors and their running sum.
 * 4. If at any point the count exceeds 4, stop checking that number (early exit).
 * 5. After the inner loop, if the count is exactly 4, add the sum to the total answer.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * sqrt(M))
 * - N is the number of elements in the array (up to 10^4).
 * - M is the maximum value in the array (up to 10^5).
 * - For each number, we perform at most sqrt(10^5) ≈ 316 iterations.
 * * Space Complexity: O(1)
 * - We only use a few variables to store counts and sums.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java- 
class Solution {
    public int sumFourDivisors(int[] nums) {
        int ans = 0;

        for (int num : nums) {
            int count = 0;
            int sum = 0;

            for (int i = 1; i * i <= num; i++) {
             if (num % i == 0)
              {
                int d1 = i;
                int d2 = num / i;

                if (d1 == d2) {
                 count++;
                 sum += d1;
            } 
            else {
                 count += 2;
                 sum += d1 + d2;
             }
         }
          if (count > 4) break;
         }
          if (count == 4) {
           ans += sum;
            }
        }
         return ans;
    }
}
