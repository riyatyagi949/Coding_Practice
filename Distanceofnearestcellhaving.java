/**
 * Problem Statement: Distance of nearest cell having 1
 * ----------------------------------------------------
 * Given a binary grid[][], where each cell contains either 0 or 1, 
 * find the distance of the nearest 1 for every cell in the grid.
 * The distance between two cells (i1, j1) and (i2, j2) is calculated 
 * using Manhattan distance: |i1 - i2| + |j1 - j2|.
 * Return a matrix of the same size where each cell (i, j) contains the 
 * minimum distance from grid[i][j] to the nearest cell having value 1.
 *
 * Constraints:
 * 1 <= grid.size() <= 200
 * 1 <= grid[0].size() <= 200
 * Guaranteed at least one cell has value 1.
 *//**
     * Optimal Solution: Multi-Source Breadth-First Search (BFS)
     * ---------------------------------------------------------
     * This problem is equivalent to finding the shortest distance from all 
     * cells to the nearest source node (where source nodes are the cells containing 1).
     * Since the distance metric is Manhattan distance (unweighted edges of cost 1 
     * for movement to adjacent cells), BFS is the optimal algorithm.
     *
     * * Why Multi-Source BFS is optimal:
     * - Standard BFS starts from one source. Multi-Source BFS starts by enqueuing ALL
     * source cells (all cells with value 1) initially.
     * - The distance from a '1' cell is 0.
     * - In the first level of BFS, all adjacent '0' cells are reached, and their distance 
     * is correctly set to 1.
     * - This process expands outwards layer by layer, guaranteeing that when a cell is 
     * first visited, it is reached by the shortest possible path from *any* of the '1' sources.
     *
     * * Algorithm Steps:
     * 1. Initialization:
     * - Create a result matrix 'dist' of the same size, initialized to -1 (or infinity) 
     * to mark unvisited cells.
     * - Initialize a Queue and enqueue the coordinates of all cells containing 1.
     * - Set the distance in 'dist' for all '1' cells to 0.
     * 2. BFS Traversal:
     * - While the queue is not empty, dequeue the current cell (r, c).
     * - Check its 4 neighbors (up, down, left, right).
     * - For any valid neighbor (nr, nc) that is unvisited (dist[nr][nc] is default/unvisited):
     * - Set dist[nr][nc] = dist[r][c] + 1.
     * - Enqueue (nr, nc).
     * 3. Return the 'dist' matrix.
     */
  /**
 * Time Complexity Analysis:
 * -------------------------
 * O(R * C), where R is the number of rows and C is the number of columns in the grid.
 * - The time complexity of BFS is proportional to the number of vertices (V) plus the 
 * number of edges (E): O(V + E).
 * - Here, the number of vertices V is R * C.
 * - The number of edges E is at most 4 * (R * C) (4 neighbors per cell).
 * - Since R and C are constant factors, the time complexity is linear with respect to 
 * the total number of cells in the grid: O(R * C).
 * * Space Complexity Analysis:
 * --------------------------
 * O(R * C), where R is the number of rows and C is the number of columns.
 * - This space is required to store the result matrix 'dist' (O(R * C)) and 
 * the auxiliary Queue for BFS, which in the worst case can hold all cells (O(R * C)).
 */  
// Optimal Solution - 
import java.util.*;