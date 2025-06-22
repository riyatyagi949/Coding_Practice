// Problem Statement:
// Given an array arr[] of distinct positive integers. Your task is to find the largest subset such that for every pair of elements (x, y) in the subset, either x divides y or y divides x.
// Note: If multiple subsets of the same maximum length exist, return the one that is lexicographically greatest, after sorting the subset in ascending order.

// Approach:
// This problem can be solved using Dynamic Programming.
// First, sort the input array arr in ascending order. This helps in ensuring that if x divides y, then x will appear before y in the sorted array.
// We can define dp[i] as the length of the largest divisible subset ending with arr[i].
// We also need to store the previous element in the subset to reconstruct the path, so we use a prev[i] array to store the index of the previous element that forms the largest divisible subset ending at arr[i].

// Initialize dp[i] = 1 for all i, as each element itself forms a subset of length 1.
// Initialize prev[i] = i for all i, indicating no previous element initially.

// Iterate from i = 0 to n-1 (where n is the length of arr):
//   Iterate from j = 0 to i-1:
//     If arr[i] is divisible by arr[j]:
//       If dp[j] + 1 > dp[i]:
//         dp[i] = dp[j] + 1
//         prev[i] = j

// Keep track of the maximum length found so far (maxLength) and the index of the last element in that subset (lastIndex).

// After filling the dp array, reconstruct the largest divisible subset by backtracking using the prev array starting from lastIndex.
// Add elements to a list and then reverse the list to get the subset in ascending order.
// Since the problem asks for the lexicographically greatest subset among those with the same maximum length, sorting the input array initially and then building the subsets in this manner naturally helps achieve this. If arr[i] % arr[j] == 0, we prioritize extending a longer subset. If two subsets have the same length, by iterating j from 0 to i-1, we consider smaller divisors first, which contributes to the lexicographically greatest property.

// Time Complexity:
// Sorting takes O(N log N) time.
// The nested loops for DP take O(N^2) time.
// Reconstructing the subset takes O(N) time.
// Overall time complexity: O(N^2), where N is the number of elements in the input array.

// Space Complexity:
// O(N) for the dp array and prev array.
// O(N) for storing the result list.
// Overall space complexity: O(N), where N is the number of elements in the input array.

// Optimal Solution:
import java.util.*;

class Solution {
    public ArrayList<Integer> largestSubset(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(arr);

        int[] dp = new int[n];
        int[] prev = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            prev[i] = i;
        }

        int maxLength = 1;
        int lastIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (prev[lastIndex] != lastIndex) {
            result.add(arr[lastIndex]);
            lastIndex = prev[lastIndex];
        }
        result.add(arr[lastIndex]);

        Collections.reverse(result);
        return result;
    }
}