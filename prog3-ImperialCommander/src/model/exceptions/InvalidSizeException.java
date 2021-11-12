/* @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.exceptions;


/**
 * The Class InvalidSizeException.
 */
@SuppressWarnings("serial")
public class InvalidSizeException extends Exception{
	
	/** The size. */
	int size;
	
	/**
	 * Instantiates a new invalid size exception.
	 *
	 * @param size the size
	 */
	public InvalidSizeException(int size) {
		super();
		this.size = size;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR board size: " + size + "is invalid";
	}
}
