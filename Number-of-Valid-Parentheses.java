/**
 * PROBLEM STATEMENT: Number of Valid Parentheses
 * --------------------------------------------------------------------------------
 * You are given an integer 'n'. Your task is to find the total number of valid 
 * parentheses expressions of length 'n' using only "(" and ")".
 * * A parentheses expression is valid if:
 * 1. Open brackets are closed in the correct order.
 * 2. Every close bracket has a corresponding open bracket.
 * * Note: If 'n' is odd, it is impossible to form a valid balanced expression.
 * * Examples:
 * Input: n = 4
 * Output: 2
 * Explanation: Valid expressions are "(())" and "()()".
 * * Input: n = 6
 * Output: 5
 * Explanation: "((()))", "(())()", "()(())", "()()()", "(()())".
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: CATALAN NUMBERS (Dynamic Programming)
 * --------------------------------------------------------------------------------
 * The number of valid balanced parentheses sequences with 'k' pairs (where k = n/2)
 * is given by the k-th Catalan Number.
 * * The recursive formula for Catalan Numbers is:
 * C(i) = Σ [C(j) * C(i - 1 - j)] for j from 0 to i-1.
 * * Logic:
 * 1. If n is odd, return 0 (balance is impossible).
 * 2. Let k = n / 2 (number of pairs).
 * 3. Use an array dp[] where dp[i] stores the number of valid sequences for 'i' pairs.
 * 4. Base Case: dp[0] = 1 (An empty string is technically a valid balanced sequence).
 * 5. For each number of pairs 'i' from 1 to k:
 * - Imagine the first set of parentheses: ( [inner] ) [outer]
 * - If we use 'j' pairs inside the first bracket, we have 'i - 1 - j' pairs 
 * remaining to place outside.
 * - Sum the products for all possible values of 'j'.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(k^2) where k = n/2.
 * - We have a nested loop where the outer loop runs k times and the inner loop 
 * runs up to k times.
 * - Given n <= 20, k <= 10, this is extremely fast.
 * * Space Complexity: O(k)
 * - We store the Catalan numbers in a DP array of size k + 1.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java- 
class Solution {
    int findWays(int n) {
        if (n % 2 != 0) 
        return 0; 

        int pairs = n / 2;
        long[] dp = new long[pairs + 1];
        dp[0] = 1;

        for (int i = 1; i <= pairs; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return (int) dp[pairs];
    }
}


