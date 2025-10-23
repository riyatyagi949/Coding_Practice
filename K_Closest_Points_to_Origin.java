/**
 * Problem Statement: K Closest Points to Origin
 * ---------------------------------------------
 * Given an integer 'k' and an array of points 'points[][]', where each point 
 * is represented as [xi, yi] on the X-Y plane.
 * The task is to return the 'k' closest points to the origin (0, 0).
 *
 * The distance is the Euclidean distance: distance = sqrt( (x2 - x1)^2 + (y2 - y1)^2 ).
 * For comparison, we can use the squared distance to avoid expensive square root operations, 
 * as sqrt(A) < sqrt(B) if and only if A < B.
 * Squared distance from origin (0, 0) for point (x, y) is: x^2 + y^2.
 *
 * Example:
 * Input: k = 2, points = [[1, 3], [-2, 2], [5, 8], [0, 1]]
 * Output: [[-2, 2], [0, 1]]
 *//**
     * Optimal Solution: Max-Heap (Priority Queue) of Size K
     * -----------------------------------------------------
     * This approach is highly efficient for finding the K smallest elements in an unsorted array.
     * * 1. **Data Structure:** Use a **Max-Heap** (Priority Queue with a custom Comparator for max-heap behavior). 
     * The heap will store the $K$ closest points found so far.
     * 2. **Heap Order:** The heap is ordered by the *squared Euclidean distance* from the origin, 
     * with the **largest distance** at the top (Max-Heap property).
     * 3. **Processing:** Iterate through each point:
     * a. Calculate its squared distance.
     * b. If the heap size is less than $K$, add the point to the heap.
     * c. If the heap is full (size == $K$) and the current point's distance is **smaller** * than the distance of the point at the top of the heap (the farthest of the current $K$), 
     * remove the point at the top (farthest) and insert the current point.
     * 4. **Result:** After processing all points, the $K$ points remaining in the heap are the 
     * $K$ closest points to the origin.
     *
     * Using squared distance $x^2 + y^2$ is crucial for performance.
     */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N log K), where N is the number of points and K is the number of closest points requested.
 * - Initializing the heap takes O(1).
 * - For each of the N points, we perform:
 * - Distance calculation: O(1).
 * - Heap insertion/deletion (poll + offer): O(log K), since the heap size is limited to K.
 * - Total complexity for the loop is N * O(log K) = O(N log K).
 * - Extracting the final K elements takes O(K log K).
 * - Overall complexity: O(N log K).
 *
 * * Alternative (Quickselect): O(N) average, O(N^2) worst-case.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(K).
 * - The space is used to store the Max-Heap, which holds at most K elements (points).
 */
// Optimal Solution in Java -
import java.util.*;