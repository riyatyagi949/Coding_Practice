// Valid Sudoku
// Problem Statement: Determine if a 9x9 Sudoku board is valid. Only filled cells need to be validated according to the following rules:

// Each row must contain the digits 1-9 without repetition.

// Each column must contain the digits 1-9 without repetition.

// Each of the nine 3x3 sub-boxes must contain the digits 1-9 without repetition.

// Approach:
// A single pass through the 9x9 board is sufficient to check for validity. We can use three arrays of hash sets to keep track of the numbers seen in each row, column, and 3x3 sub-box.

// Initialize three arrays of HashSet<Character>: rows, cols, and boxes, all of size 9.

// Iterate through the board using nested loops for row and col from 0 to 8.

// For each cell (row, col), if the character c is not a '.', meaning it's a filled cell:

// Check for duplicates in the current row: Try to add c to rows[row]. If it fails (because c is already in the set), the board is invalid.

// Check for duplicates in the current column: Try to add c to cols[col]. If it fails, the board is invalid.

// Check for duplicates in the corresponding 3x3 sub-box: The index of the sub-box can be calculated as (row / 3) * 3 + (col / 3). Try to add c to boxes[boxIndex]. If it fails, the board is invalid.

// If the loop completes without finding any duplicates, the board is valid, and you can return true.

// This approach is efficient because adding and checking for existence in a hash set takes an average of O(1) time.

// Time Complexity: O(N^2), where N is the size of the board (9). We iterate through each cell of the 9x9 grid exactly once.

// Space Complexity: O(N^2). We use three arrays of hash sets, each of which can potentially store up to 9 elements. Therefore, the space required is proportional to the number of cells.

// Optimal Solution:

// Java

// class Solution {
//     public boolean isValidSudoku(char[][] board) {
//         HashSet<Character>[] rows = new HashSet[9];
//         HashSet<Character>[] cols = new HashSet[9];
//         HashSet<Character>[] boxes = new HashSet[9];
        
//         for (int i = 0; i < 9; i++) {
//             rows[i] = new HashSet<Character>();
//             cols[i] = new HashSet<Character>();
//             boxes[i] = new HashSet<Character>();
//         }
        
//         for (int r = 0; r < 9; r++) {
//             for (int c = 0; c < 9; c++) {
//                 char current = board[r][c];
                
//                 if (current == '.') {
//                     continue;
//                 }
                
//                 // Calculate the index for the 3x3 sub-box
//                 int boxIndex = (r / 3) * 3 + (c / 3);
                
//                 if (!rows[r].add(current) || !cols[c].add(current) || !boxes[boxIndex].add(current)) {
//                     return false;
//                 }
//             }
//         }
        
//         return true;
//     }
// }
