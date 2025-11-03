/**
 * Problem Statement: Minimum Time to Make Rope Colorful
 * ----------------------------------------------------
 * Alice has a rope with n balloons. The color of the i-th balloon is given by 
 * colors[i], and the time to remove it is neededTime[i].
 * The goal is to make the rope "colorful," meaning no two consecutive balloons 
 * have the same color. Bob must remove the minimum number of balloons to satisfy 
 * this, resulting in the minimum total removal time.
 * Return the minimum time Bob needs to make the rope colorful.
 *
 * Constraints:
 * n == colors.length == neededTime.length
 * 1 <= n <= 10^5
 * 1 <= neededTime[i] <= 10^4
 *//**
     * Optimal Solution: Greedy Algorithm
     * ----------------------------------
     * The problem only concerns **consecutive** balloons of the same color. 
     * We can process the array sequentially, focusing on continuous groups of 
     * same-colored balloons (a "block").
     *
     * In any block of $L$ consecutive balloons of the same color, we must remove $L-1$ 
     * of them, leaving only one balloon. To minimize the total time, the greedy strategy 
     * is to **always keep the balloon with the maximum removal time** in that block, 
     * and remove all others.
     *
     * * Algorithm Steps (Single Pass):
     * 1. Initialize `totalTime` to 0.
     * 2. Iterate through the rope from the second balloon (index $i=1$).
     * 3. If `colors[i]` is the same as `colors[i-1]`, we are inside a block:
     * a. The balloon to be removed is the one with the *minimum* time between 
     * `neededTime[i]` and the time kept from the *previous* comparison (`neededTime[i-1]`).
     * Add this minimum time to `totalTime`.
     * b. To correctly carry forward the *maximum* time to the next comparison (if the block continues), 
     * update `neededTime[i]` to be the **maximum** time between the two. 
     * This effectively ensures that the current variable at index `i` (which is now `i-1` for the *next* iteration) 
     * always holds the max time seen in the current consecutive block.
     * 4. If `colors[i]` is different from `colors[i-1]`, the block ends, and we simply move on. 
     *
     * * Note on Implementation: By modifying `neededTime[i]` in place to store the maximum 
     * * time encountered so far in the current block, we avoid using extra variables 
     * * to track the block's sum or maximum.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of the string 'colors' (or array 'neededTime').
 * - The algorithm involves a single pass through the array/string from index 1 to N-1.
 * - All operations inside the loop (comparison, Math.min, Math.max, addition) are O(1).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1) auxiliary space.
 * - The solution modifies the input array `neededTime` in place, but does not use any 
 * additional data structures whose size depends on the input size N.
 */
// Optimal Solution in Java-