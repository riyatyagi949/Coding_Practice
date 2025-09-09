// Problem Statement:
// You are given two arrays mices[] and holes[] of the same size. The array mices[] represents the positions of the mice on a straight line, while the array holes[] represents the positions of the holes on the same line. Each hole can accommodate exactly one mouse. A mouse can either stay in its current position, move one step to the right, or move one step to the left, and each move takes one minute. The task is to assign each mouse to a distinct hole in such a way that the time taken by the last mouse to reach its hole is minimized.

// Approach:
// The problem asks to minimize the maximum time taken by any mouse to reach its hole. This is a classic "minimax" problem.
// The time taken for a mouse at position `m` to reach a hole at position `h` is `|m - h|`. We need to find a one-to-one mapping (a permutation) between mice and holes such that `max(|mices[i] - holes[j]|)` is minimized.
//
// Let's consider a simple case. Suppose we have two mice at `m1`, `m2` and two holes at `h1`, `h2`.
// The possible pairings are:
// 1. (m1, h1) and (m2, h2). Max time = `max(|m1 - h1|, |m2 - h2|)`.
// 2. (m1, h2) and (m2, h1). Max time = `max(|m1 - h2|, |m2 - h1|)`.
//
// To minimize the maximum difference, it is optimal to sort both the `mices` and `holes` arrays.
// After sorting, the best strategy is to pair the `i`-th smallest mouse position with the `i`-th smallest hole position.
//
// Proof:
// Let the sorted arrays be `sortedMice[]` and `sortedHoles[]`.
// Assume an optimal solution exists where a mouse at `sortedMice[i]` is not paired with the hole at `sortedHoles[i]`.
// Let `sortedMice[i]` be paired with `sortedHoles[k]` and `sortedMice[j]` be paired with `sortedHoles[l]`, where `i < j` and `k > l`.
// The costs for these pairs are `|sortedMice[i] - sortedHoles[k]|` and `|sortedMices[j] - sortedHoles[l]|`.
//
// Since `i < j`, we have `sortedMice[i] <= sortedMice[j]`.
// Since `k > l`, we have `sortedHoles[k] >= sortedHoles[l]`.
//
// Let's consider a new pairing: `(sortedMice[i], sortedHoles[l])` and `(sortedMice[j], sortedHoles[k])`.
// The new costs are `|sortedMice[i] - sortedHoles[l]|` and `|sortedMices[j] - sortedHoles[k]|`.
//
// Using the property `|a-d| + |b-c| >= |a-c| + |b-d|` for `a <= b` and `c <= d`, we can show that `max(|mices[i] - holes[k]|, |mices[j] - holes[l]|) >= max(|mices[i] - holes[l]|, |mices[j] - holes[k]|)`.
// Thus, by swapping the pairings, we get a solution that is at least as good. This holds for any such "crossed" pairing, proving that the optimal solution is to pair sorted mice with sorted holes.
//
// The algorithm is as follows:
// 1. Sort the `mices` array.
// 2. Sort the `holes` array.
// 3. Iterate through both arrays simultaneously from `i = 0` to `n-1`.
// 4. For each `i`, calculate the time taken: `|mices[i] - holes[i]|`.
// 5. Keep track of the maximum time found so far.
// 6. Return the maximum time.
//
// The time complexity will be dominated by the sorting step.
//
// Time Complexity: O(N log N) due to sorting, where N is the number of mice (or holes).
// Space Complexity: O(1) if sorting is done in-place, or O(N) if a new array is used for sorting.

// Optimal Solution in Java - 
import java.util.Arrays;

class Solution {
    public int assignMiceHoles(int[] mices, int[] holes) {
        Arrays.sort(mices);
        Arrays.sort(holes);
        
        int maxTime = 0;
        
        for (int i = 0; i < mices.length; i++) {
            maxTime = Math.max(maxTime, Math.abs(mices[i] - holes[i]));
        }
        
        return maxTime;
    }
}