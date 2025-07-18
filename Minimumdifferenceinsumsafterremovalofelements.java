/*
    Problem Statement:
    You are given a 0-indexed integer array nums consisting of 3 * n elements.
    You are allowed to remove any subsequence of elements of size exactly n from nums. The remaining 2 * n elements will be divided into two equal parts:
    The first n elements belonging to the first part and their sum is sumFirst.
    The next n elements belonging to the second part and their sum is sumSecond.
    The difference in sums of the two parts is denoted as sumFirst - sumSecond.
    Return the minimum difference possible between the sums of the two parts after the removal of n elements.

    Approach:
    To minimize `sumFirst - sumSecond`, we need to minimize `sumFirst` and maximize `sumSecond`.
    We consider a split point `j` such that `sumFirst` is formed by `n` elements chosen from `nums[0...j-1]` and `sumSecond` is formed by `n` elements chosen from `nums[j...3n-1]`.
    The possible values for `j` range from `n` to `2n`.

    1. Precompute `minLeft[i]`: This array stores the minimum sum of `n` elements selected from `nums[0...i]`. A max-priority queue is used to maintain the `n` smallest elements seen so far.
    2. Precompute `maxRight[i]`: This array stores the maximum sum of `n` elements selected from `nums[i...3n-1]`. A min-priority queue is used to maintain the `n` largest elements seen so far.

    Finally, iterate `j` from `n` to `2n`. For each `j`, the `sumFirst` will be `minLeft[j-1]` and `sumSecond` will be `maxRight[j]`. Calculate `minLeft[j-1] - maxRight[j]` and find the global minimum.

    Time Complexity: O(N log n), where N = 3 * n. (Two passes for precomputation, each involving N priority queue operations of O(log n), plus one pass for final calculation).
    Space Complexity: O(N) (For `minLeft`, `maxRight` arrays, and priority queues).
    */

    import java.util.PriorityQueue;
    import java.util.Collections;

 class Solution {
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        int N = nums.length;

        long[] minLeft = new long[N];
        PriorityQueue<Integer> pqLeft = new PriorityQueue<>(Collections.reverseOrder()); // Max-heap for smallest n elements
        long currentSumLeft = 0;
        for (int i = 0; i < N; i++) {
            pqLeft.offer(nums[i]);
            currentSumLeft += nums[i];
            if (pqLeft.size() > n) {
                currentSumLeft -= pqLeft.poll();
            }
            if (pqLeft.size() == n) {
                minLeft[i] = currentSumLeft;
            }
        }

        long[] maxRight = new long[N];
        PriorityQueue<Integer> pqRight = new PriorityQueue<>(); 
        long currentSumRight = 0;
        for (int i = N - 1; i >= 0; i--) {
            pqRight.offer(nums[i]);
            currentSumRight += nums[i];
            if (pqRight.size() > n) {
                currentSumRight -= pqRight.poll();
            }
            if (pqRight.size() == n) {
                maxRight[i] = currentSumRight;
            }
        }

        long minDiff = Long.MAX_VALUE;

        for (int j = n; j <= 2 * n; j++) {
            long sumFirst = minLeft[j - 1];
            long sumSecond = maxRight[j];
            minDiff = Math.min(minDiff, sumFirst - sumSecond);
        }
        return minDiff;
    }
}