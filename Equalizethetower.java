// Problem Statement:
// You are given an array heights[] representing the heights of towers and another array cost[] where each element represents the cost of modifying the height of respective tower.
// The goal is to make all towers of same height by either adding or removing blocks from each tower.
// Modifying the height of tower (add or remove) 'i' by 1 unit costs cost[i].
// Return the minimum cost to equalize the heights of all towers.

// Approach:
// This problem can be solved using a ternary search or by observing the convexity of the cost function.
// The cost function f(H) = sum(abs(heights[i] - H) * cost[i]) is a convex function.
// For a convex function, we can use ternary search to find the minimum value.
// The range of possible heights for the target height H can be from the minimum height in the array to the maximum height in the array.
// Alternatively, since the heights are integers and within a relatively small range (1 to 10^4),
// we can iterate through all possible target heights from min_height to max_height and calculate the cost for each.
// The minimum cost found during this iteration will be the answer.
// Let's consider the iterative approach as it's simpler to implement and the constraints allow it.

// Time Complexity:
// The maximum height is 10^4 and the minimum height is 1. So, the range of possible target heights is at most 10^4.
// For each possible target height, we iterate through all `n` towers to calculate the cost.
// Therefore, the total time complexity will be O((max_height - min_height) * n).
// Given max_height = 10^4 and n = 10^5, this is approximately 10^4 * 10^5 = 10^9, which is too slow.

// Let's re-evaluate the approach. The cost function is indeed convex.
// A more efficient approach for convex functions over integers is to use ternary search.
// In ternary search, we pick two midpoints, m1 and m2, and compare f(m1) and f(m2).
// If f(m1) < f(m2), the minimum lies in [low, m2]. Else, it lies in [m1, high].
// We repeat this process until the search range is small enough.
// The range of heights is from 1 to 10000. Let's set the search space from 1 to 10000.

// Ternary search for integers:
// The typical ternary search loop continues while high - low >= 3 (or a similar small constant).
// Then, we can iterate linearly over the remaining small range.

// Let's refine the time complexity for ternary search.
// The number of iterations in ternary search is log3(range).
// In each iteration, we calculate the cost for two midpoints, which takes O(n) time.
// So, the total time complexity will be O(n * log(max_height)).
// Given n = 10^5 and max_height = 10^4, log(10^4) is about 13-14 (log base 3).
// So, 10^5 * 14 = 1.4 * 10^6 operations, which is efficient enough.

// Space Complexity:
// O(1) as we only use a few variables for calculations.

// Optimal Solution:
class Solution {
    private long calculateCost(int[] heights, int[] cost, int targetHeight) {
        long currentCost = 0;
        for (int i = 0; i < heights.length; i++) {
            currentCost += (long) Math.abs(heights[i] - targetHeight) * cost[i];
        }
        return currentCost;
    }

    public int minCost(int[] heights, int[] cost) {
        int minHeight = 1; 
        int maxHeight = 10000; 

        while (maxHeight - minHeight >= 3) { 
            int m1 = minHeight + (maxHeight - minHeight) / 3;
            int m2 = maxHeight - (maxHeight - minHeight) / 3;

            long cost1 = calculateCost(heights, cost, m1);
            long cost2 = calculateCost(heights, cost, m2);

            if (cost1 < cost2) {
                maxHeight = m2; 
            } else {
                minHeight = m1; 
            }
        }
        long overallMinCost = Long.MAX_VALUE;
        for (int h = minHeight; h <= maxHeight; h++) {
            overallMinCost = Math.min(overallMinCost, calculateCost(heights, cost, h));
        }

        return (int) overallMinCost;
    }
}