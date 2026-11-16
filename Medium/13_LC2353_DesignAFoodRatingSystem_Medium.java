import java.util.*;

class FoodRatings {

    class Food {
        String name;
        int rating;

        Food(String name, int rating) {
            this.name = name;
            this.rating = rating;
        }
    }

    Map<String, Food> foodMap = new HashMap<>();

    Map<String, TreeSet<Food>> cuisineMap = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines,
                       int[] ratings) {

        Comparator<Food> comp = (a, b) -> {
            if (a.rating != b.rating) {
                return b.rating - a.rating;
            }
            return a.name.compareTo(b.name);
        };

        for (int i = 0; i < foods.length; i++) {
            Food food = new Food(foods[i], ratings[i]);

            foodMap.put(foods[i], food);

            cuisineMap.putIfAbsent(
                cuisines[i], new TreeSet<>(comp)
            );

            cuisineMap.get(cuisines[i]).add(food);
        }
    }

    public void changeRating(String food, int newRating) {
        Food f = foodMap.get(food);

        String cuisine = null;

        for (String c : cuisineMap.keySet()) {
            if (cuisineMap.get(c).contains(f)) {
                cuisine = c;
                break;
            }
        }

        TreeSet<Food> set = cuisineMap.get(cuisine);

        set.remove(f);
        f.rating = newRating;
        set.add(f);
    }

    public String highestRated(String cuisine) {
        return cuisineMap.get(cuisine).first().name;
    }
}
