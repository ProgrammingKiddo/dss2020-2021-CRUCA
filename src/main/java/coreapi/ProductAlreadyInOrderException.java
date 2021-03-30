/**
 * 
 */
package coreapi;

/**
 * @author Maria
 * @author Borja
 * @version 0.1
 *
 */
public class ProductAlreadyInOrderException extends Exception
{

	/**
	 * @param message
	 */
	public ProductAlreadyInOrderException(String message) {
		super(message);
	}
}
