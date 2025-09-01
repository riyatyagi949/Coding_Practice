// Problem Statement:
// Given an array `arr[]` of positive integers and an integer `k`. You have to find the sum of the modes of all the subarrays of size `k`.
// The mode of a subarray is the element that occurs with the highest frequency. If multiple elements have the same highest frequency, the smallest such element is considered the mode.

// Approach:
// This problem can be solved efficiently using a sliding window approach with a frequency map and a data structure to maintain the mode and its frequency.
// We can use a `HashMap` to store the frequency of each element within the current window of size `k`.
// To find the mode efficiently at each step, we need a way to quickly get the element with the highest frequency and, in case of a tie, the smallest element. A simple variable pair `(mode, maxFrequency)` is not sufficient because when an element is removed from the window, the mode might change, and we would need to re-scan the `HashMap` to find the new mode, which is inefficient.
// A better approach is to use a `TreeMap` to maintain frequencies. The keys of the `TreeMap` can be the frequencies, and the values can be a `TreeSet` of elements that have that frequency. This allows us to quickly access the highest frequency (last key in the `TreeMap`) and the smallest element with that frequency (first element in the corresponding `TreeSet`).
// The sliding window works as follows:
// 1. Initialize a `TreeMap` `freqMap` to store `frequency -> TreeSet of elements`.
// 2. Initialize a `HashMap` `countMap` to store `element -> frequency`.
// 3. Initialize `totalSum` to 0.
// 4. Process the first window of size `k`:
//    - Iterate from `i = 0` to `k-1`.
//    - For each element `arr[i]`:
//      - Get its current frequency `oldFreq` from `countMap`.
//      - If `oldFreq` is greater than 0, remove the element from `freqMap` at `oldFreq`.
//      - Increment its frequency to `newFreq` in `countMap`.
//      - Add the element to `freqMap` at `newFreq`.
//    - Add the mode of this initial window to `totalSum`. The mode is the smallest element in the `TreeSet` corresponding to the last key in `freqMap`.
// 5. Slide the window from `i = k` to `arr.length - 1`:
//    - Add the new element `arr[i]` to the window:
//      - Get its `oldFreq`.
//      - Remove from `freqMap` at `oldFreq` if it exists.
//      - Increment its `newFreq` in `countMap`.
//      - Add it to `freqMap` at `newFreq`.
//    - Remove the old element `arr[i - k]` from the window:
//      - Get its `oldFreq`.
//      - Remove from `freqMap` at `oldFreq`.
//      - Decrement its `newFreq` in `countMap`.
//      - If `newFreq` is greater than 0, add it back to `freqMap` at `newFreq`.
//    - Add the mode of the current window to `totalSum`.
// 6. Return `totalSum`.

// Time Complexity:
// O(N log K) where N is the length of the array and K is the window size.
// The sliding window iterates through the array once (N steps).
// Inside the loop, adding or removing an element from the `TreeMap` and `HashMap` takes logarithmic time, specifically `O(log K)` because the number of distinct frequencies and elements in the window is at most `k`. `TreeMap` and `TreeSet` operations are logarithmic.

// Space Complexity:
// O(K) where K is the window size.
// In the worst case, all elements in a window are distinct, and each has a frequency of 1.
// The `countMap` stores at most `K` elements.
// The `freqMap` stores at most `K` entries, as there can be at most `K` distinct frequencies. Each `TreeSet` can contain multiple elements, but the total number of elements across all `TreeSets` is at most `K`.

class Solution {
    public long sumOfModes(int[] arr, int k) {
        long totalSum = 0;
        java.util.TreeMap<Integer, java.util.TreeSet<Integer>> freqMap = new java.util.TreeMap<>();
        java.util.HashMap<Integer, Integer> countMap = new java.util.HashMap<>();

        // Helper function to update frequency map
        java.util.function.BiConsumer<Integer, Integer> updateFreqMap = (element, newFreq) -> {
            int oldFreq = countMap.getOrDefault(element, 0);
            if (oldFreq > 0) {
                java.util.TreeSet<Integer> oldSet = freqMap.get(oldFreq);
                oldSet.remove(element);
                if (oldSet.isEmpty()) {
                    freqMap.remove(oldFreq);
                }
            }
            if (newFreq > 0) {
                freqMap.computeIfAbsent(newFreq, x -> new java.util.TreeSet<>()).add(element);
            }
            countMap.put(element, newFreq);
        };

        // Initial window
        for (int i = 0; i < k; i++) {
            int element = arr[i];
            updateFreqMap.accept(element, countMap.getOrDefault(element, 0) + 1);
        }

        totalSum += freqMap.lastEntry().getValue().first();

        // Slide the window
        for (int i = k; i < arr.length; i++) {
            // Add new element
            int newElement = arr[i];
            updateFreqMap.accept(newElement, countMap.getOrDefault(newElement, 0) + 1);

            // Remove old element
            int oldElement = arr[i - k];
            updateFreqMap.accept(oldElement, countMap.getOrDefault(oldElement, 0) - 1);

            // Add mode of current window
            totalSum += freqMap.lastEntry().getValue().first();
        }

        return totalSum;
    }
}