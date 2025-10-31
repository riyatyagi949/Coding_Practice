/**
 * Problem Statement: Shortest Cycle
 * ----------------------------------
 * You are given an undirected graph with V vertices (0 to V-1) and E edges.
 * Find the length of the shortest cycle in the graph. If the graph does not 
 * contain any cycle, return -1.
 * A cycle is a path that starts and ends at the same vertex without repeating 
 * any edge or vertex (except the start/end vertex).
 * * Constraints:
 * 1 <= V <= 10^3
 * 0 <= E <= 10^3
 *//**
     * Optimal Solution: Multiple BFS (One BFS from every node)
     * ---------------------------------------------------------
     * The shortest cycle in an unweighted graph has the shortest distance between 
     * two nodes 'u' and 'v' that share a common neighbor.
     * * **The Key Idea:** A cycle of length 'L' starting at node 's' is formed when 
     * a BFS from 's' discovers an edge (u, v) such that 'u' and 'v' have already 
     * been visited, and the edge (u, v) is NOT the edge that connected 'v' back to 'u' 
     * in the BFS tree (i.e., v is not the parent of u).
     * * However, a simpler and more robust approach is to run BFS from *every* node 
     * 's' and look for a simple condition:
     * * 1. Run BFS starting from each vertex 's' (0 to V-1).
     * 2. For each BFS, maintain the distance `dist[v]` from 's' to 'v' and the `parent[v]` 
     * of 'v' in the BFS tree.
     * 3. When exploring an edge (u, v) where `v` has already been visited (`dist[v] != -1`):
     * a. If `v` is NOT the parent of `u` (i.e., the edge (u, v) is a **non-tree edge** or **back edge**), 
     * then a cycle is found.
     * b. The length of this cycle is: `dist[u] + dist[v] + 1` (where +1 is for the edge (u, v)).
     * 4. Track the minimum cycle length found across all BFS runs.
     * * @param V The number of vertices.
     * @param E The number of edges (unused directly).
     * @param edges The array representing the undirected edges.
     * @return The length of the shortest cycle.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(V * (V + E)), where V is the number of vertices and E is the number of edges.
 * - We perform a BFS for every vertex (V iterations).
 * - Each BFS takes O(V + E) time.
 * - Total complexity: O(V * (V + E)).
 * - Given constraints V <= 10^3 and E <= 10^3, the maximum operations is 
 * approximately 10^3 * (10^3 + 10^3) = 2 * 10^6, which is very fast.
 * * Space Complexity Analysis:
 * --------------------------
 * O(V + E)
 * - O(V + E) to store the adjacency list.
 * - O(V) for the distance array (`dist`), parent array (`parent`), and the BFS queue.
 * - Overall complexity: O(V + E).
 */
// Optimal Solution in Java - 
import java.util.*;

class Solution {
    public int shortCycle(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        int shortestCycle = Integer.MAX_VALUE;

        for (int start = 0; start < V; start++) {
            int[] dist = new int[V];
            Arrays.fill(dist, -1);
            int[] parent = new int[V];
            Arrays.fill(parent, -1);

            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            dist[start] = 0;

            while (!q.isEmpty()) {
                int u = q.poll();

                for (int v : adj.get(u)) {
                    if (dist[v] == -1) { 
                        dist[v] = dist[u] + 1;
                        parent[v] = u;
                        q.add(v);
                    }
                    else if (parent[u] != v) {
                        int cycleLength = dist[u] + dist[v] + 1;
                        shortestCycle = Math.min(shortestCycle, cycleLength);
                    }
                }
            }
        }
        return (shortestCycle == Integer.MAX_VALUE) ? -1 : shortestCycle;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        int V1 = 7;
        int[][] edges1 = {{0,5},{0,6},{5,1},{6,1},{6,2},{2,3},{3,4},{1,4}};
        System.out.println(sol.shortCycle(V1, edges1)); 

        int V2 = 7;
        int[][] edges2 = {{0,5},{0,6},{1,2},{1,4},{1,5},{1,6},{2,6},{2,3},{3,4}};
        System.out.println(sol.shortCycle(V2, edges2)); 
    }
}
