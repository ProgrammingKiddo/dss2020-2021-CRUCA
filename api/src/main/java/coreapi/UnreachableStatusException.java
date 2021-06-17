package coreapi;

/**
 * This exception class represents the situation where a client is trying to change the status
 * of an order to a new one, but the conditions for that change are not met.
 * 
 * The specifics of which should be detailed in the message passed to the constructor.
 * @author Maria
 * @author Borja
 * @version 0.1
 * @see OrderService
 */
@SuppressWarnings("serial")
public class UnreachableStatusException extends Exception
{
	/**
	 * Create a new exception of this type, detailing on the message
	 * exactly why the state of the order could not be changed.
	 * @param message The message detailing the specifics of the issue.
	 */
	public UnreachableStatusException(String message) {
		super(message);
	}
}
