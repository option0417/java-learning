package op.sample;

import java.net.URI;

import op.sample.webservice.rs.JSONServiceClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Executor {
	private static final Logger LOG = LogManager.getLogger(Executor.class);
	public static void main(String[] args) throws Exception {
//		new Prepopulate().prepopulate();
//		LOG.debug("** Database filled. **");
		
		JSONServiceClient client = new JSONServiceClient();
		client.setURI(new URI(""));
		client.sendRequest();
	}
}

