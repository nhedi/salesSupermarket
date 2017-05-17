package se.kth.ict.nextgenpos.model;

/**
 * Thrown when the requested item does not exist in the product catalog.
 *
 */
public class NoneExistingItemIdException extends Exception {

    /**
     * Creates a new instance representing the condition described in the specified message.
     * @param message A message that describes what went wrong.
     */
	public NoneExistingItemIdException (String message) {
		super(message);
	}
}