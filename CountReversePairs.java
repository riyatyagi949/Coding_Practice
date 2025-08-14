// Problem Statement:
// You are given an array `arr` of positive integers. Find the count of "reverse pairs".
// A pair of indices (i, j) is a reverse pair if:
// 1. 0 <= i < j < arr.size()
// 2. arr[i] > 2 * arr[j]

// Approach:
// A naive approach would be to use two nested loops to check every possible pair (i, j) with i < j and count the pairs that satisfy `arr[i] > 2 * arr[j]`. This approach has a time complexity of O(N^2), which is too slow for the given constraints.

// A more efficient approach is to use a divide and conquer strategy, similar to Merge Sort. The core idea is to count the reverse pairs while merging the sorted subarrays.
//
// Let's break down the process:
// 1. **Divide**: Split the array into two halves recursively until we have subarrays of size 1.
// 2. **Conquer**: Sort the two halves.
// 3. **Combine (and count)**: This is the crucial step. While merging the two sorted halves, we count the reverse pairs.
//
// Let the two sorted halves be `left` and `right`.
// We iterate through the `left` array with a pointer `i`. For each element `arr[i]`, we need to find how many elements `arr[j]` in the `right` array satisfy `arr[i] > 2 * arr[j]`.
// Since both `left` and `right` arrays are sorted, we can use a two-pointer approach to do this efficiently. Let's use a pointer `j` for the `right` array.
// For each `arr[i]` from the `left` array, we advance `j` in the `right` array as long as `arr[i] > 2 * arr[j]`. The number of elements skipped by `j` at this point gives us the count of reverse pairs for the current `arr[i]`. We add this count to our total.
// After counting, we proceed with the standard merge sort merge logic to combine the two sorted halves into a single sorted array. This ensures that the array remains sorted for the next recursive call.

// The merge function will look something like this:
// `mergeAndCount(arr, left, mid, right)`
// This function will first count the reverse pairs between the `[left, mid]` and `[mid+1, right]` subarrays.
// After counting, it will merge these two sorted subarrays into a single sorted subarray.

// The main `countReversePairs` function will be a recursive function that splits the array, calls itself for the two halves, and then calls the `mergeAndCount` function. The total count will be the sum of counts from the left half, the right half, and the count from the merge step.

// Time Complexity:
// O(N log N) - The recursive structure is identical to Merge Sort, and the counting step during the merge operation takes linear time, O(N). Therefore, the recurrence relation is T(N) = 2T(N/2) + O(N), which solves to O(N log N).

// Space Complexity:
// O(N) - This is due to the auxiliary array used during the merge step.

// Optimal Solution:
class Solution {
    public int reversePairs(int[] nums) {
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }

    private int mergeSortAndCount(int[] nums, int low, int high) {
        if (low >= high) {
            return 0;
        }

        int mid = low + (high - low) / 2;
        int count = mergeSortAndCount(nums, low, mid);
        count += mergeSortAndCount(nums, mid + 1, high);
        count += merge(nums, low, mid, high);
        return count;
    }

    private int merge(int[] nums, int low, int mid, int high) {
        int count = 0;
        int j = mid + 1;
        for (int i = low; i <= mid; i++) {
            while (j <= high && (long) nums[i] > 2L * nums[j]) {
                j++;
            }
            count += (j - (mid + 1));
        }

        int[] temp = new int[high - low + 1];
        int i = low;
        j = mid + 1;
        int k = 0;

        while (i <= mid && j <= high) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }

        while (j <= high) {
            temp[k++] = nums[j++];
        }

        for (int l = 0; l < temp.length; l++) {
            nums[low + l] = temp[l];
        }

        return count;
    }
}
    

