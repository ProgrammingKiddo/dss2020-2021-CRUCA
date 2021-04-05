package coreapi;

import java.math.BigDecimal;

/**
 * Implementation of the <code>Product</code> interface.
 * 
 * Users of the API shouldn't use this class directly.
 * @author Fran
 * @author Borja
 * @version 0.2
 */
public class ProductImpl implements Product {

	private int id;
	private BigDecimal price;
	private String name;
	
	/**
	 * Creates a new instance of a specific product.
	 * 
	 * To be used only in the creation of the product catalog.
	 * @param id the identifier assigned to this product.
	 * @param price the cost of this product.
	 * @param name the name assigned to this product.
	 * @see ProductCatalog
	 */
	public ProductImpl(int id, BigDecimal price, String name)
	{
		this.id = id;
		this.price = price;
		this.name = name;
	}
	/**
	 * Returns the unique id assigned by the product catalog to this specific catalog.
	 * @return Returns the id of this product.
	 * @see ProductCatalog
	 */
	public int getId() { return id; }
	/**
	 * Returns the price of this product as a <code>BigDecimal</code> to avoid precision loss.
	 * @return Returns the price of this product.
	 * @see BigDecimal
	 */
	public BigDecimal getPrice() { return price; }
	/**
	 * Returns the name assigned by the product catalog to this specific catalog.
	 * @return Returns the name of this product.
	 * @see ProductCatalog
	 */
	public String getName() {return name;}
}
