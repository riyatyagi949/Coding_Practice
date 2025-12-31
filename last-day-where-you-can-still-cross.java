/**
 * PROBLEM STATEMENT: 1970. Last Day Where You Can Still Cross
 * --------------------------------------------------------------------------------
 * You are given a 1-based binary matrix of size row x col, where 0 is land and 1 
 * is water. Initially (Day 0), all cells are land. Each day, one cell becomes water.
 * You are given an array 'cells' where cells[i] = [ri, ci] is the cell that 
 * becomes water on day i+1.
 * * Goal: Find the last day you can still walk from the top row to the bottom row 
 * using only land cells (moving Up, Down, Left, Right).
 * * Example:
 * Input: row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
 * Output: 2
 * Explanation: On day 2, cells [1,1] and [2,1] are water. A path exists through 
 * [1,2] and [2,2]. On day 3, [1,2] also becomes water, blocking the path.
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: BINARY SEARCH ON ANSWER + BFS
 * --------------------------------------------------------------------------------
 * The problem exhibits a monotonic property: if you can cross on day 'D', you 
 * can also cross on any day before 'D'. This allows us to binary search for the 
 * maximum day.
 * * 1. Binary Search Range: [1, row * col].
 * 2. check(day): 
 * - Mark all cells in 'cells' from index 0 to day-1 as water.
 * - Use BFS (or DFS) starting from all land cells in the top row.
 * - If any cell in the bottom row is reached, return true.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity: O(log(N) * (R * C))
 * - N = row * col (total days). Binary search takes log(N) steps.
 * - Each check() performs a BFS over the grid, taking O(R * C) time.
 * Space Complexity: O(R * C)
 * - Space needed for the water grid and the visited array in BFS.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java - 

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    public int latestDayToCross(int row, int col, int[][] cells) {
        int low = 0, high = cells.length, ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canCross(row, col, cells, mid)) {
                ans = mid;
                low = mid + 1;
            }
             else {
                high = mid - 1;
            }
        }
        return ans;
    }
    private boolean canCross(int row, int col, int[][] cells, int day) {
        boolean[][] water = new boolean[row][col];

        for (int i = 0; i < day; i++) {
            water[cells[i][0] - 1][cells[i][1] - 1] = true;
        }
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];

        for (int c = 0; c < col; c++) {
            if (!water[0][c]) {
                q.offer(new int[]{0, c});
                visited[0][c] = true;
            }
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            if (r == row - 1) 
            return true;

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < row && nc >= 0 && nc < col && !water[nr][nc] && !visited[nr][nc]) 
                {
                 visited[nr][nc] = true;
                 q.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}
