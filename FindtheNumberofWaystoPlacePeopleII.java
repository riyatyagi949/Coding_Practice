//  Problem 3027, "Find the Number of Ways to Place People II."

// # Problem Analysis

// The problem asks us to count the number of pairs of points `(Alice, Bob)` that can form a valid rectangle.

//  A valid rectangle is defined by an upper-left corner (Alice) and a lower-right corner (Bob) and must not contain any other points either inside or on its boundaries.

//   This means that for a pair of points `(p1, p2)`, `p1` must be Alice and `p2` must be Bob if `p1.x <= p2.x` and `p1.y >= p2.y`, and there are no other points `p3` such that `p1.x <= p3.x <= p2.x` and `p2.y <= p3.y <= p1.y`.

// Approach

// The provided solution uses a clever `O(N^2)` approach to solve this problem by leveraging sorting and a nested loop.

// Sorting the Points

// The first step is to sort the input `points` array. The sorting criteria are:
// 1.Primary Key: Sort by the x-coordinate in ascending order.
// 2.Secondary Key: For points with the same x-coordinate, sort by the y-coordinate in descending order.

// This specific sort is crucial. It ensures that when we iterate through the sorted array, if we pick a point `i` and a point `j` with `i < j`, the condition `points[i].x <= points[j].x` is always met. The descending y-sort for ties is important to correctly handle points on the same vertical line, ensuring that the highest point comes first.

// Nested Loop and Obstruction Check

// After sorting, a nested loop is used to check every possible pair of points.
// - The outer loop iterates from `i = 0` to `n-1`, treating `points[i]` as Alice's position.
// - The inner loop iterates from `j = i + 1` to `n-1`, considering `points[j]` as a potential Bob's position.

// Inside the inner loop, the code checks for two conditions for a pair `(points[i], points[j])` to form a valid, non-obstructed fence:

// 1. Valid Fence Shape: The first condition is `points[i][1] >= points[j][1]`. Since the array is already sorted by `x` in ascending order, this check, combined with the sorting, ensures that `points[i]` is indeed the upper-left corner and `points[j]` is the lower-right.

// 2.No Obstruction: The second condition is `points[j][1] > maxY`. To check for obstructions efficiently, a variable `maxY` is maintained within the inner loop. `maxY` keeps track of the maximum y-coordinate of all points encountered between Alice's current position (`i`) and Bob's potential position (`j`). Because of our sorting, all intermediate points between `i` and `j` will have x-coordinates that are on or between `points[i].x` and `points[j].x`. The condition `points[j][1] > maxY` ensures that no intermediate point `k` has a y-coordinate that would place it on or within the vertical range of the fence `[points[j][1], points[i][1]]`. If this condition is met, the pair is valid, and the answer is incremented.

// This approach effectively reduces the obstruction check from an `O(N)` loop to a single `O(1)` comparison, making the overall algorithm `O(N^2)`.

// Complexity

// - Time Complexity: The dominant operation is the nested loop, which runs in `O(N^2)`. The initial sort takes `O(N log N)`, which is less significant. Therefore, the total time complexity is `O(N^2)`.
// - Space Complexity: The solution uses an in-place sort and a few variables, resulting in an auxiliary space complexity of git`O(1)`.