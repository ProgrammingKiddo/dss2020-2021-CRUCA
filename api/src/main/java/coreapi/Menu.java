package coreapi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the <code>Product</code> interface representing a menu comprised of multiple products.
 * 
 * Users of the API shouldn't use this class directly.
 * @author Fran
 * @author Borja
 * @version 0.2
 */
public class Menu implements Product, Serializable {
	
	private static final long serialVersionUID = 1L;
	private LinkedHashMap<Product, Integer> productsMenu;
	private int id;
	private String name;
	private String type;
	
	/**
	 * Creates a new menu with the assigned numerical Id and name.
	 * 
	 * The user of this class should be aware to assign a unique identifier to this menu,
	 * and to provide a user-friendly name for display.
	 * @param assignedId	The numerical identifier to assign to this menu.
	 * @param assignedName	The name to assign to this menu.
	 * @param assignedType 	The type of product to assign to this menu.
	 */
	public Menu(int assignedId, String assignedName, String assignedType)
	{
		id = assignedId;
		name = assignedName;
		type = assignedType;
		productsMenu = new LinkedHashMap<Product, Integer>();
	}
	
	/**
	 * Returns the identifier assigned to this menu.
	 * @return	Returns the identifier assigned to this menu.
	 */
	public int getId() 
	{ 
		return id;
	}
	
	/**
	 * Returns the name of this menu.
	 * @return	Returns the name of this menu.
	 */
	public String getName() 
	{ 
		return name; 
	}
	
	/**
	 * Returns the type of product of this menu.
	 * @return 	Returns the type of menu.
	 */
	public String getType() 
	{
		return type;
	}
	
	/**
	 * Returns the combined cost of all products that make up this menu.
	 * @return	Returns the cost of the entire menu.
	 */
	public BigDecimal getPrice()
	{	
		BigDecimal sumCost = BigDecimal.ZERO;
		BigDecimal productCost = BigDecimal.ZERO;
		BigDecimal productQuantity = BigDecimal.ZERO;
		
		for (Map.Entry<Product, Integer> entry : productsMenu.entrySet())
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
	 * Returns a read-only list containing the products that make up this menu.
	 * @return	Returns a list containing the products in the menu.
	 * @see List
	 * @see Product
	 */
	public List<Product> getProductsInMenu()
	{
		return List.copyOf(new ArrayList<Product>(productsMenu.keySet()));
	}
	
	/**
	 * Returns how much of a product is contained in this menu.
	 * 
	 * If the product doesn't exist within this menu, the returned value is zero.
	 * @param prod		The product to check.
	 * @return 		Returns the quantity of the product contained in this menu.
	 */
	public int getProductQuantity(Product prod)
	{
		int productQuantity;
		
		if (productsMenu.containsKey(prod))
		{
			productQuantity = productsMenu.get(prod).intValue();			
		}
		else
		{
			productQuantity = 0;
		}
		return productQuantity;
	}
	
	/**
	 * Adds one unit of the specified product to the menu.
	 * @param product 	The product to add to the menu.
	 * @see Product
	 */
	public void addProductToMenu(Product product)
	{
		this.addProductToMenu(product, 1);
	}
	
	/**
	 * Add the specified amount of units of  the specified product to the menu.
	 * @param product	The product to add to the menu.
	 * @param quantity	How much of that product to add to the menu.
	 * @see Product
	 */
	public void addProductToMenu(Product product, int quantity)
	{
		productsMenu.put(product, Integer.valueOf(quantity));
	}
	
	
}
