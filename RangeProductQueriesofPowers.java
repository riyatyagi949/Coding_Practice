// Problem Statement:
// Given a positive integer n, there exists a 0-indexed array called powers, composed of the minimum number of powers of 2 that sum to n. The array is sorted in non-decreasing order, and there is only one way to form the array.
// You are also given a 0-indexed 2D integer array queries, where queries[i] = [left_i, right_i]. Each queries[i] represents a query where you have to find the product of all powers[j] with left_i <= j <= right_i.
// Return an array answers, equal in length to queries, where answers[i] is the answer to the ith query. Since the answer to the ith query may be too large, each answers[i] should be returned modulo 10^9 + 7.

// Approach:
// 1. First, we need to find the powers of 2 that sum up to n. This is equivalent to finding the set bits in the binary representation of n. The powers array will contain 2^i for each bit i that is set in n. We can iterate from 0 up to 30 (since n <= 10^9, which is less than 2^30) and check if the i-th bit of n is set. If it is, we add 2^i to our powers list.
// 2. To efficiently handle the product queries, we can use a prefix product array. The prefix product at index i will store the product of all powers from index 0 to i, modulo 10^9 + 7.
// 3. Let's call this prefix product array `prefixProducts`. `prefixProducts[i] = powers[0] * powers[1] * ... * powers[i] % MOD`.
// 4. For each query `[left, right]`, the product of `powers[left]` to `powers[right]` can be calculated as `(prefixProducts[right] * modInverse(prefixProducts[left - 1])) % MOD`.
// 5. If `left` is 0, the product is simply `prefixProducts[right]`.
// 6. The modular inverse of a number `a` modulo `m` can be calculated using Fermat's Little Theorem since `m = 10^9 + 7` is a prime number. The modular inverse of `a` is `a^(m-2) % m`.
// 7. We can implement a function for modular exponentiation to calculate `a^(m-2) % m` efficiently.
// 8. We then iterate through the queries, calculate the product for each query using the prefix product array and modular inverse, and store the result in our answers array.

// Time Complexity:
// O(log n + q*log(MOD))
// - Building the powers array: O(log n) since we iterate up to log n (or 30).
// - Building the prefix products array: O(log n).
// - For each query: O(log(MOD)) to calculate the modular inverse using modular exponentiation.
// - q is the number of queries.

// Space Complexity:
// O(log n)
// - The `powers` array will have at most log n elements.
// - The `prefixProducts` array will also have at most log n elements.

import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int[] productQueries(int n, int[][] queries) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            if ((n & (1 << i)) != 0) {
                list.add(1 << i);
            }
        }

        int m = list.size();
        long[] prefix = new long[m];
        long[] invPrefix = new long[m];

        prefix[0] = list.get(0) % MOD;
        for (int i = 1; i < m; i++) {
            prefix[i] = (prefix[i - 1] * list.get(i)) % MOD;
        }

        for (int i = 0; i < m; i++) {
            invPrefix[i] = modPow(prefix[i], MOD - 2);
        }

        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0], r = queries[q][1];
            if (l == 0) {
                ans[q] = (int) prefix[r];
            } 
            else {
                ans[q] = (int) ((prefix[r] * invPrefix[l - 1]) % MOD);
            }
        }
        return ans;
    }
    private long modPow(long base, long exp) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}
