/**
 * @author rayane Chelihi Chelouche 
 * @author 51257925X
 */
package model;

import java.util.Objects;
import java.util.TreeSet;


/**
 * The Class Coordinate.
 */
public class Coordinate implements Comparable<Coordinate> {
	
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
		/*hash debe tener el mismo hash code, 
		 * si o1==o2 tienen que tener el mismo hash*/
	
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
	
	
	/**
	 * Compare to.
	 *
	 * @param otra the otra
	 * @return the int
	 */
	@Override
	public int compareTo(Coordinate otra) {
		if(this.x != otra.x) {
			return this.x-otra.x;
		}else {
			return this.y - otra.y;
		}
	}
	
	/**
	 * Gets the neighborhood.
	 *
	 * @return the neighborhood
	 */
	public TreeSet<Coordinate> getNeighborhood(){
		TreeSet <Coordinate> vecinas = new TreeSet<Coordinate>();
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				if(i!=0 && j!=0)
				vecinas.add(new Coordinate(x+i,y+j));
			}
		}
	return vecinas;
	}


}

