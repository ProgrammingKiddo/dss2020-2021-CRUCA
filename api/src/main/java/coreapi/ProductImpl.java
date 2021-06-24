package coreapi;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Implementation of the <code>Product</code> interface representing a single product.
 * 
 * Users of the API shouldn't use this class directly.
 * @author Fran
 * @author Borja
 * @version 0.2
 */
public class ProductImpl implements Product, Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private BigDecimal price;
	private String name;
	private String type;
	
	/**
	 * Creates a new instance of a specific product.
	 * 
	 * @param id		The identifier assigned to this product.
	 * @param price		The cost of this product.
	 * @param name		The name assigned to this product.
	 * @param type		The name of the type product.
	 */
	public ProductImpl(int id, BigDecimal price, String name, String type)
	{
		this.id = id;
		this.price = price;
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Returns the unique id assigned to a specific product.
	 * @return	Returns the id of this product.
	 */
	public int getId() 
	{ 
		return id; 
	}
	
	/**
	 * Returns the price of this product as a <code>BigDecimal</code> to avoid precision loss.
	 * @return	Returns the price of this product.
	 * @see BigDecimal
	 */
	public BigDecimal getPrice() 
	{ 
		return price; 
	}
	
	/**
	 * Returns the name assigned to a specific product.
	 * @return	Returns the name of this product.
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Returns the name of the product category which belong.
	 * @return Returns the type of this product.
	 */
	public String getType() 
	{
		return type;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o == this)
		{
			return true;
		}
		
		if(!(o instanceof Product) && !(o instanceof ProductImpl))
		{
			return false;
		}
		
		Product p = (Product) o;
		return p.getId() == this.id;
	}
	
	@Override
	public int hashCode()
	{
		int result = 17;
		
		result = 31 * result + id;
		result = 31 * result + price.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + type.hashCode();
		
		return result;
	}
}