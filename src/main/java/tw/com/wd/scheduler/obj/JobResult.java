package tw.com.wd.scheduler.obj;

public class JobResult {
    private static final JobResult EMPTY_JOB_RESULT = new JobResult("");
    private String result;

    public JobResult(String result) {
        super();
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public static JobResult getEmptyJobResult() {
        return EMPTY_JOB_RESULT;
    }
}
