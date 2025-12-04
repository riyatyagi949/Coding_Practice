/**
 * Problem Statement: Count Collisions on a Road
 * --------------------------------------------
 * Given a string 'directions' where 'L', 'R', and 'S' represent cars moving Left, 
 * Right, or Stationary, respectively. All moving cars have the same speed.
 * - R vs L collision: +2 collisions, both cars stop.
 * - Moving vs S collision: +1 collision, moving car stops.
 * - After a collision, all involved cars become stationary.
 * Return the total number of collisions.
 *
 * Constraints: 1 <= directions.length <= 10^5
 *//**
     * Optimal Solution: Two-Pointer / Filtering Approach
     * --------------------------------------------------
     * The total number of collisions is equal to the number of moving cars ('L' or 'R') 
     * that are eventually forced to stop. A car is NOT forced to stop if:
     * 1. It is an 'L' car at the very beginning of the road, and it never encounters 
     * anything moving right or stationary. These 'L' cars will drive off to the left 
     * without colliding.
     * 2. It is an 'R' car at the very end of the road, and it never encounters 
     * anything moving left or stationary. These 'R' cars will drive off to the right 
     * without colliding.
     *
     * Every other moving car *must* collide.
     * - An 'R' car will eventually hit an 'S' car, an 'L' car (R-L collision), 
     * or another stopped car (which was originally R, L, or S).
     * - An 'L' car will eventually hit an 'S' car, an 'R' car (R-L collision), 
     * or another stopped car.
     *
     * * The total collisions = (Total R cars that stop) + (Total L cars that stop).
     *
     * * Simplification:
     * 1. Use two pointers, `i` and `j`, to find the effective segment of the road 
     * where collisions can occur.
     * 2. `i` skips all initial 'L' cars (these never collide).
     * 3. `j` skips all final 'R' cars (these never collide).
     * 4. The cars remaining in the segment `[i, j]` must all eventually stop.
     * 5. The total number of collisions is simply the count of all 'R' and 'L' cars 
     * within the segment `[i, j]`. Every such moving car contributes exactly 1 collision 
     * to the total count (e.g., R-L gives 2 collisions, 1 for R, 1 for L; R-S gives 1 collision 
     * for R, but the S car was never counted).
     *
/**
 * Time Complexity Analysis:
 * -------------------------
 * O(N), where N is the length of the 'directions' string.
 * - The time complexity involves three single-pass operations (two while loops and one for loop).
 * - Each character of the string is visited a constant number of times (at most once by `i`, 
 * at most once by `j`, and at most once by `k`).
 * - Overall complexity: O(N).
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(N) or O(1), depending on implementation details.
 * - If the input string is converted to a character array (`arr`), it uses O(N) auxiliary space.
 * - If the solution works directly on the input string using `charAt(k)`, the auxiliary space is O(1).
 * - Using the character array for clean iteration, the complexity is O(N).
 */
// Code - 

class Solution {
    public int countCollisions(String directions) {
        char[] arr = directions.toCharArray();
        int n = arr.length;
        int i = 0, j = n - 1;
        int collisions = 0;

        while (i < n && arr[i] == 'L')
         i++;

        while (j >= 0 && arr[j] == 'R') 
        j--;

        for (int k = i; k <= j; k++) 
        {
            if (arr[k] != 'S')
            collisions++;
        }

        return collisions;
    }
}
