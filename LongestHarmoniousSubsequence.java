/*
Problem Statement:
We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.
Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.

Example 1:
Input: nums = [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].

Example 2:
Input: nums = [1,2,3,4]
Output: 2
Explanation: The longest harmonious subsequences are [1,2], [2,3], and [3,4], all of which have a length of 2.

Example 3:
Input: nums = [1,1,1,1]
Output: 0
Explanation: No harmonic subsequence exists.

Constraints:
1 <= nums.length <= 2 * 10^4
-10^9 <= nums[i] <= 10^9

Approach:
A harmonious subsequence must contain only two distinct numbers, say 'x' and 'x+1'.
To find the longest harmonious subsequence, we need to count the occurrences of each number in the input array.
A HashMap can be used to store the frequency of each number.
After populating the HashMap, we iterate through its entries. For each number `num` and its count `count` in the map, we check if `num + 1` also exists in the map.
If `num + 1` exists, then `num` and `num + 1` can form a harmonious subsequence. The length of this subsequence would be `count` (frequency of `num`) + `map.get(num + 1)` (frequency of `num + 1`).
We keep track of the maximum length found so far.
If no such pair (num, num+1) is found, the result is 0.

Time Complexity:
O(N) - where N is the length of the input array `nums`.
- Populating the HashMap takes O(N) time as we iterate through the array once.
- Iterating through the HashMap entries takes O(M) time, where M is the number of distinct elements in `nums`. In the worst case, M can be N.
Therefore, the overall time complexity is O(N).

Space Complexity:
O(M) - where M is the number of distinct elements in `nums`.
In the worst case, all elements are distinct, so M can be N. Thus, the space complexity can be O(N) for storing the frequencies in the HashMap.
*/
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
         int maxLength = 0;
        for (int num : freqMap.keySet()) {
            if (freqMap.containsKey(num + 1)) {
                maxLength = Math.max(maxLength, freqMap.get(num) + freqMap.get(num + 1));
            }
        }
        return maxLength;
    }
}