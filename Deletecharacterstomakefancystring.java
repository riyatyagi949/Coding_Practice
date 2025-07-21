/*
Problem Statement:
A fancy string is a string where no three consecutive characters are equal.
Given a string s, delete the minimum possible number of characters from s to make it fancy.
Return the final string after the deletion. It can be shown that the answer will always be unique.

Example 1:
Input: s = "leeetcode"
Output: "leetcode"
Explanation:
Remove an 'e' from the first group of 'e's to create "leetcode".
No three consecutive characters are equal, so return "leetcode".

Example 2:
Input: s = "aaabaaaa"
Output: "aabaa"
Explanation:
Remove an 'a' from the first group of 'a's to create "aabaaaa".
Remove two 'a's from the second group of 'a's to create "aabaa".
No three consecutive characters are equal, so return "aabaa".

Example 3:
Input: s = "aab"
Output: "aab"
Explanation: No three consecutive characters are equal, so return "aab".

Constraints:
1 <= s.length <= 10^5
s consists only of lowercase English letters.

Approach:
We can build the fancy string character by character. We iterate through the input string `s`.
For each character, we decide whether to append it to our result string.
A character can be appended if appending it does not violate the "no three consecutive characters are equal" rule.

We can maintain a count of consecutive identical characters at the end of our current result string.
Let's use a StringBuilder to efficiently build the result string.
Initialize an empty StringBuilder `result`.
Iterate through the input string `s` from left to right.
For each character `c` at index `i`:
1. If `result` has less than 2 characters, we can always append `c`.
2. If `result` has 2 or more characters, check the last two characters.
   If `result.charAt(result.length() - 1)` is equal to `result.charAt(result.length() - 2)` and both are equal to `c`, then appending `c` would create three consecutive identical characters. In this case, we skip `c` (do not append it).
   Otherwise, we can safely append `c`.

After iterating through all characters in `s`, the `result` StringBuilder will contain the fancy string.

Let's refine the logic for checking consecutive characters.
Instead of directly checking `result.charAt(result.length() - 1)` and `result.charAt(result.length() - 2)`, we can maintain a `count` of consecutive occurrences of the current character and the `previous_char` added to the `result`.

Revised Approach:
Initialize an empty StringBuilder `fancyString`.
Initialize `count = 0`.
Initialize `lastChar = ' '` (or any character not in 'a'-'z').

Iterate through the input string `s` character by character:
For each `currentChar` in `s`:
1. If `currentChar` is the same as `lastChar`:
   Increment `count`.
2. Else (`currentChar` is different from `lastChar`):
   Reset `count = 1`.
   Update `lastChar = currentChar`.

3. If `count < 3`:
   Append `currentChar` to `fancyString`.
   Update `lastChar` if it was just changed, or simply it's already updated.
   (No, `lastChar` should always be the last char added to `fancyString`. Let's re-evaluate this part.)

Simpler approach:
Iterate through the input string `s`. Maintain the `lastTwoChars` added to the fancy string.

Let's keep track of the count of the current character being processed from the input string `s`.
Initialize an empty `StringBuilder result`.
If `s` is empty or has only 1 or 2 characters, it's already fancy, return `s`.

Initialize `result` with the first character of `s`.
Initialize `consecutiveCount = 1`.
For `i` from 1 to `s.length() - 1`:
  `currentChar = s.charAt(i)`
  `previousCharInResult = result.charAt(result.length() - 1)`

  If `currentChar == previousCharInResult`:
    `consecutiveCount++`
  Else:
    `consecutiveCount = 1`

  If `consecutiveCount < 3`:
    Append `currentChar` to `result`.

This approach seems correct. `consecutiveCount` tracks the consecutive occurrences of the `currentChar` *ending at the current `result` length*.

Example walkthrough: s = "leeetcode"
1. `result = "l"`, `consecutiveCount = 1`
2. `i = 1`, `s[1] = 'e'`. `previousCharInResult = 'l'`. Different.
   `consecutiveCount = 1`.
   `1 < 3` is true. Append 'e'. `result = "le"`.
3. `i = 2`, `s[2] = 'e'`. `previousCharInResult = 'e'`. Same.
   `consecutiveCount = 2`.
   `2 < 3` is true. Append 'e'. `result = "lee"`.
4. `i = 3`, `s[3] = 'e'`. `previousCharInResult = 'e'`. Same.
   `consecutiveCount = 3`.
   `3 < 3` is false. Do NOT append 'e'. `result` remains "lee".
5. `i = 4`, `s[4] = 't'`. `previousCharInResult = 'e'`. Different.
   `consecutiveCount = 1`.
   `1 < 3` is true. Append 't'. `result = "leet"`.
6. ... and so on. This works.

Time Complexity:
We iterate through the input string `s` once. Appending characters to a `StringBuilder` takes amortized O(1) time.
Overall, the time complexity is O(N), where N is the length of the input string `s`.

Space Complexity:
We use a `StringBuilder` to store the result string. In the worst case (e.g., "aab", "ab", "abc"), no characters are deleted, and the result string will have the same length as the input string.
So, the space complexity is O(N), where N is the length of the input string `s`.

Constraints check:
1 <= s.length <= 10^5. O(N) time and O(N) space are well within limits.
s consists only of lowercase English letters. This is handled by direct character comparison.
*/
class Solution {
    public String makeFancyString(String s) {
        if (s == null || s.length() < 3) {
            return s;
        }

        StringBuilder fancyString = new StringBuilder();
        // Add the first two characters if they exist, as they can never violate the rule by themselves.
        fancyString.append(s.charAt(0));
        int consecutiveCount = 1;

        for (int i = 1; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            char prevCharInFancyString = fancyString.charAt(fancyString.length() - 1);

            if (currentChar == prevCharInFancyString) {
                consecutiveCount++;
            } else {
                consecutiveCount = 1;
            }

            if (consecutiveCount < 3) {
                fancyString.append(currentChar);
            }
        }

        return fancyString.toString();
    }
}