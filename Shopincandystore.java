// Problem Statement
// Given an array prices representing the prices of different types of candies and an integer k.
// For every candy you buy, you can get up to k other different candies for free.
// Find the minimum and maximum amount of money needed to buy all the candies.
//  Note that in both cases, you must take the maximum number of free candies possible during each purchase.


// Approach
// The problem asks for two values: the minimum and maximum cost to buy all the candies under a specific offer.
//  The strategy to achieve these two outcomes will be opposite.
// To find the minimum cost, a greedy approach is most effective.
//  We should always buy the cheapest available candy and, for each purchase, take the most expensive remaining candies for free.
// We can achieve this by first sorting the prices array in ascending order. Then, we iterate from the beginning of the sorted array, adding the price of the current candy to our total minimum cost. For each candy bought, we get k free candies.
// To maximize the value of this offer (and thus minimize our cost), we take the k most expensive candies for free.
//  We can simulate this by using two pointers: one (buy_ptr) starting at the beginning of the array for the candies we buy, and another (free_ptr) at the end for the candies we take for free. We advance buy_ptr by one and decrement free_ptr by k for each transaction. The process continues until all candies are accounted for (buy_ptr crosses free_ptr).

// Similarly, to find the maximum cost, we should apply a greedy strategy that is the reverse of the minimum cost approach. To maximize the total spent, we should always buy the most expensive available candy and, for each purchase, take the cheapest remaining candies for free.
//  After sorting the prices array in ascending order, the most expensive candies are at the end. 
//  We'll iterate from the end of the sorted array, adding the price of the current candy to our total maximum cost. For each purchase, we take the k cheapest remaining candies for free.
//   This can be done with two pointers: one (buy_ptr) at the end of the array and another (free_ptr) at the beginning. 
//   For each transaction, we decrement buy_ptr by one and increment free_ptr by k. This continues until all candies are processed.

// Time and Space Complexity
// Time Complexity: The dominant operation is sorting the prices array, which takes O(N
// logN) time, where N is the number of candies. The subsequent pointer-based traversals are linear, taking O(N) time. Therefore, the overall time complexity is O(N
// logN).

// Space Complexity: The space complexity is O(1) if the sorting is done in-place. If the sorting algorithm requires additional space, it would be O(
// logN) or O(N) depending on the implementation.

// Optimal Solution
import java.util.*;

class Solution {
    public ArrayList<Integer> minMaxCandy(int[] prices, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        Arrays.sort(prices);

        int minCost = 0;
        int start = 0, end = prices.length - 1;
        while (start <= end) {
            minCost += prices[start];
            start++; 
            end -= k; 
        }

        int maxCost = 0;
        start = 0; end = prices.length - 1;
        while (start <= end) {
            maxCost += prices[end]; 
            end--;
            start += k; 
        }

        ans.add(minCost);
        ans.add(maxCost);

        return ans;
    }
}
