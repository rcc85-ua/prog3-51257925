/* 
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.exceptions;

/**
 * The Class NoFighterAvailableException.
 */
@SuppressWarnings("serial")
public class NoFighterAvailableException extends Exception{
	
	/**
	 * Instantiates a new no fighter available exception.
	 */
	public NoFighterAvailableException() {
		super();
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: no Fighter Available";
	}
}
