package op.sample.spring.factory_method;

import op.sample.spring.product.AProductBean;
import op.sample.spring.product.ProductBooks;

public class ProductCreator {
	public static AProductBean createProduct(String productId) {
		if( "aaa".equals(productId))
			return new ProductBooks(111, "FactoryBook");
		else
			throw new IllegalArgumentException("Unknown Product");
	}
}
