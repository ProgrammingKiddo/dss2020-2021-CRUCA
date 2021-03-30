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
public class InsufficientStockException extends Exception {

	/**
	 * @param message
	 */
	public InsufficientStockException(String message) {
		super(message);
	}

}
