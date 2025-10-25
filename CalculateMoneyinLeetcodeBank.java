/**
 * Problem Statement: Calculate Money in Leetcode Bank
 * ---------------------------------------------------
 * Hercy saves money over 'n' days.
 * - Day 1 (Monday): $1.
 * - Tuesday to Sunday (Days 2-7): $1 more than the previous day.
 * - Every subsequent Monday: $1 more than the previous Monday.
 * The pattern repeats weekly. Given 'n', return the total amount of money 
 * saved by the end of the nth day.
 *
 * Example:
 * Input: n = 10
 * Output: 37
 * Explanation: Week 1 (Days 1-7): (1+2+3+4+5+6+7) = 28
 * Week 2 (Days 8-10): Monday($2) + Tuesday($3) + Wednesday($4) = 9
 * Total = 28 + 9 = 37
 *
 * Constraints:
 * 1 <= n <= 1000
 */
/**
     * Optimal Solution: Mathematical Calculation using Arithmetic Series
     * ------------------------------------------------------------------
     * The problem breaks down into:
     * 1. A number of complete weeks.
     * 2. A number of remaining days in a partial week.
     * * * Week Pattern:
     * - Week 1 starts with $1 on Monday. Sum = 1 + ... + 7 = 28.
     * - Week 2 starts with $2 on Monday. Sum = 2 + ... + 8 = 35.
     * - Week 'i' starts with $i on Monday. Sum = i + (i+1) + ... + (i+6).
     * - The sum of Week 'i' is (Sum of 1..7) + 7 * (i-1) = 28 + 7 * (i-1).
     * * * Calculations:
     * - Number of complete weeks: `weeks = n / 7`.
     * - Number of remaining days: `remainingDays = n % 7`.
     * - Starting deposit for the first day of the *next* (partial) week: `startDeposit = weeks + 1`.
     * * 1. **Sum of Complete Weeks:**
     * We need to sum the total money for weeks 1 through `weeks`.
     * Week 1 sum = 28
     * Week 2 sum = 35
     * ...
     * Week `w` sum = 28 + 7*(w-1)
     * Total for complete weeks is the sum of an arithmetic series where:
     * - Start term (a): Sum of Week 1 = 28
     * - End term (b): Sum of Week `weeks` = 28 + 7 * (weeks - 1)
     * - Number of terms (count): `weeks`
     * * 2. **Sum of Remaining Days:**
     * The partial week starts on Monday with a deposit of `startDeposit` (`weeks + 1`).
     * The sum is the total of `remainingDays` starting from `startDeposit`.
     * Sum = `startDeposit` + (`startDeposit` + 1) + ... + (`startDeposit` + `remainingDays` - 1)
     */
  /**
 * Time Complexity Analysis:
 * -------------------------
 * O(1) (Constant Time)
 * - The solution uses only basic arithmetic operations and the arithmetic series formula.
 * - The time taken does not depend on the input size 'n', only on a fixed number of operations.
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - The solution uses a fixed, small amount of memory for variables regardless of the input size 'n'.
 */
//  Optimal Solution in Java -