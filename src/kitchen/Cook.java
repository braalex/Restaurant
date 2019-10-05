package kitchen;

import restaurant.ConsoleHelper;
import restaurant.Tablet;

import java.util.Observable;
import java.util.Observer;

public class Cook implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable tablet, Object order) {
        ConsoleHelper.writeMessage("Start cooking - " + order.toString());
    }

    @Override
    public String toString() {
        return name;
    }
}
