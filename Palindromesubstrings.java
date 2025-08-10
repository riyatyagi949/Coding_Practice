/*
Problem Statement: Palindrome Substrings
Given a string s, count all palindromic substrings present in the string. The length of the palindromic substring must be greater than or equal to 2.

Approach:
A straightforward approach to solve this problem is to iterate through each character of the string and treat it as the potential center of a palindrome. A palindrome can have either an odd length (a single character center) or an even length (two characters forming the center).

For each character at index `i`, we can expand outwards to find all odd-length palindromes centered at `i`. We use two pointers, `left` and `right`, initialized to `i`. We expand as long as `left` is a valid index, `right` is a valid index, and the characters `s.charAt(left)` and `s.charAt(right)` are equal. Each time this condition holds, we have found a new palindrome, and we increment our count.

Similarly, we can check for all even-length palindromes. For each character at index `i`, the potential center is between `s.charAt(i)` and `s.charAt(i+1)`. We initialize `left` to `i` and `right` to `i+1`. We then expand outwards, checking the same conditions as with odd-length palindromes.

We must remember to only count palindromes with a length greater than or equal to 2. In our expansion logic, the first check already finds a palindrome of length 1 (for odd) or 2 (for even). We only count if the length is at least 2.

The overall algorithm is:
1. Initialize a count variable to 0.
2. Iterate through the string from `i = 0` to `s.length() - 1`.
3. For each `i`, find all odd-length palindromes centered at `i`:
   - Set `left = i`, `right = i`.
   - While `left >= 0`, `right < s.length()`, and `s.charAt(left) == s.charAt(right)`:
     - If `right - left + 1 >= 2`, increment the count.
     - Decrement `left`, increment `right`.
4. For each `i`, find all even-length palindromes centered between `i` and `i+1`:
   - Set `left = i`, `right = i + 1`.
   - While `left >= 0`, `right < s.length()`, and `s.charAt(left) == s.charAt(right)`:
     - If `right - left + 1 >= 2`, increment the count.
     - Decrement `left`, increment `right`.
5. Return the final count.

Time Complexity:
The outer loop runs `s.length()` times. Inside the loop, the expansion for both odd and even palindromes takes at most `s.length()` time.
So, the total time complexity is $O(n^2)$, where $n$ is the length of the string. Given the constraint $s.size() \leq 5 \times 10^3$, an $O(n^2)$ solution is efficient enough.

Space Complexity:
The solution uses only a few variables for indices and the count, so the space complexity is $O(1)$.
*/
class Solution {
    public int countPS(String s) {
        int n = s.length();
        int count = 0;

        for (int center = 0; center < 2 * n - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 >= 2) 
                count++;
                left--;
                right++;
            }
        }
        return count;
    }
}
