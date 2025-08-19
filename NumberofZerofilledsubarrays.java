// Problem Statement:
// Given an integer array nums, return the number of subarrays filled with 0.
// A subarray is a contiguous non-empty sequence of elements within an array.

// Approach:
// We can iterate through the array and count the number of consecutive zeros.
// For each contiguous block of zeros of length `k`, the number of zero-filled
// subarrays is the sum of integers from 1 to `k`. This can be calculated
// using the formula `k * (k + 1) / 2`.
// We can use a counter to keep track of the current length of consecutive zeros.
// When we encounter a non-zero element or reach the end of the array,
// we calculate the number of subarrays for the current block of zeros and
// add it to our total count, then reset the counter.

// Time Complexity: O(n)
// We iterate through the array once.

// Space Complexity: O(1)
// We only use a few variables for counting.

