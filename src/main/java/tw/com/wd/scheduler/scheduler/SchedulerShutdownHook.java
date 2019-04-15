package tw.com.wd.scheduler.scheduler;


import tw.com.wd.scheduler.job.SchedulerJob;
import tw.com.wd.scheduler.obj.JobStatus;
import tw.com.wd.scheduler.persistent.JobStore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SchedulerShutdownHook extends Thread {
    private JobStore jobStore;

    public SchedulerShutdownHook(JobStore jobStore) {
        super();
        this.jobStore = jobStore;
    }

    @Override
    public void run() {
        Collection<JobMonitor> jobMonitorCollection = JobMonitorHolder.getAllJobMonitor();
        List<SchedulerJob> schedulerJobList         = new ArrayList<SchedulerJob>(jobMonitorCollection.size());

        Iterator<JobMonitor> jobMonitorIter = jobMonitorCollection.iterator();
        while (jobMonitorIter.hasNext()) {
            JobMonitor jobMonitor   = jobMonitorIter.next();
            JobStatus jobStatus     = jobMonitor.getSchedulerJob().getJobStatus();

            if (JobStatus.Scheduler == jobStatus || JobStatus.Running == jobStatus) {
                jobMonitor.stopJob();
                schedulerJobList.add(jobMonitor.getSchedulerJob());
            }
        }

        this.jobStore.backupSchedulerJob(schedulerJobList);
    }
}
