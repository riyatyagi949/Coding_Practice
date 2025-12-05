/**
 * Problem Statement: Count Partitions with Even Sum Difference
 * -------------------------------------------------------------
 * Given an integer array 'nums' of length 'n'.
 * A partition is an index 'i' (0 <= i < n - 1) that splits the array into:
 * - Left subarray: indices [0, i] (sum = S_L)
 * - Right subarray: indices [i + 1, n - 1] (sum = S_R)
 * Return the number of partitions where the difference between the sums, |S_L - S_R|, is even.
 *
 * Constraints:
 * 2 <= n == nums.length <= 100
 * 1 <= nums[i] <= 100
 */
/**
     * Optimal Solution: Mathematical Parity Analysis (O(N))
     * ------------------------------------------------------
     * We want to find the number of partitions 'i' such that:
     * (S_L - S_R) is even.
     *
     * Let T be the total sum of the entire array: T = S_L + S_R.
     *
     * Key Parity Property: The difference (S_L - S_R) is even if and only if 
     * S_L and S_R have the same parity (both even or both odd).
     * * Case 1: T (Total Sum) is EVEN (T % 2 == 0)
     * The equation T = S_L + S_R can be rewritten as S_R = T - S_L.
     * Since T is even, S_R has the same parity as S_L:
     * - If S_L is Even, S_R = Even - Even = Even. (Same parity: Even, Even)
     * - If S_L is Odd, S_R = Even - Odd = Odd. (Same parity: Odd, Odd)
     * Conclusion for T is EVEN: For *every* partition 'i', S_L and S_R always 
     * have the same parity, which means (S_L - S_R) is always even.
     * The number of possible partitions is n - 1 (indices 0 to n-2).
     *
     * Case 2: T (Total Sum) is ODD (T % 2 != 0)
     * The equation S_R = T - S_L.
     * Since T is odd, S_R must have the opposite parity of S_L:
     * - If S_L is Even, S_R = Odd - Even = Odd. (Different parity)
     * - If S_L is Odd, S_R = Odd - Odd = Even. (Different parity)
     * Conclusion for T is ODD: For *every* partition 'i', S_L and S_R always 
     * have different parity, which means (S_L - S_R) is always odd.
     * The number of valid partitions is 0.
     *
     * * Optimized Algorithm:
     * 1. Calculate the total sum T.
     * 2. If T is odd, return 0.
     * 3. If T is even, return n - 1.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of 'nums'.
 * - The complexity is dominated by the initial pass to calculate the total sum.
 * - The rest of the operations (modulus, subtraction, return) are O(1).
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - The algorithm uses only a few integer variables (n, totalSum, x) regardless of the input size.
 */
// Code - 
class Solution {
    public int countPartitions(int[] nums) {
        int total = 0;
        for (int x : nums)
        total += x;
        
        if (total % 2 != 0)
        return 0;

        return nums.length - 1;
    }
}