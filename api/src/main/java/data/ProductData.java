package data;

/**
 * 
 * @author Borja
 *
 */

import coreapi.Product;

public interface ProductData {

	public Product getProduct(int id);
	public void saveProduct(Product prod);
}
