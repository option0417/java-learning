package op.sample.hbase.hw.service;

import java.util.List;

import op.sample.hbase.hw.domain.Message;

public interface MessageCenter {
	/**
	 * List messages on timeline.
	 * @param userID
	 * @return
	 */
	public List<Message> listTimeline(String userID);
	
	/**
	 * Post a message to specific timeline. 
	 * @param message
	 * @return
	 */
	public boolean postMessage(Message message);
}
