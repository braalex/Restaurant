package restaurant;

import kitchen.Cook;
import kitchen.Order;
import kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Waiter waiter = new Waiter();

        Cook cook1 = new Cook("Tony");
        cook1.setQueue(orderQueue);
        cook1.addObserver(waiter);
        Thread c1 = new Thread(cook1);
        c1.setDaemon(true);

        Cook cook2 = new Cook("Paul");
        cook2.setQueue(orderQueue);
        cook2.addObserver(waiter);
        Thread c2 = new Thread(cook2);
        c2.setDaemon(true);

        c1.start();
        c2.start();

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }

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