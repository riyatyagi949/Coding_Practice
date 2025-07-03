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
        StringBuilder sb = new StringBuilder("a"); 
        
        while (sb.length() < k) {
            StringBuilder next = new StringBuilder();
            
            for (int i = 0; i < sb.length(); i++) {
                char ch = sb.charAt(i);
                char nextChar = (char) ((ch - 'a' + 1) % 26 + 'a');
                next.append(nextChar);
            }
             sb.append(next); 
        }
          return sb.charAt(k - 1); 
    }
}
