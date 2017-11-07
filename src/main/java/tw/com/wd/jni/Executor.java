package tw.com.wd.jni;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Executor {
	public static void main(String[] args) throws Exception {		
		int count = 1000000;
		List<DataObj> dataObjList = new ArrayList<DataObj>(count);
		
		while (count > 0) {
			dataObjList.add(DataObjFactory.build());
			count--;
		}

		System.out.println("Java parser Starting");
		long startTime = System.currentTimeMillis();
		showDataObj(dataObjList);		
		long endTime = System.currentTimeMillis();
		
		double round_1_cost = ((double)(endTime - startTime)) / 1000d;
		System.out.println("Java parser done");
		

		Thread.currentThread().sleep(1000);

		System.out.println("JNI parser Starting");
		startTime = System.currentTimeMillis();
		JNIService jniService = new JNIServiceImpl();		
		jniService.showDataObj(dataObjList);
		endTime = System.currentTimeMillis();
		
		double round_2_cost = ((double)(endTime - startTime)) / 1000d;
		System.out.println("JNI parser done");
		
		System.out.flush();
		Thread.currentThread().sleep(1000);
		System.out.flush();
		
		System.out.println("Round1 Cost: " + round_1_cost);
		System.out.println("Round2 Cost: " + round_2_cost);
	}

	
	private static void showDataObj(List<DataObj> dataObjList) {
		int index = 0;
        try {
            FileOutputStream fos = new FileOutputStream(new File(""));

            for (; index < dataObjList.size(); index++) {
                dataObjList.get(index).toJson();
                //System.out.println(dataObjList.get(index).toJson());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
}
