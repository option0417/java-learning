package tw.com.wd.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class SchedulerService {
    private static final ScheduledThreadPoolExecutor POOL = new ScheduledThreadPoolExecutor(1);

    public static void sendJob(SchedulerJob job) {
        POOL.scheduleWithFixedDelay(job, 1L, 1L, TimeUnit.SECONDS);
    }
}
