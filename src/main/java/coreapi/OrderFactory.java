/**
 * @author Borja
 * @author María
 * @version 0.2
 * @see Order
 */

package coreapi;

import java.util.Date;

public class OrderFactory 
{
	
	// There is just this ONE number counter, which is incremental.
	// It starts at 0 because the first registered order will have 0 as its identification number.
	private static int orderCount = 0;
	
	/**
	 * Returns the current counter of created Orders.
	 * This is id number that will be assigned to the next Order created,
	 * which is unique for each and everyone of them.
	 * @return the current unique id counter.
	 */
	public static int getOrderCount() 
	{
		return orderCount;
	}
	
	/**
	 * Returns a new instance of Order with a unique id.
	 * Sets the timestamp as the current time of execution.
	 * @return the newly created instance of an order.
	 * @see Order
	 */
	public static Order createOrder()
	{
		return createOrder(new Date(System.currentTimeMillis()));
	}

	/**
	 * Returns a new instance of <code>Order</code> with a unique id.
	 * Sets the specified timestamp as the order's creation date.
	 * @param creationDate the timestamp to assign to the order
	 * @return the newly created instance of an order from the indicated date.
	 * @see Order
	 * @see Date
	 */
	public static Order createOrder(Date creationDate)
	{
		return new OrderImpl(orderCount++, creationDate);
	}
}
