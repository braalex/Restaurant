package kitchen;

import restaurant.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable tablet, Object order) {
//        if (((Order) order).isEmpty()) return;
        ConsoleHelper.writeMessage("Start cooking - " + order);
        setChanged();
        notifyObservers(order);
    }

    @Override
    public String toString() {
        return name;
    }
}
