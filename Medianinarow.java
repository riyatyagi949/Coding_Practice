// Problem Statement:
// Given a row-wise sorted matrix mat[][] of size n*m, where the number of rows and columns is always odd.
// Return the median of the matrix.

// Approach:
// The problem asks for the median of a matrix where each row is sorted. Since both n and m are odd, the total number of elements in the matrix, n*m, is also odd. The median will be the (n*m / 2 + 1)-th smallest element.
// A naive approach would be to flatten the matrix into a single array, sort it, and then find the median. However, this would have a time complexity of O(n*m log(n*m)), which might be too slow given the constraints.
// A more efficient approach is to use binary search on the answer. The median value must lie within the range of possible values, which is from 1 to 2000 based on the constraints. We can perform a binary search within this range.
// For a given potential median 'mid', we need to count how many elements in the matrix are less than or equal to 'mid'. This count can be found efficiently because each row is sorted. For each row, we can use binary search (or a two-pointer approach) to find the number of elements less than or equal to 'mid'. The time complexity for this step for a single row is O(log m). Since there are n rows, the total time to count elements for a given 'mid' is O(n log m).
// Let the target number of elements less than or equal to the median be 'desired', which is (n*m) / 2.
// We initialize our binary search range from low = 1 to high = 2000.
// In each step of the binary search:
// 1. Calculate mid = low + (high - low) / 2.
// 2. Count the number of elements in the matrix that are less than or equal to 'mid'. Let this count be 'count'.
// 3. If 'count' is less than or equal to 'desired', it means the median is larger than 'mid', so we update our search range to low = mid + 1.
// 4. If 'count' is greater than 'desired', it means 'mid' could be the median, or the median is smaller than 'mid', so we update high = mid.
// The binary search continues until low equals high. The final value of 'low' (or 'high') will be the median.

// Time Complexity:
// The binary search for the median is performed on a range from 1 to 2000. The number of iterations is O(log(2000)).
// In each iteration, we iterate through all n rows and perform a binary search on each row, which takes O(log m) time.
// Thus, the total time complexity is O(n * log m * log(max_val)), where max_val is the maximum possible value in the matrix (2000).

// Space Complexity:
// The algorithm uses a constant amount of extra space, so the space complexity is O(1).

// Optimal Solution:
class Solution {
    public int median(int[][] mat) {
        int n = mat.length, m = mat[0].length;

        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            low = Math.min(low, mat[i][0]);     
            high = Math.max(high, mat[i][m - 1]); 
        }
        int required = (n * m + 1) / 2;

        while (low < high) {
            int mid = (low + high) / 2;
            int count = 0;

            for (int i = 0; i < n; i++) {
                count += countLessEqual(mat[i], mid);
            }

            if (count < required) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return low;
    }
    private int countLessEqual(int[] row, int target) {
        int l = 0, r = row.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (row[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}

