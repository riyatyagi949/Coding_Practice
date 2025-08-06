// Problem Statement:
// Given a string s in Roman number format, your task is to convert it to an integer.
// Note: I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000.
// Constraints: 1 <= roman number <= 3999, s[i] belongs to [I, V, X, L, C, D, M].
//
// Example 1:
// Input: s = "IX"
// Output: 9
//
// Example 2:
// Input: s = "XL"
// Output: 40
//
// Example 3:
// Input: s = "MCMIV"
// Output: 1904
//
// Approach:
// The key to converting Roman numerals to integers is understanding the subtractive notation.
// Roman numerals are typically read from left to right, adding the value of each symbol.
// However, if a symbol of smaller value appears before a symbol of larger value, it indicates a subtraction.
// For example, "IV" is 4 (5 - 1), and "IX" is 9 (10 - 1). "VI" is 6 (5 + 1).
//
// A simple and efficient approach is to iterate through the Roman numeral string from right to left.
// We can use a map or a switch statement to get the integer value of each Roman symbol.
// We start with the value of the last character.
// Then, for each character from right to left, we compare its value with the value of the character to its right.
// - If the current character's value is greater than or equal to the previous character's value, we add it to our total.
// - If the current character's value is less than the previous character's value, it's a subtractive case, so we subtract its value from our total.
//
// For example, with "MCMIV":
// 1. Start from the rightmost character 'V'. Total = 5.
// 2. Move to 'I'. Value of 'I' is 1, which is less than 'V' (5). So, Total = 5 - 1 = 4.
// 3. Move to 'M'. Value of 'M' is 1000, which is greater than 'I' (1). So, Total = 4 + 1000 = 1004.
// 4. Move to 'C'. Value of 'C' is 100, which is less than 'M' (1000). So, Total = 1004 - 100 = 904.
// 5. Move to 'M'. Value of 'M' is 1000, which is greater than 'C' (100). So, Total = 904 + 1000 = 1904.
//
// This logic works because we are always comparing adjacent values.
//
// Time Complexity: O(n), where n is the length of the Roman numeral string, as we iterate through the string once.
// Space Complexity: O(1), as we only use a constant amount of extra space for the map/switch.

import java.util.HashMap;
import java.util.Map;

