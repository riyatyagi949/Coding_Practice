/*
Problem Statement:
Given two positive integers n and k, determine the highest value of x such that k^x divides n! (n factorial) completely (i.e., n! % (k^x) == 0).

Approach:
The problem asks us to find the highest power of k that divides n!.
First, we need to find the prime factorization of k. Let k = p1^a1 * p2^a2 * ... * pm^am.
For k^x to divide n!, each prime factor pi^ai in k must contribute its share to n! such that (p1^a1 * p2^a2 * ... * pm^am)^x divides n!. This means p1^(a1*x) * p2^(a2*x) * ... * pm^(am*x) must divide n!.

The number of times a prime p divides n! is given by Legendre's formula:
count = floor(n/p) + floor(n/p^2) + floor(n/p^3) + ...

Let's say a prime factor pi has a power of `count_pi` in n!.
For k^x to divide n!, we need (a_i * x) <= count_pi for all i from 1 to m.
This implies x <= count_pi / a_i for all i.
Therefore, the maximum possible value of x will be the minimum of (count_pi / a_i) for all prime factors pi of k.

Steps:
1. Handle the base case where k = 1. In this case, 1^x always divides n!, so x can be infinitely large. However, since the problem implies k >= 2, this case is usually not relevant. But if it were, the answer would be Integer.MAX_VALUE or a very large number. Given constraints, k is at least 2.
2. Find the prime factorization of k. Store the prime factors and their counts in a map (e.g., HashMap<Integer, Integer>).
   Iterate from 2 up to sqrt(k). If i divides k, increment its count and divide k by i until it's no longer divisible. If k is still greater than 1 after the loop, the remaining k is a prime factor itself.
3. For each unique prime factor `p` of `k` with count `a` (i.e., p^a is in the prime factorization of k):
   a. Calculate the number of times `p` divides `n!`. Let this be `count_p_in_n_factorial`.
      This can be done using Legendre's formula: `sum(n / (p^i))` for `i = 1, 2, ...` as long as `p^i <= n`.
   b. Calculate the maximum power `x_p` such that `p^(a * x_p)` divides `n!`. This is `count_p_in_n_factorial / a`.
4. The final answer `x` is the minimum of all `x_p` calculated in step 3. Initialize `min_x` to `Integer.MAX_VALUE`.

Example: n = 10, k = 9
Prime factorization of k = 9: 3^2. So p1 = 3, a1 = 2.
Count of 3 in 10!:
  floor(10/3) = 3
  floor(10/9) = 1
  Total count_3_in_10_factorial = 3 + 1 = 4.
For prime 3, we need (2 * x) <= 4, so x <= 4/2 = 2.
Since there's only one prime factor, min_x = 2.

Time Complexity:
1. Prime factorization of k: O(sqrt(k)).
2. For each prime factor p of k, calculating its count in n!:
   The loop for `count_p_in_n_factorial` runs `log_p(n)` times.
   In the worst case, k can have many small prime factors (e.g., product of first few primes).
   The number of distinct prime factors for k is small (at most around log k).
   So, approximately O(log k * log_min_prime(n)).
Overall time complexity: O(sqrt(k) + D * log(n)) where D is the number of distinct prime factors of k. Since D <= log k, it's roughly O(sqrt(k) + log k * log n).

Space Complexity:
O(log k) to store the prime factors of k in a HashMap.

Optimal Solution:
The approach described is optimal.

*/
import java.util.HashMap;
import java.util.Map;

class Solution {
    long countPrimeFactorsInFactorial(int n, int p) {
        long count = 0;
        long currentPowerOfP = p;
        while (currentPowerOfP <= n) {
            count += n / currentPowerOfP;
            if (currentPowerOfP > n / p) { // Avoid overflow for currentPowerOfP * p
                break;
            }
            currentPowerOfP *= p;
        }
        return count;
    }

    public int power(int n, int k) {
        if (k == 1) {
            return Integer.MAX_VALUE; // As 1^x always divides n!, x can be arbitrarily large.
        }

        Map<Integer, Integer> primeFactorsK = new HashMap<>();
        int tempK = k;

        // Find prime factorization of k
        for (int i = 2; i * i <= tempK; i++) {
            while (tempK % i == 0) {
                primeFactorsK.put(i, primeFactorsK.getOrDefault(i, 0) + 1);
                tempK /= i;
            }
        }
        if (tempK > 1) {
            primeFactorsK.put(tempK, primeFactorsK.getOrDefault(tempK, 0) + 1);
        }

        int minX = Integer.MAX_VALUE;

        // For each prime factor of k, calculate the maximum power x
        for (Map.Entry<Integer, Integer> entry : primeFactorsK.entrySet()) {
            int prime = entry.getKey();
            int countInK = entry.getValue();

            long countInNFactorial = countPrimeFactorsInFactorial(n, prime);

            minX = Math.min(minX, (int) (countInNFactorial / countInK));
        }

        return minX;
    }
}