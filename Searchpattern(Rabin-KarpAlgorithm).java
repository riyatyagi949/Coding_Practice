// Problem Statement:
// Given two strings:
// A text string in which you want to search.
// A pattern string that you are looking for within the text.
// Return all positions (1-based indexing) where the pattern occurs as a substring in the text. If the pattern does not occur, return an empty list.
// All characters in both strings are lowercase English letters (a to z).

// Approach: Rabin-Karp Algorithm
// The Rabin-Karp algorithm is a string searching algorithm that uses hashing to find any one of a set of pattern strings in a text.
// It uses a rolling hash to quickly compare the hash value of the pattern with the hash values of all substrings of the text of the same length as the pattern.
// If the hash values match, it then performs a character-by-character comparison to confirm the match (to handle hash collisions).

// 1. Preprocessing:
//    - Calculate the hash value of the pattern.
//    - Calculate the hash value of the first window of the text (of size `m`, where `m` is the length of the pattern).

// 2. Rolling Hash:
//    - For subsequent windows, instead of recomputing the hash from scratch, we "roll" the hash. This means we remove the contribution of the leftmost character and add the contribution of the new rightmost character.
//    - The formula for rolling hash is:
//      `new_hash = (d * (old_hash - text.charAt(i) * h) + text.charAt(i + m)) % q`
//      where `d` is the number of characters in the alphabet (256 for ASCII), `q` is a prime number to avoid large hash values and `h = d^(m-1) % q`.
//    - We use modulo `q` to keep the hash values within a manageable range and prevent overflow. If the hash becomes negative after subtraction, we add `q` to make it positive.

// 3. Comparison:
//    - If the hash of the current text window matches the hash of the pattern, perform a direct character-by-character comparison to verify if the strings are truly equal (this handles spurious hits due to hash collisions).
//    - If they are equal, add the 1-based starting index of the current window to the result list.

// Time Complexity:
// The average-case time complexity of the Rabin-Karp algorithm is O(N + M), where N is the length of the text and M is the length of the pattern.
// In the worst case, if there are many spurious hits (due to hash collisions or a bad hash function), the time complexity can degrade to O(N * M), because it might perform character-by-character comparison for almost every window. However, with a good hash function and a large prime number `q`, this worst case is rare.

// Space Complexity:
// The space complexity is O(1) for storing hash values and a few variables. The space for storing the result list is O(K), where K is the number of occurrences of the pattern in the text.

// Optimal Solution:
class Solution {
    ArrayList<Integer> search(String pat, String txt) {
        ArrayList<Integer> result = new ArrayList<>();
        int d = 256; 
        int q = 101; 
        int m = pat.length();
        int n = txt.length();
        
        int h = 1;
        for (int i = 0; i < m - 1; i++)
            h = (h * d) % q;
        
        int p = 0; 
        int t = 0; 
        for (int i = 0; i < m; i++) {
            p = (d * p + pat.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }
      for (int i = 0; i <= n - m; i++) {
            if (p == t) {
                int j;
                for (j = 0; j < m; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }
                if (j == m) {
                    result.add(i + 1);
                }
            }
            if (i < n - m) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;
                if (t < 0)
                    t = (t + q);
            }
        }
        return result;
    }
}