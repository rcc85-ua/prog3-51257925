/* 
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.exceptions;

/**
 * The Class FighterIsDestroyedException.
 */
@SuppressWarnings("serial")
public class FighterIsDestroyedException extends java.lang.Exception{
	
	/**
	 * Instantiates a new fighter is destroyed exception.
	 */
	public FighterIsDestroyedException() {
		super();
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: the fighter is destroyed"; 
	}
}
