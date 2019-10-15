package kitchen;

import restaurant.ConsoleHelper;
import statistic.StatisticManager;
import statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        StatisticManager.getInstance().register(new CookedOrderEventDataRow
                (order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes()));
        ConsoleHelper.writeMessage("Start cooking - " + order);
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!isBusy() && !queue.isEmpty())
                    startCookingOrder(queue.take());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
