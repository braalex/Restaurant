package restaurant;

import statistic.StatisticManager;

import java.util.Locale;
import java.util.stream.Collectors;

public class DirectorTablet {
    public void printAdvertisementProfit() {
        StatisticManager.getInstance().getAdvertisementProfit()
                .forEach((k, v) -> ConsoleHelper.writeMessage(
                        String.format(Locale.ENGLISH, "%s - %.2f", k, (double) v / 100)));
        long sum = StatisticManager.getInstance().getAdvertisementProfit().values()
                .stream().collect(Collectors.summarizingLong(Long::longValue)).getSum();
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", (double) sum / 100));
    }

    public void printCookWorkload() {
        StatisticManager.getInstance().getCookWorkload()
                .forEach((k, v) -> {
                    ConsoleHelper.writeMessage(k);
                    v.forEach((k1, v1) -> ConsoleHelper.writeMessage(String.format("%s - %d min", k1, v1)));
                    ConsoleHelper.writeMessage("");
                });
    }

    public void printActiveVideoSet() {}

    public void printArchivedVideoSet() {}
}
