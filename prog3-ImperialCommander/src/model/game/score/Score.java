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
	private int score;

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
	 * Adds the score.
	 */
	public void addScore() {
		score++;
	}

	/**
	 * Xcore.
	 *
	 * @param sum the sum
	 */
	public void xcore(int sum) {
		score += sum;
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
		if (score < other.score) {
			return other.score;
		}
		if (score == other.score) {
			return this.compareTo(other);
		} else {
			return score;
		}
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
