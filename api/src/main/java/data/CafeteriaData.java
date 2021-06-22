package data;

/**
 * Interface that offers operations to save the data of a cafeteria.
 * @author Borja
 * @author Maria
 */

import coreapi.Cafeteria;
import coreapi.Product;
import java.util.List;

public interface CafeteriaData {

	/**
	 * Return a specific cafeteria.
	 * @param cafeteriaId	The id of a specific cafeteria.
	 * @return	Return a specific cafeteria.
	*/
	public Cafeteria getCafeteria(int cafeteriaId);
	
	/**
	 * Returns the list of products that the cafeteria has in stock.
	 * @param cafeteriaId	The identification of a specific cafeteria.
	 * @return	Returns the list of products that this cafeteria has in stock.
	 */
	public List<Product> getProductsInStock(int cafeteriaId);
	
	/**
	 * Returns the amount of stock a product has in a specific coffee.
	 * @param cafeteriaId	The id of a specific cafeteria.
	 * @param prod			A specific product.
	 * @return
	 */
	public int getProductStock(int cafeteriaId, Product prod);
	
	/**
	 * Save a specific coffee.
	 * @param coffee	The specific coffe.
	 */
	public void saveCafeteria(Cafeteria coffee);
}
