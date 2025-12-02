/**
 * Problem Statement: 3623. Count Number of Trapezoids I
 * -----------------------------------------------------
 * Given a 2D integer array 'points', where points[i] = [xi, yi], find the number 
 * of unique horizontal trapezoids that can be formed by choosing any four distinct 
 * points from 'points'. 
 * A horizontal trapezoid is a convex quadrilateral with at least one pair of 
 * horizontal sides (i.e., parallel to the x-axis).
 * Return the result modulo 10^9 + 7.
 * * Constraints:
 * 4 <= points.length <= 10^5
 * -10^8 <= xi, yi <= 10^8
 * All points are pairwise distinct.
 */
/**
     * Optimal Solution: Counting Pairs of Horizontal Line Segments (Combinatorics)
     * -----------------------------------------------------------------------------
     * A horizontal trapezoid requires a pair of parallel horizontal sides. This means
     * we need two horizontal line segments (HLS) that are formed by points at different 
     * y-coordinates.
     * * 1. **Count Collinear Points:** First, group points by their y-coordinate (y-level).
     * The number of points on a single horizontal line segment at level 'y' is `freq[y]`.
     * 2. **Count Horizontal Line Segments (HLS):** The number of unique HLS of length 2 
     * (pairs of points) that can be formed on a specific y-level `y` is given by the 
     * combination formula "n choose 2": C(freq[y], 2) = freq[y] * (freq[y] - 1) / 2.
     * We need at least 2 points on a y-level to form an HLS.
     * 3. **Count Trapezoids:** A trapezoid is formed by choosing two such HLSs, one 
     * from level $y_1$ and one from level $y_2$ ($y_1 \neq y_2$).
     * - Let $C_y$ be the count of HLSs at level $y$.
     * - If we iterate through the y-levels, choosing the first HLS count $C_{y_{top}}$, 
     * the number of valid trapezoids formed is $C_{y_{top}} \times (\text{sum of all } C_{y_{bottom}} \text{ from previous levels})$.
     * - The provided code uses a cumulative sum technique to count these pairs of HLSs.
     *
     * * Implementation Detail:
     * - `freq` map stores the count of points for each $y_i$.
     * - The loop iterates over all $C_y$ values (`val`).
     * - `sum` accumulates the total count of HLSs from *previously processed* y-levels.
     * - `ans` accumulates the total number of trapezoids: $C_y \times \text{sum}$.
     * - `sum` is then updated to include the current $C_y$.
     * * This effectively calculates $\sum_{y_1 < y_2} C_{y_1} \cdot C_{y_2}$, which is the count
     * of all pairs of distinct HLSs (one on $y_1$ and one on $y_2$), where $y_1 \neq y_2$.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the number of points.
 * - O(N) is spent iterating through the `points` array to populate the `freq` HashMap.
 * - The second loop iterates over the unique y-coordinates, U. Since U <= N, this loop 
 * runs at most N times.
 * - All operations inside the loop are O(1).
 * - Overall complexity: O(N).
 * * Space Complexity Analysis:
 * --------------------------
 * O(U), where U is the number of unique y-coordinates.
 * - This space is used to store the `freq` HashMap. In the worst case, U = N 
 * (all points have unique y-coordinates), so the space complexity is O(N).
 */
// Optimal Solution in Java - 
import java.util.*;

class Solution {
    private static final int MOD = 1000000007;
    public int countTrapezoids(int[][] points)
     {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int[] point : points)
         freq.merge(point[1], 1, Integer::sum);

        long sum = 0, ans = 0;
        for(int x : freq.values()) 
        {
            long val = x * (x - 1L) / 2L;
            ans += val * sum;
            sum += val;
        }
        return (int)(ans % MOD);
    }
}