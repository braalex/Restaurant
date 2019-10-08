package restaurant;

import kitchen.Cook;
import kitchen.Order;
import kitchen.Waiter;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet1 = new Tablet(1);
        Cook cook = new Cook("Bender");
        tablet1.addObserver(cook);
        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        Order order1 = tablet1.createOrder();
        Order order2 = tablet1.createOrder();
    }
}