/*
Problem Statement:
Given an array arr[] and an integer k, the task is to find the length of the longest subarray in which the count of elements greater than k is more than the count of elements less than or equal to k.

Approach:
This problem can be transformed into a prefix sum problem. We can create a new array, let's call it 'transformed_arr', where each element represents a condition. If arr[i] > k, we can assign a value of 1. If arr[i] <= k, we can assign a value of -1.
The condition "count of elements greater than k is more than the count of elements less than or equal to k" can be rephrased as "the sum of the subarray in the transformed array is greater than 0".
The problem now becomes finding the longest subarray with a positive sum.
We can iterate through the array and calculate the prefix sum. We use a HashMap to store the first occurrence of each prefix sum.
For each index 'i', we calculate the current prefix sum. If the current prefix sum is positive, the subarray from index 0 to 'i' is a potential answer.
If the current prefix sum is not positive, we check if `current_sum - 1` exists in our HashMap. If it does, it means there is a subarray from `map.get(current_sum - 1) + 1` to 'i' with a sum of 1. A sum of 1 is the minimum positive sum.
We keep track of the maximum length found so far.

Time Complexity: O(N)
The time complexity is O(N) because we iterate through the array once to calculate prefix sums and update the hash map. Each hash map operation (insertion and lookup) takes, on average, O(1) time.

Space Complexity: O(N)
The space complexity is O(N) because, in the worst case, the hash map can store up to N distinct prefix sums.

*/

import java.util.HashMap;

class Solution {
    public int longestSubarray(int[] arr, int k) {
        int n = arr.length;
        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            diff[i] = (arr[i] > k) ? 1 : -1;
        }
        HashMap<Integer, Integer> prefixMap = new HashMap<>();
        int sum = 0, maxLen = 0;

        for (int i = 0; i < n; i++) {
            sum += diff[i];

            if (sum > 0) {
                maxLen = i + 1;
            }
            else {
                prefixMap.putIfAbsent(sum, i);

                if (prefixMap.containsKey(sum - 1)) {
                    maxLen = Math.max(maxLen, i - prefixMap.get(sum - 1));
                }
            }
        }
        return maxLen;
    }
}
