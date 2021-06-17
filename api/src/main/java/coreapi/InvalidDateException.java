package coreapi;

/**
* This exception class represents the situation where a client is trying to add
* a invalid date. 
* @author María
* @author Fran
* @see OrderImpl
* @see OrderService
*/
@SuppressWarnings("serial")
public class InvalidDateException extends Exception{
	/**
	* Create a new exception of this type, detailing the message.
	* the details of the date you are trying to assign.
	* @param message 	The message detailing the details of the problem.
	*/
	public InvalidDateException(String message)
	{
		super(message);
	}
}
