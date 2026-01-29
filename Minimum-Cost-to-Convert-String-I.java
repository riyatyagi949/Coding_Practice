/**
 * PROBLEM STATEMENT: 2976. Minimum Cost to Convert String I
 * --------------------------------------------------------------------------------
 * You are given two strings 'source' and 'target' of length n, consisting of 
 * lowercase English letters. You are also given character arrays 'original' and 
 * 'changed', and an integer array 'cost'.
 * * cost[i] represents the cost to change original[i] to changed[i].
 * * You can perform any number of operations. You want to convert 'source' to 
 * 'target' with the minimum total cost.
 * * Return the minimum cost, or -1 if the conversion is impossible.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: FLOYD-WARSHALL ALGORITHM
 * --------------------------------------------------------------------------------
 * 1. The Core Idea:
 * Since we are dealing with lowercase English letters, there are only 26 
 * possible characters. This suggests we can model the character conversions 
 * as a directed graph where:
 * - Nodes: Characters 'a' through 'z' (0 to 25).
 * - Edges: A directed edge exists from original[i] to changed[i] with weight cost[i].
 * * 2. Precomputing Shortest Paths:
 * To find the minimum cost to convert any character 'x' to 'y', we need the 
 * shortest path between them in the conversion graph. Because the number of 
 * nodes is very small (V=26), the Floyd-Warshall algorithm is perfect for 
 * computing all-pairs shortest paths.
 * * 3. Calculating Result:
 * Once the 26x26 distance matrix is ready:
 * - Iterate through source[i] and target[i].
 * - If source[i] != target[i], add the precomputed shortest path cost to the total.
 * - If any conversion is impossible (distance remains INF), return -1.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(E + V^3 + N)
 * - E: Number of edges in 'original' array (up to 2000).
 * - V^3: Floyd-Warshall on 26 characters (26^3 = 17,576 operations).
 * - N: Length of source/target strings (up to 10^5).
 * - Total: Very efficient given the small alphabet size.
 * * Space Complexity: O(V^2)
 * - We store a 26x26 matrix to keep track of conversion costs.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public long minimumCost(
            String source,
            String target,
            char[] original,
            char[] changed,
            int[] cost) {

        int INF = Integer.MAX_VALUE / 2;
        int[][] dist = new int[26][26];

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }
        for (int i = 0; i < original.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }
         for (int k = 0; k < 26; k++) {
         for (int i = 0; i < 26; i++) {
         for (int j = 0; j < 26; j++) {
         dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        long ans = 0;

        for (int i = 0; i < source.length(); i++) {
            int s = source.charAt(i) - 'a';
            int t = target.charAt(i) - 'a';

            if (dist[s][t] >= INF)
             return -1;
            ans += dist[s][t];
        }
        return ans;
    }
}


