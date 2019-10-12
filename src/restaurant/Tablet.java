package restaurant;

import ad.AdvertisementManager;
import ad.NoVideoAvailableException;
import kitchen.Order;
import kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private AdvertisementManager adManager;

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
            setChanged();
            notifyObservers(order);
            try {
                adManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                adManager.processVideos();
            } catch (NoVideoAvailableException exc) {
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