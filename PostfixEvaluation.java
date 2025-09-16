/**
 * Problem Statement:
 * Given a string array `arr` representing a valid arithmetic expression in Reverse Polish Notation (Postfix Notation),
 * evaluate the expression and return its integer value. The valid operators are "+", "-", "*", "/", and "^".
 * The division operation should compute the floor value. All intermediate calculations and the final result will fit within a 32-bit signed integer.
 *
 * Optimal Approach:
 * The optimal way to evaluate a postfix expression is by using a stack. The nature of postfix notation, where operators follow their operands,
 * makes a stack an ideal data structure. The algorithm processes the expression from left to right.
 *
 * The steps are as follows:
 * 1. Initialize an empty stack of integers.
 * 2. Iterate through each element (token) of the input string array `arr`.
 * 3. For each token, check if it is an operator or a number.
 * - To check if it's a number, we can use a try-catch block to parse it as an integer. If parsing is successful, it's an operand.
 * - A more direct way is to check if the token is one of the five given operators: "+", "-", "*", "/", or "^".
 * 4. If the token is a number (operand):
 * - Convert the token string to an integer and push it onto the stack.
 * 5. If the token is an operator:
 * - It means we have encountered an operation to perform. A valid postfix expression guarantees that there will be at least two operands on the stack.
 * - Pop the top two elements from the stack. The top element is the second operand (`operand2`), and the element below it is the first operand (`operand1`).
 * - Perform the operation (`operand1 operator operand2`).
 * - Handle the special case for integer division (`/`) to ensure it returns the floor value. In Java, integer division `a / b` naturally handles this for positive and negative numbers correctly, but it's good to be aware of the floor behavior. For example, `5 / 3 = 1` and `-5 / 3 = -1`. The problem states `floor(-5 / 3) = -2`. Java's integer division truncates towards zero. So, for negative results, we need to adjust. If `a * b < 0` and `a % b != 0`, we need to subtract 1 from the result of `a / b`.
 * - Push the result of the operation back onto the stack.
 * 6. After iterating through all tokens, the single element remaining on the stack is the final result of the expression. Pop this element and return it.
 *
 * This stack-based approach is optimal as it processes each element of the expression exactly once.
 *
 * Time Complexity: O(n), where `n` is the number of tokens in the array. We iterate through the array once.
 * Space Complexity: O(n), in the worst case, if all elements are operands, the stack could grow to a size of `n/2` (for an expression like "1 2 3 ... n/2 + + ..."). In a balanced expression, it's much smaller. A safe upper bound is O(n).
 */
import java.util.Stack;

class Solution {
    public int evaluatePostfix(String[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (String token : arr) {
            switch (token) {
                case "+":
                    int b_add = stack.pop();
                    int a_add = stack.pop();
                    stack.push(a_add + b_add);
                    break;
                case "-":
                    int b_sub = stack.pop();
                    int a_sub = stack.pop();
                    stack.push(a_sub - b_sub);
                    break;
                case "*":
                    int b_mul = stack.pop();
                    int a_mul = stack.pop();
                    stack.push(a_mul * b_mul);
                    break;
                case "/":
                    int b_div = stack.pop();
                    int a_div = stack.pop();
                    int result_div = a_div / b_div;
                    // Handle floor for negative results.
                    // Java's integer division truncates towards zero. For a/b where a and b have different signs and a is not a multiple of b,
                    // the result needs to be adjusted down by one to match the floor behavior.
                    if ((a_div % b_div != 0) && (a_div * b_div < 0)) {
                        result_div--;
                    }
                    stack.push(result_div);
                    break;
                case "^":
                    int b_pow = stack.pop();
                    int a_pow = stack.pop();
                    // Using Math.pow and casting to int. `Math.pow` returns a double.
                    stack.push((int) Math.pow(a_pow, b_pow));
                    break;
                default:
                    // It's a number, so push it onto the stack.
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        
        return stack.pop();
    }
}