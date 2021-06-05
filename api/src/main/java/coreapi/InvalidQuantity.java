package coreapi;

/**
* This exception class represents the situation where a quantity is invalid. 
* @author María
* @author Fran
* @see OrderService
*/

public class InvalidQuantity extends Exception{
	
	public InvalidQuantity(String message) {
		super(message);
	}
}
