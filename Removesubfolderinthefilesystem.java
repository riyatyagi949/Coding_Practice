// Problem Statement:
// Given a list of folders `folder`, return the folders after removing all sub-folders.
// A sub-folder `folder[i]` is located within another `folder[j]` if `folder[i]` starts with `folder[j]` followed by a "/".

// Approach:
// 1. Sort the input `folder` array lexicographically. This ensures that parent folders always appear before their sub-folders.
// 2. Initialize an empty list `result` to store the non-sub-folders.
// 3. Add the first folder from the sorted array to `result` as it cannot be a sub-folder of any preceding folder (since it's the first).
// 4. Iterate from the second folder onwards. For each `currentFolder`:
//    a. Check if `currentFolder` is a sub-folder of the `lastRootFolder` (the last folder added to `result`).
//    b. A folder `X` is a sub-folder of `Y` if `X` starts with `Y` and the character immediately following `Y` in `X` is a '/'. This handles cases like "/a" and "/aa".
//    c. If `currentFolder` is NOT a sub-folder of `lastRootFolder`, it means `currentFolder` itself is a top-level folder (or a folder not contained by any previously identified top-level folder). In this case, add `currentFolder` to `result` and update `lastRootFolder` to `currentFolder`.
// 5. Return the `result` list.

// Time Complexity:
// O(N * L * log N), where N is the number of folders and L is the maximum length of a folder path.
// Sorting dominates the complexity. String comparisons within sorting take O(L) time.

// Space Complexity:
// O(N * L) in the worst case, to store the `result` list and for the sorting process (depending on Java's `Arrays.sort` implementation).

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder); // Sorts lexicographically, putting parents before children

        List<String> result = new ArrayList<>();
        if (folder.length == 0) {
            return result;
        }

        // The first folder is always a root folder
        result.add(folder[0]);
        String lastRootFolder = folder[0];

        for (int i = 1; i < folder.length; i++) {
            String currentFolder = folder[i];

            // Check if currentFolder is a sub-folder of lastRootFolder
            // It must start with lastRootFolder, be longer, and have a '/' exactly at the length of lastRootFolder
            boolean isSubfolder = currentFolder.startsWith(lastRootFolder) &&
                                  currentFolder.length() > lastRootFolder.length() &&
                                  currentFolder.charAt(lastRootFolder.length()) == '/';

            if (!isSubfolder) {
                // If it's not a sub-folder, it's a new root folder
                result.add(currentFolder);
                lastRootFolder = currentFolder;
            }
        }

        return result;
    }
}