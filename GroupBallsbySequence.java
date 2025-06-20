// Problem Statement:
// You are given an array arr[] of positive integers, where each element arr[i] represents the number written on the i-th ball, and a positive integer k.
// Your task is to determine whether it is possible to rearrange all the balls into groups such that:
// 1. Each group contains exactly k balls.
// 2. The numbers in each group are consecutive integers.

// Approach:
// The problem can be solved by using a frequency map (HashMap) to store the count of each number in the array.
// First, check if the total number of balls (arr.length) is divisible by k. If not, it's impossible to form groups of size k, so return false.
// Then, sort the array to process numbers in ascending order. This helps in easily finding consecutive numbers.
// Iterate through the sorted array. For each number, if its frequency is greater than 0:
// 1. Decrement its frequency.
// 2. Try to form a consecutive sequence of k numbers starting from the current number.
// 3. For each subsequent number in the sequence (current number + 1 to current number + k - 1), check if it exists in the frequency map and its frequency is greater than 0.
// 4. If any number in the sequence is missing or has a frequency of 0, then it's not possible to form a valid group, so return false.
// 5. If all k numbers are found and their frequencies are decremented, continue to the next number in the sorted array.
// If all numbers are successfully grouped, return true.

// Time Complexity:
// O(N log N) due to sorting the array.
// The iteration through the array takes O(N) time.
// Inside the loop, HashMap operations (get, put) take O(1) on average.
// In the worst case, we might iterate k times for each starting number, but each number is processed and decremented from the map at most once across all sequence formations.
// Therefore, the overall time complexity is dominated by sorting.

// Space Complexity:
// O(N) in the worst case for the HashMap to store frequencies of all distinct numbers.

// Optimal Solution:
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean validgroup(int[] arr, int k) {
        if (arr.length % k != 0) {
            return false;
        }

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        Arrays.sort(arr);

        for (int num : arr) {
            if (freqMap.get(num) > 0) {
                for (int i = 0; i < k; i++) {
                    int currentConsecutiveNum = num + i;
                    if (freqMap.containsKey(currentConsecutiveNum) && freqMap.get(currentConsecutiveNum) > 0) {
                        freqMap.put(currentConsecutiveNum, freqMap.get(currentConsecutiveNum) - 1);
                    } else {
                        return false; 
                    }
                }
            }
        }
        return true;
    }
}