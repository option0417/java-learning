package tw.com.wd.scheduler.obj;

public enum JobStatus {
    Initial, Scheduler, Running, Stop, Done, Delete;

    public static JobStatus build(int code) {
        for (JobStatus jobStatus : JobStatus.values()) {
            if (jobStatus.ordinal() == code) {
                return jobStatus;
            }
        }
        return null;
    }
}
