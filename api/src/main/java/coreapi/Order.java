package coreapi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * This interface represents a regular order at a cafeteria.
 * 
 * Users should be able to get by with the methods declared here for a basic
 * usage of an order object.
 * @author Borja
 * @version 0.2
 */
public interface Order {

	/**
	 * Returns the combined cost of the whole order.
	 * 
	 * Takes into account which products comprised the order, and how many of each of them.
	 * @return	Returns the cost of the whole order.
	 * @see BigDecimal
	 */
	public BigDecimal totalCost();
	/**
	 * Returns the unique Id assigned to this order.
	 * 
	 * Each Order has a unique identifier within the system. Two different orders cannot
	 * have the same id at any time.
	 * @return	Returns the order Id.
	 * @see OrderFactory
	 */
	public int getId();
	/**
	 * Returns the date when the order was created.
	 * @return	Returns the day of creation of the order
	 * @see LocalDate
	 */
	public LocalDate getDate();
	/**
	 * Returns the constant corresponding to the current state of the order,
	 * as determined by the Enum <code>OrderStatus</code>.
	 * @return	Returns the current state of the order.
	 * @see OrderStatus
	 */
	public OrderStatus getStatus();
	/**
	 * Returns a read-only list of the products contained in this order.
	 * 
	 * The list does NOT contain how many of each product is contained within the order.
	 * @return	Returns the list of products in this order.
	 * @see List
	 * @see Product
	 */
	public List<Product> getProducts();
	/**
	 * Returns a map containing the products contained in this order,
	 * and how many of each of them is stored.
	 * @return	Returns the map containing the quantity of each product in the order
	 * @see Map
	 */
	public Map<Product, Integer> getBasket();
	/**
	 * Returns how much of a certain product is contained within this order.
	 * 
	 * If the product isn't contained in this order, the return value is zero.
	 * @param id	The id of the product to check.
	 * @return		Retunrs the amount of a certain product in this order.
	 */
	public int checkProductQuantity(int id);
	/**
	 * Returns whether or not the product passed as parameter is contained in this order or not.
	 * 
	 * The return value is <code>true</code> if there is at least 1 unit of the product in this order,
	 * and <code>false</code> if there is none.
	 * @param id	The id of the product to check.
	 * @return		Returns <code>true</code> if the order contains the specified product.
	 */
	public boolean containsProduct(int id);
}
