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

class Solution {
    public int nextBeautifulNumber(int n) {
        int maxLen = 7;
        TreeSet<Integer> set = new TreeSet<>();
        
        for (int mask = 1; mask < (1 << 9); mask++) {
            int len = 0;
            for (int b = 0; b < 9; b++) {
                if ((mask & (1 << b)) != 0) {
                    len += (b + 1);
                }
            }
            if (len == 0 || len > maxLen) continue;
            
            char[] arr = new char[len];
            int idx = 0;
            for (int b = 0; b < 9; b++) {
                if ((mask & (1 << b)) != 0) {
                    char ch = (char)('0' + (b + 1));
                    int repeat = b + 1;
                    for (int r = 0; r < repeat; r++) {
                        arr[idx++] = ch;
                    }
                }
            }
            Arrays.sort(arr);
            do {
                if (arr[0] == '0') continue; 
                int val = 0;
                for (char c : arr) val = val * 10 + (c - '0');
                set.add(val);
            }
             while (nextPermutation(arr));
        }
            Integer ans = set.higher(n);
            return ans == null ? -1 : ans;
    }
    private boolean nextPermutation(char[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] >= a[i + 1]) i--;
        if (i < 0)
         return false;
         
        int j = a.length - 1;
        while (a[j] <= a[i]) j--;
        swap(a, i, j);
        reverse(a, i + 1, a.length - 1);
        return true;
    }
    private void swap(char[] a, int i, int j) {
        char t = a[i]; a[i] = a[j]; a[j] = t;
    }
    private void reverse(char[] a, int l, int r) {
        while (l < r) swap(a, l++, r--);
    }
}
