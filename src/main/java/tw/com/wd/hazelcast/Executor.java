package tw.com.wd.hazelcast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import tw.com.wd.hazelcast.listener.impl.SimpleListenerImpl;
import tw.com.wd.hazelcast.server.SimpleNode;
import tw.com.wd.hazelcast.server.impl.SimpleNodeImpl;

public class Executor {
	private static final Logger LOG = LoggerFactory.getLogger(Executor.class);
	
	public static void main(String[] args) {
		LOG.debug("===== Start Executor =====");
		LOG.debug("Start Node 1");
		SimpleNode node1 = new SimpleNodeImpl();
		HazelcastInstance instance1 = node1.getInstance();
		instance1.addDistributedObjectListener(new SimpleListenerImpl());
		

		SimpleNode node2 = new SimpleNodeImpl();
		HazelcastInstance instance2 = node2.getInstance();
		instance2.addDistributedObjectListener(new SimpleListenerImpl());

		IMap<Object, Object> iMap1 = instance1.getMap("Test");
		IMap<Object, Object> iMap2 = instance2.getMap("Test");
		 
		iMap1.put(1, "A");
		iMap1.put(2, "B");
		iMap1.put(3, "C");
		LOG.debug("Pre SizeOfMap1: {}", instance1.getMap("Test").size());
		
		iMap2.put(4, "D");
		iMap2.put(5, "E");
		iMap2.put(6, "F");
		LOG.debug("Post SizeOfMap1: {}", instance1.getMap("Test").size());
		
		
		
//		LOG.debug("Start Node 2");
//		SimpleNode node2 = new SimpleNodeImpl();
//		node2.start();
		
//		SimpleNodeClient simpleNodeClient = new SimpleNodeClientImpl();
//		LOG.debug("Invoke SimpleNodeClient connect to SimpleNode");
//		simpleNodeClient.connect();
	}
}