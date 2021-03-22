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
		Product1 = new ProductImpl(3,float 0.9,"Meat");
		Product2 = new ProductImpl(4,float 5.2,"Fish");
		Product3 = new ProductImpl(5,float 10,"Lentils");
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
		assertEquals("DifferentIdAtProduct1",3,Product1.getId());
		assertEquals("DifferentIdAtProduct2",4,Product2.getId());
		assertEquals("DifferentIdAtProduct3",5,Product3.getId());
	}
	
	@Test
	public void PriceCheckProduct()
	{
		assertEquals("DifferentPriceAtProduct1",float 0.9,Product1.getPrice());
		assertEquals("DifferentPriceAtProduct2",float 5.2,Product2.getPrice());
		assertEquals("DifferentPriceAtProduct3",float 10,Product3.getPrice());
	}
	
	@Test
	public void NameCheckProduct()
	{
		assertEquals("DifferentNameAtProduct1","Meat",Product1.getName());
		assertEquals("DifferentNameAtProduct2","Fish",Product2.getName());
		assertEquals("DifferentNameAtProduct3","Lentils",Product3.getName());
	}
}