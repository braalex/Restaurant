package restaurant;

import java.util.List;

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
            int num = (int) (Math.random() * tablets.size());
            Tablet t = tablets.get(num);
            t.createTestOrder();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException ignored) {
                return;
            }
        }
    }
}
