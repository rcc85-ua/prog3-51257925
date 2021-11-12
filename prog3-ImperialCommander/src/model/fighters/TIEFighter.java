/* @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * The Class TIEFighter.
 */
public class TIEFighter extends Fighter{
	
	/**
	 * Instantiates a new TIE fighter.
	 *
	 * @param mother the mother
	 */
	public TIEFighter(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(5);
		addShield(-10);
		
		
	}
	
	/**
	 * Instantiates a new TIE fighter.
	 *
	 * @param f the f
	 */
	public TIEFighter(Fighter f) {
		super(f);
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new TIEFighter(this);
		
		
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'f';
	}

}
