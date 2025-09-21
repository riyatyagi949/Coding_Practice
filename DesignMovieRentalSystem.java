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
// Optimal Solution in Java - 

import java.util.*;

class MovieRentingSystem {
    Map<Integer, TreeSet<MovieInfo>> available;
    Map<String, Integer> priceMap;
    TreeSet<MovieInfo> rented;

    public MovieRentingSystem(int n, int[][] entries) {
        available = new HashMap<>();
        priceMap = new HashMap<>();
        rented = new TreeSet<>();

        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            priceMap.put(shop + "#" + movie, price);

            available.putIfAbsent(movie, new TreeSet<>());
            available.get(movie).add(new MovieInfo(shop, movie, price));
        }
    }
     public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (!available.containsKey(movie))
         return res;

        for (MovieInfo mi : available.get(movie)) {
            res.add(mi.shop);
            if (res.size() == 5) break;
        }
        return res;
    }
    public void rent(int shop, int movie) {
        int price = priceMap.get(shop + "#" + movie);
        MovieInfo mi = new MovieInfo(shop, movie, price);

        available.get(movie).remove(mi);
        rented.add(mi);
    }
     public void drop(int shop, int movie) {
        int price = priceMap.get(shop + "#" + movie);
        MovieInfo mi = new MovieInfo(shop, movie, price);

        rented.remove(mi);
        available.get(movie).add(mi);
    }
     public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        int count = 0;
        for (MovieInfo mi : rented) {
            res.add(Arrays.asList(mi.shop, mi.movie));
            if (++count == 5) break;
        }
        return res;
    }
    class MovieInfo implements Comparable<MovieInfo> {
        int shop, movie, price;

        public MovieInfo(int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }
          public int compareTo(MovieInfo other) {
            if (this.price != other.price) 
            return this.price - other.price;
            if (this.shop != other.shop)
            return this.shop - other.shop;
            return this.movie - other.movie;
        }
        public boolean equals(Object o) {
            if (!(o instanceof MovieInfo)) 
            return false;
            MovieInfo other = (MovieInfo) o;
            return shop == other.shop && movie == other.movie && price == other.price;
        }
        public int hashCode() {
            return Objects.hash(shop, movie, price);
        }
    }
}

