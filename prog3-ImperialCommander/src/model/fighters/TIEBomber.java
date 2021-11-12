/* @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * The Class TIEBomber.
 */
public class TIEBomber extends Fighter{
	
	/** The Constant DivAtk. */
	static final int DivAtk = 300;
	
	/**
	 * Instantiates a new TIE bomber.
	 *
	 * @param mother the mother
	 */
	public TIEBomber(Ship mother) {
		super(mother);
		addVelocity(-30);
		addAttack(-50);
		addShield(35);
		
		
	}
	
	/**
	 * Instantiates a new TIE bomber.
	 *
	 * @param f the f
	 */
	public TIEBomber(Fighter f) {
		super(f);
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new TIEInterceptor(this);
		
		
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'b';
	}

	/**
	 * Gets the damage.
	 *
	 * @param n the n
	 * @param enemy the enemy
	 * @return the damage
	 */
	@Override
	public int getDamage(int n, Fighter enemy) {
		switch(enemy.getSymbol()) {
		case 'Y':
		case 'X':
			return ((n * getAttack()) / DivAtk)/2;
		case 'A':
			return ((n * getAttack()) / DivAtk)/3;
		default:
			return (n * getAttack())/DivAtk;
		}
	}
}
