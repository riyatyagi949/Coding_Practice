// A concise and direct solution to the "Count the number of possible triangles" problem is to first sort the input array. This allows for an efficient approach to counting valid triangles.

// Method Explanation
// To form a triangle with side lengths a, b, and c, the following three conditions must be met:

// a + b > c

// a + c > b

// b + c > a

// If we sort the array in non-decreasing order, we can simplify these conditions. Let the sorted side lengths be a, b, and c where a ≤ b ≤ c. The last two conditions (a + c > b and b + c > a) are always true because c is the largest side, and a and b are non-negative. Therefore, we only need to check the condition a + b > c.

// After sorting the array, we can iterate through the elements. For each element arr[k] (where k is the index of the largest side c), we use a two-pointer approach to find pairs (arr[i], arr[j]) that can form a triangle with arr[k]. We initialize i at the beginning of the array (index 0) and j at the element just before k (index k-1).

// If arr[i] + arr[j] > arr[k], then any element between i and j will also satisfy the condition with arr[i] and arr[k]. This is because the array is sorted, so for a given arr[i], any arr[x] where i < x < j will be less than or equal to arr[j], and thus arr[i] + arr[x] + arr[k] will also form a triangle. The number of such pairs for a fixed i is j - i. We add this count to our total and decrement j to find more pairs.

// If arr[i] + arr[j] ≤ arr[k], the sum is too small. We need a larger sum, so we increment i to use a larger number.

// We repeat this process until the pointers i and j cross. The total count accumulated throughout this process is the number of possible triangles.

// Optimal Solution in Java - 

import java.util.Arrays;

class Solution {
    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        int n = nums.length;
        
        for (int k = n - 1; k >= 2; k--) {
            int i = 0;
            int j = k - 1;
            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    count += (j - i);
                    j--;
                }
                 else {
                    i++;
                }
            }
        }
        return count;
    }
}
