// Problem Statement:
// On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.
// You are given an integer n, an array languages, and an array friendships where:
// There are n languages numbered 1 through n,
// languages[i] is the set of languages the i-th user knows, and
// friendships[i] = [ui, vi] denotes a friendship between the users ui and vi.
// You can choose one language and teach it to some users so that all friends can communicate with each other.
// Return the minimum number of users you need to teach.
// Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.

// Approach:
// The problem asks for the minimum number of users to teach a single language so that all friends can communicate. This implies we need to find the optimal language to teach.
// Since we can teach any of the 'n' languages, we can iterate through each language from 1 to 'n' and calculate the number of users that need to be taught that specific language. The minimum of these counts will be our answer.

// To calculate the number of users to teach for a specific language `l`:
// 1. Identify all pairs of friends who cannot communicate. A pair of friends `(u, v)` cannot communicate if they do not share any common language.
// 2. For each such non-communicating pair `(u, v)`, we need to ensure they can communicate. If we choose to teach language `l`, at least one of them must be taught this language if they don't already know it.
// 3. The most efficient way to make all non-communicating pairs communicate using language `l` is to teach `l` to all users involved in these pairs who don't already know `l`.
// 4. We can use a set to keep track of all unique users who need to learn language `l`. We iterate through all friendship pairs. If a pair `(u, v)` doesn't have a common language, we check if user `u` knows `l`. If not, we add `u` to our set of users to teach. Similarly, we check for user `v`.
// 5. The size of this set gives us the number of users to teach for language `l`.

// The overall algorithm is as follows:
// 1. Create a set `unconnectedUsers` to store unique users who are part of a non-communicating friendship pair. Iterate through `friendships`. For each pair `(u, v)`, check if they have a common language. If not, add `u` and `v` to the `unconnectedUsers` set.
// 2. If `unconnectedUsers` is empty, it means all friends can already communicate, so the answer is 0.
// 3. If not empty, initialize `minUsersToTeach` to a very large number.
// 4. Iterate through each language `l` from 1 to `n`.
// 5. For each language `l`, calculate the number of users from `unconnectedUsers` who don't know this language. This count is the number of users we would need to teach language `l`.
// 6. Update `minUsersToTeach` with the minimum of its current value and the newly calculated count.
// 7. After checking all languages, `minUsersToTeach` will hold the minimum number of users we need to teach.

// To efficiently check for common languages and language knowledge, we can convert the `languages` array into a more accessible data structure, such as an array of sets or a 2D boolean array.

// Time Complexity: O(m*L + F*L + n*U), where m is the number of users, F is the number of friendships, L is the average number of languages per user, n is the number of languages, and U is the number of unique non-communicating users.
// - O(m*L): To convert `languages` to a more efficient structure (e.g., a 2D boolean array or array of sets).
// - O(F*L): To find all non-communicating pairs. For each friendship, we iterate through languages of one user and check for presence in the other's languages.
// - O(n*U): To iterate through each language `l` and count users to teach from the `unconnectedUsers` set.
// A simpler bound: O(F * avg_lang_len + n * M), where M is number of users.
// The dominant part is O(F * max_lang_len + n * m), where F is friendships.length and m is languages.length.

// Space Complexity: O(m*n + m + F), where m is the number of users, n is the number of languages, F is the number of friendships.
// - O(m*n): For the 2D boolean array `knowsLanguage` to store language knowledge for all users.
// - O(m): For the set of users who need to be taught.
// - O(F): For storing friendships.
// The `knowsLanguage` 2D array dominates, making the complexity O(m*n).

// Optimal  Solution in Java - 

import java.util.*;

class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        boolean[][] knowsLanguage = new boolean[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int lang : languages[i]) {
                knowsLanguage[i + 1][lang] = true;
            }
        }

        Set<Integer> unconnectedUsers = new HashSet<>();
        for (int[] friendship : friendships) {
            int u = friendship[0];
            int v = friendship[1];
            boolean hasCommon = false;
            for (int lang : languages[u - 1]) {
                if (knowsLanguage[v][lang]) {
                    hasCommon = true;
                    break;
                }
            }
            if (!hasCommon) {
                unconnectedUsers.add(u);
                unconnectedUsers.add(v);
            }
        }

        if (unconnectedUsers.isEmpty()) {
            return 0;
        }

        int minUsersToTeach = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) {
            int usersToTeach = 0;
            for (int user : unconnectedUsers) {
                if (!knowsLanguage[user][lang]) {
                    usersToTeach++;
                }
            }
            minUsersToTeach = Math.min(minUsersToTeach, usersToTeach);
        }

        return minUsersToTeach;
    }
}