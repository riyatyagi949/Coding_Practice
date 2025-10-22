/**
 * Problem Statement: Nearly Sorted Array
 * -------------------------------------
 * Given an array arr[] where each element is at most 'k' positions away from its 
 * correct position in the sorted order.
 * The task is to restore the sorted order of arr[] by rearranging the elements in place.
 * Note: Standard sort() methods are not allowed.
 *
 * Example:
 * Input: arr[] = [2, 3, 1, 4], k = 2
 * Output: [1, 2, 3, 4]
 *
 * Constraints:
 * 1 <= arr.size() <= 10^6
 * 0 <= k < arr.size()
 * 1 <= arr[i] <= 10^6
*//**
     * Optimal Solution: Using a Min-Heap (Priority Queue)
     * ----------------------------------------------------
     * The nearly sorted property (k-sorted) implies that the smallest element in the 
     * array must be present within the first k+1 elements.
     * * * The Greedy Strategy:
     * 1. Initialize a **Min-Heap** (PriorityQueue) to store the first k+1 elements of the array.
     * 2. Iterate through the array from the beginning:
     * a. At each step 'i', the smallest element in the heap must be the correct 
     * element for the current sorted position (index 'i').
     * b. Extract the minimum element from the heap and place it into the current 
     * position of the result array.
     * c. Load the next element from the input array (at index i + k + 1) into the heap, 
     * if it exists, to maintain the heap size of at most k+1.
     * 3. After the main loop, empty the remaining elements from the heap and append them 
     * to the result array.
     * * * Why this works: When we are filling the element at index 'i', all elements that 
     * * could possibly belong at or before index 'i' (i.e., those initially at indices 
     * * up to i + k) are already in the heap or have been processed. The smallest 
     * * remaining element must be the correct one for index 'i'.
     */
   /**
 * Time Complexity Analysis:
 * -------------------------
 * O(N log K), where N is the number of elements in the array and K is the given constant.
 * - The heap size is maintained at most K+1.
 * - We perform N operations (N additions and N removals) on the heap.
 * - The time complexity for adding or removing an element from a heap of size K is O(log K).
 * - Total time complexity: N * O(log K) = O(N log K).
 * * Space Complexity Analysis:
 * --------------------------
 * O(K), where K is the given constant.
 * - This space is used by the PriorityQueue (minHeap), which holds at most K+1 elements.
 * - The result ArrayList also requires O(N) space, but typically, only the auxiliary 
 * space (the heap) is considered for this analysis if the result is required to be
 * returned as a new array/list. Since we are asked to "restore the sorted order," 
 * if an in-place modification of the original array was required (often the case 
 * when "in place" is used), the auxiliary space remains O(K).
 */ 
// Optimal Solution in Java -
import java.util.*;