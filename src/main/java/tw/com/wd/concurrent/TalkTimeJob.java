package tw.com.wd.concurrent;

public class TalkTimeJob extends AbstractSchedulerJob implements SchedulerJob {
    @Override
    public void doSimpleJob() {
        long time = System.currentTimeMillis();
        System.out.printf("I'm %s\n", Thread.currentThread().toString());
        System.out.printf("The current time: %d\n", time);
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
