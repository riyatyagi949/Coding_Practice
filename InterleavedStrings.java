/**
 * Problem Statement: Interleaved Strings
 * --------------------------------------
 * Given three strings s1, s2, and s3, determine if s3 is formed by an interleaving 
 * of s1 and s2. Interleaving means mixing the characters of s1 and s2 to form s3 
 * while maintaining the relative order of characters from both s1 and s2.
 * The length of s3 must equal the combined length of s1 and s2.
 *
 * Example: s1="AAB", s2="AAC", s3="AAAABC" -> True
 *//**
     * Optimal Solution: Dynamic Programming (DP)
     * ----------------------------------------
     * This problem has Optimal Substructure and Overlapping Subproblems, making it 
     * ideal for Dynamic Programming.
     *
     * State Definition:
     * dp[i][j] is a boolean value that indicates whether the prefix of s3 of length 
     * (i + j) can be formed by interleaving the prefix of s1 of length 'i' 
     * and the prefix of s2 of length 'j'.
     *
     * Base Case:
     * dp[0][0] = true (Empty prefixes interleave to form an empty string).
     *
     * Transition (for dp[i][j]):
     * The character s3.charAt(i + j - 1) (the current character of s3) must match 
     * either the last unmatched character of s1 or the last unmatched character of s2.
     *
     * 1. Check s1: If the current character of s3 matches s1.charAt(i - 1), 
     * and the smaller problem dp[i-1][j] was solvable (meaning s3 prefix of 
     * length i+j-1 was formed by s1 prefix of length i-1 and s2 prefix of length j).
     * -> dp[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1))
     *
     * 2. Check s2: If the current character of s3 matches s2.charAt(j - 1), 
     * and the smaller problem dp[i][j-1] was solvable.
     * -> dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1))
     *
     * dp[i][j] is true if either of these conditions is true.
     */

//  Code - 
 class Solution {
      public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();

        if (n + m != l) 
            return false;

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char c3 = s3.charAt(i + j - 1);
                
                boolean fromS1 = dp[i - 1][j] && (s1.charAt(i - 1) == c3);
                boolean fromS2 = dp[i][j - 1] && (s2.charAt(j - 1) == c3);
                
                dp[i][j] = fromS1 || fromS2;
            }
        }
        return dp[n][m];
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N * M), where N = s1.length() and M = s2.length().
 * - We fill an N x M 2D DP table. Each cell calculation involves a few constant-time 
 * comparisons and lookups.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N * M), where N = s1.length() and M = s2.length().
 * - This space is required to store the 2D DP table `dp[n+1][m+1]`.
 * (Note: This could be optimized to O(min(N, M)) space using only two rows/columns 
 * since the current state only depends on the previous row/column, but the 2D solution 
 * is cleaner for explanation.)
 */