/**
 * Problem Statement:
 * Given an array `power` representing the damage of various spells. When a spell with damage `p` is cast,
 * no other spell with damage `p-2, p-1, p+1, or p+2` can be cast. Each spell can be cast only once.
 * Find the maximum possible total damage that can be cast.
 *
 * Optimal Approach: Dynamic Programming on Sorted Unique Damages
 *
 * This problem is a variation of the classic "House Robber" problem, where the decision to include an item (spell damage)
 * excludes items within a certain range. Since the constraint is based on the damage value, not the index, we first need to:
 * 1. Count the total damage available for each unique spell damage value.
 * 2. Sort the unique spell damage values.
 *
 * Let `uniqueDamages` be the sorted list of unique spell damage values: $d_1, d_2, \ldots, d_m$.
 * Let `totalDamage[d]` be the total damage from all spells with value $d$.
 *
 * We can use Dynamic Programming (DP) to find the maximum damage.
 * Let `dp[i]` be the maximum total damage that can be achieved using only spells with damage values from $d_1$ up to $d_i$.
 *
 * For the current unique damage $d_i$:
 *
 * **Decision 1: Cast all spells with damage $d_i$.**
 * - The total damage gained is `totalDamage[d_i]`.
 * - By casting these, we cannot cast any spells with damage: $d_i-2, d_i-1, d_i+1, d_i+2$.
 * - We need to find the *last* unique damage value $d_j$ in the sorted list such that $d_j$ is "safe" to cast before $d_i$.
 * The unsafe range for $d_i$ is $[d_i-2, d_i+2]$.
 * A damage $d_j$ is safe if $d_i - d_j > 2$.
 * We look back in the sorted list of unique damages and find the largest index $j < i$ where $d_j \le d_i - 3$.
 * The maximum damage achieved up to this safe point is `dp[j]`. If no such safe $d_j$ exists, the previous damage is 0.
 * The total damage for this decision is: `totalDamage[d_i] + (max damage from spells safe to cast before d_i)`.
 *
 * **Decision 2: Skip all spells with damage $d_i$.**
 * - The maximum total damage remains the same as the maximum damage achieved up to $d_{i-1}$, which is `dp[i-1]`.
 *
 * **The recurrence relation (simplified using two pointers or a map for look-up):**
 *
 * For a current unique damage $d_i$:
 * `dp[i] = max(`
 * `dp[i-1],` // Skip $d_i$
 * `totalDamage[d_i] + max_damage_before_safe_point` // Cast $d_i$
 * `)`
 *
 * We can use a `TreeMap` or `HashMap` for `totalDamage` and a sorted `ArrayList` for `uniqueDamages`. We will use an iterative DP approach with $O(N)$ space where $N$ is the number of unique damage values.
 *
 * Since $d_i$ can be up to $10^9$, we cannot use an array for DP indexed by damage. We must use DP indexed by the position in the sorted unique damage list.
 *
 * Time Complexity:
 * - $O(N)$ to count frequencies (where $N$ is the length of the `power` array).
 * - $O(M \log M)$ to sort the unique damages (where $M$ is the number of unique damage values, $M \le N$).
 * - $O(M^2)$ if we linearly scan for the safe point for each DP state, or **$O(M)$ if we use a two-pointer approach** (since $d_j$ increases monotonically, the safe index also increases).
 * Overall: **$O(N + M \log M)$** using the two-pointer optimization, which simplifies to $O(N \log N)$ in the worst case where $M \approx N$.
 *
 * Space Complexity:
 * - $O(M)$ for the `totalDamage` map and the `uniqueDamages` list.
 * - $O(M)$ for the DP array/list.
 * Overall: **$O(M)$**, where $M$ is the number of unique damage values ($M \le N$).
 */
// Optimal Solution in Java -
import java.util.*;

class Solution {
  public long maximumTotalDamage(int[] power) {
    Map<Integer, Integer> count = new HashMap<>();

    for (final int damage : power)
      count.merge(damage, 1, Integer::sum);

    List<Integer> uniqueDamages = getSortedUniqueDamages(count);
    final int n = uniqueDamages.size();
    long[][] dp = new long[n][2];

    for (int i = 0; i < n; ++i)
     {
      final int damage = uniqueDamages.get(i);
      if (i == 0) {
        dp[0][0] = 0;
        dp[0][1] = (long) damage * count.get(damage);
        continue;
      }
      dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
      dp[i][1] = (long) damage * count.get(damage);

      if (i >= 1 && uniqueDamages.get(i - 1) != damage - 1 &&
          uniqueDamages.get(i - 1) != damage - 2)
           {
        dp[i][1] += Math.max(dp[i - 1][0], dp[i - 1][1]);
      }
       else if (i >= 2 && uniqueDamages.get(i - 2) != damage - 2)
        {
        dp[i][1] += Math.max(dp[i - 2][0], dp[i - 2][1]);
      } 
      else if (i >= 3)
       {
        dp[i][1] += Math.max(dp[i - 3][0], dp[i - 3][1]);
      }
    }
    return Math.max(dp[n - 1][0], dp[n - 1][1]);
  }

  private List<Integer> getSortedUniqueDamages(Map<Integer, Integer> count) {
    List<Integer> uniqueDamages = new ArrayList<>(count.keySet());
    Collections.sort(uniqueDamages);
    return uniqueDamages;
  }
}
