/**
 * PROBLEM STATEMENT: Bus Ticket Change
 * --------------------------------------------------------------------------------
 * You are given an array arr[] representing passengers in a queue. Each bus ticket 
 * costs 5 coins. arr[i] denotes the note a passenger uses to pay (5, 10, or 20). 
 * You must serve passengers in order and provide correct change so each effectively 
 * pays exactly 5 coins.
 * * Determine if it is possible to serve all passengers without running out of change.
 *
 * Example 1:
 * Input: arr[] = [5, 5, 5, 10, 20] -> Output: true
 * Explanation: Collect three 5s. 4th person gives 10, you return one 5. 5th person 
 * gives 20, you return one 10 and one 5.
 *
 * Example 2:
 * Input: arr[] = [5, 5, 10, 10, 20] -> Output: false
 * Explanation: After 4th person, you have two 10s and zero 5s. 5th person gives 20 
 * (needs 15 change), but you can't provide it.
 *
 * Constraints:
 * - 1 <= arr.size() <= 10^5
 * - arr[i] is in {5, 10, 20}
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: GREEDY SIMULATION
 * --------------------------------------------------------------------------------
 * We track the count of 5-coin and 10-coin notes we have in hand. 
 * (We don't need to track 20-coin notes because they are never used as change).
 * * * Greedy Strategy for 20-coin notes:
 * When a passenger pays with 20, we need 15 change. There are two ways to give it:
 * 1. One 10-coin + One 5-coin
 * 2. Three 5-coins
 * * We ALWAYS prefer option 1 (10+5) over option 2 (5+5+5). 
 * Why? Because 5-coin notes are more versatile; they are required to give change 
 * for 10-coin notes, whereas 10-coin notes can only be used for 20-coin change. 
 * Saving 5s for later is the optimal greedy choice.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We iterate through the array once, performing constant time operations per element.
 * * Space Complexity: O(1)
 * - We only use two integer variables (`five` and `ten`) regardless of input size.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public boolean canServe(int[] arr) {
        int five = 0;
        int ten = 0;

        for (int note : arr) {

            if (note == 5) {
                five++;
            }
            else if (note == 10) {
                if (five == 0) 
                return false;
                
                five--;
                ten++;
            }
            else { 
                // note == 20
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } 
                else if (five >= 3) {
                    five -= 3;
                } 
                else {
                    return false;
                }
            }
        }
        return true;
    }
}


