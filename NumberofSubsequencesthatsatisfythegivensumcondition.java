// Problem Statement:
// You are given an array of integers nums and an integer target.
// Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 10^9 + 7.

// Approach:
// First, sort the array `nums`. This is crucial because for any subsequence, its minimum and maximum elements will be at the two ends of a chosen range in the sorted array.
// We can use a two-pointer approach (left and right) on the sorted array.
// `left` pointer starts at the beginning (index 0) and `right` pointer starts at the end (index nums.length - 1).
// We precompute powers of 2 modulo 10^9 + 7, as this will be used to count subsequences. `powers[i]` will store 2^i % MOD.
// We iterate while `left <= right`.
// In each iteration, we check `nums[left] + nums[right]`.
// If `nums[left] + nums[right] <= target`, it means `nums[left]` can be the minimum element of a valid subsequence. All elements between `nums[left]` and `nums[right]` (inclusive, except `nums[left]` itself) can be optionally included in the subsequence. The number of such elements is `right - left`. The number of ways to choose these elements is `2^(right - left)`. We add this to our total count. Then, we increment `left` to try a larger minimum.
// If `nums[left] + nums[right] > target`, it means `nums[right]` is too large to form a valid pair with `nums[left]`. So, we must decrement `right` to try a smaller maximum.

// Time Complexity: O(N log N) due to sorting. The two-pointer traversal takes O(N) time. Precomputing powers takes O(N).
// Space Complexity: O(N) for storing powers of 2.

// Optimal Solution:
import java.util.Arrays;

class Solution {
    public int numSubseq(int[] nums, int target) {
        int mod = 1_000_000_007;
        int n = nums.length;

        Arrays.sort(nums);

        int[] pow = new int[n];
        pow[0] = 1;
        for (int i = 1; i < n; i++) {
            pow[i] = (pow[i - 1] * 2) % mod;
        }
        int left = 0, right = n - 1;
        int count = 0;

        while (left <= right) {
            // If the sum of the smallest and largest in the current window <= target
            if (nums[left] + nums[right] <= target) {
                // All subsets from left to right are valid
                // Number of such subsequences = 2^(right - left)
                count = (count + pow[right - left]) % mod;
                // Move left pointer right to explore more combinations
                left++;
            } else {
                // If the sum is too large, reduce it by moving right pointer left
                right--;
            }
        }

        // Return total valid subsequences count
        return count;
    }
}
