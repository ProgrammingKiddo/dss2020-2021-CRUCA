package coreapiTest;

import coreapi.OrderFactory;
import coreapi.Cafeteria;
import coreapi.Order;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderFactoryTest 
{
	private Order ord1, ord2;
	private Cafeteria cafet = new Cafeteria(0, "Santa Fe", "santafe@gmail.com");
	
	@Before
	public void setUp()
	{
		ord1 = OrderFactory.createOrder(cafet);
		ord2 = OrderFactory.createOrder(cafet);
	}
	
	@After
	public void tearDown()
	{
		ord1 = null;
		ord2 = null;
	}
	
	@Test
	public void IdCheck_OrderFactory()
	{
		Assert.assertEquals("The Order's Id are not correlative", 1, ord2.getId() - ord1.getId());
		
	}
	@Test
	public void DateCheck_OrderFactory()
	{
		Date today =  new Date(System.currentTimeMillis());
		Assert.assertEquals("Incorrect Date", ord1.getDate().toString(), today.toString());
		
	}
}
