package kitchen;

import java.util.Arrays;

public enum Dish {
    Steak(30),
    Fish(25),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    Dish(int i) {
        duration = i;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        return Arrays.toString(Dish.values()).replaceAll("[\\[\\]]", "");
    }
}