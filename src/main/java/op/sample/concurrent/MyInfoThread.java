package op.sample.concurrent;

import java.util.concurrent.Callable;

public class MyInfoThread implements Callable<MyInfo> {
	private String name;
	private int count;
	
	public MyInfoThread(String name, int count) {
		this.name = name;
		this.count = count;
	}
	
	@Override
	public MyInfo call() throws Exception {
		MyInfo myInfo = new MyInfo(this.name, this.count);
		MyInfoHolder.setMyInfo(myInfo);
		
		try {
			MyInfoHanlder.handleMyInfo();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.printf("Thread: %s, %d  Exception occured\n", this.name, this.count);
		}
		
		return MyInfoHolder.getMyInfo();
	}
}
