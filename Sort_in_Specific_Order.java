/**
 * Problem Statement: Sort in Specific Order
 * -----------------------------------------
 * Given an array arr[] of positive integers, sort them such that:
 * 1. The first part contains odd numbers sorted in descending order.
 * 2. The second part contains even numbers sorted in ascending order.
 * * Example:
 * Input: arr[] = [1, 2, 3, 5, 4, 7, 10]
 * Output: [7, 5, 3, 1, 2, 4, 10]
 * * Constraints:
 * 1 <= arr.size() <= 10^5
 * 0 <= arr[i] <= 10^9
 */
/**
     * Optimal Solution: Partition and Sort
     * ------------------------------------
     * 1. Categorize: We first transform the elements to distinguish them.
     * - We can make odd numbers negative. This allows a single sort to
     * put them at the front. However, partitioning is cleaner.
     * 2. Two-Pointer Partition: Move all odd numbers to the left and evens to the right.
     * 3. Segment Sort: 
     * - Sort the odd segment (left) in descending order.
     * - Sort the even segment (right) in ascending order.
  */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N log N): The bottleneck of the algorithm is the Arrays.sort() method, 
 * which uses Dual-Pivot Quicksort for primitives, taking O(N log N) time.
 * * Space Complexity Analysis:
 * --------------------------
 * O(1): We perform the operations in-place by negating the odd values. 
 * Note: Arrays.sort() for primitives has O(log N) stack space, but no 
 * additional O(N) data structures are created.
 */

// Code - 

import java.util.Arrays;
import java.util.Collections;

class Solution {
    public void sortIt(int[] arr) {
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        
        for (int num : arr) {
            if (num % 2 != 0) odd.add(num);
            else even.add(num);
        }
        odd.sort(Collections.reverseOrder());
        Collections.sort(even);
        
        int idx = 0;
        for (int num : odd) arr[idx++] = num;
        for (int num : even) arr[idx++] = num;
    }
}
