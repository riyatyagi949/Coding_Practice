/**
 * Problem Statement:
 * Given the root of a binary tree where each node has a certain number of candies, and the total number of candies equals the number of nodes (n),
 * find the minimum number of moves (transfers of one candy between adjacent nodes) required so that every node has exactly one candy.
 *
 * Optimal Approach: Depth First Search (DFS)
 * This problem can be solved efficiently using a single Depth First Search (DFS) traversal, which is a greedy approach.
 * The minimum number of moves is the sum of the absolute values of the "net flow" of candies across every edge in the tree.
 * The net flow is the number of candies that *must* cross an edge to satisfy the requirement of one candy per node.
 *
 * We define a DFS function that calculates and returns the "excess" or "deficit" of candies for the subtree rooted at the current node.
 *
 * Let `flow` be the value returned by the DFS for a node:
 * - If `flow > 0`, the subtree rooted at this node has `flow` **excess** candies to be sent to its parent (or other subtrees).
 * - If `flow < 0`, the subtree rooted at this node has a **deficit** of `|flow|` candies that must be received from its parent.
 * - If `flow = 0`, the subtree is perfectly balanced internally.
 *
 * The DFS for a node `root` works as follows:
 * 1. Recursively call DFS on the left child (`left_flow`) and the right child (`right_flow`).
 * 2. The total number of candies required by the subtree is the number of nodes in the subtree. Since each node should have 1 candy, the required count is 1 for the root itself plus the number of nodes in its children's subtrees.
 * 3. The net flow for the current subtree is calculated as:
 * `root_flow = root.data + left_flow + right_flow - 1`
 * - `root.data + left_flow + right_flow`: This is the total number of candies available in the subtree *after* internal adjustments in the children.
 * - `- 1`: Represents the 1 candy that the current `root` node needs to keep for itself.
 *
 * 4. The number of moves required across the edge connecting `root` to its parent due to the balance/imbalance in the children's subtrees is `|left_flow|` and `|right_flow|`. We add these moves to a global total count.
 * 5. We then add the move required across the edge connecting `root` to its parent, which is `|root_flow|`, to the global total count *in the parent's context*.
 *
 * By summing the absolute flow across every edge, we count the minimum number of moves because each unit of flow (excess or deficit) must correspond to exactly one move across an edge.
 *
 * We maintain a global variable `totalMoves` to accumulate the absolute flow across every edge.
 *
 * Time Complexity: O(n), where `n` is the number of nodes. We visit each node exactly once during the DFS traversal.
 * Space Complexity: O(h), where `h` is the height of the binary tree, due to the recursion stack. In the worst case (skewed tree), $O(n)$.
 */
// Optimal Solution in Java -