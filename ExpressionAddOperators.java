/**
 * Problem Statement:
 * Given a string `s` containing only digits ('0'-'9') and an integer `target`, find all possible strings by inserting
 * binary operators '+', '-', or '*' between the digits of `s` such that the resulting expression evaluates to the `target` value.
 * Constraints include:
 * 1. Operands must not have leading zeros (e.g., "03" is invalid, but "0" or "10" are valid).
 * 2. It is allowed to form multi-digit numbers (i.e., not insert an operator).
 *
 * Optimal Approach: Backtracking / Depth First Search (DFS)
 * This problem is a classic combinatorial generation problem, optimally solved using **Backtracking** (or DFS). We need to explore all ways
 * to partition the digit string into numbers and place operators between them.
 *
 * The core idea is to process the string `s` digit by digit. At each position, we decide how long the current operand (number) should be.
 * After forming an operand, we recursively call the function to process the rest of the string, adding an operator (+, -, or *) before the next number.
 *
 * State for Backtracking:
 * The recursive function `solve(index, currentExpression, currentVal, prevOperand)` needs to maintain:
 * 1. `index`: The current starting index in the digit string `s`.
 * 2. `currentExpression`: The expression string built so far.
 * 3. `currentVal`: The calculated value of the expression *without* considering the effect of the previous multiplication (needed for correct multiplication precedence).
 * 4. `prevOperand`: The value of the last number/operand added to the expression. This is critical for handling the '*' operator, as multiplication has higher precedence than addition/subtraction.
 * - When we encounter multiplication (`*`), the operation is: `currentVal - prevOperand + (prevOperand * currentNum)`.
 * - This effectively "undoes" the previous operation involving `prevOperand` and substitutes it with the multiplication result.
 *
 * Base Case:
 * When `index` reaches the end of the string (`n`):
 * - If `currentVal` equals `target`, the `currentExpression` is a valid result. Add it to the answer list.
 *
 * Recursive Step (Looping to form the current number):
 * From the current `index`, we try to form a number by extending it to index `j`. The number is `s.substring(index, j+1)`.
 * 1. **Current Number Formation:** Extract the number `currentNum` from `s[index...j]`.
 * 2. **Leading Zero Check:** If `s[index]` is '0' and the number has more than one digit (i.e., `j > index`), we must stop further extension from this `index` and process only "0". This handles the leading zero constraint.
 * 3. **First Number (Base Case of the Recursion):**
 * - If `index == 0`, we just append the number to the expression and call the function for the remaining string:
 * `solve(j + 1, currentNum, currentNum, currentNum)`
 * 4. **Subsequent Numbers (Operators):**
 * - If `index > 0`, we try all three operators:
 * - **'+'**: `solve(j + 1, expr + "+" + currentNum, currentVal + currentNum, currentNum)`
 * - **'-'**: `solve(j + 1, expr + "-" + currentNum, currentVal - currentNum, -currentNum)` (Note: `-currentNum` is passed as `prevOperand` for subsequent multiplication)
 * - **'*'**: `solve(j + 1, expr + "*" + currentNum, currentVal - prevOperand + (prevOperand * currentNum), prevOperand * currentNum)`
 *
 * Time Complexity: O(4^n), where $n$ is the length of the string `s`. This is because at each of the $n-1$ gaps between digits, we have 4 choices: merge the digits (no operator), or insert '+', '-', or '*'. The exponential complexity is acceptable due to the small constraint on $n$ ($\le 9$).
 * Space Complexity: O(n) for the recursion stack depth and storing the resulting expressions.
 */
// Optimal Solution in Java -
import java.util.*; 

class Solution {
    public ArrayList<String> findExpr(String s, int target) {
        ArrayList<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), s, target, 0, 0, 0);
        Collections.sort(result); 
        return result;
    }
    private void backtrack(ArrayList<String> result, StringBuilder expr, String s, int target,
                           int index, long eval, long prev) {
        if (index == s.length()) {
            if (eval == target) {
                result.add(expr.toString());
            }
            return;
        }
        
        for (int i = index; i < s.length(); i++) {
            if (i != index && s.charAt(index) == '0')
            break;
            
            String part = s.substring(index, i + 1);
            long num = Long.parseLong(part);
            int len = expr.length();
            
            if (index == 0) {
                expr.append(part);
                backtrack(result, expr, s, target, i + 1, num, num);
                expr.setLength(len);
            }
            else {
                expr.append('+').append(part);
                backtrack(result, expr, s, target, i + 1, eval + num, num);
                expr.setLength(len);
                
                expr.append('-').append(part);
                backtrack(result, expr, s, target, i + 1, eval - num, -num);
                expr.setLength(len);
                
                expr.append('*').append(part);
                backtrack(result, expr, s, target, i + 1, eval - prev + prev * num, prev * num);
                expr.setLength(len);
            }
        }
    }
}
