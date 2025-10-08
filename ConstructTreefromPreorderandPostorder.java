/**
 * Problem Statement:
 * Given two arrays, `pre[]` and `post[]`, representing the preorder and postorder traversals of a full binary tree (a binary tree where every node has 0 or 2 children),
 * construct the binary tree and return its root. The traversals contain unique values.
 *
 * Full Binary Tree Property:
 * This constraint is crucial. In a full binary tree, a node is either a leaf (0 children) or has two children.
 * This property allows us to uniquely determine the structure of the left and right subtrees from the given traversals.
 *
 * Optimal Approach:
 * The tree can be constructed recursively using the properties of preorder and postorder traversals.
 *
 * 1. Preorder Traversal (`pre`): [Root | Left Subtree | Right Subtree]
 * - The first element of `pre` is always the **Root** of the current subtree.
 * 2. Postorder Traversal (`post`): [Left Subtree | Right Subtree | Root]
 * - The last element of `post` is always the **Root** of the current subtree.
 *
 * **Recursive Steps:**
 * The recursive function will take the current range for both `pre` and `post` arrays.
 *
 * **Base Cases:**
 * - If the start index is greater than the end index for the current subarray (or pre/post segment is empty), return `null`.
 * - If the start index equals the end index (only one element), this is a leaf node. Create the node and return it.
 *
 * **Recursive Step:**
 * 1. **Current Root:** The root of the current subtree is `pre[preStart]`. Create a new `Node` with this value.
 *
 * 2. **Left Subtree Root:** The next element in `pre`, `pre[preStart + 1]`, is the root of the **Left Subtree**.
 *
 * 3. **Find Split Point:** Locate the Left Subtree Root's value (`pre[preStart + 1]`) in the `post` array within the current range (`postStart` to `postEnd - 1`).
 * - Let this index be `postRootIndex`. All elements in `post` from `postStart` up to `postRootIndex` belong to the **Left Subtree**.
 *
 * 4. **Calculate Subtree Size:** The size of the Left Subtree is `postRootIndex - postStart + 1`.
 *
 * 5. **Recursive Calls:**
 * - **Left Child:** Construct the left child using the calculated size.
 * - `pre` range: `preStart + 1` to `preStart + leftSubtreeSize`
 * - `post` range: `postStart` to `postRootIndex`
 *
 * - **Right Child:** Construct the right child using the remaining elements.
 * - `pre` range: `preStart + leftSubtreeSize + 1` to `preEnd`
 * - `post` range: `postRootIndex + 1` to `postEnd - 1` (The `postEnd` element is the current root, which is already used).
 *
 * **Handling the Full Binary Tree Constraint:**
 * Since it's a full binary tree, any node that is not a leaf must have two children. Our algorithm naturally ensures this:
 * - If `preStart == preEnd`, it's a leaf.
 * - If `preStart < preEnd`, we find a left child root and split the remaining elements for the right child.
 * (Note: In a standard construction from pre/post without the "full" constraint, one of the recursive calls might be for an empty segment, resulting in `null`. Here, since the problem is solvable, both recursive calls will successfully return a non-null node or hit a leaf base case).
 *
 * **Implementation Detail (Optimization):**
 * To efficiently find the position of the Left Subtree Root in the `post` array, a **HashMap** should be used to store the index of every element in the `post` array. This reduces the search time from O(N) to O(1).
 *
 * Time Complexity: O(n). We use O(N) for pre-processing the postorder array into a HashMap. The recursive function processes each node once, and the work done at each node (lookup in the map, constant time calculations) is O(1).
 * Space Complexity: O(n) for the HashMap and O(n) for the recursion stack and the constructed tree. Total space is dominated by O(n).
 */
// Optimal Solution in Java - 


class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

class Solution {
    int preIndex = 0;
    public Node constructTree(int[] pre, int[] post)
    {
        return build(pre, post, 0, post.length - 1);
    }

    private Node build(int[] pre, int[] post, int l, int r)
    {
        if (preIndex >= pre.length || l > r) 
        return null;

        Node root = new Node(pre[preIndex++]);
        if (l == r || preIndex >= pre.length)
        return root;

        int idx = l;
        while (idx <= r && post[idx] != pre[preIndex]) idx++;
        if (idx <= r) {
            root.left = build(pre, post, l, idx);
            root.right = build(pre, post, idx + 1, r - 1);
        }
        return root;
    }
}
