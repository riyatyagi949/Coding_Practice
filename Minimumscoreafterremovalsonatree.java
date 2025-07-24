/*
Problem Statement:
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
You are given a 0-indexed integer array nums of length n where nums[i] represents the value of the ith node. You are also given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
Remove two distinct edges of the tree to form three connected components. For a pair of removed edges, the following steps are defined:
1. Get the XOR of all the values of the nodes for each of the three components respectively.
2. The difference between the largest XOR value and the smallest XOR value is the score of the pair.
Return the minimum score of any possible pair of edge removals on the given tree.

Approach:
The problem asks us to find the minimum score achievable by removing two distinct edges from a tree, which splits it into three connected components. The score is defined as the difference between the maximum and minimum XOR sums of the three components.

Since N is up to 1000, an O(N^2) or O(N^2 log N) solution might be acceptable.
A brute-force approach would be to iterate through all possible pairs of edges to remove. If we remove two edges, say (u1, v1) and (u2, v2), it's complex to determine the three components and their XOR sums directly.

A more effective approach involves recognizing that removing an edge (u, v) splits the tree into two components. If we remove two edges, say (edge1, edge2), we create three components.

Let's first precompute the XOR sum of all nodes in each subtree. We can do this using a DFS traversal. `subtreeXor[i]` will store the XOR sum of all node values in the subtree rooted at `i` (when considering node 0 as the root, for example).

Let the total XOR sum of all nodes in the tree be `totalXor`.

When we remove an edge (u, v), where `v` is a child of `u`, the tree is split into two parts: the subtree rooted at `v`, and the rest of the tree. The XOR sum of the subtree rooted at `v` is `subtreeXor[v]`. The XOR sum of the remaining part is `totalXor ^ subtreeXor[v]`.

Now, consider removing two edges.
Let's iterate through all possible pairs of nodes (i, j) such that `i` is an ancestor of `j`, or `j` is an ancestor of `i`, or they are in different subtrees relative to their common ancestor. This defines the "cuts" we are making.

The key insight is that removing two edges (u1, v1) and (u2, v2) divides the tree into three components.
Consider the case where `v1` is in the subtree of `u1`, and `v2` is in the subtree of `u2`.
There are two main scenarios for the removed edges:
1. The two removed edges are "nested", meaning one component created by the first cut contains the second cut. For example, edge (p, u) and edge (u, v) are removed, where p is parent of u, and u is parent of v.
   - Component 1: subtree rooted at `v`. Its XOR sum is `subtreeXor[v]`.
   - Component 2: nodes in subtree `u` excluding subtree `v`. Its XOR sum is `subtreeXor[u] ^ subtreeXor[v]`.
   - Component 3: the rest of the tree. Its XOR sum is `totalXor ^ subtreeXor[u]`.

2. The two removed edges are "parallel", meaning the subtrees formed by cutting them are disjoint. For example, edge (p, u) and edge (q, v) are removed, where u and v are in different branches from a common ancestor.
   - Component 1: subtree rooted at `u`. Its XOR sum is `subtreeXor[u]`.
   - Component 2: subtree rooted at `v`. Its XOR sum is `subtreeXor[v]`.
   - Component 3: the rest of the tree. Its XOR sum is `totalXor ^ subtreeXor[u] ^ subtreeXor[v]`.

We can perform a DFS traversal. During the DFS, we calculate the subtree XOR sum for each node. We also keep track of the `parent` of each node in the DFS tree.
The total XOR sum of the whole tree (`totalXor`) can be calculated once.

After computing `subtreeXor` for all nodes, we can iterate through all possible pairs of nodes `(i, j)` where `i != j`.
For each pair `(i, j)`, we need to determine which node is an ancestor of the other, or if they are in different subtrees relative to the DFS root.
A simpler approach is to use the `dfsOrder` (entry and exit times) to determine ancestor-descendant relationships.
`entry[u]` and `exit[u]` denote the time we enter and exit node `u` during DFS.
`u` is an ancestor of `v` if `entry[u] <= entry[v]` and `exit[u] >= exit[v]`.

Let's maintain an adjacency list for the tree.
`adj[u]` stores neighbors of `u`.
`subtreeXor[u]` stores the XOR sum of nodes in the subtree rooted at `u` (considering a rooted tree, say, at node 0).

Algorithm:
1. Build the adjacency list from `edges`.
2. Perform a DFS from an arbitrary root (e.g., node 0) to:
   a. Compute `subtreeXor[u]` for all `u`. `subtreeXor[u]` is `nums[u]` XORed with `subtreeXor` of all its children.
   b. Store `parent[u]` for each `u`.
   c. Store `entryTime[u]` and `exitTime[u]` for each `u` to determine ancestor-descendant relationships.
3. Calculate `totalXor = subtreeXor[0]`.
4. Initialize `minScore = Integer.MAX_VALUE`.
5. Iterate through all possible first cut edges. An edge (u, v) means we cut the connection between `u` and `parent[u]` (assuming `u` is not the root) or between `v` and `parent[v]`.
   The actual "cut" is effectively removing the child's subtree from its parent. So we can iterate through all nodes `i` from `1` to `n-1` (excluding the root). Cutting the edge connecting `i` to `parent[i]` gives us the `subtreeXor[i]` as one component and `totalXor ^ subtreeXor[i]` as the other.
   Let `xor1 = subtreeXor[i]`. The other part is `totalXor ^ xor1`.

6. Now, for the second cut: iterate through all possible nodes `j` from `1` to `n-1` where `j != i`.
   If `i` is an ancestor of `j` (meaning `entryTime[i] <= entryTime[j]` and `exitTime[i] >= exitTime[j]`):
     - `C1 = subtreeXor[j]`
     - `C2 = subtreeXor[i] ^ subtreeXor[j]`
     - `C3 = totalXor ^ subtreeXor[i]`
   If `j` is an ancestor of `i` (meaning `entryTime[j] <= entryTime[i]` and `exitTime[j] >= exitTime[i]`):
     - This case is symmetric to the previous one. We can swap `i` and `j` and process. Or more directly:
     - `C1 = subtreeXor[i]`
     - `C2 = subtreeXor[j] ^ subtreeXor[i]`
     - `C3 = totalXor ^ subtreeXor[j]`
   If `i` and `j` are in different subtrees (neither is an ancestor of the other):
     - `C1 = subtreeXor[i]`
     - `C2 = subtreeXor[j]`
     - `C3 = totalXor ^ subtreeXor[i] ^ subtreeXor[j]`

   After getting `C1, C2, C3`, calculate `max_val = max(C1, C2, C3)` and `min_val = min(C1, C2, C3)`.
   Update `minScore = min(minScore, max_val - min_val)`.

The `N` nodes are up to 1000. Iterating through all pairs of nodes `(i, j)` results in `O(N^2)` iterations. Inside the loop, calculations are `O(1)`. The DFS is `O(N)`. So, the overall time complexity is `O(N^2)`.

Let's refine the iteration for removing edges. Instead of nodes `i` and `j`, let's think about *edges*.
An edge can be identified by its child node (if we root the tree at 0).
So, we can iterate `i` from `1` to `n-1` (representing the edge `(i, parent[i])`). This is our first cut.
The two components formed are `subtreeXor[i]` and `totalXor ^ subtreeXor[i]`.
Then iterate `j` from `1` to `n-1`, where `j != i` (representing the edge `(j, parent[j])`). This is our second cut.

When `i` and `j` represent the nodes that are the 'child' endpoint of the removed edges:
Case 1: `j` is in the subtree of `i` (i.e., `i` is an ancestor of `j`).
   - Cut 1: `(parent[i], i)`. This separates `subtreeXor[i]`.
   - Cut 2: `(parent[j], j)`. This separates `subtreeXor[j]` from `subtreeXor[i]`.
   The three XORs are:
   `x1 = subtreeXor[j]` (component with `j`'s subtree)
   `x2 = subtreeXor[i] ^ subtreeXor[j]` (component with `i`'s subtree excluding `j`'s subtree)
   `x3 = totalXor ^ subtreeXor[i]` (remaining part of the tree)

Case 2: `i` is in the subtree of `j` (i.e., `j` is an ancestor of `i`).
   This is symmetric to Case 1. Swap `i` and `j`.
   `x1 = subtreeXor[i]`
   `x2 = subtreeXor[j] ^ subtreeXor[i]`
   `x3 = totalXor ^ subtreeXor[j]`

Case 3: `i` and `j` are in different subtrees (neither is an ancestor of the other).
   - Cut 1: `(parent[i], i)`. This separates `subtreeXor[i]`.
   - Cut 2: `(parent[j], j)`. This separates `subtreeXor[j]`.
   The three XORs are:
   `x1 = subtreeXor[i]`
   `x2 = subtreeXor[j]`
   `x3 = totalXor ^ subtreeXor[i] ^ subtreeXor[j]`

The base case for N=3, there is only 1 way to remove 2 edges. For example, 0-1-2. Remove (0,1) and (1,2). Components are [0], [1], [2]. XORs are nums[0], nums[1], nums[2].

Implementation details:
`adj`: `List<List<Integer>>`
`subtreeXor`: `int[]`
`parent`: `int[]` (not strictly needed if using entry/exit times, but helpful for clarity in DFS and reasoning about edges)
`entryTime`, `exitTime`: `int[]`
`timer`: `int`

DFS function:
`dfs(u, p, nums, adj, subtreeXor, entryTime, exitTime, timer)`
  `parent[u] = p` (or we can just pass `p` for the current call)
  `entryTime[u] = timer++`
  `subtreeXor[u] = nums[u]`
  `for v in adj.get(u)`:
    `if v != p`:
      `dfs(v, u, ...)`
      `subtreeXor[u] ^= subtreeXor[v]`
  `exitTime[u] = timer++`

Root the DFS at node 0. `dfs(0, -1, ...)`
 */

