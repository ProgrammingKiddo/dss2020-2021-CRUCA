package coreapi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Class representing a Cafeteria with associated orders and its own stock of products.
 * @author Borja
 * @version 0.1
 */
public class Cafeteria implements Serializable
{
	private static final long serialVersionUID = 1L;
	private LinkedHashMap<Product, Integer> productStock;
	private List<Order> orderHistory;
	private String name;
	private int id;
	private List<String> types;
	
	/**
	 * Creates a new instance of a cafeteria, with its own name and an identifier to
	 * be related by orders.
	 * 
	 * To be used only by the designers of the information database.
	 * @param assignedId	the identifier assigned to this cafeteria.
	 * @param givenName		the name assigned to this cafeteria.
	 */
	public Cafeteria(int assignedId, String givenName)
	{
		productStock = new LinkedHashMap<Product, Integer>();
		orderHistory = new ArrayList<Order>();
		id = assignedId;
		name = givenName;
		types = new ArrayList<String>();
	}
	/**
	 * Returns the unique id assigned by the info database to this specific cafeteria.
	 * @return 	the identifier of this cafeteria.
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * Returns the name assigned by the info database to this specific cafeteria.
	 * @return Returns the name of this cafeteria.
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Returns the list of available products in this cafeteria.
	 * 
	 * The list is a read-only copy of the products available for purchase in
	 * the cafeteria, even if there's no stock of them at the moment.
	 * @return Returns the list of available products in this cafeteria.
	 */
	public List<Product> getAvailableProducts()
	{
		return List.copyOf(new ArrayList<Product>(productStock.keySet()));
	}
	
	/**
	 * Return the list of the differents types of products
	 * 
	 *  @return Return the list of the differents types of products
	 */
	public List<String> getTypes()
	{
		return List.copyOf(types);
	}
	/**
	 * Registers a new product as available on this cafeteria.
	 * @param prod		A reference to the product to add to this cafeteria. 
	 * @param quantity	The amount of quantity of product to register to the stock.
	 */
	public void registerProduct(Product prod, int quantity)
	{
		if (productStock.containsKey(prod) == false)
		{
			productStock.put(prod, quantity);			
		}
	}
	/**
	 * Returns the current stock of the indicated product available in this cafeteria.
	 * @param prod	The identifier of the product to check for.
	 * @return		Returns the amount of stock in this cafeteria from the indicated product.
	 */
	public int getProductQuantity(Product prod)
	{
		return productStock.get(prod).intValue();
	}
	/**
	 * Add the indicated amount of product stock to this cafeteria.
	 * @param prod		The product for which to add stock. 
	 * @param quantity	The quantity of product to add.
	 */
	public void addProductStock(Product prod, int quantity)
	{
		if (quantity > 0)
		{
			AddType(prod.getType());
			if (productStock.containsKey(prod) == false)
			{
				productStock.put(prod, quantity);
			}
			else
			{
				Integer newQuantity = Integer.valueOf(productStock.get(prod).intValue() + quantity);
				productStock.replace(prod, newQuantity);
			}
		}
		else
		{
			//TO-DO Maybe implement new exception just for this?
		}
	}
	/**
	 * Removes the indicated amount of product stock from this cafeteria.
	 * 
	 * Keep in mind that removing all stock of this product does NOT remove the product
	 * from the cafeteria, to do so, use the <code>removeProduct()</code> method instead.
	 * 
	 * @param prod		The product from which to remove stock.
	 * @param quantity	The amount of product to remove from the stock of this cafeteria.
	 * @throws InsufficientStockException If the amount to be removed is greater than
	 * 							the current stock of the indicated product.
	 */
	public void removeStock(Product prod, int quantity) throws InsufficientStockException
	{
		if (getProductQuantity(prod) < quantity)
		{
			throw new InsufficientStockException("Attempted to remove more stock than available from the Cafeteria " + name + ".");
		}
		else
		{
			//DeleteType(prod.getType());
			Integer newQuantity = Integer.valueOf(productStock.get(prod).intValue() - quantity);
			productStock.replace(prod, newQuantity);
		}
	}
	/**
	 * Removes the product from the list of available products from this cafeteria.
	 * @param prod	The product to remove.
	 */
	public void removeProduct(Product prod)
	{
		productStock.remove(prod);
	}
	/**
	 * Registers an order as pertaining to this cafeteria.
	 * @param newOrder The order to register to this cafeteria.
	 */
	public void registerOrder(Order newOrder)
	{
		orderHistory.add(newOrder);
	}
	/**
	 * Returns the list of orders registered to this cafeteria.
	 * @return Returns the list of orders registered to this cafeteria.
	 */
	public List<Order> getOrders()
	{
		return List.copyOf(orderHistory);
	}
	 /**
	  * Add a new type to the types list in case that this type
	  * doesn't exist in the list.
	  * 
	  * @param String which contains the type to add
	  */
	public void AddType(String t)
	{
		if(types.contains(t) != true)
		{
			types.add(t);
		}
	}
	
	/**
	 * Delete a type of the types list.
	 * 
	 * @param String which contains the type to delete.
	 */
	public void DeleteType(String type)
	{
		types.remove(type);
	}
}
