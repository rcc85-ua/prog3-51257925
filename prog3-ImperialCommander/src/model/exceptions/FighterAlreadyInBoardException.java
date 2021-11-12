/* @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.exceptions;

import model.Coordinate;

/**
 * The Class FighterAlreadyInBoardException.
 */
@SuppressWarnings("serial")
public class FighterAlreadyInBoardException extends Exception{
	
	/** The coord 2. */
	Coordinate coord,coord2;
	
	/**
	 * Instantiates a new fighter already in board exception.
	 *
	 * @param cordenada the cordenada
	 * @param coord2 the coord 2
	 */
	public FighterAlreadyInBoardException(Coordinate cordenada,Coordinate coord2) {
		super();
		coord = cordenada;
		this.coord2 = coord2;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR the fighter is already in the coordinate " + coord + ", can't launch fighter in the Coordinate" + coord2;
	}
}
