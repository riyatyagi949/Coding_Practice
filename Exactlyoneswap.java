        // Problem Statement:
        // Given a string s, return the number of distinct strings that can be obtained by exactly one swap of two different indices (i < j).

        // Approach:
        // The total number of unique pairs of indices (i, j) with i < j is N * (N - 1) / 2, where N is the string length.
        // Each such swap generates a string. We need to count how many of these generated strings are distinct.
        // A swap of s[i] and s[j] results in the original string 's' only if s[i] == s[j].
        // All swaps where s[i] != s[j] produce a string distinct from the original and distinct from each other.

        // So, the number of distinct strings is:
        // (Total number of swaps where s[i] != s[j]) + (1 if any swap results in the original string, otherwise 0).
        // The '1' is for the original string 's' itself, which is only produced if there are duplicate characters.

        // Time Complexity: O(N)
        // Space Complexity: O(1) (due to fixed alphabet size for character counts)

        // Optimal Solution :

        class Solution {
            int countStrings(String s) {
            int n = s.length();
            long totalPossibleSwaps = (long) n * (n - 1) / 2;

        Map<Character, Integer> charCounts = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        long numIdenticalSwaps = 0;
        boolean hasDuplicateChars = false;
        for (int count : charCounts.values()) {
            if (count > 1) {
                numIdenticalSwaps += (long) count * (count - 1) / 2;
                hasDuplicateChars = true;
            }
        }
        return (int) (totalPossibleSwaps - numIdenticalSwaps + (hasDuplicateChars ? 1 : 0));
    }
}