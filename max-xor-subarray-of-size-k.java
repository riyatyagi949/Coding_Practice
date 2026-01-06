/**
 * PROBLEM STATEMENT: Max XOR Subarray of size K
 * --------------------------------------------------------------------------------
 * Given an array of integers arr[] and a number k, return the maximum XOR value 
 * of any contiguous subarray of size k.
 * * * Examples:
 * Input: arr[] = [2, 5, 8, 1, 1, 3], k = 3
 * Output: 15
 * Explanation: Subarray [2, 5, 8] -> 2 ^ 5 ^ 8 = 15, which is the maximum.
 * * Input: arr[] = [1, 2, 4, 5, 6], k = 2
 * Output: 6
 * Explanation: Subarray [2, 4] -> 2 ^ 4 = 6, which is the maximum.
 * * * Constraints:
 * 1 <= arr.size() <= 10^6
 * 0 <= arr[i] <= 10^6
 * 1 <= k <= arr.size()
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SLIDING WINDOW (XOR PROPERTIES)
 * --------------------------------------------------------------------------------
 * Similar to the "Sliding Window Sum" technique, we can maintain the XOR sum 
 * of a window of size k. 
 * * * Logic:
 * 1. Calculate the XOR sum of the first 'k' elements.
 * 2. Slide the window from left to right.
 * 3. XOR has a unique property: A ^ A = 0. 
 * To remove an element from the XOR sum, we simply XOR it again.
 * New XOR = Old XOR ^ (Element leaving left) ^ (Element entering right).
 * 4. Update the maximum XOR found during the process.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We traverse the array once to calculate the initial window and once to slide it.
 * * Space Complexity: O(1)
 * - Only a few variables are used to track current and maximum XOR values.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -
class Solution {
    public int maxSubarrayXOR(int[] arr, int k) {
        int n = arr.length;
        
        int currXor = 0;
        
        for (int i = 0; i < k; i++) {
            currXor ^= arr[i];
        }
        int maxXor = currXor;
        
        for (int i = k; i < n; i++) {
            // Remove
            currXor ^= arr[i - k];
            // Add
            currXor ^= arr[i];
            
            maxXor = Math.max(maxXor, currXor);
        }
        return maxXor;
    }
}




