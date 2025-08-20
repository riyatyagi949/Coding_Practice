// This problem can be solved by first identifying the pivot point of the rotated sorted 1D array and then performing a binary search for the target value.

// Approach
// The key insight is that the 2D matrix, when flattened into a 1D array, is a rotated sorted array. We can find the target element in such an array using a modified binary search. A standard binary search won't work directly because the array is not fully sorted. However, each half of the array created by the midpoint will be sorted, allowing us to narrow down our search.

// Flatten the Matrix: The 2D matrix can be treated as a single 1D array of size n
// timesm. The element at mat[i][j] corresponds to the index i * m + j in the 1D array. Similarly, a 1D index k corresponds to the matrix coordinates mat[k / m][k % m].

// Binary Search on the Flattened Array: We can perform a binary search on the indices of this flattened array, from 0 to n * m - 1.

// Modified Binary Search Logic:

// Find the middle index mid.

// Get the value at mid: val = mat[mid / m][mid % m].

// If val == x, we've found the element, return true.

// Otherwise, we need to determine which half to continue searching in. Since the array is rotated, one of the two halves (from low to mid or from mid to high) will always be sorted.

// We check if the left half is sorted: mat[low / m][low % m] <= val.

// If it is sorted, and x is within this range (mat[low / m][low % m] <= x < val), search the left half by setting high = mid - 1.

// If x is not in this range, it must be in the right half, so set low = mid + 1.

// If the left half is not sorted, the right half must be sorted.

// Check if x is within the right half's range (val < x <= mat[high / m][high % m]). If it is, search the right half by setting low = mid + 1.

// Otherwise, search the left half by setting high = mid - 1.

// If the loop completes without finding the element, return false.

// Time and Space Complexity
// Time Complexity: O(log(n * m)). The binary search reduces the search space by half in each step, making it highly efficient.

// Space Complexity: O(1). We are only using a few variables for the binary search pointers, so the space required is constant.

// Optimal Solution-

class Solution {
    public boolean searchMatrix(int[][] mat, int x) {
        int n = mat.length, m = mat[0].length;
        int left = 0, right = n * m - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midVal = mat[mid / m][mid % m];

            if (midVal == x) 
            return true;

            int leftVal = mat[left / m][left % m];
            int rightVal = mat[right / m][right % m];

            if (leftVal <= midVal) { 
                if (x >= leftVal && x < midVal) {
                    right = mid - 1;
                } 
                else {
                    left = mid + 1;
                }
            } 
            else { 
                if (x > midVal && x <= rightVal) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}

