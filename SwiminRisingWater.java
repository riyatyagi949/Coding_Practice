/**
 * Problem Statement:
 * Given an n x n integer matrix `grid` representing elevations, find the minimum time `t` required to swim from the top-left cell (0, 0) to the bottom-right cell (n-1, n-1).
 * At time `t`, a path exists between two adjacent cells (4-directionally) if and only if the elevation of *both* cells is at most `t`.
 * The "time" to complete the swim is determined by the highest elevation encountered along the path.
 *
 * Optimal Approach: Dijkstra's Algorithm / Priority Queue (Minimum Spanning Tree-like approach)
 *
 * The problem asks for the minimum required time `t` (maximum elevation on the path) to travel from (0, 0) to (n-1, n-1). This is a classic
 * "bottleneck path" problem, which can be solved efficiently using a modified version of Dijkstra's algorithm or a variation of Prim's algorithm
 * using a Priority Queue (Min-Heap).
 *
 * The "cost" to move to a cell (r, c) is not a fixed distance, but the time `t` at which that cell becomes reachable, which is `grid[r][c]`.
 * However, the *total time* for the path ending at (r, c) is the maximum of the time taken to reach the previous cell and the elevation of the current cell.
 *
 * Let `dist[r][c]` be the minimum *time* (maximum elevation) required to reach cell (r, c) from (0, 0).
 *
 * Algorithm Steps:
 * 1. Initialize a 2D array `dist` of size n x n with infinity to store the minimum time required to reach each cell.
 * 2. Set `dist[0][0] = grid[0][0]`, as the minimum time to reach the starting cell is its own elevation.
 * 3. Use a Priority Queue (Min-Heap) to store tuples `(time, r, c)`, prioritized by `time`. Add the starting cell: `(grid[0][0], 0, 0)`.
 * 4. While the Priority Queue is not empty:
 * a. Extract the cell with the minimum current time: `(t, r, c)`.
 * b. If the extracted time `t` is greater than `dist[r][c]`, skip this entry (it's an outdated, longer path).
 * c. If `(r, c)` is the destination `(n-1, n-1)`, return `t`.
 * d. For each 4-directionally adjacent neighbor `(nr, nc)`:
 * i. Calculate the time needed to reach the neighbor through the current cell:
 * `new_time = max(t, grid[nr][nc])`. This represents the highest elevation on the path from (0, 0) to (nr, nc) via (r, c).
 * ii. If `new_time` is less than the currently recorded minimum time to reach `(nr, nc)` (`dist[nr][nc]`):
 * - Update `dist[nr][nc] = new_time`.
 * - Add the new path to the Priority Queue: `(new_time, nr, nc)`.
 *
 * This effectively uses Dijkstra's approach where the "weight" of an edge from (r, c) to (nr, nc) is `max(current_path_time, grid[nr][nc])`,
 * and we seek to minimize the overall path weight.
 *
 * Time Complexity: O(N^2 log N), where N is the size of the grid.
 * - The grid has $N^2$ cells (vertices).
 * - The number of edges is $O(N^2)$ (each cell has up to 4 edges).
 * - In the worst case, we push and pull $O(N^2)$ elements from the Priority Queue.
 * - The complexity of Dijkstra's with a Binary Heap is $O(E \log V)$, which here is $O(N^2 \log(N^2)) = O(N^2 \cdot 2 \log N) = O(N^2 \log N)$.
 *
 * Space Complexity: O(N^2) to store the `dist` array and the elements in the Priority Queue.
 */
// Optimal Solution in Java - 
import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int t, r, c;
        Node(int t, int r, int c) {
             this.t = t; this.r = r; this.c = c;
              }
        public int compareTo(Node o) {
             return Integer.compare(this.t, o.t); 
             }
    }
    
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] dist = new int[n][n];
        for (int[] row : dist) 
        Arrays.fill(row, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0][0] = grid[0][0];
        pq.add(new Node(dist[0][0], 0, 0));
        
        int[] dr = { -1, 1, 0, 0 };
        int[] dc = { 0, 0, -1, 1 };
        
        while (!pq.isEmpty())
         {
            Node cur = pq.poll();
            int t = cur.t, r = cur.r, c = cur.c;
            if (r == n - 1 && c == n - 1)
             return t;
            if (t != dist[r][c])
             continue; 
            
            for (int k = 0; k < 4; ++k)
             {
                int nr = r + dr[k], nc = c + dc[k];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n)
                 continue;
                int nt = Math.max(t, grid[nr][nc]); 

                if (nt < dist[nr][nc]) 
                {
                    dist[nr][nc] = nt;
                    pq.add(new Node(nt, nr, nc));
                }
            }
        }
        return -1; 
    }
}