/**
 * 
 */
package coreapi;

import java.util.List;

public interface Order {

	public float totalCost();
	public int getId();
	public List<Product> getProducts();
	public boolean containsProduct(int id);
}
