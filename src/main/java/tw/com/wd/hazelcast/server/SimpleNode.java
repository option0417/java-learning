package tw.com.wd.hazelcast.server;

import com.hazelcast.core.HazelcastInstance;

public interface SimpleNode {
	/** Start SimpleNode */
	public void start();
	
	/**
	 * Get instance of Hazelcast node.
	 * @return
	 */
	public HazelcastInstance getInstance();
}
