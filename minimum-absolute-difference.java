/**
 * PROBLEM STATEMENT: 1200. Minimum Absolute Difference
 * --------------------------------------------------------------------------------
 * Given an array of distinct integers 'arr', find all pairs of elements with the 
 * minimum absolute difference of any two elements.
 * * Return a list of pairs in ascending order (with respect to pairs), 
 * each pair [a, b] follows:
 * 1. a, b are from arr
 * 2. a < b
 * 3. b - a equals the minimum absolute difference of any two elements in arr.
 * * Example:
 * Input: arr = [4, 2, 1, 3]
 * Output: [[1, 2], [2, 3], [3, 4]]
 * Explanation: Min diff is 1. Pairs: (1,2), (2,3), (3,4).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SORTING + SINGLE PASS
 * --------------------------------------------------------------------------------
 * 1. Sorting:
 * The minimum absolute difference between any two elements must exist between 
 * two elements that are adjacent when the array is sorted.
 * * 2. Algorithm:
 * - Sort the array 'arr'.
 * - First pass (or combined pass): Find the global minimum difference by comparing 
 * adjacent elements (arr[i+1] - arr[i]).
 * - Second pass: Collect all pairs (arr[i], arr[i+1]) whose difference matches 
 * the global minimum.
 * * 3. Sorting naturally handles the "ascending order" requirement for both the 
 * pairs and the elements within the pairs.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N log N)
 * - Sorting the array takes O(N log N).
 * - Finding the min difference and collecting pairs takes O(N).
 * * Space Complexity: O(log N) or O(N)
 * - O(log N) for the sorting stack space.
 * - O(N) if we consider the space required for the output list of pairs.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        
        List<List<Integer>> result = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;
        
        for (int i = 0; i < arr.length - 1; i++) {
            int currentDiff = arr[i + 1] - arr[i];
            if (currentDiff < minDiff) {
                minDiff = currentDiff;
            }
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] == minDiff) {
                List<Integer> pair = new ArrayList<>();
                pair.add(arr[i]);
                pair.add(arr[i + 1]);
                result.add(pair);
            }
        }
       return result;
    }
}
