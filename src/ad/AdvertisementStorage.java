package ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage instance = new AdvertisementStorage();
    private final List<Object> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement(someContent, "First video", 5000, 100, 3 * 60));
        add(new Advertisement(someContent, "Second video", 100, 10, 15 * 60));
        add(new Advertisement(someContent, "Third video", 400, 2, 10 * 60));
    }

    public static AdvertisementStorage getInstance() {
        return instance;
    }

    public List list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
