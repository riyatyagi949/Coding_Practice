/**
 * Problem Statement: Power Grid Maintenance
 * -----------------------------------------
 * Given 'c' power stations (IDs 1 to c) and 'n' connections.
 * Stations form power grids (connected components). Initially, all are online.
 * Queries:
 * 1. [1, x]: Check station x. If x is online, result is x. If x is offline, 
 * result is the smallest ID of an operational station in x's grid. If none, result is -1.
 * 2. [2, x]: Station x goes offline (non-operational).
 * Return results for type 1 queries. Connectivity remains unchanged by offline status.
 *
 * Constraints: V, E, Q <= 2 * 10^5
 *//**
     * Optimal Solution: DSU combined with HashMap of TreeSets
     * -------------------------------------------------------
     * 1. **DSU for Grids:** Use DSU to pre-calculate the final connected components (the power grids). 
     * Since the connections *never change* and offline stations remain part of the grid, 
     * the grid structure is static.
     * 2. **Tracking Online Stations:** Maintain a boolean array `online` for the status of each station.
     * 3. **Tracking Smallest Online ID:** Use a `HashMap<Integer, TreeSet<Integer>>` where:
     * - Key: The root of a component (from DSU).
     * - Value: A `TreeSet` containing the IDs of all **currently online** stations in that grid. 
     * `TreeSet` guarantees the smallest ID is retrievable in O(log N) time via `set.first()`.
     * 4. **Query Processing:**
     * - **Type 1 ([1, x]):** Find the root `r` of `x`. If `x` is online, return `x`. If offline, 
     * check `gridMap.get(r).first()`.
     * - **Type 2 ([2, x]):** Set `online[x] = false` and remove `x` from the corresponding `TreeSet`.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * Let V be the number of stations (c), E be the number of connections (n), and Q be the number of queries.
 * 1. **DSU Initialization & Union:** O(V + E * α(V)). The initial DSU setup is dominated by the near-constant time Union operations.
 * 2. **GridMap Initialization:** O(V * α(V) + V * log V). O(V * α(V)) for finds and O(V * log V) for adding V elements into TreeSets (max size V).
 * 3. **Query Processing:** O(Q * (α(V) + log V)).
 * - Type 1: O(α(V)) for DSU find + O(log V) for TreeSet.first().
 * - Type 2: O(α(V)) for DSU find + O(log V) for TreeSet.remove().
 * - **Overall Complexity:** O(V * log V + (E + Q) * (α(V) + log V)). Since V, E, Q are up to 2*10^5, this efficient structure is necessary.
 * * Space Complexity Analysis:
 * --------------------------
 * O(V)
 * - O(V) for the DSU arrays (`parent`, `rank`).
 * - O(V) for the `online` status array.
 * - O(V) for the `gridMap` structure, as the total number of elements across all `TreeSet`s is V.
 * - Overall complexity: O(V).
 */
// Optimal Solution in Java - 
import java.util.*;

class Solution {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DSU dsu = new DSU(c + 1);
         for (int[] conn : connections) {
            dsu.union(conn[0], conn[1]);
        }
        Map<Integer, TreeSet<Integer>> gridMap = new HashMap<>();
        for (int i = 1; i <= c; i++)
         {
            int root = dsu.find(i);
            gridMap.computeIfAbsent(root, k -> new TreeSet<>()).add(i);
        }
        boolean[] online = new boolean[c + 1];
        Arrays.fill(online, true);

        List<Integer> result = new ArrayList<>();
        for (int[] q : queries) {
            int type = q[0];
            int x = q[1];
            int root = dsu.find(x);
            TreeSet<Integer> set = gridMap.get(root);

            if (type == 1) { 
                if (online[x]) {
                    result.add(x);
                } 
                else {
                    if (set.isEmpty()) result.add(-1);
                    else result.add(set.first());
                }
            } else {
                if (online[x]) {
                    online[x] = false;
                    set.remove(x);
                }
            }
        }
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) 
        ans[i] = result.get(i);
        return ans;
    }
    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x)
         {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int a, int b)
         {
            int pa = find(a), pb = find(b);
            if (pa == pb)
             return;
             
            if (rank[pa] < rank[pb]) parent[pa] = pb;
            else if (rank[pb] < rank[pa]) parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
        }
    }
}