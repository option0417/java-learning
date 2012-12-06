package op.sample.app;

public class Sub extends Super{
	public int subID;
	public String subName;
	public Super obj;
	
	public Sub() {
		subName = "Sub";
	}
	
	public void setID(int subID) {
		this.subID = subID;
	}
	
	public void setName(String _name) {
		subName = _name;
	}
	
	public void setSuper(Super obj) {
		this.obj = obj;
	}
	
	
	public int getID() {
		return subID;
	}
	
	public String getName()	{
		return subName;
	}
	
	public Super getSuper() {
		return obj;
	}
}
