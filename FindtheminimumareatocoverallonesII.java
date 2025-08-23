// Problem Statement:
// You are given a 2D binary array grid. You need to find 3 non-overlapping rectangles having non-zero areas with horizontal and vertical sides such that all the 1's in grid lie inside these rectangles.
// Return the minimum possible sum of the area of these rectangles.
// Note that the rectangles are allowed to touch.

// Approach:
// The problem asks for the minimum total area of three non-overlapping rectangles that cover all the '1's in a grid.
// A brute-force approach would involve trying all possible ways to partition the grid into three regions, each covered by a rectangle. However, this is too complex.
// A better approach is to consider the ways to split the grid into three parts. The splits can be horizontal or vertical.
// There are a limited number of ways to split the grid into three parts with two straight lines.
// We can categorize the splits into a few main cases:
// 1. Two vertical lines: The grid is split into three vertical strips.
// 2. Two horizontal lines: The grid is split into three horizontal strips.
// 3. One vertical and one horizontal line: This can be done in two ways.
//    a) A vertical split followed by a horizontal split of one of the resulting parts.
//    b) A horizontal split followed by a vertical split of one of the resulting parts.
//
// We can iterate through all possible split lines.
// Let the dimensions of the grid be `m x n`.
// For each case, we can calculate the area of the three rectangles required to cover all the '1's within each partition.
// The total minimum area is the minimum of the areas calculated for all these cases.
//
// Case 1: Three vertical rectangles.
// We can iterate through all possible pairs of vertical split lines.
// Let the split columns be `c1` and `c2`.
// The three rectangles will cover columns `0..c1`, `c1+1..c2`, and `c2+1..n-1`.
// For each rectangle, we find the bounding box of the '1's within its column range to calculate its minimum area.
//
// Case 2: Three horizontal rectangles.
// Similar to case 1, but with horizontal split lines.
// Iterate through all pairs of horizontal split lines `r1` and `r2`.
// The rectangles cover rows `0..r1`, `r1+1..r2`, and `r2+1..m-1`.
//
// Case 3: A vertical split and a horizontal split.
// We iterate through all possible vertical split lines `c`.
// This divides the grid into two parts: left and right.
// Then, for each part (left and right), we calculate the minimum area to cover its '1's with two rectangles.
// This can be done by considering all possible horizontal split lines within that part.
// The total area is the sum of the minimum area for the left part (1 rectangle) and the minimum area for the right part (2 rectangles, split horizontally), or vice-versa.
//
// A more efficient way to handle Case 3 is to iterate through one split line (say, vertical `c`) and then for the left part (cols `0..c`) and right part (cols `c+1..n-1`), find the minimum area to cover their '1's with one and two rectangles respectively.
// The problem then boils down to a subproblem: finding the minimum area to cover all '1's in a subgrid with two non-overlapping rectangles. This can be precomputed or calculated on the fly.
//
// The `minArea1(grid)` function can calculate the minimum area of a single rectangle to cover all '1's.
// The `minArea2(grid)` function can be implemented to find the minimum area for two rectangles, which is a subproblem for Case 3. It can consider all horizontal and vertical splits of the subgrid.
//
// To optimize, we can first find the bounding box of all '1's in the entire grid. This effectively reduces the search space.
//
// The overall algorithm:
// 1. Find the bounding box of all '1's in the grid to reduce the dimensions.
// 2. Initialize a minimum area `minArea` to infinity.
// 3. Iterate through all possible horizontal and vertical split configurations:
//    a) Two horizontal splits (3 horizontal rectangles).
//    b) Two vertical splits (3 vertical rectangles).
//    c) One horizontal split followed by a vertical split of one of the resulting two parts.
//    d) One vertical split followed by a horizontal split of one of the resulting two parts.
// 4. For each configuration, calculate the total area of the three rectangles and update `minArea`.
// 5. Return `minArea`.
//
// To implement this, helper functions for calculating the minimum area for 1 and 2 rectangles covering '1's in a given subgrid are useful.
// `calculateArea1(grid, r1, c1, r2, c2)`: computes the area of a single rectangle covering all '1's in the subgrid.
// `calculateArea2(grid, r1, c1, r2, c2)`: computes the minimum area of two rectangles covering all '1's in the subgrid. This function will itself consider all horizontal and vertical splits of the subgrid.
//
// The constraints are small (30x30), so a solution involving iterating through all split points should be efficient enough.

