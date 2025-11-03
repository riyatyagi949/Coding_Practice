/**
 * Problem Statement: Safe States
 * ------------------------------
 * Given a directed graph with V vertices (0 to V-1) and E directed edges, 
 * return all "Safe Nodes" of the graph.
 * A terminal node is a vertex with no outgoing edges. 
 * A vertex is considered safe if every path starting from it eventually 
 * reaches a terminal node. This means a safe node cannot be part of a cycle
 * or lead to a cycle.
 *
 * Constraints: 1 <= V, E <= 10^5
 *//**
     * Optimal Solution: Depth-First Search (DFS) with States
     * --------------------------------------------------------
     * The most efficient approach for this problem is using DFS combined with 
     * memoization (the 'state' array) to detect cycles and determine safety.
     * * A node 'u' is UNSAFE if:
     * 1. It is currently being visited (state 1) and we try to revisit it (cycle detected).
     * 2. Any of its neighbors 'v' is already marked UNSAFE (state 1).
     * * * Algorithm:
     * 1. Build the adjacency list.
     * 2. Use a state array (0, 1, 2) to track the status of each node.
     * 3. Iterate through all nodes (0 to V-1) and call the recursive `isSafe` function.
     * 4. In `isSafe(u)`:
     * a. If `u` is already marked UNSAFE (1), return false (cycle detected/reached).
     * b. If `u` is already marked SAFE (2), return true.
     * c. Mark `u` as **UNSAFE (1)** before processing its neighbors. This is the 
     * key for cycle detection in the current path.
     * d. Recursively check all neighbors. If any neighbor returns false, `u` is UNSAFE, 
     * and we implicitly return false through the stack.
     * e. If the loop completes, all paths from `u` are safe. Mark `u` as **SAFE (2)**.
     * 5. Collect all nodes `i` for which `isSafe(i)` returns true.
     *
     * @param V The number of vertices.
     * @param E The number of edges (unused directly, but defines array size).
     * @param edges The array representing the directed edges.
     * @return A list of safe nodes, sorted by their index.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(V + E), where V is the number of vertices and E is the number of edges.
 * - **Graph Construction:** Building the adjacency list takes O(V + E).
 * - **DFS:** Although the DFS is called V times, each node is visited and its state 
 * determined exactly once (due to the memoization using the `state` array). 
 * Each edge is traversed at most once during the DFS across all calls.
 * - Total complexity: O(V + E).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(V + E), where V is the number of vertices and E is the number of edges.
 * - O(V + E) for storing the adjacency list (`adj`).
 * - O(V) for the state array (`state`).
 * - O(V) for the recursion stack depth in the worst case (a simple path).
 * - Overall complexity: O(V + E).
 */
// Optimal Solution in Java -
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
