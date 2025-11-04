/**
 * Problem Statement: Find X-Sum of All K-Long Subarrays I
 * --------------------------------------------------------
 * Given an array 'nums' of n integers and two integers 'k' and 'x'.
 * The **x-sum** of an array is calculated by:
 * 1. Counting the occurrences of all elements.
 * 2. Keeping only the occurrences of the **top x most frequent elements**. 
 * Tie-breaker: If two elements have the same frequency, the element with the 
 * **bigger value** is considered more frequent.
 * 3. Calculating the sum of the resulting array.
 * Note: If an array has less than x distinct elements, its x-sum is the sum of the array.
 *
 * Return an array 'answer' where answer[i] is the x-sum of the subarray nums[i..i + k - 1].
 *
 * Constraints:
 * 1 <= n <= 50 (Small constraint is key to the optimal approach)
 * 1 <= x <= k <= n
 *//**
     * Data structure to represent an element's frequency and value.
     * Comparator prioritizes:
     * 1. Higher frequency (for top X)
     * 2. Higher value (tie-breaker for frequency)
     *//**
     * Optimal Solution: Brute Force with Custom Sorting (Due to Small Constraints)
     * -----------------------------------------------------------------------------
     * Given the extreme constraint on N (N <= 50), the complexity of processing each 
     * subarray is not a major bottleneck. A standard Sliding Window approach for 
     * frequency counting would typically be used for larger N, but the custom logic 
     * for selecting the top X elements makes updating the sliding window complex.
     *
     * * Strategy: Process each subarray independently (Brute Force).
     * 1. Iterate through all possible starting indices `i` from 0 to `n - k`.
     * 2. For each subarray `nums[i...i + k - 1]`:
     * a. Count Frequencies: Use a HashMap to count the frequency of each unique element.
     * b. Convert to Stats List: Convert the HashMap entries into a List of `ElementStats` objects.
     * c. Sort: Sort this list using the custom `XSUM_COMPARATOR` (Frequency DESC, then Value DESC).
     * d. Calculate X-Sum: Sum up the total occurrences of the first `x` elements in the sorted list.
     * 3. Store the result and repeat.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O((N - K + 1) * (K + D log D)), where N is nums.length, K is subarray length, and 
 * D is the number of distinct elements in the subarray (D <= K).
 * - There are (N - K + 1) subarrays.
 * - For each subarray:
 * - Frequency counting takes O(K) time.
 * - Sorting the distinct elements list takes O(D log D) time (since D <= K).
 * - Total Complexity: O(N * (K + K log K)).
 * - Given N <= 50 and K <= 50, the complexity is roughly O(50 * (50 + 50 * log 50)) 
 * which is very small (approx 50 * 350 = 17,500 operations). This confirms the 
 * brute-force approach is efficient for these constraints.
 * * Space Complexity Analysis:
 * --------------------------
 * O(K)
 * - We use a HashMap and a List to store frequencies and statistics for a single subarray 
 * of length K. The number of distinct elements is at most K.
 */
// Optimal Solution in Java -
