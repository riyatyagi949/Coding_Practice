/**
 * PROBLEM STATEMENT: Count X in Range of a Sorted Array
 * ---------------------------------------------------------------------------------------
 * You are given a sorted array arr[] and a 2D array queries[][], where queries[i] represents 
 * a query in the form [l, r, x]. For each query, count how many times the number x appears 
 * in the subarray arr[l...r] (inclusive).
 * * Example:
 * Input: arr[] = [1, 2, 2, 4, 5, 5, 5, 8], queries[][] = [[0, 7, 5], [1, 2, 2]]
 * Output: [3, 2]
 * * Explanation:
 * Query [0, 7, 5] -> In range [0...7], 5 appears at indices 4, 5, and 6. Total count = 3.
 * Query [1, 2, 2] -> In range [1...2], 2 appears at indices 1 and 2. Total count = 2.
 * * Constraints:
 * 1 <= arr.size(), queries.size() <= 10^5
 * 1 <= arr[i], x <= 10^6
 * 0 <= l <= r < arr.size()
 * ---------------------------------------------------------------------------------------
 */

/**
 * OPTIMAL SOLUTION LOGIC:
 * ---------------------------------------------------------------------------------------
 * Since the input array is already sorted, the occurrences of any number 'x' will be contiguous. 
 * To find the count of 'x' within a specific index range [l, r], we use Binary Search.
 * * 1. Find the first occurrence of 'x' within the query boundaries [l, r].
 * 2. Find the last occurrence of 'x' within the query boundaries [l, r].
 * 3. The count is calculated as: (lastIndex - firstIndex + 1).
 * 4. If 'x' is not found, the count is 0.
 * * Using Binary Search ensures that each query is answered in logarithmic time, 
 * making the total time complexity efficient for large inputs.
 * * 
 * ---------------------------------------------------------------------------------------
 */

import java.util.*;

class Solution {
    public ArrayList<Integer> countXInRange(int[] arr, int[][] queries) {
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int x = q[2];
            
            int first = findFirst(arr, l, r, x);
                if (first == -1) {
                result.add(0);
                continue;
            }
            int last = findLast(arr, first, r, x);
            result.add(last - first + 1);
        }
       return result;
    }
    private int findFirst(int[] arr, int l, int r, int x) {
        int res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x) {
                res = mid;
                r = mid - 1;
            } 
            else if (arr[mid] < x) {
                l = mid + 1;
            } 
            else {
                r = mid - 1;
            }
        }
        return res;
    }
  private int findLast(int[] arr, int l, int r, int x) {
        int res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x) {
                res = mid;
                l = mid + 1;
            } 
            else if (arr[mid] < x) {
                l = mid + 1;
            } 
            else {
                r = mid - 1;
            }
        }
        return res;
    }
}

/**
 * COMPLEXITY ANALYSIS:
 * ---------------------------------------------------------------------------------------
 * Time Complexity: O(Q * log N)
 * - N is the size of the array, Q is the number of queries.
 * - Each query performs two Binary Searches, each taking O(log N).
 * - Total time: O(Q * log N). With N, Q = 10^5, this is approx 1.7 * 10^6 operations,
 * which comfortably fits within the 1-second time limit.
 * * Space Complexity: O(Q)
 * - We need O(Q) space to store and return the result list.
 * - Auxiliary space used by the algorithm is O(1) as we perform Binary Search iteratively.
 * ---------------------------------------------------------------------------------------
 */
