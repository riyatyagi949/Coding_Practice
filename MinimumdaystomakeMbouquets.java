/**
 * Problem: Minimum days to make M bouquets
 * * We are given an array `arr` where `arr[i]` is the day the i-th flower blooms.
 * We need to make `m` bouquets, and each bouquet requires `k` adjacent bloomed flowers.
 * The goal is to find the minimum number of days to make `m` bouquets. If it's not possible, return -1.
 * * Approach:
 * The problem asks for the minimum number of days, which suggests a search space for the answer.
 * The number of days can range from the minimum value in `arr` to the maximum value in `arr`.
 * This monotonic property (if we can make `m` bouquets in `d` days, we can also make them in `d+1` days)
 * makes this problem suitable for binary search on the number of days.
 * * 1. Check for feasibility: The total number of flowers required is `m * k`. If `arr.length < m * k`, it's impossible to make the bouquets, so we return -1.
 * 2. Binary Search: We perform a binary search on the possible number of days.
 * - The search space is `[low, high]`, where `low` is the minimum blooming day and `high` is the maximum blooming day in `arr`.
 * - For each `mid` day, we check if it's possible to make `m` bouquets.
 * - To check feasibility for a given day `d`, we iterate through the `arr`. We count the number of consecutive bloomed flowers. A flower is considered bloomed if its blooming day `arr[i]` is less than or equal to `d`.
 * - We maintain a `currentFlowers` counter for consecutive bloomed flowers. When `currentFlowers` reaches `k`, we increment the `bouquets` count and reset `currentFlowers` to 0.
 * - If `arr[i] > d`, the streak of consecutive bloomed flowers is broken, so we reset `currentFlowers` to 0.
 * - After iterating through `arr`, if the total `bouquets` count is greater than or equal to `m`, it means it's possible to make `m` bouquets by day `d`. We try for a smaller day, so we set `ans = mid` and `high = mid - 1`.
 * - If the total `bouquets` count is less than `m`, we need more time, so we set `low = mid + 1`.
 * 3. The binary search continues until `low > high`. The final `ans` will be the minimum number of days.
 * * Complexity Analysis:
 * Time Complexity: O(N * log(max_day - min_day)).
 * - O(N) for finding min and max day.
 * - Binary search takes O(log(max_day - min_day)) iterations.
 * - Inside the binary search, the `isPossible` function takes O(N) to iterate through the array.
 * - Total time complexity is dominated by the binary search part.
 * * Space Complexity: O(1).
 * - We only use a few variables for the binary search and the helper function. No extra data structures are needed that scale with the input size.
 */
class Solution {
    public int minDays(int[] arr, int m, int k) {
        long totalFlowersNeeded = (long) m * k;
        if (arr.length < totalFlowersNeeded) {
            return -1;
        }

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for (int day : arr) {
            low = Math.min(low, day);
            high = Math.max(high, day);
        }

        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(arr, m, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean isPossible(int[] arr, int m, int k, int day) {
        int bouquets = 0;
        int currentFlowers = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= day) {
                currentFlowers++;
                if (currentFlowers == k) {
                    bouquets++;
                    currentFlowers = 0;
                }
            } else {
                currentFlowers = 0;
            }
        }
        return bouquets >= m;
    }
}