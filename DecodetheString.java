/**
 * Problem Statement:
 * Given an encoded string `s`, decode it. The encoding rule is `k[substring]`, which means the `substring` inside the brackets is repeated `k` times.
 * `k` is a positive integer, and `s` contains only lowercase English alphabets, digits, and square brackets.
 *
 * Optimal Approach:
 * This problem can be solved efficiently using two stacks: one for numbers (repetition counts) and one for strings (the parts being built).
 * We iterate through the input string character by character.
 *
 * 1. If the character is a digit, we parse the full number (which can be multi-digit) and push it onto the `countStack`.
 *
 * 2. If the character is a lowercase alphabet, it is part of the current string being built. We append it to a `currentString`.
 *
 * 3. If the character is an opening bracket `[`, it signifies the start of a new, nested encoded string. We push the current `count` and the `currentString` onto their respective stacks. Then, we reset `currentString` to be empty and `count` to 0 to prepare for the new, nested substring.
 *
 * 4. If the character is a closing bracket `]`, it means we have finished decoding a nested substring. We pop the last `count` from `countStack` and the last `string` from `stringStack`. We then repeat the `currentString` `count` times and append the result to the popped string. This new combined string becomes the new `currentString`.
 *
 * The `stringStack` stores the parts of the string before each opening bracket, which we will later append the decoded nested substrings to. The `countStack` stores the repetition count for each nested substring.
 *
 * This stack-based approach correctly handles nested brackets and ensures that the substrings are expanded in the correct order, from the innermost to the outermost.
 *
 * Time Complexity: O(n), where `n` is the length of the string. Although there are nested loops for string repetition, each character of the original string and the final decoded string is processed a constant number of times. The total number of operations is proportional to the length of the final decoded string. Given the constraint that the output length won't exceed 10^5, this is a linear time solution relative to the total work done.
 *
 * Space Complexity: O(max_k * max_depth), where `max_k` is the maximum value of a number `k` and `max_depth` is the maximum nesting depth of the brackets. In the worst case, the stacks could store all numbers and strings, which can be proportional to the length of the input string and the number of repetitions. Given the constraints, the stack size is manageable.
 */

//  Optimal  Solution  in Java - 
import java.util.Stack;

