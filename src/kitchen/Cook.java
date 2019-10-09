package kitchen;

import restaurant.ConsoleHelper;
import restaurant.Tablet;
import statistic.StatisticManager;
import statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Tablet tablet = (Tablet) o;
        Order order = (Order) arg;
        ConsoleHelper.writeMessage("Start cooking - " + order);
        StatisticManager.getInstance().register(new CookedOrderEventDataRow
                (tablet.toString(), name, order.getTotalCookingTime(), order.getDishes()));
        setChanged();
        notifyObservers(order);
    }

    @Override
    public String toString() {
        return name;
    }
}
