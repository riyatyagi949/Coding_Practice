/**
 * Problem Statement: Replace O's with X's
 * ---------------------------------------
 * Given a grid of size N x M where elements are either 'O' or 'X', replace all 
 * 'O's (or groups of connected 'O's) with 'X' that are surrounded by 'X'.
 * A group of 'O's is considered surrounded if they are NOT connected to the 
 * boundary of the grid.
 *
 * Example:
 * Input: grid = [['X', 'O', 'X'], ['O', 'O', 'X'], ['X', 'O', 'X']]
 * Output: grid = [['X', 'O', 'X'], ['X', 'X', 'X'], ['X', 'O', 'X']] 
 * (The top-center 'O' and bottom-center 'O' are safe because they touch the boundary).
 *
 * Constraints:
 * 1 <= grid.size() (N) <= 100
 * 1 <= grid[0].size() (M) <= 100
 */
/**
     * Optimal Solution: Boundary DFS/BFS and Two-Pass Cleanup
     * --------------------------------------------------------
     * The strategy is to identify all 'O's that are **NOT** surrounded (i.e., those connected to the boundary).
     *
     * 1. **Phase 1 (Mark Safe 'O's):** Traverse the **four borders** of the grid.
     * - If an 'O' is found on the border, start a DFS (or BFS) from that cell.
     * - During the traversal, change all connected 'O's to a temporary character (e.g., '#'). 
     * These '#' characters represent the "safe" 'O's.
     *
     * 2. **Phase 2 (Flip and Restore):** Traverse the entire grid again.
     * - If a cell is still 'O', it means it was not reachable from the boundary, hence it is surrounded. Flip it to 'X'.
     * - If a cell is '#', it means it was a safe 'O'. Restore it back to 'O'.
     * - If a cell is 'X', leave it as 'X'.
     */
    /**
 * Time Complexity Analysis:
 * -------------------------
 * O(N * M), where N is the number of rows and M is the number of columns.
 * - **Graph Traversal (DFS/BFS):** Every cell is visited at most a constant number of times (once by the main loop, and once by the DFS/BFS traversal). 
 * The complexity of DFS/BFS on a grid is proportional to the number of vertices (V = N*M) and edges (E = 4*N*M), resulting in O(N*M).
 * - **Cleanup Pass:** The final two nested loops iterate over all N*M cells once, taking O(N*M) time.
 * - Overall complexity: O(N * M).
 * * Space Complexity Analysis:
 * --------------------------
 * O(N * M)
 * - **Recursion Stack:** In the worst-case scenario (a grid full of 'O's), the DFS recursion depth can go up to O(N * M).
 * - **Auxiliary Space:** No extra data structure (like a visited array) is explicitly used because the grid itself is modified to store visited states ('#'). Thus, auxiliary space is O(1), but total space complexity considering the recursion stack is O(N * M).
 */
// Optimal Solution in Java -
