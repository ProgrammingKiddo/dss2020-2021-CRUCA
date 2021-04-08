package coreapi;

/**
 * This exception class represents the situation where a client is trying to add
 * a certain amount of a product to an order, but there isn't enough quantity of that
 * product in stock at the current cafeteria.
 * @author Maria
 * @author Borja
 * @version 0.1
 * @see Cafeteria
 * @see OrderService
 */
public class InsufficientStockException extends Exception {

	/**
	 * Create a new exception of this type, detailing on the message
	 * the specifics of how much product was being attempted to allocate
	 * and how much of it there's actually in stock.
	 * @param message The message detailing the specifics of the issue.
	 */
	public InsufficientStockException(String message) {
		super(message);
	}

}
