/**
 * Problem Statement: Count Unguarded Cells in the Grid
 * ----------------------------------------------------
 * Given an m x n grid, and the positions of 'guards' and 'walls'.
 * A guard sees cells in the four cardinal directions (N, E, S, W) until hitting a wall, 
 * another guard, or the grid boundary. A cell is 'guarded' if a guard can see it.
 * The task is to return the number of unoccupied cells that are NOT guarded.
 *
 * Cell Status Codes for the Grid:
 * 0: Empty (Initial state)
 * 1: Wall
 * 2: Guard
 * 3: Guarded (Covered by a guard, but not a guard/wall itself)
 *
 * Constraints:
 * 1 <= m, n <= 10^5, but 2 <= m * n <= 10^5 (total cells max 100,000)
 *//**
     * Optimal Solution: Simulation with a Single Grid Pass
     * ----------------------------------------------------
     * The problem involves line-of-sight propagation, which is best modeled by processing 
     * the grid's rows and columns sequentially in both directions (forward and backward).
     * This avoids repeated work from each guard and ensures that the obstruction rule 
     * (walls and other guards stop the view) is correctly applied.
     *
     * * Algorithm Steps:
     * 1. **Initialize Grid:** Create an m x n grid to mark the position of walls (1) and 
     * guards (2). All other cells start as 0 (Empty/Unguarded).
     * 2. **Four Directional Sweeps:** Perform four passes over the grid to mark guarded cells (3):
     * a. **Sweep 1 (Left to Right):** For each cell (r, c), check the previous cell (r, c-1).
     * If (r, c-1) was a guard/guarded, and (r, c) is empty, mark (r, c) as guarded.
     * If (r, c) is a wall or a guard, the line of sight stops.
     * b. **Sweep 2 (Right to Left):** Similar to (a), but check (r, c+1).
     * c. **Sweep 3 (Top to Bottom):** Similar, but check (r-1, c).
     * d. **Sweep 4 (Bottom to Top):** Similar, but check (r+1, c).
     * 3. **Count Unguarded:** Iterate through the final grid and count the number of cells 
     * that remain marked as 0 (Empty/Unguarded).
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(M * N + G + W), where M is the number of rows, N is the number of columns, 
 * G is the number of guards, and W is the number of walls.
 * - Initialization (Walls and Guards): O(G + W).
 * - Four Directional Sweeps: Each sweep iterates over every cell once, taking O(M * N) time.
 * - Final Count: Iterates over every cell once, taking O(M * N) time.
 * - Since G + W <= M * N, the complexity is dominated by the grid traversal: O(M * N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(M * N), where M is the number of rows and N is the number of columns.
 * - This space is required to store the intermediate state of the grid (`int[][] grid`).
 */
// Optimal Solution in Java -