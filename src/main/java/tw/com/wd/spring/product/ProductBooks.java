package tw.com.wd.spring.product;

public class ProductBooks extends AProductBean{
	private int price;
	
	public ProductBooks() {	super();	}
	
	public ProductBooks(int _id, String _name)	{	super(_id, _name);	}
	
	public void setPrice(int _price)	{	price = _price;	}
	
	public void showData() {
		super.showData();
		System.out.println("Product Price :" + price);
	}
}
