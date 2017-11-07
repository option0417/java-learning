package tw.com.wd.concurrent;

public class MyInfo {
	private String name;
	private int count;
	
	public MyInfo(String name, int count) {
		this.name = name;
		this.count = count;
	}
	
	public MyInfo setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public MyInfo setCount(int count) {
		this.count = count;
		return this;
	}
	
	public int getCount() {
		return this.count;
	}
}
