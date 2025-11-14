/**
 * Problem Statement: Minimum Cost to Merge Stones
 * -----------------------------------------------
 * Given an array stones[], where the i-th element represents the number of stones 
 * in the i-th pile. In one move, you can merge exactly 'k' consecutive piles 
 * into a single pile. The cost of this move is equal to the total number of 
 * stones in these 'k' piles. Determine the minimum total cost required to merge 
 * all the piles into one single pile. If it is not possible, return -1.
 *
 * Constraints:
 * 1 <= stones.size() <= 30
 * 2 <= k <= 30
 */
/**
     * Optimal Solution: Dynamic Programming (DP)
     * ----------------------------------------
     * This problem is a classic application of interval Dynamic Programming.
     * The core idea is to find the minimum cost to merge the subarray of stones 
     * `stones[i...j]` into a single pile.
     *
     * 1. Feasibility Check:
     * A merge is only possible if we can reduce the number of piles (N) to 1.
     * In each merge, we reduce the pile count by `k - 1` (k piles become 1 pile).
     * The total number of reductions needed is `N - 1`. Therefore, `N - 1` must be 
     * perfectly divisible by `k - 1`. If `(N - 1) % (k - 1) != 0`, return -1.
     *
     * 2. DP State:
     * `dp[i][j]` = Minimum cost required to merge the subarray `stones[i...j]` 
     * into exactly **one pile**.
     *
     * 3. Prefix Sum:
     * A `prefix` array is used to calculate the sum of stones in any sub-array 
     * `stones[i...j]` in O(1) time: `sum(i, j) = prefix[j + 1] - prefix[i]`.
     *
     * 4. DP Transition (Optimal Substructure):
     * To merge `stones[i...j]` into one pile, the last operation must merge 
     * 'k' smaller piles into one. We iterate through all possible split points `mid`.
     * The crucial insight is that the subproblems must result in a total number of 
     * sub-piles that is suitable for the final merge.
     *
     * `dp[i][j]` = min( `dp[i][mid]` + `dp[mid + 1][j]` )
     * where `mid` iterates from `i` to `j-1` in steps of `k - 1`.
     * This step size ensures that the resulting number of piles in `[i, mid]` and 
     * `[mid+1, j]` can eventually combine into a group of `k` piles for the final merge.
     *
     * Final Cost Addition:
     * If the segment `[i, j]` can be reduced to exactly **one pile** * (checked by `(j - i) % (k - 1) == 0`), we must add the cost of the final merge, 
     * which is the total sum of stones in the interval: `prefix[j + 1] - prefix[i]`.
     */
// Code -

class Solution {
    public static int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) 
        return -1;

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + stones[i];

        int[][] dp = new int[n][n];
        for (int len = 2; len <= n; len++) 
        {
            for (int i = 0; i + len <= n; i++)
            {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE / 2;

                for (int mid = i; mid < j; mid += (k - 1))
                {
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
                }
                if ((j - i) % (k - 1) == 0) 
                {
                    dp[i][j] += prefix[j + 1] - prefix[i];
                }
            }
        }
        return dp[0][n - 1];
    }
}
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N^3), where N is the length of the 'stones' array.
 * - The solution involves three nested loops:
 * - Outer loop for length `len`: O(N)
 * - Middle loop for start index `i`: O(N)
 * - Inner loop for split point `mid` (steps by k-1): O(N / (k-1)), which is O(N) since k >= 2.
 * - Given the constraint N <= 30, an O(N^3) solution is highly efficient.
 * * Space Complexity Analysis:
 * --------------------------
 * O(N^2), where N is the length of the 'stones' array.
 * - O(N^2) is used for the DP table `dp[n][n]`.
 * - O(N) is used for the `prefix` sum array.
 * - Overall complexity: O(N^2).
 */