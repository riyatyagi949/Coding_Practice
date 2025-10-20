/**
 * Problem Statement: Final Value of Variable After Performing Operations
 * ---------------------------------------------------------------------
 * A programming language has one variable, X, initially set to 0, and four operations:
 * - ++X and X++ (Increment X by 1)
 * - --X and X-- (Decrement X by 1)
 * Given an array of strings 'operations' containing a list of these operations, 
 * the task is to return the final value of X after performing all operations.
 * * Example:
 * Input: operations = ["--X", "X++", "X++"]
 * Output: 1
 * * Constraints:
 * 1 <= operations.length <= 100
 * operations[i] will be "++X", "X++", "--X", or "X--".
 */
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of the 'operations' array.
 * - We iterate through the array exactly once.
 * - Inside the loop, the operation (character check and increment/decrement) takes O(1) time.
 * - Total time is proportional to the number of operations.
 * * Space Complexity Analysis:
 * --------------------------
 * O(1)
 * - We use a single integer variable 'x' to store the running total. 
 * - The space used does not depend on the input size N.
 */
// Optimal Solution in Java - 