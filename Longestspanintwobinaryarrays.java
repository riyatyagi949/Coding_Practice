// Problem Statement:
// Given two binary arrays, a1[] and a2[]. Find the length of longest common span (i, j) where j>= i such that a1[i] + a1[i+1] + .... + a1[j] = a2[i] + a2[i+1] + ... + a2[j].

// Approach:
// The problem asks for the longest common span (subarray) in two binary arrays where the sum of elements in that span is equal for both arrays.
// This can be rephrased as finding the longest span (i, j) such that:
// (a1[i] + ... + a1[j]) - (a2[i] + ... + a2[j]) = 0
// This is equivalent to finding the longest span (i, j) such that:
// (a1[i] - a2[i]) + (a1[i+1] - a2[i+1]) + ... + (a1[j] - a2[j]) = 0

// Let's create a new array `diff` where `diff[k] = a1[k] - a2[k]`.
// Now, the problem transforms into finding the longest subarray in `diff` that sums to 0.
// This is a classic problem that can be solved using a HashMap. We iterate through the `diff` array, maintaining a running `sum`.
// If `sum` becomes 0 at any point, it means the subarray from index 0 to the current index `i` has a sum of 0, so the length is `i + 1`.
// If `sum` has been seen before (i.e., `map.containsKey(sum)`), it means the subarray between the previous occurrence of `sum` and the current index `i` sums to 0. The length of this subarray would be `i - map.get(sum)`. We take the maximum of this length and the current `maxLength`.
// If `sum` is not in the map, we store the current `sum` and its index `i` in the map.

// Time Complexity: O(n)
// We iterate through the arrays once to create the `diff` array and once more to calculate sums and use the hash map. Hash map operations (put, get, containsKey) take O(1) on average.
// Space Complexity: O(n)
// In the worst case, the hash map can store up to n distinct sum values.


class Solution {
    public int longestCommonSum(int[] a1, int[] a2) {
        int n = a1.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = a1[i] - a2[i];
        }
        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();
        int sum = 0;
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (sum == 0) {
                maxLength = i + 1;
            } else if (map.containsKey(sum)) {
                maxLength = Math.max(maxLength, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxLength;
    }
}