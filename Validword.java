// Problem Statement:
// A word is considered valid if:
// 1. It contains a minimum of 3 characters.
// 2. It contains only digits (0-9), and English letters (uppercase and lowercase).
// 3. It includes at least one vowel.
// 4. It includes at least one consonant.
// You are given a string word.
// Return true if word is valid, otherwise, return false.
// Notes:
// 'a', 'e', 'i', 'o', 'u', and their uppercases are vowels.
// A consonant is an English letter that is not a vowel.

// Approach:
// We need to check four conditions for the given word.
// 1. Length check: The length of the word must be at least 3.
// 2. Character type check: Iterate through each character of the word. Each character must be a digit (0-9), or an English letter (a-z, A-Z). If any character does not satisfy this, the word is invalid.
// 3. Vowel check: Maintain a boolean flag, `hasVowel`, initialized to false. If a character is an English letter and a vowel, set `hasVowel` to true.
// 4. Consonant check: Maintain a boolean flag, `hasConsonant`, initialized to false. If a character is an English letter and a consonant, set `hasConsonant` to true.

// After iterating through all characters:
// If the length condition is not met, return false.
// If the character type check failed at any point, return false.
// Finally, return true only if both `hasVowel` and `hasConsonant` are true.

// To implement vowel/consonant check, we can use a helper function `isVowel` or directly check against vowel characters.
// To check if a character is a letter or digit, we can use `Character.isLetterOrDigit()`.
// To check if a character is a letter, we can use `Character.isLetter()`.

// Time Complexity:
// O(N), where N is the length of the input string `word`. We iterate through the string once.

// Space Complexity:
// O(1), as we only use a few boolean flags and integer variables.

class Solution {
    public boolean isValid(String word) {
        if (word.length() < 3) {
            return false;
        }
        boolean hasVowel = false;
        boolean hasConsonant = false;

        for (char c : word.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        if (Character.isLetter(c)) {
                char lowerC = Character.toLowerCase(c);
                if (lowerC == 'a' || lowerC == 'e' || lowerC == 'i' || lowerC == 'o' || lowerC == 'u') {
                    hasVowel = true;
                } else {
                    hasConsonant = true;
                }
            }
        }
        return hasVowel && hasConsonant;
    }
}