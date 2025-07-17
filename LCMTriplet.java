// Problem Statement:
// Given a number n. Find the maximum possible LCM that can be obtained by selecting three numbers less than or equal to n.

// Approach:
// The goal is to maximize the LCM of three numbers from 1 to n. This is achieved by selecting numbers that are as large as possible and are largely pairwise coprime.
//
// Special Cases:
// - If n is 1, the maximum LCM is 1 (triplet {1,1,1}).
// - If n is 2, the maximum LCM is 2 (triplet {1,2,2} or {2,2,2}).
// - If n is 3, the maximum LCM is 6 (triplet {1,2,3}).
//
// General Cases (n > 3):
// - If n is odd: The optimal triplet is typically {n, n-1, n-2}. These numbers are pairwise coprime, yielding an LCM of n * (n-1) * (n-2).
// - If n is even:
//   - If n is a multiple of 3 (i.e., n % 6 == 0): The optimal triplet is {n-1, n-2, n-3}. These are typically pairwise coprime or have smaller common factors, maximizing the LCM.
//   - If n is not a multiple of 3 (i.e., n % 6 == 2 or n % 6 == 4): The optimal triplet is typically {n, n-1, n-3}. These are often pairwise coprime.

// Time Complexity: O(1)
// Space Complexity: O(1)
class Solution {
    public long lcmTriplets(long n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 6;
        }

        if (n % 2 != 0) { 
            return n * (n - 1) * (n - 2);
        } else { // n is even
            if (n % 3 == 0) { // n is a multiple of 6
                return (n - 1) * (n - 2) * (n - 3);
            } else { // n is even, but not a multiple of 3
                return n * (n - 1) * (n - 3);
            }
        }
    }
}