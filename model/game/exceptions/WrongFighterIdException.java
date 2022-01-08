/*
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.game.exceptions;

/**
 * The Class WrongFighterIdException.
 */
@SuppressWarnings("serial")
public class WrongFighterIdException extends Exception{

	/** The id. */
	private int id;
	
	/**
	 * Instantiates a new wrong fighter id exception.
	 *
	 * @param id the id
	 */
	public WrongFighterIdException(int id) {
		super();
		this.id = id;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: Invalid ID " + id;
	}
	
}
