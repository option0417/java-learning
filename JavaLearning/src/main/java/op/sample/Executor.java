package op.sample;

import java.io.File;

import op.sample.spring.exception.TestException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Executor {
	private static final Logger LOG = LogManager.getLogger(Executor.class);
	//private static final ClassPathXmlApplicationContext appContext =
	//		new ClassPathXmlApplicationContext("opSample-beans-config.xml");
	public static void main(String[] args) throws Exception {
//		new Prepopulate().prepopulate();
//		LOG.debug("** Database filled. **");
		
//		JSONServiceClient client = new JSONServiceClient();
//		client.setURI(new URI(""));
//		client.sendRequest();
		
//		try {
//			appContext.getBean(TestException.class).testException();
//			System.out.println("Done");
//		} catch (Exception e) {
//			System.out.println("Exception occured.");
//		}
		
		File directory = new File("/home/option0417/Dev/EclipsePlatform/JEEWorkspace/mep");
		delDir(directory, "_svn");
		
	}
	
	private static void delDir(File targetDir, String dirName) {
		for (File tmpFile : targetDir.listFiles()) {
			if (tmpFile.isDirectory()) {
				if (tmpFile.getName().equals(dirName)) {
					System.out.println(tmpFile.getName());
					delDir(tmpFile);
				} else {
					delDir(tmpFile, dirName);
				}
			}
		}
	}
	
	private static void delDir(File targetDir) {
		for (File tmpFile : targetDir.listFiles()) {
			if (tmpFile.isDirectory()) {
				delDir(tmpFile);
			} else {
				System.out.println(tmpFile.getName() + " will deleted");
				tmpFile.delete();
			}
		}
		targetDir.delete();
	}
}

