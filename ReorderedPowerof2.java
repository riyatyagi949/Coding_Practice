/**
 * Problem: 869. Reordered Power of 2
 * You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.
 * Return true if and only if we can do this so that the resulting number is a power of two.
 *
 * Approach:
 * The core idea is to realize that if a number `n` can be reordered to form a power of two, then the frequency count of its digits must match the frequency count of the digits of some power of two.
 * Since the constraints for `n` are up to $10^9$, we only need to check powers of two that have the same number of digits as `n`. The maximum number of digits for a number up to $10^9$ is 10.
 * We can pre-compute the digit counts for all powers of two up to $10^9$.
 * A number is a power of two if it can be written as $2^k$.
 * $2^{29}$ is approximately $5 \times 10^8$ and $2^{30}$ is approximately $10^9$.
 * Powers of two up to $10^9$ are $2^0, 2^1, \dots, 2^{29}$.
 * So, we can generate all powers of two up to $10^9$ and store their digit frequency counts.
 * Then, for the given number `n`, we calculate its digit frequency count.
 * We compare this count with the pre-computed counts of all powers of two.
 * If we find a match, we return `true`. Otherwise, we return `false`.
 * A simple way to get the digit frequency count of a number is to use an array of size 10.
 *
 * Time Complexity:
 * The pre-computation of powers of two takes a constant amount of time as we only need to go up to $2^{29}$.
 * Let `L` be the number of digits in `n`. The number of powers of two up to $10^9$ is small (around 30).
 * Calculating the digit count for `n` takes $O(L)$ time, where $L$ is at most 10.
 * Comparing `n`'s digit count with all pre-computed powers of two takes $O(k * 10)$, where `k` is the number of powers of two, and 10 is the number of digits (0-9).
 * Since `k` is small and constant, the overall time complexity is dominated by the initial digit counting, which is $O(\log_{10} n)$.
 *
 * Space Complexity:
 * We need to store the digit counts for the powers of two.
 * Since the number of powers of two up to $10^9$ is small (around 30), and each count is an array of size 10, the space used is constant. $O(30 \times 10)$.
 * So, the space complexity is $O(1)$.
 */
import java.util.*;

class Solution {
    public boolean reorderedPowerOf2(int n) {
        String target = countDigits(n);
        for (int i = 0; i < 31; i++) {
            if (target.equals(countDigits(1 << i)))
             return true;
        }
        return false;
    }
     private String countDigits(int n) {
        char[] count = new char[10];
        while (n > 0) {
            count[n % 10]++;
            n /= 10;
        }
        return new String(count);
    }
}
