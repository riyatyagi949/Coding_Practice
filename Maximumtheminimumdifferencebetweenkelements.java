// Problem Statement:
// Given an array arr[] of integers and an integer k, select k elements from the array such that the minimum absolute difference between any two of the selected elements is maximized. Return this maximum possible minimum difference.

// Approach:
// This problem can be solved using binary search on the answer. The goal is to maximize the minimum difference. We can binary search for the optimal minimum difference, which we'll call 'x'.
// The search space for 'x' ranges from 0 to the difference between the largest and smallest elements in the array.
// For a given potential answer 'mid', we check if it's possible to select k elements from the array such that the difference between any two of the selected elements is at least 'mid'.
// This check is performed greedily on a sorted version of the array. We start by selecting the first element. Then we iterate through the rest of the array, selecting the next available element that is at least 'mid' distance away from the last selected element.
// If we are able to select k or more elements, 'mid' is a feasible minimum difference, and we try for a larger one by searching in the right half of the binary search range. Otherwise, 'mid' is too large, and we search in the left half.

// Time Complexity:
// The time complexity is dominated by sorting the array, which takes O(N log N). The binary search performs O(log(Max-Min)) iterations, and each feasibility check takes O(N).
// Thus, the total time complexity is O(N log N + N * log(Max-Min)), which is effectively O(N log N).

// Space Complexity:
// The space complexity is O(1) if the array is sorted in-place.

// Optimal  Solution - 

class Solution {
    public int maxMinDiff(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        
        int low = 0, high = arr[n - 1] - arr[0];
        int ans = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlace(arr, k, mid)) {
                ans = mid;
                low = mid + 1;  
            } 
            else {
                high = mid - 1;
            }
        }
        return ans;
    }
    private boolean canPlace(int[] arr, int k, int mid) {
        int count = 1; 
        int lastPicked = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - lastPicked >= mid) {
                count++;
                lastPicked = arr[i];
                if (count >= k)
                return true;
            }
        }
        return false;
    }
}
