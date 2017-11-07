package tw.com.wd.hazelcast.listener.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.DistributedObjectEvent;
import tw.com.wd.hazelcast.listener.SimpleListener;

public class SimpleListenerImpl implements SimpleListener {
	private Logger LOG = LoggerFactory.getLogger(getClass());

	public void distributedObjectCreated(DistributedObjectEvent event) {
		DistributedObject distributedObj = event.getDistributedObject();
		LOG.debug("Created " + distributedObj.getName() + "," + distributedObj.getId());
	}

	public void distributedObjectDestroyed(DistributedObjectEvent event) {
		DistributedObject distributedObj = event.getDistributedObject();
		LOG.debug("Destroyed " + distributedObj.getName() + "," + distributedObj.getId());
	}
}
