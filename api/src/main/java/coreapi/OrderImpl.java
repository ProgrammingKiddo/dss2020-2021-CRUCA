package coreapi;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.time.LocalDateTime;


/**
 * Implementation of the <code>Order</code> interface.
 * 
 * Users of the API shouldn't use this class directly.
 * @author Borja
 * @author Mar�a
 * @author Fran
 * @version 0.2
 */
public class OrderImpl implements Order, Serializable {

	private static final long serialVersionUID = 1L;
	private final int id;
	private LocalDateTime creationDate;
	private LocalDateTime programmingDate;
	// Map cannot take primitive types as the type of its mapped values
	private Map<Product, Integer> basket;
	private OrderStatus orderStatus;
	private String validationCode;
	
	/**
	 * Creates a new <code>OrderImpl</code> instance.
	 * @param assignedId	The identifier assigned to this order.
	 * @param assignedDate	The timestamp representing the day this order was created.
	 */
	OrderImpl(int assignedId, LocalDateTime assignedDate)
	{
		id = assignedId;
		creationDate = assignedDate;
		basket = new LinkedHashMap<Product, Integer>();
		orderStatus = OrderStatus.OPEN;
	}
	
	/**
	 * Returns the unique id assigned by the order factory to this specific order.
	 * @return	Returns the order Id.
	 * @see OrderFactory
	 */
	public int getId() 
	{ 
		return id; 
	}
	
	/**
	 * Returns the date when the order was created.
	 * @return	Returns the day of creation of the order
	 * @see LocalDate
	 */
	public LocalDateTime getDate() 
	{ 
		return creationDate; 
	}
	
	/**
	 * Returns the constant corresponding to the current state of the order,
	 * as determined by the Enum <code>OrderStatus</code>.
	 * @return	Returns the current state of the order.
	 * @see OrderStatus
	 */
	public OrderStatus getStatus() 
	{ 
		return OrderStatus.valueOf(orderStatus.name()); 
	}
	
	/**
	 * Returns the date for which the corresponding order has been scheduled.
	 * @return	Returns the date and time of the scheduled order.
	 */
	public LocalDateTime getProgrammingDate()
	{
		return programmingDate;
	}
	
	/**
	 * Returns the validation code of the order.
	 * @return Returns the validation code of the concrete order.
	 */
	public String getCode()
	{
		return validationCode;
	}
	/**
	 * Set the validation code of this order to the one passed by parameter.
	 * @param vC	The new validation code to set this order.
	 */
	public void setCode(String vC)
	{
		validationCode = vC;
	}
	
	/**
	 * Sets the current status of this order to the one passed by parameter.
	 * @param newStatus		The new status to set this order to.
	 * @see OrderStatus
	 */
	public void setStatus(OrderStatus newStatus) 
	{ 
		orderStatus = newStatus; 
	}
	
	/**
	 * Sets the programming date of this order to the one passed by parameter.
	 * @param pDate		The new programming date to set this order.
	 * @throws InvalidDateException	If the date entered is before the current date.
	 */
	public void setProgrammingDate(LocalDateTime pDate) throws InvalidDateException
	{
		try
		{
			if((pDate.compareTo(LocalDateTime.now()) >= 0))
			{
				programmingDate = pDate;
			}
			else
			{
				throw new InvalidDateException("Assigned date is not valid");
			}
		}catch(InvalidDateException ex)
		{
			
		}
	}
	
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
	public boolean containsProduct(Product prod)
	{
		boolean contained = false;
		for (Map.Entry<Product,Integer> e: basket.entrySet())
		{
			if (e.getKey().equals(prod))
			{
				contained = true;
			}
		}
		return contained;
	}

	/**
	 * Returns how many existences of the product determined by the id are there in this order.
	 * 
	 * Returns zero if the product in question isn't in this order.
	 * @return	Returns the quantity of a product in this order.
	 */
	public int checkProductQuantity(Product prod)
	{
		int quantity;
		
		if (this.containsProduct(prod))
		{
			quantity = basket.get(prod).intValue();
		}
		else
		{
			quantity = 0;
		}
		return quantity;
	}
	
	/**
	 * Adds a single unit of the product determined by the id to this order.
	 * @param prod	The product to add.
	 */
	public void addProduct(Product prod)
	{
		addProduct(prod, 1);
	}
	
	/**
	 * Adds a certain amount of units of the product determined by the id to this order.
	 * @param prod			The product to add.
	 * @param quantity		The amount of product to add.
	 */
	public void addProduct(Product prod, int quantity)
	{
		if (quantity > 0)
		{
			if (this.containsProduct(prod))
			{
				Integer actualQuantity = Integer.valueOf(quantity + basket.get(prod).intValue());
				for (Map.Entry<Product,Integer> e : basket.entrySet())
				{
					if (e.getKey().equals(prod))
					{
						e.setValue(actualQuantity);
					}
				}
				//basket.replace(prod, Integer.valueOf(actualQuantity + quantity));
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
	 * @param prod	The product to remove.
	 */
	public void removeProduct(Product prod)
	{
		basket.remove(prod);
	}
	
	/**
	 * Removes a certain amount of units of the product determined by the id from this order.
	 * 
	 * If the amount to remove is equal to or greater that the amount of that product currently
	 * in the order, the product in question is removed from the order entirely.
	 * @param prod		The the product to remove.
	 * @param quantity	The amount of product to remove.
	 */
	public void removeProduct(Product prod, int quantity)
	{
		if (quantity > 0)
		{
			if (basket.get(prod).intValue() <= quantity)
			{
				this.removeProduct(prod);
			}
			else
			{
				int actualQuantity = basket.get(prod).intValue();
				basket.replace(prod, Integer.valueOf(actualQuantity - quantity));
			}
		}
	}
	

	/**
	 * Returns true or false based on whether enough products are available from an order.
	 * @param coffee	The current coffee.
	 * @return	Returns true if there is enough stock of products and false otherwise.
	 */
	public boolean validationStock(Cafeteria coffee)
	{
		boolean comprobation = true;
		List<Product> coffeStock = coffee.getAvailableProducts();
		for (Map.Entry<Product, Integer> entry : basket.entrySet())
		{
			if(coffeStock.contains(entry.getKey()))
			{
				if(coffee.getProductQuantity(entry.getKey()) < entry.getValue())
				{
					comprobation = false;
				}
			}
			else
			{
				comprobation = false;
			}
		}
		return comprobation;
	}
	

}
