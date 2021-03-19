package coreapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class Menu implements Product {
	
	private LinkedHashMap<Product, Integer> productsMenu;
	private int id;
	private String name;
	
	public Menu(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	
	public float  getPrice() {
		
		float price = 0;
		Set<Product> containedProducts = productsMenu.keySet();
		Iterator<Product> prodIterator = containedProducts.iterator();
		
		while (prodIterator.hasNext())
		{
			price += prodIterator.next().getPrice();
		}
		return price;
	}
	
	
}
