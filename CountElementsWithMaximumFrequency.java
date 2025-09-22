/**
 * Problem Statement:
 * Given an array `nums` of positive integers, return the total frequencies of elements that have the maximum frequency.
 * For example, if the maximum frequency in the array is `k`, and `m` elements have this frequency, the result is `k * m`.
 *
 * Optimal Approach:
 * The most efficient way to solve this is to use a frequency map to count the occurrences of each number.
 * Since the constraints are small (1 <= nums[i] <= 100), a simple integer array can serve as a hash map, making the process very fast.
 *
 * The algorithm involves two main steps:
 * 1. **Count Frequencies:** Iterate through the input array `nums` and store the frequency of each number.
 * - An array `freq` of size 101 (or 102) is sufficient to store counts for numbers from 1 to 100.
 * - Loop through `nums` and increment `freq[num]` for each `num`.
 *
 * 2. **Find Max Frequency and Calculate Total:** After counting, find the maximum frequency and then sum the frequencies of all elements that match this maximum.
 * - Initialize a variable `maxFreq` to 0.
 * - Iterate through the `freq` array to find the highest frequency.
 * - Initialize a variable `totalFreq` to 0.
 * - Iterate through the `freq` array again. For each frequency that equals `maxFreq`, add that frequency to `totalFreq`.
 * - Alternatively, a more optimized single pass:
 * - During the first pass, while counting frequencies, you can also keep track of the `maxFreq` seen so far.
 * - Reset `totalFreq` to 0 whenever a new `maxFreq` is found, and then add the count of the new number. If the new count equals the current `maxFreq`, just add it.
 *
 * Let's refine the two-pass approach as it is very clear and easy to implement.
 *
 * Time Complexity: O(n), where `n` is the length of `nums`.
 * - The first loop to count frequencies takes O(n).
 * - The second and third loops (or a single combined loop) to find the max frequency and then calculate the total sum take O(101), which is constant time.
 *
 * Space Complexity: O(1) because we use a fixed-size array (101 elements) for frequency counting, regardless of the input size `n`.
 */
// Optimal Solution in Java -

class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] freq = new int[101]; 

        // Step 1: Count frequencies and find the maximum frequency in one pass
        int maxFreq = 0;
        for (int num : nums) {
            freq[num]++;
            if (freq[num] > maxFreq) {
                maxFreq = freq[num];
            }
        }

        // Step 2: Sum the frequencies of elements that have the maximum frequency
        int totalFreq = 0;
        for (int count : freq) {
            if (count == maxFreq) {
                totalFreq += count;
            }
        }

        return totalFreq;
    }
}