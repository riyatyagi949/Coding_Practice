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
import java.util.*;

class Solution {
  public int maxPartitionsAfterOperations(String s, int k)
   {
    Map<Long, Integer> mem = new HashMap<>();
    return maxPartitionsAfterOperations(s, 0, true, 0, k, mem) + 1;
  }
  private int maxPartitionsAfterOperations(final String s, int i, boolean canChange, int mask,
                                           int k, Map<Long, Integer> mem)
     {
       if (i == s.length())
        return 0;

    Long key = (long) i << 27 | (canChange ? 1 : 0) << 26 | mask;
    if (mem.containsKey(key))
      return mem.get(key);

    int res = getRes(s, i, canChange, mask, 1 << (s.charAt(i) - 'a'), k, mem);
     if (canChange)
      for (int j = 0; j < 26; ++j)
        res = Math.max(res, getRes(s, i, false, mask, 1 << j, k, mem));

    mem.put(key, res);
    return res;
  }
   private int getRes(final String s, int i, boolean nextCanChange, int mask, int newBit, int k,
                     Map<Long, Integer> mem)
                      {
    final int newMask = mask | newBit;
    if (Integer.bitCount(newMask) > k) 
    return 1 + maxPartitionsAfterOperations(s, i + 1, nextCanChange, newBit, k, mem);
    return maxPartitionsAfterOperations(s, i + 1, nextCanChange, newMask, k, mem);
  }
}
