/**
 * Problem Statement: Lexicographically Smallest String After Applying Operations
 * -----------------------------------------------------------------------------
 * Given a string 's' of even length (digits 0-9) and two integers 'a' and 'b'.
 * Two operations can be applied any number of times in any order:
 * 1. Add 'a' to all odd indices of 's'. Digits cycle back to 0 (i.e., (digit + a) % 10).
 * 2. Rotate 's' to the right by 'b' positions.
 * Return the lexicographically smallest string obtainable.
 *
 * Constraints:
 * 2 <= s.length <= 100
 * s.length is even.
 * 1 <= a <= 9
 * 1 <= b <= s.length - 1
 */
/**
     * Optimal Solution: Breadth-First Search (BFS) on the State Space
     * --------------------------------------------------------------
     * The problem can be viewed as finding the shortest path (in lexicographical order, not length) 
     * in a graph where:
     * - Nodes: All reachable strings.
     * - Edges: The two operations (Add and Rotate).
     * Since the number of nodes (strings) is finite (at most N * 10^(N/2)), we can use BFS to 
     * explore all reachable strings, guaranteeing that we find the lexicographically smallest one 
     * by simply tracking the minimum string found so far.
     * * * Algorithm Steps (BFS):
     * 1. Initialize 'minString' to the original string 's'.
     * 2. Use a Queue for BFS and a HashSet to track 'visited' strings to avoid cycles and redundant work.
     * 3. Start BFS from 's'.
     * 4. For each string 'current' popped from the queue:
     * a. **Update Minimum:** Compare 'current' with 'minString' and update 'minString' if 'current' is smaller.
     * b. **Apply Operation 1 (Add):** Generate 'next_add' string by applying the Add operation. If 'next_add' is new, add it to 'visited' and the queue.
     * c. **Apply Operation 2 (Rotate):** Generate 'next_rot' string by applying the Rotate operation. If 'next_rot' is new, add it to 'visited' and the queue.
     * 5. Return the final 'minString'.
     */
    /**
 
 * Time Complexity Analysis:
 * -------------------------
 * O(N^2 * 10 * gcd(b, N)), where N is s.length().
 * * N: The length of the string (up to 100).
 * * The maximum number of distinct rotation states is N / gcd(b, N), where gcd is the greatest common divisor.
 * * For each rotation state, the number of distinct 'Add' transformations is at most 10 for even indices (if 2*b is not divisible by N) or (10*10) for both index groups. 
 * * The overall number of reachable states (nodes) is upper-bounded by O(N * 100). Since N is small (<= 100), the number of nodes is manageable.
 * * Let S be the number of reachable states. S is at most N * 100.
 * * Each BFS step involves:
 * - String comparison: O(N)
 * - Apply Add: O(N)
 * - Apply Rotate: O(N)
 * * Total Time Complexity: O(S * N). Since S is polynomial in N (O(N*100)), the overall complexity is O(N^3). Given N <= 100, N^3 is about 1,000,000, which is fast enough.
 * * More accurately, S is bounded by N * L, where L is the least common multiple of 10 and 10 (or a factor thereof), so S is around O(N * 10^2). Thus, the complexity is approximately O(N^3).
 
 * * Space Complexity Analysis:
 * --------------------------
 * O(S * N), where S is the number of reachable states, and N is the string length.
 * - This space is used to store the 'visited' HashSet and the Queue, both of which hold up to S distinct strings, each of length N.
 * - S is at most N * 100 (approximately).
 * - Overall complexity: O(N^2).
 */
// Optimal Solution in Java - 
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Set<String> seen = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        String res = s;
        queue.offer(s);
        seen.add(s);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.compareTo(res) < 0) res = curr;

            char[] arr = curr.toCharArray();
            for (int i = 1; i < arr.length; i += 2) {
                arr[i] = (char) (((arr[i] - '0' + a) % 10) + '0');
            }
            String added = new String(arr);
            if (seen.add(added)) queue.offer(added);

            String rotated = curr.substring(curr.length() - b) + curr.substring(0, curr.length() - b);
            if (seen.add(rotated)) queue.offer(rotated);
        }
        return res;
    }
}