package restaurant;

import kitchen.Cook;
import kitchen.Order;
import statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public OrderManager() {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    if (!orderQueue.isEmpty()) {
                        for (Cook cook : StatisticManager.getInstance().getCooks()) {
                            if (!cook.isBusy() && !orderQueue.isEmpty())
                                cook.startCookingOrder(orderQueue.take());
                        }
                    }
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            orderQueue.put((Order) arg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
