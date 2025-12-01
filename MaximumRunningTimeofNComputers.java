/**
 * Problem Statement: Maximum Running Time of N Computers
 * -----------------------------------------------------
 * Given 'n' computers and an array 'batteries' where batteries[i] is the minutes 
 * the i-th battery can run a computer. We can switch batteries at any integer time 
 * moment. Find the maximum number of minutes 'T' that all 'n' computers can run 
 * simultaneously.
 *
 * Constraints:
 * 1 <= n <= batteries.length <= 10^5
 * 1 <= batteries[i] <= 10^9
 */
/**
     * Optimal Solution: Binary Search on the Answer (Time T)
     * ------------------------------------------------------
     * The problem asks for the maximum time T. The feasibility function is monotonic: 
     * if it's possible to run 'n' computers for time T, it is also possible for any time T' < T.
     * This monotonicity allows for an efficient Binary Search on the time T.
     *
     * 1. Search Range:
     * - Minimum possible time (left): 0
     * - Maximum possible time (right): The total sum of all battery life divided by 'n'.
     * (i.e., the average time, which is the theoretical upper limit).
     *
     * 2. Feasibility Check (`canRun(T)`):
     * - To run 'n' computers for time T, we need a total energy of $n \times T$.
     * - How much total energy can the batteries provide to the $n$ computers?
     * - A single battery `b` can contribute at most `b` minutes, but it only needs to contribute
     * at most `T` minutes to the overall $n \times T$ goal.
     * - **Greedy Check:** If a battery `b` is shorter than the target time $T$ ($b < T$), 
     * it must be fully consumed, contributing `b` time.
     * - If a battery `b` is longer than the target time $T$ ($b \ge T$), it can power 
     * one computer for the full time $T$. We only count its contribution as $T$.
     * - The total available operating time is $\sum \min(\text{battery}_i, T)$.
     * - **Condition:** $\sum \min(\text{battery}_i, T) \ge n \times T$.
     *
     * The check is simplified by noting that we only need to consider the batteries smaller
     * than $T$ and add $T$ for the rest. However, the direct sum $\sum \min(\text{battery}_i, T)$
     * is equivalent and sufficient.
     *//**
 * Time Complexity Analysis:
 * -------------------------
 * O(M log S), where M is the number of batteries (batteries.length), and S is 
 * the maximum possible time (total sum / n).
 * - **Binary Search Range:** The search space S is up to $10^{14}$ (since sum $\approx 10^5 \times 10^9 = 10^{14}$).
 * The number of iterations is $\log S$, which is at most $\log_2(10^{14}) \approx 47$.
 * - **Feasibility Check (`canRun`):** In each iteration, we loop through all M batteries, 
 * taking O(M) time. M is up to $10^5$.
 * - **Total Complexity:** $O(\text{iterations} \times \text{time per iteration}) = O(\log S \times M)$.
 * Since $\log S$ is small, the complexity is dominated by $O(M \log S)$, which is very efficient.
 *
 * Space Complexity Analysis:
 * --------------------------
 * O(1) auxiliary space.
 * - The algorithm uses only a few long variables for the binary search and summation.
 */
// Code - 
import java.util.*;