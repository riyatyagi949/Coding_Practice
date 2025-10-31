/**
 * Problem Statement: The Two Sneaky Numbers of Digitville
 * --------------------------------------------------------
 * Given an array 'nums' which originally contained integers from 0 to n - 1 
 * exactly once. Two mischievous numbers sneaked in an additional time, making 
 * the list length n + 2.
 * The task is to find these two sneaky numbers (the two numbers that appear twice).
 *
 * Example: nums = [0, 1, 1, 0], n = 2. Output: [0, 1]
 *
 * Constraints:
 * 2 <= n <= 100
 * nums.length == n + 2
 * 0 <= nums[i] < n
 * Exactly two elements are repeated.
 *//**
     * Optimal Solution: Boolean Array / Frequency Counting
     * ----------------------------------------------------
     * Since the elements are small (0 to n-1) and 'n' is very small (up to 100), 
     * the optimal approach is to use a fixed-size auxiliary array (or a Set, but array 
     * is faster) to track the elements we have seen once.
     *
     * * Algorithm Steps:
     * 1. Create a boolean array `seen` of size `n` (since max element is n-1), initialized to false.
     * 2. Initialize an integer array `result` of size 2.
     * 3. Iterate through `nums`:
     * a. If `seen[num]` is true, it means we have seen this number once before, so 
     * this is the second occurrence. Add `num` to the `result` array.
     * b. If `seen[num]` is false, mark it as true.
     * 4. Once two numbers are found in the `result` array, return it.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of 'nums'.
 * - The algorithm performs a single pass over the input array 'nums' (length N).
 * - Inside the loop, array access and assignment (O(1) time) are used.
 * - Total time complexity: O(N). Since N is fixed (N=n+2) and n <= 100, this is extremely fast.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(n), where n is the number of distinct elements (up to 100).
 * - This space is required for the auxiliary boolean array `seen`, which is of size 'n'.
 * - The space for the result array is O(1).
 * - Overall space complexity: O(n).
 */
// Optimal Solution in Java - 
class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length;
        boolean[] seen = new boolean[n];
        int[] result = new int[2];
        int index = 0;

        for (int num : nums)
         {
            if (seen[num])
             {
                result[index++] = num;
            } 
            else {
                seen[num] = true;
            }
        }
        return result;
    }
}