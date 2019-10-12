package kitchen;

import restaurant.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        dishes = new ArrayList<>();
        int count = (int) (1 + Math.random() * Dish.values().length);
        while (count > 0) {
            int index = (int) (Math.random() * Dish.values().length);
            dishes.add(Dish.values()[index]);
            count--;
        }
    }
}
