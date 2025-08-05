/**
 * Problem Statement:
 * You are given two arrays of integers, fruits and baskets, each of length n, where fruits[i] represents the quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
 * From left to right, place the fruits according to these rules:
 * Each fruit type must be placed in the leftmost available basket with a capacity greater than or equal to the quantity of that fruit type.
 * Each basket can hold only one type of fruit.
 * If a fruit type cannot be placed in any basket, it remains unplaced.
 * Return the number of fruit types that remain unplaced after all possible allocations are made.
 *
 * Example 1:
 * Input: fruits = [4,2,5], baskets = [3,5,4]
 * Output: 1
 *
 * Example 2:
 * Input: fruits = [3,6,1], baskets = [6,4,7]
 * Output: 0
 *
 * Constraints:
 * n == fruits.length == baskets.length
 * 1 <= n <= 100
 * 1 <= fruits[i], baskets[i] <= 1000
 */
/**
     * Approach:
     * The problem requires us to simulate the process of placing fruits into baskets according to specific rules.
     * We need to iterate through the fruits from left to right. For each fruit, we need to find the leftmost available basket that can hold it.
     * A basket is available if it has not been used yet. A basket can hold a fruit if its capacity is greater than or equal to the fruit's quantity.
     * We can use a boolean array or a similar data structure to keep track of which baskets are used.
     *
     * The algorithm would be as follows:
     * 1. Initialize a counter for unplaced fruits to 0.
     * 2. Initialize a boolean array, say `usedBaskets`, of the same size as `baskets`, all set to `false`.
     * 3. Iterate through the `fruits` array from `i = 0` to `n-1`.
     * 4. For each `fruits[i]`, iterate through the `baskets` array from `j = 0` to `n-1`.
     * 5. Inside the inner loop, check if `usedBaskets[j]` is `false` and `baskets[j]` is greater than or equal to `fruits[i]`.
     * 6. If such a basket is found, mark it as used (`usedBaskets[j] = true`) and break the inner loop to move to the next fruit.
     * 7. If the inner loop completes without finding a suitable basket for `fruits[i]`, it means the fruit remains unplaced.
     * 8. Increment the unplaced fruits counter.
     * 9. After iterating through all the fruits, return the unplaced fruits counter.
     *
     * Time Complexity: O(n^2)
     * We have a nested loop. The outer loop iterates through `n` fruits, and the inner loop iterates through `n` baskets. In the worst case, we might scan all baskets for each fruit.
     *
     * Space Complexity: O(n)
     * We use a boolean array `usedBaskets` of size `n` to keep track of used baskets.
     */

 class Solution {
     public int findUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] usedBaskets = new boolean[n];
        int unplacedFruits = 0;

        for (int i = 0; i < n; i++) {
            boolean placed = false;
            
            for (int j = 0; j < n; j++) {
                if (!usedBaskets[j] && baskets[j] >= fruits[i]) {
                    usedBaskets[j] = true;
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                unplacedFruits++;
            }
        }
        return unplacedFruits;
    }
}