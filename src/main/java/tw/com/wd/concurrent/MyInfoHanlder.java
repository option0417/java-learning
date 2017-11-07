package tw.com.wd.concurrent;

public class MyInfoHanlder {
	private static volatile Integer cnt = 0;
	private static final String[] TEXT_ARRAY = new String[10000];
	
	static {
		System.out.println(" ======================== Create!! =======================");
		for (int idx = 0; idx < 10000; idx++) {
			TEXT_ARRAY[idx] = "N-" + String.valueOf(idx);
		}
	}
	
	public synchronized static void handleMyInfo() {
		MyInfo myInfo = MyInfoHolder.getMyInfo();
		
		myInfo.setCount(myInfo.getCount() + 100000);
		myInfo.setName(myInfo.getName() + "-handled");
		
		MyInfoHolder.setMyInfo(myInfo);


			synchronized (MyInfoHanlder.class) {
				System.out.printf("MyInfoHanlder count: %d, %s, %d\n", cnt, TEXT_ARRAY[cnt], TEXT_ARRAY[cnt].length());
				TEXT_ARRAY[cnt] = null;
				cnt++;
			}
	}
	
	public static int getCount() {
		return cnt;
	}
}
