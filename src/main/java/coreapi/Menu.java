package coreapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class Menu implements Product {
	
	private LinkedHashMap<Product, Integer> productsMenu;
	private int id;
	private String name;
	
	public Menu(int assignedId, String assignedName)
	{
		id = assignedId;
		name = assignedName;
		productsMenu = new LinkedHashMap<Product, Integer>();
	}
	
	/*
	 * @return Returns the unique identifier assigned to this Menu.
	 */
	public int getId() { return id; }
	/*
	 * @return Returns the name of this Menu.
	 */
	public String getName() { return name; }
	
	/*
	 * @return Returns the combined cost of all products that make up this Menu.
	 */
	public float  getPrice() {
		
		float price = 0;
		Set<Product> containedProducts = productsMenu.keySet();
		Iterator<Product> prodIterator = containedProducts.iterator();
		
		while (prodIterator.hasNext())
		{
			price += (prodIterator.next().getPrice() * productsMenu.get(prodIterator.next()));
		}
		return price;
	}
	
	/*
	 * @return Returns a read-only list containing the products that make up this Menu.
	 */
	public List<Product> getProductsInMenu()
	{
		return Collections.unmodifiableList(new ArrayList<Product>(productsMenu.keySet()));
	}
	
	/*
	 * Add a product to the menu.
	 */
	
	public void addProductToMenu(Product product, Integer q)
	{
		productsMenu.put(product,q);
	}
}
