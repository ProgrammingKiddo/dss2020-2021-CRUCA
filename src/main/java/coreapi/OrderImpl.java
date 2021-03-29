/**
 * Implementation of the <code>Order</code> interface.
 * Users of the API shouldn't use this class directly.
 * @author Borja
 * @version 0.2
 */
package coreapi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;

public class OrderImpl implements Order {

	private final int id;
	private final Date date;
	// Map cannot take primitive types as the type of its mapped values
	private LinkedHashMap<Product, Integer> basket;
	private OrderStatus orderStatus;
	
	/**
	 * Creates a new <code>OrderImpl</code> instance.
	 * @param assignedId the identifier assigned to this order.
	 * @param creationDate the timestamp representing the moment this order was created.
	 */
	OrderImpl(int assignedId, Date creationDate)
	{
		id = assignedId;
		date = creationDate;
		basket = new LinkedHashMap<Product, Integer>();
		orderStatus = OrderStatus.OPEN;
	}
	
	/**
	 * Returns the unique id assigned by the order factory to this specific order.
	 * @return returns the id of this order.
	 * @see OrderFactory
	 */
	public int getId() { return id; }
	/**
	 * Returns the timestamp representing the moment this order was created. 
	 * @return the date on which the order was created.
	 * @see Date
	 */
	public Date getDate() { return date; }
	/**
	 * Returns a read-only value of the current state of this order.
	 * @return returns the state of this order.
	 * @see OrderStatus
	 */
	public OrderStatus getStatus() { return OrderStatus.valueOf(orderStatus.name()); }
	/**
	 * Sets the current status of this order to the one passed by parameter.
	 * @param newStatus the new status to set this order to.
	 * @see OrderStatus
	 */
	public void setStatus(OrderStatus newStatus) { orderStatus = newStatus; }
	
	/**
	 * Returns the total cost of all the products contained in the order.
	 * Takes into account the individual price of each product, as well as how many
	 * of them are currently in this order.
	 * @return the combined cost of this order.
	 * @see BigDecimal
	 */
	public BigDecimal totalCost() 
	{
		BigDecimal sumCost = BigDecimal.ZERO;
		BigDecimal productCost = BigDecimal.ZERO;
		BigDecimal productQuantity = BigDecimal.ZERO;
		
		for (Map.Entry<Product, Integer> entry : basket.entrySet())
		{
			// We create a copy of the product's price as to not modify its value via this reference.
			productCost = entry.getKey().getPrice();
			productQuantity = new BigDecimal(entry.getValue().intValue());
			productCost = productCost.multiply(productQuantity);
			
			sumCost = sumCost.add(productCost);
		}
		return sumCost;
	}
	
	/**
	 * Returns a read-only list containing what products are in this Order.
	 * @return the list of products contained in this order.
	 */
	public List<Product> getProducts()
	{
		return Collections.unmodifiableList(new ArrayList<Product>(basket.keySet()));
	}
	
	/**
	 * Returns a read-only map containing what products and how many
	 * of each of them are in this order.
	 * @return the map of products and their quantities in this order.
	 */
	public Map<Product,Integer> getBasket()
	{
		return Collections.unmodifiableMap(basket);
	}
	
	/**
	 * Returns whether or not the product determined by the id is contained in this order.
	 * Returns <code>true</code> if there is at least one unit of the product in this order, and
	 * <code>false</code> if there is none.
	 * @return if the product represented by the id exists in this order.
	 */
	public boolean containsProduct(int id)
	{
		return basket.containsKey(ProductCatalog.Instance().getProduct(id));
	}

	/**
	 * Returns how many existences of the product determined by the id are there in this order.
	 * Returns zero if the product in question isn't in this order.
	 * @return the quantity of a product in this order.
	 */
	public int checkProductQuantity(int id)
	{
		int quantity;
		
		if (this.containsProduct(id))
		{
			quantity = basket.get(ProductCatalog.Instance().getProduct(id)).intValue();
		}
		else
		{
			quantity = 0;
		}
		return quantity;
	}
	/**
	 * Adds a single unit of the product determined by the id to this order.
	 * @param newProductId the id of the product to add.
	 */
	public void addProduct(int newProductId)
	{
		addProduct(newProductId, 1);
	}
	/**
	 * Adds a certain amount of units of the product determined by the id to this order.
	 * @param newProductId the id of the product to add.
	 * @param quantity the amount of the product to add.
	 */
	public void addProduct(int newProductId, int quantity)
	{
		if (quantity > 0)
		{
			Product prod = ProductCatalog.Instance().getProduct(newProductId);
			if (this.containsProduct(newProductId))
			{
				int actualQuantity = basket.get(prod).intValue();
				basket.replace(prod, Integer.valueOf(actualQuantity + quantity));
			}
			else
			{
				basket.put(prod, Integer.valueOf(quantity));
			}	
		}
	}
	/**
	 * Removes all amounts of the product determined by the id from this order.
	 * The product in question will no longer be contained within this order.
	 * @param productId the id of the product to remove.
	 */
	public void removeProduct(int productId)
	{
		basket.remove(ProductCatalog.Instance().getProduct(productId));
	}
	/**
	 * Removes a certain amount of units of the product determined by the id from this order.
	 * If the amount to remove is equal to or greater that the amount of that product currently
	 * in the order, the product in question is removed from the order entirely.
	 * @param productId the id of the product to remove.
	 * @param quantity the amount of the product to remove.
	 */
	public void removeProduct(int productId, int quantity)
	{
		if (quantity > 0)
		{
			Product prod = ProductCatalog.Instance().getProduct(productId);
			if (basket.get(prod).intValue() <= quantity)
			{
				this.removeProduct(productId);
			}
			else
			{
				int actualQuantity = basket.get(prod).intValue();
				basket.replace(prod, Integer.valueOf(actualQuantity - quantity));
			}
		}
	}
}
