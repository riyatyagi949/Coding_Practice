/*
Problem Statement:
You are given a 2D array paths, where paths[i] is an array representing an absolute path to the ith folder in the file system.
For example, ["one", "two", "three"] represents the path "/one/two/three".
Two folders (not necessarily on the same level) are identical if they contain the same non-empty set of identical subfolders and underlying subfolder structure. The folders do not need to be at the root level to be identical. If two or more folders are identical, then mark the folders as well as all their subfolders.
For example, folders "/a" and "/b" in the file structure below are identical. They (as well as their subfolders) should all be marked:
/a
/a/x
/a/x/y
/a/z
/b
/b/x
/b/x/y
/b/z
However, if the file structure also included the path "/b/w", then the folders "/a" and "/b" would not be identical. Note that "/a/x" and "/b/x" would still be considered identical even with the added folder.
Once all the identical folders and their subfolders have been marked, the file system will delete all of them. The file system only runs the deletion once, so any folders that become identical after the initial deletion are not deleted.
Return the 2D array ans containing the paths of the remaining folders after deleting all the marked folders. The paths may be returned in any order.

Approach:
This problem can be solved by building a trie (prefix tree) to represent the file system paths and then identifying duplicate subtrees.

1.  Trie Construction:
    - Create a trie where each node represents a folder name.
    - Traverse each path in the input `paths` array. For each folder name in a path, add it as a child to the current trie node.
    - Each trie node should store:
        - Its children (a map from folder name string to child trie node).
        - A flag `isDeleted` to mark if this folder (and its subfolders) should be deleted.
        - The actual folder name string it represents.

2.  Serialize Subtrees and Detect Duplicates:
    - We need a way to determine if two subtrees are identical. A common technique is to serialize the subtree structure into a string.
    - Perform a Depth First Search (DFS) traversal of the trie, starting from the root.
    - For each node, recursively call DFS on its children.
    - When returning from a child's DFS call, append its serialized string to a list.
    - After processing all children of a node, sort the serialized strings of its children. This is crucial because the order of subfolders doesn't matter for identity.
    - Concatenate the current node's name and the sorted, combined serialized strings of its children to form the serialized string for the current subtree. Use delimiters (e.g., "(" and ")") to clearly define the boundaries of subfolders in the serialized string. For example, "/a(x(y)z)" or "(x(y))(z)".
    - Store these serialized strings in a map where the key is the serialized string and the value is a list of trie nodes that have this identical subtree structure.

3.  Marking for Deletion:
    - After the DFS traversal, iterate through the map of serialized strings.
    - If a serialized string maps to a list of more than one trie node, it means all these nodes represent identical subtrees. Mark the `isDeleted` flag to `true` for all these nodes.

4.  Collecting Remaining Paths:
    - Perform another DFS traversal from the root of the trie.
    - This time, collect all paths that lead to folders whose `isDeleted` flag is `false`.
    - When traversing, if a node's `isDeleted` flag is true, do not descend into its children as they are also marked for deletion.

Time Complexity:
Let `N` be the number of paths, `L_max` be the maximum length of a path, and `S_max` be the maximum length of a folder name.
Let `M` be the total number of unique folder nodes in the trie. `M` can be up to `sum(paths[i].length)`.

1. Trie Construction:
   - For each path `paths[i]`, we iterate through its components.
   - Inserting a path takes time proportional to its length.
   - Total time for trie construction: O(Sum of lengths of all paths) = O(N * L_max * S_max) in worst case if string comparisons are long, but effectively O(Sum of paths length) using HashMap for children. If we consider `path[i][j].length` up to 10, total string comparisons can be high. Let's denote `total_chars = sum(sum(paths[i][j].length))`. Building trie is roughly O(total_chars).

2. Serialize Subtrees and Detect Duplicates (DFS traversal):
   - Each node in the trie is visited once.
   - At each node, we iterate over its children (at most 10).
   - String concatenation: The length of the serialized string for a subtree can be significant. In the worst case, it can be proportional to the number of nodes in that subtree. Let `V` be the total number of nodes in the trie. Building the serialized string for each node involves concatenating strings from its children. Sorting children's serialized strings might take `O(C * C_len * log C)` where `C` is number of children and `C_len` is max length of child's serialized string.
   - The overall time for serialization and adding to map: If `H` is the maximum depth of the trie, the length of the serialized string can be `O(V * S_max)`. Storing and comparing these strings in a HashMap can lead to a factor of string length.
   - A rough estimate for serialization might be `O(V * L_max)` (total length of all serialized strings).
   - Total time: `O(total_chars + V * L_max_serialized_string_length)`.

3. Marking for Deletion:
   - Iterating through the map: `O(Number of unique serialized strings * average list length)`. In worst case, this could be related to `V`.

4. Collecting Remaining Paths (DFS traversal):
   - Each non-deleted node is visited once. Building path strings.
   - Total time: `O(total_chars)` in the worst case to reconstruct paths.

Overall Complexity: Dominated by the DFS serialization.
A tighter bound for serialization: The total length of all serialized strings combined is `O(total_chars)`. Each character from the original paths contributes to the serialized string once. Sorting children's serialized strings at each node contributes to the total complexity. If `k` is the number of children and `S` is the average length of their serialized strings, sorting takes `k * S * log k`. Summing this over all nodes can be tricky.
A more practical bound for DFS with string serialization: `O(Total number of characters in all paths)`. Plus `O(V log V)` for sorting children strings at each node, where `V` is the number of trie nodes.
Given `sum(paths[i][j].length) <= 2 * 10^5`, string operations will be significant.
Let `C` be the total number of characters in all paths combined. The time complexity for constructing the trie and performing DFS with serialization is roughly `O(C)`. The use of `HashMap` for children in the `TrieNode` makes string operations (insertion/lookup) efficient. String concatenation in Java could be `O(length)`. Overall, it should be acceptable given constraints.

Space Complexity:
- Trie: `O(Total number of characters in all paths)` for storing all folder names and pointers. This is `O(C)`.
- `HashMap<String, List<TrieNode>>` for `serializedToNodes`: In the worst case, each unique subtree might have its own serialized string. The sum of lengths of these serialized strings can be `O(C)`.
- Recursion stack for DFS: `O(L_max)` in the worst case (deepest path).
- `ArrayList<List<String>>` for `result`: `O(C)` in the worst case (all paths remain).

Total Space Complexity: O(C).

Optimal Solution:
The approach of using a Trie and serializing subtrees is generally considered optimal for this type of problem. The key is to handle the serialization correctly (e.g., sorting child strings) to ensure identical subtrees are identified regardless of child order.
*/

