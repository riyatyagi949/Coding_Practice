/**
 * Problem Statement: Shortest Common Supersequence (Length)
 * --------------------------------------------------------
 * Given two strings s1 and s2, find the length of the smallest string 
 * which has both s1 and s2 as its sub-sequences.
 * * Examples:
 * Input: s1 = "geek", s2 = "eke" -> Output: 5 ("geeke")
 * * Constraints:
 * 1 <= s1.size(), s2.size() <= 500
 *//**
     * Optimal Solution: Dynamic Programming (DP) based on Longest Common Subsequence (LCS)
     * -----------------------------------------------------------------------------------
     * The length of the Shortest Common Supersequence (SCS) of two strings, s1 and s2, 
     * is directly related to the length of their Longest Common Subsequence (LCS).
     *
     * The key relationship is:
     * Length(SCS) = Length(s1) + Length(s2) - Length(LCS)
     *
     * Why this works:
     * To form the shortest supersequence, we must include all characters from s1 and s2.
     * We start with Length(s1) + Length(s2). This counts the common characters (LCS) twice.
     * To minimize the length, we only need to count the common characters once.
     * Therefore, subtracting the length of the LCS once gives the minimum length.
     *
     * * Algorithm Steps:
     * 1. Calculate the length of the Longest Common Subsequence (LCS) of s1 and s2 
     * using standard Dynamic Programming.
     * 2. The result is m + n - LCS_length, where m and n are the lengths of s1 and s2.
     */
//  Code -

class Solution{
    public static int minSuperSeq(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++)
         {
            for (int j = 0; j <= n; j++) 
            {
                if (i == 0 || j == 0)
                 {
                    dp[i][j] = 0;
                }
                 else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                  {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } 
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int lcs = dp[m][n];
        return m + n - lcs;
    }
}
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(m * n), where m is the length of s1 and n is the length of s2.
 * - The time complexity is determined by the size of the 2D DP table (m+1) x (n+1)
 * and the O(1) work done inside each nested loop iteration.
 * * Space Complexity Analysis:
 * --------------------------
 * O(m * n), where m is the length of s1 and n is the length of s2.
 * - This space is required to store the 2D DP table.
 * * * Note: Space complexity can be optimized to O(min(m, n)) by using only two rows
 * of the DP table, but O(m*n) is standard for clear DP implementation.
 */