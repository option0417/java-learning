package op.sample.hibernate;

import java.util.Date;

public class TestVO {
	private Integer tbid;
	private String testchar;
	private Date applytime;
	private Date replytime;
	private String testchar2;
	
	
	public TestVO() {
		super();
	}
	
	public TestVO(Integer tbid, String testchar, Date applytime, Date replytime, String testchar2) {
		
	}
	
	public TestVO setTbid(Integer tbid) {
		this.tbid = tbid;
		return this;
	}
	
	public Integer getTbid() {
		return tbid;
	}
	
	public TestVO setTestchar(String testchar) {
		this.testchar = testchar;
		return this;
	}
	
	public String getTestchar() {
		return testchar;
	}
	
	public TestVO setApplytime(Date applytime) {
		this.applytime = applytime;
		return this;
	}
	
	public Date getApplytime() {
		return applytime;
	}
	
	public TestVO setReplytime(Date replytime) {
		this.replytime = replytime;
		return this;
	}
	
	public Date getReplytime() {
		return replytime;
	}
	
	public TestVO setTestchar2(String testchar2) {
		this.testchar2 = testchar2;
		return this;
	}
	
	public String getTestchar2() {
		return testchar2;
	}
}
