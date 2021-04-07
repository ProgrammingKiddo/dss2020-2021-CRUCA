package coreapi;

import java.math.BigDecimal;

/**
 * This interface is the handler for the usage of all products.
 * @author Fran
 * @version 0.2
 */

public interface Product {
	
	/**
	 * Returns the unique id assigned by the product catalog to this specific catalog.
	 * @return	Returns the id of this product.
	 * @see ProductCatalog
	 */
	public int getId();
	
	/**
	 * Returns the price of this product as a <code>BigDecimal</code> to avoid precision loss.
	 * @return	Returns the price of this product.
	 * @see BigDecimal
	 */
	public BigDecimal getPrice();
	
	/**
	 * Returns the name assigned by the product catalog to this specific catalog.
	 * @return	Returns the name of this product.
	 * @see ProductCatalog
	 */
	public String getName();
	
	
	/**
	 * Returns the name of the product category which belong.
	 * @return Returns the tyoe of this product
	 */
	public String getType();
}
