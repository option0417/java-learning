package tw.com.wd.jni;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class DataObj {
	@SerializedName("ui")
	private String userID;
	
	@SerializedName("at")
	private String accessToken;
	
	@SerializedName("un")
	private String userName;
	
	@SerializedName("pn")
	private String phone;
	
	@SerializedName("gi")
	private String groupID;
	
	@SerializedName("gn")
	private String groupName;
	
	@SerializedName("ua")
	private String userAvatar;
	
	@SerializedName("ga")
	private String groupAvatar;
	
	@SerializedName("ct")
	private long createTime;
	
	@SerializedName("ut")
	private long updateTime;

	
	public DataObj() {
		super();
	}
	
	
	public DataObj setUserID(String userID) {
		this.userID = userID;
		return this;
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public String getAccessToken() {
		return accessToken;
	}


	public DataObj setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}


	public String getUserName() {
		return userName;
	}


	public DataObj setUserName(String userName) {
		this.userName = userName;
		return this;
	}


	public String getPhone() {
		return phone;
	}


	public DataObj setPhone(String phone) {
		this.phone = phone;
		return this;
	}


	public String getGroupID() {
		return groupID;
	}


	public DataObj setGroupID(String groupID) {
		this.groupID = groupID;
		return this;
	}


	public String getGroupName() {
		return groupName;
	}


	public DataObj setGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}


	public String getUserAvatar() {
		return userAvatar;
	}


	public DataObj setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
		return this;
	}


	public String getGroupAvatar() {
		return groupAvatar;
	}


	public DataObj setGroupAvatar(String groupAvatar) {
		this.groupAvatar = groupAvatar;
		return this;
	}


	public long getCreateTime() {
		return createTime;
	}


	public DataObj setCreateTime(long createTime) {
		this.createTime = createTime;
		return this;
	}


	public long getUpdateTime() {
		return updateTime;
	}


	public DataObj setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
