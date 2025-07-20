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
    class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        String name;
        String serial;
        boolean isDeleted = false;

        TrieNode(String name) {
            this.name = name;
        }
    }
  public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        TrieNode root = new TrieNode("");
        for (List<String> path : paths) {
            insert(root, path);
        }

        Map<String, List<TrieNode>> map = new HashMap<>();
        serialize(root, map);

        for (List<TrieNode> group : map.values()) {
            if (group.size() > 1) {
                for (TrieNode node : group) {
                    node.isDeleted = true;
                }
            }
        }
        List<List<String>> result = new ArrayList<>();
        dfs(root, new ArrayList<>(), result);
        return result;
    }

    private void insert(TrieNode root, List<String> path) {
        TrieNode curr = root;
        for (String folder : path) {
            curr.children.putIfAbsent(folder, new TrieNode(folder));
            curr = curr.children.get(folder);
        }
    }

    private String serialize(TrieNode node, Map<String, List<TrieNode>> map) {
        if (node.children.isEmpty()) return "";

        List<String> parts = new ArrayList<>();
        for (String key : node.children.keySet()) {
            String childSerial = serialize(node.children.get(key), map);
            parts.add("(" + key + childSerial + ")");
        }

        Collections.sort(parts);
        node.serial = String.join("", parts);
        map.computeIfAbsent(node.serial, k -> new ArrayList<>()).add(node);
        return node.serial;
    }

    private void dfs(TrieNode node, List<String> path, List<List<String>> result) {
        if (node != null && node != path && node.isDeleted) return;

        if (node != null && node != path && !node.name.equals("")) {
            path.add(node.name);
            result.add(new ArrayList<>(path));
        }

        for (TrieNode child : node.children.values()) {
            dfs(child, path, result);
        }

        if (!path.isEmpty()) path.remove(path.size() - 1);
    }
}
