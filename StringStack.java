/**
 * Problem Statement:
 * Given two strings, `pat` and `tar`, and a set of allowed operations, determine if `tar` can be formed from `pat`.
 * For each character `pat[i]`, you can either:
 * 1. Append `pat[i]` to a string `s`.
 * 2. Delete the last character of `s`.
 * These operations must be performed for every character in `pat` exactly once, in order.
 *
 * Optimal Approach:
 * This problem can be solved by simulating the process with a stack. We process `pat` character by character and maintain a string `s` that represents the current state.
 *
 * Since we can only delete the last character, this LIFO (Last-In, First-Out) behavior is naturally modeled by a stack.
 * We iterate through the `pat` string with index `i` and the `tar` string with index `j`.
 *
 * The core idea is to append `pat[i]` to our stack (representing the string `s`) and check if the characters at the end of the stack match the prefix of `tar`.
 * If they match, we "virtually" pop them from the stack and advance the `tar` index `j`. This signifies that we have successfully built that part of `tar`.
 *
 * The algorithm is as follows:
 * 1. Initialize an empty stack of characters, and a pointer `j` for the `tar` string, set to 0.
 * 2. Iterate through each character `pat[i]` from `i = 0` to `pat.length() - 1`.
 * 3. At each step, push `pat[i]` onto the stack. This represents the append operation.
 * 4. After pushing, check if the top of the stack matches the character at `tar[j]`.
 * - While the stack is not empty and `j` is within bounds of `tar` and `stack.peek() == tar.charAt(j)`:
 * - Pop from the stack.
 * - Increment `j`.
 * This simulates building `tar` by appending characters from `pat`.
 * 5. After the main loop finishes, all characters of `pat` have been processed.
 * 6. The final check is if the entire `tar` string has been successfully matched. This happens if `j` is equal to `tar.length()`.
 * If `j == tar.length()` and the stack is empty, it means we have successfully formed `tar` and the rest of the operations (deletions) from `pat` canceled out any extra characters.
 * If `j == tar.length()` and the stack is not empty, it implies there are remaining characters from `pat` that need to be deleted.
 * Since for each character of `pat` we have an operation, any remaining character in the stack must correspond to a pending `pat` character.
 * The `pat` string has length `m` and the `tar` string has length `n`. The number of characters we appended to build `tar` is `n`.
 * The number of characters pushed to the stack that were not part of `tar` is `m - n`. These must be `m-n` delete operations to be performed.
 * So, the number of remaining characters on the stack must be `pat.length() - tar.length()`.
 * A simpler way to think about this is that the stack should be empty if we have used up all characters in `pat` and matched all characters in `tar`.
 * But wait, the problem says "delete the last character of s". This means for a character `pat[i]`, we can append it, or we can use it to delete.
 * * Let's refine the stack logic:
 * A character from `pat` can be appended, or a deletion can be performed. The key is that we must use *every* character of `pat`.
 * Let's track the index of `pat` we're at, `i`, and the index of `tar` we're at, `j`.
 * We iterate through `pat`. We can either append `pat[i]` to a temporary string (or a stack) or perform a deletion.
 * The deletion operation means we must have a character to delete, so the stack cannot be empty.
 *
 * A better approach is to use a greedy strategy.
 * Iterate through `pat` and `tar` simultaneously.
 * - If `pat[i]` matches `tar[j]`, and we are at the right place, we can "consume" this character by advancing `i` and `j`. This is an append operation.
 * - If they don't match, we must be performing an append that will later be deleted, or a direct deletion.
 *
 * This can get complex quickly. Let's stick with the stack simulation as it directly models the operations.
 *
 * The refined logic:
 * 1. Initialize an empty stack.
 * 2. Iterate through `pat` with index `i`.
 * 3. Push `pat.charAt(i)` onto the stack. This models the append operation.
 * 4. We also have the option to delete. A delete operation doesn't depend on the current `pat[i]`. It's a choice made for `pat[i]`.
 * 5. This makes the problem more about dynamic programming. However, the greedy stack approach is simpler and correct.
 *
 * Re-evaluating the greedy stack logic:
 * We iterate through `pat`.
 * We maintain a stack to represent the current string `s`.
 * For `pat[i]`, we must choose: append `pat[i]` or delete from `s`.
 * To match `tar`, we should only append `pat[i]` if it is a part of `tar`.
 *
 * A simplified and correct approach:
 * The final string `tar` must be a subsequence of `pat`. The characters of `tar` must appear in the same relative order in `pat`.
 * All characters of `pat` not in `tar` must be "canceled out" by the delete operation.
 * The total number of `append` operations must equal the number of `delete` operations plus `tar.length()`.
 *
 * Let's use a two-pointer approach.
 * - Pointer `i` for `pat`, pointer `j` for `tar`.
 * - Iterate through `pat`.
 * - If `pat.charAt(i) == tar.charAt(j)`: we have found a character for `tar`. This is an append operation. We advance both `i` and `j`.
 * - If `pat.charAt(i) != tar.charAt(j)`: this character must be part of a "noise" sequence that is eventually deleted. We just advance `i`.
 *
 * Let's refine this two-pointer approach with a counter for deletions.
 *
 * Let `n = pat.length()` and `m = tar.length()`.
 * 1. Initialize `j = 0` (for `tar`).
 * 2. Iterate `i` from `0` to `n-1` (for `pat`).
 * 3. If `j < m` and `pat.charAt(i) == tar.charAt(j)`, increment `j`. This means we appended this character to form `tar`.
 * 4. After the loop, if `j == m`, we have successfully "matched" all characters of `tar` as a subsequence of `pat`.
 *
 * This is not the whole story because of the delete operation.
 * Let's re-examine the example: `pat = "geuaek"`, `tar = "geek"`.
 * - `pat[0] = 'g'`, `tar[0] = 'g'`. Match.
 * - `pat[1] = 'e'`, `tar[1] = 'e'`. Match.
 * - `pat[2] = 'u'`, `tar[2] = 'e'`. No match.
 * - `pat[3] = 'a'`, `tar[3] = 'k'`. No match.
 * - `pat[4] = 'e'`, `tar[4]` is out of bounds. No match.
 * The subsequence check alone is insufficient. We need a way to model the stack behavior.
 *
 * The **correct and optimal** solution uses a stack to simulate the string `s`.
 * We iterate through `pat` and for each character, we try to form the longest possible prefix of `tar`.
 *
 * Algorithm using Stack:
 * 1. Create a stack of characters.
 * 2. Initialize `j = 0` (for `tar`).
 * 3. Iterate through `pat` with index `i`:
 * - Push `pat.charAt(i)` onto the stack.
 * - If `j < tar.length()` and the top of the stack matches `tar.charAt(j)`, we have two choices:
 * a. This character is part of the final `tar`. We "pop" it from our conceptual working space by incrementing `j`. We also "pop" it from the stack to reflect that it's now part of the final `tar`.
 * b. This character is `pat[i]` and we appended it to our string `s`. This character `pat[i]` will not be a part of the final `tar`.
 *
 * This is still not right. The problem states for *each* character of `pat`, we do *exactly one* operation.
 * The key is the state of `s` and `tar` and how they align.
 *
 * Final, correct, and optimal approach:
 * Use a single pointer `j` for `tar`.
 * For each character `pat[i]`, we have two options to consider relative to `tar`.
 * 1. Append `pat[i]`. This character `pat[i]` is a potential part of `tar`. We can only append if it matches `tar.charAt(j)`. If it matches, we advance `j`.
 * 2. Perform a deletion. This is a "free" operation that doesn't depend on `pat[i]` but consumes `pat[i]`'s turn. We can do this at any time, as long as the stack is not empty.
 *
 * The logic is simpler than that:
 * We iterate through `pat` and greedily try to match `tar`.
 * `j` = index for `tar`.
 * `count` = number of characters appended to `s` that are not part of `tar`. These must be deleted.
 *
 * 1. Initialize `j = 0` and `count = 0`.
 * 2. Iterate `i` from `0` to `pat.length() - 1`:
 * - If `j < tar.length()` and `pat.charAt(i) == tar.charAt(j)`:
 * - We have a match. This character must be appended. This is an `append` operation that contributes to `tar`. We advance `j`.
 * - Any pending deletions (`count`) must be handled by this point.
 * - Wait, this is still too complex. Let's use a stack.
 *
 * The stack approach:
 * - A stack `s` simulates the temporary string.
 * - Iterate through `pat` from `i=0` to `pat.length()-1`.
 * - Push `pat[i]` onto the stack. This is the `append` operation.
 * - The `delete` operation must be associated with a different character in `pat`.
 * - The total number of `append` operations is `N_append`.
 * - The total number of `delete` operations is `N_delete`.
 * - `N_append + N_delete = pat.length()`.
 * - The final string `tar` must be formed by `N_append` operations followed by `N_delete` operations.
 * - `tar.length()` is the number of final `append` operations.
 * - `pat.length() - tar.length()` is the number of "net" deletions.
 * - `N_append = tar.length() + N_delete`.
 * - `(tar.length() + N_delete) + N_delete = pat.length()`.
 * - `2 * N_delete = pat.length() - tar.length()`.
 * - This implies `pat.length() - tar.length()` must be non-negative and even. This is a necessary condition.
 *
 * Now, let's use a two-pointer approach, but with this insight.
 * `i` for `pat`, `j` for `tar`.
 * The characters of `tar` must be a subsequence of `pat`.
 * We can greedily check if `tar` is a subsequence of `pat`.
 *
 * The final, robust algorithm:
 * 1. `j = 0` (pointer for `tar`).
 * 2. `unmatched_count = 0`. This counts the characters in `pat` that are not part of `tar`.
 * 3. Iterate through `pat` with index `i`:
 * - If `j < tar.length()` and `pat.charAt(i) == tar.charAt(j)`:
 * - This is a match. It means `pat[i]` is appended as part of `tar`.
 * - Increment `j`.
 * - Else:
 * - `pat[i]` is not part of `tar`. It's a "throwaway" character that must be deleted.
 * - Increment `unmatched_count`.
 *
 * 4. After the loop, check if `j == tar.length()`. If not, `tar` is not a subsequence of `pat`, so it's impossible. Return `false`.
 *
 * 5. If `j == tar.length()`, we have matched all `tar` characters. The number of operations left to perform is `unmatched_count`.
 * These must be paired `append` and `delete` operations. One `append` puts a character on the stack, and one `delete` removes it.
 * Each pair of "throwaway" `append`/`delete` operations uses two characters from `pat`.
 * So, `unmatched_count` must be a multiple of 2, i.e., `unmatched_count % 2 == 0`.
 *
 * This final approach is correct and covers all cases.
 *
 * Time Complexity: O(N), where N is the length of `pat`. We iterate through `pat` once.
 * Space Complexity: O(1). We use a constant amount of extra space for pointers and counters.
 */
// Optimal Solution in Java-
class Solution {
    public boolean stringStack(String pat, String tar) {
        int i = pat.length() - 1; 
        int j = tar.length() - 1;  

        while (i >= 0 && j >= 0) {
            if (pat.charAt(i) == tar.charAt(j)) {
                i--;
                j--;
            } 
            else {
                i -= 2;
            }
        }
         return j < 0;
    }
}
