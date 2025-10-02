/**
 * Problem Statement:
 * You are given `numBottles` (initial full water bottles) and `numExchange`.
 * You can: 1) Drink a full bottle (it becomes an empty bottle). 2) Exchange `numExchange` empty bottles for one full bottle, and then increase `numExchange` by one.
 * You want to maximize the total number of water bottles you can drink.
 *
 * Optimal Approach (Simulation/Greedy):
 * This problem is a straightforward simulation of the process. To maximize the total number of drunk bottles, we should drink every full bottle we have and perform an exchange as soon as we have enough empty bottles, because the cost of exchange (`numExchange`) keeps increasing. Delaying an exchange will only make the next one more expensive.
 *
 * The process naturally splits into two main phases:
 * 1. Drinking: While we have full bottles (`numBottles > 0`), we drink them. This increases the total `drank` count and the `empty` bottle count.
 * 2. Exchanging: While we have enough `empty` bottles to meet the current exchange cost (`empty >= numExchange`), we perform the exchange.
 * - This gives us one new full bottle (`numBottles++`).
 * - The `empty` count decreases by the exchange cost (`empty -= numExchange`).
 * - The exchange cost increases for the next time (`numExchange++`).
 *
 * We repeat these two phases until no full bottles remain AND we don't have enough empty bottles to perform an exchange.
 *
 * The most efficient way to implement this is with a single loop that continues as long as an exchange is possible (since drinking will always be possible if we have bottles, and the loop logic handles drinking first). A simpler approach is to use a `while(true)` loop that breaks when no more progress can be made.
 *
 * Algorithm Steps:
 * 1. Initialize `totalDrank` to 0 and `emptyBottles` to 0.
 * 2. First, drink all initial full bottles:
 * - `totalDrank += numBottles`
 * - `emptyBottles = numBottles`
 * - `numBottles = 0`
 * 3. Start a loop for exchanges:
 * - Check if the current `emptyBottles` is sufficient for the current `numExchange` cost.
 * - If `emptyBottles >= numExchange`:
 * - Perform the exchange: `emptyBottles -= numExchange`.
 * - Gain a full bottle: `numBottles = 1`.
 * - Increase the cost for the next exchange: `numExchange++`.
 * - Drink the new full bottle: `totalDrank++`, `emptyBottles++`, `numBottles--`. (This is equivalent to `totalDrank++` and `emptyBottles` remains unchanged relative to the start of the drinking phase, but since we already accounted for the empty bottle from the previous step, we can just combine them).
 * - A cleaner way: Treat the exchange and immediate drinking as one step:
 * `emptyBottles -= numExchange;` // Bottles used for exchange
 * `numExchange++;`            // Cost increases
 * `totalDrank++;`              // The new bottle is immediately drunk
 * `emptyBottles++;`            // The new bottle becomes an empty bottle
 * - If `emptyBottles < numExchange`, we can no longer make an exchange, so the loop terminates.
 *
 * The logic is simpler if we treat the process as consuming all full bottles, then exchanging, then consuming the new bottle, and repeating.
 *
 * Time Complexity: O(log(N)) or O(sqrt(N)) if N is the initial number of bottles, as `numExchange` increments linearly. Since `numExchange` starts small (e.g., 3) and has to reach the maximum possible number of empty bottles (at most 200) to stop the process, the number of exchange operations is relatively small. With the tight constraints (N <= 100), the overall complexity is very fast, essentially O(max(numBottles, numExchange)), which is $O(C)$ where $C$ is a small constant (200 in this case).
 *
 * Space Complexity: O(1). Only a few integer variables are used for tracking state.
 */
// Optimal Solution in Java - 