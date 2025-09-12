/**
 * Problem Statement:
 * Alice and Bob are playing a game on a string.
 * Alice removes a non-empty substring with an odd number of vowels.
 * Bob removes a non-empty substring with an even number of vowels.
 * The first player who cannot make a move loses. Alice starts first.
 * Return true if Alice wins, and false otherwise, assuming optimal play.
 * English vowels are 'a', 'e', 'i', 'o', 'u'.
 *
 * Optimal Approach:
 * This is a game theory problem, but it has a very simple solution due to the nature of the moves.
 * Alice wins if and only if she can make a valid first move. A valid move for Alice is to remove a
 * substring with an odd number of vowels.
 *
 * The key insight is to check if such a substring exists.
 * If the original string `s` contains at least one vowel, Alice can simply choose a substring consisting of just that single vowel.
 * This substring has 1 vowel, which is an odd number.
 * This is a valid move for Alice, and she doesn't lose on her first turn.
 * Since both players play optimally, if Alice has a valid move, she can always find a winning strategy.
 * The simplest winning strategy is to make a move that leaves Bob with a state from which he cannot move. For example, if the entire string has an odd number of vowels, Alice can take the whole string and win immediately.
 * More generally, as long as a single vowel exists, Alice can remove it. This guarantees she has a move.
 *
 * The only scenario where Alice cannot make a valid move is if every single non-empty substring has an even number of vowels.
 * This is only possible if the string `s` contains no vowels at all. In this case, every substring will have 0 vowels (an even number), so Alice has no moves and loses immediately.
 *
 * Therefore, the problem simplifies to checking if the string `s` contains any vowels.
 * If the total number of vowels in `s` is greater than 0, Alice can make a move and wins.
 * If the total number of vowels is 0, Alice cannot make a move and loses.
 *
 * Time Complexity: O(n), where n is the length of the string `s`. We iterate through the string once to count the vowels.
 * Space Complexity: O(1). We only use a constant amount of extra space for the vowel counter.
 */
// Optimal Solution in Java - 
