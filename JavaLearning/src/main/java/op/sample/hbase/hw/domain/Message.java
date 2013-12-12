package op.sample.hbase.hw.domain;

public class Message {
	private String messageID;
	private String timelineID;
	private String srcUserID;
	private String destUserID;
	private String content;
	private int likeCount;
	private int sharedCount;
	
	public Message() {
		super();
	}
	
	public Message setMessageID(String messageID) {
		this.messageID = messageID;
		return this;
	}
	
	public String getMessageID() {
		return this.messageID;
	}
	
	public Message setTimelineID(String timelineID) {
		this.timelineID = timelineID;
		return this;
	}
	
	public String getTimelineID() {
		return this.timelineID;
	}
	
	public Message setSrcUserID(String srcUserID) {
		this.srcUserID = srcUserID;
		return this;
	}
	
	public String getSrcUserID() {
		return this.srcUserID;
	}
	
	public Message setDestUserID(String destUserID) {
		this.destUserID = destUserID;
		return this;
	}
	
	public String getDestUserID() {
		return this.destUserID;
	}
	
	public Message setContent(String content) {
		this.content = content;
		return this;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public Message setLikeCount(int likeCount) {
		this.likeCount = likeCount;
		return this;
	}
	
	public int getLikeCount() {
		return this.likeCount;
	}
	
	public Message setSharedCount(int sharedCount) {
		this.sharedCount = sharedCount;
		return this;
	}
	
	public int getSharedCount() {
		return this.sharedCount;
	}
}
