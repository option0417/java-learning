package op.sample.jni;

import java.util.ArrayList;
import java.util.List;


public class Executor {
	public static void main(String[] args) {		
		String javaLibPath = System.getProperty("java.library.path");
		String customLibPath = "/home/option0417/Dev/sharedLib";
		System.setProperty("java.library.path", customLibPath);
		System.out.println(System.getProperty("java.library.path"));
		
		int count = 10;
		List<DataObj> dataObjList = new ArrayList<DataObj>(count);
		
		while (count > 0) {
			dataObjList.add(DataObjFactory.build());
			count--;
		}
//		
//		long startTime = System.currentTimeMillis();		
		showDataObj(dataObjList);		
//		long endTime = System.currentTimeMillis();
//		
//		double round_1_cost = ((double)(endTime - startTime)) / 1000d;
//		System.out.println("StartTime: " + startTime);
//		System.out.println("EndTime: " + endTime);
//		System.out.println("Cost: " + round_1_cost);
		
		JNIService jniService = new JNIServiceImpl();
		
		jniService.showDataObj(dataObjList);
		
	}

	
	private static void showDataObj(List<DataObj> dataObjList) {
		int index = 0;
		for (; index < dataObjList.size(); index++) {
			System.out.println(dataObjList.get(index).toJson());
		}
	}
}
