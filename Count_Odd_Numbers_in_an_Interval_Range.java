/**
 * Problem Statement: Count Odd Numbers in an Interval Range
 * --------------------------------------------------------
 * Given two non-negative integers 'low' and 'high' (inclusive), 
 * return the count of odd numbers between 'low' and 'high'.
 *
 * Constraints:
 * 0 <= low <= high <= 10^9
 */
/**
     * Optimal Solution: Mathematical Formula (O(1) Arithmetic)
     * --------------------------------------------------------
     * The odd numbers occur every two numbers. In any continuous range, the count of 
     * odd and even numbers is nearly equal.
     * The total count of integers in the range [low, high] is `high - low + 1`.
     *
     * * General Formula:
     * The count of odd numbers in the range [1, N] is `(N + 1) / 2`.
     * The count of odd numbers in [low, high] can be derived from:
     * Count[low, high] = Count[1, high] - Count[1, low - 1]
     *
     * * Simplified Case Analysis (Faster O(1) Solution):
     * The total length of the interval is `length = high - low + 1`.
     *
     * 1. **If both 'low' and 'high' are EVEN:**
     * The number of odd numbers is half the length: `length / 2`.
     * Example: [8, 10] -> length=3. Odds are [9]. 3/2 = 1 (integer division).
     *
     * 2. **If at least one of 'low' or 'high' is ODD:**
     * The number of odd numbers is half the length plus one: `length / 2 + 1`.
     * This happens because the total length is odd (if one is odd, one is even, length is even, this rule fails) 
     * OR the total length is even but both ends are odd.
     * * * **A single, robust formula:**
     * * Calculate the count of odd numbers in the total range `[0, high]`: `(high + 1) / 2`.
     * * Calculate the count of odd numbers **before** the range `[0, low - 1]`: `(low) / 2`.
     * * The difference is NOT correct due to the inclusive nature and how integer division works.
     * * * **The most clean and mathematically correct O(1) approach:**
     * * Start by finding the number of odds in the total length `(high - low + 1) / 2`.
     * * Add 1 *only if* the range starts or ends with an odd number.
     * * * **Final Optimal Formula:**
     * * `count = (high - low) / 2` (The guaranteed pairs of odd/even)
     * * If `low` is odd OR `high` is odd, we have one extra odd number included in the count.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(1) (Constant Time)
 * - The solution involves only a few constant-time arithmetic operations 
 * (subtraction, division, modulus, comparison, addition). The size of the input 
 * numbers (up to 10^9) does not affect the number of operations.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - The algorithm uses only a single integer variable (`count`) for calculation.
 */
// Code - 
