/**
 * Problem Statement: Ways To Tile A Floor
 * ---------------------------------------
 * Given a floor of dimensions 2 x n and tiles of dimensions 2 x 1, 
 * find the number of ways the floor can be tiled. A tile can be placed 
 * either horizontally (1 x 2) or vertically (2 x 1).
 *
 * Example:
 * Input: n = 3, Output: 3
 * Input: n = 4, Output: 5
 *
 * Constraints:
 * 1 <= n <= 45
 *//**
     * Optimal Solution: Dynamic Programming / Fibonacci Relation
     * ----------------------------------------------------------
     * Let dp[i] be the number of ways to tile a 2 x i floor.
     * When considering the last column of the 2 x n floor, there are two possibilities:
     *
     * 1. **Place the last tile vertically (2 x 1):**
     * - This covers the last column (column n).
     * - The remaining floor is 2 x (n - 1).
     * - Number of ways: dp[n - 1].
     *
     * 2. **Place two tiles horizontally (two 1 x 2 tiles stacked):**
     * - This covers the last two columns (columns n and n-1).
     * - The remaining floor is 2 x (n - 2).
     * - Number of ways: dp[n - 2].
     *
     * * Recurrence Relation: dp[n] = dp[n - 1] + dp[n - 2].
     * * Base Cases:
     * - dp[1]: Only one vertical tile is possible. dp[1] = 1.
     * - dp[2]: Two vertical tiles OR two horizontal tiles. dp[2] = 2.
     *
     * This recurrence is identical to the Fibonacci sequence (F(n) where F(1)=1, F(2)=2).
     * The optimal solution uses **iterative DP** to solve this in linear time and constant space.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the dimension of the floor.
 * - The solution involves a single loop that iterates from 3 up to N, performing 
 * a constant number of operations in each step.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - The iterative approach only uses a fixed number of variables (`prev2`, `prev1`, `curr`) 
 * to store the necessary previous results, regardless of the input size N.
 */
// Optimal Solution in Java - 
class Solution {
    public int numberOfWays(int n)
     {
        if (n <= 2) 
        return n;

        int prev2 = 1; 
        int prev1 = 2; 
        int curr = 0;

        for (int i = 3; i <= n; i++)
         {
            curr = prev1 + prev2; 
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }
}
