package data;

/**
 * Interface that offers operations to save the data of a Order.
 * @author Borja
 * @author Maria
 *
 */

import coreapi.Order;

public interface OrderData 
{
	/**
	 * Return a specific order.
	 * @param id	The id of a specific order.
	 * @return		Return a specific order.
	 */
	public Order getOrder(int id);
	
	/**
	 * Save a specific order.
	 * @param orderToSave	The specific order.
	 */
	public void saveOrder(Order orderToSave);
	
	/**
	 * Checks how many Order files exist already in the active directory.
	 * @return	The number of pre-existing orders
	 */
	public int getNumberOfExistingOrders();
	
	/**
	 * Deletes the data from the indicated order.
	 * @param id	The id of a specific order.
	 */
	public void deleteOrder(int id);
}
