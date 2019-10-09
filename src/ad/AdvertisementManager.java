package ad;

import restaurant.ConsoleHelper;
import statistic.StatisticManager;
import statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().isEmpty()) throw new NoVideoAvailableException();
        List<Advertisement> videos = new ArrayList<>();
        for (Object vid : storage.list()) {
            Advertisement ad = (Advertisement) vid;
            if (ad.getDuration() <= timeSeconds && ad.getHits() > 0) videos.add(ad);
        }

        videos.sort(Comparator.comparingLong(Advertisement::getAmountPerOneDisplaying)
                .thenComparingInt(Advertisement::getDuration).reversed());

        int remainingTime = timeSeconds;
        List<Advertisement> videoSet = new ArrayList<>();
        for (Advertisement ad : videos) {
            if (ad.getDuration() <= remainingTime && ad.getAmountPerOneDisplaying() > 0) {
                videoSet.add(ad);
                ad.revalidate();
                remainingTime -= ad.getDuration();
            }
        }

        StatisticManager.getInstance().register
                (new VideoSelectedEventDataRow(
                        videoSet,
                        videoSet.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum(),
                        videoSet.stream().mapToInt(Advertisement::getDuration).sum()));

        videoSet.stream().map(Advertisement::toString).forEach(ConsoleHelper::writeMessage);
    }
}
