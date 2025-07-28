// Problem Statement:
// A beautiful matrix is defined as a square matrix in which the sum of elements in every row and every column is equal.
// Given a square matrix mat[][], your task is to determine the minimum number of operations required to make the matrix beautiful.
// In one operation, you are allowed to increment the value of any single cell by 1.

// Approach:
// The goal is to make the sum of every row and every column equal to some target sum. To minimize the number of operations,
// which is equivalent to minimizing the total sum of increments, we should choose the smallest possible target sum.
// Let the target sum be `S`. The total number of operations will be `S * N - totalSum`, where `N` is the dimension of the matrix
// and `totalSum` is the sum of all elements in the original matrix.
// To make all row and column sums equal to `S`, `S` must be at least as large as the maximum initial sum among all rows and columns.
// Let `maxSum` be the maximum of all initial row and column sums.
// The minimum possible value for `S` that allows all row and column sums to be equalized is `maxSum`.
// If we choose `S = maxSum`, any row or column with a sum less than `maxSum` will need to be incremented to reach `maxSum`.
// The total number of operations is the sum of all increments.
//
// The total sum of elements in the matrix after the operations will be `S * N`.
// The total number of operations is `(S * N) - initialTotalSum`.
//
// We can calculate the initial sum of each row and each column.
// Then, find the maximum of all these sums. This maximum sum will be our target sum `S`.
// Let `rowSums[i]` be the sum of the i-th row, and `colSums[j]` be the sum of the j-th column.
// Let `maxSum` be `max(max(rowSums), max(colSums))`.
// The total number of operations needed is the sum of increments for each row and each column.
//
// Let's re-evaluate the total number of operations.
// The final sum of all elements in the matrix will be `N * S`.
// The initial total sum of all elements is `totalSum`.
// The number of operations is `N * S - totalSum`.
//
// The value of `S` needs to be large enough to accommodate all initial sums.
// `S >= max(rowSums)` for all rows.
// `S >= max(colSums)` for all columns.
//
// To minimize `N * S - totalSum`, we need to minimize `S`.
// The minimum possible value for `S` is `max(max(rowSums), max(colSums))`.
// Let's call this `minTargetSum`.
//
// So, the algorithm is:
// 1. Calculate the sum of each row. Store them in an array, say `rowSums`.
// 2. Calculate the sum of each column. Store them in an array, say `colSums`.
// 3. Find the maximum sum among all `rowSums` and `colSums`. Let this be `max_sum_rc`.
// 4. Calculate the total sum of all elements in the original matrix.
// 5. The total number of operations is `(N * max_sum_rc) - totalSum`.
//
// Time Complexity: O(N^2) to iterate through the matrix to calculate row and column sums and the total sum.
// Space Complexity: O(N) to store the row and column sums.

class Solution {
    public static int findMinOpeartion(int[][] matrix, int n) {
        int[] rowSums = new int[n];
        int[] colSums = new int[n];
        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowSums[i] += matrix[i][j];
                colSums[j] += matrix[i][j];
                totalSum += matrix[i][j];
            }
        }
        int max_sum = 0;
        for (int sum : rowSums) {
            max_sum = Math.max(max_sum, sum);
        }
        for (int sum : colSums) {
            max_sum = Math.max(max_sum, sum);
        }
         long targetTotalSum = (long) n * max_sum;
        return (int) (targetTotalSum - totalSum);
    }
}