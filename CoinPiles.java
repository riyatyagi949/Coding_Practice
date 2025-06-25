// Problem Statement:
// You are given an array arr[] of integers, where each element represents the number of coins in a pile. You are also given an integer k.
// Your task is to remove the minimum number of coins such that the absolute difference between the number of coins in any two updated piles is at most k.
// Note: You can also remove a pile by removing all the coins of that pile.

// Approach:
// The problem asks us to minimize the number of removed coins such that the difference between any two remaining piles is at most k.
// Let's sort the array first. If we choose a range [x, x + k] such that all remaining piles fall within this range,
// then the condition |a - b| <= k will be satisfied for any two remaining piles 'a' and 'b'.
// To minimize the removed coins, we should iterate through each possible pile as the smallest pile that remains.
// For a chosen smallest pile `arr[i]`, all other remaining piles must have a value `val` such that `arr[i] <= val <= arr[i] + k`.
// All piles `arr[j]` where `arr[j] < arr[i]` must be removed. The number of coins to remove from such piles is `arr[j]`.
// All piles `arr[j]` where `arr[j] > arr[i] + k` must be reduced to `arr[i] + k` or removed.
// If we choose to keep them, we remove `arr[j] - (arr[i] + k)` coins from each.
//
// A more efficient approach is to iterate through each possible pile `arr[i]` and consider it as the minimum value in our target range.
// Let `min_val = arr[i]`. Then the maximum value allowed in any remaining pile is `max_val = arr[i] + k`.
// We need to find all piles `arr[j]` such that `arr[i] <= arr[j] <= arr[i] + k`.
// For all piles `arr[p]` where `arr[p] < arr[i]`, we must remove all `arr[p]` coins.
// For all piles `arr[q]` where `arr[q] > arr[i] + k`, we must remove `arr[q] - (arr[i] + k)` coins from each.
//
// Let's sort the array `arr`.
// We can precompute prefix sums to quickly calculate the sum of coins in a range.
// `prefixSum[i]` will store the sum of `arr[0]` to `arr[i-1]`.
//
// Iterate `i` from `0` to `n-1`:
// For each `arr[i]`, it can be the minimum value of our chosen range.
// The maximum allowed value is `arr[i] + k`.
// We need to find the largest index `j` such that `arr[j] <= arr[i] + k`. This can be done using `upper_bound` (or `Arrays.binarySearch` and adjusting).
// Let `j` be the index such that `arr[j]` is the last element less than or equal to `arr[i] + k`.
//
// Coins to remove from piles smaller than `arr[i]`:
// Sum of `arr[0]` to `arr[i-1]` is `prefixSum[i]`. These coins must be removed completely.
//
// Coins to remove from piles larger than `arr[i] + k`:
// These are piles `arr[j+1]` to `arr[n-1]`.
// For each such pile `arr[p]`, we need to remove `arr[p] - (arr[i] + k)` coins.
// The total coins to remove from these piles is `sum(arr[p] - (arr[i] + k))` for `p` from `j+1` to `n-1`.
// This can be rewritten as `(sum(arr[p] for p from j+1 to n-1)) - (count of such piles * (arr[i] + k))`.
// `sum(arr[p] for p from j+1 to n-1)` is `prefixSum[n] - prefixSum[j+1]`.
// `count of such piles` is `n - (j + 1)`.
// So, total to remove from larger piles is `(prefixSum[n] - prefixSum[j+1]) - (long)(n - (j + 1)) * (arr[i] + k)`.
//
// The total coins to remove for a given `arr[i]` as the minimum is `prefixSum[i]` + `coins_from_larger_piles`.
// We take the minimum of this value over all possible `i`.

// Time Complexity: O(N log N) due to sorting. The loop iterates N times, and binary search takes log N.
// Space Complexity: O(N) for storing the prefix sums. If we don't store prefix sums and calculate sums on the fly, it would still be O(N) due to sorting, or O(1) if sorting is in-place and no extra space for prefix sums. Since Java's `Arrays.sort` is O(log N) stack space for mergesort, we can consider it O(N) in worst case for objects or O(log N) for primitives. Here we use an array for prefix sums, so O(N).

import java.util.Arrays;

class Solution {
    public int minimumCoins(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        }
        long minRemovedCoins = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            long currentMinVal = arr[i];
            long currentMaxValAllowed = arr[i] + k;

            int j = Arrays.binarySearch(arr, i, n, (int)currentMaxValAllowed);
            if (j < 0) {
                j = -j - 1; 
                j--;      
            }
            long coinsRemoved = 0;
            coinsRemoved += prefixSum[i];
            if (j + 1 < n) {
                long sumOfLargerPiles = prefixSum[n] - prefixSum[j + 1];
                long countOfLargerPiles = n - (j + 1);
                coinsRemoved += sumOfLargerPiles - (countOfLargerPiles * currentMaxValAllowed);
            }
            minRemovedCoins = Math.min(minRemovedCoins, coinsRemoved);
        }
        return (int) minRemovedCoins;
    }
}