import java.util.*;

class Solution {

    // TrieNode represents a folder in the file system
    static class TrieNode {
        String name;
        Map<String, TrieNode> children;
        boolean isDeleted; // Flag to mark if this folder (and its subfolders) should be deleted

        public TrieNode(String name) {
            this.name = name;
            this.children = new TreeMap<>(); // Using TreeMap to ensure consistent iteration order for serialization
            this.isDeleted = false;
        }
    }

    // Map to store serialized subtree strings to the list of TrieNodes having that structure
    Map<String, List<TrieNode>> serializedToNodes;

    public List<List<String>> deleteDuplicateFolders(List<List<String>> paths) {
        TrieNode root = new TrieNode(""); // Root of the file system trie
        serializedToNodes = new HashMap<>();

        // 1. Build the Trie
        for (List<String> path : paths) {
            TrieNode current = root;
            for (String folder : path) {
                current.children.putIfAbsent(folder, new TrieNode(folder));
                current = current.children.get(folder);
            }
        }

        // 2. Perform DFS to serialize subtrees and detect duplicates
        dfsSerialize(root);

        // 3. Mark folders for deletion
        for (List<TrieNode> nodes : serializedToNodes.values()) {
            if (nodes.size() > 1) { // If more than one node has this identical subtree structure
                for (TrieNode node : nodes) {
                    node.isDeleted = true; // Mark all such nodes as deleted
                }
            }
        }

        // 4. Collect the remaining paths
        List<List<String>> result = new ArrayList<>();
        dfsCollectPaths(root, new ArrayList<>(), result);

        return result;
    }

