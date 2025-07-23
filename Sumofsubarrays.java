// Problem Statement:
// Given an array arr[], find the sum of all the subarrays of the given array.
// Note: It is guaranteed that the total sum will fit within a 32-bit integer range.

// Approach:
// A naive approach would be to generate all possible subarrays, calculate their sums, and then add them up.
// This would involve three nested loops: one for the starting element, one for the ending element, and one for summing the elements within the subarray.
// This would lead to a time complexity of O(n^3).

// A more optimized approach involves observing how many times each element contributes to the total sum.
// For an element arr[i] at index i, it will be part of subarrays that start at or before i and end at or after i.
// The number of subarrays starting at or before i is (i + 1).
// The number of subarrays ending at or after i is (n - i).
// So, the element arr[i] will contribute to (i + 1) * (n - i) subarrays.
// Therefore, the total sum of all subarrays can be calculated by summing (arr[i] * (i + 1) * (n - i)) for all i from 0 to n-1.

// Time Complexity: O(n)
// The approach iterates through the array once to calculate the sum, making it linear in time complexity.

// Space Complexity: O(1)
// The approach uses a constant amount of extra space, as it only stores a few variables for the sum and loop counter.

class Solution {
    public int subarraySum(int[] arr) {
        int n = arr.length;
        long total = 0;
        
        for (int i = 0; i < n; i++) {
            long contribution = (long) arr[i] * (i + 1) * (n - i);
            total += contribution;
        }
         return (int) total;
    }
}
