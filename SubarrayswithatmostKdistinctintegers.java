// Problem Statement:
// You are given an array arr[] of positive integers and an integer k, find the number of subarrays in arr[] where the count of distinct integers is at most k.
// Note: A subarray is a contiguous part of an array.

// Approach:
// The problem asks us to count the number of subarrays with at most K distinct integers.
// This is a classic sliding window problem. We can use a helper function to count subarrays with at most K distinct elements.
// The main idea is to use a sliding window [left, right] and a frequency map (or an array if the range of elements is small) to keep track of distinct elements within the current window.
// We expand the window by moving the `right` pointer. For each `right`, we add `arr[right]` to our frequency map.
// If the number of distinct elements in the window exceeds `k`, we shrink the window from the left by moving the `left` pointer and decrementing the count of `arr[left]` in the frequency map until the distinct count is `k` or less.
// For every valid window [left, right] (i.e., distinct elements <= k), all subarrays ending at `right` and starting from `left` up to `right` are valid. The number of such subarrays is `right - left + 1`. We add this to our total count.

// Time Complexity: O(N), where N is the length of the array arr.
// Both the left and right pointers traverse the array at most once.
// Space Complexity: O(U), where U is the number of unique elements in the array (or O(max(arr[i])) if using an array for frequency map directly, but a HashMap is safer for large values of arr[i]). In the worst case, U can be N.

import java.util.*;

class Solution {
    public int countAtMostK(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> freq = new HashMap<>();
        int start = 0, total = 0;

        for (int end = 0; end < n; end++) {
            freq.put(arr[end], freq.getOrDefault(arr[end], 0) + 1);

            while (freq.size() > k) {
                freq.put(arr[start], freq.get(arr[start]) - 1);
                if (freq.get(arr[start]) == 0) {
                    freq.remove(arr[start]);
                }
                start++;
            }
            total += (end - start + 1);
        }

        return total;
    }

    public int subarraysWithAtMostKDistinct(int[] arr, int k) {
        return countAtMostK(arr, k);
    }
}
