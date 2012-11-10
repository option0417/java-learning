package op.sample;

import op.sample.app.Sub;
import op.sample.app.Super;
import op.sample.log4j.TestLog4j;


public class Executor {
	public static void main(String[] args) {
		Super superObj = new Super();
		Sub subObj = new Sub();		
		
		superObj.setID(123);
		superObj.setName("Super");
		
		subObj.setID(999);
		subObj.setName("Sub");
		
		superObj.setSub(subObj);
		subObj.setSuper(superObj);
		
		System.out.println("===== 1 =====");
		System.out.println(superObj.getSub().getID());
		System.out.println(subObj.getSuper().getID());
		System.out.println();
		
		superObj.setID(321);
		subObj.setID(111);
		
		System.out.println("===== 2 =====");
		System.out.println(superObj.getSub().getID());
		System.out.println(subObj.getSuper().getID());
		System.out.println();
	}
}
