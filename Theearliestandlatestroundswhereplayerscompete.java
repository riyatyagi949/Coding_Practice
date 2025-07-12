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
    Map<String, int[]> memo = new HashMap<>();
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer)
     {
        return dfs(n, firstPlayer, secondPlayer, 1);
    }
     private int[] dfs(int n, int a, int b, int round) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (a + b == n + 1) return new int[]{round, round};

        String key = n + "," + a + "," + b;
        if (memo.containsKey(key)) return memo.get(key);

        int earliest = Integer.MAX_VALUE;
        int latest = Integer.MIN_VALUE;

        for (List<Integer> next : getNextRounds(n, a, b)) {
            int indexA = next.indexOf(a);
            int indexB = next.indexOf(b);
            int[] res = dfs(next.size(), indexA + 1, indexB + 1, round + 1);
            earliest = Math.min(earliest, res[0]);
            latest = Math.max(latest, res[1]);
        }
        int[] result = new int[]{earliest, latest};
        memo.put(key, result);
        return result;
    }
     private List<List<Integer>> getNextRounds(int n, int a, int b) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        for (int i = 1; i <= n; i++) current.add(i);

        backtrack(current, 0, current.size() - 1, a, b, new ArrayList<>(), results);
        return results;
    }
   private void backtrack(List<Integer> players, int left, int right, int a, int b,
    List<Integer> path, List<List<Integer>> results) {
        if (left > right) {
            List<Integer> sorted = new ArrayList<>(path);
            Collections.sort(sorted);
            results.add(sorted);
            return;
        }
        if (left == right) {
            path.add(players.get(left));
            backtrack(players, left + 1, right - 1, a, b, path, results);
            path.remove(path.size() - 1);
            return;
        }
        int p1 = players.get(left), p2 = players.get(right);
        boolean isAB = (p1 == a && p2 == b) || (p1 == b && p2 == a);
        boolean hasAorB = (p1 == a || p1 == b || p2 == a || p2 == b);

        if (isAB) return; 

        if (hasAorB) {
            if (p1 == a || p1 == b) {
                path.add(p1);
                backtrack(players, left + 1, right - 1, a, b, path, results);
                path.remove(path.size() - 1);
            } 
            else {
                path.add(p2);
                backtrack(players, left + 1, right - 1, a, b, path, results);
                path.remove(path.size() - 1);
            }
        } 
        else {
            path.add(p1);
            backtrack(players, left + 1, right - 1, a, b, path, results);
            path.remove(path.size() - 1);
            path.add(p2);
            backtrack(players, left + 1, right - 1, a, b, path, results);
            path.remove(path.size() - 1);
        }
    }
}
