    /**
     * Problem Statement:
     * You have two soups, A and B, each starting with n mL. On every turn, one of the following four serving operations is chosen at random, each with probability 0.25 independent of all previous turns:
     * 1. pour 100 mL from type A and 0 mL from type B
     * 2. pour 75 mL from type A and 25 mL from type B
     * 3. pour 50 mL from type A and 50 mL from type B
     * 4. pour 25 mL from type A and 75 mL from type B
     * The amounts are poured simultaneously. If an operation asks to pour more than you have, pour all that remains. The process stops when one of the soups is used up. Return the probability that A is used up before B, plus half the probability that both soups are used up in the same turn. Answers within 10^-5 of the actual answer will be accepted.
     * * Approach:
     * This problem can be solved using dynamic programming with memoization. The state of our system can be defined by the remaining amounts of soup A and soup B. Let's represent the amounts in units of 25 mL to simplify calculations. So, an initial amount of `n` mL becomes `n/25` units. The serving operations then become:
     * 1. (4, 0)
     * 2. (3, 1)
     * 3. (2, 2)
     * 4. (1, 3)
     * * We can define a function `solve(a, b)` that returns the probability of the desired outcome (A finishes before B + 0.5 * both finish together) given `a` units of soup A and `b` units of soup B.
     * * The base cases for the recursion are:
     * - If `a <= 0` and `b <= 0`: Both soups are finished. We return `0.5` as per the problem statement.
     * - If `a <= 0` and `b > 0`: Soup A is finished first. We return `1.0`.
     * - If `a > 0` and `b <= 0`: Soup B is finished first. We return `0.0` as this scenario doesn't contribute to the desired probability.
     * - If `a > 0` and `b > 0`: We recursively calculate the probabilities for the four possible serving operations.
     * `solve(a, b) = 0.25 * (solve(a-4, b) + solve(a-3, b-1) + solve(a-2, b-2) + solve(a-1, b-3))`
     * * We can use a 2D array, `memo[a][b]`, to store the results of `solve(a, b)` to avoid redundant calculations.
     * * An important observation is that as `n` becomes very large, the probability converges to `1.0`. This is because soup A is consistently being depleted more or equally than soup B in all four operations. The total amount of A removed is always greater than or equal to the total amount of B removed. Therefore, for a sufficiently large `n`, A will almost certainly run out first. We can find a threshold value for `n` (around `4800` to `4801`) where the probability is extremely close to `1.0` (within `10^-5`) and return `1.0` directly to avoid time limit exceeded errors for large inputs.
     * * Time Complexity:
     * O(n^2) for smaller values of n, as we fill a 2D DP table of size approximately `(n/25) x (n/25)`. For large `n`, it's O(1) due to the threshold optimization.
     * * Space Complexity:
     * O(n^2) to store the memoization table. For large `n`, it's O(1) due to the threshold optimization.
     * * Optimal Solution:
     */
    