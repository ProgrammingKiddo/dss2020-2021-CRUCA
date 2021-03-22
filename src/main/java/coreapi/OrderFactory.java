package coreapi;

import java.util.Date;

public class OrderFactory 
{
	
	//Save the order number, which is correlative and cannot be repeated
	//It starts at 0 because the first registered order will have 0 as its identification number
	private static int orderCount = 0;
	
	/**
	 * @return Returns the current unique counter of new Orders' id.
	 */
	public static int getOrderCount() 
	{
		return orderCount;
	}
	
	/**
	 * @return Returns a new instance of Order with a unique id.
	 */
	public Order createOrder()
	{
		// We are increasing the number so that they are correlative
		return new OrderImpl(orderCount++, new Date(System.currentTimeMillis()));
		
	}

	/**
	 * Create an object of OrderFactory
	 */
	public OrderFactory(){}
	
}
