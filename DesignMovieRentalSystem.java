/**
 * Problem Statement:
 * Design a movie rental system with `n` shops. The system should support the following operations:
 * - `search(movie)`: Find the 5 cheapest unrented copies of a given movie. Sort by price, then by shop ID.
 * - `rent(shop, movie)`: Rent an unrented copy of a movie from a specific shop.
 * - `drop(shop, movie)`: Drop off a rented movie at a specific shop.
 * - `report()`: Find the 5 cheapest currently rented movies. Sort by price, then shop ID, then movie ID.
 *
 * Optimal Approach:
 * To efficiently handle all operations, we need data structures that allow for quick searching, adding, and removing elements while maintaining
 * the required sorted order. Given the constraints and the nature of the queries, `TreeSet` and `HashMap` are excellent choices.
 *
 * We need to maintain the state of two groups of movies: **unrented** (available) and **rented**.
 *
 * 1.  **For `available` movies**:
 * - We need to search by movie ID. A `Map<Integer, TreeSet<MovieInfo>>` is suitable, where the key is the movie ID and the value is a `TreeSet` of `MovieInfo` objects.
 * - `MovieInfo` should be a custom class or record containing `price`, `shop`, and `movie` details.
 * - The `TreeSet` automatically keeps the unrented movies for a given movie ID sorted. The custom `Comparator` for `MovieInfo` should prioritize price, then shop ID, as per the `search` function's requirements.
 * - A separate `Map<String, Integer>` can store the price for each `(shop, movie)` pair, providing quick access to prices without iterating through `TreeSet`. The key can be a string like `"shop_movie"`.
 *
 * 2.  **For `rented` movies**:
 * - The `report` function requires finding the 5 cheapest rented movies globally, not per movie ID. A single `TreeSet<MovieInfo>` is perfect for this.
 * - The `Comparator` for this `TreeSet` needs to sort based on price, then shop ID, then movie ID, as specified by the `report` function.
 *
 * **Data Structures:**
 * - `available`: `Map<Integer, TreeSet<MovieInfo>>`
 * - `priceMap`: `Map<String, Integer>`
 * - `rented`: `TreeSet<MovieInfo>`
 *
 * **Class `MovieInfo`:**
 * - To represent a movie instance, a simple class or record with `shop`, `movie`, and `price` is needed.
 * - Implement a custom `Comparator` for this class to define the sorting logic for both `available` and `rented` sets.
 *
 * **Implementation of Methods:**
 * - `MovieRentingSystem(n, entries)`: Initialize the maps and sets. Populate `available` and `priceMap` from the `entries` array.
 * - `search(movie)`: Get the `TreeSet` for the given `movie` from the `available` map. Iterate through the first 5 elements of the set and return their shop IDs.
 * - `rent(shop, movie)`:
 * - Construct a `MovieInfo` object using the price from `priceMap`.
 * - Remove this object from the `available` `TreeSet` for the specific movie ID.
 * - Add the object to the `rented` `TreeSet`.
 * - `drop(shop, movie)`:
 * - Construct a `MovieInfo` object using the price from `priceMap`.
 * - Remove this object from the `rented` `TreeSet`.
 * - Add the object back to the `available` `TreeSet` for the specific movie ID.
 * - `report()`:
 * - Get the first 5 elements from the `rented` `TreeSet` (which are already sorted).
 * - Return a list of `[shop, movie]` pairs for these elements.
 *
 * Time Complexity:
 * - `MovieRentingSystem`: O(E * log(S)), where E is the number of entries and S is the number of shops per movie. Sorting and insertion into TreeSet takes logarithmic time.
 * - `search`: O(log(S) + 5), where S is the number of shops with the given movie. Logarithmic to find the set, then constant time to get the first 5 elements.
 * - `rent`, `drop`: O(log(E) + log(R)), where E is the max number of movies per shop and R is the total number of rented movies. The operations are logarithmic due to `TreeSet`.
 * - `report`: O(5) - constant time after initial setup.
 *
 * Space Complexity:
 * - O(E) to store all movie entries across the various maps and sets.
 */
import java.util.*;

