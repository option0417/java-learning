package tw.com.wd.scheduler.scheduler;


import tw.com.wd.scheduler.job.SchedulerJob;
import tw.com.wd.scheduler.obj.SchedulerOperator;
import tw.com.wd.scheduler.obj.SchedulerParam;
import tw.com.wd.scheduler.utils.SchedulerBroadcaster;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RemoteJobController extends AbstractJobController implements JobController {

    public RemoteJobController() {
        super();
    }


    @Override
    public JobMonitor start(SchedulerJob schedulerJob) {
        String schedulerJobID = schedulerJob.getJobID();
        return null;
    }

    @Override
    public JobMonitor stop(String schedulerJobID) {
        URL jobLocationURL = findJobLocationURL(schedulerJobID);
        String schedulerCommand = buildSchedulerCommand(schedulerJobID, SchedulerOperator.Stop);
        LOG.debug("\tSchedulerCommand: {}", schedulerCommand);
        return sendToRemoteScheduler(jobLocationURL, schedulerCommand);
    }



    private URL findJobLocationURL(String jobID) {
        return null;
    }

    private JobMonitor sendToRemoteScheduler(URL jobLocationURL, String schedulerCommand) {
        String resp = SchedulerBroadcaster.getInstance().broadcast(jobLocationURL, schedulerCommand);
        LOG.info("Remote Response: {}", resp);

        return JobMonitor.getEmptyMonitor(JobMonitor.MonitorStatus.OK);
    }

    private String buildSchedulerCommand(String jobID, SchedulerOperator schedulerOperator) {
        SchedulerParam schedulerParam =
                new SchedulerParam()
                        .setJobID(jobID)
                        .setOperator(schedulerOperator.ordinal());

        Map<String, Object> valMap = new HashMap<>();

        //valMap.put(SysManager.FIELD_SERVICE_TYPE, ServiceType.Scheduler.name());
        //valMap.put(SysManager.FIELD_SERVICE_PARAMETER, schedulerParam.toJSON());

        //return JSONUtils.toJSON(valMap);
        return "";
    }
}
