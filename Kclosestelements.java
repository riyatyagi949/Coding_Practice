// Problem Statement:
// You are given a sorted array arr[] of unique integers, an integer k, and a target value x.
// Return exactly k elements from the array closest to x, excluding x if it exists.
// An element a is closer to x than b if:
// |a - x| < |b - x|, or
// |a - x| == |b - x| and a > b (i.e., prefer the larger element if tied)
// Return the k closest elements in order of closeness.

// Approach:
// The problem asks us to find the k closest elements to a given target x from a sorted array,
// with a specific tie-breaking rule: if two elements have the same absolute difference from x,
// the larger element is preferred. The element x itself should be excluded if it exists in the array.
// The result should be returned in order of closeness.

// A min-priority queue (min-heap) is a suitable data structure for this problem.
// We need to store pairs of (absolute_difference, element_value) in the priority queue.
// The custom comparator for the priority queue will handle the sorting logic:
// 1. Sort by absolute difference in ascending order.
// 2. If absolute differences are equal, sort by element value in descending order (to prefer larger element).

// Algorithm:
// 1. Initialize a min-priority queue `pq` with a custom comparator.
//    The comparator will prioritize elements with smaller absolute difference from `x`.
//    If absolute differences are equal, it will prioritize elements with larger values.
//    The elements stored in the priority queue will be `int[]` of size 2: `{absolute_difference, element_value}`.
// 2. Iterate through each element `val` in the input array `arr`.
// 3. If `val` is equal to `x`, skip it (as `x` should be excluded).
// 4. Calculate the absolute difference: `diff = Math.abs(val - x)`.
// 5. Offer a new array `{diff, val}` to the priority queue.
// 6. After iterating through all elements in `arr`, the priority queue will contain all eligible elements
//    sorted according to our closeness criteria.
// 7. Initialize an integer array `res` of size `k` to store the result.
// 8. Poll `k` elements from the priority queue and store their `element_value` in the `res` array.
//    The elements will be polled in increasing order of closeness.
// 9. Return the `res` array.

// Time Complexity:
// 1. Building the priority queue: We iterate through the `arr` array once. For each element,
//    we perform `Math.abs` and `pq.offer()`. `pq.offer()` takes `O(log N)` time, where `N` is the number of elements
//    currently in the priority queue (at most `arr.length`).
//    So, this step takes `O(arr.length * log(arr.length))`.
// 2. Polling `k` elements: We poll `k` times from the priority queue. Each `pq.poll()` operation
//    takes `O(log N)` time.
//    So, this step takes `O(k * log(arr.length))`.
// Overall Time Complexity: `O(arr.length * log(arr.length))` because `arr.length * log(arr.length)` is generally
// larger than `k * log(arr.length)`.

// Space Complexity:
// 1. The priority queue `pq` stores at most `arr.length` elements. Each element is an `int[]` of size 2.
//    So, the space for the priority queue is `O(arr.length)`.
// 2. The result array `res` takes `O(k)` space.
// Overall Space Complexity: `O(arr.length)` because `arr.length` is generally larger than `k`.

// Optimal Solution:

class Solution {
    int[] printKClosest(int[] arr, int k, int x) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) return a[0] - b[0]; 
                return b[1] - a[1]; 
            }
        );
         for (int val : arr) {
            if (val == x) {
                continue; 
            }
            pq.offer(new int[]{Math.abs(val - x), val});
        }
         int[] res = new int[k];
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            res[i] = pq.poll()[1]; 
        }
        return res;
    }
}