//  Time Complexity:

// Building adjacency list: O(N + E), where E = N-1 for a tree, so O(N).
// DFS traversal: O(N) to compute subtreeXor, entryTime, exitTime for all nodes.
// Iterating through pairs of nodes (i, j): There are O(N^2) such pairs.
// Inside the loop, isAncestor check and XOR calculations are O(1).
// Total time complexity: O(N^2). Given N <= 1000, N^2 = 10^6, which is acceptable.

// Space Complexity:

// Adjacency list: O(N + E) = O(N).
// nums, subtreeXor, entryTime, exitTime arrays: O(N).
// Recursion stack depth for DFS: O(N) in the worst case (skewed tree).
// Total space complexity: O(N).

import java.util.*;

class Solution {
    int[] xor; 
    // to store XOR of subtree rooted at each node
    int[] in, out; 
    int time = 0;
    int totalXor = 0;
    List<Integer>[] graph;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        xor = new int[n];
        in = new int[n];
        out = new int[n];
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        dfs(0, -1, nums);

        int res = Integer.MAX_VALUE;

        List<int[]> directedEdges = new ArrayList<>();
        for (int[] e : edges) {
            if (in[e[0]] < in[e[1]]) directedEdges.add(new int[]{e[1], e[0]});
            else directedEdges.add(new int[]{e[0], e[1]});
        }

