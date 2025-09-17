/**
 * Problem Statement:
 * Design a food rating system with three main functionalities:
 * 1. Initialize the system with a list of food items, their cuisines, and initial ratings.
 * 2. Update the rating of a specific food item.
 * 3. Find the highest-rated food for a given cuisine. If there's a tie in rating, return the one with the lexicographically smaller name.
 *
 * Optimal Approach:
 * To efficiently handle the required operations, we need a data structure that allows for fast lookups and updates.
 *
 * For `changeRating(String food, int newRating)` and `highestRated(String cuisine)` to be efficient, we need to quickly access information by food name and cuisine type.
 *
 * We can use three primary data structures for an optimal solution:
 *
 * 1. A `HashMap<String, Integer> foodToRating`: This map stores the current rating of each food item. The key is the food name (String), and the value is its rating (Integer). This allows for O(1) average time complexity to get or update a food's rating.
 *
 * 2. A `HashMap<String, String> foodToCuisine`: This map stores the cuisine type for each food item. The key is the food name (String), and the value is the cuisine (String). This allows for O(1) average time complexity to find a food's cuisine.
 *
 * 3. A `HashMap<String, PriorityQueue<Food>> cuisineToFoods`: This is the most crucial part. This map stores the food items for each cuisine. The key is the cuisine type (String). The value is a `PriorityQueue` (or a similar data structure like a `TreeSet`) that maintains the food items for that cuisine sorted by rating (descending) and then by name (lexicographically ascending).
 *
 * `PriorityQueue` is a good choice because it offers O(log k) for insertion and removal, where k is the number of elements. The `peek()` operation to get the highest-rated food is O(1).
 * The custom comparator for the `PriorityQueue` should be defined to order elements based on the rating in descending order. If ratings are equal, it should order by food name in lexicographically ascending order.
 *
 * The `Food` class can be a simple data structure to hold a food's name and its rating.
 *
 * ## Implementation details for each method:
 *
 * - **`FoodRatings(String[] foods, String[] cuisines, int[] ratings)`:**
 * - Iterate through the input arrays from `i = 0` to `n-1`.
 * - Populate `foodToRating` and `foodToCuisine` maps.
 * - For `cuisineToFoods`, for each cuisine, if it's not already in the map, create a new `PriorityQueue` with the custom comparator. Then, add the `Food` object (with name and rating) to the corresponding `PriorityQueue`.
 *
 * - **`changeRating(String food, int newRating)`:**
 * - This is where the design choice of `PriorityQueue` with its remove operation needs careful handling. Removing an arbitrary element from a `PriorityQueue` is not efficient (O(n)).
 * - A more optimal approach for `changeRating` is to "lazily" update the `PriorityQueue`.
 * - Instead of removing the old entry, we just update the rating in the `foodToRating` map and then add a new entry `(food, newRating)` to the corresponding `PriorityQueue` for that cuisine.
 * - When `highestRated` is called, we will check the top element of the `PriorityQueue`. If its rating in the `foodToRating` map doesn't match the rating stored in the `PriorityQueue` entry, it means this entry is "stale". We can then remove it from the `PriorityQueue` and repeat the check until we find a valid, up-to-date entry. This ensures the `PriorityQueue` always returns the correct highest-rated food, even with stale entries.
 *
 * - **`highestRated(String cuisine)`:**
 * - Get the `PriorityQueue` for the given `cuisine` from `cuisineToFoods`.
 * - Use a `while` loop to repeatedly peek at the top of the queue.
 * - For each element peeked, get its name and rating. Compare this rating with the current rating in `foodToRating`.
 * - If the ratings do not match, the `PriorityQueue` element is stale. Poll it and continue the loop.
 * - If the ratings match, this is the highest-rated food, so return its name.
 *
 * The lazy update strategy avoids expensive `remove` operations from the `PriorityQueue` and keeps the overall complexity efficient.
 *
 * Time Complexity:
 * - `FoodRatings` constructor: O(n log n), dominated by adding n items to priority queues (log n for each insertion). In the worst case, all items belong to one cuisine.
 * - `changeRating`: O(log n) on average due to the lazy update approach (adding a new item to the priority queue).
 * - `highestRated`: O(log n) on average. In the worst case, it might need to remove multiple stale entries, but over many calls, the total cost amortizes to O(log n) per call.
 *
 * Space Complexity:
 * - O(n), where `n` is the number of food items, to store the three hashmaps and the priority queues.
 */
// Optimal Solution in Java -
import java.util.*;

class FoodRatings {
    private Map<String, String> foodToCuisine;
    private Map<String, Integer> foodToRating;
    private Map<String, TreeSet<String>> cuisineToFoods;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineToFoods = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            cuisineToFoods.putIfAbsent(cuisine, new TreeSet<>((a, b) -> {
                int cmp = Integer.compare(foodToRating.get(b), foodToRating.get(a));

                if (cmp == 0) 
                return a.compareTo(b);
                return cmp;
            }));

            cuisineToFoods.get(cuisine).add(food);
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);

        cuisineToFoods.get(cuisine).remove(food);
        foodToRating.put(food, newRating);
        cuisineToFoods.get(cuisine).add(food);
    }
    
    public String highestRated(String cuisine) {
        return cuisineToFoods.get(cuisine).first();
    }
}