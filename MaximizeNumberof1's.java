// Problem Statement:
// Given a binary array arr[] containing only 0s and 1s and an integer k, you are allowed to flip at most k 0s to 1s. Find the maximum number of consecutive 1's that can be obtained in the array after performing the operation at most k times.

// Approach:
// This problem can be solved efficiently using the sliding window technique. We maintain a window (defined by left and right pointers) and expand it to the right. As we expand the window, we keep track of the number of zeros encountered within the current window.
// The right pointer `j` iterates through the array, and if we encounter a 0, we increment a counter for zeros. The left pointer `i` is used to shrink the window from the left.
// If the number of zeros in our current window exceeds `k`, it means we have flipped more zeros than allowed. To fix this, we need to shrink the window from the left by moving the left pointer `i` forward. As we move `i`, if the element at `arr[i]` was a 0, we decrement our zero counter because that 0 is no longer in our window.
// At each step, we calculate the length of the current valid window (j - i + 1) and update our maximum length found so far.
// This sliding window approach ensures that we always maintain a window with at most `k` zeros, and we are continuously expanding it to find the longest possible subarray.

// Time Complexity: O(N), where N is the size of the array.
// The right pointer `j` and the left pointer `i` both traverse the array at most once.

// Space Complexity: O(1)
// We only use a few variables for pointers and counters, which requires constant space.

// Optimal Solution:
class Solution {
    public int longestOnes(int[] arr, int k) {
        int i = 0;
        int j = 0;
        int zeroCount = 0;
        int maxLength = 0;

        while (j < arr.length) {
            if (arr[j] == 0) {
                zeroCount++;
            }

            while (zeroCount > k) {
                if (arr[i] == 0) {
                    zeroCount--;
                }
                i++;
            }
            maxLength = Math.max(maxLength, j - i + 1);
            j++;
        }
        return maxLength;
    }
}