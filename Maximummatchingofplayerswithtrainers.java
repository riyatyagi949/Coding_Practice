/*
    Problem Statement:
    You are given a 0-indexed integer array players, where players[i] represents the ability of the ith player.
    You are also given a 0-indexed integer array trainers, where trainers[j] represents the training capacity of the jth trainer.
    The ith player can match with the jth trainer if the player's ability is less than or equal to the trainer's training capacity.
    Additionally, the ith player can be matched with at most one trainer, and the jth trainer can be matched with at most one player.
    Return the maximum number of matchings between players and trainers that satisfy these conditions.

    Approach:
    This problem is a classic greedy problem. To maximize the number of pairings, we should try to match players with trainers efficiently.
    Consider sorting both the players' abilities and the trainers' capacities in ascending order.

    If we sort both arrays, we can iterate through the players and try to match each player with the smallest possible trainer that can train them.
    Why greedy works:
    Suppose we have `player1 <= player2` and `trainer1 <= trainer2`.
    If `player1` can be matched with `trainer1`, and `player2` can be matched with `trainer2`, that's a valid matching.
    What if `player1` could be matched with `trainer2` and `player2` with `trainer1`? This is not possible if `player2 > trainer1`.
    The core idea is to try to satisfy the "easiest" matches first, which means matching the weakest players with the weakest suitable trainers. This leaves the stronger trainers for stronger players who might not be able to be trained by weaker trainers.

    Let's sort `players` and `trainers` arrays in ascending order.
    We'll use two pointers, `playerIdx` for the `players` array and `trainerIdx` for the `trainers` array, both starting at 0.
    We iterate while `playerIdx` is less than `players.length` and `trainerIdx` is less than `trainers.length`.

    In each step:
    1. If `players[playerIdx]` is less than or equal to `trainers[trainerIdx]`, it means the current player can be matched with the current trainer. We increment our `matchCount`, and move to the next player (`playerIdx++`) and the next trainer (`trainerIdx++`), as both are now used.
    2. If `players[playerIdx]` is greater than `trainers[trainerIdx]`, it means the current player cannot be matched with the current trainer (the trainer is too weak). Since we sorted the trainers, this trainer also cannot train any stronger player (who would be at `playerIdx` or further down the line). So, this trainer is useless for the current player and any subsequent players. We move to the next trainer (`trainerIdx++`) to find a stronger one. The current player (`playerIdx`) remains to be matched.

    We continue this process until we run out of players or trainers. The `matchCount` will give us the maximum number of pairings.

    Example Walkthrough:
    players = [4, 7, 9], trainers = [8, 2, 5, 8]

    1. Sort arrays:
       players = [4, 7, 9]
       trainers = [2, 5, 8, 8]

    2. Initialize:
       playerIdx = 0
       trainerIdx = 0
       matchCount = 0

    3. Loop:
       - Iteration 1:
         players[0] = 4, trainers[0] = 2
         4 > 2. Player 4 cannot be trained by trainer 2.
         Increment trainerIdx. trainerIdx = 1.
         players = [4, 7, 9]
         trainers = [2, 5, 8, 8]
                     ^  ^

       - Iteration 2:
         players[0] = 4, trainers[1] = 5
         4 <= 5. Player 4 can be trained by trainer 5.
         Increment matchCount. matchCount = 1.
         Increment playerIdx. playerIdx = 1.
         Increment trainerIdx. trainerIdx = 2.
         players = [4, 7, 9]
                     ^
         trainers = [2, 5, 8, 8]
                           ^

       - Iteration 3:
         players[1] = 7, trainers[2] = 8
         7 <= 8. Player 7 can be trained by trainer 8.
         Increment matchCount. matchCount = 2.
         Increment playerIdx. playerIdx = 2.
         Increment trainerIdx. trainerIdx = 3.
         players = [4, 7, 9]
                         ^
         trainers = [2, 5, 8, 8]
                             ^

       - Iteration 4:
         players[2] = 9, trainers[3] = 8
         9 > 8. Player 9 cannot be trained by trainer 8.
         Increment trainerIdx. trainerIdx = 4.
         players = [4, 7, 9]
                         ^
         trainers = [2, 5, 8, 8]
                               ^

       - Iteration 5:
         trainerIdx (4) is now equal to trainers.length (4). Loop terminates.

    4. Return matchCount = 2. This matches the example output.

    Time Complexity:
    Sorting takes O(N log N + M log M) where N is players.length and M is trainers.length.
    The two-pointer iteration takes O(N + M) time in the worst case (each pointer traverses its array at most once).
    Overall time complexity: O(N log N + M log M).
    Given N, M <= 10^5, N log N is approximately 10^5 * log2(10^5) ~ 10^5 * 17, which is about 1.7 * 10^6, well within typical time limits.

    Space Complexity:
    Sorting arrays in Java (Arrays.sort) for primitives typically uses a dual-pivot quicksort which is O(log N) or O(N) auxiliary space depending on the implementation details and recursion depth, but effectively O(1) for in-place sort. If a new array is created during sorting, it would be O(N). For this problem, it's generally considered O(1) auxiliary space (excluding the input arrays).
    We only use a few integer variables for pointers and count.
    Overall space complexity: O(1) auxiliary space (or O(N+M) if sorting uses external space, but usually not counted for in-place sort).
    */
    import java.util.Arrays;

 class Solution {
    public int maxMatchingPlayersWithTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int playerIdx = 0;
        int trainerIdx = 0;
        int matchCount = 0;

        while (playerIdx < players.length && trainerIdx < trainers.length) {
            if (players[playerIdx] <= trainers[trainerIdx]) {
                matchCount++;
                playerIdx++;
                trainerIdx++;
            } else {
                trainerIdx++;
            }
        }

        return matchCount;
    }
}