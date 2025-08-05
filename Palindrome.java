// Problem Statement:
// Given a single string s, the task is to check if it is a palindrome sentence or not.
// A palindrome sentence is a sequence of characters, such as a word, phrase, or series of symbols that reads the same backward as forward after converting all uppercase letters to lowercase and removing all non-alphanumeric characters (including spaces and punctuation).
    // Approach:
    // To solve this problem, we first need to preprocess the input string s to create a new string that contains only lowercase alphanumeric characters.
    // The steps for preprocessing are:
    // 1. Iterate through the input string s.
    // 2. For each character, check if it is an alphanumeric character (a-z, A-Z, 0-9).
    // 3. If it is, convert the character to lowercase and append it to a new string or a StringBuilder.
    // 4. After iterating through the entire string, we will have a new, cleaned-up string.
    //
    // Once we have the processed string, we can check if it is a palindrome. A simple and efficient way to do this is using a two-pointer approach.
    // 1. Initialize two pointers, one at the beginning of the string (left) and one at the end (right).
    // 2. While the left pointer is less than the right pointer, compare the characters at these positions.
    // 3. If the characters are not equal, the string is not a palindrome, so we can return false.
    // 4. If they are equal, move the left pointer one step to the right and the right pointer one step to the left.
    // 5. If the loop completes without finding any non-matching characters, the string is a palindrome, and we can return true.
    //
    // Alternatively, we can use a simpler approach without explicitly creating a new string.
    // 1. Use two pointers, one at the start of the original string (left) and one at the end (right).
    // 2. In a loop, move the left pointer forward until it points to an alphanumeric character.
    // 3. Simultaneously, move the right pointer backward until it points to an alphanumeric character.
    // 4. If the left pointer crosses the right pointer, it means we have processed all characters and the string is a palindrome.
    // 5. If the pointers have not crossed, compare the lowercase versions of the characters they point to. If they don't match, return false.
    // 6. If they match, move both pointers one step inward and repeat the process.
    //
    // This second approach is more space-efficient as it avoids creating a new string.
    //
    // Time Complexity: O(N)
    // We iterate through the string a constant number of times (once to build the new string or with two pointers).
    //
    // Space Complexity: O(N) for the first approach, O(1) for the second.
    // The first approach uses a StringBuilder which can, in the worst case, store a string of length N. The second approach uses only a few variables, so its space complexity is O(1). We will implement the second, more optimal approach.

   class Solution {
       public boolean isPalindromeSentence(String s) {
        if (s == null) {
            return false;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
            } else if (!Character.isLetterOrDigit(rightChar)) {
                right--;
            } else {
                if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
    

