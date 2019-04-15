package tw.com.wd.scheduler.scheduler;


import tw.com.wd.scheduler.job.DurationSchedulerJob;
import tw.com.wd.scheduler.job.SchedulerJob;
import tw.com.wd.scheduler.obj.JobStatus;
import tw.com.wd.scheduler.obj.JobType;
import tw.com.wd.scheduler.obj.ScheduleException;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class JobMonitor {
    private static final JobMonitor EmptyJobMonitor = new JobMonitor();
    private Future<?> jobFuture;        // Future object of the Job
    private SchedulerJob schedulerJob;  // SchedulerJob
    private long startTimestamp;
    private long endTimestamp;
    private long duration;
    private boolean isEmpty;
    private MonitorStatus monitorStatus;


    public enum MonitorStatus {
        UNDEFINE,   // Undefine
        OK,         // Job operation ok
        FAIL,       // Job operation fail
        NOTFOUND    // Job not found
    }

    private JobMonitor() {
        super();
        this.startTimestamp = 0L;
        this.endTimestamp   = 0L;
        this.duration       = 0L;
        this.isEmpty        = true;
        this.monitorStatus  = MonitorStatus.UNDEFINE;
    }

    public JobMonitor(SchedulerJob schedulerJob, ScheduledFuture<?> scheduledFuture) {
        super();
        this.jobFuture      = scheduledFuture;
        this.schedulerJob   = schedulerJob;
        this.startTimestamp = System.currentTimeMillis();
        this.endTimestamp   = 0L;
        this.duration       = 0L;
        this.isEmpty        = false;
        this.monitorStatus  = MonitorStatus.UNDEFINE;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public JobMonitor setEndTimestamp(long endTimestamp) {
        this.endTimestamp   = endTimestamp;
        this.duration       = this.endTimestamp - this.startTimestamp;

        return this;
    }

    public boolean isJobDone() {
        return this.jobFuture == null || this.jobFuture.isDone();
    }

    public boolean isJobSuccess() {
        return this.schedulerJob.isSuccess();
    }

    public long getJobDuration() {
        return this.duration;
    }

    public long getJobDuration(TimeUnit timeUnit) {
        if (TimeUnit.MILLISECONDS == timeUnit) {
            return this.duration;
        } else if (TimeUnit.SECONDS == timeUnit) {
            return TimeUnit.MILLISECONDS.toSeconds(this.duration);
        } else if (TimeUnit.MINUTES == timeUnit) {
            return TimeUnit.MILLISECONDS.toMinutes(this.duration);
        } else if (TimeUnit.HOURS == timeUnit) {
            return TimeUnit.MILLISECONDS.toHours(this.duration);
        } else if (TimeUnit.NANOSECONDS == timeUnit) {
            return TimeUnit.MILLISECONDS.toNanos(this.duration);
        } else if (TimeUnit.DAYS == timeUnit) {
            return TimeUnit.MILLISECONDS.toDays(this.duration);
        }

        if (timeUnit == null) {
            throw new ScheduleException("TimeUnit is null");
        } else {
            throw new ScheduleException("Unsupported TimeUnit: " + timeUnit.name());
        }
    }

    public String getJobID() {
        return schedulerJob.getJobID();
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    protected Future getScheduledFuture() {
        return this.jobFuture;
    }

    public static JobMonitor getEmptyMonitor(MonitorStatus monitorStatus) {
        return EmptyJobMonitor.setMonitorStatus(monitorStatus);
    }

    public void stopJob() {
        this.jobFuture.cancel(true);
        this.schedulerJob.shutdown();
    }

    public long getJobNextInvokeTime() {
        long nextInvokeTimestamp = this.startTimestamp + this.schedulerJob.getInitialDelay();
        return nextInvokeTimestamp;
    }

    public long getJobPeriod() {
        if (JobType.DURATION == this.schedulerJob.getJobType()) {
            return ((DurationSchedulerJob)schedulerJob).getJobPeriod();
        } else {
            return 0L;
        }
    }

    private JobMonitor setMonitorStatus(MonitorStatus monitorStatus) {
        this.monitorStatus = monitorStatus;
        return this;
    }

    public JobStatus getJobStatus() {
        return this.schedulerJob.getJobStatus();
    }

    public MonitorStatus getMonitorStatus() {
        return monitorStatus;
    }

    public SchedulerJob getSchedulerJob() {
        return schedulerJob;
    }
}