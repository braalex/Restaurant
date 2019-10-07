package kitchen;

import restaurant.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

public class Waiter implements Observer {
    @Override
    public void update(Observable observable, Object order) {
        ConsoleHelper.writeMessage(order.toString() + " was cooked by " + observable.toString());
    }
}