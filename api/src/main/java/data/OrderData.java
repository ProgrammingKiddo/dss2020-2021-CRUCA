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
}
