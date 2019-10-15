package restaurant;

import ad.AdvertisementManager;
import ad.NoVideoAvailableException;
import kitchen.Order;
import kitchen.TestOrder;
import statistic.StatisticManager;
import statistic.event.NoAvailableVideoEventDataRow;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private AdvertisementManager adManager;
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        try {
            Order order = new Order(this);
            checkOrder(order);
            return order;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }
    }

    public void createTestOrder() {
        try {
            checkOrder(new TestOrder(this));
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    public void checkOrder(Order order) {
        if (!order.isEmpty()) {
            ConsoleHelper.writeMessage(order.toString());
            try {
                queue.put(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                adManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                adManager.processVideos();
            } catch (NoVideoAvailableException exc) {
                StatisticManager.getInstance().register(
                        new NoAvailableVideoEventDataRow(order.getTotalCookingTime() * 60));
                logger.log(Level.INFO, "No video is available for order " + order);
            }
        }
    }


    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}