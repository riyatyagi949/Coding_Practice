/**
 * PROBLEM STATEMENT: 66. Plus One
 * --------------------------------------------------------------------------------
 * You are given a large integer represented as an integer array 'digits', where 
 * each digits[i] is the ith digit of the integer. The digits are ordered from 
 * most significant to least significant in left-to-right order. The large 
 * integer does not contain any leading 0's.
 * * Increment the large integer by one and return the resulting array of digits.
 * * Example 1:
 * Input: digits = [1,2,3] -> Output: [1,2,4]
 * * Example 2:
 * Input: digits = [9,9,9] -> Output: [1,0,0,0]
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: SINGLE PASS REVERSE ITERATION
 * --------------------------------------------------------------------------------
 * The core logic follows standard addition rules, specifically handling the carry.
 * 1. Start from the last digit (least significant).
 * 2. If the digit is less than 9:
 * - Simply increment it by 1 and return the array. We are done because there 
 * is no carry to propagate further.
 * 3. If the digit is 9:
 * - Change it to 0. This creates a carry of 1 that must be added to the next 
 * digit to the left.
 * 4. Corner Case (All digits are 9):
 * - If the loop finishes, it means every digit was a 9 and has been turned to 0.
 * - We need an extra space for the leading 1 (e.g., 999 -> 1000).
 * - Create a new array of size n+1, set the first element to 1, and return it.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(N)
 * - In the worst case (e.g., [9,9,9]), we traverse the entire array once.
 * Space Complexity: O(N)
 * - Usually O(1) if we modify in place, but O(N) in the worst case when we must 
 * allocate a new array of size N+1 to handle the carry.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java -
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) 
        {
         if (digits[i] < 9)
         {
          digits[i]++;  
         return digits;
            }
            digits[i] = 0;        
        }
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}

