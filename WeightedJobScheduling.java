/**
 * Problem Statement: Weighted Job Scheduling
 * ------------------------------------------
 * Given a 2D array jobs[][] of size n x 3, where each row represents a single job 
 * with [Start Time, End Time, Profit]. Find the maximum profit you can earn by 
 * scheduling a subset of non-overlapping jobs.
 *
 * Note: Jobs are non-overlapping if the end time of one job is less than or equal 
 * to the start time of the next job (i.e., [1, 2] and [2, 100] are non-overlapping).
 *
 * Constraints:
 * 1 <= jobs.size() <= 10^5
 * 1 <= jobs[i][0] < jobs[i][1] <= 10^9
 * 1 <= jobs[i][2] <= 10^4
 *//**
     * Optimal Solution: Dynamic Programming with Binary Search
     * --------------------------------------------------------
     * 1. **Sort:** Sort the jobs based on their **end times**. This order is crucial 
     * for the DP formulation.
     * 2. **DP State:** Let `dp[i]` be the **maximum profit** that can be achieved 
     * using a subset of jobs from the first `i+1` jobs (jobs[0] to jobs[i]).
     * 3. **DP Transition:** For job `i`, we have two choices:
     * a. **Exclude job i:** The profit is the same as the maximum profit achievable 
     * using the first `i` jobs, which is `dp[i-1]`.
     * b. **Include job i:** The profit is `jobs[i].profit` PLUS the maximum profit 
     * from a non-overlapping job scheduled *before* job `i`.
     * To find the best preceding non-overlapping job, we must find the latest 
     * job `j < i` such that `jobs[j].endTime <= jobs[i].startTime`.
     * 4. **Optimization (Binary Search):** Since the jobs are sorted by end time, 
     * we can use **Binary Search** on the end times of jobs [0...i-1] to efficiently 
     * find the index `j` of the latest job that satisfies `jobs[j].endTime <= jobs[i].startTime`.
     * If such a job `j` exists, the profit gained from including job `i` is 
     * `jobs[i].profit + dp[j]`.
     * 5. **Final Result:** `dp[i] = max(dp[i-1], jobs[i].profit + (dp[j] if j exists))`.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N log N), where N is the number of jobs.
 * - **Sorting:** Sorting the jobs by end time takes O(N log N).
 * - **DP Loop:** The main loop iterates N times.
 * - **Binary Search:** Inside the loop, the `binarySearch` takes O(log N) time.
 * - Total time complexity: O(N log N + N * log N) = O(N log N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the number of jobs.
 * - O(N) space is used for the `dp` array.
 * - O(N) space is used for the `endTimes` array.
 */
// Optimal Solution in Java -
import java.util.*;

class Solution {
    public int maxProfit(int[][] jobs) {
        int n = jobs.length;
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[] endTimes = new int[n];
        for (int i = 0; i < n; i++)
        {
            endTimes[i] = jobs[i][1];
        }
        int[] dp = new int[n];
        dp[0] = jobs[0][2];

        for (int i = 1; i < n; i++)
        {
            int profitIncludingCurrent = jobs[i][2];

            int index = binarySearch(endTimes, jobs[i][0]);
            if (index != -1)
            {
                profitIncludingCurrent += dp[index];
            }
            dp[i] = Math.max(dp[i - 1], profitIncludingCurrent);
        }
        return dp[n - 1];
    }
    private int binarySearch(int[] endTimes, int startTime) 
    {
        int low = 0, high = endTimes.length - 1, ans = -1;
        while (low <= high)
        {
            int mid = (low + high) / 2;
            if (endTimes[mid] <= startTime) 
            {
                ans = mid;
                low = mid + 1;
            } 
            else 
            {
                high = mid - 1;
            }
        }
        return ans;
    }
}
