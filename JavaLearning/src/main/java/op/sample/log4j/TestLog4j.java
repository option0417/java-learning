package op.sample.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class TestLog4j {
	private static final  Logger logger = LogManager.getLogger(TestLog4j.class.getName());
	
	public static void showTest()	{
    	logger.trace("Hello Trace");
    	logger.debug("Hello Debug");
    	logger.info("Hello Info");
    	logger.warn("Hello Warn");
    	logger.error("Hello Error");
    	logger.fatal("Hello Fatal");
	}
}
