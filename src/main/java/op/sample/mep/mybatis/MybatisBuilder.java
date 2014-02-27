package op.sample.mep.mybatis;

import org.mybatis.generator.ant.GeneratorAntTask;

public class MybatisBuilder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {  
            GeneratorAntTask task = new GeneratorAntTask();
            task.setVerbose(true);
            task.setConfigfile(
            		"/home/option0417/Dev/JEEWorkspace/" +
            		"JavaLearning/JavaLearning/src/main/resources/generatorConfig.xml");
            task.log("TEST");
            task.execute();
        } catch (Exception e) {  
            e.printStackTrace();
        } 
	}
}
