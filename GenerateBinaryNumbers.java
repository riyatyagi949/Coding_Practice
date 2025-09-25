/**
 * Problem Statement:
 * Given a number `n`, the task is to generate all binary numbers with decimal values from 1 to `n` and return them as an array of strings.
 *
 * Examples:
 * Input: n = 4
 * Output: ["1", "10", "11", "100"]
 *
 * Input: n = 6
 * Output: ["1", "10", "11", "100", "101", "110"]
 *
 * Optimal Approach:
 * This problem can be solved efficiently using a Breadth-First Search (BFS) approach with a queue. The structure of binary numbers
 * generated in sequence (1, 10, 11, 100, ...) resembles a tree where each node represents a binary number. A node's children
 * are formed by appending '0' and '1' to its binary string.
 *
 * For example:
 * The root node is "1".
 * Its children are "10" and "11".
 * The children of "10" are "100" and "101".
 *
 * A queue is the perfect data structure for performing a level-by-level traversal of this conceptual tree, which is exactly how we want to
 * generate the numbers in increasing order.
 *
 * Algorithm steps:
 * 1. Initialize an empty `ArrayList<String>` to store the result.
 * 2. Initialize a `Queue<String>` and add the first binary number, "1", to it.
 * 3. Loop until the result list contains `n` elements:
 * a. Dequeue a binary string from the front of the queue. Let's call it `currentBinary`.
 * b. Add `currentBinary` to the result list.
 * c. Enqueue two new strings: `currentBinary + "0"` and `currentBinary + "1"`.
 * 4. Return the result list.
 *
 * This method ensures that we generate the binary numbers in the correct order without needing to perform any explicit base conversions,
 * which makes the solution clean and intuitive.
 *
 * Time Complexity: O(n)
 * The algorithm processes each number from 1 to `n` once. String concatenations are an `O(length)` operation, but the total
 * work is proportional to the sum of the lengths of all binary numbers up to `n`, which is `O(n)`.
 *
 * Space Complexity: O(n)
 * The space is required to store the result array and the queue. The queue can hold up to `O(n)` strings in the worst case (the last level of the BFS tree),
 * and the result list will store `n` strings. The length of each string is at most `log(n)`. So, the total space is `O(n * log n)`.
 */
// Optimal Solution in Java - 

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

