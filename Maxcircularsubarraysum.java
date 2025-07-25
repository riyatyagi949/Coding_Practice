/*
Problem Statement:
You are given a circular array arr[] of integers, find the maximum possible sum of a non-empty subarray. In a circular array, the subarray can start at the end and wrap around to the beginning. Return the maximum non-empty subarray sum, considering both non-wrapping and wrapping cases.

Approach:
The maximum subarray sum in a circular array can be one of two types:
1. The maximum subarray sum in a normal, non-circular array. This can be found using Kadane's algorithm.
2. The maximum subarray sum that wraps around. This can be found by inverting the signs of all elements in the array, finding the maximum subarray sum of this new array, and subtracting this from the total sum of the original array. This is because the minimum subarray sum in the original array (which would be the non-wrapping part of the wrapping subarray) becomes the maximum subarray sum in the inverted array. The sum of the original array minus the minimum subarray sum gives the maximum wrapping subarray sum.

We need to handle the case where all elements are negative. In this case, the maximum subarray sum is the single largest element. The wrapping case would give a negative result (total sum - min sum), but the non-wrapping case will correctly find the largest single element. We should only consider the wrapping case if the total sum is not equal to the minimum sum, as this would imply all elements are negative and the wrapping sum would be 0, which is incorrect.

Time Complexity: O(n)
Space Complexity: O(1)
*/

class Solution {
    public int maxSubarraySumCircular(int[] arr) {
        int n = arr.length;
        int totalSum = 0;
        int maxKadane = Integer.MIN_VALUE;
        int minKadane = Integer.MAX_VALUE;
        int currentMax = 0;
        int currentMin = 0;

        for (int i = 0; i < n; i++) {
            totalSum += arr[i];
            
            currentMax += arr[i];
            maxKadane = Math.max(maxKadane, currentMax);
            if (currentMax < 0) {
                currentMax = 0;
            }

            currentMin += arr[i];
            minKadane = Math.min(minKadane, currentMin);
            if (currentMin > 0) {
                currentMin = 0;
            }
        }
        
        if (totalSum == minKadane) {
            return maxKadane;
        } else {
            return Math.max(maxKadane, totalSum - minKadane);
        }
    }
}