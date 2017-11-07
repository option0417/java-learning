package tw.com.wd.spring.factory_method;

import tw.com.wd.spring.product.AProductBean;
import tw.com.wd.spring.product.ProductBooks;

public class ProductCreator {
	public static AProductBean createProduct(String productId) {
		if( "aaa".equals(productId))
			return new ProductBooks(111, "FactoryBook");
		else
			throw new IllegalArgumentException("Unknown Product");
	}
}
