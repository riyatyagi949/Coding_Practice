/**
 * Problem Statement: Next Greater Numerically Balanced Number
 * -----------------------------------------------------------
 * An integer 'x' is numerically balanced if for every digit 'd' in the number 'x', 
 * there are exactly 'd' occurrences of that digit in 'x'. (Note: this implies the 
 * digit 0 cannot be present, as 0 must appear 0 times if present).
 * Given an integer 'n', return the smallest numerically balanced number strictly greater than 'n'.
 * Constraints: 0 <= n <= 10^6.
 */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(1) (Constant Time)
 * - The time complexity is dominated by the **pre-computation** of all possible NBNs.
 * - The number of NBNs and the maximum length L (<= 8) are tiny constants. 
 * - The DFS explores a fixed, small search space, and the subsequent sorting of the small list 
 * of NBNs is also constant time.
 * * Space Complexity Analysis:
 * --------------------------
 * O(1) (Constant Space)
 * - The space is used to store the constant number of generated NBNs (Set<Long> allNbns) 
 * and the recursive call stack for the DFS (max depth L=8). Since all storage is bounded 
 * by small constants, the complexity is O(1).
 */
// Optimal Solution in Java-
import java.util.*;
