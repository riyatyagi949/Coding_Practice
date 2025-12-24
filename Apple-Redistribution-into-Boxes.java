/**
 * Problem Statement: 3074. Apple Redistribution into Boxes
 * --------------------------------------------------------
 * You are given an array 'apple' of size 'n' and an array 'capacity' of size 'm'.
 * There are 'n' packs where the i-th pack contains apple[i] apples. 
 * There are 'm' boxes where the i-th box has a capacity of capacity[i] apples.
 * * Return the minimum number of boxes you need to select to redistribute all apples.
 * Note: Apples from the same pack can be distributed into different boxes (flexible distribution).
 * * Example:
 * Input: apple = [1,3,2], capacity = [4,3,1,5,2]
 * Output: 2
 * Explanation: Total apples = 1 + 3 + 2 = 6. Using boxes with capacities 5 and 4 
 * (total 9) is sufficient and uses the minimum number of boxes.
 */
/**
     * Optimal Solution: Greedy Strategy
     * ---------------------------------
     * Since we want to minimize the number of boxes, we should always prioritize 
     * using the boxes with the largest capacities first.
     * * 
     * * Algorithm:
     * 1. Calculate the total number of apples across all packs.
     * 2. Sort the box 'capacity' array in ascending order.
     * 3. Iterate through the 'capacity' array from the largest element (the end) 
     * towards the smallest.
     * 4. Keep a running sum of the selected boxes' capacities.
     * 5. Stop as soon as the running capacity is greater than or equal to the total apples.
     */
// Optimal Solution Code - 

import java.util.Arrays;

class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }
        Arrays.sort(capacity);

        int boxesUsed = 0;
        int currentTotalCapacity = 0;

        for (int i = capacity.length - 1; i >= 0; i--) 
        {
        currentTotalCapacity += capacity[i];
        boxesUsed++;

        if (currentTotalCapacity >= totalApples)
        {
          return boxesUsed;
     }
   }
     return boxesUsed;
    }
}

/**
 * Complexity Analysis:
 * --------------------
 * Time Complexity: $O(n + m \log m)$
 * - Calculating the total sum of apples takes $O(n)$, where $n$ is the length of 'apple'.
 * - Sorting the 'capacity' array takes $O(m \log m)$, where $m$ is the length of 'capacity'.
 * - The final loop to pick boxes takes $O(m)$ in the worst case.
 * * Space Complexity: $O(1)$ or $O(m)$
 * - The space complexity depends on the sorting algorithm implementation (e.g., dual-pivot 
 * quicksort in Java for primitives uses $O(\log m)$ stack space). 
 * No extra data structures proportional to the input size are created by us.
 */
