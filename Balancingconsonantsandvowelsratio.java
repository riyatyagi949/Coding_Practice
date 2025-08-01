// Problem Statement:
// Given an array of strings `arr[]`, where each `arr[i]` consists of lowercase English alphabets.
// You need to find the number of balanced strings in `arr[]` which can be formed by concatenating one or more contiguous strings of `arr[]`.
// A balanced string contains an equal number of vowels and consonants.

// Approach:
// This problem can be solved by converting the problem of finding contiguous balanced strings into a problem of finding subarrays with a sum of zero.
// First, we need to count the difference between the number of vowels and consonants for each string in the input array.
// We can represent vowels as +1 and consonants as -1. This way, a balanced string will have a total sum of 0.
// Let `diff[i]` be the difference (vowels - consonants) for `arr[i]`.
// A contiguous subarray `arr[i..j]` is balanced if the sum of `diff` values from `i` to `j` is 0.
// This is equivalent to `prefixSum[j] - prefixSum[i-1] == 0`, where `prefixSum` is the prefix sum of the `diff` array.
// So, `prefixSum[j] == prefixSum[i-1]`.
// We can iterate through the `diff` array and calculate the prefix sum. We use a hash map to store the frequency of each prefix sum encountered so far.
// If we encounter a prefix sum `p` at index `i`, we can find all previous indices `j` where `prefixSum[j] == p`.
// Each of these occurrences forms a balanced subarray. The number of such subarrays ending at `i` would be the frequency of `p` in the hash map.
// We initialize the map with `(0, 1)` to handle subarrays starting from the beginning of the `diff` array that have a prefix sum of 0.

// Time Complexity: O(N), where N is the total number of characters across all strings.
// We iterate through all the strings to calculate the vowel/consonant difference, which takes O(N).
// Then, we iterate through the `diff` array once, which has a length of `arr.length`. The hash map operations take O(1) on average.
// The overall time complexity is dominated by the initial counting, which is O(N).

// Space Complexity: O(M), where M is the length of the `arr` array.
// We create a `diff` array of size `M`.
// The hash map can store up to `M+1` distinct prefix sums in the worst case.
// Thus, the space complexity is O(M).

import java.util.*;

class Solution {
    public int countBalanced(String[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        freqMap.put(0, 1);
        int totalBalanced = 0;
        int diff = 0;

        for (String str : arr) {
            for (char ch : str.toCharArray()) {
                if (isVowel(ch)) {
                    diff += 1;
                } else {
                    diff -= 1;
                }
            }
            totalBalanced += freqMap.getOrDefault(diff, 0);
            freqMap.put(diff, freqMap.getOrDefault(diff, 0) + 1);
        }

        return totalBalanced;
    }

    private boolean isVowel(char ch) {
        return "aeiou".indexOf(ch) >= 0;
    }
}
