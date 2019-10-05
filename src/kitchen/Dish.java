package kitchen;

import java.util.Arrays;

public enum Dish {
    Steak,
    Fish,
    Soup,
    Juice,
    Water;

    public static String allDishesToString() {
        return Arrays.toString(Dish.values()).replaceAll("[\\[\\]]", "");
    }
}
