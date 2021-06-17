/**
 * @author Fran
 * @author Borja
 * @version 0.2
 */
package coreapiTest;

import coreapi.*;
import org.junit.Assert;

import java.math.BigDecimal;

import org.junit.Test;

public class ProductTest
{
	private Product myProduct = new ProductImpl(3, new BigDecimal(0.5), "Doritos","Comida");
	
	@Test
	public void IdCheckProduct()
	{
		Assert.assertEquals("DifferentIdAtProduct1", 3, myProduct.getId());
	}
	
	@Test
	public void PriceCheckProduct()
	{
		Assert.assertEquals(0, myProduct.getPrice().compareTo(new BigDecimal(0.5)));
	}
	
	@Test
	public void NameCheckProduct()
	{
		Assert.assertEquals("DifferentNameAtProduct1", "Doritos", myProduct.getName());
	}
	
	@Test
	public void TypeCheckProduct()
	{
		Assert.assertEquals("DifferentTypeAtProduct1", "Comida", myProduct.getType());
	}
}