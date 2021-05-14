package tw.com.wd.concurrent;

public abstract class AbstractSchedulerJob implements SchedulerJob {
    @Override
    public void run() {
        this.doSimpleJob();
    }
}
