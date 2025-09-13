/**
 * Problem Statement:
 * Given a string `s` of lowercase English letters, find the sum of the maximum frequency of a vowel and the maximum frequency of a consonant.
 * Vowels are 'a', 'e', 'i', 'o', 'u'. Consonants are all other lowercase letters.
 * If there are no vowels or no consonants, their maximum frequency is considered 0.
 *
 * Optimal Approach:
 * The most straightforward way to solve this problem is to count the frequencies of all characters in the string.
 * A frequency map or an array of size 26 can be used for this. Since the input string consists only of lowercase English letters, an integer array of size 26 is efficient.
 * The index `0` would correspond to 'a', `1` to 'b', and so on.
 *
 * The algorithm involves the following steps:
 * 1. Initialize an integer array of size 26 to store the frequency of each character, all set to 0.
 * 2. Iterate through the input string `s` and increment the count for each character in the frequency array.
 * 3. Initialize two variables, `maxVowelFreq` and `maxConsonantFreq`, to 0.
 * 4. Iterate through the frequency array (from index 0 to 25):
 * a. For each index `i`, check if the character corresponding to `i` is a vowel. The vowels are 'a', 'e', 'i', 'o', 'u'.
 * - If it's a vowel, update `maxVowelFreq` with the maximum of its current value and the character's frequency (`freq[i]`).
 * b. If it's not a vowel (i.e., a consonant), update `maxConsonantFreq` with the maximum of its current value and the character's frequency (`freq[i]`).
 * 5. Finally, return the sum of `maxVowelFreq` and `maxConsonantFreq`.
 *
 * This approach guarantees finding the correct maximum frequencies for both vowels and consonants in a single pass over the string to build the frequency map,
 * and another pass over the map to find the maximum frequencies. This is highly efficient given the constraints.
 *
 * Time Complexity: O(n), where `n` is the length of the string. We iterate through the string once to populate the frequency array, and then
 * a constant number of times (26) to find the maximum frequencies.
 *
 * Space Complexity: O(1) because we use a fixed-size array of 26 to store character frequencies, regardless of the input string's length.
 */
// Optimal Solution in Java - 
import java.util.*;