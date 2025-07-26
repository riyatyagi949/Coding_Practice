/*
Problem Statement:
Given an array of integer arr[] where each number represents a vote to a candidate. Return the candidates that have votes greater than one-third of the total votes. If there's not a majority vote, return an empty array.
Note: The answer should be returned in an increasing format.

Approach:
This problem can be solved using the Boyer-Moore Voting Algorithm, which is extended to find at most two majority elements. Since a number can be a majority element if its frequency is greater than n/3, there can be at most two such elements. We can initialize two candidates and their counts. We iterate through the array, and if the current element is one of the candidates, we increment its count. If not, and a candidate's count is 0, we assign the current element as that candidate. Otherwise, we decrement both counts. After the first pass, the two candidates will be the potential majority elements. We then perform a second pass to verify if their counts are actually greater than n/3. Finally, we add the valid candidates to a list and sort it before returning.

Time Complexity:
The time complexity is O(N) because we iterate through the array a constant number of times (two passes).

Space Complexity:
The space complexity is O(1) because we only use a few variables to store the candidates and their counts, and the result list will contain at most two elements.

Optimal Solution:
*/
import java.util.*;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } 
            else if (num == candidate2) {
                count2++;
            }
        }

        List<Integer> result = new ArrayList<>();
        if (count1 > nums.length / 3) {
            result.add(candidate1);
        }

        if (count2 > nums.length / 3) {
            result.add(candidate2);
        }
        
        Collections.sort(result);
        return result;
    }
}