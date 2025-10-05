/**
 * Problem Statement:
 * Given an n x n maze represented by a matrix where 1 means a free cell and 0 means a blocked cell.
 * A rat starts at (0, 0) and needs to reach the destination (n-1, n-1).
 * The rat can move 'U' (Up), 'D' (Down), 'L' (Left), or 'R' (Right).
 * Find all unique paths from start to end such that the rat does not revisit any cell in the same path.
 * Return the list of paths in lexicographically smallest order. If no path exists, return an empty list.
 * Constraints: 2 <= n <= 5.
 *
 * Optimal Approach (Backtracking/Depth First Search):
 * Since we need to find all possible paths and the size of the maze (n <= 5) is very small, a recursive backtracking approach (Depth First Search) is suitable.
 *
 * The Core Idea:
 * 1. Start at (0, 0).
 * 2. At the current cell (row, col), explore all four possible directions ('D', 'L', 'R', 'U') in a specific order ('D', 'L', 'R', 'U') to naturally generate the paths in lexicographically sorted order.
 * 3. Before moving to a neighbor cell, check if the move is valid:
 * a. The new cell is within the maze boundaries (0 <= newRow, newCol < n).
 * b. The new cell is a free cell (maze[newRow][newCol] == 1).
 * c. The new cell has not been visited in the current path (to prevent cycles and ensure no revisit).
 * 4. If the move is valid:
 * a. Mark the current cell as visited.
 * b. Append the direction ('D', 'L', 'R', or 'U') to the current path string.
 * c. Recurse to the new cell.
 * d. Backtrack: Remove the last direction from the path string and unmark the current cell as visited to explore other paths.
 * 5. Base Case: If the rat reaches the destination (n-1, n-1), add the current path string to the results list.
 * 6. Initial Check: If the starting cell maze[0][0] is blocked (0), no path is possible, and an empty list should be returned.
 *
 * Visited Tracking:
 * To ensure no cell is revisited in the same path, we use a separate `visited` 2D array (or modify the `maze` array temporarily) to keep track of cells used in the current recursive call stack.
 *
 * Direction Order:
 * Moving in the order **D, L, R, U** guarantees that the paths are generated in **lexicographically smallest order** ('D' before 'L', 'L' before 'R', 'R' before 'U').
 *
 * Time Complexity: O(4^(n^2)). In the worst case, from every cell, we explore 4 directions, and there are n^2 cells. This is an exponential complexity, which is acceptable given the small constraint n <= 5 ($5^2 = 25$ cells).
 * Space Complexity: O(n^2) to store the `visited` array and O(n^2) for the recursion stack depth (since the maximum path length is $n^2$). O(L*P) where L is max path length and P is number of paths, for storing the result list.
 */
// Optimal Solution in Java - 

import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public ArrayList<String> ratInMaze(int[][] maze) {
        ArrayList<String> result = new ArrayList<>();
        int n = maze.length;
        
        if (maze[0][0] == 0 || maze[n - 1][n - 1] == 0) {
            return result;
        }
        
        boolean[][] visited = new boolean[n][n];
        dfs(0, 0, maze, visited, "", result, n);
        Collections.sort(result);
        return result;
    }
    
    private void dfs(int i, int j, int[][] maze, boolean[][] visited, String path, ArrayList<String> result, int n) {
        if (i == n - 1 && j == n - 1) {
            result.add(path);
            return;
        }
        
        visited[i][j] = true;
        
        int[] di = {1, 0, 0, -1};
        int[] dj = {0, -1, 1, 0};
        char[] dir = {'D', 'L', 'R', 'U'};
        
        for (int k = 0; k < 4; k++) {
            int nextI = i + di[k];
            int nextJ = j + dj[k];
            
            if (isSafe(nextI, nextJ, maze, visited, n)) {
                dfs(nextI, nextJ, maze, visited, path + dir[k], result, n);
            }
        }
        
        visited[i][j] = false; 
    }
    
    private boolean isSafe(int i, int j, int[][] maze, boolean[][] visited, int n) {
        return i >= 0 && j >= 0 && i < n && j < n && maze[i][j] == 1 && !visited[i][j];
    }
}
