package tw.com.wd.scheduler.scheduler;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JobMonitorHolder {
    private static final Map<String, JobMonitor> JOB_MONITOR_MAP = new ConcurrentHashMap<String, JobMonitor>();

    public static void putJobMonitor(String jobID, JobMonitor jobMonitor) {
        JOB_MONITOR_MAP.put(jobID, jobMonitor);
    }

    public static JobMonitor getJobMonitor(String jobID) {
        return JOB_MONITOR_MAP.get(jobID);
    }

    public static Collection<JobMonitor> getAllJobMonitor() {
        return JOB_MONITOR_MAP.values();
    }
}
