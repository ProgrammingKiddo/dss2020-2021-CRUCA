package coreapi;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.time.LocalDateTime;
import java.time.LocalTime;
import filepersistence.*;

/**
 * Implementation of the <code>Order</code> interface.
 * 
 * Users of the API shouldn't use this class directly.
 * @author Borja
 * @version 0.2
 */
public class OrderImpl implements Order, Serializable {

	private static final long serialVersionUID = 1L;
	private final int id;
	private LocalDateTime creationDate;
	private LocalDateTime programmingDate;
	// Map cannot take primitive types as the type of its mapped values
	private LinkedHashMap<Product, Integer> basket;
	private OrderStatus orderStatus;
	
	/**
	 * Creates a new <code>OrderImpl</code> instance.
	 * @param assignedId	The identifier assigned to this order.
	 * @param creationDate	The timestamp representing the day this order was created.
	 */
	OrderImpl(int assignedId, LocalDateTime assignedDate)
	{
		id = assignedId;
		creationDate = assignedDate;
		programmingDate = LocalDateTime.now();
		basket = new LinkedHashMap<Product, Integer>();
		orderStatus = OrderStatus.OPEN;
	}
	
	/**
	 * Returns the unique id assigned by the order factory to this specific order.
	 * @return	Returns the order Id.
	 * @see OrderFactory
	 */
	public int getId() { return id; }
	/**
	 * Returns the date when the order was created.
	 * @return	Returns the day of creation of the order
	 * @see LocalDate
	 */
	public LocalDateTime getDate() { return creationDate; }
	/**
	 * Returns the constant corresponding to the current state of the order,
	 * as determined by the Enum <code>OrderStatus</code>.
	 * @return	Returns the current state of the order.
	 * @see OrderStatus
	 */
	public OrderStatus getStatus() { return OrderStatus.valueOf(orderStatus.name()); }
	/**
	 * Sets the current status of this order to the one passed by parameter.
	 * @param newStatus		The new status to set this order to.
	 * @see OrderStatus
	 */
	public void setStatus(OrderStatus newStatus) { orderStatus = newStatus; }
	
	/**
	 * Returns the combined cost of the whole order.
	 * 
	 * Takes into account the individual price of each product, as well as how many
	 * of them are currently in this order.
	 * @return	Returns the combined cost of this order.
	 * @see BigDecimal
	 */
	public BigDecimal totalCost() 
	{
		BigDecimal sumCost = BigDecimal.ZERO;
		BigDecimal productCost;
		BigDecimal productQuantity;
		
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
	 * @return	Returns the list of products contained in this order.
	 */
	public List<Product> getProducts()
	{
		return List.copyOf(new ArrayList<Product>(basket.keySet()));
	}
	
	/**
	 * Returns a read-only map containing what products and how many
	 * of each of them are in this order.
	 * @return	Returns the map of products and their quantities in this order.
	 */
	public Map<Product,Integer> getBasket()
	{
		return Map.copyOf(basket);
	}
	
	/**
	 * Returns whether or not the product determined by the id is contained in this order.
	 * 
	 * Returns <code>true</code> if there is at least one unit of the product in this order, and
	 * <code>false</code> if there is none.
	 * @param id	The identifier of the product to check for.
	 * @return 		Returns whether or not the product represented by the id exists in this order.
	 */
	public boolean containsProduct(int id)
	{
		return basket.containsKey(Load.LoadProduct(id));
	}

	/**
	 * Returns how many existences of the product determined by the id are there in this order.
	 * 
	 * Returns zero if the product in question isn't in this order.
	 * @return	Returns the quantity of a product in this order.
	 */
	public int checkProductQuantity(int id)
	{
		int quantity;
		
		if (this.containsProduct(id))
		{
			quantity = basket.get(Load.LoadProduct(id)).intValue();
		}
		else
		{
			quantity = 0;
		}
		return quantity;
	}
	/**
	 * Adds a single unit of the product determined by the id to this order.
	 * @param newProductId	The id of the product to add.
	 */
	public void addProduct(int newProductId)
	{
		addProduct(newProductId, 1);
	}
	/**
	 * Adds a certain amount of units of the product determined by the id to this order.
	 * @param newProductId	The id of the product to add.
	 * @param quantity		The amount of product to add.
	 */
	public void addProduct(int newProductId, int quantity)
	{
		if (quantity > 0)
		{
			Product prod = Load.LoadProduct(newProductId);
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
	 * 
	 * The product in question will no longer be contained within this order.
	 * @param productId	The id of the product to remove.
	 */
	public void removeProduct(int productId)
	{
		basket.remove(Load.LoadProduct(productId));
	}
	/**
	 * Removes a certain amount of units of the product determined by the id from this order.
	 * 
	 * If the amount to remove is equal to or greater that the amount of that product currently
	 * in the order, the product in question is removed from the order entirely.
	 * @param productId	The id of the product to remove.
	 * @param quantity	The amount of product to remove.
	 */
	public void removeProduct(int productId, int quantity)
	{
		if (quantity > 0)
		{
			Product prod = Load.LoadProduct(productId);
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
