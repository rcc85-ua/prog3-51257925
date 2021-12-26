/*
 * @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.game.score;

import model.Fighter;
import model.Side;

/**
 * The Class DestroyedFightersScore.
 */
public class DestroyedFightersScore extends Score<Fighter>{
	
	/**
	 * Instantiates a new destroyed fighters score.
	 *
	 * @param side the side
	 */
	public DestroyedFightersScore(Side side) {
		super(side);
	}
	
	/**
	 * Score.
	 *
	 * @param luchador the luchador
	 */
	public void score(Fighter luchador) {
		this.xcore(luchador.getValue());
	}
	
}