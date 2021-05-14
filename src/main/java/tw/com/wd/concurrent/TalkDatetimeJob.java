package tw.com.wd.concurrent;

import java.time.LocalDate;


public class TalkDatetimeJob extends AbstractSchedulerJob implements SchedulerJob {
    @Override
    public void doSimpleJob() {
        String localDate = LocalDate.now().toString();
        System.out.printf("I'm %s\n", Thread.currentThread().toString());
        System.out.printf("LocalDate: %s\n", localDate);
    }
}
