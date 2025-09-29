/**
 * Problem Statement:
 * Given a convex n-sided polygon with integer values at its vertices (provided in the array `values` in clockwise order),
 * find the minimum total score of any possible triangulation of the polygon.
 * The total score is the sum of the weights of all triangles in the triangulation, where the weight of a triangle is the product of the values at its three vertices.
 *
 * Optimal Approach (Dynamic Programming):
 * This problem has optimal substructure and overlapping subproblems, making it a perfect fit for Dynamic Programming (DP). It is a classic matrix chain multiplication type problem.
 *
 * 1. Define the State:
 * Let `dp[i][j]` be the minimum score to triangulate the sub-polygon formed by the vertices from index `i` to index `j` (inclusive, in the original `values` array).
 * Note that the side connecting `values[i]` and `values[j]` acts as the base of this sub-polygon.
 *
 * 2. Base Case:
 * A polygon with only 2 sides (or 3 vertices, if $j=i+2$) is already a triangle.
 * - If $j - i < 2$ (e.g., a single side or two adjacent vertices), it cannot form a polygon that needs triangulation, so the score is 0.
 * - If $j - i = 2$, the polygon is a single triangle: $values[i] \cdot values[i+1] \cdot values[j]$.
 *
 * 3. Transition (Recurrence Relation):
 * To triangulate the polygon $(i, i+1, \dots, j)$, we must choose one triangle to be the "last cut" or "base triangle" that splits the polygon into two smaller sub-polygons.
 * This triangle must have the side $(i, j)$ as one of its edges, and the third vertex, $k$, must be somewhere between $i$ and $j$, i.e., $i < k < j$.
 *
 * The cost of this triangulation for a specific choice of $k$ is:
 * Cost($i, j, k$) = (Weight of triangle $(i, k, j)$) + (Minimum score for sub-polygon $(i, \dots, k)$) + (Minimum score for sub-polygon $(k, \dots, j)$)
 * Cost($i, j, k$) = $(values[i] \cdot values[k] \cdot values[j]) + dp[i][k] + dp[k][j]$
 *
 * The optimal score for $dp[i][j]$ is the minimum cost over all possible splitting vertices $k$:
 * $dp[i][j] = \min_{i < k < j} \{ values[i] \cdot values[k] \cdot values[j] + dp[i][k] + dp[k][j] \}$
 *
 * 4. Iteration Order:
 * We must compute the DP states in an order that ensures $dp[i][k]$ and $dp[k][j]$ are already calculated when we compute $dp[i][j]$. This is achieved by iterating on the length of the sub-polygon (the distance $L = j - i$).
 * - $L$ runs from $2$ (for triangles) to $n-1$ (for the whole polygon).
 * - $i$ runs from $0$ to $n-1 - L$.
 * - $j$ is set to $i + L$.
 * - $k$ runs from $i+1$ to $j-1$.
 *
 * The final answer is $dp[0][n-1]$, the minimum score for the entire polygon from vertex 0 to $n-1$.
 *
 * Time Complexity: O(n^3). There are $O(n^2)$ DP states ($dp[i][j]$), and the calculation of each state involves a loop over $k$ from $i+1$ to $j-1$, which takes $O(n)$ time.
 * Space Complexity: O(n^2) to store the $n \times n$ DP table.
 */
