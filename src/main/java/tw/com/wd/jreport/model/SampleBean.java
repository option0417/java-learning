/*
 * 版權宣告: FDC all rights reserved.
 */
package tw.com.wd.jreport.model;

import java.sql.Date;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：SampleBean.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author 1010481
 *@version 1.0
 *@since 1.0
 */
public class SampleBean {
    private String beanID;
    private Date beanDate;
    private String beanValue;
    
    public SampleBean() { }
    
    public void setBeanID(String beanID) {
        this.beanID = beanID;
    }
    
    public void setBeanDate(Date beanDate) {
        this.beanDate = beanDate;
    }
    
    public void setBeanValue(String beanValue) {
        this.beanValue = beanValue;
    }
    
    public String getBeanID() {
        return this.beanID;
    }
    
    public Date getBeanDate() {
        return this.beanDate;
    }
    
    public String getBeanValue() {
        return this.beanValue;
    }
}
