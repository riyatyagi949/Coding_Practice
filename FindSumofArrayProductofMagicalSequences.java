/**
 * Problem Statement:
 * Given two integers `m` (sequence size) and `k` (target set bits), and an array `nums`.
 * A sequence `seq` of length `m` (indices from `nums`) is "magical" if the sum 
 * S = 2^seq[0] + 2^seq[1] + ... + 2^seq[m - 1] has exactly `k` set bits (ones) in its binary form.
 * The array product is `prod(seq) = nums[seq[0]] * ... * nums[seq[m - 1]]`.
 * Find the sum of products of all magical sequences, modulo 10^9 + 7.
 *
 * Optimal Approach: Dynamic Programming with Combinatorics
 *
 * The problem is equivalent to finding the sum of products for all multisets of `m` indices $\{i_1, i_2, \dots, i_m\}$ 
 * such that the sum $S = \sum_{j=1}^{m} 2^{i_j}$ has `k` set bits.
 *
 * Let $c_i$ be the count of index $i$ in the sequence, so $\sum_{i=0}^{n-1} c_i = m$.
 * The sum is $S = \sum_{i=0}^{n-1} c_i \cdot 2^i$. The problem asks for the sum of:
 * $\sum_{\text{valid } c_i} \left( \prod_{i=0}^{n-1} nums[i]^{c_i} \right) \cdot \left( \frac{m!}{c_0! c_1! \dots c_{n-1}!} \right)$
 *
 * We use Dynamic Programming (DP) to build the sequence incrementally based on the index $i$.
 *
 * DP State Definition:
 * dp[i][j][l][b]: Sum of all products multiplied by their multinomial coefficient (which represents the count of permutations) 
 * for sequences of length `j` formed using only indices $0$ to $i-1$, where:
 * - `i`: Current index (or bit position) being considered (from $0$ to $n$, where $n = nums.length$).
 * - `j`: Number of elements used in the sequence so far ($\sum_{p=0}^{i-1} c_p$). ($0 \le j \le m$).
 * - `l`: The total carry propagated to bit position $i$ from lower bits. ($0 \le l \le m$).
 * - `b`: The number of set bits (ones) accumulated so far in the sum $S_i = \sum_{p=0}^{i-1} c_p \cdot 2^p$. ($0 \le b \le k$).
 *
 * Transition:
 * To compute $dp[i+1]$, we iterate over $dp[i][j][l][b]$ and choose to include index $i$ exactly $c_i$ times, where $0 \le c_i \le m-j$.
 *
 * 1. New number of elements: $j' = j + c_i$.
 * 2. New carry: $l' = \lfloor (l + c_i) / 2 \rfloor$.
 * 3. Set bit at position $i$: $b_i = (l + c_i) \pmod 2$.
 * 4. New total set bits: $b' = b + b_i$.
 *
 * The update formula is:
 * $dp[i+1][j'][l'][b'] = (dp[i+1][j'][l'][b'] + dp[i][j][l][b] \cdot \text{Power}(nums[i], c_i) \cdot \text{Comb}(j', c_i)) \pmod{MOD}$
 * where $\text{Comb}(j', c_i)$ is the binomial coefficient $\binom{j'}{c_i}$, representing the number of ways to choose $c_i$ positions for $nums[i]$ out of $j'$ total positions.
 *
 * Base Case:
 * $dp[0][0][0][0] = 1$.
 *
 * Final Result:
 * The result is the sum of $dp[n][m][l][b]$ for all $l$ and $b$ such that $b + \text{popcount}(l) = k$.
 *
 * Time Complexity: O(n * m^3 * k)
 * - $n$: Number of indices/elements in `nums` (max 50).
 * - $m$: Max sequence length, used in the states `j` and `l` (max 30).
 * - $k$: Target set bits, used in state `b` (max 30).
 * - Inner loops: 
 * - $O(n \cdot m \cdot m \cdot k)$ states.
 * - Each state transition involves an inner loop for $c_i$, which runs up to $m$ times.
 * Total complexity: $O(n \cdot m^2 \cdot k \cdot m) = O(n \cdot m^3 \cdot k)$. 
 * With constraints $50 \times 30^3 \times 30 \approx 4$ million operations, which is efficient.
 *
 * Space Complexity: O(n * m^2 * k)
 * - Dominated by the size of the 4D DP table: $O(n \cdot m \cdot m \cdot k)$.
 * - $O(50 \cdot 30 \cdot 30 \cdot 30) \approx 1.35$ million entries for the `long` type DP table.
 */

// Optimal Solution in Java - 

class Solution {
  public int magicalSum(int m, int k, int[] nums)
   {
    int[][] comb = getComb(m, m);
    Integer[][][][] mem = new Integer[m + 1][k + 1][nums.length + 1][m + 1];
    return dp(m, k, 0, 0, nums, mem, comb);
  }

  private static final int MOD = 1_000_000_007;

  private int dp(int m, int k, int i, int carry, int[] nums, Integer[][][][] mem, int[][] comb) {
    if (m < 0 || k < 0 || (m + Integer.bitCount(carry) < k))
      return 0;
    if (m == 0)
      return k == Integer.bitCount(carry) ? 1 : 0;
    if (i == nums.length)
      return 0;
    if (mem[m][k][i][carry] != null)
      return mem[m][k][i][carry];
    int res = 0;

    for (int count = 0; count <= m; count++) {
      final long contribution = comb[m][count] * modPow(nums[i], count) % MOD;
      final int newCarry = carry + count;
      res = (int) ((res +
                    (long) dp(m - count, k - (newCarry % 2), i + 1, newCarry / 2, nums, mem, comb) *
                        contribution) %
                   MOD);
    }
    return mem[m][k][i][carry] = res;
  }

  private int[][] getComb(int n, int k) {
    int[][] comb = new int[n + 1][k + 1];
    
    for (int i = 0; i <= n; i++)
      comb[i][0] = 1;
    for (int i = 1; i <= n; i++)
      for (int j = 1; j <= k; j++)
        comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;
    return comb;
  }

  private long modPow(long x, long n) {
    if (n == 0)
      return 1;
    if (n % 2 == 1)
      return x * modPow(x % MOD, n - 1) % MOD;
    return modPow(x * x % MOD, n / 2) % MOD;
  }
}
