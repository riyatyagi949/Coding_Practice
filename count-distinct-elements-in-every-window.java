/**
 * PROBLEM STATEMENT: Count Distinct Elements in Every Window
 * --------------------------------------------------------------------------------
 * Given an integer array 'arr[]' and a number 'k', find the count of distinct 
 * elements in every window of size 'k' in the array.
 * * Examples:
 * Input: arr[] = [1, 2, 1, 3, 4, 2, 3], k = 4
 * Output: [3, 4, 4, 3]
 * * Input: arr[] = [4, 1, 1], k = 2
 * Output: [2, 1]
 * * Constraints:
 * - 1 <= k <= arr.size() <= 10^5
 * - 1 <= arr[i] <= 10^5
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SLIDING WINDOW + HASHMAP
 * --------------------------------------------------------------------------------
 * We use a sliding window of size 'k' and a HashMap to store the frequency of 
 * elements currently within that window.
 * * Logic:
 * 1. Initialize an empty HashMap to store frequencies.
 * 2. Process the first window (indices 0 to k-1):
 * - Add elements to the map and update their counts.
 * - The size of the map after this step represents the distinct count for 
 * the first window.
 * 3. Slide the window one element at a time from index 'k' to 'n-1':
 * - Remove the outgoing element (arr[i - k]):
 * - Decrement its count in the map.
 * - If the count becomes 0, remove the key from the map entirely.
 * - Add the incoming element (arr[i]):
 * - Increment its count in the map.
 * - The size of the map at each step is the distinct count for that window.
 * 4. Return the list of distinct counts.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n)
 * - We traverse the array once. HashMap operations (put, get, remove) take 
 * O(1) on average.
 * * Space Complexity: O(k) or O(n)
 * - The HashMap stores at most 'k' elements at any given time.
 * - The result list stores (n - k + 1) integers.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -

import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    ArrayList<Integer> countDistinct(int arr[], int k) {
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < k; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        result.add(map.size());

        for (int i = k; i < arr.length; i++)
        {
            int outgoing = arr[i - k];
            map.put(outgoing, map.get(outgoing) - 1);
            
            if (map.get(outgoing) == 0) 
            {
             map.remove(outgoing);
            }
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            result.add(map.size());
        }
        return result;
    }
}


