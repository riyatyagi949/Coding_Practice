/**
 * Problem Statement:
 * Given an array `arr` of `n` tower heights and an integer `k`, you must either increase or decrease each tower's height by `k`.
 * The resulting heights must be non-negative. The goal is to find the minimum possible difference between the tallest and shortest towers
 * after these operations.
 *
 * Optimal Approach:
 * The key to solving this problem efficiently is to first sort the array of tower heights. This allows us to reason about the final
 * minimum and maximum heights in a structured way.
 *
 * After sorting, let's consider the towers. We have two choices for each tower: `arr[i] + k` or `arr[i] - k`.
 * The optimal solution will have a structure where some towers are increased and the rest are decreased. Because the array is sorted,
 * a logical strategy is to find a "split point" in the array. All towers before this point are increased, and all towers after it are decreased.
 *
 * Let the sorted array be `arr[0], arr[1], ..., arr[n-1]`.
 * We can initially consider the difference between the largest and smallest elements if we don't perform any operations: `arr[n-1] - arr[0]`.
 * This serves as our initial minimum difference.
 *
 * Now, we iterate through the sorted array from the first element up to the second-to-last element. At each index `i`, we consider
 * splitting the array. The towers from `0` to `i` are increased by `k`, and towers from `i+1` to `n-1` are decreased by `k`.
 *
 * For each split point `i`:
 * 1. The potential new minimum height will be the smaller of the first increased tower (`arr[0] + k`) and the first decreased tower (`arr[i+1] - k`).
 * `newMin = Math.min(arr[0] + k, arr[i+1] - k)`
 * 2. The potential new maximum height will be the larger of the last increased tower (`arr[i] + k`) and the last decreased tower (`arr[n-1] - k`).
 * `newMax = Math.max(arr[i] + k, arr[n-1] - k)`
 *
 * It's crucial to check if the decrease operation `arr[i+1] - k` results in a non-negative height. If it doesn't, we skip this split point
 * as it's not a valid configuration.
 *
 * We update our minimum difference (`ans`) with the difference calculated for each valid split point.
 * `ans = Math.min(ans, newMax - newMin)`
 *
 * This approach covers all valid configurations and guarantees finding the optimal solution because sorting ensures that the minimum and maximum
 * elements will always be found at the extremes of the two partitioned segments.
 *
 * Time Complexity: O(n log n) due to the sorting step. The subsequent single loop takes O(n) time.
 * Space Complexity: O(1) if sorting is done in-place.
 */

//  Optimal  Solution in Java-
import java.util.*;

class Solution {
    public int getMinDiff(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);

        int ans = arr[n-1] - arr[0];
        int smallest = arr[0] + k;
        int largest = arr[n-1] - k;

        for (int i = 0; i < n - 1; i++) {
            int minVal = Math.min(smallest, arr[i+1] - k);
            int maxVal = Math.max(largest, arr[i] + k);

            if (minVal < 0) 
            continue; 
            ans = Math.min(ans, maxVal - minVal);
        }

        return ans;
    }
}

