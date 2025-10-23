/**
 * Problem Statement: Check If Digits Are Equal in String After Operations I
 * ------------------------------------------------------------------------
 * You are given a string 's' consisting of digits. Perform the following operation 
 * repeatedly until the string has exactly two digits:
 * * Operation: For each pair of consecutive digits in 's', calculate a new digit as 
 * the sum of the two digits modulo 10. Replace 's' with the sequence of newly 
 * calculated digits. The new string length will be old_length - 1.
 * * Return true if the final two digits in 's' are the same; otherwise, return false.
 * * Example:
 * Input: s = "3902"
 * Operation 1: (3+9)%10=2, (9+0)%10=9, (0+2)%10=2. s becomes "292".
 * Operation 2: (2+9)%10=1, (9+2)%10=1. s becomes "11".
 * Final digits are '1' and '1', which are equal. Output: true.
 * * Constraints:
 * 3 <= s.length <= 100
 * s consists of only digits.
 *//**
     * Optimal Solution: Direct Simulation
     * ----------------------------------
     * Given the very small constraint on the input string length (N <= 100), the most 
     * straightforward and optimal approach is to directly simulate the transformation 
     * process until the string length is reduced to 2.
     * * The number of operations required is N - 2, and in each operation, the length of 
     * the new string is reduced by 1.
     * * Algorithm Steps:
     * 1. Loop while the length of 's' is greater than 2.
     * 2. Inside the loop, create a new StringBuilder to build the next string.
     * 3. Iterate from the first digit up to the second-to-last digit (index i to length - 2).
     * 4. Calculate the sum of s[i] and s[i+1], take it modulo 10, and append the result 
     * (as a character) to the new string.
     * 5. Replace 's' with the new string.
     * 6. Once the loop terminates, 's' has length 2. Compare the first and second characters.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(N^2), where N is the initial length of the string s.
 * - The simulation runs for N - 2 operations.
 * - In the k-th operation (k from 1 to N-2), the string length is L_k = N - (k-1).
 * - The work done in the k-th operation (iterating and building the new string) is O(L_k).
 * - Total complexity is proportional to the sum of lengths:
 * Sum (from k=1 to N-2) of (N - (k-1)) = N + (N-1) + ... + 3
 * - This sum is O(N^2). Since N <= 100, N^2 <= 10,000, which is extremely fast.
 * * Space Complexity Analysis:
 * --------------------------
 * O(N), where N is the initial length of the string s.
 * - At any point, the maximum space is used to store the current string 's' and the 
 * temporary StringBuilder 'nextS', both of which have a maximum length of N.
 */
// Optimal Solution in Java - 
class Solution {
    public boolean hasSameDigits(String s) {
        while (s.length() > 2)
         {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < s.length() - 1; i++) {
                int a = s.charAt(i) - '0';
                int b = s.charAt(i + 1) - '0';
                sb.append((a + b) % 10);
            }
            s = sb.toString();
        }
        return s.charAt(0) == s.charAt(1);
    }
}