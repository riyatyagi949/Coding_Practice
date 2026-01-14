/**
 * PROBLEM STATEMENT: Police and Thieves
 * --------------------------------------------------------------------------------
 * Given an array arr[] where each element is either 'P' (Policeman) or 'T' (Thief).
 * Find the maximum number of thieves that can be caught subject to:
 * 1. Each policeman can catch only one thief.
 * 2. A policeman cannot catch a thief who is more than k units away (index difference <= k).
 *
 * Example 1:
 * Input: arr[] = ['P', 'T', 'T', 'P', 'T'], k = 1 -> Output: 2
 * Explanation: The first P catches the first T. The second P catches either the 
 * second or third T. Total = 2.
 *
 * Example 2:
 * Input: arr[] = ['T', 'T', 'P', 'P', 'T', 'P'], k = 2 -> Output: 3
 *
 * Constraints:
 * - 1 <= arr.size() <= 10^5
 * - 1 <= k <= 1000
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: GREEDY WITH TWO POINTERS
 * --------------------------------------------------------------------------------
 * We use a greedy strategy to ensure that each policeman catches the "earliest" 
 * possible thief within their reach. This leaves thieves further down the line 
 * available for policemen further down the line.
 *
 * 1. Store all indices of 'P' in one list and all indices of 'T' in another.
 * 2. Use two pointers (i for police, j for thieves) to iterate through the lists.
 * 3. If the distance |police[i] - thieves[j]| <= k:
 * - A catch is made. Increment count, increment both i and j.
 * 4. If police[i] < thieves[j] and the distance is > k:
 * - This policeman is too far behind the current thief. He can never catch 
 * this thief or any subsequent thieves. Increment i.
 * 5. If thieves[j] < police[i] and the distance is > k:
 * - This thief is too far behind the current policeman. No subsequent 
 * policeman can reach this thief. Increment j.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - We iterate through the array once to build the index lists: O(N).
 * - The two-pointer while loop traverses each index at most once: O(N).
 * * Space Complexity: O(N)
 * - We store the indices of police and thieves in separate lists, which in total 
 * store N elements.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int catchThieves(char[] arr, int k) {
        List<Integer> police = new ArrayList<>();
        List<Integer> thieves = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 'P') police.add(i);
            else thieves.add(i);
        }
        int i = 0, j = 0, count = 0;

        while(i < police.size() && j < thieves.size()){
            if(Math.abs(police.get(i) - thieves.get(j)) <= k){
                count++;
                i++;
                j++;
            }
            else if(police.get(i) < thieves.get(j)){
                i++;
            }
            else{
                j++;
            }
        }
        return count;
    }
}


