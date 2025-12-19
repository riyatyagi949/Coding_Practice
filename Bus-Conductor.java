/**
 * Problem Statement: Bus Conductor
 * -------------------------------
 * You are the conductor of a bus. You are given two integer arrays, 'chairs[]' and 
 * 'passengers[]', both of equal length 'n'. 
 * - chairs[i] represents the position of the i-th chair.
 * - passengers[j] represents the position of the j-th passenger.
 * * You can move a passenger from position x to x+1 or x-1 in a single move.
 * Your task is to find the minimum number of moves required to assign each passenger 
 * to exactly one unique chair.
 * * Example:
 * Input: chairs[] = [3, 1, 5], passengers[] = [2, 7, 4]
 * Output: 4
 * Explanation: 
 * Sorted chairs: [1, 3, 5]
 * Sorted passengers: [2, 4, 7]
 * Moves: |1-2| + |3-4| + |5-7| = 1 + 1 + 2 = 4.
 * * Constraints:
 * 1 ≤ n ≤ 10^5
 * 1 ≤ chairs[i], passengers[j] ≤ 10^4
 */
/**
     * Optimal Solution Description:
     * ----------------------------
     * To minimize the total distance (moves), we should use a Greedy Approach. 
     * The intuition is that the passenger at the leftmost position should be moved 
     * to the leftmost available chair, the second leftmost passenger to the second 
     * leftmost chair, and so on. 
     * * Crossing paths (e.g., moving a far-left passenger to a far-right chair) will 
     * always result in a total distance greater than or equal to the non-crossing 
     * assignment.
     * * Algorithm:
     * 1. Sort the 'chairs' array in non-decreasing order.
     * 2. Sort the 'passengers' array in non-decreasing order.
     * 3. Iterate through both arrays simultaneously from index 0 to n-1.
     * 4. For each index 'i', calculate the absolute difference: |chairs[i] - passengers[i]|.
     * 5. Accumulate these differences into a 'totalMoves' variable.
     * 6. Return the totalMoves.
     */
// Code - 
import java.util.Arrays;
    class Solution {
          public int findMoves(int[] chairs, int[] passengers) {
          Arrays.sort(chairs);
          Arrays.sort(passengers);

          int totalMoves = 0;
          int n = chairs.length;

         for (int i = 0; i < n; i++) {
            totalMoves += Math.abs(chairs[i] - passengers[i]);
        }
        return totalMoves;
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N log N), where N is the number of chairs/passengers.
 * - Sorting the 'chairs' array takes O(N log N).
 * - Sorting the 'passengers' array takes O(N log N).
 * - The single pass to calculate absolute differences takes O(N).
 * - Total time: O(N log N) + O(N log N) + O(N) = O(N log N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) or O(log N) auxiliary space.
 * - The algorithm itself uses a constant amount of extra space (totalMoves, i, n).
 * - However, the sorting algorithm (Dual-Pivot Quicksort used in Arrays.sort for primitives) 
 * requires O(log N) space for the recursion stack.
 */
