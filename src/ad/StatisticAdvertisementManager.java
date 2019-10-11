package ad;

import java.util.*;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {}

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    public Map<String, Integer> getActiveVideos() {
        Map<String, Integer> activeVideos = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Object o : advertisementStorage.list()) {
            Advertisement ad = (Advertisement) o;
            int hits = ad.getHits();
            if (hits > 0) activeVideos.put(ad.getName(), hits);
        }
        return activeVideos;
    }

    public Set<String> getArchivedVideos() {
        Set<String> archivedVideos = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (Object o : advertisementStorage.list()) {
            Advertisement ad = (Advertisement) o;
            int hits = ad.getHits();
            if (hits == 0) archivedVideos.add(ad.getName());
        }
        return archivedVideos;
    }
}
