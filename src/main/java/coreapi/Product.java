/**
 * @author Fran
 * @version 0.2
 */

package coreapi;

import java.math.BigDecimal;

public interface Product {
	
	/**
	 * Returns the unique id assigned by the product catalog to this specific catalog.
	 * @return returns the id of this product.
	 * @see ProductCatalog
	 */
	public int getId();
	
	/**
	 * Returns the price of this product as a <code>BigDecimal</code> to avoid precision loss.
	 * @return returns the price of this product.
	 * @see BigDecimal
	 */
	public BigDecimal getPrice();
	
	/**
	 * Returns the name assigned by the product catalog to this specific catalog.
	 * @return returns the name of this product.
	 * @see ProductCatalog
	 */
	public String getName();
	
}
