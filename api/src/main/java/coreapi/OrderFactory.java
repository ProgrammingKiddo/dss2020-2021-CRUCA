package coreapi;


import java.time.LocalDateTime;
/**
 * The static factory to use in the creation of new order objects.
 * @author Borja
 * @author Maria
 * @version 0.2
 * @see Order
 */
public class OrderFactory 
{
	
	// There is just this ONE number counter, which is incremental.
	// It starts at 0 because the first registered order will have 0 as its identification number.
	private static int orderCount = 0;
	
	/**
	 * Returns the current counter of created Orders.
	 * 
	 * This is id number that will be assigned to the next Order created,
	 * which is unique for each and everyone of them.
	 * @return	Returns the current unique id counter.
	 */
	public static int getOrderCount() 
	{
		return orderCount;
	}
	
	public static void setOrderCount(int baseCount)
	{
		orderCount = baseCount;
	}
	
	/**
	 * Returns a new instance of Order with a unique id.
	 * 
	 * Sets the timestamp as the current time of execution.
	 * @param assignedCafeteria		The cafeteria to which the new order pertains.	
	 * @return 	The newly created instance of an order.
	 * @see Order
	 */
	public static Order createOrder(Cafeteria assignedCafeteria)
	{
		return createOrder(assignedCafeteria, LocalDateTime.now());
	}

	/**
	 * Returns a new instance of <code>Order</code> with a unique id.
	 * 
	 * Sets the specified timestamp as the order's creation date.
	 * @param assignedCafeteria		The cafeteria to which the new order pertains.
	 * @param creationDate 			The timestamp to assign to the order.
	 * @return 		The newly created instance of an order from the indicated date.
	 * @see Order
	 * @see Date
	 */
	public static Order createOrder(Cafeteria assignedCafeteria, LocalDateTime creationDate)
	{
		OrderImpl createdOrder = new OrderImpl(orderCount++, creationDate);
		assignedCafeteria.registerOrder(createdOrder);
		return createdOrder;
	}
}
