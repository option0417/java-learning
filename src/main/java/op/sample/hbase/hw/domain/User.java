package op.sample.hbase.hw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "u", schema = "User@hbase_pu")
public class User {
	@Id
	private String userID;
	
	@Column(name="timeline_id")
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
