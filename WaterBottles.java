/**
 * Problem Statement:
 * Given `numBottles` (initial full water bottles) and `numExchange` (empty bottles required for one full bottle exchange),
 * return the maximum number of water bottles you can drink. Drinking a full bottle turns it into an empty one.
 *
 * Optimal Approach (Simulation/Iterative):
 * The problem can be solved by simulating the process of drinking and exchanging bottles until no more exchanges are possible.
 *
 * 1. Initialize `totalDrank` with the initial number of full bottles, `numBottles`. This is the first batch of bottles consumed.
 * 2. Initialize `emptyBottles` to `numBottles`, as these are the empty bottles resulting from the first round of drinking.
 * 3. Start a loop that continues as long as we have enough empty bottles to perform at least one exchange, i.e., `emptyBottles >= numExchange`.
 * 4. Inside the loop, perform the exchange:
 * a. Calculate the number of new full bottles obtained: `newFullBottles = emptyBottles / numExchange`.
 * b. Add these new full bottles to the total count of bottles drunk: `totalDrank += newFullBottles`.
 * c. Update the number of empty bottles. The new empty bottles come from two sources:
 * i. The empty bottles that were *not* used in the exchange: `emptyBottles % numExchange`.
 * ii. The empty bottles that result from drinking the newly obtained full bottles: `newFullBottles`.
 * The updated empty bottles count is: `emptyBottles = (emptyBottles % numExchange) + newFullBottles`.
 * 5. Once the loop terminates (when `emptyBottles < numExchange`), return `totalDrank`.
 *
 * Time Complexity: O(log_{numExchange}(numBottles)).
 * In each iteration, the number of empty bottles is reduced by approximately `newFullBottles * (numExchange - 1)`, which means the number of bottles effectively reduces. Since `numExchange` is at least 2, the number of operations is logarithmic with respect to `numBottles`.
 * Given the tight constraint (numBottles <= 100), it's also effectively O(1) or O(numBottles) in practice, but the theoretical complexity is logarithmic.
 *
 * Space Complexity: O(1). We use a constant amount of extra space for variables like `totalDrank`, `emptyBottles`, and `newFullBottles`.
 */
// Optimal Solution in Java - 

class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int total = numBottles; 
        int empty = numBottles;  
        
        while (empty >= numExchange) 
        {
            int newBottles = empty / numExchange;   
            total += newBottles;
            empty = empty % numExchange + newBottles; 
        }
        return total;
    }
}