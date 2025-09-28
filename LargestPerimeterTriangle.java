/**
 * Problem Statement:
 * Given an integer array `nums`, return the largest possible perimeter of a triangle with a non-zero area that can be formed using three side lengths from the array.
 * If it is impossible to form any such triangle, return 0.
 *
 * Triangle Inequality Theorem:
 * For three lengths `a`, `b`, and `c` to form a triangle, the sum of any two sides must be greater than the third side.
 * If we assume `a <= b <= c`, the only condition we need to check is: `a + b > c`.
 *
 * Optimal Approach:
 * To maximize the perimeter (`a + b + c`), we should choose the largest possible side lengths.
 * 1. Sort the array `nums` in non-decreasing order. This ensures that the largest potential sides are at the end of the array.
 * 2. Iterate from the largest element (at index `n-1`) backward, considering it as the longest side `c`.
 * 3. Check the two preceding elements (`b` at `n-2` and `a` at `n-3`) to see if they satisfy the triangle inequality theorem: `nums[i-2] + nums[i-1] > nums[i]`.
 * - Where `c = nums[i]`, `b = nums[i-1]`, and `a = nums[i-2]`.
 * 4. The first set of three adjacent numbers that satisfies the triangle inequality will yield the maximum possible perimeter. This is because:
 * - By iterating backward, we ensure that `c` is the largest possible side length chosen so far.
 * - By choosing the immediately preceding elements `a` and `b`, we maximize their values for the current `c`, thus maximizing the perimeter `a + b + c`. Any other valid triplet will have a smaller or equal `c` or smaller `a` and `b`, leading to a smaller perimeter.
 * 5. If the loop completes without finding a valid triangle, it means no three side lengths can form a triangle, and we return 0.
 *
 * Time Complexity: O(n log n), dominated by the sorting of the array. The subsequent search takes O(n) time.
 * Space Complexity: O(1) or O(log n) depending on the sorting algorithm implementation (e.g., O(log n) for recursive calls in quicksort, O(1) for heap sort if done in-place). Assuming Java's `Arrays.sort()` which is an optimized quicksort/mergesort hybrid, the auxiliary space is typically O(log n) or O(n).
 */
// Optimal  Solution in Java - 
import java.util.*;