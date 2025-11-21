/**
 * Problem Statement: Unique Length-3 Palindromic Subsequences
 * -----------------------------------------------------------
 * Given a string 's' consisting of lowercase English letters, return the number 
 * of unique palindromes of length three that are a subsequence of 's'.
 * A length-3 palindrome has the form "cxc", where c and x are characters.
 *
 * Constraints:
 * 3 <= s.length <= 10^5
 * s consists of only lowercase English letters ('a' through 'z').
 *//**
     * Optimal Solution: Fixed Structure and Pre-computation
     * ----------------------------------------------------
     * A length-3 palindromic subsequence is always of the form "cxc".
     * Since the alphabet is small (26 letters), we can iterate over all 
     * possible outer characters 'c' and then for each 'c', iterate over all 
     * possible middle characters 'x'.
     *
     * The condition for the palindrome "cxc" to be a subsequence of 's' is:
     * We must find three indices i, j, k such that:
     * 1. i < j < k
     * 2. s[i] = 'c'
     * 3. s[j] = 'x'
     * 4. s[k] = 'c'
     *
     * * Optimized Strategy:
     * 1. **Pre-compute First and Last Occurrence:** For every character 'c' 
     * in the alphabet ('a' to 'z'), find its first index (`first[c]`) and its 
     * last index (`last[c]`) in the string 's'.
     * 2. **Iterate Outer Character 'c':** Loop through all 26 possible outer 
     * characters ('a' to 'z').
     * 3. **Check Existence:** If a character 'c' exists (i.e., `first[c]` is 
     * less than `last[c]`), a subsequence "cxc" is possible.
     * 4. **Count Middle Characters 'x':** The middle character 'x' can be ANY 
     * character found between `first[c]` and `last[c]` (exclusive). 
     * We count the number of unique characters in the substring `s.substring(first[c] + 1, last[c])`.
     * 5. **Accumulate:** Add this count to the total number of unique palindromes.
     *
     * This approach ensures that for every valid pair of 'c' occurrences, we 
     * count the unique set of middle characters 'x' they can form.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N + A^2) where N is the length of 's' and A is the size of the alphabet (A=26).
 * - O(N) to pre-compute the `first` and `last` indices.
 * - O(A) for the outer loop (26 iterations).
 * - Inside the loop, we iterate over a substring `s.substring(first[c] + 1, last[c])`.
 * - **Crucially, the total length of all these substrings across all 26 iterations is at most N.**
 * - The work inside the loop is dominated by the linear scan of the substring, which aggregates to O(N) 
 * over the entire run. The final counting of unique middle characters is O(A).
 * - Total time complexity: O(N + A^2), which simplifies to **O(N)** since A is a small constant (26).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(A) where A is the size of the alphabet (A=26).
 * - O(A) is used for the `first`, `last`, and `seenMiddleChars` arrays, which are all fixed size 26.
 * - This is considered **O(1)** constant space since the alphabet size is constant.
 */
// Optimal Solution in Java -
