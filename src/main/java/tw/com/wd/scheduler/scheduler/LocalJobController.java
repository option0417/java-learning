package tw.com.wd.scheduler.scheduler;


import tw.com.wd.scheduler.job.DurationSchedulerJob;
import tw.com.wd.scheduler.job.OneTimeSchedulerJob;
import tw.com.wd.scheduler.job.SchedulerJob;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LocalJobController extends AbstractJobController implements JobController {
    private ScheduledThreadPoolExecutor scheduleJobExecutor;

    public LocalJobController() {
        this.scheduleJobExecutor = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 5, new SchedulerThreadFactory());
    }

    @Override
    public JobMonitor start(SchedulerJob schedulerJob) {
        ScheduledFuture scheduledFuture = null;
        switch(schedulerJob.getJobType()) {
            case ONE_TIME:
                OneTimeSchedulerJob oneTimeSchedulerJob = (OneTimeSchedulerJob)schedulerJob;
                scheduledFuture = scheduleJobExecutor.schedule(
                        oneTimeSchedulerJob,
                        oneTimeSchedulerJob.getInitialDelay(),
                        TimeUnit.MILLISECONDS);
                schedulerJob.putInScheduler();
                break;
            case DURATION:
                DurationSchedulerJob durationSchedulerJob = (DurationSchedulerJob)schedulerJob;
                scheduledFuture = scheduleJobExecutor.scheduleAtFixedRate(
                        durationSchedulerJob,
                        durationSchedulerJob.getInitialDelay(),
                        durationSchedulerJob.getJobPeriod(),
                        TimeUnit.MILLISECONDS);
                schedulerJob.putInScheduler();
                break;
        }

        JobMonitor jobMonitor = buildJobMonitor(schedulerJob, scheduledFuture);
        JobMonitorHolder.putJobMonitor(schedulerJob.getJobID(), jobMonitor);
        return jobMonitor;
    }

    private JobMonitor buildJobMonitor(SchedulerJob schedulerJob, ScheduledFuture scheduledFuture) {
        return new JobMonitor(schedulerJob, scheduledFuture);
    }

    public JobMonitor stop(String schedulerJobID) {
        if (isLocalScheduledJob(schedulerJobID)) {
            JobMonitor jobMonitor = JobMonitorHolder.getJobMonitor(schedulerJobID);
            jobMonitor.stopJob();
            return jobMonitor;
        } else {
            return JobMonitor.getEmptyMonitor(JobMonitor.MonitorStatus.NOTFOUND);
        }
    }

    private boolean isLocalScheduledJob(String jobID) {
        return JobMonitorHolder.getJobMonitor(jobID) != null;
    }

    private static final class SchedulerThreadFactory implements ThreadFactory {
        private static final String WORKER_NAME_PREFIX = "Scheduler-";
        private final AtomicInteger threadNumber       = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName(WORKER_NAME_PREFIX + threadNumber.getAndIncrement());
            return t;
        }
    }
}
