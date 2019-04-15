package tw.com.wd.scheduler.scheduler;

import org.slf4j.Logger;
import tw.com.wd.scheduler.utils.ScheduleLogger;

public abstract class AbstractJobController implements JobController {
    protected static final Logger LOG = ScheduleLogger.getLogger();
    //protected static final SchedulerJobDao SCHEDULER_JOB_DAO = new SchedulerJobDao();
}
