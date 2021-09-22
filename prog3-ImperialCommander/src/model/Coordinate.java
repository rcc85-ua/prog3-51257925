/**
 * @author rayane Chelihi Chelouche 
 * @author 51257925X
 */
package model;

import java.util.Objects;


/**
 * The Class Coordinate.
 */
public class Coordinate {
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param c the c
	 */
	public Coordinate(Coordinate c) {
		x=c.x;
		y=c.y;
	}

  	/**
	   * Gets the x.
	   *
	   * @return the x
	   */
  	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Adds the.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the coordinate
	 */
	public Coordinate add(int x, int y) {
		Coordinate newC= new Coordinate(this.x+x,this.y+y);
		return newC;
	}
	
	/**
	 * Adds the.
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public Coordinate add(Coordinate c) {
		Coordinate newC = new Coordinate(c.x+x, y+c.y);
		return newC;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String std = new String("[" + Integer.toString(x) + "," + Integer.toString(y) + "]");
		return std;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return x == other.x && y == other.y;
	}

	/*public boolean equals(Object obj) {
	if(obj == this) return true;
	if(obj == null) return false;
	if(!(obj instanceof Coordinate)) return false;
	Coordinate cord=(Coordinate) obj;
	if(cord.x==this.x && cord.y == this.y) return true;
	else return false;
	}*/
	
}

