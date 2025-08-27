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

class Solution {
    public int longestVShapedDiagonalSegment(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int maxLength = 0;

        int[][] dpEndUL = new int[n][m];
        int[][] dpEndUR = new int[n][m];
        int[][] dpEndDL = new int[n][m];
        int[][] dpEndDR = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dpEndUL[i][j] = 1;
                } else if (i > 0 && j > 0 && dpEndUL[i - 1][j - 1] > 0) {
                    int prevLen = dpEndUL[i - 1][j - 1];
                    int expectedVal = (prevLen % 2 == 1) ? 2 : 0;
                    if (grid[i][j] == expectedVal) {
                        dpEndUL[i][j] = prevLen + 1;
                    }
                }
                maxLength = Math.max(maxLength, dpEndUL[i][j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    dpEndUR[i][j] = 1;
                } else if (i > 0 && j < m - 1 && dpEndUR[i - 1][j + 1] > 0) {
                    int prevLen = dpEndUR[i - 1][j + 1];
                    int expectedVal = (prevLen % 2 == 1) ? 2 : 0;
                    if (grid[i][j] == expectedVal) {
                        dpEndUR[i][j] = prevLen + 1;
                    }
                }
                maxLength = Math.max(maxLength, dpEndUR[i][j]);
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dpEndDL[i][j] = 1;
                } else if (i < n - 1 && j > 0 && dpEndDL[i + 1][j - 1] > 0) {
                    int prevLen = dpEndDL[i + 1][j - 1];
                    int expectedVal = (prevLen % 2 == 1) ? 2 : 0;
                    if (grid[i][j] == expectedVal) {
                        dpEndDL[i][j] = prevLen + 1;
                    }
                }
                maxLength = Math.max(maxLength, dpEndDL[i][j]);
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    dpEndDR[i][j] = 1;
                } else if (i < n - 1 && j < m - 1 && dpEndDR[i + 1][j + 1] > 0) {
                    int prevLen = dpEndDR[i + 1][j + 1];
                    int expectedVal = (prevLen % 2 == 1) ? 2 : 0;
                    if (grid[i][j] == expectedVal) {
                        dpEndDR[i][j] = prevLen + 1;
                    }
                }
                maxLength = Math.max(maxLength, dpEndDR[i][j]);
            }
        }

        int[][] dx = { {1, 1}, {1, -1}, {-1, -1}, {-1, 1} };
        int[][] dpEnd[] = {dpEndUL, dpEndUR, dpEndDR, dpEndDL};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 4; d++) {
                    int len1 = dpEnd[d][i][j];
                    if (len1 > 0) {
                        int turnDir = (d + 1) % 4;
                        int curX = i + dx[turnDir][0];
                        int curY = j + dx[turnDir][1];
                        int len2 = 0;
                        int currentLenTotal = len1;
                        
                        while (curX >= 0 && curX < n && curY >= 0 && curY < m) {
                            currentLenTotal++;
                            int expectedVal = (currentLenTotal % 2 == 0) ? 2 : 0;
                            if (grid[curX][curY] == expectedVal) {
                                len2++;
                                curX += dx[turnDir][0];
                                curY += dx[turnDir][1];
                            } else {
                                break;
                            }
                        }
                        if (len2 > 0) {
                            maxLength = Math.max(maxLength, len1 + len2);
                        }
                    }
                }
            }
        }
        
        return maxLength;
    }
}