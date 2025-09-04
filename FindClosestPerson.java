// Problem Statement:
// You are given three integers x, y, and z, representing the positions of three people on a number line:
// x is the position of Person 1.
// y is the position of Person 2.
// z is the position of Person 3, who does not move.
// Both Person 1 and Person 2 move toward Person 3 at the same speed.
// Determine which person reaches Person 3 first:
// Return 1 if Person 1 arrives first.
// Return 2 if Person 2 arrives first.
// Return 0 if both arrive at the same time.
// Return the result accordingly.

// Approach:
// The problem asks us to determine which of two people, starting at positions x and y, reaches a third person at position z first.
// Both people move at the same speed. This means the person who is closer to z will reach it first.
// The "distance" from a person at position p to Person 3 at position z is the absolute difference between their positions, i.e., |p - z|.
// We need to calculate the distance for Person 1, which is |x - z|, and for Person 2, which is |y - z|.
// Let dist1 = |x - z| and dist2 = |y - z|.
// If dist1 < dist2, Person 1 is closer and will arrive first. We should return 1.
// If dist2 < dist1, Person 2 is closer and will arrive first. We should return 2.
// If dist1 == dist2, both are at the same distance and will arrive at the same time. We should return 0.
// We can use the Math.abs() function to calculate the absolute difference.

// Time Complexity:
// O(1)
// The solution involves a few simple arithmetic operations and comparisons, which take constant time regardless of the input values.

// Space Complexity:
// O(1)
// No additional data structures are used. The solution only requires a few variables to store the distances, which is constant space.

// Optimal Solution:
class Solution {
    public int findClosestPerson(int x, int y, int z) {
        int dist1 = Math.abs(x - z);
        int dist2 = Math.abs(y - z);
        if (dist1 < dist2) {
            return 1;
        } else if (dist2 < dist1) {
            return 2;
        } else {
            return 0;
        }
    }
}