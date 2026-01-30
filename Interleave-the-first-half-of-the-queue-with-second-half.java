/**
 * PROBLEM STATEMENT: Interleave the First Half of the Queue with Second Half
 * --------------------------------------------------------------------------------
 * Given a queue 'q' of even size, rearrange the queue by interleaving its first 
 * half with the second half.
 * * Interleaving means alternating elements from the two halves while preserving 
 * their relative order.
 * * Example:
 * Input: q = [2, 4, 3, 1]
 * Output: [2, 3, 4, 1]
 * Explanation: 
 * - First half: [2, 4]
 * - Second half: [3, 1]
 * - Interleaved: [2, 3, 4, 1]
 * * Constraints:
 * - 1 <= queue.size() <= 10^3
 * - 1 <= queue[i] <= 10^5
 * - Queue size is always even.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: AUXILIARY QUEUE APPROACH
 * --------------------------------------------------------------------------------
 * 1. Identify the size of the first half (n / 2).
 * 2. Dequeue the first half of the elements from the original queue and store 
 * them in an auxiliary queue (let's call it 'firstHalf').
 * 3. Now, the original queue 'q' contains only the second half of the elements.
 * 4. While the 'firstHalf' queue is not empty:
 * - Dequeue an element from 'firstHalf' and add it back to 'q'.
 * - Dequeue the current front element from 'q' (which belongs to the second half) 
 * and add it back to 'q'.
 * 5. This process ensures the elements are interleaved in the correct order.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We iterate through half the elements to move them to the auxiliary queue: O(N/2).
 * - We iterate through the remaining elements once to interleave them: O(N).
 * - Total time complexity is linear relative to the number of elements.
 * * Space Complexity: O(N)
 * - We use an auxiliary queue to store half of the elements, which takes O(N/2) space.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -

import java.util.Queue;
import java.util.LinkedList;

class Solution {
   public void rearrangeQueue(Queue<Integer> q) {
        int n = q.size();
        int half = n / 2;
        
        Queue<Integer> firstHalf = new LinkedList<>();
        
        for (int i = 0; i < half; i++) {
            firstHalf.add(q.poll());
        }
        while (!firstHalf.isEmpty()) {
            q.add(firstHalf.poll());
            q.add(q.poll());
        }
    }
}