// Time Complexity:
// The time complexity will be dominated by the nested loops for iterating through split lines.
// For two vertical splits, we have `O(n^2)` pairs of split lines. For each, we might iterate through rows `O(m)`. This would be `O(m * n^2)`.
// Similarly for two horizontal splits, `O(n * m^2)`.
// For the mixed splits, we have one split line (e.g., vertical) `O(n)`, and then for each part, we need to find the minimum area for one and two rectangles. The two-rectangle subproblem takes `O(m * n)` time. Total `O(n * m * n) = O(m * n^2)`.
// Overall, the time complexity is roughly `O(m * n^2 + n * m^2)`, which is efficient for `m, n <= 30`.

// Space Complexity:
// The space complexity is `O(1)` besides the input grid, as we are only using a few variables to store split points and areas.

class Solution {
    int[][] grid;
    int m, n;
    int[][] onesBounds;

    public int minimumArea(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        
        onesBounds = new int[m][n];
        
        int minArea = Integer.MAX_VALUE;

        // Case 1: Two vertical splits
        for (int c1 = 0; c1 < n - 1; c1++) {
            for (int c2 = c1 + 1; c2 < n - 1; c2++) {
                int area1 = getMinArea(0, 0, m - 1, c1);
                int area2 = getMinArea(0, c1 + 1, m - 1, c2);
                int area3 = getMinArea(0, c2 + 1, m - 1, n - 1);
                if (area1 != -1 && area2 != -1 && area3 != -1) {
                    minArea = Math.min(minArea, area1 + area2 + area3);
                }
            }
        }

        // Case 2: Two horizontal splits
        for (int r1 = 0; r1 < m - 1; r1++) {
            for (int r2 = r1 + 1; r2 < m - 1; r2++) {
                int area1 = getMinArea(0, 0, r1, n - 1);
                int area2 = getMinArea(r1 + 1, 0, r2, n - 1);
                int area3 = getMinArea(r2 + 1, 0, m - 1, n - 1);
                if (area1 != -1 && area2 != -1 && area3 != -1) {
                    minArea = Math.min(minArea, area1 + area2 + area3);
                }
            }
        }
        
        // Case 3: One vertical split, one of the two parts split horizontally
        for (int c = 0; c < n - 1; c++) {
            int areaLeft = getMinArea(0, 0, m - 1, c);
            int areaRight2 = getMinArea2(0, c + 1, m - 1, n - 1);
            if (areaLeft != -1 && areaRight2 != -1) {
                minArea = Math.min(minArea, areaLeft + areaRight2);
            }
            int areaRight = getMinArea(0, c + 1, m - 1, n - 1);
            int areaLeft2 = getMinArea2(0, 0, m - 1, c);
            if (areaRight != -1 && areaLeft2 != -1) {
                minArea = Math.min(minArea, areaRight + areaLeft2);
            }
        }
        
        // Case 4: One horizontal split, one of the two parts split vertically
        for (int r = 0; r < m - 1; r++) {
            int areaTop = getMinArea(0, 0, r, n - 1);
            int areaBottom2 = getMinArea2(r + 1, 0, m - 1, n - 1);
            if (areaTop != -1 && areaBottom2 != -1) {
                minArea = Math.min(minArea, areaTop + areaBottom2);
            }
            int areaBottom = getMinArea(r + 1, 0, m - 1, n - 1);
            int areaTop2 = getMinArea2(0, 0, r, n - 1);
            if (areaBottom != -1 && areaTop2 != -1) {
                minArea = Math.min(minArea, areaBottom + areaTop2);
            }
        }
        
        return minArea;
    }

    private int getMinArea(int r1, int c1, int r2, int c2) {
        int top = m, bottom = -1, left = n, right = -1;
        boolean hasOne = false;
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (grid[i][j] == 1) {
                    hasOne = true;
                    top = Math.min(top, i);
                    bottom = Math.max(bottom, i);
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                }
            }
        }
        if (!hasOne) return -1;
        return (bottom - top + 1) * (right - left + 1);
    }
    
    private int getMinArea2(int r1, int c1, int r2, int c2) {
        int minArea = Integer.MAX_VALUE;
        
        // Horizontal split
        for (int r = r1; r < r2; r++) {
            int area1 = getMinArea(r1, c1, r, c2);
            int area2 = getMinArea(r + 1, c1, r2, c2);
            if (area1 != -1 && area2 != -1) {
                minArea = Math.min(minArea, area1 + area2);
            }
        }
        
        // Vertical split
        for (int c = c1; c < c2; c++) {
            int area1 = getMinArea(r1, c1, r2, c);
            int area2 = getMinArea(r1, c + 1, r2, c2);
            if (area1 != -1 && area2 != -1) {
                minArea = Math.min(minArea, area1 + area2);
            }
        }
        
        return minArea;
    }
}