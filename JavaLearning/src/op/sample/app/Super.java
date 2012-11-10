package op.sample.app;

public class Super {
	private int id;
	private String name;	
	private Sub obj;
	
	public Super() {
		name = "Super";
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSub(Sub obj) {
		this.obj = obj;
	}

	
	public int getID() {
		return id;
	}
	
	public String getName()	{
		return name;
	}
	
	public Sub getSub()	{
		return obj;
	}
}
