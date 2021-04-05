package coreapi;

/**
 * Representation of the possible states an order can be in.
 * @author Borja
 * @version 0.1
 * @see Order
 */
public enum OrderStatus {
	/**
	 * This state represents the order being fulfilled before being sent to the kitchen.
	 */
	OPEN,
	/**
	 * This state represents the order currently being cooked in the kitchen.
	 */
	IN_KITCHEN,
	/**
	 * This state represents the order fully complete having been delivered to the client.
	 */
	DELIVERED,
	/**
	 * This state represents the order being payed in full by the client.
	 */
	PAYED,
	/**
	 * This state represents the order after its cycle has ended and is now closed.
	 */
	FINISHED
}
