/**
 * Problem Statement: Split Array Subsequences
 * -------------------------------------------
 * Given a sorted integer array arr[] and an integer k, determine if it is possible 
 * to split the array into one or more consecutive subsequences such that:
 * 1. Each subsequence consists of consecutive integers (each number is exactly one 
 * greater than the previous, e.g., [3, 4, 5]).
 * 2. Every subsequence has a length of at least k.
 * Return true if such a split is possible, otherwise return false.
 *
 * Example:
 * Input: arr[] = [2, 2, 3, 3, 4, 5], k = 2
 * Output: true 
 * (Split into [2, 3], [2, 3], [4, 5])
 *
 * Constraints:
 * 1 <= arr.size() <= 10^5
 * 1 <= k <= arr.size()
 *//**
     * Optimal Solution: Greedy Algorithm with HashMaps
     * ------------------------------------------------
     * The key insight is to process the numbers in increasing order and use a greedy choice.
     * When processing a number 'x', we always prioritize extending an existing subsequence 
     * that ends at 'x-1'. This is greedy because using 'x' to extend an existing sequence 
     * leaves other, smaller numbers available for starting new, longer sequences later.
     *
     * Two HashMaps are used for state tracking:
     * 1. freq: Stores the remaining count of each number in the array.
     * 2. endCount: Stores the count of valid subsequences that currently END at a specific number.
     *
     * * Algorithm Steps:
     * 1. Initialize 'freq' map with counts of all elements in 'arr'.
     * 2. Initialize 'endCount' map.
     * 3. Iterate through 'arr' (since it's sorted, we process numbers from smallest to largest):
     * a. If freq[x] is 0, continue (already used).
     * b. **Case 1 (Extend):** Check if endCount[x - 1] > 0.
     * - If yes, decrement endCount[x - 1] and increment endCount[x]. This extends a sequence.
     * c. **Case 2 (Start New):** If we cannot extend, try to start a new sequence of length 'k'.
     * - This requires 'x', 'x+1', 'x+2', ..., 'x + k - 1' to be available (freq > 0).
     * - If all k elements are available, consume one instance of each (decrementing their freqs)
     * and increment endCount[x + k - 1] (the new sequence ends here).
     * - If any required element is not available (freq is 0), then it's impossible to
     * form a valid sequence of length 'k' starting at 'x'. Return false.
     * 4. If the entire array is processed successfully, return true.
     */
    /**
 * Time Complexity Analysis:
 * -------------------------
 * O(N * k) in the worst case, but practically O(N + U * k), where N is the length of 'arr' 
 * and U is the number of unique elements (U <= N).
 * - O(N) to build the initial 'freq' map.
 * - The main loop iterates N times (for each element in 'arr').
 * - Inside the loop:
 * - Case 1 (Extend): O(1) map operations.
 * - Case 2 (Start New): Involves a loop of size (k-1) to check and decrement frequencies. 
 * This loop runs at most once for each unique starting number.
 * - Since the number of unique starts is at most U, the total time spent in the inner k-loop 
 * across the entire algorithm is O(U * k).
 * - Given the constraints (N <= 10^5, k <= 10^5), a stricter analysis is O(N + U * k), which 
 * can be up to O(N * k) if the inner loop is executed for every element, but is often much 
 * faster due to the nature of the greedy choices. For competitive programming, this is
 * considered the optimal approach.
 * * Space Complexity Analysis:
 * --------------------------
 * O(U), where U is the number of unique elements in 'arr'.
 * - This space is used to store the two HashMaps, 'freq' and 'endCount', which hold at most 
 * U distinct keys each. O(N) in the worst case where all elements are unique.
 */
// Optimal Solution in Java -
import java.util.*;

class Solution {
    public boolean isPossible(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> need = new HashMap<>();

        for (int x : arr) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        for (int x : arr) {
            if (freq.get(x) == 0) continue; 

            if (need.getOrDefault(x, 0) > 0) {
                need.put(x, need.get(x) - 1);
                need.put(x + 1, need.getOrDefault(x + 1, 0) + 1);
            } 
            else {
                for (int next = x; next < x + k; next++) {
                    if (freq.getOrDefault(next, 0) <= 0) {
                        return false;
                    }
                    freq.put(next, freq.get(next) - 1);
                }
                need.put(x + k, need.getOrDefault(x + k, 0) + 1);
                continue;
            }
            freq.put(x, freq.get(x) - 1);
        }
        return true;
    }
}
