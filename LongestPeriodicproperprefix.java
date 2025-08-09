// Problem Statement:
// Given a string s, find the length of the longest periodic proper prefix of s.
// A periodic proper prefix is a non-empty prefix of s (but not the whole string)
// such that repeating this prefix enough times produces a string that starts with s.
// If no such prefix exists, return -1.

// Approach:
// This problem can be solved using the Knuth-Morris-Pratt (KMP) algorithm's Longest Proper Prefix (LPS) array.
// The LPS array for a string s stores at each index i, the length of the longest proper prefix of s[0...i]
// that is also a suffix of s[0...i].
// A periodic proper prefix of a string s, of length p, implies that s is composed of one or more repetitions of this prefix.
// The length of the longest periodic proper prefix will be the length of the string minus the length of the longest repeating unit.
//
// Let's analyze the properties of the LPS array. The value at the last index of the LPS array, say lps[n-1],
// gives the length of the longest proper prefix of s that is also a suffix of s.
// Let 'len' be lps[n-1]. If s has a repeating unit, then the length of the repeating unit will be n - len.
// This is because the last 'len' characters of s are a copy of the first 'len' characters.
// This means the string s of length 'n' can be divided into a prefix of length 'n-len' and a suffix of length 'len',
// where the suffix is a copy of the prefix of the string.
//
// The condition for a periodic proper prefix of length p is that the string s of length n must be divisible by p,
// and the string s is a repetition of the prefix s[0...p-1].
// We can check for this using the LPS array. If `n % (n - lps[n-1]) == 0`, and `lps[n-1] > 0`,
// it means the string is periodic. The length of the repeating unit is `n - lps[n-1]`.
// The longest periodic proper prefix would then be the entire string length minus this repeating unit length, which is `lps[n-1]`.
// If `n % (n - lps[n-1]) != 0` or `lps[n-1] == 0`, the string does not have such a periodic proper prefix.
// The longest proper prefix in this case is `lps[n-1]`, but it may not be periodic. We need to check if the string can be formed by repeating
// a smaller prefix. The length of this longest periodic proper prefix would be `n - k` where `k` is the smallest repeating unit length.
//
// The logic is as follows:
// 1. Compute the LPS array for the given string `s`. The KMP algorithm can be used for this.
// 2. Let `n` be the length of `s`. Let `lps_val` be `lps[n-1]`.
// 3. The length of the smallest repeating unit, if one exists, would be `n - lps_val`.
// 4. A proper periodic prefix exists if and only if `lps_val > 0` and `n` is perfectly divisible by the length of the repeating unit, `n - lps_val`.
//    If `n % (n - lps_val) == 0`, it means the string is a repetition of the prefix of length `n - lps_val`.
//    The longest periodic proper prefix's length would then be `lps_val`.
// 5. If `n % (n - lps_val) != 0` or `lps_val == 0`, no such proper periodic prefix exists, so we return -1.
//
// Let's refine the logic.
// The length of the longest proper prefix that is also a suffix is `lps[n-1]`.
// This means the substring `s[0...lps[n-1]-1]` is equal to `s[n-lps[n-1]...n-1]`.
// The string can be thought of as `prefix + middle + prefix`.
// For the whole string to be periodic, the length of the string, `n`, must be a multiple of the period length `p`.
// The period length `p` would be `n - lps[n-1]`.
// So we need to check if `lps[n-1] > 0` and `n % (n - lps[n-1]) == 0`.
// If this condition is met, the longest periodic proper prefix has a length of `lps[n-1]`.
// If `lps[n-1]` is 0, or `n` is not divisible by `n - lps[n-1]`, there's no such proper periodic prefix.
// In this case, we return -1.

// Time Complexity: O(N) where N is the length of the string `s`, due to the computation of the LPS array.
// Space Complexity: O(N) for storing the LPS array.

class Solution {
    public int solve(String s) {
        int n = s.length();
        if (n <= 1) {
            return -1;
        }

        int[] lps = new int[n];
        computeLPSArray(s, lps);

        int len = lps[n - 1];

        if (len > 0 && n % (n - len) == 0) {
            return n - (n - len);
        } else {
            return -1;
        }
    }

    private void computeLPSArray(String s, int[] lps) {
        int n = s.length();
        lps[0] = 0;
        int length = 0;
        int i = 1;

        while (i < n) {
            if (s.charAt(i) == s.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
}