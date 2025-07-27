// Problem Statement:
// You are given a 0-indexed integer array nums. An index i is part of a hill in nums if the closest non-equal neighbors of i are smaller than nums[i]. Similarly, an index i is part of a valley in nums if the closest non-equal neighbors of i are larger than nums[i]. Adjacent indices i and j are part of the same hill or valley if nums[i] == nums[j].
// Note that for an index to be part of a hill or valley, it must have a non-equal neighbor on both the left and right of the index.
// Return the number of hills and valleys in nums.
//
// Example 1:
// Input: nums = [2,4,1,1,6,5]
// Output: 3
//
// Example 2:
// Input: nums = [6,6,5,5,4,1]
// Output: 0
//
// Constraints:
// 3 <= nums.length <= 100
// 1 <= nums[i] <= 100
//
// Approach:
// The problem asks us to count the number of hills and valleys in an array. A hill or valley is defined by its closest non-equal neighbors.
// First, we can simplify the array by removing consecutive duplicate elements. This is because consecutive equal elements are considered part of the same hill or valley. For example, in [2,4,1,1,6,5], the two '1's at indices 2 and 3 are part of the same valley. By compressing the array, we can treat them as a single point.
// After compressing the array, we can iterate from the second element to the second-to-last element. An element at index `i` (in the compressed array) is a hill if its neighbors `compressedNums[i-1]` and `compressedNums[i+1]` are both smaller than `compressedNums[i]`. Similarly, it's a valley if both neighbors are larger. We just need to check these conditions and increment a counter. The first and last elements of the compressed array cannot be a hill or valley as they lack one neighbor.
//
// Time Complexity: O(N), where N is the number of elements in the input array `nums`. We iterate through the array once to compress it, and then once more to count hills and valleys.
// Space Complexity: O(N) in the worst case, to store the compressed array. If there are no consecutive duplicate elements, the compressed array will have the same size as the input array.
//
// Optimal Solution:
class Solution {
    public int countHillAndValley(int[] nums) {
        List<Integer> compressedNums = new ArrayList<>();
        if (nums.length > 0) {
            compressedNums.add(nums[0]);
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                compressedNums.add(nums[i]);
            }
        }
        
        if (compressedNums.size() < 3) {
            return 0;
        }
        
        int count = 0;
        for (int i = 1; i < compressedNums.size() - 1; i++) {
            if (compressedNums.get(i) > compressedNums.get(i - 1) && compressedNums.get(i) > compressedNums.get(i + 1)) {
                count++;
            } else if (compressedNums.get(i) < compressedNums.get(i - 1) && compressedNums.get(i) < compressedNums.get(i + 1)) {
                count++;
            }
        }
        
        return count;
    }
}