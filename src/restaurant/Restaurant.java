package restaurant;

import kitchen.Cook;
import kitchen.Order;
import kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static List<Tablet> tablets = new ArrayList<>();

    public static void main(String[] args) {
        Tablet tablet1 = new Tablet(1);
        Tablet tablet2 = new Tablet(2);
        Tablet tablet3 = new Tablet(3);
        tablets.add(tablet1);
        tablets.add(tablet2);
        tablets.add(tablet3);
        Cook cook1 = new Cook("Bender");
        Cook cook2 = new Cook("Paul");
        tablet1.addObserver(cook1);
        tablet2.addObserver(cook2);
        tablet3.addObserver(cook1);
        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);

        Thread t = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

        DirectorTablet dirTab = new DirectorTablet();
        dirTab.printAdvertisementProfit();
        dirTab.printCookWorkload();
        dirTab.printActiveVideoSet();
        dirTab.printArchivedVideoSet();
    }
}