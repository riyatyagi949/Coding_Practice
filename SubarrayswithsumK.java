/*
Problem Statement:
Given an unsorted array arr[] of integers, find the number of subarrays whose sum exactly equal to a given number k.

Approach:
This problem can be efficiently solved using a HashMap. The idea is to keep track of the cumulative sum of the array elements as we iterate through the array. For each element, we calculate the current cumulative sum. If (current_sum - k) exists in our HashMap, it means that there is a subarray ending at the current position whose sum is k. The number of times (current_sum - k) has appeared in the HashMap tells us how many such subarrays exist.

We initialize a HashMap to store (cumulative_sum, frequency) pairs. We also initialize a variable 'count' to 0 (to store the number of subarrays with sum k) and 'currentSum' to 0. We put (0, 1) into the HashMap initially to handle cases where a subarray starting from the beginning itself sums up to k.

Iterate through the array:
1. Add the current element to 'currentSum'.
2. Check if 'currentSum - k' exists in the HashMap. If it does, add its frequency to 'count'.
3. Increment the frequency of 'currentSum' in the HashMap. If 'currentSum' is not present, add it with frequency 1.

Time Complexity:
The time complexity is O(N) because we iterate through the array once, and HashMap operations (put, get) take O(1) on average.

Space Complexity:
The space complexity is O(N) in the worst case, as the HashMap can store up to N distinct cumulative sums.
*/

// Optimal Solution:-
import java.util.*;

class Solution {
    public int cntSubarrays(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int count = 0;
        int prefixSum = 0;

        for (int num : arr) {
            prefixSum += num;
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
          return count;
    }
}
