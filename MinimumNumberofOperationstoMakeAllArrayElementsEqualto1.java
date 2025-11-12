/**
 * Problem Statement: Minimum Number of Operations to Make All Array Elements Equal to 1
 * -------------------------------------------------------------------------------------
 * You are given a 0-indexed array 'nums' of positive integers.
 * Operation: Select an index 'i' (0 <= i < n - 1) and replace either nums[i] or nums[i+1] 
 * with their greatest common divisor (GCD) value.
 * Return the minimum number of operations to make all elements of 'nums' equal to 1. 
 * If it is impossible, return -1.
 *
 * Constraints:
 * 2 <= nums.length <= 50 (N is very small)
 * 1 <= nums[i] <= 10^6
 */
/**
     * Optimal Solution: Analysis and Two-Pass Approach
     * --------------------------------------------------
     * Key Properties of the Operation:
     * 1. **GCD is Non-Increasing:** Replacing a number with a GCD operation guarantees 
     * the new value is less than or equal to the original value (gcd(a, b) <= min(a, b)).
     * 2. **GCD Property:** The GCD of a range of numbers [i...j] can be brought into 
     * any position k within that range [i...j] in exactly (j - i) operations.
     * - Example: To replace nums[j] with gcd(nums[i]...nums[j]), it takes j-i operations.
     * - Example: To get '1' at some position takes (length_of_subarray_to_compute_gcd - 1) operations.
     *
     * * Feasibility Condition:
     * A solution is only possible if and only if the GCD of the entire array is 1. 
     * Since every operation replaces a number with a divisor, the overall array GCD 
     * can never decrease below the initial overall GCD. If the initial overall GCD 
     * is greater than 1, we can never reach 1.
     *
     * * Case 1: Array already contains '1'.
     * If there is at least one '1' present, we can spread this '1' to the entire array.
     * - The first '1' can be used to convert an adjacent non-'1' number to 1 in 1 operation (gcd(x, 1) = 1).
     * - We need to convert (n - countOnes) non-'1' numbers to '1'.
     * - Minimum operations = n - countOnes.
     *
     * * Case 2: Array does not contain '1' (but overall GCD is 1).
     * 1. **Goal:** Create the *first* '1' in the array in the minimum number of operations.
     * 2. This requires finding the **smallest subarray** whose GCD is 1. Let the length of 
     * this smallest subarray be `minLen`.
     * 3. The minimum operations to turn **one element** within this subarray into 1 is 
     * `minLen - 1` (e.g., a subarray of length 3 needs 2 operations: gcd(a, b) -> g1, then gcd(g1, c) -> 1).
     * 4. Once we have created the first '1', we fall back to Case 1. The remaining 
     * `n - 1` non-'1' elements can be converted to '1' using the newly created '1'.
     * 5. Total minimum operations = (minLen - 1) + (n - 1).
     *
     * The optimal solution first checks feasibility and Case 1, then falls back to 
     * finding `minLen` for Case 2.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N^2 * log(max(nums[i]))), where N is the length of 'nums'.
 * - **Feasibility Check:** O(N * log(max(nums[i]))), as we compute GCD for N elements.
 * - **Minimum Length Search:** This involves a nested loop (N^2 iterations). In the inner 
 * loop, we calculate GCD, which takes O(log(max(nums[i]))).
 * - Total time complexity is dominated by the nested loop: O(N^2 * log(max(nums[i]))). 
 * This is acceptable because the constraint N <= 50 is very small.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1)
 * - The algorithm uses only a few integer variables. The space required for the 
 * recursive GCD call stack is negligible (logarithmic in max(nums[i])).
 */
// Optimal Solution in Java - 
import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int overallGcd = nums[0];

        for (int i = 1; i < n; i++) {
            overallGcd = gcd(overallGcd, nums[i]);
        }
        if (overallGcd != 1) 
        return -1;

        int countOnes = 0;
        for (int x : nums) {
            if (x == 1) countOnes++;
        }
        if (countOnes > 0) 
        return n - countOnes;

        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
         {
            int g = nums[i];
            for (int j = i + 1; j < n; j++) 
            {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }
        return (minLen - 1) + (n - 1);
    }
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}