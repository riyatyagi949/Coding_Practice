// Problem Statement:
// You are given two unsorted arrays a[] and b[]. Both arrays may contain duplicate elements. For each element in a[], your task is to count how many elements in b[] are less than or equal to that element.

// Approach:
// The most efficient way to solve this problem is to sort array b. Once b is sorted, for each element in array a, we can use binary search (specifically, Collections.binarySearch or a custom binary search that finds the insertion point) to find the count of elements in b that are less than or equal to the current element from a.
// The `upperBound` function finds the index of the first element in the sorted array `b` that is strictly greater than `x`. This index directly gives us the count of elements less than or equal to `x`.

// Time Complexity:
// Sorting array b takes O(M log M) time, where M is the size of array b.
// For each element in array a (N elements), we perform a binary search on array b, which takes O(log M) time.
// So, the total time complexity is O(M log M + N log M).

// Space Complexity:
// O(1) if we sort b in-place and store the result in a new ArrayList. If we consider the space for the sorted copy of b (if not sorting in-place) or the output ArrayList, it would be O(M) or O(N) respectively. Here, we modify the input array b, so auxiliary space is O(1) beyond the output list.

class Solution {
    public static ArrayList<Integer> countLessEq(int a[], int b[]) {
        java.util.Arrays.sort(b);

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            int count = upperBound(b, a[i]);
            result.add(count);
        }
        return result;
    }
    private static int upperBound(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int ans = 0; 

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= x) {
                ans = mid + 1;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}