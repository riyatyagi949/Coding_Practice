/**
 * Problem Statement:
 * You are given a positive integer `num` consisting only of digits `6` and `9`.
 * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
 *
 * Example 1:
 * Input: num = 9669
 * Output: 9969
 * Explanation:
 * Changing the first digit results in 6669.
 * Changing the second digit results in 9969.
 * Changing the third digit results in 9699.
 * Changing the fourth digit results in 9666.
 * The maximum number is 9969.
 *
 * Example 2:
 * Input: num = 9996
 * Output: 9999
 * Explanation: Changing the last digit 6 to 9 results in the maximum number.
 *
 * Example 3:
 * Input: num = 9999
 * Output: 9999
 * Explanation: It is better not to apply any change.
 *
 * Constraints:
 * 1 <= num <= 10^4
 * num consists of only 6 and 9 digits.
 */
/**
     * Approach:
     * To obtain the maximum possible number, we should aim to change the most significant digit that is currently a '6' to a '9'.
     * A '9' in a more significant position contributes more to the value of the number than a '9' in a less significant position.
     * We can convert the integer to a character array or a string to easily manipulate its digits.
     * Then, we iterate through the digits from left to right (most significant to least significant).
     * The first '6' we encounter is the most significant '6'.
     * If we change this '6' to a '9', the resulting number will be the largest possible.
     * After changing the first '6' to a '9', we can convert the character array back to an integer and return it.
     * If we iterate through all the digits and do not find any '6's (meaning the number is already all '9's), we don't need to make any changes, and the original number is the maximum.
     *
     * Time Complexity: O(log10(num))
     * The time complexity is proportional to the number of digits in `num`. Converting the integer to a string and iterating through its characters takes time proportional to the number of digits.
     *
     * Space Complexity: O(log10(num))
     * The space complexity is determined by the storage required for the string or character array representation of `num`, which is proportional to the number of digits.
     */


class Solution {
    public int maximum69Number (int num) {
        char[] digits = String.valueOf(num).toCharArray();
        
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break; 
            }
        }
        return Integer.parseInt(new String(digits));
    }
}
