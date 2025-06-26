/* Problem Statement:
Given a string s consisting of lowercase alphabets and an integer k, your task is to find the minimum possible value of the string after removing exactly k characters. The value of the string is defined as the sum of the squares of the frequencies of each distinct character present in the string.

Approach:
To minimize the sum of squares of frequencies, we should always try to remove characters from the character that has the highest frequency. This is because a larger frequency contributes disproportionately more to the sum of squares. For example, reducing a frequency from 5 to 4 reduces the sum by 5^2 - 4^2 = 9, while reducing from 2 to 1 reduces it by 2^2 - 1^2 = 3. Thus, it's always optimal to reduce the highest frequency.

We can use a max-priority queue to store the frequencies of all characters.
1. First, calculate the frequency of each character in the string.
2. Add these frequencies to a max-priority queue.
3. While k > 0 and the priority queue is not empty:
    a. Extract the maximum frequency from the priority queue.
    b. Decrement this frequency by 1.
    c. If the decremented frequency is greater than 0, add it back to the priority queue.
    d. Decrement k.
4. After k removals, calculate the sum of squares of the remaining frequencies in the priority queue.

Time Complexity:
O(N + K log M), where N is the length of the string, K is the number of characters to remove, and M is the number of distinct characters (at most 26).
- Counting frequencies takes O(N) time.
- Building the priority queue takes O(M log M) time (at most O(26 log 26)).
- Each removal and re-insertion from the priority queue takes O(log M) time. We perform this K times, so O(K log M).
- Calculating the final sum takes O(M) time.
Since M is a constant (26), the complexity simplifies to O(N + K).

Space Complexity:
O(M), where M is the number of distinct characters (at most 26), for storing character frequencies in a map/array and the priority queue. This is effectively O(1) constant space.
*/

class Solution {
    public int minValue(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // Max-priority queue
        for (int f : freq) {
            if (f > 0) {
                pq.offer(f);
            }
        }

        while (k > 0 && !pq.isEmpty()) {
            int currentMaxFreq = pq.poll();
            currentMaxFreq--;
            if (currentMaxFreq > 0) {
                pq.offer(currentMaxFreq);
            }
            k--;
        }

        int result = 0;
        while (!pq.isEmpty()) {
            int f = pq.poll();
            result += f * f;
        }

        return result;
    }
}