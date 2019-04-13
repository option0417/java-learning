package tw.com.wd.scheduler.job;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.wd.scheduler.obj.JobResult;
import tw.com.wd.scheduler.obj.JobStatus;
import tw.com.wd.scheduler.obj.JobType;
import tw.com.wd.scheduler.utils.SchedulerConstants;

import java.util.HashMap;
import java.util.Map;

public abstract class SchedulerJob implements Runnable {
	private static final Gson GSON_INSTANCE 	= new Gson(); // Instance of Gson
    private static final String JOB_ID_FORMAT 	= "%010d"; // Format for Job Id
    protected Logger                log;                // Just a logger
    @SerializedName("id")
    protected String                jobID;              // Job identify
    @SerializedName("name")
    protected String                jobName;            // Job name
    @SerializedName("st")
    protected JobStatus             jobStatus;          // Job status
    @SerializedName("delay")
    protected long                  initialDelay;       // Delay that until next invoked
    @SerializedName("period")
    protected long                  jobPeriod;          // Period time that next time for execution
    @SerializedName("it")
    protected long                  invokeTimestamp; 	// The timestamp that the job will executed.
    protected transient boolean               isSuccess; 	// Flag for indicate that job success or not.
    protected transient JobResult             jobResult; 	// Result of this Job
    protected transient Map<String, String>   paramMap;  	// Job parameters
    
    public SchedulerJob() {
        super();
        this.log            = LoggerFactory.getLogger("Job-" + this.getClass().getSimpleName());
        this.jobID          = createJobID();
        this.jobName        = this.getClass().getSimpleName();
        this.jobStatus      = JobStatus.Initial;
        this.initialDelay   = 1000L;
        this.jobPeriod      = -1L;
        this.isSuccess      = false;
        this.paramMap       = new HashMap<>();

        // Write initial job status
        //initial();
    }

    public SchedulerJob(String json) {
        super();
        this.log            = LoggerFactory.getLogger("Job-" + this.getClass().getSimpleName());
        this.isSuccess      = false;
        this.paramMap       = new HashMap<>();
        this.fromJSON(json);
    }

    public boolean isRecoverable() {
        return false;
    }

    public String getJobID() {
        return jobID;
    }

    public String getJobName() {
        return jobName;
    }

    public JobStatus getJobStatus() {
        return this.jobStatus;
    }

    public long getInitialDelay() {
        return initialDelay;
    }
    
    @SuppressWarnings("unchecked")
	public <JOB extends SchedulerJob> JOB setInitialDelay(long initialDelay) {
    	this.initialDelay = initialDelay;
    	return (JOB) this;
    }

    public long getInvokeTimestamp() {
        return invokeTimestamp;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    @SuppressWarnings("unchecked")
	public <JOB extends SchedulerJob> JOB putParameter(String key, String param) {
        this.paramMap.put(key, param);
        return (JOB) this;
    }

    @SuppressWarnings("unchecked")
	public <PARAM> PARAM getParameter(String key) {
        return (PARAM) this.paramMap.get(key);
    }

    @Override
    public void run () {
        try {
            preStage();
            doJob();
            postStage();
            this.jobResult = buildResult();
        } catch (Throwable t) {
            this.handleException(t);
            this.jobResult = new JobResult(t.getMessage());
        }
    }

    protected void preStage() {
        this.log.info("Job Setup.");
        try {
            changeJobStatus(JobStatus.Running);
        } catch (Throwable t) {
            log.error("Caught exception at pre-stage: {}", t.getMessage());
            throw t;
        }
    }

    protected void postStage() {
        this.log.info("Job done.");
        try {
            isSuccess = true;
            if (JobType.ONE_TIME == getJobType()) {
                changeJobStatus(JobStatus.Done);
            } else {
                changeJobStatus(JobStatus.Scheduler);
            }
        } catch (Throwable t) {
            log.error("Caught exception at post-stage: {}", t.getMessage());
            throw t;
        }
    }

    public JobResult buildResult() {
        // As default implementation
        return JobResult.getEmptyJobResult();
    }

    protected void handleException(Throwable t) {
        log.error(SchedulerConstants.EXCEPTION_PREFIX, t);
        this.paramMap.put("err", t.getMessage());
        this.isSuccess = false;
        changeJobStatus(JobStatus.Stop);
    }

    public void shutdown() {
        this.isSuccess = false;
        changeJobStatus(JobStatus.Stop);
    }

    // Notify this job in scheduler already
    public void putInScheduler() {
        changeJobStatus(JobStatus.Scheduler);
    }

    private String createJobID() {
        long serialNumber = incrementAndGetSerialNumber();
        return String.format(JOB_ID_FORMAT, serialNumber);
    }

    private long incrementAndGetSerialNumber() {
    	return 0;
    }

    private String fetchSchedulerLocation() {
        return null;
    }

    protected void changeJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String toJSON() {
        return GSON_INSTANCE.toJson(this);
    }

    public <T extends SchedulerJob> T fromJSON(String json) {
        return (T) GSON_INSTANCE.fromJson(json, this.getClass());
    }

    public abstract JobType getJobType();
    protected abstract void doJob() throws Throwable ;
}
