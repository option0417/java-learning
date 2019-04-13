package tw.com.wd.scheduler.obj;


import java.util.HashMap;
import java.util.Map;

public class SchedulerParam {
    private String jobID;
    private int operator;

    public String getJobID() {
        return jobID;
    }

    public SchedulerParam setJobID(String jobID) {
        this.jobID = jobID;
        return this;
    }

    public int getOperator() {
        return operator;
    }

    public SchedulerParam setOperator(int operator) {
        this.operator = operator;
        return this;
    }

    public String toJSON() {
        Map<String, Object> valMap = new HashMap<>();

        valMap.put("j_id", this.jobID);
        valMap.put("j_op", this.operator);

        //return JSONUtils.toJSON(valMap);
        return "";
    }

    public <T> T fromJSON(String jsonText) {
        return null;
    }
}
