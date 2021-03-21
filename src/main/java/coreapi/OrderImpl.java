package coreapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class OrderImpl implements Order {

	private final int id;
	private Date date;
	// Map cannot take primitive types as the type
	// of its mapped values
	private LinkedHashMap<Product, Integer> basket;
	
	
	OrderImpl(int assignedId)
	{
		id = assignedId;
		basket = new LinkedHashMap<Product, Integer>();
	}
	
	public float totalCost()
	{
		return 0;
	}
	
	public int getId() { return id; }
	
	public List<Product> getProducts()
	{
		return new ArrayList<Product>(basket.keySet());
	}
	
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
}
