package tw.com.wd.scheduler.persistent.impl;

import org.slf4j.Logger;
import tw.com.wd.scheduler.job.SchedulerJob;
import tw.com.wd.scheduler.persistent.JobStore;
import tw.com.wd.scheduler.utils.ScheduleLogger;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HBaseJobStore implements JobStore {
    private static final Logger LOG = ScheduleLogger.getLogger();
    //private SchedulerJobDao schedulerJobDao;

    public HBaseJobStore() {
        super();
        //schedulerJobDao = new SchedulerJobDao();
    }

    @Override
    public void backupSchedulerJob(SchedulerJob schedulerJob) {
    	List<SchedulerJob> schedulerJobList = new ArrayList<SchedulerJob>(1);
    	backupSchedulerJob(schedulerJobList);
    }

    public void backupSchedulerJob(Collection<SchedulerJob> schedulerJobCollection) {
    }

    @Override
    public List<SchedulerJob> getSchedulerJobs() {
        try {
            //List<SchedulerJob3> schedulerJob3List = fetchSchedulerJob3();
            //return convertToSchedulerJob(schedulerJob3List);
        	return null;
        } catch (Throwable t) {
            LOG.error("Exception: {}", t);
            return null;
        }
    }


    private SchedulerJob buildSchedulerJob(String clazzString, String jobContent) throws Exception {
        Class clazz                 = Class.forName(clazzString);
        Constructor constructor     = clazz.getConstructor(String.class);
        SchedulerJob schedulerJob   = (SchedulerJob) constructor.newInstance(jobContent);
        return schedulerJob;
    }

    @Override
    public SchedulerJob getSchedulerJob(String jobID) {
    	return null;
    }

    private SchedulerJob convertToSchedulerJob() {
        try {
            //String clazzString = schedulerJob3.getJobClass();
            //String jobContent = schedulerJob3.getJobContent();
            return buildSchedulerJob("clazzString", "jobContent");
        } catch (Exception e) {
            LOG.error("Exception: {}", e);
            return null;
        }
    }
}
