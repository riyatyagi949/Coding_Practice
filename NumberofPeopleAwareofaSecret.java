// Problem Statement:
// On day 1, one person discovers a secret.
// You are given an integer delay, which means that each person will share the secret with a new person every day, starting from delay days after discovering the secret.
// You are also given an integer forget, which means that each person will forget the secret forget days after discovering it.
// A person cannot share the secret on the same day they forgot it, or on any day afterwards.
// Given an integer n, return the number of people who know the secret at the end of day n.
// Since the answer may be very large, return it modulo 10^9 + 7.

// Approach:
// This problem can be solved using dynamic programming. We can keep track of the number of people who know the secret on each day.
// Let `dp[i]` be the number of people who newly learn the secret on day `i`.
// The total number of people who know the secret on day `i` is the sum of people who learned it on previous days and haven't forgotten it yet.
//
// On day 1, one person learns the secret. So `dp[1] = 1`.
// For each day `i` from 2 to `n`, the number of new people who learn the secret is the sum of people who learned the secret on day `j` (where `j` is from `i - forget + 1` to `i - delay`) and can now share it.
// A person who learned the secret on day `j` can start sharing on day `j + delay`.
// A person who learned the secret on day `j` forgets it on day `j + forget`.
// So, a person who learned the secret on day `j` can share it on days `j + delay`, `j + delay + 1`, ..., `j + forget - 1`.
//
// The number of new people on day `i` (`dp[i]`) is the sum of `dp[j]` for all `j` such that `i - delay >= j` and `i < j + forget`.
// This simplifies to `dp[i] = sum(dp[j])` for `j` from `i - forget + 1` to `i - delay`.
//
// To optimize this, we can use two arrays:
// 1. `newlyInfected[i]` : number of people who learn the secret on day `i`.
// 2. `totalInfected[i]` : total number of people who know the secret at the end of day `i`.
//
// The recurrence relation is:
// `newlyInfected[i] = sum(newlyInfected[j])` for `j` from `max(1, i - forget + 1)` to `i - delay`.
// `totalInfected[i] = totalInfected[i-1] + newlyInfected[i] - newlyInfected[i - forget]`.
// Note: `newlyInfected[i - forget]` should be considered only if `i > forget`.
//
// The base case is `newlyInfected[1] = 1`.
// The modulo is `10^9 + 7`.
//
// Time Complexity: O(n)
// We iterate from day 2 to n. Inside the loop, we can calculate `newlyInfected[i]` efficiently in O(1) by maintaining a running sum of sharable people.
// The total sum of sharable people on day `i-1` is `totalSharable`. On day `i`, we add `newlyInfected[i-delay]` to the sum and subtract `newlyInfected[i-forget]` (if `i > forget`).
//
// Space Complexity: O(n)
// We use two arrays of size `n+1` to store the counts for each day.

class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int MOD = 1_000_000_007;

        long[] newlyInfected = new long[n + 1];
        long sharable = 0;
        long total = 0;

        newlyInfected[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            if (i > delay) {
                sharable = (sharable + newlyInfected[i - delay]) % MOD;
            }
            if (i > forget) {
                sharable = (sharable - newlyInfected[i - forget] + MOD) % MOD;
            }
            newlyInfected[i] = sharable;
        }

        for (int i = n; i > n - forget; i--) {
            total = (total + newlyInfected[i]) % MOD;
        }
        
        return (int) total;
    }
}