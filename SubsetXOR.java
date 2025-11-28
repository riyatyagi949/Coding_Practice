/**
 * Problem Statement: Subset XOR
 * -----------------------------
 * Given a positive integer 'n', find a subset of numbers from 1 to n (inclusive), 
 * where each number can be used at most once, such that:
 * 1. The XOR of all elements in the subset is exactly 'n'.
 * 2. The size of the subset is as large as possible.
 * 3. If multiple such subsets exist, choose the lexicographically smallest one.
 *
 * Constraints: 1 <= n <= 10^5
 *//**
     * Helper function to calculate the XOR sum of all integers from 1 to N.
     * This is a known pattern based on N mod 4.
     */

/**
     * Optimal Solution: Prefix XOR and Greedy Exclusion
     * --------------------------------------------------
     * 1. **Maximize Size:** The largest possible subset is the set of all numbers from 1 to n, 
     * which has size 'n'. The goal is to achieve size 'n' or 'n-1'.
     * 2. **Prefix XOR:** Calculate the XOR sum of all numbers from 1 to n, let this be $X$.
     * $X = 1 \oplus 2 \oplus \dots \oplus n$.
     * 3. **Case 1: Size n is Possible**
     * If $X = n$, then the set $\{1, 2, \dots, n\}$ itself has the required XOR sum. 
     * Since this is the maximum size (n), this is the optimal solution.
     * 4. **Case 2: Size n-1 is Optimal**
     * If $X \neq n$, we must exclude at least one element to change the total XOR sum from $X$ to $n$.
     * Let $S$ be the required subset. The total XOR sum is $\bigoplus_{i \in S} i = n$.
     * If we exclude exactly one element $T \in \{1, 2, \dots, n\}$, the new XOR sum is:
     * $(1 \oplus 2 \oplus \dots \oplus n) \oplus T = n$
     * $X \oplus T = n$
     * $T = X \oplus n$ (Since $A \oplus B = C \implies A \oplus C = B$)
     * - The element we must exclude is $T = X \oplus n$.
     * - If $1 \le T \le n$, then excluding this single element $T$ results in a subset of size $n-1$ 
     * with the correct XOR sum. This is the maximum possible size (since size $n$ failed).
     * 5. **Lexicographical Smallest:** The subset is $\{1, 2, \dots, n\} \setminus \{T\}$. To make 
     * this subset lexicographically smallest, we must include the smallest numbers possible. 
     * Since we include $1, 2, \dots, T-1$ and $T+1, \dots, n$, and only exclude one $T$, this
     * approach inherently yields the lexicographically smallest subset of size $n-1$.
     *
     * *Edge Case:* The problem assumes an element $T$ exists. Since $X \neq n$, $T = X \oplus n \neq 0$.
     * Since all numbers are positive, $T$ will always be positive. The constraint $1 \le n \le 10^5$ 
     * ensures that $X$ is small enough that $T$ will also be within a reasonable range, though 
     * technically $T$ could be larger than $n$. However, due to properties of XOR sums of prefixes, 
     * $T$ is always $\le n$, or the two cases cover all possibilities.
     */

// Code -

import java.util.ArrayList;

 class Solution {
     private static int xor1toN(int n) {
        int mod = n % 4;
        if (mod == 0) return n;
        if (mod == 1) return 1;
        if (mod == 2) return n + 1;
        return 0;
    }
    public static ArrayList<Integer> subsetXOR(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        int X = xor1toN(n);
        
        if (X == n) 
            {
            for (int i = 1; i <= n; i++)
                 {
                ans.add(i);
            }
            return ans;
        }
        int target = X ^ n;
        
        for (int i = 1; i <= n; i++)
             {
            if (i != target)
                 {
                ans.add(i);
            }
        }
        return ans;
    }
}

/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the input integer 'n'.
 * - The `xor1toN` helper function runs in O(1) time (constant time lookup based on $N \pmod 4$).
 * - The main loop iterates N times to construct the final subset.
 * - Overall complexity: O(N).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the input integer 'n'.
 * - O(N) space is required to store the resulting `ArrayList` (the subset), which has a size of $N$ or $N-1$.
 */
