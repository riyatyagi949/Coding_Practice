/**
 * Problem Statement: Course Schedule II
 * -------------------------------------
 * Given 'n' courses (0 to n-1) and a 2D array 'prerequisites' where 
 * prerequisites[i] = [x, y] means course 'x' requires course 'y' as a prerequisite 
 * (y -> x), find a valid ordering of courses to complete all of them.
 * If it is impossible to finish all courses (i.e., a cycle exists), return an empty array.
 * * This is a classic Topological Sort problem.
 *
 * Constraints:
 * 1 <= n <= 10^4
 * 0 <= prerequisites.size() <= 10^5
 *//**
     * Optimal Solution: Topological Sort using Kahn's Algorithm (BFS)
     * -------------------------------------------------------------
     * Kahn's algorithm is an efficient way to perform topological sorting.
     * 1. **Graph Representation:** Build an Adjacency List (adj) and an In-degree array (indegree).
     * - `adj[y]` contains all courses `x` for which `y` is a prerequisite (y -> x).
     * - `indegree[x]` is the count of prerequisites for course `x`.
     * 2. **Initialization:** Add all courses with an `indegree` of 0 (no prerequisites) to a Queue.
     * 3. **BFS Process:** While the queue is not empty:
     * - Dequeue a course `u`. Add `u` to the result list.
     * - For every neighbor `v` of `u` (i.e., courses that require `u`):
     * - Decrement `indegree[v]`.
     * - If `indegree[v]` becomes 0, enqueue `v`.
     * 4. **Result Check:** If the size of the result list equals 'n', a valid ordering was found. 
     * Otherwise, a cycle exists, and it's impossible to finish all courses.
     * * @param n The total number of courses.
     * @param prerequisites The prerequisite pairs.
     * @return An array containing a valid course order, or an empty array if impossible.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(V + E), where V is the number of vertices (courses, n) and E is the number of edges (prerequisites).
 * - Graph construction (building adj and indegree): O(E).
 * - BFS traversal (Kahn's algorithm): Each vertex is added to the queue and processed once (O(V)). 
 * Each edge is checked once when a neighbor's in-degree is decremented (O(E)).
 * - Total complexity: O(V + E).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(V + E), where V is the number of vertices and E is the number of edges.
 * - O(V + E) for the Adjacency List (`adj`).
 * - O(V) for the In-degree array (`indegree`).
 * - O(V) for the Queue and the result array.
 * - Overall complexity: O(V + E).
 */
// Optimal Solution in Java - 
import java.util.*;

class Solution {
    public ArrayList<Integer> findOrder(int n, int[][] prerequisites)
    {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[n];
        
        for (int[] prerequisite : prerequisites)
        {
            int course = prerequisite[0]; 
            int prereq = prerequisite[1];  

            adj.get(prereq).add(course);
            inDegree[course]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
        {
            if (inDegree[i] == 0)
            {
                q.add(i);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        
        while (!q.isEmpty()) 
        {
            int u = q.poll();
            result.add(u);
            
            for (int v : adj.get(u))
            {
                inDegree[v]--;
                    if (inDegree[v] == 0)
                    {
                    q.add(v);
                }
            }
        }
        if (result.size() == n)
        {
            return result;
        }
        else
        {
            return new ArrayList<>(); 
        }
    }
}