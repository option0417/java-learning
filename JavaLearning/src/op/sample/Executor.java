package op.sample;

import op.sample.cassandra.hotel.service.Prepopulate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Executor {
	private static final Logger LOG = LogManager.getLogger(Executor.class);
	public static void main(String[] args) throws Exception {
		new Prepopulate().prepopulate();
		LOG.debug("** Database filled. **");
	}
}
