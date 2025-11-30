/**
 * Problem Statement: Make Sum Divisible by P
 * ------------------------------------------
 * Given an array of positive integers 'nums' and an integer 'p', find the 
 * length of the smallest subarray (possibly empty, but not the whole array) 
 * that must be removed such that the sum of the remaining elements is divisible by 'p'.
 * Return -1 if no such subarray exists.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 1 <= p <= 10^9
 */
/**
     * Optimal Solution: Prefix Sum and HashMap (Modulo Arithmetic)
     * -------------------------------------------------------------
     * Let S be the total sum of 'nums'. We need the remaining sum (S_rem) to satisfy:
     * S_rem % p = 0
     *
     * Let S_sub be the sum of the removed subarray. Since S_rem = S - S_sub, we need:
     * (S - S_sub) % p = 0
     * S % p = S_sub % p
     *
     * Let 'target_rem' = S % p. If 'target_rem' is 0, the total sum is already divisible, 
     * and the answer is 0.
     * If 'target_rem' > 0, we must find a subarray sum S_sub such that:
     * S_sub % p = target_rem
     *
     * The problem transforms into finding the **shortest subarray** whose sum modulo 'p' 
     * equals 'target_rem'. This is a classic **Prefix Sum** problem solved efficiently 
     * with a HashMap.
     *
     * * Key Idea: Finding a subarray sum (S_sub) is equivalent to finding two prefix sums, 
     * PrefixSum[j] and PrefixSum[i], such that:
     * S_sub = PrefixSum[j] - PrefixSum[i] (where i < j)
     *
     * * Required Modulo Relationship:
     * (PrefixSum[j] - PrefixSum[i]) % p = target_rem
     * PrefixSum[j] % p - PrefixSum[i] % p = target_rem (modulo p)
     * PrefixSum[i] % p = (PrefixSum[j] % p - target_rem + p) % p
     *
     * * Algorithm Steps:
     * 1. Calculate the required remainder: `target_rem = total_sum % p`. If 0, return 0.
     * 2. Initialize a HashMap: `map[prefix_sum_mod_p] = index`. This stores the index of the 
     * end of the longest prefix encountered so far for a given modulo value.
     * Initialize `map[0] = -1` (representing an empty prefix sum of 0 ending before index 0).
     * 3. Iterate through 'nums', maintaining the current prefix sum modulo 'p', `current_rem`.
     * 4. For each index `i`:
     * a. Calculate the required previous prefix remainder:
     * `required_prev_rem = (current_rem - target_rem + p) % p`
     * b. If `map` contains `required_prev_rem`, it means a valid subarray exists.
     * The length of this subarray is `i - map.get(required_prev_rem)`.
     * c. Update the minimum length found so far (`minLen`).
     * d. Store the current `current_rem` and its index `i` in the map: `map.put(current_rem, i)`.
     * 5. If `minLen` is less than the array length `n`, return `minLen`. Otherwise, 
     * it is impossible to remove a subarray (without removing the whole array), so return -1.
     */
// Code -

import java.util.*;

class Solution {
     public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        
        long totalSum = 0;
        for (int num : nums) 
            {
            totalSum += num;
        }
        int targetRem = (int) (totalSum % p);
        
        if (targetRem == 0)
             {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        
        int currentRem = 0;
        int minLen = n; 
        
        for (int i = 0; i < n; i++)
             {
            currentRem = (currentRem + nums[i]) % p;
            int requiredPrevRem = (currentRem - targetRem + p) % p;
            
            if (map.containsKey(requiredPrevRem))
                 {
                int len = i - map.get(requiredPrevRem);
                minLen = Math.min(minLen, len);
            }
            map.put(currentRem, i);
        }
        return minLen == n ? -1 : minLen;
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of 'nums'.
 * - We iterate through the array 'nums' exactly once.
 * - Inside the loop, all operations (modulo, addition, HashMap put/get/containsKey) 
 * take O(1) average time.
 * - The initial sum calculation is also O(N).
 * - Overall complexity: O(N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(P) or O(N), whichever is smaller.
 * - The HashMap stores prefix remainders modulo 'p'.
 * - Since the remainder can only take values from 0 to p-1, the map size is bounded by O(P).
 * - However, the map can store at most N unique entries (one for each prefix sum).
 * - Therefore, the space complexity is O(min(N, P)). Given N <= 10^5, the space is practical.
 */