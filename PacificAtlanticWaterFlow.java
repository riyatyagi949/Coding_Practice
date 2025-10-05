/**
 * Problem Statement:
 * Given an m x n integer matrix `heights` representing an island, where `heights[r][c]` is the elevation of a cell.
 * The Pacific Ocean borders the top (row 0) and left (column 0) edges.
 * The Atlantic Ocean borders the bottom (row m-1) and right (column n-1) edges.
 * Rainwater flows from a cell to a neighboring cell (north, south, east, west) only if the neighboring cell's height
 * is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into that ocean.
 * The task is to return a list of coordinates [r, c] from which water can flow to *both* the Pacific and Atlantic Oceans.
 *
 * Optimal Approach: Reverse DFS/BFS (Multi-Source Traversal)
 * A direct approach of checking flow from every cell to both oceans would be inefficient. A more optimal approach is to use a **reverse traversal** (or multi-source BFS/DFS) starting from the oceans and flowing *inland*.
 *
 * The water flow rule is: `current_height >= neighbor_height`.
 * In the reverse flow (inland traversal), this rule translates to: **`current_height <= neighbor_height`**.
 * Water can "reach" a cell from an ocean if the cell's height is greater than or equal to the cell the water came from.
 *
 * Steps:
 * 1. Initialization:
 * - Create two boolean matrices, `pacificReachable` and `atlanticReachable`, both of size m x n, initialized to false.
 * - These matrices will track whether water can flow *from* a cell *to* the respective ocean (or, more precisely, if a cell is reachable by water originating from that ocean).
 * 2. Multi-Source Traversal:
 * - **Pacific Ocean**: Start a traversal (DFS or BFS) from all cells on the Pacific border:
 * - Top row (`r=0`, `0 <= c < n`).
 * - Left column (`0 <= r < m`, `c=0`).
 * The traversal will mark all reachable cells in the `pacificReachable` matrix.
 * - **Atlantic Ocean**: Start a separate traversal from all cells on the Atlantic border:
 * - Bottom row (`r=m-1`, `0 <= c < n`).
 * - Right column (`0 <= r < m`, `c=n-1`).
 * The traversal will mark all reachable cells in the `atlanticReachable` matrix.
 * 3. Traversal Logic (DFS is often simpler for this):
 * - `dfs(r, c, reachableMatrix, heights, prevHeight)`:
 * - Base Cases/Constraints: Check if `(r, c)` is out of bounds, already visited (`reachableMatrix[r][c]` is true), or if the flow condition is violated (`heights[r][c] < prevHeight`).
 * - Mark the current cell as reachable: `reachableMatrix[r][c] = true`.
 * - Recursively call DFS for all four neighbors, passing `heights[r][c]` as the new `prevHeight`.
 * 4. Final Result:
 * - Iterate through the entire m x n grid.
 * - If a cell `(r, c)` is marked as true in *both* `pacificReachable[r][c]` and `atlanticReachable[r][c]`, it means water can flow from this cell to both oceans. Add `[r, c]` to the result list.
 *
 * Time Complexity: O(m * n). We perform two independent DFS/BFS traversals, each visiting every cell and edge at most once.
 * Space Complexity: O(m * n) to store the two boolean visited/reachable matrices (`pacificReachable`, `atlanticReachable`) and for the recursion stack depth (in the worst case for DFS).
 */
// Optimal Solution in Java-

import java.util.*;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0, heights[i][0]);  
            dfs(heights, atlantic, i, n - 1, heights[i][n - 1]);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j, heights[0][j]);     
            dfs(heights, atlantic, m - 1, j, heights[m - 1][j]); 
        }
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }
    
    private void dfs(int[][] heights, boolean[][] visited, int r, int c, int prevHeight) {
        int m = heights.length, n = heights[0].length;
        if (r < 0 || c < 0 || r >= m || c >= n)
         return;
        if (visited[r][c])
         return;
        if (heights[r][c] < prevHeight) 
        return; 
        
        visited[r][c] = true;
        dfs(heights, visited, r + 1, c, heights[r][c]);
        dfs(heights, visited, r - 1, c, heights[r][c]);
        dfs(heights, visited, r, c + 1, heights[r][c]);
        dfs(heights, visited, r, c - 1, heights[r][c]);
    }
}