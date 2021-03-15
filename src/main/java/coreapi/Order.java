/**
 * 
 */
package coreapi;

import java.util.List;

public interface Order {

	public int totalCost();
	public List<Product> getProducts();
	public boolean containsProduct(int id);
}
