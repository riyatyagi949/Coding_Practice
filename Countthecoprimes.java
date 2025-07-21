/*
Problem Statement:
You are given an array arr[] of positive integers. Your task is to count the number of pairs (i, j) such that:
0 <= i < j <= n-1
gcd(arr[i], arr[j]) = 1
In other words, count the number of unordered pairs of indices (i, j) where the elements at those positions are co-prime.

Approach:
The problem asks us to count pairs (arr[i], arr[j]) such that their greatest common divisor (GCD) is 1. A naive approach would be to iterate through all possible pairs (i, j) with i < j and compute the GCD for each pair, incrementing a counter if the GCD is 1. This would be O(N^2 * log(max(arr[i]))) which might be too slow given N up to 10^4.

A more efficient approach involves using the Principle of Inclusion-Exclusion and properties of GCD.
Let's find the maximum value in the array, say `MAX_VAL`. We can precompute prime numbers up to `MAX_VAL` using a sieve.
Instead of directly counting coprime pairs, we can count the total number of pairs and subtract pairs that are NOT coprime.
However, it's easier to use a different perspective.

Consider the total number of pairs of elements in the array. For each prime `p`, we can count how many pairs have `p` as a common divisor.
The number of pairs (a, b) such that `gcd(a, b) = 1` can be expressed using Mobius inversion.
Specifically, `sum_{d=1 to MAX_VAL} mu(d) * (count of pairs (x, y) where d divides both x and y)`.
Here, `mu(d)` is the Mobius function.

Let `count[k]` be the number of occurrences of `k` in the input array `arr`.
Let `multiples[d]` be the count of numbers in `arr` that are multiples of `d`.
We can calculate `multiples[d]` by iterating from `d` up to `MAX_VAL` in steps of `d`, and adding `count[k]` for each `k` that is a multiple of `d`.
`multiples[d] = sum_{k=1 to MAX_VAL/d} count[k*d]`

The number of pairs whose GCD is a multiple of `d` is `multiples[d] * (multiples[d] - 1) / 2`.
Let `g[d]` be the number of pairs (x, y) from the array such that `d` divides `gcd(x, y)`. This is equal to `multiples[d] * (multiples[d] - 1) / 2`.

The number of pairs whose `gcd(x, y) = 1` is given by:
`sum_{d=1 to MAX_VAL} mu(d) * g[d]`

Algorithm:
1. Find `MAX_VAL`, the maximum element in `arr`.
2. Create a frequency array `count` for elements in `arr`. `count[k]` stores the number of times `k` appears in `arr`.
3. Precompute Mobius function `mu` and an array for smallest prime factor (or just primes for sieve) up to `MAX_VAL`.
   - `mu[1] = 1`.
   - For `i` from 2 to `MAX_VAL`:
     - If `i` is prime, `mu[i] = -1`.
     - For composite `i`, `mu[i] = 0` if `i` has a squared prime factor, otherwise `mu[i] = (-1)^k` where `k` is the number of distinct prime factors.
     A standard sieve for Mobius function works by iterating through primes and marking multiples.
4. Calculate `multiples[d]` for `d` from 1 to `MAX_VAL`.
   Initialize `multiples` array to zeros.
   For `i` from 1 to `MAX_VAL`:
     For `j` from `i` to `MAX_VAL` in steps of `i`:
       `multiples[i] += count[j]`
   Alternatively, initialize `multiples[i] = count[i]`. Then for `i` from 1 to `MAX_VAL`:
     For `j` from `2*i` to `MAX_VAL` in steps of `i`:
       `multiples[i] += multiples[j]`
   This latter approach calculates `multiples[d]` such that `multiples[d]` is the count of numbers in `arr` that are multiples of `d`.

5. Calculate the total number of coprime pairs:
   Initialize `ans = 0`.
   For `d` from 1 to `MAX_VAL`:
     `num_pairs_with_d_as_common_divisor_multiple = (long)multiples[d] * (multiples[d] - 1) / 2;`
     `ans += mu[d] * num_pairs_with_d_as_common_divisor_multiple;`

Return `ans`.

Edge Cases:
- `arr` size up to 10^4. `arr[i]` up to 10^4.
- `MAX_VAL` is at most 10^4.

Time Complexity:
1. `MAX_VAL` calculation: O(N)
2. Frequency array `count`: O(N)
3. Sieve for Mobius function: O(MAX_VAL * log(log(MAX_VAL)))
4. Calculating `multiples` array: This can be done efficiently.
   One way: For `i` from 1 to `MAX_VAL`, for `j` from `i` to `MAX_VAL` in steps of `i`, add `count[j]` to `multiples[i]`.
   This is `sum_{i=1 to MAX_VAL} (MAX_VAL / i) = O(MAX_VAL * log(MAX_VAL))`.
   Another way: Initialize `multiples[k] = count[k]`. Then iterate `i` from `MAX_VAL` down to 1. For each `i`, iterate through its multiples `j = 2*i, 3*i, ...` up to `MAX_VAL` and add `multiples[j]` to `multiples[i]`. This is also `O(MAX_VAL * log(MAX_VAL))`.
5. Final summation: O(MAX_VAL)

Total Time Complexity: O(N + MAX_VAL * log(MAX_VAL)). Given N and MAX_VAL are both up to 10^4, this is roughly 10^4 * log(10^4) which is feasible.

Space Complexity:
O(MAX_VAL) for `count`, `multiples`, `mu`, and prime-related arrays for sieve.
Given `MAX_VAL` up to 10^4, this is fine.

Optimal Solution:
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public long countCoprimePairs(int[] arr) {
        int MAX_VAL = 0;
        for (int x : arr) {
            MAX_VAL = Math.max(MAX_VAL, x);
        }

        int[] count = new int[MAX_VAL + 1];
        for (int x : arr) {
            count[x]++;
        }

        int[] mu = new int[MAX_VAL + 1];
        int[] spf = new int[MAX_VAL + 1]; // smallest prime factor
        List<Integer> primes = new ArrayList<>();

        Arrays.fill(spf, 0); // 0 indicates not processed or no prime factor yet
        mu[1] = 1;

        for (int i = 2; i <= MAX_VAL; i++) {
            if (spf[i] == 0) { // i is prime
                spf[i] = i;
                primes.add(i);
                mu[i] = -1;
            }

            for (int p : primes) {
                if (p > spf[i] || i * p > MAX_VAL) {
                    break;
                }
                spf[i * p] = p;
                if (p == spf[i]) { // i*p has p^2 as a factor
                    mu[i * p] = 0;
                } else {
                    mu[i * p] = -mu[i];
                }
            }
        }

        long[] multiples = new long[MAX_VAL + 1];
        for (int i = 1; i <= MAX_VAL; i++) {
            for (int j = i; j <= MAX_VAL; j += i) {
                multiples[i] += count[j];
            }
        }

        long ans = 0;
        for (int d = 1; d <= MAX_VAL; d++) {
            long numPairs = multiples[d] * (multiples[d] - 1) / 2;
            ans += (long) mu[d] * numPairs;
        }

        return ans;
    }
}