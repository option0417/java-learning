package tw.com.wd.scheduler.scheduler;

import tw.com.wd.scheduler.job.SchedulerJob;

public interface JobController {
    /**
     * Start job into scheduler
     * @param schedulerJob
     * @return JobMonitor
     */
    public JobMonitor start(SchedulerJob schedulerJob);

    /**
     * Stop job in the scheduler
     * @param schedulerJobID
     * @return JobMonitor
     */
    public JobMonitor stop(String schedulerJobID);
}
