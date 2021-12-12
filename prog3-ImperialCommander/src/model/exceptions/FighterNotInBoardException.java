/* @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.exceptions;



/**
 * The Class FighterNotInBoardException.
 */
@SuppressWarnings("serial")
public class FighterNotInBoardException extends Exception{
	
	/**
	 * Instantiates a new fighter not in board exception.
	 */
	public FighterNotInBoardException() {
		super();
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: the fighter is not in the board";
	}
}
