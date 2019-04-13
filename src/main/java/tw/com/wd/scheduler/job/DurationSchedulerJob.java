package tw.com.wd.scheduler.job;

import tw.com.wd.scheduler.obj.JobStatus;
import tw.com.wd.scheduler.obj.JobType;

public abstract class DurationSchedulerJob extends SchedulerJob {
    protected static final long ONE_HOUR = 1L * 60L * 60L * 1000L;


    public DurationSchedulerJob() {
        super();
    }

    public DurationSchedulerJob(String json) {
        super(json);
    }

    public long getJobPeriod() {
        return jobPeriod;
    }

    public <JOB extends DurationSchedulerJob> JOB setJobPeriod(long jobPeriod) {
        this.jobPeriod = jobPeriod;
        return (JOB) this;
    }

    @Override
    protected void postStage() {
        this.log.info("Job done.");
        try {
            changeJobStatus(JobStatus.Scheduler);
        } catch (Throwable t) {
            log.error("Caught exception at post-stage: {}", t.getMessage());
            throw t;
        }
        return;
    }

    @Override
    public JobType getJobType() {
        return JobType.DURATION;
    }
}
