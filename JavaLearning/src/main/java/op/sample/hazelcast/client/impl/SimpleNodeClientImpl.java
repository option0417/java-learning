package op.sample.hazelcast.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import op.sample.hazelcast.client.SimpleNodeClient;

public class SimpleNodeClientImpl implements SimpleNodeClient {
	private Logger LOG = LoggerFactory.getLogger(getClass());

	public void connect() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.addAddress("127.0.0.1:5701");
		HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		IMap<Object, Object> map = client.getMap("customers");
		LOG.debug("Map Size:" + map.size());
	}
}
