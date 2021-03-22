package coreapi;

import org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderFactoryTest 
{
	private Cafeteria coffe;
	private Order ord1, ord2, ord3;
	
	@Before
	protected void setUp()
	{
		coffe = new Cafeteria();
		ord1 = coffe.ordFact.createOrder();
		ord2 = coffe.ordFact.createOrder();
		ord3 = coffe.ordFact.createOrder();
	}
	
	@After
	protected void tearDown()
	{
		coffe = null;
		ord1 = null;
		ord2 = null;
		ord3 = null;
	}
	
	@Test
	public void IdCheck_OrderFactory()
	{
		Assert.assertEquals("The Order's Id not is correlative", ord1.getId(), ord2.getId() - 1 , ord3.getId() - 2);
		
	}
	
	public void DateCheck_OrderFactory()
	{
		Date today =  new Date(System.currentTimeMillis());
		Assert.assertEquals("Incorrect Date", ord1.getDate().toString(), today.toString());
		
	}
}
