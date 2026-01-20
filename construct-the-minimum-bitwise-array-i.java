/**
 * PROBLEM STATEMENT: 3314. Construct the Minimum Bitwise Array I
 * --------------------------------------------------------------------------------
 * You are given an array 'nums' consisting of n prime integers.
 * You need to construct an array 'ans' such that for each index i:
 * ans[i] OR (ans[i] + 1) == nums[i].
 * * Additionally, you must minimize each value of ans[i]. If no such value exists,
 * set ans[i] = -1.
 * * Example:
 * Input: nums = [2, 3, 5, 7]
 * Output: [-1, 1, 4, 3]
 * Explanation: 
 * - For 2: No x exists where x OR (x+1) = 2.
 * - For 3: 1 OR 2 = 3. Smallest is 1.
 * - For 5: 4 OR 5 = 5. Smallest is 4.
 * - For 7: 3 OR 4 = 7. Smallest is 3.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Bit Manipulation / Brute Force Search
 * --------------------------------------------------------------------------------
 * 1. Brute Force (Constraint Based):
 * Since nums[i] is small (up to 1000), we can iterate from x = 0 to nums[i] - 1.
 * The first x that satisfies (x | (x + 1)) == nums[i] is our minimum answer.
 * * 2. Mathematical Observation:
 * The operation x OR (x + 1) essentially sets the rightmost zero bit of x to 1.
 * For a prime p, if p = 2, no such x exists (-1).
 * For odd primes, p is always of the form ...0111. To minimize x such that 
 * setting its rightmost 0 yields p, we look at the trailing sequence of 1s in p.
 * If p ends in 'k' ones, the minimum x is obtained by turning the last 1 into a 0.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N * max(nums[i]))
 * - N is the number of elements (up to 100).
 * - For each element, we check values up to 1000.
 * - Total operations: 100 * 1000 = 10^5, which is very efficient.
 * * Space Complexity: O(N)
 * - To store the result array.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            boolean found = false;

            for (int x = 0; x < num; x++) {
                if ( (x | (x + 1)) == num ) {
                    result[i] = x;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result[i] = -1;
            }
        }
         return result;
    }
}

