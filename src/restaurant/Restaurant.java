package restaurant;

import kitchen.Cook;
import kitchen.Order;
import kitchen.Waiter;
import statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static List<Tablet> tablets = new ArrayList<>();

    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        Cook cook1 = new Cook("Bender");
        Cook cook2 = new Cook("Paul");
        StatisticManager.getInstance().register(cook1);
        StatisticManager.getInstance().register(cook2);
        for (int i = 1; i < 6; i++) {
            Tablet tablet = new Tablet(i);
            tablets.add(tablet);
            tablet.addObserver(orderManager);
        }
        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);

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