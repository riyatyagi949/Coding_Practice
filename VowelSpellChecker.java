/**
 * Problem Statement:
 * Given a `wordlist` and a list of `queries`, implement a spellchecker. For each query, return the correct word from the wordlist based on the following precedence rules:
 * 1. Exact match (case-sensitive).
 * 2. Case-insensitive match. If multiple matches exist, return the first one from the original wordlist.
 * 3. Vowel-insensitive match. This means replacing all vowels ('a', 'e', 'i', 'o', 'u') with a single placeholder character. If multiple matches exist, return the first one from the original wordlist.
 * 4. If no match is found, return an empty string.
 *
 * Optimal Approach:
 * The problem requires searching for matches in the wordlist with different precedence rules. A naive approach of iterating through the entire wordlist for each query would be too slow. A more efficient solution is to pre-process the `wordlist` into different data structures that allow for quick lookups based on the three matching rules. Hash Maps (or Hash Sets) are ideal for this.
 *
 * We can use three different hash maps to store the words from the `wordlist`:
 * 1. A `HashSet` for **exact matches**. This allows for a fast O(1) average time complexity lookup for the first rule.
 * 2. A `HashMap` for **case-insensitive matches**. The keys will be the lowercase version of the words, and the values will be the original words from the `wordlist`. We should only store the first instance of a lowercase word encountered in the `wordlist` to handle the "first such match" rule.
 * 3. A `HashMap` for **vowel-insensitive matches**. The keys will be a "de-voweled" version of the words (e.g., replace all vowels with a placeholder like `*`), and the values will be the original words. Similar to the case-insensitive map, we store only the first encountered match.
 *
 * The `devowel` function will be a helper to convert a word into its vowel-insensitive form (e.g., "KiTe" -> "k*t*").
 *
 * The main algorithm proceeds as follows:
 *
 * 1. Pre-processing:
 * - Initialize the three data structures: `exactMatch` (HashSet), `caseInsensitive` (HashMap), and `vowelInsensitive` (HashMap).
 * - Iterate through the `wordlist`:
 * - Add the word to the `exactMatch` set.
 * - Convert the word to lowercase. Add it to the `caseInsensitive` map with the original word as the value, but only if the lowercase key is not already in the map.
 * - Convert the word to lowercase and then de-vowel it. Add this de-voweled string to the `vowelInsensitive` map with the original word as the value, again, only if the key is not already present.
 *
 * 2. Processing Queries:
 * - Initialize a result array or list.
 * - Iterate through the `queries` array:
 * - For each query `q`:
 * - **Rule 1 (Exact Match):** Check if `exactMatch.contains(q)`. If so, add `q` to the result.
 * - **Rule 2 (Case-Insensitive Match):** If rule 1 fails, convert `q` to lowercase. Check if `caseInsensitive.containsKey(q.toLowerCase())`. If so, add the value from the map to the result.
 * - **Rule 3 (Vowel-Insensitive Match):** If rule 2 fails, de-vowel the lowercase `q`. Check if `vowelInsensitive.containsKey(devoweledQ)`. If so, add the value from the map to the result.
 * - **Rule 4 (No Match):** If all rules fail, add an empty string to the result.
 *
 * This approach respects the precedence rules and handles the "first match" requirement by carefully populating the hash maps during pre-processing.
 *
 * Time Complexity: O(L + Q), where L is the total number of characters in all words in `wordlist`, and Q is the total number of characters in all `queries`.
 * This is because pre-processing the wordlist takes time proportional to its total length. Each query lookup then takes O(word.length) due to string conversions and hashing, which on average is O(1) if string length is considered constant.
 *
 * Space Complexity: O(L) to store the three hash maps, which on average store all the words from the `wordlist`.
 */

//  Optimal Solution in Java - 

class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exactWords = new HashSet<>();
        Map<String, String> caseInsensitive = new HashMap<>();
        Map<String, String> vowelInsensitive = new HashMap<>();
        
        for (String word : wordlist) {
            exactWords.add(word);
            
            String lower = word.toLowerCase();
            caseInsensitive.putIfAbsent(lower, word);
            
            String devoweled = devowel(lower);
            vowelInsensitive.putIfAbsent(devoweled, word);
        }
        
        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            
            if (exactWords.contains(query)) {
                result[i] = query;
            }
             else {
                String lower = query.toLowerCase();
                if (caseInsensitive.containsKey(lower)) {
                    result[i] = caseInsensitive.get(lower);
                }
                 else {
                    String devoweled = devowel(lower);
                    result[i] = vowelInsensitive.getOrDefault(devoweled, "");
                }
            }
        }
        return result;
    }
    private String devowel(String word) {
        return word.replaceAll("[aeiou]", "*");
    }
}
