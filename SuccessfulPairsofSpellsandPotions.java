/**
 * Problem Statement:
 * Given two arrays, `spells` (length n) and `potions` (length m), and a required product `success`.
 * A pair (spell[i], potion[j]) is successful if `spell[i] * potion[j] >= success`.
 * Return an array `pairs` of length n, where `pairs[i]` is the number of potions that form a successful pair with the i-th spell.
 *
 * Optimal Approach:
 * A brute-force approach would be to iterate through all spells and for each spell, iterate through all potions, resulting in O(n*m) complexity, which is too slow for the given constraints (n, m up to 10^5).
 *
 * We can optimize this using **Sorting and Binary Search**.
 *
 * 1. **Sort the `potions` array**: Sorting allows us to efficiently find the minimum required potion strength using binary search. This takes O(m log m) time.
 *
 * 2. **Iterate through `spells`**: For each spell `s = spells[i]`:
 * a. **Calculate the Minimum Required Potion Strength**: For a successful pair, we need `s * p >= success`, where `p` is the potion strength.
 * Solving for `p`, we get `p >= success / s`.
 * Since `success` can be up to $10^{10}$, we must use `long` to calculate the required minimum strength to avoid overflow, as $s$ is an integer.
 * The required minimum potion strength, `minPotion`, is $\lceil \frac{success}{s} \rceil$.
 * In integer arithmetic, this is calculated as: `minPotion = (success + s - 1) / s`.
 *
 * b. **Find the count using Binary Search (Lower Bound)**: In the sorted `potions` array, we need to find the index of the **first potion** with strength $\ge$ `minPotion`. This is a classic **Lower Bound** problem.
 * - The standard `Arrays.binarySearch()` method in Java can be tricky with insertion points, so it's safer to implement a custom binary search or use a method that specifically finds the lower bound.
 * - The resulting index, say `idx`, gives the starting point of successful potions.
 * - All potions from index `idx` to `m-1` will be successful.
 * - The number of successful pairs for the current spell is `m - idx`.
 *
 * 3. **Store the result**: Save the count `m - idx` into the result array at index `i`.
 *
 * 4. **Return the result array**.
 *
 * Time Complexity:
 * - Sorting `potions`: **O(m log m)**.
 * - Iterating through `spells` and performing a binary search for each: **O(n log m)**.
 * - Total Time Complexity: **O(m log m + n log m)**. Given $n, m \le 10^5$, this is highly efficient.
 *
 * Space Complexity:
 * - O(n) for the result array.
 * - O(1) or O(m) depending on the sort implementation (in Java's `Arrays.sort()`, it's $O(\log m)$ auxiliary space for merge sort or $O(1)$ for quicksort, but often simplified to $O(m)$ for non-primitive types). For primitive `int[]`, it's typically $O(\log m)$. We'll state **O(n)** for the result array and auxiliary space for sorting.
 */
// Optimal Solution in Java - 
import java.util.*;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) 
    {
        Arrays.sort(potions);
        int n = spells.length, m = potions.length;
        int[] res = new int[n];
        
        for (int i = 0; i < n; i++) {
            long minPotion = (success + spells[i] - 1) / spells[i]; 
            int idx = lowerBound(potions, minPotion);
            res[i] = m - idx;
        }
        return res;
    }
    private int lowerBound(int[] arr, long target)
     {
        int l = 0, r = arr.length;
        while (l < r)
         {
            int mid = l + (r - l) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}