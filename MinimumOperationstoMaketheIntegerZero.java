/**
 * Problem Statement:
 * You are given two integers num1 and num2.
 * In one operation, you can choose integer i in the range [0, 60] and subtract 2^i + num2 from num1.
 * Return the integer denoting the minimum number of operations needed to make num1 equal to 0.
 * If it is impossible to make num1 equal to 0, return -1.
 */

    /**
     * Approach:
     * The goal is to find the minimum number of operations, `k`, such that `num1` becomes 0.
     * Each operation subtracts `2^i + num2` from `num1`.
     * If we perform `k` operations, the total value subtracted is the sum of `k` powers of 2, plus `k * num2`.
     * The equation is: `num1 - (sum of k powers of 2) - (k * num2) = 0`.
     * This can be rearranged to: `num1 - k * num2 = sum of k powers of 2`.
     * Let `diff = num1 - k * num2`.
     * We need to find the smallest positive integer `k` for which `diff` can be represented as a sum of exactly `k` powers of 2.
     * A number `diff` can be represented as the sum of `k` powers of 2 if and only if two conditions are met:
     * 1. `diff >= k`: The value `diff` must be at least as large as the number of terms (`k`).
     * 2. `Long.bitCount(diff) <= k`: The number of set bits (1s) in the binary representation of `diff` must be less than or equal to `k`. This is because a power of 2 has only one set bit, so to form `diff` with `k` powers of 2, we can at most use one `2^i` per `2^i` bit in `diff`, and any remaining `k - Long.bitCount(diff)` operations can be used to convert a larger power of 2 into two smaller ones (e.g., `2^3 = 2^2 + 2^2`).
     * The loop iterates through possible values of `k` from 1 to 60. For each `k`, it checks if the two conditions are satisfied for `diff = num1 - k * num2`. The first `k` that satisfies both conditions is the minimum number of operations.
     */

// Time and Space Complexity-
// Time Complexity: The time complexity of the provided solution is O(1). The for loop iterates a maximum of 60 times. Inside the loop, the operations, including Long.bitCount(), are constant time operations. Therefore, the total time complexity is constant and does not depend on the input values num1 and num2.

// Space Complexity: The space complexity is O(1). The solution uses a fixed number of variables (k, diff) and does not require any additional data structures whose size would scale with the input.




 class Solution {
    public int makeTheIntegerZero(int num1, int num2) 
    {
        for (int k = 1; k <= 60; k++)
         {
            long diff = (long) num1 - (long) k * num2;
            if (diff < k) 
            continue;
            if (Long.bitCount(diff) <= k && k <= diff) 
            return k;
        }
        return -1;
    }
}