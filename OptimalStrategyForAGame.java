/**
 * Problem Statement: Optimal Strategy For A Game
 * ----------------------------------------------
 * You are given an integer array arr[] of size n (even). The array elements represent n coins 
 * of values v1, v2, ..., vn. You and an opponent play a game in an alternating way. 
 * In each turn, a player selects either the first or last coin from the row, removes it 
 * permanently, and receives its value.
 *
 * The goal is to determine the maximum possible amount of money you can win if you go first.
 * Note: Both players play optimally (meaning they always try to maximize their own score, 
 * which implicitly means minimizing the opponent's next maximum score).
 *
 * Constraints:
 * 2 <= n <= 10^3
 * 1 <= arr[i] <= 10^6
 *//**
     * Optimal Solution: Dynamic Programming (DP)
     * ------------------------------------------
     * This problem exhibits Optimal Substructure and Overlapping Subproblems, making DP ideal.
     * The game state can be fully defined by the remaining subarray of coins, arr[i...j].
     *
     * Let dp[i][j] be the maximum score the current player can get from the subarray arr[i...j].
     *
     * * Recurrence Relation:
     * When it's the current player's turn (on subarray arr[i...j]), they have two choices:
     * 1. Pick arr[i]: The player gets arr[i]. The opponent will then play optimally on the 
     * remaining subarray arr[i+1...j]. The opponent's best score will be dp[i+1][j]. 
     * The player wants to leave the opponent in the worst possible state.
     * The opponent will choose an action (taking arr[i+1] or arr[j]) that maximizes *their* score.
     * This means the player's score will be: arr[i] + MIN(dp[i+2][j], dp[i+1][j-1])
     * - If the opponent takes arr[i+1], the player gets dp[i+2][j] on the next turn.
     * - If the opponent takes arr[j], the player gets dp[i+1][j-1] on the next turn.
     * The opponent minimizes the current player's next score.
     *
     * 2. Pick arr[j]: The player gets arr[j]. The opponent plays optimally on arr[i...j-1].
     * The player's score will be: arr[j] + MIN(dp[i+1][j-1], dp[i][j-2])
     *
     * The player chooses the move that maximizes their score:
     * dp[i][j] = MAX (
     * arr[i] + MIN(dp[i + 2][j], dp[i + 1][j - 1]),   // Choice 1: Pick arr[i]
     * arr[j] + MIN(dp[i + 1][j - 1], dp[i][j - 2])    // Choice 2: Pick arr[j]
     * )
     *
     * * Base Cases:
     * 1. Length 1 (i == j): dp[i][i] = arr[i]
     * 2. Length 2 (j == i + 1): dp[i][i+1] = MAX(arr[i], arr[i+1])
     *
     * * Implementation Strategy:
     * The DP table is filled diagonally by increasing the length (len) of the subarray.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N^2), where N is the size of the array 'arr'.
 * - The solution uses two nested loops to fill the N x N DP table.
 * - The outer loop runs N times (for length `len`).
 * - The inner loop runs N times (for starting index `i`).
 * - The work inside the inner loop is O(1).
 * - Total time complexity: O(N * N) = O(N^2).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N^2), where N is the size of the array 'arr'.
 * - O(N^2) space is required to store the DP table `dp[n][n]`.
 */
// Code -
