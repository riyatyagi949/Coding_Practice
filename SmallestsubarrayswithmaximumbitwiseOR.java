// Problem Statement:
// Given a 0-indexed array nums, for each index i, find the length of the minimum-sized subarray starting at i that has the maximum possible bitwise OR.

// Approach:
// Iterate from right to left. For each index `i`, we need to find the smallest `j >= i` such that the bitwise OR of `nums[i...j]` is equal to the bitwise OR of `nums[i...n-1]`.
// The maximum bitwise OR for a suffix `nums[i...n-1]` is determined by all bits set in any number within that suffix.
// We maintain `lastSeenBitIdx[b]`, which stores the rightmost index `k` where the `b`-th bit is set in `nums[k]` considering elements from the current `i` to `n-1`.
// For each `i`, we update `lastSeenBitIdx` based on `nums[i]`.
// Then, the rightmost index `j` needed to achieve the maximum OR starting at `i` is the maximum value among `lastSeenBitIdx[b]` for all bits `b` (0 to 29).
// The length is `j - i + 1`.

// Time Complexity: O(N * Max_Bits) -> O(N) as Max_Bits is constant (30).
// Space Complexity: O(N) for the answer array, O(Max_Bits) for `lastSeenBitIdx` -> O(N).

class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] lastSeen = new int[32];

        for (int i = n - 1; i >= 0; i--) {
            for (int b = 0; b < 32; b++) {
                if (((nums[i] >> b) & 1) == 1) {
                    lastSeen[b] = i;
                }
            }
            int furthest = i;
            for (int b = 0; b < 32; b++) {
                furthest = Math.max(furthest, lastSeen[b]);
            }
             res[i] = furthest - i + 1;
        }

        return res;
    }
}



    

