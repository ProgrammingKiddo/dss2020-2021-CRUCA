package coreapi;

/**
 * This exception class represents the situation where a client is trying to remove a product
 * from an order, but said product does not exist in that order.
 * @author Maria
 * @author Borja
 * @version 0.1
 * @see OrderService
 */
@SuppressWarnings("serial")
public class ProductNotContainedInOrderException extends Exception
{
	/** Create a new exception of this type, detailing on the message
	 * which product was being attempted to remove from which order.
	 * @param message The message detailing the specifics of the issue.
	 */
	public ProductNotContainedInOrderException(String message) {
		super(message);
	}

}
