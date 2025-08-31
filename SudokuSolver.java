// Problem Statement:
// Write a program to solve a Sudoku puzzle by filling the empty cells.
// A sudoku solution must satisfy all of the following rules:
// 1. Each of the digits 1-9 must occur exactly once in each row.
// 2. Each of the digits 1-9 must occur exactly once in each column.
// 3. Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
// The '.' character indicates empty cells.
// It is guaranteed that the input board has only one solution.

// Approach:
// This problem can be solved using backtracking. The idea is to recursively try placing numbers from 1 to 9 in the empty cells (represented by '.') and check if the placement is valid.
// 1. Iterate through the board from top-left to bottom-right.
// 2. Find the first empty cell ('.').
// 3. For this empty cell, try placing a digit from '1' to '9'.
// 4. Before placing a digit, check if it's a valid move. A move is valid if the digit doesn't already exist in the same row, same column, or same 3x3 sub-box.
// 5. If the digit is valid, place it on the board and recursively call the solving function for the next empty cell.
// 6. If the recursive call returns true (meaning a solution was found), then we've successfully filled the board, and we can return true.
// 7. If the recursive call returns false (meaning the current path didn't lead to a solution), we need to backtrack. We'll remove the digit we just placed (by setting the cell back to '.') and try the next digit.
// 8. If we've tried all digits from '1' to '9' for the current cell and none of them lead to a solution, it means our previous choices were wrong. In this case, we return false to trigger backtracking in the previous recursive call.
// 9. If the entire board is filled without any conflicts, we have found the solution.

// Time Complexity:
// The time complexity is difficult to calculate precisely due to the nature of backtracking. In the worst case, for each empty cell, we might try up to 9 different digits. This leads to a complexity that is exponential with respect to the number of empty cells, roughly O(9^k) where k is the number of empty cells. A tighter upper bound is closer to O(9^(n*n)) for a n x n grid, but in practice, the pruning by the validity checks makes it much faster.

// Space Complexity:
// The space complexity is O(1) if we don't consider the input board. The space used by the recursion stack is at most O(n*n) where n is the side length of the grid (n=9), as we make a recursive call for each cell. Therefore, the space complexity is O(n^2).

class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    private boolean solve(char[][] board) {
    for (int i = 0; i < 9; i++)
     {
         for (int j = 0; j < 9; j++)
          {
           if (board[i][j] == '.') 
           {
         for (char c = '1'; c <= '9'; c++)
          {
             if (isValid(board, i, j, c))
              {
                 board[i][j] = c;
                 if (solve(board))
                 {
                    return true;
                            } 
                else {
                    board[i][j] = '.'; 
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) 
        {
            if (board[i][col] == c)
             return false;

            if (board[row][i] == c) 
            return false;
            
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
             return false;
        }
        return true;
    }
}