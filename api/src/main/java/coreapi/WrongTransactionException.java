package coreapi;
/**
 * This class of exception represents the situation in which a customer enters 
 * incorrect data when he is going to carry out a transaction, 
 * and therefore this cannot be carried out correctly.
 * @author Mar�a
 * @author Fran
 */

public class WrongTransactionException extends Exception {

	/**
	 * Create a new exception of this type, detailing on the message
	 * exactly why the transaction failed.
	 * @param message The message detailing the specifics of the issue.
	 */
	public WrongTransactionException(String message) {
		super(message);
	}

}
