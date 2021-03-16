package coreapi;

import java.util.ArrayList;

public class Menu implements Product {
	
	private Map<Product,int> productsMenu;
	private int id;
	private String name;
	
	public Menu(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public int getId() { return id; }
	
	public int getPrice() {
		
		float price = 0;
		Iterator<Product> prodIterator = productsMenu.iterator();
		while (prodIterator.hasNext())
		{
			Product actual = prodIterator.next();
			price += actual.getPrice() * linkedHashMap.get(actual);
		}
		return price;
	}
	
	public String getName() {return name;}
	
}
