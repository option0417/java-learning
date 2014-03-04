package op.sample.concurrent;

public class MyInfoHolder {
	private static final ThreadLocal<MyInfo> MY_INFO_HOLDER = new ThreadLocal<MyInfo>();
	
	public static void setMyInfo(MyInfo myInfo) {
		MY_INFO_HOLDER.set(myInfo);		
	}
	
	public static MyInfo getMyInfo() {
		return MY_INFO_HOLDER.get();
	}
	
	public static void clear() {
		MY_INFO_HOLDER.set(null);
	}
}
