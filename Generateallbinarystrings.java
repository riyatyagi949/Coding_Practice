/**
 * Problem Statement:
 * Given an integer 'n', generate all possible binary strings of length 'n'.
 * The resulting strings should be returned in lexicographically ascending order.
 *
 * Optimal Approach (Using Backtracking/Recursion):
 * The problem requires generating all combinations of '0' and '1' for 'n' positions. This is a classic combinatorial problem best solved using recursion or backtracking.
 *
 * The idea is to build the binary string character by character from the first position (index 0) up to the last position (index n-1).
 *
 * 1. Base Case: If the current string's length (`curr.length()`) reaches `n`, it means a complete binary string has been formed. We add this string to the result list and return.
 *
 * 2. Recursive Step: For the current position, we have two choices: '0' and '1'.
 * - Choice 1 ('0'): Append '0' to the current string and make a recursive call to generate the rest of the string.
 * - Choice 2 ('1'): Append '1' to the current string and make a recursive call.
 *
 * **Ensuring Ascending Order:**
 * By always exploring the '0' choice before the '1' choice in the recursive step, we naturally generate the strings in lexicographically ascending order.
 * The strings starting with '0' will be generated before those starting with '1'. Among strings starting with '0', those with '0' at the next position will be generated before those with '1', and so on.
 *
 * Initial Call: `generate(n, "", result)`
 *
 * Time Complexity: O(2^n * n).
 * - There are $2^n$ possible binary strings of length $n$.
 * - For each of the $2^n$ strings, it takes $O(n)$ time to construct the string (e.g., string concatenation or StringBuilder operations) and add it to the result list.
 *
 * Space Complexity: O(2^n * n).
 * - O(2^n * n) space is needed to store the $2^n$ generated strings, each of length $n$.
 * - O(n) space is also used by the recursion stack for the depth of the recursive calls (the length of the string).
 */
// Optimal Solution in Java - 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