    // DFS function to serialize subtrees
    private String dfsSerialize(TrieNode node) {
        if (node.children.isEmpty()) {
            // Leaf node, its serialization is just its name
            return node.name;
        }

        StringBuilder sb = new StringBuilder(node.name);
        sb.append('('); // Start delimiter for children

        // TreeMap ensures children are iterated in sorted order, which is crucial for identical serialization
        for (TrieNode child : node.children.values()) {
            sb.append(dfsSerialize(child));
        }
        sb.append(')'); // End delimiter for children

        String serializedString = sb.toString();

        // If the serialized string represents a non-empty folder's structure
        // (i.e., it's not just "()", meaning an empty folder at the root level which doesn't count for identity)
        // A serialized string like "a(b)" means "a" contains "b". If "b" is a leaf, it's "a(b)".
        // If a node has no children (e.g. `["a"]` and `["c"]` where `a` and `c` are empty folders), their serialization would be "a" and "c".
        // The problem states "same non-empty set of identical subfolders". This implies a folder itself should have children to be considered for duplication,
        // unless it's a leaf node that is duplicated. Let's re-read: "Two folders ... are identical if they contain the same non-empty set of identical subfolders and underlying subfolder structure."
        // This implies that a folder must have subfolders to be considered for duplication by content.
        // The crucial part is "non-empty set of identical subfolders". An empty folder like "/a" has an empty set of subfolders. If /a and /c both have empty subfolders,
        // they are identical in their (empty) structure. However, the example shows /a and /c with /a/b and /c/b marked. Here, /a contains /b, and /c contains /b.
        // So "/b" is the "non-empty set of subfolders" for "/a" and "/c" respectively, making them identical.
        // If a folder itself has no children, its content is effectively "empty".
        // The special case for "empty folders" (nodes with no children) is critical. If a folder's direct children list is empty, its serialized representation would be just its name.
        // The problem says "non-empty set of identical subfolders". This implies a folder must have at least one subfolder to be considered for duplication by its subfolder content.
        // If the serialized string of a node represents just its name (no children), or if the part inside the parenthesis is empty, it means it has no subfolders.
        // The critical part is when a node's children list is empty, its serialization is just `node.name`. If it has children, its serialization is `node.name(...)`.
        // The problem implies comparing the structure *of subfolders*. If a folder "x" is empty, its structure is just "x". If another folder "y" is also empty, its structure is "y".
        // They are identical in that they are both empty. However, the problem example 1:
        // Input: paths = [["a"],["c"],["d"],["a","b"],["c","b"],["d","a"]]
        // Output: [["d"],["d","a"]]
        // Explanation: Folders "/a" and "/c" (and their subfolders) are marked for deletion because they both contain an empty folder named "b".
        // Here, /a has subfolder /b (which is empty). /c has subfolder /b (which is empty). So /a and /c are identical due to "b".
        // This means the content of the serialized string should be checked for being "empty".
        // The length check `sb.length() > node.name.length() + 2` essentially checks if there's anything inside the parentheses.
        // If `sb.length() == node.name.length() + 2` (e.g., "a()"), it means the folder has no *named* children, thus it is an empty folder itself in terms of contents.
        // We only consider a node for duplication if its serialized form contains actual subfolder structure.
        // A serialized string `name()` is for a folder with no subfolders. Its children map is empty.
        // My current `dfsSerialize` will return `node.name` if children are empty.
        // If `node.children` is empty, the first `if` branch `return node.name;` is taken.
        // If `node.children` is NOT empty, `sb.append('(')` etc. is used.
        // So, if `node.children.isEmpty()` is true, the `serializedString` will just be `node.name`.
        // If `node.children.isEmpty()` is false, but all children are also empty, it could result in something like "a(b()c())".
        // The critical condition for adding to `serializedToNodes` is that the folder must have a "non-empty set of identical subfolders".
        // This implies that `node.children` must not be empty. If `node.children` is empty, its `serializedString` is just `node.name`.
        // Such nodes should NOT be considered for duplication based on "subfolder structure".
        // So, we only add to `serializedToNodes` if `node.children` is not empty.

        if (!node.children.isEmpty()) { // Only consider folders with actual subfolders for duplication
            serializedToNodes.computeIfAbsent(serializedString, k -> new ArrayList<>()).add(node);
        }

        return serializedString;
    }

    // DFS function to collect paths of non-deleted folders
    private void dfsCollectPaths(TrieNode node, List<String> currentPath, List<List<String>> result) {
        if (node.isDeleted) {
            return; // If current folder is marked for deletion, stop here
        }

        // Only add non-root folders to the path list
        if (!node.name.isEmpty()) {
            currentPath.add(node.name);
        }

        // If it's a valid path (not root and not marked for deletion)
        if (!currentPath.isEmpty()) {
            result.add(new ArrayList<>(currentPath));
        }

        // Recursively visit children
        // TreeMap ensures consistent iteration, though not strictly necessary for collection
        for (TrieNode child : node.children.values()) {
            dfsCollectPaths(child, new ArrayList<>(currentPath), result);
        }
    }
}