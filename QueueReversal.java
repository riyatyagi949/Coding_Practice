/**
 * Problem Statement:
 * Given a queue `q` containing integer elements, your task is to reverse the queue.
 *
 * Example:
 * Input: q[] = [5, 10, 15, 20, 25]
 * Output: [25, 20, 15, 10, 5]
 *
 * Optimal Approach:
 * The most efficient and standard way to reverse a queue is by using an auxiliary data structure, specifically a stack.
 * The queue follows a First-In, First-Out (FIFO) principle, while a stack follows a Last-In, First-Out (LIFO) principle.
 * By transferring elements from the queue to a stack, we can reverse their order.
 *
 * The steps are as follows:
 * 1. Transfer all elements from the queue to a temporary stack. The first element of the queue becomes the last element of the stack, and the last element of the queue becomes the first element of the stack.
 * 2. Transfer all elements from the stack back to the queue. Due to the LIFO property of the stack, the elements will be dequeued from the stack in reversed order, effectively reversing the queue.
 *
 * Time Complexity: O(N), where N is the number of elements in the queue.
 * This is because we traverse all N elements twice: once to move them from the queue to the stack, and once to move them back.
 *
 * Space Complexity: O(N), where N is the number of elements in the queue.
 * This is for the auxiliary stack used to store the queue elements. In the worst case, the stack will hold all N elements.
 */
// Optimal Solution in Java - 