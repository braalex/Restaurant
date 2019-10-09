package restaurant;

import kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        writeMessage(Dish.allDishesToString());
        writeMessage("Enter the dish name from the list. Print 'exit' to complete order.");
        while (true) {
            String s = readString();
            if (s.equalsIgnoreCase("exit")) return dishes;
            else {
                boolean isDishInTheMenu = false;
                for(Dish d : Dish.values()) {
                    if (s.equalsIgnoreCase(d.name())) {
                        dishes.add(d);
                        isDishInTheMenu = true;
                    }
                }
                if (!isDishInTheMenu) writeMessage("This dish don't present in the menu.");
            }
        }
    }
}