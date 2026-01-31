/**
 * PROBLEM STATEMENT: 744. Find Smallest Letter Greater Than Target
 * --------------------------------------------------------------------------------
 * You are given an array of characters 'letters' that is sorted in non-decreasing 
 * order, and a character 'target'. 
 * * Task: Return the smallest character in 'letters' that is lexicographically 
 * greater than 'target'. 
 * * Special Rule: If such a character does not exist (i.e., the target is greater 
 * than or equal to the last character in the array), return the first character 
 * in 'letters'.
 * * Example:
 * Input: letters = ["c","f","j"], target = "a"
 * Output: "c"
 * * Input: letters = ["c","f","j"], target = "j"
 * Output: "c" (Since no letter is > 'j', we wrap around to the first element)
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: BINARY SEARCH (O(log N))
 * --------------------------------------------------------------------------------
 * Since the input array is already sorted, we can use Binary Search to find the 
 * "upper bound" of the target.
 * * 1. Initialize 'left' to 0 and 'right' to n-1.
 * 2. Initialize 'ans' to the first element (default wrap-around value).
 * 3. While left <= right:
 * - Calculate mid.
 * - If letters[mid] > target:
 * - This could be our answer, so store it in 'ans'.
 * - Move right = mid - 1 to see if a smaller character > target exists.
 * - Else (letters[mid] <= target):
 * - Move left = mid + 1 to find larger characters.
 * 4. Return 'ans'.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(log N)
 * - The search space is halved in every iteration of the while loop.
 * Space Complexity: O(1)
 * - We only use a constant amount of extra space for variables (left, right, mid).
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        char ans = letters[0];

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (letters[mid] > target) {
                ans = letters[mid];
                right = mid - 1;
            } 
            else {
                left = mid + 1;
            }
        }
        return ans;
    }
}


