package op.sample;

import op.sample.spring.exception.TestException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Executor {
	private static final Logger LOG = LogManager.getLogger(Executor.class);
	private static final ClassPathXmlApplicationContext appContext =
			new ClassPathXmlApplicationContext("opSample-beans-config.xml");
	public static void main(String[] args) throws Exception {
//		new Prepopulate().prepopulate();
//		LOG.debug("** Database filled. **");
		
//		JSONServiceClient client = new JSONServiceClient();
//		client.setURI(new URI(""));
//		client.sendRequest();
		
		try {
			appContext.getBean(TestException.class).testException();
			System.out.println("Done");
		} catch (Exception e) {
			System.out.println("Exception occured.");
		}
		
	}
}

