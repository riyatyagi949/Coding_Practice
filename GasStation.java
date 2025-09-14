/**
 * Problem Statement:
 * You are given two arrays, `gas` and `cost`, of size `n`, representing the amount of gas at `n` stations and the cost to travel to the next station.
 * You have a car with an unlimited gas tank, starting with an empty tank.
 * The task is to find a starting gas station index from which you can complete a full circular tour.
 * If a solution exists, it is guaranteed to be unique. If no such station exists, return -1.
 *
 * Optimal Approach:
 * The key to this problem is to understand that a full tour is possible if and only if the total gas available is greater than or equal to the total cost to travel the entire tour.
 * This is a necessary condition: if `sum(gas) < sum(cost)`, we can never complete the tour, regardless of the starting point, because we will eventually run out of gas.
 *
 * If `sum(gas) >= sum(cost)`, a solution is guaranteed to exist. We can then find the starting station with a single pass.
 * We can use a greedy approach. Let's calculate the `difference = gas[i] - cost[i]` for each station `i`. This difference represents the net gain or loss of gas at each station.
 *
 * We maintain two variables:
 * - `total`: The total gas difference over the entire tour. This helps us check the necessary condition `sum(gas) >= sum(cost)`.
 * - `tank`: The current gas in the tank, starting from a potential starting station.
 * - `start`: The index of the potential starting station.
 *
 * The algorithm proceeds as follows:
 * 1. Initialize `total = 0`, `tank = 0`, and `start = 0`.
 * 2. Iterate through the stations from `i = 0` to `n-1`.
 * 3. In each iteration, calculate the `diff = gas[i] - cost[i]`.
 * 4. Add `diff` to both `total` and `tank`.
 * 5. If `tank` becomes negative at any point, it means that the current starting point (`start`) is not a valid solution because we ran out of gas before reaching station `i+1`.
 * - In this case, we reset our `start` to the next station (`i+1`) and reset `tank` to 0. This is the greedy part: we discard all previous stations and start fresh from `i+1`.
 * 6. After the loop, check the `total` sum. If `total` is negative, a tour is impossible, so we return -1.
 * 7. If `total` is non-negative, a solution exists, and the `start` index we found in our single pass is the correct starting station. The logic behind this is that if we complete the loop with `total >= 0`, and we reset `start` whenever `tank` dropped below zero, the final `start` must be a valid one. This is because the overall non-negative sum ensures that any negative cumulative segments are "made up for" later in the tour, and by resetting our starting point, we effectively ensure we find the first station from which a continuous non-negative path is possible.
 *
 * Time Complexity: O(n). We perform a single pass through the arrays.
 * Space Complexity: O(1). We use only a few variables to track the state.
 */

//  Optimal Solution in Java - 

class Solution {
    public int startStation(int[] gas, int[] cost) {
        int n = gas.length;
        int total = 0, tank = 0, start = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = gas[i] - cost[i];
            total += diff;
            tank += diff;
            
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        
        return total >= 0 ? start : -1;
    }
}