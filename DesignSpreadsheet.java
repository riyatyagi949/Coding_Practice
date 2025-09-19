/**
 * Problem Statement:
 * Design a spreadsheet class with 26 columns ('A' to 'Z') and a given number of rows.
 * The class must support the following operations:
 * 1. `Spreadsheet(int rows)`: Initializes the grid with all cells set to 0.
 * 2. `setCell(String cell, int value)`: Sets the value of a specific cell (e.g., "A1").
 * 3. `resetCell(String cell)`: Resets a cell's value to 0.
 * 4. `getValue(String formula)`: Evaluates a formula of the form "=X+Y", where X and Y are either cell references or integers, and returns their sum.
 *
 * Optimal Approach:
 * The most efficient way to handle a grid-based problem like this is to use a 2D array.
 * A 2D integer array, `int[][] grid`, can represent the spreadsheet.
 * - The number of rows is given in the constructor.
 * - The number of columns is fixed at 26.
 *
 * To map a cell reference like "A1" to array indices `[row][col]`, we need a helper function.
 * - The column character ('A' to 'Z') can be converted to an index from 0 to 25. For example, `'A' - 'A' = 0`, `'B' - 'A' = 1`, etc.
 * - The row number is 1-indexed, so we need to subtract 1 to get the 0-indexed row.
 *
 * The implementation of each method would be as follows:
 * - `Spreadsheet(int rows)`: Initialize the grid `int[rows][26]` with all zeros.
 * - `setCell(String cell, int value)`: Parse the cell string to get `(row, col)` indices, then set `grid[row][col] = value`.
 * - `resetCell(String cell)`: Parse the cell string and set `grid[row][col] = 0`.
 * - `getValue(String formula)`:
 * - The formula is in the format "=X+Y".
 * - We need to parse this string to extract `X` and `Y`. This can be done by splitting the string at `'+'`.
 * - We then need a helper function to evaluate each part (`X` and `Y`). This helper function checks if the string is a number or a cell reference.
 * - If it's a number, convert the string to an integer.
 * - If it's a cell reference, parse it to get `(row, col)` indices and return the value from `grid[row][col]`.
 * - Finally, sum the values of `X` and `Y` and return the result.
 *
 * Time Complexity:
 * - `Spreadsheet`: O(rows * 26) for initialization.
 * - `setCell`, `resetCell`: O(1). Parsing the cell string takes constant time as the format is fixed.
 * - `getValue`: O(1). Parsing the formula and looking up values takes constant time.
 * - Total complexity for `k` calls will be `O(rows) + O(k)`. Given the constraints, this is very efficient.
 *
 * Space Complexity:
 * - O(rows * 26) to store the spreadsheet grid.
 */
