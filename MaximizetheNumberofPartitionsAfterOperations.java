/**
 * Problem Statement: Maximize the Number of Partitions After Operations
 * --------------------------------------------------------------------------------------
 * You are given a string 's' and an integer 'k'.
 * First, you are allowed to change at most one index in 's' to any other lowercase English letter.
 * After that, the string is partitioned greedily:
 * 1. Choose the **longest** prefix of the remaining string containing at most 'k' distinct characters.
 * 2. Delete the prefix and increment the partition count.
 * 3. Repeat until 's' is empty.
 * * Return the maximum number of resulting partitions by optimally choosing at most one index to change.
 *
 * Constraints:
 * 1 <= s.length <= 10^4
 * 1 <= k <= 26
 */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N^2), where N is the length of the string s.
 * 1. Pre-calculation of `suffixPartitionCount` (O(N)):
 * The outer loop runs N times. The inner loop (j) runs at most N times. 
 * The `HashSet` operations (add, size) are O(1) due to the constant alphabet size (26). 
 * Total: O(N * N) = O(N^2).
 * * *Correction*: Since the inner loop breaks as soon as distinct characters > K, 
 * the total number of set insertions across all prefixes is bounded by N * K = O(N).
 * Therefore, the pre-calculation of $suffixPartitionCount$ is O(N).
 * * 2. Main Logic: Iterate through change index 'i' (O(N)).
 * Inside the loop, we iterate through the split point 'j' (O(N)).
 * The set operations are O(1).
 * Total: O(N * N) = O(N^2).
 *
 * * The overall complexity is dominated by the $O(N^2)$ step where we test every
 * possible position $i$ for the change and every possible end point $j$ for the 
 * first partition. (The $O(N)$ solution involves a complex $O(1)$ lookup for the 
 * inner loop's effect, which is often not expected).
 * * $N \le 10^4$, $N^2 \approx 10^8$, which is acceptable but borderline.
 * * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the length of the string s.
 * We use O(N) space for the `suffixPartitionCount` array and the `prefixPartitionCount` array. 
 * The `HashSet` takes O(K) space (K <= 26) which is constant.
 */
// Optimal Solution in Java -