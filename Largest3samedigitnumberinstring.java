// Problem Statement:
// You are given a string `num` representing a large integer. An integer is "good" if it meets the following conditions:
// 1. It is a substring of `num` with length 3.
// 2. It consists of only one unique digit.
// Return the maximum good integer as a string or an empty string "" if no such integer exists.

// Approach:
// The problem asks us to find the largest "good" integer. A good integer is a 3-digit substring with all digits being the same. We need to iterate through all possible 3-digit substrings of the input string `num`. Since we are looking for the "maximum" good integer, and the good integers are formed by digits '0' through '9', we can check for them in a decreasing order, from "999" down to "000". The first one we find that exists as a substring in `num` will be our answer. If we iterate through all possible good numbers and none are found, we return an empty string. A simple way to check if a good number exists is to use the `contains` method of the String class.

// Time Complexity:
// O(1) - The number of possible "good" integers is constant (from "999" to "000", which is 10). The length of the input string `num` is at most 1000. Checking for the existence of a 3-character substring using `contains` can take up to O(N*M) where N is the length of `num` and M is the length of the substring. However, M is always 3. In the worst case, we perform 10 such checks. Thus, the time complexity is constant with respect to the input string length, as the number of checks is fixed.

// Space Complexity:
// O(1) - We are not using any extra data structures that grow with the input size. We only store a few constant-size strings.

// Optimal Solution:
class Solution {
    public String largestGoodInteger(String num) {
        String maxGood = "";
        for (int i = 9; i >= 0; i--) {
            String goodNum = "" + i + i + i;
            if (num.contains(goodNum)) {
                return goodNum;
            }
        }
        return "";
    }
}