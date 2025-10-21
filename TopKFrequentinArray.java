/**
 * Problem Statement: Top K Frequent in Array
 * -------------------------------------------
 * Given a non-empty integer array arr[]. The task is to find and return the 
 * top k elements which have the highest frequency in the array.
 * * Note: If two numbers have the same frequency, the larger number should 
 * be given the higher priority (i.e., should come first in the output).
 * * Examples:
 * 1. Input: arr[] = [3, 1, 4, 4, 5, 2, 6, 1], k = 2
 * Output: [4, 1] 
 * (Freq: 4->2, 1->2. Since 4 > 1, 4 comes before 1.)
 * 2. Input: arr[] = [7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9], k = 4
 * Output: [5, 11, 7, 10]
 * (Freq: 5->3, 11->2, 7->2, 10->1. Tie-breaking: 11 > 7.)
 *
 * Constraints:
 * 1 <= arr.size() <= 10^5
 * 1 <= arr[i] <= 10^5
 * 1 <= k <= no. of distinct elements
 *//**
     * Optimal Solution: Hashing followed by a Min-Heap (PriorityQueue)
     * ---------------------------------------------------------------
     * The most efficient solution involves two main steps:
     * 1. **Frequency Counting (Hashing):** Use a HashMap to count the occurrences 
     * (frequency) of every element in the array. This takes O(N) time.
     * 2. **Top K Selection (Min-Heap):** Use a Min-Heap (PriorityQueue) of size K 
     * to maintain the top K most frequent elements encountered so far.
     * * * Custom Comparator for PriorityQueue:
     * The core logic lies in the comparator used for the Min-Heap. The heap must 
     * maintain the K largest *frequency* elements.
     * The priority logic is:
     * - Primary Sort: Prioritize **lower frequency** elements (min-heap property). 
     * If `freqA < freqB`, A has higher priority to be *removed* (since it's worse).
     * - Secondary Tie-breaker: If frequencies are equal, prioritize the **smaller value** * element to be *removed* (since the requirement is to keep the larger value).
     * If `freqA == freqB` and `valA < valB`, A has higher priority to be *removed*.
     * * * Steps for Heap operation:
     * - Iterate through the map entries (element, frequency).
     * - Add each entry to the PriorityQueue.
     * - If the queue size exceeds K, remove the top element (the one with the lowest priority 
     * based on the custom comparator), which ensures only the Top K remain.
     * 3. **Final Output:** Extract the elements from the remaining heap. Since the problem 
     * requires the final output to be sorted by priority (highest freq first, then largest value), 
     * the elements must be pulled out and sorted again, or inserted into a result list 
     * and then sorted, typically using a final sort step.
     */
    /**
 * Time Complexity Analysis:
 * -------------------------
 * O(N + D log K), where N is the length of the array and D is the number of distinct elements.
 * * 1. Frequency Counting: O(N) to iterate through the array.
 * * 2. Heap Operations: O(D) total distinct elements. Each insertion/removal in the heap takes O(log K). 
 * Total time is O(D log K).
 * * 3. Final Sort: The final sorting of the K elements takes O(K log K). Since K <= D, this is less than O(D log K).
 * * Overall Complexity: O(N + D log K). Since D <= N, this is often stated as O(N log K).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(D + K), where D is the number of distinct elements.
 * * 1. HashMap: O(D) space to store the frequencies of all distinct elements.
 * * 2. PriorityQueue: O(K) space to store the top K pairs.
 * * 3. Final List/Array: O(K) space for the result storage.
 * * Overall Complexity: O(D). Since D <= N, this is often stated as O(N).
 */
// Optimal Solution in Java - 