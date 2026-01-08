/**
 * PROBLEM STATEMENT: Count Subarrays with k Odds
 * --------------------------------------------------------------------------------
 * Given an array of positive integers 'arr[]' and an integer 'k', count the 
 * number of subarrays that contain exactly 'k' odd numbers.
 * * Examples:
 * Input: arr[] = [2, 5, 6, 9], k = 2
 * Output: 2
 * Explanation: [2, 5, 6, 9] and [5, 6, 9] both contain exactly two odds (5 and 9).
 * * Constraints:
 * - 1 <= k <= arr.size() <= 10^5
 * - 1 <= arr[i] <= 10^9
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SLIDING WINDOW (AtMost(k) - AtMost(k-1))
 * --------------------------------------------------------------------------------
 * Finding "exactly k" can be difficult with a standard sliding window because 
 * the condition isn't strictly monotonic. Instead, we use a helper function 
 * 'countAtMost(k)' which counts all subarrays containing k or fewer odd numbers.
 * * Logic:
 * 1. exactly(k) = atMost(k) - atMost(k-1)
 * 2. In countAtMost(k):
 * - Use two pointers (left, right) to define a window.
 * - Expand 'right' and track the number of odd integers.
 * - If odds > k, shrink the window from the 'left' until odds <= k.
 * - For a fixed 'right', the number of valid subarrays ending at 'right' 
 * is (right - left + 1).
 * 3. Sum these counts to get the result for atMost(k).
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n)
 * - We call countAtMost twice. Each call traverses the array with two pointers,
 * where both pointers move at most 'n' times.
 * * Space Complexity: O(1)
 * - We only use a few integer variables for counting and pointers.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -
class Solution {
    public int countSubarrays(int[] arr, int k) {
        return countAtMost(arr, k) - countAtMost(arr, k - 1);
    }
    private int countAtMost(int[] arr, int k) {
        int left = 0;
        int oddCount = 0;
        int result = 0;

        for (int right = 0; right < arr.length; right++) {
            if (arr[right] % 2 == 1) 
            {
             oddCount++;
            }
            while (oddCount > k) {
                if (arr[left] % 2 == 1) {
                    oddCount--;
                }
                left++;
            }
            result += (right - left + 1);
        }
        return result;
    }
}


