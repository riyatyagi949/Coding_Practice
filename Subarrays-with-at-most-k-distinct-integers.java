/**
 * PROBLEM STATEMENT: Subarrays With At Most K Distinct Integers
 * --------------------------------------------------------------------------------
 * Given an array 'arr[]' of positive integers and an integer 'k', find the total 
 * number of subarrays where the count of distinct integers is at most k.
 * * A subarray is defined as a contiguous part of an array.
 * * Example:
 * Input: arr[] = [1, 2, 2, 3], k = 2
 * Output: 9
 * Explanation: The valid subarrays are [1], [2], [2], [3], [1,2], [2,2], [2,3], [1,2,2], [2,2,3].
 * * Constraints:
 * 1 <= arr.size() <= 2 * 10^4
 * 1 <= k <= 2 * 10^4
 * 1 <= arr[i] <= 10^9
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SLIDING WINDOW (TWO POINTERS)
 * --------------------------------------------------------------------------------
 * This problem is solved efficiently using a sliding window approach. 
 * * Key Logic:
 * 1. For a fixed right boundary 'r', if the window [l, r] contains at most 'k' 
 * distinct elements, then all subarrays ending at 'r' and starting at any index 
 * from 'l' to 'r' are also valid.
 * 2. The number of such valid subarrays ending exactly at 'r' is (r - l + 1).
 * 3. We maintain a frequency map (HashMap) to track the count of distinct integers 
 * currently in the window.
 * 4. If the number of distinct elements (map.size()) exceeds 'k', we increment the 
 * left pointer 'l' and update the frequency map until the condition is restored.
 * * Why (r - l + 1)?
 * If [1, 2, 2] is valid (l=0, r=2), the subarrays ending at index 2 are:
 * [2] (length 1)
 * [2, 2] (length 2)
 * [1, 2, 2] (length 3)
 * Total = 2 - 0 + 1 = 3.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n)
 * - The right pointer 'r' traverses the array once.
 * - The left pointer 'l' also traverses the array at most once.
 * - HashMap operations (put, get, remove) are O(1) on average.
 * * Space Complexity: O(k)
 * - In the worst case, the HashMap will store 'k + 1' distinct integers before 
 * shrinking the window.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-

import java.util.HashMap;

class Solution {
    public int countAtMostK(int arr[], int k) {
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int l = 0;
        int ans = 0;
        
        for (int r = 0; r < n; r++) {
            map.put(arr[r], map.getOrDefault(arr[r], 0) + 1);
            
            while (map.size() > k) {
                map.put(arr[l], map.get(arr[l]) - 1);
                if (map.get(arr[l]) == 0)
                    map.remove(arr[l]);
                l++;
            }
            ans += (r - l + 1);
        }
        return ans;
    }
}


