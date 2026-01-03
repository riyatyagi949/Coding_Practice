/**
 * PROBLEM STATEMENT: 1411. Number of Ways to Paint N × 3 Grid
 * --------------------------------------------------------------------------------
 * You have a grid of size n x 3 and you want to paint each cell with one of three 
 * colors (Red, Yellow, Green) such that no two adjacent cells (horizontal or 
 * vertical) have the same color.
 * * Given n, return the number of ways to paint the grid modulo 10^9 + 7.
 * * Example 1:
 * Input: n = 1 -> Output: 12
 * (6 ways for ABA pattern like R-Y-R, 6 ways for ABC pattern like R-Y-G)
 * * Example 2:
 * Input: n = 5000 -> Output: 30228214
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: DYNAMIC PROGRAMMING (State Transition)
 * --------------------------------------------------------------------------------
 * For a row of 3 cells, there are only two types of valid patterns:
 * 1. ABA (Two colors used: e.g., Red-Yellow-Red)
 * 2. ABC (Three colors used: e.g., Red-Yellow-Green)
 * * For n = 1:
 * - ABA type: 3 * 2 * 1 = 6 ways (3 choices for A, 2 for B)
 * - ABC type: 3 * 2 * 1 = 6 ways (3 choices for A, 2 for B, 1 for C)
 * Total = 12.
 * * Transition to the next row (i + 1):
 * - If current row is ABA:
 * - Next ABA can be: 3 ways
 * - Next ABC can be: 2 ways
 * - If current row is ABC:
 * - Next ABA can be: 2 ways
 * - Next ABC can be: 2 ways
 * * DP Formula:
 * - next_ABA = (current_ABA * 3 + current_ABC * 2)
 * - next_ABC = (current_ABA * 2 + current_ABC * 2)
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We iterate from row 2 to n exactly once.
 * Space Complexity: O(1)
 * - We only maintain two variables (same/ABA and diff/ABC) to calculate the next state.
 * --------------------------------------------------------------------------------
 */

// Optimal Solution in Java -
class Solution {
    public int numOfWays(int n) {
        long MOD = 1000000007;
        long same = 6;
        long diff = 6;

        for (int i = 2; i <= n; i++) {
            long newSame = (same * 3 + diff * 2) % MOD;
            long newDiff = (same * 2 + diff * 2) % MOD;

            same = newSame;
            diff = newDiff;
        }
        return (int)((same + diff) % MOD);
    }
}

