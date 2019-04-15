package tw.com.wd.scheduler.scheduler;


import org.slf4j.Logger;
import tw.com.wd.scheduler.job.SchedulerJob;
import tw.com.wd.scheduler.persistent.JobStore;
import tw.com.wd.scheduler.persistent.impl.HBaseJobStore;
import tw.com.wd.scheduler.utils.ScheduleLogger;

import java.util.List;

public class ScheduleManager implements SchedulerOperation {
    private Logger          log;                    // Logger for log
    private JobController   localJobController;     // Control local jobs
    private JobController   remoteJobController;    // Control remote jobs
    private JobStore        hbaseJobStore;          // Job store


    private static class InstanceHolder {
        private static final ScheduleManager INSTANCE = new ScheduleManager();
    }

    public static final ScheduleManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private ScheduleManager() {
        super();
        this.log                    = ScheduleLogger.getLogger();
        this.localJobController     = new LocalJobController();
        this.remoteJobController    = new RemoteJobController();
        this.hbaseJobStore          = new HBaseJobStore();

        Runtime.getRuntime().addShutdownHook(new SchedulerShutdownHook(this.hbaseJobStore));
    }

    public JobMonitor startJob(SchedulerJob schedulerJob) {
        this.log.info("Setup Job: {}", schedulerJob.getJobName());
        JobMonitor jobMonitor = localJobController.start(schedulerJob);

        this.log.info("Setup done");
        this.log.info("\tJob ID: {}", jobMonitor.getJobID());
        this.log.info("\tJob in schedule time: {}", jobMonitor.getStartTimestamp());
        this.log.info("\tJob next invoke time: {}", jobMonitor.getJobNextInvokeTime());
        this.log.info("\tJob period time: {}", jobMonitor.getJobPeriod());
        return jobMonitor;
    }

    @Override
    public JobMonitor stopJob(String jobID) {
        return stopJob(jobID, false);
    }

    @Override
    public JobMonitor stopJob(String jobID, boolean isForce) {
        JobMonitor jobMonitor = localJobController.stop(jobID);
        switch (jobMonitor.getMonitorStatus()) {
            case OK:
            case FAIL:
                return jobMonitor;
            case NOTFOUND:
                return remoteJobController.stop(jobID);
            default:
                return JobMonitor.getEmptyMonitor(JobMonitor.MonitorStatus.UNDEFINE);
        }
    }

    @Override
    public JobMonitor deleteJob(String jobID) {
        return stopJob(jobID);
    }

    @Override
    public JobMonitor reStartJob(String jobID) {
        throw new UnsupportedOperationException("Unsupported reStartJob yet");
    }

    public void restoreSchedulerJob() {
        List<SchedulerJob> schedulerJobList = hbaseJobStore.getSchedulerJobs();

        for (SchedulerJob schedulerJob : schedulerJobList) {
            this.startJob(schedulerJob);
        }
    }
}
