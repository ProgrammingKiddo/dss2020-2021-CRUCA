/**
 * 
 */
package coreapi;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Order {

	public float totalCost();
	public int getId();
	public Date getDate();
	public List<Product> getProducts();
	public Map<Product, Integer> getBasket();
	public int checkProductQuantity(int id);
	public boolean containsProduct(int id);
}
