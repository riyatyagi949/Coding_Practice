/**
 * PROBLEM STATEMENT: 3507. Minimum Pair Removal to Sort Array I
 * --------------------------------------------------------------------------------
 * You are given an array 'nums'. You can perform the following operation 
 * any number of times:
 * 1. Select the adjacent pair with the minimum sum in 'nums'.
 * 2. If multiple such pairs exist, choose the leftmost one.
 * 3. Replace the selected pair with their sum.
 * * Goal: Return the minimum number of operations needed to make the array non-decreasing.
 * An array is non-decreasing if each element is greater than or equal to its 
 * previous element.
 * * Example:
 * Input: nums = [5, 2, 3, 1]
 * - Turn 1: Min pair (3,1) sum=4. Array becomes [5, 2, 4].
 * - Turn 2: Min pair (2,4) sum=6. Array becomes [5, 6].
 * Sorted! Result: 2 operations.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Simulation
 * --------------------------------------------------------------------------------
 * Given the constraints (N <= 50), a direct simulation of the process described 
 * in the problem is the optimal approach.
 * * 1. Convert the array to a List for easy removal and replacement.
 * 2. Check if the list is already non-decreasing (sorted).
 * 3. If not sorted:
 * - Scan the list to find the first pair (i, i+1) that produces the minimum sum.
 * - Replace element at 'i' with (list[i] + list[i+1]).
 * - Remove element at 'i+1'.
 * - Increment the operation counter.
 * 4. Repeat until the list is sorted.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N^2)
 * - In each operation, we reduce the size of the array by 1. There are at most 
 * N-1 operations.
 * - In each operation, we scan the array to check if it's sorted O(N) and find 
 * the minimum pair sum O(N).
 * - Total time: (N operations) * O(N scan) = O(N^2).
 * - For N=50, N^2 = 2500, which is extremely fast.
 * * Space Complexity: O(N)
 * - We use an ArrayList to store the current state of the array during simulation.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

class Solution {
    private int minPairSum(List<Integer> nums) {
        int minSum = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < nums.size() - 1; i++) {
            int sum = nums.get(i) + nums.get(i + 1);
            if (sum < minSum) {
                minSum = sum;
                index = i;
            }
        }
         return index;
    }
    private boolean isSorted(List<Integer> nums) {
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) > nums.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
         int operations = 0;

        while (!isSorted(list)) {
            int index = minPairSum(list);

            int merged = list.get(index) + list.get(index + 1);
            list.set(index, merged);
            list.remove(index + 1);

            operations++;
        }
         return operations;
    }
}