        for (int i = 0; i < directedEdges.size(); i++) {
            for (int j = i + 1; j < directedEdges.size(); j++) {
                int a = directedEdges.get(i)[0];
                int b = directedEdges.get(j)[0];

                int x, y, z;

                if (isAncestor(a, b)) {
                    x = xor[b];
                    y = xor[a] ^ xor[b];
                    z = totalXor ^ xor[a];
                } 
                else if (isAncestor(b, a)) {
                    x = xor[a];
                    y = xor[b] ^ xor[a];
                    z = totalXor ^ xor[b];
                }
                 else {
                    x = xor[a];
                    y = xor[b];
                    z = totalXor ^ xor[a] ^ xor[b];
                }
                int max = Math.max(x, Math.max(y, z));
                int min = Math.min(x, Math.min(y, z));
                res = Math.min(res, max - min);
            }
        }
        return res;
    }
    
    private void dfs(int node, int parent, int[] nums) {
        in[node] = ++time;
        xor[node] = nums[node];
        for (int neighbor : graph[node]) {
            if (neighbor != parent) {
                dfs(neighbor, node, nums);
                xor[node] ^= xor[neighbor];
            }
        }
        out[node] = time;
        totalXor = xor[0];
    }
    private boolean isAncestor(int u, int v) {
        return in[u] <= in[v] && out[v] <= out[u];
    }
}


