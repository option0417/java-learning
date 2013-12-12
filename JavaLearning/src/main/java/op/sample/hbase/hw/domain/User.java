package op.sample.hbase.hw.domain;

public class User {
	private String userID;
	private String timelineID;
	
	
	public User() {
		super();
	}
	
	public User(String userID, String timelineID) {
		setUserID(userID);
		setTimelineID(timelineID);
	}
	
	public User setUserID(String userID) { 
		this.userID = userID;
		return this;
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public User setTimelineID(String timelineID) {
		this.timelineID = timelineID;
		return this;
	}
	
	public String getTimelineID() {
		return this.timelineID;
	}
}
