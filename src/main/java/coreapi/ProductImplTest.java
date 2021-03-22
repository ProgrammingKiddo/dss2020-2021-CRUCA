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
		Product1 = new ProductImpl(0,(float) 3.15,"Coffe");
		Product2 = new ProductImpl(1,(float) 4,"Cake");
		Product3 = new ProductImpl(2,(float) 6.5,"Water");
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
		assertEquals("DifferentIdAtProductImpl1",0,Product1.getId());
		assertEquals("DifferentIdAtProductImpl2",1,Product2.getId());
		assertEquals("DifferentIdAtProductImpl3",2,Product3.getId());
	}
	
	@Test
	public void PriceCheckProductImpl()
	{
		assertEquals("DifferentPriceAtProductImpl1",float 3.15,Product1.getPrice());
		assertEquals("DifferentPriceAtProductImpl2",float 4,Product2.getPrice());
		assertEquals("DifferentPriceAtProductImpl3",float 6.5,Product3.getPrice());
	}
	
	@Test
	public void NameCheckProductImpl()
	{
		assertEquals("DifferentNameAtProductImpl1","Coffe",Product1.getName());
		assertEquals("DifferentNameAtProductImpl2","Cake",Product2.getName());
		assertEquals("DifferentNameAtProductImpl3","Water",Product3.getName());
	}
}