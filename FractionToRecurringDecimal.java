/**
 * Problem Statement:
 * Given two integers, `numerator` and `denominator`, return their fraction in string format. If the fractional part is repeating,
 * enclose the repeating part in parentheses.
 *
 * Optimal Approach:
 * This problem is a simulation of the manual long division process. The key challenge is detecting a repeating decimal.
 * A repeating decimal occurs when a remainder repeats during the division process.
 *
 * We can use a `HashMap` to store the remainders and the position where they first occurred in the result string.
 * This allows us to detect a cycle. If we encounter a remainder that we have seen before, it means the sequence of digits
 * will start repeating from that point.
 *
 * The algorithm proceeds as follows:
 * 1. Handle edge cases and signs:
 * - If the numerator is 0, the result is "0".
 * - Determine the sign of the result. If one of the numbers is negative (but not both), the result is negative. Use a `StringBuilder` to build the string.
 * - Convert `numerator` and `denominator` to `long` to avoid integer overflow issues, especially with `Integer.MIN_VALUE`.
 * 2. Calculate the integer part:
 * - The integer part is `num / den`. Append this to the `StringBuilder`.
 * - The new remainder is `num % den`. If the remainder is 0, the division is exact, so return the current string.
 * 3. Handle the fractional part:
 * - Append a decimal point "." to the `StringBuilder`.
 * - Use a `HashMap` to store remainders as keys and their corresponding position in the `StringBuilder` as values.
 * - Start a loop:
 * - If the current remainder is already in the map, a repeating cycle is found.
 * - Insert a "(" at the position stored in the map and append a ")" at the end of the string. Break the loop.
 * - Otherwise, add the current remainder and the current length of the `StringBuilder` to the map.
 * - Multiply the remainder by 10 to simulate the next step of long division.
 * - Append the next digit of the quotient (`(rem * 10) / den`) to the `StringBuilder`.
 * - Update the remainder: `(rem * 10) % den`.
 * - The loop continues until the remainder becomes 0 (terminating decimal) or a repeating remainder is found.
 * 4. Return the final string.
 *
 * This method efficiently handles all cases, including non-repeating, repeating, and large numbers, by using `long` for calculations and a `HashMap` for cycle detection.
 *
 * Time Complexity: O(log(denominator)). The length of the repeating part of a fraction is at most `denominator - 1`.
 * Each step in the division corresponds to one remainder, and since there are at most `denominator - 1` unique non-zero remainders, the loop terminates within that many steps.
 *
 * Space Complexity: O(log(denominator)) to store the remainders in the HashMap. In the worst case, we might store up to `denominator - 1` remainders.
 */
// Optimal Solution in Java - 

import java.util.HashMap;
import java.util.Map;

