/*
 * =========================================================================================
 * PROBLEM STATEMENT: 960. Delete Columns to Make Sorted III
 * =========================================================================================
 * You are given an array of n strings 'strs', all of the same length 'm'.
 * We want to delete the minimum number of columns such that after deletions, 
 * every single string in the array is in non-decreasing lexicographic order.
 * * A string s is in lexicographic order if s[0] <= s[1] <= ... <= s[s.length - 1].
 * Note: We are not sorting the array of strings; we are ensuring each individual 
 * row becomes sorted by removing the same columns from every row.
 * * Example:
 * Input: strs = ["babca","bbazb"]
 * Output: 3
 * Explanation: Keep columns 2 and 3 ("bc" and "az"). Delete columns 0, 1, and 4.
 * =========================================================================================
 * * =========================================================================================
 * OPTIMAL SOLUTION: Dynamic Programming (Variant of Longest Increasing Subsequence)
 * =========================================================================================
 * This problem can be rephrased: "What is the maximum number of columns we can KEEP?"
 * If we keep 'k' columns, the number of deleted columns will be (m - k).
 * * To maximize 'k', we use Dynamic Programming similar to the Longest Increasing 
 * Subsequence (LIS) algorithm.
 * * 
 * * 1. Let dp[j] be the maximum number of columns we can keep ending at column 'j'.
 * 2. For each column 'j' from 0 to m-1:
 * - Initialize dp[j] = 1 (we can always keep at least column 'j' itself).
 * - For each previous column 'i' from 0 to j-1:
 * - Check if column 'i' can come before column 'j'.
 * - This is valid ONLY if for EVERY string 's' in 'strs', s[i] <= s[j].
 * - If valid: dp[j] = max(dp[j], dp[i] + 1).
 * 3. The maximum value in the dp array represents the most columns we can keep.
 * 4. Result = Total Columns (m) - max(dp).
 * =========================================================================================
 */
/*
 * =========================================================================================
 * COMPLEXITY ANALYSIS
 * =========================================================================================
 * Time Complexity: O(m^2 . n)
 * - We have two nested loops to compute the DP array, which takes O(m^2).
 * - Inside the inner loop, we call 'isValidTransition', which iterates through all 'n' 
 * strings, taking O(n).
 * - Given m, n <= 100, the total operations are roughly 100^3 = 1,000,000, which
 * easily fits within time limits.
 * * Space Complexity: O(m).
 * - We use a 1D DP array of size 'm' to store the maximum columns kept ending at each index.
 * =========================================================================================
 */
// Optimal Solution Java code - 
class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();

        int[] dp = new int[m];
        int maxKept = 1;

        for (int j = 0; j < m; j++)
         {
            dp[j] = 1;
            for (int i = 0; i < j; i++) 
            {
            if (isValid(strs, i, j))
              {
             dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            maxKept = Math.max(maxKept, dp[j]);
        }
      return m - maxKept;
    }
    private boolean isValid(String[] strs, int i, int j)
     {
        for (String s : strs) 
        {
         if (s.charAt(i) > s.charAt(j)) 
            {
            return false;
            }
        }
        return true;
    }
}
