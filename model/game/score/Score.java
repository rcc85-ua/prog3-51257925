/*
 * @author Rayane Chelihi Chelouche 
 * @author 51257925X
 */
package model.game.score;

import model.Side;

/**
 * The Class Score.
 *
 * @param <T> the generic type
 */
public abstract class Score<T> implements Comparable<Score<T>> {

	/** The score. */
	protected int score;

	/** The side. */
	protected Side side;

	/**
	 * Instantiates a new score.
	 *
	 * @param side the side
	 */
	public Score(Side side) {
		score = 0;
		this.side = side;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}


	/**
	 * Sets the side.
	 *
	 * @param side the new side
	 */
	public void setSide(Side side) {
		this.side = side;
	}

	/**
	 * Compare to.
	 *
	 * @param other the other
	 * @return the int
	 */
	public int compareTo(Score<T> other) {
		// devuelve 1 si es mayor, -1 si es menor y 0 si son iguales
		int devuelto = 0;

		if (score < other.score) {
			devuelto = 1;
		} 
		
		if(score > other.score) {
				devuelto=-1;
		}
		
		if(score == other.score) {
			
			if(side.equals(other.side)) { devuelto= 0;}
			
			if(side.equals(Side.IMPERIAL) && other.side.equals(Side.REBEL)) {devuelto = -1;}
			
			if(side.equals(Side.REBEL)&& other.side.equals(Side.IMPERIAL)) {devuelto= 1;}
		}
		return devuelto;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {

		return "Player " + side + ": " + score;
	}

	/**
	 * Score.
	 *
	 * @param sc the sc
	 */
	public abstract void score(T sc);
}
