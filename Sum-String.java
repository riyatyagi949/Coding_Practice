// Problem Statement:
// Given a string s consisting of digits, determine whether it can be classified as a sum-string.
// A sum-string is a string that can be split into two or more non-empty substrings such that:
// The rightmost substring is equal to the sum of the two substrings immediately before it (interpreted as integers).
// This condition must apply recursively to the substrings before it.
// The rightmost substring (and any number in the sum) must not contain leading zeroes, unless the number is exactly '0'.

// Approach:
// The problem can be solved using a recursive backtracking approach. We need to find the first two numbers (s1 and s2) that can form the beginning of a sum-string. We iterate through all possible lengths for the first number (s1) and the second number (s2). Once s1 and s2 are determined, we recursively check if the remaining part of the string can be formed by successive sums.

// The `isSumString` function iterates through all possible starting points for the second number (`j`). For each pair of `s1` (from index 0 to `i-1`) and `s2` (from index `i` to `j-1`), it checks for leading zeros. If no leading zeros are present, it calls the `isValidSumString` helper function.

// The `isValidSumString` helper function takes `s1`, `s2`, and the `remaining` part of the string.
// Base Case: If `remaining` is empty, it means we have successfully partitioned the entire string, so we return `true`.
// Recursive Step:
// 1. Calculate the sum of `s1` and `s2` using `addStrings`.
// 2. Check if the `remaining` string starts with the calculated `sum`. If not, it's not a valid sum-string, so return `false`.
// 3. If it starts with the `sum`, recursively call `isValidSumString` with `s2` as the new `s1`, `sum` as the new `s2`, and the `remaining` string after removing the `sum`.

// The `addStrings` function performs string addition similar to how we add numbers by hand, handling carry-overs. It builds the sum string from right to left.

// Time Complexity:
// The time complexity is difficult to express precisely with a simple Big O notation due to the string operations and recursion.
// In the worst case, `isSumString` has nested loops iterating up to `N` times each, where `N` is the length of the string.
// Inside the loops, `substring` operations take `O(N)` time.
// The `isValidSumString` function recursively calls itself. In each call, `addStrings` can take up to `O(N)` time (for string addition of numbers up to length `N`).
// The depth of the recursion can also be up to `O(N)` (e.g., for "112358...").
// Therefore, a loose upper bound could be considered as `O(N^3 * N)` due to the nested loops and string operations within the recursion, which simplifies to `O(N^4)`. However, in practice, the number of valid partitions is limited, and many branches prune early.

// Space Complexity:
// The space complexity is primarily due to the recursion stack and the new strings created in `substring` and `addStrings`.
// The maximum depth of the recursion stack can be `O(N)`.
// `addStrings` creates a `StringBuilder` and then a `String` of length up to `O(N)`.
// Therefore, the space complexity is `O(N)`.

// Optimal Solution:
// The provided solution is generally considered optimal for this problem given the constraints and nature of sum-strings.
// The core idea is to try all possible initial splits and then recursively validate. There isn't a significantly more efficient approach like dynamic programming that would drastically reduce the complexity, because the "state" of the recursion (the two previous numbers) can be large and the direct substring comparison is necessary.
// Memoization is not directly applicable here because the `remaining` string changes, and `s1` and `s2` also change, making it difficult to define a clear DP state that avoids re-computation efficiently across different paths.
// The constraints on string length (up to 100) allow for this approach. If the string were much larger, more advanced arbitrary-precision arithmetic libraries would be needed, and the complexity might become an issue.

class Solution {
    public boolean isSumString(String s) {
        int n = s.length();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String s1 = s.substring(0, i);
                String s2 = s.substring(i, j);
                if ((s1.length() > 1 && s1.startsWith("0")) || 
                    (s2.length() > 1 && s2.startsWith("0"))) {
                    continue;
                }
                if (isValidSumString(s1, s2, s.substring(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidSumString(String s1, String s2, String remaining) {
        if (remaining.length() == 0) return true;

        String sum = addStrings(s1, s2);

        if (!remaining.startsWith(sum)) {
            return false;
        }

        return isValidSumString(s2, sum, remaining.substring(sum.length()));
    }

    private String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = num1.length() - 1, j = num2.length() - 1;

        while (i >= 0 || j >= 0 || carry != 0) {
            int digit1 = (i >= 0) ? num1.charAt(i--) - '0' : 0;
            int digit2 = (j >= 0) ? num2.charAt(j--) - '0' : 0;

            int currentSum = digit1 + digit2 + carry;
            sb.append(currentSum % 10);
            carry = currentSum / 10;
        }
        return sb.reverse().toString();
    }
}