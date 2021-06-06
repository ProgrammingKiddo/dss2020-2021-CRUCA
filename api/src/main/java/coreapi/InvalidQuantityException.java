package coreapi;

/**
* This exception class represents the situation where a quantity is invalid. 
* @author María
* @author Fran
* @see OrderService
*/

public class InvalidQuantityException extends Exception{
	
	public InvalidQuantityException(String message) {
		super(message);
	}
}
