/**
 * Problem Statement: Find X-Sum of All K-Long Subarrays II
 * --------------------------------------------------------
 * Given an array 'nums', and integers 'k' and 'x'.
 * The X-sum of an array is the sum of occurrences of the top 'x' most frequent elements.
 * Tie-breaker rule: Elements with the same frequency are ranked by value (bigger value is more frequent).
 * Return an array 'answer' where answer[i] is the X-sum of the subarray nums[i..i + k - 1].
 *
 * Constraints:
 * 1 <= n <= 10^5
 * 1 <= x <= k <= n
 * 1 <= nums[i] <= 10^9
 *//**
     * Optimal Solution: Sliding Window with Two Balanced BSTs (TreeSets)
     * ------------------------------------------------------------------
     * A naive approach (recalculating the X-sum for every window) would be O(N * k) or O(N^2), 
     * which is too slow. The optimal solution uses a **Sliding Window** (O(N) moves) and 
     * maintains the top 'x' elements efficiently (O(log U) per move, where U is unique elements, U <= N).
     *
     * * Data Structures:
     * 1. **freq (HashMap<Integer, Integer>):** Tracks the count of each unique number in the current window.
     * 2. **nodeMap (HashMap<Integer, int[]>):** Maps a number to its unique node representation (value, frequency).
     * This is crucial because a number's frequency changes, meaning its "node" must be updated 
     * in the TreeSets by removal and re-insertion.
     * 3. **comp (Comparator<int[]>):** Defines the priority order: 
     * - Higher frequency first (b[1] - a[1]).
     * - Tie-breaker: Higher value first (b[0] - a[0]).
     * 4. **topX (TreeSet<int[]>):** Stores the nodes of the top 'x' most frequent elements.
     * 5. **others (TreeSet<int[]>):** Stores the nodes of the remaining elements.
     * 6. **sumTopX (long):** The running X-sum (sum of [value * frequency] for elements in `topX`).
     *
     * * Algorithm:
     * The process iterates through the array, effectively sliding the window one step right:
     * 1. **Add Element (`nums[i]`):**
     * - Update `freq[num]`.
     * - Remove the old node (old frequency) from `topX` or `others`.
     * - Create a new node (new frequency) and add it to `others`.
     * 2. **Balance (`topX` and `others`):**
     * - If `topX.size() < x`, move the best element from `others` to `topX`.
     * - If both are full, check if the worst element in `topX` should be swapped with the best element in `others` based on the comparator.
     * 3. **Remove Element (`nums[i - k]`):** (Only after the window is full)
     * - Update `freq[out]` (decrement).
     * - Remove the old node from `topX` or `others`.
     * - If `newF > 0`, create a new node (new frequency) and add it to `others`.
     * - Re-balance using the same logic as step 2.
     * 4. Record `sumTopX` as the answer for the current window.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N * log U), where N is the length of 'nums' and U is the number of unique elements (U <= N).
 * - The algorithm iterates through the array once (O(N) iterations).
 * - In each iteration, the operation involves:
 * - HashMap lookups/updates: O(1) average time.
 * - TreeSet (Balanced BST) operations: Each element change requires a remove and an insert. 
 * Since the number of unique elements U is at most N, each `remove`/`add` operation takes 
 * O(log U) time.
 * - The constant number of set operations (up to 6 per iteration for two elements leaving/entering) 
 * multiplies the logarithmic factor.
 * - Overall complexity: O(N log U). Since U <= N, this is at most O(N log N).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(U), where U is the number of unique elements in 'nums'.
 * - The `freq`, `nodeMap`, `topX`, and `others` data structures all store information proportional 
 * to the number of unique elements (U) in the current window, which is at most U (total unique elements).
 * - The auxiliary space used is therefore O(U).
 */
// Optimal Solution in Java - 
import java.util.HashMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;

class Solution {
    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] ans = new long[n - k + 1];
        
        Map<Integer, Integer> freq = new HashMap<>();
        
             Comparator<int[]> comp = (a, b) -> 
             {
            if (a[1] != b[1])
             return b[1] - a[1];
            return b[0] - a[0];
        };
        TreeSet<int[]> topX = new TreeSet<>(comp);
        TreeSet<int[]> others = new TreeSet<>(comp);
        Map<Integer, int[]> nodeMap = new HashMap<>();
        long sumTopX = 0;
        
        for (int i = 0; i < n; i++)
         {
            int num = nums[i];
            int oldFreq = freq.getOrDefault(num, 0);
            int newFreq = oldFreq + 1;
            freq.put(num, newFreq);
            
            if (oldFreq > 0) 
            {
                int[] oldNode = nodeMap.get(num);
                if (topX.remove(oldNode)) sumTopX -= (long) oldNode[0] * oldNode[1];
                else others.remove(oldNode);
            }
            int[] newNode = new int[]{num, newFreq};
            nodeMap.put(num, newNode);
            others.add(newNode);
            
            while (topX.size() < x && !others.isEmpty())
             {
                int[] best = others.pollFirst();
                topX.add(best);
                sumTopX += (long) best[0] * best[1];
            }
            if (!others.isEmpty() && !topX.isEmpty())
             {
                int[] worstTop = topX.last();
                int[] bestOther = others.first();
                if (comp.compare(bestOther, worstTop) < 0) 
                {
                    topX.remove(worstTop);
                    others.remove(bestOther);
                    sumTopX -= (long) worstTop[0] * worstTop[1];
                    sumTopX += (long) bestOther[0] * bestOther[1];
                    topX.add(bestOther);
                    others.add(worstTop);
                }
            }
                if (i >= k)
                 {
                int out = nums[i - k];
                int oldF = freq.get(out);
                int newF = oldF - 1;
                freq.put(out, newF);
                
                int[] node = nodeMap.get(out);
                if (topX.remove(node)) sumTopX -= (long) node[0] * node[1];
                else others.remove(node);
                
                if (newF > 0) 
                {
                    int[] newNode2 = new int[]{out, newF};
                    nodeMap.put(out, newNode2);
                    others.add(newNode2);
                } 
                else {
                    freq.remove(out);
                    nodeMap.remove(out);
                }
                  while (topX.size() < x && !others.isEmpty())
                   {
                    int[] best = others.pollFirst();
                    topX.add(best);
                    sumTopX += (long) best[0] * best[1];
                }
            }
         if (i >= k - 1) ans[i - k + 1] = sumTopX;
        }
    return ans;
    }
}