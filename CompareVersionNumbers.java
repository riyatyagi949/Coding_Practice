/**
 * Problem Statement:
 * Given two version strings, `version1` and `version2`, compare them. Version strings consist of revisions separated by dots '.'.
 * Revisions are compared as integers, ignoring leading zeros. If one version string has fewer revisions than the other,
 * the missing revisions are treated as '0'.
 *
 * Return -1 if version1 < version2, 1 if version1 > version2, and 0 if they are equal.
 *
 * Optimal Approach:
 * The most straightforward way to solve this problem is to parse both version strings into their individual revision numbers and then compare them sequentially.
 *
 * 1. Split the version strings: The `.` character acts as a delimiter. We can use the `split()` method in Java to separate the version string into an array of strings, where each string represents a revision number. Note that `split(".")` requires escaping the dot, so `split("\\.")` should be used.
 *
 * 2. Convert to Integers and Compare: Iterate through the arrays of revisions. Since the versions might have different numbers of revisions, we need to iterate up to the length of the longer array. For each index `i`:
 * a. Get the revision number from `version1`. If `i` is within the bounds of `version1`'s array, parse the string to an integer. Otherwise, the revision is `0`.
 * b. Get the revision number from `version2`. Similarly, if `i` is within the bounds, parse the string; otherwise, the revision is `0`.
 * c. Compare the two integer revisions.
 * - If `num1 < num2`, `version1` is smaller, so return -1.
 * - If `num1 > num2`, `version1` is larger, so return 1.
 *
 * 3. Handle Equality: If the loop finishes without returning, it means all corresponding revisions were equal. This implies the two version strings are identical, so we should return 0. The rule about trailing zeros is implicitly handled by iterating up to the length of the longer string and treating missing revisions as 0.
 *
 * Time Complexity: O(L1 + L2), where L1 and L2 are the lengths of `version1` and `version2` respectively. This is because we iterate through each string once to split it and then iterate again up to the maximum number of revisions.
 *
 * Space Complexity: O(L1 + L2) to store the arrays of revision strings. In the worst case, each character could be a revision, although this is unlikely.
 */
// Optimal Solution in Java - 

class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int len1 = v1.length;
        int len2 = v2.length;
        int maxLen = Math.max(len1, len2);

        for (int i = 0; i < maxLen; i++) {
            int num1 = (i < len1) ? Integer.parseInt(v1[i]) : 0;
            int num2 = (i < len2) ? Integer.parseInt(v2[i]) : 0;

            if (num1 < num2) {
                return -1;
            }
            if (num1 > num2) {
                return 1;
            }
        }

        return 0;
    }
}