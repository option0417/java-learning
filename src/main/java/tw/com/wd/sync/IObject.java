package tw.com.wd.sync;

public class IObject {
	private String strID;
	private int intID;
	private Object objID;
	
	public IObject() { }
	
	public IObject(String strID, int intID, Object objID) {
		this.strID = strID;
		this.intID = intID;
		this.objID = objID;
	}
	
	public void setStrID(String strID) {
		this.strID = strID;
	}
	
	public void setIntID(int intID) {
		this.intID = intID;
	}
	
	public void setObjID(Object objID) {
		this.objID = objID;
	}
	
	public String getStrID() {
		return this.strID;
	}
	
	public int getIntID() {
		return this.intID;
	}
	
	public Object getObjID() {
		return this.objID;
	}
}
