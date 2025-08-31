// Problem Statement:
// Given an array `arr` of non-negative integers, where each element `arr[i]` represents the height of a vertical line.
// Find the maximum amount of water that can be contained between any two lines, together with the x-axis.

// Approach:
// The problem can be solved efficiently using a two-pointer approach. We initialize two pointers, one at the beginning of the array (`left`) and one at the end (`right`). The area formed by the lines at these two pointers is calculated as `min(arr[left], arr[right]) * (right - left)`.
// We maintain a variable `maxArea` to store the maximum area found so far. We update `maxArea` with the maximum of its current value and the calculated area.
// The key insight is to move the pointer that is pointing to the shorter line. If we move the pointer of the taller line, the height of the container will be limited by the shorter line, and the width will decrease, thus we can't get a larger area. By moving the shorter line's pointer inward, we might find a new line that is taller, potentially leading to a larger area. We continue this process until the pointers meet.

// Time Complexity:
// The time complexity of this solution is O(n), where n is the number of elements in the array. This is because the two pointers traverse the array from opposite ends, and each pointer moves at most n times.

// Space Complexity:
// The space complexity is O(1) as we are only using a few variables to store the pointers and the maximum area, without using any additional data structures.

class Solution {
    public long maxArea(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        long maxArea = 0;

        while (left < right) {
            int currentHeight = Math.min(arr[left], arr[right]);
            int currentWidth = right - left;
            long currentArea = (long) currentHeight * currentWidth;
            maxArea = Math.max(maxArea, currentArea);

            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}