/**
 * @author Fran
 * @author Borja
 * @version 0.2
 */
package coreapiTest;

import coreapi.*;
import org.junit.Assert;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductTest
{
	private Product myProduct = ProductCatalog.Instance().getProduct(3);
	
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
}