// Problem Statement:
// You are given an array arr[]. For each element at index i (0-based indexing), find the farthest index j to the right (i.e., j > i) such that arr[j] < arr[i]. If no such index exists for a given position, return -1 for that index. Return the resulting array of answers.

// Approach:
// The most efficient way to solve this problem is with a two-pass linear time algorithm. A naive approach of iterating through the array for each element would result in an O(N^2) time complexity, which is too slow for the given constraints.
// The key insight for an optimal solution is that for a given `arr[i]`, we don't need to check every element to its right. We only need to check the potential candidates for the "farthest smaller element." These candidates will always be the minimum elements within different suffixes of the array.

// Pass 1: Suffix Minimum Index Array
// First, we precompute a helper array, let's call it `minSuffixIndex`, of the same size as the input array `arr`. The purpose of `minSuffixIndex[i]` is to store the index of the minimum element within the subarray `arr[i...n-1]`.
// We can build this array by iterating from right to left, starting from the last element.
// `minSuffixIndex[n-1]` will simply be `n-1`, as the last element is the minimum of its own suffix.
// For each `i` from `n-2` down to `0`, we compare `arr[i]` with the minimum of the suffix starting at `i+1`, which is `arr[minSuffixIndex[i+1]]`.
// If `arr[i]` is less than or equal to `arr[minSuffixIndex[i+1]]`, then `arr[i]` is the new minimum for the suffix starting at `i`, so `minSuffixIndex[i]` becomes `i`.
// Otherwise, the minimum remains the same as for the suffix `i+1`, so `minSuffixIndex[i]` is equal to `minSuffixIndex[i+1]`.
// This pass takes **O(N)** time and **O(N)** space.

// Pass 2: Finding the Farthest Smaller Element
// With the `minSuffixIndex` array precomputed, we can now efficiently find the answer for each element.
// We iterate through the original array `arr` from left to right, from `i = 0` to `n-1`.
// For each `arr[i]`, we need to find the farthest index `j > i` where `arr[j] < arr[i]`. We can use a pointer `j` initialized to `i+1`.
// Instead of incrementing `j` by one, we use our `minSuffixIndex` array to make large jumps.
// We get the index of the minimum element in the current suffix `arr[j...n-1]` from `candidateIndex = minSuffixIndex[j]`.
// We then check if `arr[candidateIndex]` is smaller than `arr[i]`.
// - If `arr[candidateIndex] < arr[i]`: This `candidateIndex` is a potential answer. Since `minSuffixIndex[j]` gives us the index of the smallest element in `arr[j...n-1]`, it is a good candidate for the farthest smaller element. We update our current best answer for `arr[i]` to `candidateIndex`. To find if there's an even farther one, we continue our search from `j = candidateIndex + 1`.
// - If `arr[candidateIndex] >= arr[i]`: The minimum element in the entire suffix `arr[j...n-1]` is not smaller than `arr[i]`. This means no element in this suffix can be smaller than `arr[i]`. So, we can safely jump our search pointer `j` to the next logical position, which is `candidateIndex + 1`. This jump allows us to skip a large portion of the array that contains no valid candidates.
// This process continues until `j` goes past the end of the array. The last `candidateIndex` that satisfied the condition is the farthest smaller element.
// This pass also takes **O(N)** time in total because the pointer `j` always moves forward and never backtracks.

// Time Complexity:
// O(N). The first pass takes O(N) to build the suffix minimum index array. The second pass takes at most O(N) because the index `j` we use to search for the farthest smaller element for each `i` always moves forward, making a total of at most `N` jumps across all outer loop iterations.

// Space Complexity:
// O(N) to store the `minSuffixIndex` array and the `result` array.

// Optimal Solution:

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> farthestSmallerRight(int n, int[] arr) {
        int[] minSuffixIndex = new int[n];
        minSuffixIndex[n - 1] = n - 1;

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[minSuffixIndex[i + 1]]) {
                minSuffixIndex[i] = i;
            } else {
                minSuffixIndex[i] = minSuffixIndex[i + 1];
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int currentFarthest = -1;
            int j = i + 1;
            while (j < n) {
                int candidateIndex = minSuffixIndex[j];
                if (arr[candidateIndex] < arr[i]) {
                    currentFarthest = candidateIndex;
                    j = candidateIndex + 1;
                } else {
                    j = candidateIndex + 1;
                }
            }
            result.add(currentFarthest);
        }
        return result;
    }
}