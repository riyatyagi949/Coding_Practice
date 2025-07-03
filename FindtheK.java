// Problem Statement:
// Find the k-th character in a string that starts with "a" and repeatedly doubles by appending a shifted version of itself (a -> a + next(a)).

// Approach:
// The string length doubles in each step (1 -> 2 -> 4 -> 8...).
// We find the smallest power of 2, `currentLength`, that is greater than or equal to `k`.
// Then, we work backward. `charOffset` tracks the total shifts from 'a'.
// If `k` falls into the second half of `currentLength`, it means the character was shifted once; we increment `charOffset` and adjust `k` for the second half.
// We repeatedly halve `currentLength` until it's 1.
// The final character is 'a' plus `charOffset`.

// Time Complexity: O(log K)
// Space Complexity: O(1)

class Solution {
    public char kthCharacter(int k) {
        long currentLength = 1;
        while (currentLength < k) {
            currentLength *= 2;
        }
        int charOffset = 0;
        while (currentLength > 1) {
            long halfLength = currentLength / 2;
            if (k > halfLength) {
                charOffset = (charOffset + 1) % 26;
                k -= halfLength;
            }
            currentLength = halfLength;
        }
        return (char) ('a' + charOffset);
    }
}