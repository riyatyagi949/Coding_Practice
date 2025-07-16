// Problem Statement:
// Given a positive integer n, you need to count the numbers less than or equal to n having exactly 9 divisors.

// Approach:
// A number can have exactly 9 divisors in two ways:
// 1. It is of the form p^8, where p is a prime number.
//    In this case, the number of divisors is (8 + 1) = 9.
// 2. It is of the form p1^2 * p2^2, where p1 and p2 are distinct prime numbers.
//    In this case, the number of divisors is (2 + 1) * (2 + 1) = 3 * 3 = 9.

// We will precompute primes up to sqrt(n) for the second case and up to n^(1/8) for the first case.
// Since n can be up to 10^9, sqrt(n) is approximately 31622.
// n^(1/8) is approximately 7 for n = 10^9.

// For numbers of the form p^8:
// Iterate through prime numbers p. If p^8 <= n, increment the count.
// Since p^8 grows very fast, we only need to check a few primes.

// For numbers of the form p1^2 * p2^2:
// We need to find pairs of distinct primes (p1, p2) such that p1^2 * p2^2 <= n.
// This is equivalent to (p1 * p2)^2 <= n, or p1 * p2 <= sqrt(n).
// We can generate primes up to sqrt(n) using a sieve.
// Then, iterate through all pairs of distinct primes (p1, p2) from the generated list.
// If p1 * p2 <= sqrt(n), increment the count. To avoid double counting, ensure p1 < p2.

// We can optimize the second case by iterating through prime p1, and for each p1,
// iterate through prime p2 > p1 such that p2 <= sqrt(n) / p1.

// Time Complexity:
// The time complexity will be dominated by the sieve for primes up to sqrt(n) and iterating through pairs of primes.
// Sieve of Eratosthenes up to sqrt(N) takes O(sqrt(N) log log sqrt(N)).
// Iterating through pairs of primes: Let pi(x) be the number of primes less than or equal to x.
// The number of pairs will be roughly (pi(sqrt(N)))^2.
// In the worst case, for N = 10^9, sqrt(N) is about 31622. pi(31622) is about 3400.
// Iterating through pairs will be roughly O(pi(sqrt(N))^2). This might be too slow.
// A better approach for the second case:
// Iterate p1 from the prime list. For each p1, iterate p2 from the prime list starting after p1.
// The condition is p1 * p1 * p2 * p2 <= n.
// This can be rewritten as p2 <= sqrt(n) / p1.
// We can use a two-pointer approach or binary search to find the upper bound for p2 for each p1.
// If we iterate through p1, for each p1, we need to find how many p2's satisfy p2 < p1 and p2^2 * p1^2 <= n.
// A more efficient way for the second case is:
// Iterate with p1 from the list of primes. For each p1, iterate with p2 (where p2 > p1) such that p1*p1*p2*p2 <= n.
// Or, p2 <= sqrt(n / (p1*p1)).
// The time complexity will be roughly O(sqrt(N) log log sqrt(N) + (pi(sqrt(N)))^2 / log(sqrt(N))).
// A more precise analysis for the second part:
// For each prime p1, we iterate p2 up to sqrt(N)/p1. The total number of pairs will be roughly sum(pi(sqrt(N)/p1) for p1 in primes).
// This sum is roughly O(sqrt(N)/log(sqrt(N))) in total.
// So, the overall time complexity is dominated by the sieve, O(sqrt(N) log log sqrt(N)).

// Space Complexity:
// O(sqrt(N)) for storing primes using a boolean array for the sieve.

class Solution {
    public static int countNumbers(int n) {
        int maxLimit = (int)Math.sqrt(n) + 1;
        boolean[] isPrime = new boolean[maxLimit];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < maxLimit; i++) {
            if (isPrime[i]) {
                primes.add(i);
                for (int j = i * 2; j < maxLimit; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int count = 0;
        for (int p : primes) {
            long val = 1;
            for (int i = 0; i < 8; i++) val *= p;
            if (val <= n) count++;
            else break;
        }
        int size = primes.size();
        for (int i = 0; i < size; i++) {
            long pSquare = (long)primes.get(i) * primes.get(i);
            for (int j = i + 1; j < size; j++) {
                long qSquare = (long)primes.get(j) * primes.get(j);
                if (pSquare * qSquare <= n) count++;
                else 
                break;
            }
        }
          return count;
    }
}
