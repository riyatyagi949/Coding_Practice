// Problem Statement:
// You are given a 2D integer matrix `grid` of size `n x m`, where each element is either `0`, `1`, or `2`.
// A V-shaped diagonal segment is defined as:
// 1. The segment starts with `1`.
// 2. The subsequent elements follow this infinite sequence: `2, 0, 2, 0, ....`
// 3. The segment: starts along a diagonal direction (top-left to bottom-right, bottom-right to top-left, top-right to bottom-left, or bottom-left to top-right), continues the sequence in the same diagonal direction, and makes at most one clockwise 90-degree turn to another diagonal direction while maintaining the sequence.
// Return the length of the longest V-shaped diagonal segment. If no valid segment exists, return 0.

// Approach:
// This problem can be solved using dynamic programming to find the lengths of all possible straight diagonal segments. We use four DP tables, one for each diagonal direction, to store the lengths of segments ending at each cell.
// A first pass is made to populate these DP tables. For each cell `(i, j)`, the length of a segment ending at this cell is calculated based on the length of the segment at the preceding cell in the same diagonal direction, ensuring the sequence `1, 2, 0, 2, 0...` is maintained. The maximum length found in this pass represents the longest straight diagonal segment.
// A second pass is then performed to check for V-shaped segments. For each cell `(i, j)`, we consider it a potential turn point. The length of a V-shaped segment is the sum of the lengths of the two legs. The first leg is a straight segment ending at `(i, j)`, and the second leg is a segment continuing from `(i, j)` after a clockwise 90-degree turn. We iterate through all possible directions for the first leg, and for each, we traverse the grid in the new clockwise-turned direction to find the length of the second leg, making sure the sequence is maintained. The maximum length found across both straight and V-shaped segments is the final answer.

// Time Complexity: O(n * m)
// The algorithm involves a constant number of passes over the `n x m` grid to precompute DP tables and then to check for V-shaped segments. Each pass takes O(n * m) time.

// Space Complexity: O(n * m)
// We use four 2D DP tables of size `n x m` to store the lengths of the straight diagonal segments.

// Optimal solution -
class Solution {
  public int lenOfVDiagonal(int[][] grid) {
    final int m = grid.length;
    final int n = grid[0].length;
    Integer[][][][][] mem = new Integer[m][n][2][2][4];

    int ans = 0;

    for (int i = 0; i < m; ++i)
      for (int j = 0; j < n; ++j)
        if (grid[i][j] == 1)
          for (int d = 0; d < 4; ++d) {
            final int dx = DIRS[d][0];
            final int dy = DIRS[d][1];
            ans = Math.max(ans, 1 + dfs(grid, i + dx, j + dy, false, 2, d, mem));
          }

    return ans;
  }

  private static final int[][] DIRS = {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};

  private int dfs(int[][] grid, int i, int j, boolean turned, int num, int dir,
                  Integer[][][][][] mem) {
    if (i < 0 || i == grid.length || j < 0 || j == grid[0].length)
      return 0;
    if (grid[i][j] != num)
      return 0;

    final int hashNum = Math.max(0, num - 1);
    if (mem[i][j][turned ? 1 : 0][hashNum][dir] != null)
      return mem[i][j][turned ? 1 : 0][hashNum][dir];

    final int nextNum = num == 2 ? 0 : 2;
    final int dx = DIRS[dir][0], dy = DIRS[dir][1];
    int res = 1 + dfs(grid, i + dx, j + dy, turned, nextNum, dir, mem);

    if (!turned) {
      final int nextDir = (dir + 1) % 4;
      final int nextDx = DIRS[nextDir][0], nextDy = DIRS[nextDir][1];
      res = Math.max(res,
                     1 + dfs(grid, i + nextDx, j + nextDy, true, nextNum, nextDir, mem));
    }

    return mem[i][j][turned ? 1 : 0][hashNum][dir] = res;
  }
}
