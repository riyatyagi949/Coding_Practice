/**
 * Problem Statement:
 * Given a string `text` of words separated by a single space and a string `brokenLetters` of distinct broken letter keys,
 * return the number of words in `text` that you can fully type. A word can be fully typed if it does not contain any of the broken letters.
 *
 * Optimal Approach:
 * The most efficient approach is to first store all the broken letters in a data structure that provides fast lookups, such as a `HashSet` or a boolean array.
 * A boolean array of size 26 is ideal because the characters are all lowercase English letters. We can map each character to an index from 0 to 25.
 *
 * The algorithm proceeds as follows:
 * 1. Create a boolean array `isBroken` of size 26, initialized to `false`.
 * 2. Iterate through the `brokenLetters` string and for each character, set the corresponding index in `isBroken` to `true`. For example, for character 'a', set `isBroken[0]` to `true`.
 * 3. Split the input `text` into an array of words using a space as a delimiter.
 * 4. Initialize a counter for typable words to 0.
 * 5. Iterate through each word in the array of words.
 * 6. For each word, check if it contains any broken letters. This can be done with a flag, say `canType`, initialized to `true`.
 * 7. Iterate through the characters of the current word. For each character, check if it's in our `isBroken` array.
 * If a broken letter is found, set `canType` to `false` and break the inner loop (no need to check the rest of the word).
 * 8. After checking all characters of a word, if `canType` is still `true`, it means the word can be typed. Increment the counter.
 * 9. After iterating through all the words, return the final count.
 *
 * This method is optimal because it processes each character of the `brokenLetters` and each character of the `text` exactly once, leading to a linear time complexity.
 * Using a boolean array for `brokenLetters` ensures constant time complexity for each lookup.
 *
 * Time Complexity: O(L + N), where `L` is the length of `brokenLetters` and `N` is the total number of characters in `text`.
 * The time taken to populate the `isBroken` array is proportional to the length of `brokenLetters`. The time to iterate through the words and their characters is proportional to the total number of characters in `text`.
 *
 * Space Complexity: O(1) because the space required for the `isBroken` array is constant (26), regardless of the input size.
 */
// Optimal  Solution  in Java - 

class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] isBroken = new boolean[26];
        for (char ch : brokenLetters.toCharArray()) {
            isBroken[ch - 'a'] = true;
        }

        String[] words = text.split(" ");
        int count = 0;

        for (String word : words) {
            boolean canType = true;
            for (char ch : word.toCharArray()) {
                if (isBroken[ch - 'a']) {
                    canType = false;
                    break;
                }
            }
            if (canType) {
                count++;
            }
        }

        return count;
    }
}