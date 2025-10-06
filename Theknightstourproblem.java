/**
 * Problem Statement:
 * Given an n x n chessboard with a Knight starting at (0, 0), find a valid Knight's Tour.
 * A Knight's Tour is a sequence of moves where the Knight visits every square exactly once.
 * The Knight follows the standard L-shaped movement rules of chess.
 * Return an n x n grid representing the order in which each cell is visited (starting with 0 at (0, 0)).
 * If no solution exists, the function should indicate this (e.g., by returning a specific value or structure).
 * Constraints: 1 <= n <= 6.
 *
 * Optimal Approach (Backtracking):
 * Given the small constraint on 'n' (up to 6), a standard **Backtracking** algorithm (a variation of Depth First Search - DFS) is the optimal approach for finding *a* solution.
 *
 * 1. State Space: The state is represented by the current position (x, y) on the board and the current move number (step).
 * 2. Board Representation: An n x n 2D array, let's call it `board`, is used to store the move number at which each cell is visited. Initially, all cells are marked as unvisited (-1).
 * 3. Knight's Moves: The 8 possible moves of a Knight are pre-defined as arrays `dx` and `dy`.
 * 4. Backtracking Function: The recursive function, typically `solveTour(x, y, step)`, attempts to place the current `step` at `(x, y)`.
 * - Base Case: If `step` reaches `n * n - 1` (meaning all $n^2$ squares have been visited), a solution is found.
 * - Recursive Step:
 * a. Try all 8 possible Knight moves from `(x, y)` to a new position `(nx, ny)`.
 * b. Check if the new position `(nx, ny)` is **safe** (within board boundaries and unvisited, i.e., `board[nx][ny] == -1`).
 * c. If safe, make the move: set `board[nx][ny] = step + 1` and recursively call `solveTour(nx, ny, step + 1)`.
 * d. If the recursive call returns `true` (a solution was found), return `true`.
 * e. **Backtrack**: If the recursive call returns `false`, undo the move by resetting `board[nx][ny] = -1` (unvisit the square) and try the next move.
 * 5. Initialization: Start the tour at `(0, 0)` with `step = 0`.
 *
 * Note on Warnsdorff's Rule: For larger boards (n > 6), a heuristic approach like **Warnsdorff's Rule** (prioritizing moves to squares with the minimum number of unvisited neighbors) is used to find a solution more quickly. However, for $n \le 6$, the standard backtracking is acceptable and often required to guarantee finding a solution (or proving non-existence).
 *
 * Time Complexity: The worst-case complexity of simple backtracking is exponential, $O(8^{n^2})$, as in the worst case, it explores every path. However, due to pruning and the small constant factor for $n \le 6$, it's fast enough in practice. For the specific small constraints $1 \le n \le 6$, the execution time is very small.
 * Space Complexity: O(n^2) to store the $n \times n$ chessboard/solution grid.
 */
// Optimal Solution in Java - 
import java.util.*;

class Solution {
    private static final int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public ArrayList<ArrayList<Integer>> knightTour(int n) 
    {
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();
        
        for (int i = 0; i < n; i++)
        {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++)
            row.add(-1);
            board.add(row);
        }

        board.get(0).set(0, 0);

        if (solve(board, 0, 0, 1, n)) 
        return board;

        ArrayList<ArrayList<Integer>> noSolution = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(-1);
        noSolution.add(temp);
        return noSolution;
    }

    private boolean solve(ArrayList<ArrayList<Integer>> board, int x, int y, int move, int n) {
        if (move == n * n) 
        return true;

        for (int k = 0; k < 8; k++)
        {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (isSafe(nx, ny, board, n))
            {
                board.get(nx).set(ny, move);
                if (solve(board, nx, ny, move + 1, n))
                return true;
                board.get(nx).set(ny, -1);
            }
        }
        return false;
    }
    private boolean isSafe(int x, int y, ArrayList<ArrayList<Integer>> board, int n)
    {
        return (x >= 0 && y >= 0 && x < n && y < n && board.get(x).get(y) == -1);
    }
}
