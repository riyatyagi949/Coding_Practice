// Problem Statement:
// Given `n` players in a tournament, `firstPlayer` and `secondPlayer` are the best players and always win unless they compete against each other. Other matches can have arbitrary outcomes. Return an array `[earliestRound, latestRound]` representing the earliest and latest possible round numbers when `firstPlayer` and `secondPlayer` will compete.

// Approach:
// This problem is solved using recursion with memoization (Dynamic Programming). The state of our DP is represented by a bitmask, where each bit indicates if a player (based on their original 1-indexed number) is still active in the tournament.

// The `solve(mask)` function calculates the `[minRound, maxRound]` for the given set of active players represented by `mask`.
// 1. Base Case: If `firstPlayer` and `secondPlayer` are paired in the current round (determined by their relative positions in the `activePlayersOriginalIndices` list), they meet in this round. We return `[1, 1]`.
// 2. Memoization: If the result for the current `mask` is already computed and stored in the `memo` map, return the stored result.
// 3. Recursive Step:
//    a. We identify all active players from the `mask` and store their original indices in `activePlayersOriginalIndices`.
//    b. We use a helper function `generateNextMasks` to recursively explore all possible outcomes of matches for players who are neither `firstPlayer` nor `secondPlayer`.
//    c. For matches involving `firstPlayer` or `secondPlayer`, these special players are assumed to win (as they always do until they meet each other).
//    d. For each possible combination of winners, a `nextWinnersMask` is formed.
//    e. We recursively call `solve(nextWinnersMask)` to get the min/max rounds from the next round onwards.
//    f. The results from these recursive calls are aggregated (by adding 1 for the current round) to find the overall `minRound` and `maxRound` for the current `mask`.
// 4. Store and Return: The computed `[minRound, maxRound]` for the current `mask` is stored in the `memo` map before being returned.

// The `generateNextMasks` helper function is crucial for exploring all combinations of winners for "indifferent" matches (those not involving `firstPlayer` or `secondPlayer`). It builds the `nextWinnersMask` bit by bit for players advancing to the next round.

// Time Complexity:
// The maximum number of states (unique masks) visited is significantly less than `2^N` due to the nature of the tournament (players always maintaining their relative order and `firstPlayer`/`secondPlayer` always advancing). In practice, for `N=28`, this approach is efficient enough. Each state involves iterating through active players (`O(N)`) and potentially `2^(N/2)` recursive calls for indifferent matches. However, the number of distinct reachable masks is bounded, making the overall complexity manageable.

// Space Complexity:
// The space complexity is dominated by the `memo` HashMap, which stores `int[]` for each visited `mask`. The maximum size of the `memo` map determines the space.

import java.util.*;

class Solution {
    int firstP;
    int secondP;
    Map<Integer, int[]> memo;

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        this.firstP = firstPlayer;
        this.secondP = secondPlayer;
        this.memo = new HashMap<>();

        if (this.firstP > this.secondP) {
            int temp = this.firstP;
            this.firstP = this.secondP;
            this.secondP = temp;
        }
        int initialMask = (1 << n) - 1;
       return solve(initialMask);
    }

    private int[] solve(int mask) {
        // Calculate current relative positions of firstP and secondP
        int currentFirstPPos = -1;
        int currentSecondPPos = -1;
        int numActivePlayers = 0;
        List<Integer> activePlayersOriginalIndices = new ArrayList<>();

        for (int i = 0; i < 28; i++) { // Iterate up to max possible N (28)
            if (((mask >> i) & 1) == 1) { // If player (i+1) is active
                numActivePlayers++;
                activePlayersOriginalIndices.add(i + 1); // Store original player number
                if (i + 1 == firstP) {
                    currentFirstPPos = numActivePlayers; // 1-based relative position in current list
                }
                if (i + 1 == secondP) {
                    currentSecondPPos = numActivePlayers; // 1-based relative position
                }
            }
        }

        // Base case: firstP and secondP meet in this round
        // This occurs if their relative positions are symmetric (e.g., 1st vs last, 2nd vs 2nd-to-last)
        if (currentFirstPPos + currentSecondPPos == numActivePlayers + 1) {
            return new int[]{1, 1}; // They meet in this round, so it takes 1 more round
        }

        // Memoization check: if result for this mask already computed, return it
        if (memo.containsKey(mask)) {
            return memo.get(mask);
        }

        // Initialize min and max rounds for the current state (to be updated by recursive calls)
        int[] currentMinMaxResults = {Integer.MAX_VALUE, Integer.MIN_VALUE};

        // Recursively explore all possible outcomes of matches to generate next round's masks
        generateNextMasks(activePlayersOriginalIndices, 0, 0, currentMinMaxResults);

        // Store and return the computed min/max rounds for the current mask
        memo.put(mask, currentMinMaxResults);
        return currentMinMaxResults;
    }

    private void generateNextMasks(List<Integer> activePlayersOriginalIndices, int pairIdx, int currentWinnersMask, int[] minMax) {
        int nPlayersInRound = activePlayersOriginalIndices.size();
        int numPairs = nPlayersInRound / 2;

        // Base case for this helper: all pairs have been processed
        if (pairIdx == numPairs) {
            // If an odd number of players, the middle player automatically advances
            if (nPlayersInRound % 2 == 1) {
                currentWinnersMask |= (1 << (activePlayersOriginalIndices.get(numPairs) - 1));
            }

            // Call `solve` for the next round's mask to get its min/max rounds
            int[] resFromNextRound = solve(currentWinnersMask);

            // Update the overall min and max rounds for the current `solve` call's state
            // Add 1 to account for the current round
            minMax[0] = Math.min(minMax[0], 1 + resFromNextRound[0]);
            minMax[1] = Math.max(minMax[1], 1 + resFromNextRound[1]);
            return;
        }

        // Get the two players in the current pair based on their positions in the active list
        int p1 = activePlayersOriginalIndices.get(pairIdx);
        int p2 = activePlayersOriginalIndices.get(nPlayersInRound - 1 - pairIdx);

        // Check if either player is `firstP` or `secondP`
        boolean p1IsSpecial = (p1 == firstP || p1 == secondP);
        boolean p2IsSpecial = (p2 == firstP || p2 == secondP);

        // If both p1 and p2 are special players, they would have met in this round,
        // and the base case in `solve()` would have handled it. So, this condition
        // logically shouldn't be met here.

        if (p1IsSpecial) {
            // If p1 is a special player, p1 must win
            generateNextMasks(activePlayersOriginalIndices, pairIdx + 1, currentWinnersMask | (1 << (p1 - 1)), minMax);
        } else if (p2IsSpecial) {
            // If p2 is a special player, p2 must win
            generateNextMasks(activePlayersOriginalIndices, pairIdx + 1, currentWinnersMask | (1 << (p2 - 1)), minMax);
        } else {
            // Neither p1 nor p2 is a special player, so either can win.
            // Explore both possibilities:

            // Option 1: p1 wins
            generateNextMasks(activePlayersOriginalIndices, pairIdx + 1, currentWinnersMask | (1 << (p1 - 1)), minMax);

        }
    }
}