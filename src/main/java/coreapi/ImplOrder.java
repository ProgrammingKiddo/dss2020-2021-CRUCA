package coreapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class ImplOrder implements Order {

	private int id;
	private Date date;
	// Map cannot take primitive types as the type
	// of its mapped values
	private Map<Product, Integer> basket;
	
	
	public int totalCost()
	{
		return 0;
	}
	
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
