/**
 * Problem Statement: Travelling Salesman Problem (TSP)
 * ----------------------------------------------------
 * Given an N x N matrix cost[][], where cost[i][j] denotes the cost of moving 
 * from city 'i' to city 'j'. Find the minimum cost required to complete a tour 
 * that starts at city 0, visits every other city exactly once, and returns to 
 * city 0 at the end.
 *
 * Constraints:
 * 1 <= cost.size() (N) <= 15
 * 0 <= cost[i][j] <= 10^4
 *//**
     * Optimal Solution: Dynamic Programming with Bitmasking
     * ------------------------------------------------------
     * Since the number of cities N is small (N <= 15), the exponential complexity 
     * of O(N^2 * 2^N) is acceptable. The state space is defined by the set of 
     * visited cities and the current location of the salesman.
     *
     * * DP State: dp[mask][pos]
     * - `mask`: An integer bitmask representing the set of cities visited so far.
     * - `pos`: The current city (the last city visited).
     * - `dp[mask][pos]` = Minimum cost to visit all unvisited cities in `mask` 
     * and finally return to city 0, starting from `pos`.
     *
     * * Base Case:
     * - If `mask == (1 << n) - 1` (all cities visited): 
     * Return `cost[pos][0]` (the cost to return to the starting city 0).
     *
     * * Recurrence Relation:
     * - `dp[mask][pos] = min( cost[pos][city] + dp[mask | (1 << city)][city] )`
     * for all unvisited cities `city`.
     *
     * * Final Answer: `solve(1, 0)`
     * - Start at mask=1 (only city 0 visited) and position pos=0.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N^2 * 2^N), where N is the number of cities.
 * - The number of DP states is 2^N (for the mask) * N (for the current position).
 * - For each state `dp[mask][pos]`, we iterate through N possible next cities.
 * - Total Complexity = O(Number of States * Time per State) = O(2^N * N * N) = O(N^2 * 2^N).
 * - Since N <= 15, this complexity is computationally feasible.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N * 2^N), where N is the number of cities.
 * - This space is required to store the memoization table `dp[1 << N][N]`.
 */
// Optimal Solution Code - 

class Solution {
    private int n;
    private int[][] cost;
    private int[][] dp;

    public int tsp(int[][] cost) {
        this.n = cost.length;
        this.cost = cost;

        dp = new int[1 << n][n];

        for (int i = 0; i < (1 << n); i++)
            Arrays.fill(dp[i], -1);
            return solve(1, 0);
    }
    private int solve(int mask, int pos) {
        if (mask == (1 << n) - 1)
        {
            return cost[pos][0];
        }
        if (dp[mask][pos] != -1) 
        return dp[mask][pos];

        int ans = Integer.MAX_VALUE;

        for (int city = 0; city < n; city++) 
        {
            if ((mask & (1 << city)) == 0) 
            { 
                int newMask = mask | (1 << city);
                int newCost = cost[pos][city] + solve(newMask, city);
                ans = Math.min(ans, newCost);
            }
        }
        dp[mask][pos] = ans;
        return ans;
    }
}

