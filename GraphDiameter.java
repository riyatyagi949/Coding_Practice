/**
 * Problem Statement: Graph Diameter
 * ---------------------------------
 * Given an undirected graph with V vertices (0 to V-1) and E edges, find the 
 * diameter of the graph. The diameter is defined as the number of edges on the 
 * longest path between any two vertices in the graph.
 * (Note: The constraints E <= V - 1 suggest the input graph is either a tree or a forest. 
 * Since we are asked for 'the' diameter, we assume a connected component or tree).
 *
 * Constraints:
 * 2 <= V <= 10^5
 * 1 <= E <= V - 1
 *//**
     * Optimal Solution: Two BFS Algorithm (for Trees/Connected Graphs)
     * ----------------------------------------------------------------
     * The diameter of a connected graph (especially a tree) can be found in two steps:
     * 1. **First BFS:** Start from an arbitrary vertex (e.g., vertex 0) and find the 
     * vertex farthest from it. Let this farthest vertex be 'A'.
     * 2. **Second BFS:** Start a second BFS from vertex 'A'. The vertex farthest from 
     * 'A' in this second search is the other endpoint of the diameter, 'B'.
     * The distance between 'A' and 'B' is the graph's diameter.
     *
     * This greedy approach works because any longest path (diameter) must start at a 
     * leaf node (or an endpoint of a longest path) relative to the arbitrary starting node.
     *
     * @param V The number of vertices.
     * @param E The number of edges (unused directly, but defines array size).
     * @param edges The array representing the undirected edges.
     * @return The diameter of the graph (longest path length in edges).
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(V + E), where V is the number of vertices and E is the number of edges.
 * - **Graph Construction:** Building the adjacency list takes O(V + E) time.
 * - **First BFS:** BFS explores every vertex and every edge once, taking O(V + E) time.
 * - **Second BFS:** Similarly, the second BFS takes O(V + E) time.
 * - Total complexity: O(V + E). Given the constraint E <= V - 1 (for a single component/tree), 
 * this is effectively O(V).
 * * * Space Complexity Analysis:
 * --------------------------
 * O(V + E), where V is the number of vertices and E is the number of edges.
 * - O(V + E) is required to store the adjacency list representation of the graph.
 * - O(V) is required for the distance array (`dist`) and the BFS queue.
 * - Overall complexity: O(V + E).
 */
// Optimal Solution in Java -
import java.util.*;

class Solution {
    public int diameter(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        int farthestNode = bfsToFindFarthest(V, adj, 0)[0];
        int diameter = bfsToFindFarthest(V, adj, farthestNode)[1];

        return diameter;
    }
    private int[] bfsToFindFarthest(int V, List<List<Integer>> adj, int start)
    {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[V];
        int[] dist = new int[V];

        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    dist[neighbor] = dist[node] + 1;
                    q.add(neighbor);
                }
            }
        }
        int farthestNode = start;
        int maxDist = 0;
        for (int i = 0; i < V; i++) {
            if (dist[i] > maxDist) {
                maxDist = dist[i];
                farthestNode = i;
            }
        }
        return new int[]{farthestNode, maxDist};
    }
}

