// Problem Statement:
// You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
// You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
// 1. You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
// 2. Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
// 3. Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
// Given the integer array fruits, return the maximum number of fruits you can pick.

// Approach:
// This problem can be solved using a sliding window approach. We want to find the longest subarray that contains at most two unique fruit types.
// We use a HashMap to keep track of the count of each fruit type within the current window.
// We use two pointers, `left` and `right`, to define the window. The `right` pointer expands the window by moving to the right and adding the fruit to our map.
// If the number of unique fruit types in the map becomes greater than 2, we need to shrink the window from the left.
// We move the `left` pointer to the right, decrementing the count of the fruit at `fruits[left]` in the map. If the count of a fruit becomes 0, we remove it from the map.
// We continue shrinking the window until the number of unique fruit types is 2 or less.
// At each step, we update the maximum length of the valid window found so far, which is `right - left + 1`.

// Time Complexity:
// We use a single pass through the array with two pointers. Each element is visited by the `right` pointer once and by the `left` pointer at most once.
// The HashMap operations (put, get, remove) take O(1) on average.
// Therefore, the time complexity is O(n), where n is the length of the `fruits` array.

// Space Complexity:
// The space is used by the HashMap to store the count of fruit types.
// Since we only allow at most two unique fruit types in the window, the maximum size of the map is 2.
// Therefore, the space complexity is O(1).

// Optimal Solution:
import java.util.HashMap;

class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        if (n <= 2) {
            return n;
        }

        HashMap<Integer, Integer> fruitCount = new HashMap<>();
        int left = 0;
        int maxFruits = 0;

        for (int right = 0; right < n; right++) {
            int currentFruit = fruits[right];
            fruitCount.put(currentFruit, fruitCount.getOrDefault(currentFruit, 0) + 1);

            while (fruitCount.size() > 2) {
                int leftFruit = fruits[left];
                fruitCount.put(leftFruit, fruitCount.get(leftFruit) - 1);
                
                if (fruitCount.get(leftFruit) == 0) {
                    fruitCount.remove(leftFruit);
                }
                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        return maxFruits;
    }
}
    

