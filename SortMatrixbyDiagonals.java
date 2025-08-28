/**
 * Problem Statement:
 * You are given an n x n square matrix of integers grid. Return the matrix such that:
 * 1. The diagonals in the bottom-left triangle (including the middle diagonal) are sorted in non-increasing order.
 * 2. The diagonals in the top-right triangle are sorted in non-decreasing order.
 *
 * Approach:
 * The problem can be solved by identifying the diagonals and sorting them according to the specified rules.
 * The diagonals in a square matrix can be uniquely identified by the sum or difference of their indices.
 *
 * For a matrix element at grid[i][j]:
 * - Diagonals in the bottom-left triangle (including the main diagonal) have i + j >= n - 1.
 * - Diagonals in the top-right triangle have i + j < n - 1.
 * - Diagonals with a constant sum of indices (i + j) are the anti-diagonals. Sorting these is straightforward.
 * - Diagonals with a constant difference of indices (i - j) are the main diagonals.
 *
 * However, a simpler approach is to iterate through all possible diagonals. A diagonal is defined by its starting point.
 *
 * For the bottom-left triangle and main diagonal:
 * The diagonals start from the last row (i = n-1) and columns from 0 to n-1, and from the last column (j = n-1) and rows from n-2 down to 0.
 *
 * For the top-right triangle:
 * The diagonals start from the first row (i = 0) and columns from 1 to n-1.
 *
 * A more elegant approach:
 * 1. Iterate through the matrix to extract elements of each diagonal.
 * 2. For each diagonal, store its elements in a temporary list.
 * 3. Sort the list according to the specified order (non-increasing for bottom-left, non-decreasing for top-right).
 * 4. Place the sorted elements back into the original matrix.
 *
 * To implement this, we can use a map to group elements by their diagonal.
 * - For diagonals from top-left to bottom-right (i - j is constant):
 * - The main diagonal has i - j = 0.
 * - The diagonals in the top-right have i - j > 0.
 * - The diagonals in the bottom-left have i - j < 0.
 * * - The problem statement seems to refer to anti-diagonals (i + j is constant), but the example shows sorting main diagonals. Let's re-examine the example.
 * - Example 1: `grid = [[1,7,3],[9,8,2],[4,5,6]]`
 * - Black arrows:
 * - `[1, 8, 6]`: These are elements with indices `(0,0)`, `(1,1)`, `(2,2)`. This is a main diagonal. `i-j` is constant (0).
 * - `[9, 5]`: These are elements with indices `(1,0)`, `(2,1)`. `i-j` is constant (1).
 * - `[4]`: This is `(2,0)`. `i-j` is constant (2).
 * - All these have `i >= j` or `i-j >= 0`. These are the main diagonals in the bottom-left triangle.
 * - Blue arrows:
 * - `[7, 2]`: These are elements with indices `(0,1)`, `(1,2)`. `i-j` is constant (-1).
 * - `[3]`: This is `(0,2)`. `i-j` is constant (-2).
 * - All these have `i < j` or `i-j < 0`. These are the main diagonals in the top-right triangle.
 *
 * So, the approach is:
 * 1. Create a map `Map<Integer, List<Integer>> diagonals = new HashMap<>();`
 * 2. Iterate through the matrix `grid` from `i = 0` to `n-1` and `j = 0` to `n-1`.
 * 3. For each element `grid[i][j]`, add it to the list corresponding to the key `i - j`.
 * 4. After populating the map, iterate through the keys (the `i - j` values).
 * 5. For each key `d`:
 * - If `d >= 0` (bottom-left triangle), sort the list in non-increasing order.
 * - If `d < 0` (top-right triangle), sort the list in non-decreasing order.
 * 6. Create a new matrix `result` of size `n x n`.
 * 7. Iterate through the matrix `grid` again from `i = 0` to `n-1` and `j = 0` to `n-1`.
 * 8. For each position `(i, j)`, get the correct sorted element from the list corresponding to `i - j` and place it in `result[i][j]`. A pointer for each list can be used to keep track of the current element.
 * 9. Return the `result` matrix.
 *
 * Time Complexity:
 * - O(n^2 log n) where n is the size of the matrix.
 * - Iterating through the matrix to populate the map takes O(n^2).
 * - Sorting each list in the map:
 * - A diagonal can have at most n elements.
 * - There are 2n-1 diagonals.
 * - Sorting one diagonal takes O(k log k) where k is the length. In the worst case, k=n, so O(n log n).
 * - Total time for sorting is roughly O(n * (n log n)) which is O(n^2 log n).
 * - Iterating again to fill the new matrix takes O(n^2).
 * - Overall, the dominant factor is sorting, so the complexity is O(n^2 log n).
 *
 * Space Complexity:
 * - O(n^2) to store all the elements in the map.
 * - The map will contain all `n*n` elements of the matrix.
 *
 * Optimal Solution:
 */
import java.util.*;

class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> diagMap = new HashMap<>();

        // Step 1: Collect elements by diagonal key (i - j)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                diagMap.computeIfAbsent(key, k -> new ArrayList<>()).add(grid[i][j]);
            }
        }

        // Step 2: Sort diagonals accordingly
        for (int key : diagMap.keySet()) {
            List<Integer> list = diagMap.get(key);
            if (key >= 0) {
                 // bottom-left + main diagonal
                list.sort(Collections.reverseOrder()); 
                // descending
            } 
            else {
                 // top-right
                Collections.sort(list); 
                // ascending
            }
        }
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                int idx = idxMap.getOrDefault(key, 0);
                grid[i][j] = diagMap.get(key).get(idx);
                idxMap.put(key, idx + 1);
            }
        }
        return grid;
    }
}
