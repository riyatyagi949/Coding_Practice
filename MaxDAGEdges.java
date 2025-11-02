/**
 * Problem Statement: Max DAG Edges
 * --------------------------------
 * Given a directed acyclic graph (DAG) with V vertices (0 to V-1) and E edges, 
 * find the maximum number of additional edges that can be added to the graph 
 * without forming any cycles.
 *
 * The maximum number of edges in a DAG is achieved when its graph structure 
 * becomes the transitive closure of itself, meaning an edge (u, v) exists 
 * if and only if there is a path from u to v.
 *
 * Constraints:
 * 1 <= V <= 1000
 * 0 <= E <= (V*(V-1))/2
 *//**
     * Optimal Solution: Transitive Closure (Reachability)
     * ---------------------------------------------------
     * The maximum number of edges a DAG can have is the total number of ordered 
     * pairs (u, v) such that there is a path from u to v. This is the size of the 
     * **transitive closure**.
     * * The problem asks for the number of *additional* edges, which is:
     * Max Edges in DAG - Initial Edges (E)
     * = (Total reachable pairs (u, v)) - E
     *
     * We use the **Floyd-Warshall Algorithm** (or V separate BFS/DFS traversals) 
     * to compute reachability (transitive closure). Since V is up to 1000, 
     * the O(V^3) complexity of Floyd-Warshall is acceptable.
     *
     * * Algorithm Steps:
     * 1. Initialize a V x V boolean matrix `reachable` where `reachable[u][v]` is true 
     * if there is an edge or path from `u` to `v`.
     * 2. Initialize `reachable[u][v] = true` for all given initial edges (u, v).
     * 3. Apply the Floyd-Warshall logic to compute the transitive closure:
     * `reachable[i][j] = reachable[i][j] || (reachable[i][k] && reachable[k][j])`
     * This finds all paths of length 2 or more.
     * 4. Count the total number of reachable pairs (u, v) where u != v. This is the 
     * **maximum number of edges** possible.
     * 5. The result is (Total reachable pairs) - E.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(V^3), where V is the number of vertices.
 * - This complexity comes from the triple nested loop structure of the Floyd-Warshall 
 * algorithm, which is necessary to compute the transitive closure. Given V <= 1000, 
 * V^3 is up to 10^9, which might be tight, but is generally accepted for this approach 
 * with this constraint.
 * * Alternative approach: V separate BFS/DFS traversals would yield O(V * (V + E)). 
 * Since E can be up to O(V^2), the overall complexity is also O(V^3) in the worst case.
 *
 * * Space Complexity Analysis:
 * --------------------------
 * O(V^2), where V is the number of vertices.
 * - This space is required to store the V x V boolean matrix `reachable` which holds 
 * the transitive closure.
 */
// Optimal Solution in Java - 
import java.util.*;