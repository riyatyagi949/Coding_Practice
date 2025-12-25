/**
 * PROBLEM STATEMENT: 3075. Maximize Happiness of Selected Children
 * --------------------------------------------------------------------------------
 * You are given an array 'happiness' of length n, and a positive integer k.
 * There are n children, each with a happiness value. You must select k children 
 * in k turns. 
 * * Each turn, when you select a child:
 * 1. The happiness value of all children not yet selected decreases by 1.
 * 2. Happiness values cannot drop below 0.
 * * Goal: Return the maximum total happiness of the k children selected.
 * * Constraints:
 * - 1 <= n <= 2 * 10^5
 * - 1 <= happiness[i] <= 10^8
 * - 1 <= k <= n
 * --------------------------------------------------------------------------------
 * * OPTIMAL SOLUTION: GREEDY APPROACH
 * --------------------------------------------------------------------------------
 * Since every turn reduces the happiness of all unselected children by 1, we want 
 * to pick the children with the highest happiness values as early as possible.
 * * Strategy:
 * 1. Sort the children's happiness values in descending order.
 * 2. In turn 'i' (where i = 0, 1, ..., k-1), pick the i-th happiest child.
 * 3. The effective happiness added is: max(0, original_happiness - i).
 * - The '-i' represents the decrement of 1 per turn for the i turns that passed.
 * - The 'max(0, ...)' ensures we don't add negative happiness.
 * * Why this works:
 * Picking a smaller value early doesn't prevent larger values from decreasing. 
 * To maximize the sum, we must "save" the largest values from as many decrements 
 * as possible by picking them first.
 * --------------------------------------------------------------------------------
 * * 
 * * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(n log n)
 * - Sorting the array takes O(n log n).
 * - Iterating through k elements to calculate the sum takes O(k), where k <= n.
 * * Space Complexity: O(1) or O(log n)
 * - O(1) auxiliary space if we ignore the space used by the sorting algorithm.
 * - Most primitive sorting (like Dual-Pivot Quicksort in Java) uses O(log n) stack space.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution Code in Java-

import java.util.Arrays;
class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long sum = 0;
        int turns = 0;

        for (int i = happiness.length - 1; i >= 0 && turns < k; i--) {
            int value = happiness[i] - turns;
            if (value <= 0) 
            break;

            sum += value;
            turns++;
        }
        return sum;
    }
}

