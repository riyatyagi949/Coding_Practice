/**
 * Problem Statement: Adjacent Increasing Subarrays Detection I (LeetCode 3349)
 * --------------------------------------------------------------------------
 * Given an array 'nums' of n integers and an integer 'k', determine whether there exist 
 * two adjacent subarrays of length 'k' such that both subarrays are strictly increasing.
 * * Specifically, we need to check if there are two subarrays starting at indices 'a' and 'b' 
 * (where 'a' < 'b') such that:
 * 1. Both subarrays nums[a...a + k - 1] and nums[b...b + k - 1] are strictly increasing.
 * 2. The subarrays are adjacent, meaning b = a + k.
 * * The two subarrays are:
 * - Subarray 1: nums[a] to nums[a + k - 1] (length k)
 * - Subarray 2: nums[a + k] to nums[a + 2*k - 1] (length k)
 * * The overall sequence covering both is nums[a] to nums[a + 2*k - 1] (length 2*k).
 * * Return true if it is possible to find such a pair, and false otherwise.
 * * Constraints:
 * 2 <= nums.length <= 100
 * 1 < 2 * k <= nums.length
 *//**
     * Optimal Solution: Sliding Window with Pre-calculation (or direct check)
     * ---------------------------------------------------------------------
     * The problem requires us to find a starting index 'a' such that the window 
     * of length 2*k starting at 'a' contains two adjacent strictly increasing subarrays 
     * of length 'k'. The first subarray is [a, a+k-1] and the second is [a+k, a+2*k-1].
     * * The check must be performed for all possible starting indices 'a'.
     * Since the total length of the required segment is 2*k, the starting index 'a' 
     * can range from 0 up to nums.length - 2*k.
     * * Algorithm Steps:
     * 1. Iterate through all possible starting indices 'a' for the first subarray. 
     * The loop runs from a = 0 up to nums.length - 2*k.
     * 2. For each 'a', check if the first subarray (nums[a...a + k - 1]) is strictly increasing.
     * 3. If the first subarray is strictly increasing, check if the second, adjacent 
     * subarray (nums[a + k...a + 2*k - 1]) is also strictly increasing.
     * 4. If both are strictly increasing, we have found a valid pair, so return true immediately.
     * 5. If the loop completes without finding such a pair, return false.
     * * Helper Function `isStrictlyIncreasing(nums, start, length)`:
     * This function checks if the subarray starting at 'start' with 'length' is 
     * strictly increasing (i.e., nums[i] < nums[i+1] for all elements in the subarray).
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N * K), where N is the length of 'nums' and K is the length of the subarray.
 * * 1. The main loop iterates a total of (N - 2*K + 1) times, which is O(N).
 * 2. Inside the loop, the `isStrictlyIncreasing` helper function is called twice.
 * 3. The `isStrictlyIncreasing` function iterates K-1 times, which is O(K).
 * * Total Time Complexity = O(Number of iterations * Work per iteration) 
 * = O(N * (O(K) + O(K))) = O(N * K).
 * * Note: Since the constraints are small (N <= 100), this O(N*K) solution is very fast.
 * * Space Complexity Analysis:
 * --------------------------
 * O(1).
 * The algorithm uses only a few integer variables (a, b, i, n, max_a) and does not 
 * allocate any additional data structures whose size depends on N or K.
 */
// Optimal Solution in Java - 
import java.util.*;

class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        
        for (int i = 0; i + 2 * k <= n; i++)
         {
            if (isIncreasing(nums, i, i + k) && isIncreasing(nums, i + k, i + 2 * k))
             {
                return true;
            }
        }
        return false;
    }
    private boolean isIncreasing(List<Integer> nums, int start, int end) {
        for (int i = start + 1; i < end; i++)
         {
            if (nums.get(i) <= nums.get(i - 1)) 
            {
                return false;
            }
        }
        return true;
    }
}
