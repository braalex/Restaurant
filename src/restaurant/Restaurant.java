package restaurant;

import kitchen.Cook;
import kitchen.Order;
import kitchen.Waiter;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet1 = new Tablet(9966);
        Cook cook1 = new Cook("Bender");
        tablet1.addObserver(cook1);
        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        Order order1 = tablet1.createOrder();
        Order order2 = tablet1.createOrder();

        DirectorTablet dirTab = new DirectorTablet();
        dirTab.printAdvertisementProfit();
        dirTab.printCookWorkload();
        dirTab.printActiveVideoSet();
        dirTab.printArchivedVideoSet();
    }
}