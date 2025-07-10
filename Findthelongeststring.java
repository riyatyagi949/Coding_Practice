/*
Problem Statement:
Given an array of strings arr[]. Find the longest string in arr[] such that every prefix of it is also present in the array arr[].
Note: If multiple strings have the same maximum length, return the lexicographically smallest one.

Approach:
The problem requires finding a specific type of "longest word" from a given list of strings. The key condition is that not only the word itself but all of its prefixes must also exist within the given array of strings. If multiple words satisfy this condition and have the same maximum length, we need to choose the one that comes first alphabetically (lexicographically smallest).

A Trie (Prefix Tree) is an ideal data structure for problems involving prefixes of strings. Here's how we leverage it:

1.  Trie Construction:
    We start by creating an empty Trie. Each node in the Trie represents a character. A path from the root to a node represents a prefix.
    For every word in the input arr, we insert it into the Trie.
    During insertion, we mark the node corresponding to the last character of a word as isEndOfWord = true. This flag helps us identify if a particular prefix forms a complete word present in the input array.

2.  Trie Traversal (Depth-First Search - DFS):
    After building the Trie, we need to traverse it to find our desired string. We use a DFS approach starting from the root of the Trie.
    A StringBuilder is used to build the currentString as we traverse down the Trie.
    Crucial Condition Check: When we are at a node (and it's not the root), we check node.isEndOfWord. If node.isEndOfWord is false, it means the prefix formed up to this node is not a complete word in the input array. Since the problem states "every prefix of it is also present," we cannot extend this path further, and thus we return from this DFS branch. This ensures that only paths where every segment is a valid word are considered.
    Candidate Evaluation: If the node is marked as isEndOfWord (or it's the root, as the empty string technically has no prefixes to check), the currentString built so far is a valid candidate.
        We compare its length() with longestString.length().
        If currentString is longer, it becomes the new longestString.
        If currentString has the same length, we perform a lexicographical comparison using compareTo(). If currentString comes earlier alphabetically than longestString, it becomes the new longestString.
    Recursive Step: For each child node of the current node that exists (i.e., children[i] is not null), we:
        Append the character represented by that child to currentString.
        Recursively call dfs on the child node.
        Backtrack: After the recursive call returns, we remove the last appended character from currentString using deleteCharAt(). This is essential for exploring other branches correctly.

Time Complexity:
Trie Construction: Let N be the number of strings in arr and L be the maximum length of any string. Inserting each character of each word takes constant time (amortized). So, building the Trie takes approximately O(N * L) time.
Trie Traversal: In the worst case, the DFS might visit every node in the Trie. The total number of nodes in a Trie is at most O(N * L). At each node, we iterate through 26 (alphabet size) possible children. Therefore, the traversal takes O(26 * N * L), which simplifies to O(N * L) since 26 is a constant.
Overall: The dominant factor is O(N * L).

Space Complexity:
The space required is primarily for storing the Trie itself. In the worst case (e.g., if all words share very few common prefixes), the Trie can have up to O(N * L) nodes. Each node stores an array of 26 pointers and a boolean flag.
Overall: The space complexity is O(N * L).

Optimal Solution:
The provided solution utilizing a Trie and DFS is considered optimal because it efficiently handles prefix lookups and word validation, achieving a time complexity directly proportional to the total number of characters across all input strings.

*/
import java.util.Arrays;

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

class Solution {
    private TrieNode root;
    private String longestString = "";

    public String longestWord(String[] arr) {
        root = new TrieNode();
        for (String word : arr) {
            insert(word);
        }
        dfs(root, new StringBuilder());
        return longestString;
    }

    private void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    private void dfs(TrieNode node, StringBuilder currentString) {
        if (node != root && !node.isEndOfWord) {
            return;
        }

        if (currentString.length() > longestString.length()) {
            longestString = currentString.toString();
        } else if (currentString.length() == longestString.length()) {
            if (currentString.toString().compareTo(longestString) < 0) {
                longestString = currentString.toString();
            }
        }
        
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                currentString.append((char)('a' + i));
                dfs(node.children[i], currentString);
                currentString.deleteCharAt(currentString.length() - 1);
            }
        }
    }
}