package coreapi;

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
	
	
	OrderImpl(int assignedId, Date creationDate)
	{
		id = assignedId;
		date = creationDate;
		basket = new LinkedHashMap<Product, Integer>();
		orderStatus = OrderStatus.OPEN;
	}
	
	public int getId() { return id; }
	public Date getDate() { return date; }
	public OrderStatus getStatus() { return OrderStatus.valueOf(orderStatus.name()); }
	public void setStatus(OrderStatus newStatus) { orderStatus = newStatus; }
	
	/**
	 * @return Returns the total cost of all the products contained in the Order.
	 */
	public float totalCost() 
	{
		float sumCost = 0;
		for (Map.Entry<Product, Integer> entry : basket.entrySet())
		{
			sumCost += entry.getKey().getPrice() * entry.getValue().intValue();
		}
		return sumCost;
	}
	
	/**
	 * @return Returns a read-only map containing what products are in this Order.
	 */
	public List<Product> getProducts()
	{
		return Collections.unmodifiableList(new ArrayList<Product>(basket.keySet()));
	}
	
	/**
	 * @return Returns a read-only map containing what products and how many
	 * of them are in this Order.
	 */
	public Map<Product,Integer> getBasket()
	{
		return Collections.unmodifiableMap(basket);
	}
	
	/**
	 * @return Returns whether or not the Product determined by the id is contained in this Order.
	 */
	public boolean containsProduct(int id)
	{
		return basket.containsKey(ProductCatalog.getProduct(id));
	}

	/**
	 * @return Returns how many existences of a Product are there in this Order.
	 */
	public int checkProductQuantity(int id)
	{
		int quantity;
		
		if (this.containsProduct(id))
		{
			quantity = basket.get(ProductCatalog.getProduct(id)).intValue();
		}
		else
		{
			quantity = 0;
		}
		return quantity;
	}

	public void addProduct(int newProductId)
	{
		addProduct(newProductId, 1);
	}
	
	public void addProduct(int newProductId, int quantity)
	{
		if (quantity > 0)
		{
			Product prod = ProductCatalog.getProduct(newProductId);
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
	
	public void removeProduct(int productId)
	{
		basket.remove(ProductCatalog.getProduct(productId));
	}
	
	public void removeProduct(int productId, int quantity)
	{
		if (quantity > 0)
		{
			Product prod = ProductCatalog.getProduct(productId);
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
