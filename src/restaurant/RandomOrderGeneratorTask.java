package restaurant;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            int num = ThreadLocalRandom.current().nextInt(tablets.size());
            Tablet tablet = tablets.get(num);
            tablet.createTestOrder();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException ignored) {
                return;
            }
        }
    }
}
