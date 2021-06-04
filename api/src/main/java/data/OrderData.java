package data;

/**
 * 
 * @author Borja
 *
 */

import coreapi.Order;

public interface OrderData {

	public Order getOrder(int id);
	public void saveOrder(Order orderToSave);
}
