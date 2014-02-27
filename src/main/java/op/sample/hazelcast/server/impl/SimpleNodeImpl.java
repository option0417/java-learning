package op.sample.hazelcast.server.impl;

import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import op.sample.hazelcast.server.SimpleNode;

public class SimpleNodeImpl implements SimpleNode {
	private Logger LOG = LoggerFactory.getLogger(getClass());
	private HazelcastInstance instance;
	
	
	public SimpleNodeImpl() {
		Config cfg = new Config();
		instance = Hazelcast.newHazelcastInstance(cfg);
	}

	public void start() {
		Map<Integer, String> mapCustomers = instance.getMap("customers");
		mapCustomers.put(1, "Joe");
		mapCustomers.put(2, "Ali");
		mapCustomers.put(3, "Avi");
		LOG.debug("Customer with key 1: "+ mapCustomers.get(1));
		LOG.debug("Map Size:" + mapCustomers.size());
		Queue<String> queueCustomers = instance.getQueue("customers");
		queueCustomers.offer("Tom");
		queueCustomers.offer("Mary");
		queueCustomers.offer("Jane");
		LOG.debug("First customer: " + queueCustomers.poll());
		LOG.debug("Second customer: "+ queueCustomers.peek());
		LOG.debug("Queue size: " + queueCustomers.size());
	}

	public HazelcastInstance getInstance() {
		return this.instance;
	}

}
