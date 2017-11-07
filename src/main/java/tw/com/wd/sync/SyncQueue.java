package tw.com.wd.sync;

import java.util.ArrayList;

public class SyncQueue extends ArrayList<SyncObject>{

	/** Serial Version UID */
	private static final long serialVersionUID = 8790565612616091717L;

	public void run() {
        IObject iObj = new IObject();
        iObj.setStrID("StringID");
        iObj.setObjID(new Object());
        
		for(int i = 0; i < this.size(); i++) {
	        iObj.setIntID(i+10);
			System.out.println("Include :" + this.get(i).toString());
			System.out.println("\tPK :" + this.get(i).getPK().getClass());
			System.out.println("\tID :" + this.get(i).getID());
			String str = this.get(i).transEntityToString(iObj);
			System.out.println("\tString :" + str);
			IObject iObj2 = (IObject)this.get(i).transStringToEntity(str);
			System.out.println("\tObject :" + iObj2);
		}
	}
}
