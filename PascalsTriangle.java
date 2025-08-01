// Problem Statement:
// Given an integer `numRows`, return the first `numRows` of Pascal's triangle.
// In Pascal's triangle, each number is the sum of the two numbers directly above it.

// Approach:
// We can generate Pascal's triangle row by row. Each row is a list of integers.
// The first row is simply `[1]`.
// For each subsequent row, we can derive it from the previous row.
// A new row will have one more element than the previous row.
// The first and last elements of each row are always `1`.
// The inner elements of a new row are calculated by summing the two corresponding elements from the previous row.
// For example, to get the element at index `j` in the new row, we sum the elements at index `j-1` and `j` from the previous row.
// We will use a list of lists to store the triangle. We iterate from 1 to `numRows` to build each row.

// Time Complexity: O(numRows^2)
// The outer loop runs `numRows` times. The inner loop runs up to `i` times, where `i` is the current row number.
// The total number of elements to generate is the sum of `1` to `numRows`, which is approximately `numRows^2 / 2`.
// Therefore, the time complexity is O(numRows^2).

// Space Complexity: O(numRows^2)
// We are storing the entire Pascal's triangle. The number of elements in the triangle is `(numRows * (numRows + 1)) / 2`,
// which is proportional to `numRows^2`. Thus, the space complexity is O(numRows^2).


import java.util.*;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows == 0) {
            return triangle;
        }
        
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);
        
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> currentRow = new ArrayList<>();
            currentRow.add(1);
            
            for (int j = 1; j < i; j++) {
                currentRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            
            currentRow.add(1);
            triangle.add(currentRow);
        }
        return triangle;
    }
}