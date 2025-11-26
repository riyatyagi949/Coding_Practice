/**
 * Problem Statement: AND In Range
 * -------------------------------
 * You are given two integers 'l' and 'r'. Find the result after applying the series 
 * of Bitwise AND (&) operation on every natural number between the range 'l' to 'r' 
 * (including both).
 * Result = l & (l + 1) & ... & r
 *
 * Examples:
 * Input: l = 8, r = 13
 * Output: 8 (8 & 9 & 10 & 11 & 12 & 13 = 8)
 *
 * Constraints:
 * 1 <= l <= r <= 10^9
 */
 /**
     * Optimal Solution: Bit Manipulation and Identifying the Common Prefix
     * -------------------------------------------------------------------
     * The key insight is based on the nature of the Bitwise AND operation (&):
     * 1. A bit at a certain position is set to 1 in the final result only if 
     * that bit is 1 in *ALL* numbers within the range [l, r].
     * 2. For any position 'k', if the bit is 0 in the range [l, r], the final result's
     * k-th bit will be 0.
     *
     * Consider the binary representations of 'l' and 'r'. Since 'l' < 'r', they must
     * differ at some bit position. Let 'k' be the Most Significant Bit (MSB) where 'l' 
     * and 'r' differ (i.e., the prefix is the same up to bit k-1).
     * * Property: Any bit position to the RIGHT of the first differing bit (k) is guaranteed 
     * to become 0 in the final result.
     * This is because the numbers in the range [l, r] will necessarily contain at least one 
     * number where that bit is 0. Specifically, in the range [l, r], we are guaranteed to 
     * encounter a power of 2 (or a number whose suffix is all zeros) that falls between
     * l and r, causing all subsequent bits to be zeroed out.
     *
     * * The Optimal Algorithm:
     * The result is simply the **longest common binary prefix** of 'l' and 'r', followed by zeros.
     * We can find this common prefix by repeatedly right-shifting both 'l' and 'r' until they 
     * become equal.
     *
     * 1. Initialize a `shift` counter to 0.
     * 2. While `l` is not equal to `r`:
     * a. Right-shift both `l` and `r` by 1 bit (`l >>= 1`, `r >>= 1`).
     * b. Increment `shift` (counting how many bits were removed from the right).
     * 3. Once `l == r`, they hold the value of the common prefix.
     * 4. Left-shift the result (`l`) back by `shift` positions (`l << shift`) to restore 
     * the removed bits as zeros. This gives the final AND result.
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(log R), where R is the upper bound of the range.
 * - Since R <= 10^9, R is bounded by 30 bits. The 'while' loop runs at most 30 times 
 * (the number of bits in an integer).
 * - The number of iterations is proportional to the difference in the length of the binary 
 * representation of R, hence O(log R).
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - The algorithm uses only a few integer variables.
 */
// Code - 

 class Solution { 
        public int andInRange(int l, int r) {
        int shift = 0;

        while (l != r) {
            l >>= 1;
            r >>= 1;
            shift++;
        }
        return l << shift;
    }
}
