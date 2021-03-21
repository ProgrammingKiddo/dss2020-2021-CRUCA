package coreapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Collections;

public class OrderImpl implements Order {

	private final int id;
	private final Date date;
	// Map cannot take primitive types as the type of its mapped values
	private LinkedHashMap<Product, Integer> basket;
	
	
	OrderImpl(int assignedId, Date creationDate)
	{
		id = assignedId;
		date = creationDate;
		basket = new LinkedHashMap<Product, Integer>();
	}
	
	public int getId() { return id; }
	public Date getDate() { return date; }
	
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
		boolean isContained = false;
		Set<Product> products = basket.keySet();
		Iterator<Product> prodIterator = products.iterator();
		
		while (prodIterator.hasNext() && isContained == false)
		{
			if (prodIterator.next().getId() == id)
			{
				isContained = true;
			}
		}
		return isContained;
	}

	/**
	 * @return Returns how many existences of a Product are there in this Order.
	 */
	public int checkProductQuantity(int id)
	{
		int quantity = 0;
		
		if (this.containsProduct(id))
		{
			for (Map.Entry<Product, Integer> entry : basket.entrySet())
			{
				if (entry.getKey().getId() == id)
				{
					quantity = entry.getValue().intValue();
				}
			}
		}
		return quantity;
	}
}
