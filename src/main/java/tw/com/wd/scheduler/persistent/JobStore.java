package tw.com.wd.scheduler.persistent;


import tw.com.wd.scheduler.job.SchedulerJob;
import tw.com.wd.scheduler.obj.ScheduleException;

import java.util.Collection;
import java.util.List;

public interface JobStore {
    public void backupSchedulerJob(SchedulerJob schedulerJob) throws ScheduleException;

    public void backupSchedulerJob(Collection<SchedulerJob> schedulerJobList) throws ScheduleException;

    public List<SchedulerJob> getSchedulerJobs() throws ScheduleException;

    public SchedulerJob getSchedulerJob(String jobID) throws ScheduleException;
}
