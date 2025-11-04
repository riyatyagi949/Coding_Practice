/**
 * Problem Statement: Frog Jump
 * ----------------------------
 * Given an integer array 'heights[]' where heights[i] represents the height of the i-th stair.
 * A frog starts from the first stair (index 0) and wants to reach the last stair (index N-1).
 * From any stair 'i', the frog has two options:
 * 1. Jump to the (i+1)-th stair.
 * 2. Jump to the (i+2)-th stair.
 * The cost of a jump is the absolute difference in height between the two stairs.
 * Determine the minimum total cost required for the frog to reach the last stair.
 *
 * Example: heights[] = [20, 30, 40, 20] -> Output: 20
 *
 * Constraints:
 * 1 <= heights.size() <= 10^5
 * 0 <= heights[i] <= 10^4
 *//**
     * Optimal Solution: Dynamic Programming with Space Optimization
     * -----------------------------------------------------------
     * This problem has optimal substructure and overlapping subproblems, making it 
     * suitable for Dynamic Programming.
     *
     * Let dp[i] be the minimum cost to reach stair 'i'.
     * The recurrence relation is:
     * dp[i] = min( 
     * dp[i-1] + |heights[i] - heights[i-1]|,  // Jump from i-1
     * dp[i-2] + |heights[i] - heights[i-2]|   // Jump from i-2
     * )
     *
     * * Base Cases:
     * * dp[0] = 0 (Cost to reach the first stair is zero).
     *
     * * Space Optimization:
     * * Since dp[i] only depends on dp[i-1] and dp[i-2], we only need to maintain 
     * * the results for the previous two steps instead of the entire DP array.
     * * We use 'prev2' for dp[i-2] and 'prev1' for dp[i-1].
     *
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the number of stairs (heights.length).
 * - We iterate through the array exactly once, from index 1 to N-1.
 * - All operations inside the loop (arithmetic, Math.abs, Math.min) are O(1).
 * - Overall complexity: O(N).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - The space-optimized DP solution uses only a fixed number of variables 
 * (prev1, prev2, current, etc.) regardless of the input size N.
 * - We avoid creating the O(N) auxiliary DP array.
 */
// Optimal Solution in Java -