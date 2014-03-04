package op.sample.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executor {

	public static void main(String[] args) {
		
		
		int numOfThread = 10000;
		ExecutorService threadPool = Executors.newFixedThreadPool(numOfThread); 
		List<Future<MyInfo>> myInfoThreadList = new ArrayList<Future<MyInfo>>(numOfThread);
		

		long startTime = System.currentTimeMillis();
		for (int idx = 1; idx <= numOfThread; idx++) {
			MyInfoThread myInfoThread = new MyInfoThread("MyInfoThread-" + String.valueOf(idx), idx);
			myInfoThreadList.add(threadPool.submit(myInfoThread));
		}
		
		for (int idx = 0; idx < numOfThread; idx++) {
			try {
				MyInfo myInfo = myInfoThreadList.get(idx).get();
				
				//System.out.println("Name: " + myInfo.getName());
				//System.out.println("Count: " + myInfo.getCount());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		threadPool.shutdown();
		
		
		System.out.printf("TOTAL: %f\n", ((double)(endTime - startTime))/1000d);
		System.out.printf("CNT: %d\n", MyInfoHanlder.getCount());
	}
}
