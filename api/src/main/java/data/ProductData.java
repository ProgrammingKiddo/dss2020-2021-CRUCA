package data;

/**
 * Interface that offers operations to save the data of a product.
 * @author Borja
 * @author Maria
 *
 */

import coreapi.Product;

public interface ProductData 
{
	/**
	 * Return a specific product.
	 * @param id	The id of a specific product.
	 * @return	Return a specific product.
	 */
	public Product getProduct(int id);
	
	/**
	 * Save a specific product.
	 * @param prod	The specific product.
	 */
	public void saveProduct(Product prod);
}
