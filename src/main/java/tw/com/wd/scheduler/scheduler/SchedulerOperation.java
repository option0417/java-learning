package tw.com.wd.scheduler.scheduler;

import tw.com.wd.scheduler.job.SchedulerJob;

public interface SchedulerOperation {
    /**
     * Start job with scheduler
     * @param schedulerJob
     * @return
     */
    public JobMonitor startJob(SchedulerJob schedulerJob);

    /**
     * Stop job by job id
     * @param jobID
     * @return
     */
    public JobMonitor stopJob(String jobID);

    /**
     * Stop job by job id with boolean flag to indicate force stop or not.
     * @param jobID
     * @param isForce
     * @return
     */
    public JobMonitor stopJob(String jobID, boolean isForce);

    /**
     * Delete job by job id
     * @param jobID
     * @return
     */
    public JobMonitor deleteJob(String jobID);

    /**
     * Re-start job by job id
     * @param jobID
     * @return
     */
    public JobMonitor reStartJob(String jobID);
}
