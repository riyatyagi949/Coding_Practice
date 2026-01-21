/**
 * PROBLEM STATEMENT: 3315. Construct the Minimum Bitwise Array II
 * --------------------------------------------------------------------------------
 * You are given an array 'nums' consisting of n prime integers.
 * You need to construct an array 'ans' such that for each index i:
 * ans[i] OR (ans[i] + 1) == nums[i].
 * * Additionally, you must minimize each value of ans[i]. If no such value exists,
 * set ans[i] = -1.
 * * Difference from Version I: 
 * Constraints are much larger (nums[i] up to 10^9), so a brute force search 
 * from 0 to nums[i] is no longer feasible.
 * * Example:
 * Input: nums = [11, 13, 31]
 * Output: [9, 12, 15]
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Bit Manipulation (Finding the Rightmost Consecutive Ones)
 * --------------------------------------------------------------------------------
 * 1. The Condition: x OR (x + 1) = nums[i]
 * The operation (x + 1) on a binary number x flips the trailing block of 1s to 0s 
 * and the rightmost 0 to 1.
 * Example: x = 1011 (11), x + 1 = 1100 (12).
 * x | (x + 1) = 1011 | 1100 = 1111 (15).
 * * 2. Logical Deduction:
 * For (x | x+1) to equal nums[i], nums[i] must be the result of taking x and 
 * ensuring the bit just to the left of x's trailing ones is also set.
 * Effectively, x | (x+1) fills the first '0' bit from the right with a '1'.
 * * 3. Minimizing x:
 * To minimize x, we want it to be as close to nums[i] as possible but with one 
 * bit changed. Specifically, for odd primes, nums[i] always ends in a block of 1s.
 * Example: 11 is 1011. 
 * Trailing 1s block: bits at position 0 and 1.
 * To find min x, we change the *lowest* bit of that trailing block of 1s that 
 * is still part of the contiguous block starting from the LSB.
 * 1011 -> Change the bit at index (first 0 from right - 1).
 * First 0 from right in 1011 is at index 2.
 * Target bit to flip is index (2-1) = 1.
 * 1011 ^ (1 << 1) = 1001 (9).
 * * 4. Edge Case:
 * If num = 2 (10), there is no bitwise solution because it's the only even prime.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * log(max(nums[i])))
 * - N is the number of elements (up to 100).
 * - For each number, we check bits up to 30 (since 10^9 < 2^30).
 * Space Complexity: O(N)
 * - To store the resulting array.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-

import java.util.List;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);

            if (num == 2) {
                result[i] = -1;
                continue;
            }
            boolean found = false;

            for (int j = 1; j < 32; j++) {
                if ((num & (1 << j)) != 0) {
                    continue;
                }
                result[i] = num ^ (1 << (j - 1));
                found = true;
                break;
            }
            if (!found) {
                result[i] = -1;
            }
        }
        return result;
    }
}

