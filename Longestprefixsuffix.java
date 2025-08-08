    /**
     * Problem Statement:
     * Given a string `s`, of lowercase English alphabets, find the length of the longest proper prefix which is also a suffix.
     * Note: A proper prefix is a prefix that is not the entire string itself. A proper suffix is a suffix that is not the entire string itself.
     * The prefix and suffix can be overlapping.
     * * Approach:
     * This problem can be efficiently solved using the Knuth-Morris-Pratt (KMP) algorithm's preprocessing step. The core idea of KMP is to build a "Longest Proper Prefix which is also a Suffix" (LPS) array for a given string. This LPS array, often called `lps` or `pi`, at index `i`, stores the length of the longest proper prefix of the substring `s[0...i]` that is also a suffix of that substring.
     * The length of the longest proper prefix of the entire string `s` that is also a suffix is simply the last element of this LPS array.
     * The algorithm to build the LPS array is as follows:
     * 1. Initialize an array `lps` of the same size as the string `s`.
     * 2. Initialize a length variable `len` to 0, which will track the length of the previous longest proper prefix that is also a suffix.
     * 3. Iterate through the string `s` from index 1 to the end.
     * 4. Inside the loop, if `s.charAt(i) == s.charAt(len)`, it means we have found a longer prefix-suffix match. We increment `len` and set `lps[i] = len`.
     * 5. If `s.charAt(i) != s.charAt(len)`:
     * - If `len` is not 0, we can't extend the current prefix-suffix. We must look for a shorter prefix that is also a suffix. The length of the next potential shorter prefix-suffix is given by `lps[len - 1]`. So, we update `len = lps[len - 1]`. We don't increment `i` yet, as we need to re-evaluate the character `s.charAt(i)` with the new `len`.
     * - If `len` is 0, it means there's no prefix-suffix match. We set `lps[i] = 0` and move on to the next character by incrementing `i`.
     * After the loop finishes, the length of the longest proper prefix of `s` that is also a suffix is `lps[s.length() - 1]`.
     * * Time Complexity:
     * O(N), where N is the length of the string `s`. The algorithm iterates through the string once to build the LPS array. The `while` loop inside the `for` loop doesn't make it O(N^2) because the total number of times `len` is decremented is bounded by the number of times it's incremented, which is at most N.
     * * Space Complexity:
     * O(N) to store the `lps` array.
     * * Optimal Solution:
     */


    class Solution {
        int lps(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;

        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps[n - 1];
    }
}