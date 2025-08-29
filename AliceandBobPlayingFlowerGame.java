/**
 * Problem Statement:
 * Alice and Bob are playing a turn-based game on a field with two lanes of flowers.
 * There are x flowers in the first lane and y in the second.
 * Alice goes first. In each turn, a player picks one flower from one of the lanes.
 * The current player wins if there are no flowers left at all after their turn.
 * We are given two integers n and m. We need to find the number of pairs (x, y)
 * such that 1 <= x <= n, 1 <= y <= m, and Alice wins.
 *
 * Approach:
 * Alice wins if and only if the total number of flowers (x + y) is an odd number.
 * This is because the total number of flowers decreases by one in each turn.
 * Alice takes the first turn, Bob the second, and so on.
 * If the total number of flowers is even, let's say 2k, Bob will always be able to make the last move.
 * If the total number of flowers is odd, let's say 2k + 1, Alice will always be able to make the last move.
 * The game state is defined by the total number of flowers remaining.
 * The player who makes the total number of flowers zero wins. This is a classic impartial game.
 * The total number of turns is x + y.
 * Alice wins if x + y is odd. This means one of x and y is odd, and the other is even.
 * The number of odd integers in the range [1, n] is (n + 1) / 2.
 * The number of even integers in the range [1, n] is n / 2.
 * The number of odd integers in the range [1, m] is (m + 1) / 2.
 * The number of even integers in the range [1, m] is m / 2.
 *
 * Case 1: x is odd and y is even.
 * Number of choices for x: (n + 1) / 2
 * Number of choices for y: m / 2
 * Total pairs: ((n + 1) / 2) * (m / 2)
 *
 * Case 2: x is even and y is odd.
 * Number of choices for x: n / 2
 * Number of choices for y: (m + 1) / 2
 * Total pairs: (n / 2) * ((m + 1) / 2)
 *
 * Total number of winning pairs for Alice = (number of odd x * number of even y) + (number of even x * number of odd y)
 * Total pairs = ((n + 1) / 2) * (m / 2) + (n / 2) * ((m + 1) / 2)
 *
 * Time Complexity: O(1)
 * The solution involves simple arithmetic operations on the given inputs n and m.
 *
 * Space Complexity: O(1)
 * No extra space is required to store data.
 */
class Solution {
    public long flowerGame(int n, int m) {
        long odd_n = (n + 1) / 2;
        long even_n = n / 2;
        long odd_m = (m + 1) / 2;
        long even_m = m / 2;
        return odd_n * even_m + even_n * odd_m;
    }
}