// Problem Statement:
// We have a wooden plank of length n units. Some ants are walking on the plank, each ant moves with a speed of 1 unit per second, with some moving left and others right.
// When two ants moving in two different directions meet at some point, they change their directions and continue moving again. Assume changing directions does not take any additional time. When an ant reaches one end of the plank at a time t, it falls out of the plank immediately.
// Given an integer n and two integer arrays left[] and right[], the positions of the ants moving to the left and the right, return the time when the last ant(s) fall out of the plank.

// Approach:
// The key insight for this problem is to realize that when two ants meet and change directions, it is
// equivalent to them passing through each other without changing directions. This is because all ants
// move at the same speed (1 unit/second) and are indistinguishable. The identity of the ant doesn't matter,
// only the time it takes for an ant to fall off the plank.
//
// Therefore, we can simplify the problem:
// For ants moving to the left, the time it takes to fall off is simply their initial position. The ant furthest
// to the right among the left-moving ants will take the longest time among them to fall off.
// For ants moving to the right, the time it takes to fall off is `n - initial_position`. The ant furthest
// to the left among the right-moving ants will take the longest time among them to fall off.
//
// The last ant to fall will be the one that takes the maximum time, considering all ants moving left and all ants moving right.
// So, we iterate through the `left` array and find the maximum position `l`. This `l` is the maximum time for any left-moving ant to fall.
// We iterate through the `right` array and find `n - r` for each `r`, and take the maximum of these values.
// The overall maximum of these two results will be the time when the last ant falls off.

// Time Complexity: O(L + R), where L is the number of ants moving left and R is the number of ants moving right.
// We iterate through both arrays once.
// Space Complexity: O(1)
// We use a constant amount of extra space.

class Solution {
    public int getLastMoment(int n, int[] left, int[] right) {
        int maxTime = 0;

        for (int l : left) {
            maxTime = Math.max(maxTime, l);
        }
        for (int r : right) {
            maxTime = Math.max(maxTime, n - r);
        }
        return maxTime;
    }
}