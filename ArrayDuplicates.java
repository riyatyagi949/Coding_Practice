/**
 * Problem Statement: Array Duplicates
 * -----------------------------------
 * Given an array arr[] of size n, containing elements from the range 1 to n, 
 * where each element appears at most twice.
 * Return an array of all the integers that appear exactly twice.
 *
 * Constraints:
 * 1 <= n <= 10^6
 * 1 <= arr[i] <= n
 * Each element appears at most twice.
 */
/**
     * Optimal Solution: In-Place Modification (Using Array as a Hash Map)
     * ------------------------------------------------------------------
     * This solution leverages the constraint that array elements are within the range [1, n].
     * We can use the array indices (0 to n-1) to track the presence of elements (1 to n).
     *
     * * The Key Idea:
     * 1. Iterate through the array. For each element 'x = arr[i]':
     * 2. Calculate the corresponding index: `index = |x| - 1`.
     * 3. **Marking:** We use the sign of the element at the index `arr[index]` to mark 
     * whether the number `|x|` has been seen before.
     * - If `arr[index]` is positive, we flip its sign to negative, marking the number `|x|` as seen once.
     * - If `arr[index]` is negative, it means the number `|x|` has already been seen. 
     * This is the second occurrence, so we add `|x|` to our result list.
     *
     * * Note on Absolute Value: Since we modify the signs, we must always use the 
     * absolute value `|arr[i]|` to get the correct original number/index, preventing 
     * errors when we encounter a number that was previously sign-flipped.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the size of the array.
 * - We iterate through the array exactly once. All operations inside the loop (absolute value, 
 * index calculation, sign check, array access, and list add) are O(1).
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) auxiliary space (excluding the output list).
 * - We modify the input array in place, requiring no extra space proportional to N for 
 * tracking frequencies or visited states.
 * - If the space used by the output list (`result`) is counted, the complexity is 
 * O(K), where K is the number of duplicates (K <= N/2).
 */
// Code - 
import java.util.*;

class Solution {
    public ArrayList<Integer> findDuplicates(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < arr.length; i++) 
        {
            int val = Math.abs(arr[i]);
            
            if (arr[val - 1] < 0)
            {
                result.add(val);
            } 
            else {
                arr[val - 1] = -arr[val - 1];
            }
        }
        return result;
    }
}



