/* @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.exceptions;

import model.Coordinate;

/**
 * The Class OutOfBoundsException.
 */
/*Si la coordenada donde tenemos que poner el caza o
 *  de la que queremos la vecindad esta fuera del tablero*/
@SuppressWarnings("serial")
public class OutOfBoundsException extends Exception{
	
	/** The coord. */
	Coordinate coord;
	
	/**
	 * Instantiates a new out of bounds exception.
	 *
	 * @param coord the coord
	 */
	public OutOfBoundsException(Coordinate coord) {
		super();
		this.coord = coord;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR the coordinate" + coord + "is not in the board";
	}
}
