/*
 * @author: Rayane Chelihi Chelouche
 * @author: 51257925X
 */
package model.game.score;

import model.Side;

/**
 * The Class WinsScore.
 */
public class WinsScore extends Score<Integer>{
	
	/**
	 * Instantiates a new wins score.
	 *
	 * @param side the side
	 */
	public WinsScore(Side side) {
		super(side);
	}
	


	/**
	 * Score.
	 *
	 * @param sc the sc
	 */
	@Override
	public void score(Integer sc) {
		if(sc != null)
		if(sc == 1) {
			score++;
		}
	}
}
