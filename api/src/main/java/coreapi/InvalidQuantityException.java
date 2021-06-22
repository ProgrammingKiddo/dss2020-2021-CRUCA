package coreapi;

/**
* This exception class represents the situation where a quantity is invalid. 
* @author María
* @author Fran
* @see OrderService
*/
@SuppressWarnings("serial")
public class InvalidQuantityException extends Exception{
	/**
	 * Create a new exception of this type, detailing on the message
	 * the specifics of the invalid quantity.
	 * @param message The message detailing the specifics of the issue.
	 */
	public InvalidQuantityException(String message) {
		super(message);
	}
}
