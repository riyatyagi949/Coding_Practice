// Problem Statement:
// You are given an array arr[] of size n, where arr[i] represents the number of soldiers in the i-th troop.
// You are also given an integer k. A troop is considered "lucky" if its number of soldiers is a multiple of k.
// Find the minimum total number of soldiers to add across all troops so that at least ceil(n / 2) troops become lucky.

    // Approach:
    // The goal is to make at least ceil(n / 2) troops "lucky" by adding the minimum total number of soldiers.
    // For each troop with `arr[i]` soldiers, the number of soldiers to add to make it a multiple of `k`
    // can be calculated. If `arr[i]` is already a multiple of `k`, 0 soldiers need to be added.
    // Otherwise, the number of soldiers to add is `k - (arr[i] % k)`.
    //
    // Let's call this number of soldiers to add for each troop `cost[i]`. We want to choose ceil(n / 2) troops
    // with the smallest `cost` values and add soldiers to them. The total number of soldiers to add will be
    // the sum of these smallest costs.
    //
    // The algorithm is as follows:
    // 1. Calculate the required number of lucky troops, which is `ceil(n / 2)`. This can be done with integer arithmetic as `(n + 1) / 2`.
    // 2. Iterate through the input array `arr`. For each element `arr[i]`, calculate the minimum soldiers needed to make it a multiple of `k`.
    //    - If `arr[i] % k == 0`, the cost is 0.
    //    - If `arr[i] % k != 0`, the cost is `k - (arr[i] % k)`.
    // 3. Store these costs in a new array, say `costs`.
    // 4. Sort the `costs` array in non-decreasing order.
    // 5. Sum the first `required_lucky_troops` elements of the sorted `costs` array. This sum is the minimum total number of soldiers to add.
    //
    // Example: arr = [5, 6, 3, 2, 1], k = 2
    // n = 5, required lucky troops = ceil(5/2) = 3.
    // Costs:
    // arr[0] = 5 -> 5 % 2 = 1 -> cost = 2 - 1 = 1
    // arr[1] = 6 -> 6 % 2 = 0 -> cost = 0
    // arr[2] = 3 -> 3 % 2 = 1 -> cost = 2 - 1 = 1
    // arr[3] = 2 -> 2 % 2 = 0 -> cost = 0
    // arr[4] = 1 -> 1 % 2 = 1 -> cost = 2 - 1 = 1
    // Costs array: [1, 0, 1, 0, 1]
    // Sorted costs: [0, 0, 1, 1, 1]
    // Sum of first 3 elements (0, 0, 1) = 1.
    //
    // This approach is correct because we want to minimize the total added soldiers, and we can achieve this
    // by always choosing the troops that require the fewest additional soldiers to become lucky.
    // Sorting the costs and picking the smallest ones ensures this greedy strategy is optimal.

    // Optimal Solution -
    import java.util.*;

 class Solution {
    public int minSoldiers(int[] arr, int k) {
        int n = arr.length;
        int neededLucky = (n + 1) / 2; 
        int currentLucky = 0;
        List<Integer> additions = new ArrayList<>();

        for (int soldiers : arr) {
            if (soldiers % k == 0) {
                currentLucky++;
            } 
            else {
                additions.add(k - (soldiers % k));
            }
        }
        if (currentLucky >= neededLucky) {
            return 0;
        }
        Collections.sort(additions);

        int soldiersAdded = 0;
        int i = 0;
        while (currentLucky < neededLucky && i < additions.size()) {
            soldiersAdded += additions.get(i);
            currentLucky++;
            i++;
        }
         return soldiersAdded;
    }
}
