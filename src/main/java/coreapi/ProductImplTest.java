package coreapi;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductImplTest
{
	private ProductImpl Product1;
	private ProductImpl Product2;
	private ProductImpl Product3;
	
	@Before
	protected void setUp()
	{
		Product1 = ProductCatalog.getProduct(0);
		Product2 = ProductCatalog.getProduct(1);
		Product3 = ProductCatalog.getProduct(2);
	}
	
	@After
	protected void tearDown()
	{
		Product1 = null;
		Product2 = null;
		Product3 = null;
	}
	
	@Test
	public void IdCheckProductImpl()
	{
		Assert.assertEquals("DifferentIdAtProductImpl1",0,Product1.getId());
		Assert.assertEquals("DifferentIdAtProductImpl2",1,Product2.getId());
		Assert.assertEquals("DifferentIdAtProductImpl3",2,Product3.getId());
	}
	
	@Test
	public void PriceCheckProductImpl()
	{
		Assert.assertEquals("DifferentPriceAtProductImpl1",(float) 1.2,Product1.getPrice());
		Assert.assertEquals("DifferentPriceAtProductImpl2",(float) 1.7,Product2.getPrice());
		Assert.assertEquals("DifferentPriceAtProductImpl3",(float) 0.9,Product3.getPrice());
	}
	
	@Test
	public void NameCheckProductImpl()
	{
		Assert.assertEquals("DifferentNameAtProductImpl1","Patatas fritas",Product1.getName());
		Assert.assertEquals("DifferentNameAtProductImpl2","Bacon-queso-huevo",Product2.getName());
		Assert.assertEquals("DifferentNameAtProductImpl3","Café con leche",Product3.getName());
	}
}