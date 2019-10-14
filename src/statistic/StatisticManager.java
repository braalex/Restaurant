package statistic;

import kitchen.Cook;
import statistic.event.CookedOrderEventDataRow;
import statistic.event.EventDataRow;
import statistic.event.EventType;
import statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager instance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    private StatisticManager() {
    }

    public Set<Cook> getCooks() {
        return cooks;
    }

    public static StatisticManager getInstance() {
        return instance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public Map<String, Long> getAdvertisementProfit() {
        List<EventDataRow> videoEvents = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String, Long> map = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow event : videoEvents) {
            String date = sdf.format(event.getDate());
            Long amount = ((VideoSelectedEventDataRow) event).getAmount();
            map.merge(date, amount, Long::sum);
        }
        return map;
    }

    public Map<String, Map<String, Integer>> getCookWorkload() {
        List<EventDataRow> cookEvents = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String, Map<String, Integer>> map = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow event : cookEvents) {
            String date = sdf.format(event.getDate());
            CookedOrderEventDataRow cookEvent = (CookedOrderEventDataRow) event;
            String cookName = cookEvent.getCookName();
            int time = cookEvent.getTime();

            if (!map.containsKey(date)) {
                Map<String, Integer> cookWorkTime = new TreeMap<>();
                cookWorkTime.put(cookName, time);
                map.put(date, cookWorkTime);
            } else {
                Map<String, Integer> cookWorkTime = map.get(date);
                if (cookWorkTime.containsKey(cookName)) {
                    cookWorkTime.put(cookName, cookWorkTime.get(cookName) + time);
                } else {
                    cookWorkTime.put(cookName, time);
                }
            }
        }
        return map;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType type : EventType.values()) {
                storage.put(type, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            List<EventDataRow> list = storage.get(data.getType());
            list.add(data);
            storage.put(data.getType(), list);
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }
}
