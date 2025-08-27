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