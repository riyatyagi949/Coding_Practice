/**
 * PROBLEM STATEMENT: Max Sum Subarray of size K
 * --------------------------------------------------------------------------------
 * Given an array of integers arr[] and a number k, return the maximum sum of any 
 * contiguous subarray of size k.
 * * Examples:
 * Input: arr[] = [100, 200, 300, 400], k = 2
 * Output: 700
 * Explanation: The subarray [300, 400] has the maximum sum of 700.
 * * Constraints:
 * 1 <= arr.size() <= 10^6
 * 1 <= arr[i] <= 10^6
 * 1 <= k <= arr.size()
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SLIDING WINDOW TECHNIQUE
 * --------------------------------------------------------------------------------
 * Instead of recalculating the sum for every possible subarray (which would be O(N*K)),
 * we use the Sliding Window pattern to maintain a running sum of the current window.
 * * Logic:
 * 1. Calculate the sum of the first 'k' elements. This is our initial "window".
 * 2. Slide the window one step at a time from index 'k' to 'n-1'.
 * 3. To update the sum efficiently:
 * - Add the current element (entering the window from the right).
 * - Subtract the element that is no longer in the window (exiting from the left).
 * - New Sum = Old Sum + arr[i] - arr[i - k].
 * 4. Keep track of the maximum sum encountered during the sliding process.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We traverse the array exactly once. The initial window takes O(K) and the 
 * sliding takes O(N-K), resulting in linear time.
 * * Space Complexity: O(1)
 * - We only use a few variables (maxSum, windowSum) to store the result.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-
class Solution{
    public int maxSubarraySum(int[] arr, int  k){
        int n = arr.length;
        
        int windowSum =0;
        for(int i = 0; i <  k; i++){
            windowSum += arr[i];
        }
        int maxSum = windowSum;
        
        for( int i =  k ; i < n; i++){
            windowSum = windowSum + arr[ i] - arr[i- k];
            maxSum = Math.max(maxSum , windowSum);
        }
        return maxSum;
    }
}

