package coreapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Menu implements Product {
	
	private LinkedHashMap<Product, Integer> productsMenu;
	private int id;
	private String name;
	
	/**
	 * Creates a new menu with the assigned numerical Id and name.
	 * The user of this class should be aware to assign a unique identifier to this menu,
	 * and to provide a user-friendly name for display.
	 * @param assignedId the numerical identifier to assign to this menu.
	 * @param assignedName the name to assign to this menu.
	 */
	public Menu(int assignedId, String assignedName)
	{
		id = assignedId;
		name = assignedName;
		productsMenu = new LinkedHashMap<Product, Integer>();
	}
	
	/**
	 * @return the identifier assigned to this menu.
	 */
	public int getId() { return id; }
	/**
	 * @return the name of this menu.
	 */
	public String getName() { return name; }
	
	/**
	 * Returns the combined cost of all products that make up this menu.
	 * @return the cost of the entire menu.
	 */
	public float getPrice()
	{	
		float sumCost = 0;
		for (Map.Entry<Product, Integer> entry : productsMenu.entrySet())
		{
			sumCost += entry.getKey().getPrice() * entry.getValue().intValue();
		}
		return sumCost;
	}
	/**
	 * Returns a read-only list containing the products that make up this menu.
	 * @return a list containing the products in the menu.
	 * @see List
	 * @see Product
	 */
	public List<Product> getProductsInMenu()
	{
		return Collections.unmodifiableList(new ArrayList<Product>(productsMenu.keySet()));
	}
	/**
	 * Returns how much of a product is contained in this menu.
	 * If the product doesn't exist within this menu, the returned value is zero.
	 * @param productId the id of the product to check.
	 * @return the quantity of the product contained in this menu.
	 */
	public int getProductQuantity(int productId)
	{
		Product productToCheck = ProductCatalog.Instance().getProduct(productId);
		int productQuantity;
		
		if (productsMenu.containsKey(productToCheck))
		{
			productQuantity = productsMenu.get(ProductCatalog.Instance().getProduct(productId)).intValue();			
		}
		else
		{
			productQuantity = 0;
		}
		return productQuantity;
	}
	/**
	 * Adds one unit of the specified product to the menu.
	 * @param product the product to add to the menu.
	 * @see Product
	 */
	public void addProductToMenu(Product product)
	{
		this.addProductToMenu(product, 1);
	}
	/**
	 * Add the specified amount of units of  the specified product to the menu.
	 * @param product the product to add to the menu.
	 * @param quantity how much of that product to add to the menu.
	 * @see Product
	 */
	public void addProductToMenu(Product product, int quantity)
	{
		productsMenu.put(product, Integer.valueOf(quantity));
	}
}
