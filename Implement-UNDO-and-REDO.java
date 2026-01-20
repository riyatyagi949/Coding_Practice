/**
 * PROBLEM STATEMENT: Implement UNDO & REDO
 * --------------------------------------------------------------------------------
 * You are given an empty text document. Implement the following operations:
 * 1. append(char x): Add character x to the end of the document.
 * 2. undo(): Revert the last APPEND operation (remove the last character).
 * 3. redo(): Restore the last character removed by an UNDO operation.
 * 4. read(): Return the current string content of the document.
 * * Logic Note: 
 * - If an 'append' operation occurs, the 'redo' history must be cleared because
 * a new timeline has been established.
 * * Constraints:
 * - 1 ≤ q ≤ 10^4 (Number of queries)
 * --------------------------------------------------------------------------------
 * OPTIMAL SOLUTION: Two-Stack Approach
 * --------------------------------------------------------------------------------
 * We use two stacks to manage the characters:
 * 1. Main Stack: Stores the current characters in the document.
 * 2. Redo Stack: Stores characters that were popped during an 'undo' operation.
 * * Algorithm:
 * - append(x): Push 'x' to Main Stack. Clear Redo Stack (as per standard editor logic).
 * - undo(): If Main Stack is not empty, pop from it and push the char to Redo Stack.
 * - redo(): If Redo Stack is not empty, pop from it and push the char to Main Stack.
 * - read(): Iterate through the Main Stack to build the final string.
 * --------------------------------------------------------------------------------
 * COMPLEXITY ANALYSIS:
 * --------------------------------------------------------------------------------
 * Time Complexity:
 * - append, undo, redo: O(1) average time.
 * - read: O(L) where L is the current length of the document.
 * * Space Complexity: O(Q)
 * - In the worst case, we store all Q appended characters across the two stacks.
 * --------------------------------------------------------------------------------
 */
// Optimal Solution in Java-
import java.util.Stack;

class Solution {
    private Stack<Character> mainStack = new Stack<>();
    private Stack<Character> redoStack = new Stack<>();

    public void append(char x) {
        mainStack.push(x);
        redoStack.clear();
    }
    public void undo() {
        if (!mainStack.isEmpty()) {
            redoStack.push(mainStack.pop());
        }
    }
    public void redo() {
        if (!redoStack.isEmpty()) {
            mainStack.push(redoStack.pop());
        }
    }
    public String read() {
        StringBuilder sb = new StringBuilder();
        for (char ch : mainStack) {
            sb.append(ch);
        }
        return sb.toString();
    }
}


