/**
 * PROBLEM STATEMENT: Minimum Number of Workers
 * --------------------------------------------------------------------------------
 * You are given an array arr[], where arr[i] denotes the range of working hours 
 * a person at position i can cover.
 * - If arr[i] != -1, the person at index i can cover interval [i - arr[i], i + arr[i]].
 * - If arr[i] = -1, the person is unavailable.
 * - Find the minimum number of people required to cover the range [0, n-1].
 * - If not possible, return -1.
 *
 * Example:
 * Input: arr[] = [1, 2, 1, 0]
 * Output: 1
 * Explanation: Index 1 covers [1-2, 1+2] = [-1, 3]. Clamped to [0, 3], which covers 
 * the entire day (n=4, range 0-3).
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: GREEDY INTERVAL COVERAGE
 * --------------------------------------------------------------------------------
 * 1. Transform input: For each position 'i', if arr[i] != -1, we have an 
 * interval [max(0, i-arr[i]), min(n-1, i+arr[i])].
 * 2. Pre-process Intervals: Instead of sorting all possible intervals (which is O(N log N)),
 * we can use a "Jump Game" style optimization. For every possible starting point 
 * 'L', we only care about the maximum 'R' we can reach.
 * Create an array `maxReachAt[L]` where L is the start of an interval.
 * 3. Greedy Selection:
 * - Start at `currentEnd = 0`.
 * - While `currentEnd` is less than `n`:
 * - Look at all intervals starting at or before `currentEnd`.
 * - Pick the one that extends the farthest.
 * - If no interval can reach or pass `currentEnd`, coverage is impossible.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - Building the `maxReachAt` array takes O(N).
 * - The greedy selection loop traverses the array once, taking O(N).
 * Space Complexity: O(N)
 * - To store the `maxReachAt` array of size N.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minMen(int arr[]) {
        int n = arr.length;
        List<int[]> intervals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (arr[i] != -1) {
                int left = Math.max(0, i - arr[i]);
                int right = Math.min(n - 1, i + arr[i]);
                intervals.add(new int[]{left, right});
            }
        }
        intervals.sort((a, b) -> a[0] - b[0]);

        int curr = 0;   
        int count = 0;
        int i = 0;

        while (curr < n) {
            int farthest = -1;

            while (i < intervals.size() && intervals.get(i)[0] <= curr) {
                farthest = Math.max(farthest, intervals.get(i)[1]);
                i++;
            }
            if (farthest < curr) {
                return -1;
            }
            count++;
            curr = farthest + 1;
        }
        return count;
    }
}


