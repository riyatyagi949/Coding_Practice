/**
 * Problem Statement: Wildcard Pattern Matching
 * -------------------------------------------
 * Given two strings 'pat' (pattern) and 'txt' (text), return true if the wildcard 
 * pattern matches the entire text, otherwise return false.
 * The pattern 'pat' can include:
 * - '?' : Matches any single character.
 * - '*' : Matches any sequence of characters (including the empty sequence).
 *
 * Examples:
 * Input: txt = "abcde", pat = "a?c*" -> Output: true
 * Input: txt = "baaabab", pat = "a*ab" -> Output: false
 *
 * Constraints:
 * 1 <= txt.size(), pat.size() <= 100
 *//**
     * Optimal Solution: Dynamic Programming (DP)
     * ------------------------------------------
     * This problem has optimal substructure and overlapping subproblems, making DP ideal.
     * We define a 2D boolean array `dp[i][j]` where:
     * `dp[i][j]` is **true** if the first `i` characters of the pattern (`pat.substring(0, i)`)
     * match the first `j` characters of the text (`txt.substring(0, j)`), and **false** otherwise.
     *
     * * Base Case:
     * - `dp[0][0] = true`: An empty pattern matches an empty text.
     * - `dp[i][0] = dp[i-1][0]` if `pat[i-1] == '*'`: An '*' at the start can match an empty text.
     * - `dp[0][j] = false` for j > 0: An empty pattern cannot match a non-empty text.
     *
     * * Recurrence Relation (Iterative):
     * Let `p = pat.charAt(i - 1)` and `t = txt.charAt(j - 1)`.
     *
     * 1. **If p == t or p == '?'**:
     * `dp[i][j] = dp[i - 1][j - 1]`
     * (The current characters match, so the result depends on the match of the prefixes.)
     *
     * 2. **If p == '*'**:
     * `dp[i][j] = dp[i - 1][j] || dp[i][j - 1]`
     * - `dp[i - 1][j]` (The '*' matches an **empty** sequence): We use the previous pattern
     * character and the current text prefix.
     * - `dp[i][j - 1]` (The '*' matches the **current** character `t` and potentially more):
     * We keep the '*' and check the current pattern prefix against the shorter text prefix.
     */

//  Code - 
class Solution {
       public boolean wildCard(String txt, String pat) {
        int n = pat.length();
        int m = txt.length();
        
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true; 

        for (int i = 1; i <= n; i++) {
            if (pat.charAt(i - 1) == '*') {
                dp[i][0] = dp[i - 1][0];
            }
             else {
                break; 
            }
        }
        for (int i = 1; i <= n; i++) { 
            for (int j = 1; j <= m; j++) {
                char p = pat.charAt(i - 1);
                char t = txt.charAt(j - 1); 
                
                if (p == t || p == '?') 
                {
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                else if (p == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N * M), where N is the length of `pat` and M is the length of `txt`.
 * - The algorithm involves filling an N x M DP table. Each cell is computed in O(1) time.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N * M), where N is the length of `pat` and M is the length of `txt`.
 * - This space is required to store the 2D DP table.
 * * Note: Space optimization to O(min(N, M)) is possible by noticing that only the 
 * * previous row is needed, but the O(N*M) solution is clearer and sufficient given 
 * * the small constraints (N, M <= 100).
 */
