package coreapi;

/**
 * @author Maria
 * @author Borja
 * @version 0.1
 * @see OrderService
 */
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
