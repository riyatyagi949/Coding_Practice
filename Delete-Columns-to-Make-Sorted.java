/*
 * PROBLEM STATEMENT: 944. Delete Columns to Make Sorted
 * -----------------------------------------------------------------------------------
 * You are given an array of n strings 'strs', all of the same length.
 * * The strings can be arranged such that there is one on each line, making a grid.
 * For example, strs = ["abc", "bce", "cae"] can be arranged as follows:
 * abc
 * bce
 * cae
 * * You want to delete the columns that are not sorted lexicographically. 
 * In the example above:
 * - Column 0: 'a', 'b', 'c' is sorted.
 * - Column 1: 'b', 'c', 'a' is NOT sorted (c > a).
 * - Column 2: 'c', 'e', 'e' is sorted.
 * * Return the number of columns that you will delete.
 * -----------------------------------------------------------------------------------
 */
/**
     * OPTIMAL SOLUTION:
     * We treat the array of strings as a 2D grid where strs[i] is the i-th row.
     * To check if a column is sorted, we iterate through each column index 'j' 
     * and compare characters at row 'i' and 'i+1'.
     */

// Optimal Code - 
class Solution{
    public int minDeletionSize(String[] strs) {
        int count = 0;
        int rows = strs.length;
        int cols = strs[0].length();

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows - 1; i++) {
                if (strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}

/*
 * COMPLEXITY ANALYSIS:
 * -----------------------------------------------------------------------------------
 * TIME COMPLEXITY: O(N * L)
 * - N is the number of strings in the array (strs.length).
 * - L is the length of each string (strs[0].length()).
 * - We potentially visit every character in the grid once.
 * * SPACE COMPLEXITY: O(1)
 * - We only use a few integer variables (count, rows, cols) to track our progress.
 * - No extra data structures are used that scale with input size.
 * -----------------------------------------------------------------------------------
 */
