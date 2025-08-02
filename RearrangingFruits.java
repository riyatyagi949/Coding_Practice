/*
Problem Statement:
You have two fruit baskets containing n fruits each. You are given two 0-indexed integer arrays basket1 and basket2 representing the cost of fruit in each basket. You want to make both baskets equal. To do so, you can use the following operation as many times as you want:
Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
The cost of the swap is min(basket1[i],basket2[j]).
Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.
Return the minimum cost to make both the baskets equal or -1 if impossible.

Approach:
First, we need to check if it's possible to make the baskets equal. For the baskets to be equal, the combined frequency of each fruit cost in both baskets must be even. We can use a HashMap to count the frequency of each fruit cost across both baskets. If any fruit cost has an odd frequency, it's impossible to make the baskets equal, and we return -1.

After confirming that a solution is possible, we need to find the minimum cost. The total set of fruits must be distributed equally between the two baskets. This means that for each fruit cost, if basket1 has a certain number of fruits and basket2 has a different number, the difference must be made up by swaps.

We can identify the fruits that are "mismatched". A fruit is mismatched if it is in basket1 but should be in basket2, or vice versa. We can create two new lists, one for fruits that need to be moved from basket1 to basket2, and another for fruits that need to be moved from basket2 to basket1. The counts of these two lists must be equal.

To minimize the cost, we should pair the cheapest fruits that need to be swapped. A swap between two fruits `a` and `b` has a cost of `min(a, b)`. We have two options for each pair of fruits to be swapped:
1. Direct swap: Swap a fruit from basket1 (let's say cost `x`) with a fruit from basket2 (cost `y`). The cost is `min(x, y)`.
2. Swap with the cheapest fruit: Swap a fruit from basket1 (cost `x`) with the cheapest fruit available in either basket (cost `min_cost`), and then swap the cheapest fruit with the fruit from basket2 (cost `y`). The total cost is `2 * min_cost + min(x, y)`. Wait, this is not correct. The operation is simply swapping `basket1[i]` with `basket2[j]`. So, the cost of a single swap is `min(basket1[i], basket2[j])`.

Let's re-evaluate the swapping strategy. We have two lists of fruits to be swapped: `list1` (from basket1 to basket2) and `list2` (from basket2 to basket1). Both lists have the same size. To minimize the cost, we should perform swaps that have the lowest cost. The cost of a swap is `min(cost_from_basket1, cost_from_basket2)`. To get the minimum total cost, we should sort both lists and swap the corresponding fruits.
The total cost would be the sum of `min(list1[i], list2[i])` for all `i`.

However, there's a more optimal strategy. We can also swap a fruit from `list1` (cost `x`) with the absolute cheapest fruit in the entire combined set of fruits (let's call its cost `min_val`). Then, we can swap `min_val` with a fruit from `list2` (cost `y`).
The cost of swapping `x` with `min_val` is `min(x, min_val)`. Since `min_val` is the absolute minimum, this is `min_val`. The cost of swapping `min_val` with `y` is `min(min_val, y)`, which is also `min_val`. The total cost for this two-step swap is `2 * min_val`.
The cost of a direct swap between `x` and `y` is `min(x, y)`. We should choose the cheaper of these two options: `min(min_val * 2, min(x, y))`.

So, the algorithm is:
1. Count the frequency of all fruits in both baskets combined. Store in a HashMap.
2. Check if all frequencies are even. If not, return -1.
3. Identify the fruits that need to be moved. Iterate through the counts. If `basket1` has more of a certain fruit than it should (half the total count), those are the fruits to be moved out of `basket1`. Similarly for `basket2`. Create two lists, `fromBasket1` and `fromBasket2`.
4. The size of `fromBasket1` and `fromBasket2` must be equal.
5. Sort both lists. `fromBasket1` ascending, `fromBasket2` ascending.
6. Find the minimum fruit cost `min_cost` in the entire collection of fruits.
7. Iterate through the sorted lists. For each pair of fruits to be swapped, one from `fromBasket1` and one from `fromBasket2`, calculate the cost of a direct swap `min(fromBasket1[i], fromBasket2[i])` and the cost of an indirect swap using the cheapest fruit `2 * min_cost`. The cost for this pair is `min(2 * min_cost, min(fromBasket1[i], fromBasket2[i]))`. Sum up these costs to get the minimum total cost.

Time Complexity: O(N log N)
The main operations are counting frequencies (O(N)), creating the lists of fruits to swap (O(N)), sorting these lists (O(N log N)), and then iterating through them (O(N)). The sorting step dominates the complexity. N is the number of fruits in one basket.

Space Complexity: O(N)
We use HashMaps and lists to store frequencies and fruits to be swapped. In the worst case, all fruit costs are distinct, so the space required is proportional to N.

*/

import java.util.*;

class Solution {
  public long minCost(int[] basket1, int[] basket2) {
    long ans = 0;
    List<Integer> swapped = new ArrayList<>();
    Map<Integer, Integer> count = new HashMap<>();

    for (final int b : basket1)
      count.merge(b, 1, Integer::sum);

    for (final int b : basket2)
      count.merge(b, -1, Integer::sum);

    for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
      final Integer num = entry.getKey();
      final Integer freq = entry.getValue();
      if (freq % 2 != 0)
        return -1;
      for (int i = 0; i < Math.abs(freq) / 2; ++i)
        swapped.add(num);
    }

    final int minNum =
        Math.min(Arrays.stream(basket1).min().getAsInt(), Arrays.stream(basket2).min().getAsInt());
    Collections.sort(swapped);

    for (int i = 0; i < swapped.size() / 2; ++i)
      ans += Math.min(minNum * 2, swapped.get(i));
    return ans;
  }
}


