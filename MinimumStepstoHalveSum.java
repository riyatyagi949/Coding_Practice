/**
 * Problem Statement: Minimum Steps to Halve Sum
 * ---------------------------------------------
 * Given an array arr[], find the minimum number of operations required to make 
 * the sum of its elements less than or equal to half of the original sum. 
 * In one operation, you may replace any element with half of its value 
 * (using floating-point precision).
 *
 * Constraints:
 * 1 <= arr.size() <= 10^5
 * 0 <= arr[i] <= 10^4
 *//**
     * Optimal Solution: Greedy Approach using a Max Heap (PriorityQueue)
     * ------------------------------------------------------------------
     * The problem asks for the minimum number of operations to maximize the reduction
     * of the total sum at each step. The reduction gained by halving an element 'x' 
     * is 'x - x/2 = x/2'. To maximize this reduction, we must **always choose the 
     * largest available element** to halve.
     *
     * This greedy strategy is implemented efficiently using a Max Heap (PriorityQueue)
     * to ensure constant-time access to the largest element.
     *
     * * Algorithm Steps:
     * 1. Calculate the initial `totalSum` and the `targetSum` (`totalSum / 2.0`).
     * 2. Initialize a Max Heap (PriorityQueue in Java with Collections.reverseOrder()) 
     * with all array elements (as Doubles to handle precision).
     * 3. Loop while the `currentSum` is strictly greater than `targetSum`:
     * a. Increment `operations`.
     * b. Extract the largest element, `maxVal`, from the heap.
     * c. Calculate the new half value, `halfVal = maxVal / 2.0`.
     * d. Update `currentSum` by subtracting the reduction (`maxVal - halfVal`).
     * e. Insert `halfVal` back into the heap.
     * 4. Return the total number of operations.
     */
  /**
 * Time Complexity Analysis:
 * -------------------------
 * O(N log N + M log N), where N is the length of 'arr' and M is the total number of operations.
 * - Initial population of the Max Heap: O(N log N).
 * - The `while` loop runs M times (M = total operations).
 * - In each iteration, we perform one `poll()` and one `offer()`, both of which take O(log N) time
 * due to the heap property maintenance.
 * - Since the constraints imply M is reasonably bounded (even if M could theoretically be large, 
 * it's practical runtime is fast), the complexity is efficient.
 * - Overall, the complexity is dominated by the initial heap build and the total cost of M heap operations: O((N + M) log N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the length of 'arr'.
 * - This space is required to store all elements in the Max Heap (PriorityQueue).
 */ 
// Optimal Solution in Java - 