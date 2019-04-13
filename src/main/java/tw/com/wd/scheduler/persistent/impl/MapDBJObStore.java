package tw.com.wd.scheduler.persistent.impl;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import tw.com.wd.scheduler.job.SchedulerJob;
import tw.com.wd.scheduler.obj.ScheduleException;
import tw.com.wd.scheduler.persistent.JobStore;

import java.util.Collection;
import java.util.List;

public class MapDBJObStore implements JobStore {
	private DB mapDB;
	public MapDBJObStore() {
		mapDB = DBMaker.newMemoryDirectDB().make();
	}

	@Override
	public void backupSchedulerJob(SchedulerJob schedulerJob) throws ScheduleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backupSchedulerJob(Collection<SchedulerJob> schedulerJobList) throws ScheduleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SchedulerJob> getSchedulerJobs() throws ScheduleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SchedulerJob getSchedulerJob(String jobID) throws ScheduleException {
		// TODO Auto-generated method stub
		return null;
	}

}
