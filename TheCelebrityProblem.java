// The Celebrity Problem
// Problem Statement: A celebrity is a person who is known to all but does not know anyone at a party. A party is being organized, and a square matrix mat[][] of size n*n represents the people. mat[i][j] = 1 means person i knows person j. You need to find the index of the celebrity. If a celebrity doesn't exist, return -1.

// Note: Use 0-based indexing.

// Constraints: 1
// lemat.size()
// le1000, 0
// lemat[i][j]
// le1, and mat[i][i]=1.

// Approach:
// A two-pointer approach is an efficient way to solve this problem. We can use two pointers, i and j, initially pointing to the first and last person, respectively. We iterate through the people, comparing i and j.

// If person i knows person j (mat[i][j] == 1), then i cannot be the celebrity. A celebrity doesn't know anyone. Therefore, we increment i.

// If person i does not know person j (mat[i][j] == 0), then j cannot be the celebrity. A celebrity must be known by everyone. This implies that if i doesn't know j, then j cannot be the celebrity because i is a person who doesn't know them. Therefore, we decrement j.

// This process eliminates one person at each step, leaving us with a single candidate for the celebrity. After the loop finishes, the remaining pointer (i or j) points to the potential celebrity.

// Finally, we must verify if this candidate is indeed a celebrity by checking the two conditions:

// The candidate knows no one: We check if mat[candidate][k] is 0 for all k from 0 to n-1.

// Everyone else knows the candidate: We check if mat[k][candidate] is 1 for all k from 0 to n-1, where k is not the candidate.

// If both conditions are met, the candidate is the celebrity, and we return their index. Otherwise, no celebrity exists, and we return -1.

// Time Complexity: O(N), where N is the number of people. The initial two-pointer sweep takes O(N) time, and the final verification loop also takes O(N) time.
// Space Complexity: O(1), as we are only using a few pointers and no additional data structures.

// Optimal Solution:

// Java

// class Solution {
//     // Function to find if a person a knows a person b.
//     private boolean knows(int a, int b, int[][] M) {
//         return M[a][b] == 1;
//     }
    
//     // Function to find the celebrity in the party.
//     int celebrity(int M[][], int n) {
//         // Step 1: Find a potential celebrity using a two-pointer approach
//         int candidate = 0;
//         int i = 0;
//         int j = n - 1;
        
//         while (i < j) {
//             if (knows(i, j, M)) {
//                 i++;
//             } else {
//                 j--;
//             }
//         }
//         candidate = i;
        
//         // Step 2: Verify if the candidate is a true celebrity
//         for (int k = 0; k < n; k++) {
//             if (k != candidate) {
//                 // Condition 1: Candidate must not know anyone
//                 // Condition 2: Everyone else must know the candidate
//                 if (knows(candidate, k, M) || !knows(k, candidate, M)) {
//                     return -1;
//                 }
//             }
//         }
        
//         return candidate;
//     }
// }