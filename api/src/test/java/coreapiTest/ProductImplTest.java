/**
 * @author Fran
 * @author Borja
 * @version 0.2
 */
package coreapiTest;

import coreapi.ProductImpl;
import coreapi.ProductCatalog;

import org.junit.Assert;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductImplTest
{
	private ProductImpl myProduct = (ProductImpl) ProductCatalog.Instance().getProduct(0);
	
	@Test
	public void IdCheckProductImpl()
	{
		Assert.assertEquals("DifferentIdAtProductImpl1", 0, myProduct.getId());
	}
	
	@Test
	public void PriceCheckProductImpl()
	{
		Assert.assertEquals(0,  myProduct.getPrice().compareTo(new BigDecimal(1.2)));
	}
	
	@Test
	public void NameCheckProductImpl()
	{
		Assert.assertEquals("DifferentNameAtProductImpl1", "Patatas fritas", myProduct.getName());
	}
}