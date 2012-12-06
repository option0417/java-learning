package op.sample.spring.bean_aware;

import org.springframework.beans.factory.BeanNameAware;

public class Cashier implements BeanNameAware{
	private String name;
	private int id;
	
	public void setBeanName(String _name) {
		name = _name;
	}
	
	public void setId(int _id) {
		id = _id;
	}
	
	public void show() {
		System.out.println("Cashier Name :" + name);
		System.out.println("Cashier ID :" + id);
	}
}
