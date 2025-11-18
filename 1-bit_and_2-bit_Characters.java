/**
 * Problem Statement: 1-bit and 2-bit Characters
 * ----------------------------------------------
 * We have two special characters:
 * 1. 1-bit character: represented by one bit '0'.
 * 2. 2-bit character: represented by two bits '10' or '11' (starts with '1').
 * Given a binary array 'bits' that is guaranteed to end with '0', return true 
 * if the last character (which must be '0' due to the constraint) must be a 1-bit character.
 *
 * Example 1: bits = [1, 0, 0] -> true (Decoded as [1, 0] then [0])
 * Example 2: bits = [1, 1, 1, 0] -> false (Decoded as [1, 1] then [1, 0])
 */
/**
     * Optimal Solution: Greedy Iteration / Simple Pointer Traversal
     * ------------------------------------------------------------
     * The decoding process is uniquely determined because a 1-bit character is always '0', 
     * and a 2-bit character always starts with '1'. When a '1' is encountered, it *must* * be the start of a 2-bit character, consuming the next two indices. When a '0' 
     * is encountered, it *must* be a 1-bit character, consuming one index.
     * * We traverse the array from the beginning until we reach the **second to last** element (index bits.length - 2).
     * * * Algorithm Steps:
     * 1. Initialize a pointer 'i' at the start (0).
     * 2. Iterate while 'i' is less than the index of the last '0' (bits.length - 1):
     * a. If bits[i] is '1', it starts a 2-bit character. Advance the pointer by 2 (i += 2).
     * b. If bits[i] is '0', it is a 1-bit character. Advance the pointer by 1 (i += 1).
     * 3. After the loop, the pointer 'i' will land on the index immediately following the 
     * last fully decoded character *before* the final one.
     * 4. If the final value of 'i' is exactly `bits.length - 1`, it means the character
     * just before the final '0' was a 1-bit character, leaving the last '0' as a 
     * standalone 1-bit character. Return true.
     * 5. If the final value of 'i' is `bits.length`, it means the character just before the 
     * final '0' was a 2-bit character that *consumed* the final '0' bit. In this case, 
     * the last character is not a 1-bit character. The loop condition prevents this, 
     * but the core logic ensures the result is correct. (Since the loop condition is i < bits.length - 1, 
     * the final '0' is only counted as a 1-bit character if the loop terminates exactly one step 
     * before the end).
     */
 // Optimal Solution in Java -
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        int n = bits.length;
        
        while (i < n - 1)
         {
            if (bits[i] == 1) {
                i += 2;
            } 
            else {
                i += 1;
            }
        }
        return i == n - 1;
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of the 'bits' array.
 * - The algorithm iterates through the array using a single pointer 'i'. 
 * - In each step, 'i' advances by either 1 or 2. 
 * - Therefore, the loop runs at most N times, leading to a linear time complexity.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - The algorithm uses only a few integer variables ('i', 'n') regardless of the input size.
 */
