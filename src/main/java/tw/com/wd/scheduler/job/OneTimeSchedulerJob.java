package tw.com.wd.scheduler.job;

import tw.com.wd.scheduler.obj.JobType;

public abstract class OneTimeSchedulerJob extends SchedulerJob {
    public OneTimeSchedulerJob() {
        super();
    }

    public OneTimeSchedulerJob(String json) {
        super(json);
    }

    public <JOB extends SchedulerJob> JOB setInitialDelay(long initialDelay) {
        this.initialDelay = initialDelay;
        return (JOB) this;
    }

    @Override
    public JobType getJobType() {
        return JobType.ONE_TIME;
    }
}
