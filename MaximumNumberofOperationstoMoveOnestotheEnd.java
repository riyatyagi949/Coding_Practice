/**
 * Problem Statement: Maximum Number of Operations to Move Ones to the End
 * ----------------------------------------------------------------------
 * You are given a binary string 's'. An operation consists of:
 * - Choosing an index 'i' where s[i] == '1' and s[i + 1] == '0' (i + 1 < s.length).
 * - Moving s[i] to the right until it hits the end of the string or another '1'.
 * The goal is to return the maximum number of operations that can be performed.
 *
 * Constraints: 1 <= s.length <= 10^5, s[i] is '0' or '1'.
 *//**
     * Optimal Solution: Greedy Approach (Counting '1's Before '0' blocks)
     * -------------------------------------------------------------------
     * Key Insight:
     * 1. **Operation Effect:** The operation `s[i] == '1'` followed by `s[i + 1] == '0'`
     * is equivalent to moving the '1' past one or more '0's to the right. Critically,
     * **each time a '1' moves past a block of '0's, it is counted as one operation.**
     * 2. **Maximization:** Since any '1' that is followed by a '0' can be moved, and 
     * the movement of one '1' does not hinder the ability of another '1' to be moved, 
     * the operations are independent regarding the total count. To maximize the total 
     * operations, we must ensure every "block" of '1's contributes its maximum possible count.
     * 3. **The Greedy Rule:** When a block of '0's is encountered, every '1' that is currently
     * to the left of this block can potentially move into this block of '0's and past it.
     * Since the '1' is moved until it hits the end or another '1', all '0's in the current 
     * block are skipped in a single operation. The maximum operations we can perform by 
     * clearing the current block of '0's is **(Count of '1's to the left)**.
     *
     * * Algorithm:
     * 1. Iterate through the string, grouping consecutive identical characters.
     * 2. Maintain a running count of '1's encountered so far (`countOne`).
     * 3. When a block of **consecutive '0's** is found (after one or more '1's):
     * - Add the current `countOne` to the total operations (`ans`). This is because
     * each of the `countOne` '1's can be moved past this entire block of '0's in 
     * one operation.
     * 4. When a block of **consecutive '1's** is found:
     * - Update `countOne` by adding the size of this block.
     * 5. This greedy approach ensures we count the maximum possible operations for every 
     * `10` transition without needing to simulate the string moves.
     */
// Code -
 class Solution { 
      public int maxOperations(String s) {
        int countOne = 0; 
        int ans = 0;      
        int n = s.length();
        int i = 0;

        while (i < n) {
            char currentChar = s.charAt(i);

            if (currentChar == '0') {                
                int j = i;
                while (j < n && s.charAt(j) == '0') {
                    j++;
                }
                if (countOne > 0) {
                    ans += countOne;
                }
                i = j;

            }
             else { 
                int j = i;
                while (j < n && s.charAt(j) == '1')
                 {
                    countOne++; 
                    j++;
                }
                i = j;
            }
        }
        return ans;
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of the string 's'.
 * - The solution uses a single loop structure with a nested `while` loop, but the
 * indices `i` and `j` only move forward and cover the string exactly once (i.e., it's a 
 * two-pointer approach that effectively scans the string once).
 * * Space Complexity Analysis:
 * --------------------------
 * O(1)
 * - Only a few integer variables (`countOne`, `ans`, `i`, `j`, `n`) are used,
 * regardless of the input size N.
 */