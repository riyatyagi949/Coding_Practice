/**
 * PROBLEM STATEMENT: K Sized Subarray Maximum
 * --------------------------------------------------------------------------------
 * Given an array 'arr' of integers and an integer 'k', find the maximum element 
 * for each contiguous subarray of size k.
 *
 * Examples:
 * Input: arr[] = [1, 2, 3, 1, 4, 5, 2, 3, 6], k = 3
 * Output: [3, 3, 4, 5, 5, 5, 6]
 *
 * Input: arr[] = [5, 1, 3, 4, 2], k = 1
 * Output: [5, 1, 3, 4, 2]
 *
 * Constraints:
 * - 1 <= arr.size() <= 10^6
 * - 1 <= k <= arr.size()
 * - 0 <= arr[i] <= 10^9
 * --------------------------------------------------------------------------------
 * * OPTIMAL SOLUTION: MONOTONIC DEQUE (Sliding Window)
 * --------------------------------------------------------------------------------
 * To solve this in O(n) time, we use a Deque (Double Ended Queue) to store indices
 * of array elements. We maintain the Deque in a "Monotonic Decreasing" order.
 *
 * * Logic:
 * 1. Maintain a window of indices [i-k+1, i].
 * 2. Before adding index 'i', remove indices from the back of the Deque if the 
 * corresponding element arr[index] is smaller than arr[i]. 
 * (Why? Because arr[i] is newer and larger, so the smaller previous elements 
 * can never be the maximum for this or any future window).
 * 3. Remove the index from the front if it is "out of bounds" for the current window 
 * (i.e., index <= i - k).
 * 4. The element at the front of the Deque will always be the maximum for the 
 * current window of size k.
 * --------------------------------------------------------------------------------
 * * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n)
 * - Each element is added to the Deque exactly once and removed at most once.
 * - Even though there are nested while loops, the amortized cost is linear.
 *
 * Space Complexity: O(k)
 * - The Deque stores at most k indices at any given time.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
              while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && arr[dq.peekLast()] < arr[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);

            if (i >= k - 1) 
            {
              result.add(arr[dq.peekFirst()]);
            }
        }
        return result;
    }
}


