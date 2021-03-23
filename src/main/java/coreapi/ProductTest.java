package coreapi;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductTest
{
	private Product Product1;
	private Product Product2;
	private Product Product3;
	
	@Before
	protected void setUp()
	{
		Product1 = ProductCatalog.getProduct(3);
		Product2 = ProductCatalog.getProduct(4);
		Product3 = ProductCatalog.getProduct(5);
	}
	
	@After
	protected void tearDown()
	{
		Product1 = null;
		Product2 = null;
		Product3 = null;
	}
	
	@Test
	public void IdCheckProduct()
	{
		Assert.assertEquals("DifferentIdAtProduct1",3,Product1.getId());
		Assert.assertEquals("DifferentIdAtProduct2",4,Product2.getId());
		Assert.assertEquals("DifferentIdAtProduct3",5,Product3.getId());
	}
	
	@Test
	public void PriceCheckProduct()
	{
		Assert.assertEquals("DifferentPriceAtProduct1",(float) 0.5,Product1.getPrice());
		Assert.assertEquals("DifferentPriceAtProduct2",(float) 1.6,Product2.getPrice());
		Assert.assertEquals("DifferentPriceAtProduct3",(float) 1.3,Product3.getPrice());
	}
	
	@Test
	public void NameCheckProduct()
	{
		Assert.assertEquals("DifferentNameAtProduct1","Doritos",Product1.getName());
		Assert.assertEquals("DifferentNameAtProduct2","Monster",Product2.getName());
		Assert.assertEquals("DifferentNameAtProduct3","Bocadillo de tortilla",Product3.getName());
	}
}