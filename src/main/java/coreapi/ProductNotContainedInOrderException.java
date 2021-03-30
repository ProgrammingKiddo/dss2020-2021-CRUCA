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
public class ProductNotContainedInOrderException extends Exception
{

	/**
	 * @param message
	 */
	public ProductNotContainedInOrderException(String message) {
		super(message);
	}

}
