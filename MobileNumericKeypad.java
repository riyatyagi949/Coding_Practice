// Problem Statement:
// There is a standard numeric keypad on a mobile phone. You can press the current button or any button that is directly above, below, to the left, or to the right of it. For example, if you press 5, then pressing 2, 4, 6, or 8 is allowed. However, diagonal movements and pressing the bottom row corner buttons (* and #) are not allowed.
// Given an integer n, determine how many unique sequences of length n can be formed by pressing buttons on the keypad, starting from any digit.

// Approach:
// This problem can be solved using dynamic programming. We can define dp[i][j] as the number of sequences of length 'i' ending with digit 'j'.
// The keypad can be represented as a 2D array or a set of adjacency lists.
// The allowed moves from each digit are:
// 0: 0, 8
// 1: 1, 2, 4
// 2: 2, 1, 3, 5
// 3: 3, 2, 6
// 4: 4, 1, 5, 7
// 5: 5, 2, 4, 6, 8
// 6: 6, 3, 5, 9
// 7: 7, 4, 8
// 8: 8, 0, 5, 7, 9
// 9: 9, 6, 8

// Base case: For n = 1, dp[1][j] = 1 for all digits j from 0 to 9, as each digit itself forms a sequence of length 1.

// Recurrence relation: For n > 1, to calculate dp[i][j], we can sum up dp[i-1][k] for all digits 'k' that can move to 'j'.
// Alternatively, and more efficiently, we can calculate dp[i][j] by summing up dp[i-1][k] where 'j' can move to 'k'.
// Let's define moves[digit] as an array of digits that can be pressed after 'digit'.
// Then, dp[i][j] = sum(dp[i-1][k]) for all k in moves[j].
// No, it should be: dp[i][j] = sum(dp[i-1][k]) where k is a digit from which we can move to j.
// A simpler way is to iterate through each possible current digit `j` and its allowed next digits `k`.
// dp[len][k] += dp[len-1][j] if `j` can move to `k`.

// Let's represent the keypad and valid moves:
// int[][] keypad = {
//     {1, 2, 3},
//     {4, 5, 6},
//     {7, 8, 9},
//     {-1, 0, -1} // -1 for * and #
// };

// Valid moves:
// From 0: {0, 8}
// From 1: {1, 2, 4}
// From 2: {2, 1, 3, 5}
// From 3: {3, 2, 6}
// From 4: {4, 1, 5, 7}
// From 5: {5, 2, 4, 6, 8}
// From 6: {6, 3, 5, 9}
// From 7: {7, 4, 8}
// From 8: {8, 0, 5, 7, 9}
// From 9: {9, 6, 8}

// We will use a 2D array dp[n+1][10] where dp[i][j] stores the count of sequences of length 'i' ending with digit 'j'.

// Time Complexity: O(n * K), where K is the total number of possible moves from all digits (constant, about 30).
// Space Complexity: O(n * 10) = O(n)

// Optimal Solution:
class Solution {
    public int getCount(int n) {
        if (n == 1) return 10;

        int[][] moves = {
            {0, 8},     // 0
            {1, 2, 4},  // 1
            {2, 1, 3, 5},
            {3, 2, 6},
            {4, 1, 5, 7},
            {5, 2, 4, 6, 8},
            {6, 3, 5, 9},
            {7, 4, 8},
            {8, 5, 7, 9, 0},
            {9, 6, 8}
        };

        int[][] dp = new int[10][n + 1];

        for (int i = 0; i <= 9; i++) {
            dp[i][1] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int digit = 0; digit <= 9; digit++) {
                for (int next : moves[digit]) {
                    dp[digit][len] += dp[next][len - 1];
                }
            }
        }
        int total = 0;
        for (int i = 0; i <= 9; i++) {
            total += dp[i][n];
        }
           return total;
    }
}
