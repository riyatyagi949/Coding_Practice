/**
 * Problem Statement:
 * Given a string `s` containing only '(' and ')', find the minimum number of parentheses to add to make it a valid parentheses string.
 * A valid parentheses string has properly matched and nested parentheses.
 *
 * Optimal Approach:
 * This problem can be solved with a single pass through the string using a simple counter. The core idea is to track the balance of open parentheses.
 * A valid parentheses string can be thought of as having a balance of 0 at the end, and the balance never drops below 0 during the traversal.
 *
 * We can use a `balance` variable to count the number of currently open, but unmatched, parentheses. We also need a counter for the total insertions needed.
 *
 * Algorithm:
 * 1. Initialize `insertions` (total parentheses to add) to 0.
 * 2. Initialize `balance` (count of open, unmatched parentheses) to 0.
 * 3. Iterate through the string `s` character by character:
 * a. If the character is '(':
 * - Increment `balance`. This signifies an open parenthesis that needs a matching closing one.
 * b. If the character is ')':
 * - If `balance` is greater than 0, it means we have an open parenthesis to match.
 * - Decrement `balance`. This pair is now valid.
 * - If `balance` is 0, it means this ')' has no preceding open parenthesis to match.
 * - This is an unmatched ')' that must be fixed by adding an opening parenthesis.
 * - Increment `insertions`.
 *
 * After the loop finishes, the `balance` variable will hold the number of open parentheses that were never closed. For each of these, we need to add a closing parenthesis.
 *
 * 4. Add the final `balance` to `insertions`.
 * 5. Return `insertions`.
 *
 * This approach works because it correctly identifies and counts two types of mismatches:
 * 1. A closing parenthesis `)` that appears without a preceding open parenthesis.
 * 2. An opening parenthesis `(` that is never closed at the end of the string.
 *
 * By handling these two cases, we find the minimum number of insertions.
 *
 * Time Complexity: O(n), where `n` is the length of the string. We perform a single pass through the string.
 * Space Complexity: O(1), as we only use a few integer variables for counters, regardless of the string length.
 */
class Solution {
    public int minAddToMakeValid(String s) {
        int insertions = 0;
        int balance = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } 
            else { // c == ')'
                if (balance > 0) {
                    balance--;
                } else {
                    insertions++;
                }
            }
        }
        
        return insertions + balance;
    }
}