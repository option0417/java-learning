/*
 * 版權宣告: FDC all rights reserved.
 */
package op.sample.batch;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：SampleJob.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author 1010481
 *@version 1.0
 *@since 1.0
 */
@Service(value = "dataSyncHandler")
public class SampleJob extends QuartzJobBean {
    
    @Autowired
    private JobDetail jobDetail;
    
    public void executeInternal(JobExecutionContext context) {
        System.out.println(
                jobDetail.getDetail() + " is executed.");
    }
    
    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }
    
    public JobDetail getJobDetail() {
        return jobDetail;
    }
}
