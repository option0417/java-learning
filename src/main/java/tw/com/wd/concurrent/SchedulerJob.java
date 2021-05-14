package tw.com.wd.concurrent;

public interface SchedulerJob extends Runnable {
    public void doSimpleJob();
}
