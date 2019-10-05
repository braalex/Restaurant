package restaurant;

import kitchen.Cook;
import kitchen.Order;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet1 = new Tablet(1);
        tablet1.addObserver(new Cook("Bender"));
        Order order1 = tablet1.createOrder();
        Order order2 = tablet1.createOrder();
    }
}
