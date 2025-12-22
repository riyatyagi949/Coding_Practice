/**
 * PROBLEM STATEMENT: Row with Max 1s
 * ----------------------------------------------------------------------------------
 * You are given a 2D binary array arr[][] consisting of only 1s and 0s. 
 * Each row of the array is sorted in non-decreasing order. 
 * Your task is to find and return the index of the first row that contains 
 * the maximum number of 1s. If no such row exists, return -1.
 *
 * Note: 
 * - The array follows 0-based indexing.
 * - Rows and columns are denoted by n and m respectively.
 *
 * Example 1:
 * Input: arr[][] = [[0,1,1,1], [0,0,1,1], [1,1,1,1], [0,0,0,0]]
 * Output: 2
 *
 * Example 2:
 * Input: arr[][] = [[0,0], [1,1]]
 * Output: 1
 * ----------------------------------------------------------------------------------
 * * OPTIMAL SOLUTION: "Staircase Search" (Top-Right to Bottom-Left)
 * ----------------------------------------------------------------------------------
 * Since each row is sorted, all 0s appear before 1s. Instead of counting 1s in every 
 * row (which would be O(n*m) or O(n*log m)), we can use the "Staircase" approach.
 * 1. Start from the top-right corner of the matrix: row = 0, col = m - 1.
 * 2. If the current element arr[row][col] is 1:
 * - This row could potentially be the one with the maximum 1s found so far.
 * - Update 'ans' to the current 'row'.
 * - Move left (col--) to see if there are even more 1s in this same row.
 * 3. If the current element arr[row][col] is 0:
 * - This means there are no more 1s to the left in this row.
 * - Move down (row++) to check the next row.
 * 4. The process continues until we go out of the matrix bounds.
 * 5. This ensures we find the FIRST row with the max 1s because we only update 
 * the answer when we find a 1 further to the left than any 1 seen previously.
 * ----------------------------------------------------------------------------------
 * * COMPLEXITY ANALYSIS:
 * ----------------------------------------------------------------------------------
 * Time Complexity: O(n + m)
 * - In the worst case, we traverse from the top-right to the bottom-left. 
 * - We move at most 'n' steps down and 'm' steps left.
 *
 * Space Complexity: O(1)
 * - No extra data structures are used.
 * ----------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 
class Solution {
    public int rowWithMax1s(int arr[][]) {
        int n = arr.length;
        if (n == 0) return -1;
        int m = arr[0].length;

        int row = 0;
        int col = m - 1;
        int ans = -1;

        while (row < n && col >= 0)
          {
            if (arr[row][col] == 1)
            {
                ans = row;  
                col--;     
            } 
            else {
                row++;    
            }
        }
       return ans;
    }
}
