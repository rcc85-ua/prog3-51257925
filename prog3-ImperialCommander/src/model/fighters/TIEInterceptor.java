/* @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * The Class TIEInterceptor.
 */
public class TIEInterceptor extends Fighter{
	
	/** The Constant DivAtk. */
	static final int DivAtk = 300;
	
	/**
	 * Instantiates a new TIE interceptor.
	 *
	 * @param mother the mother
	 */
	public TIEInterceptor(Ship mother) {
		super(mother);
		addVelocity(45);
		addAttack(5);
		addShield(-20);
		
		
	}
	
	/**
	 * Instantiates a new TIE interceptor.
	 *
	 * @param f the f
	 */
	public TIEInterceptor(Fighter f) {
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
		return 'i';
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
			return 2*((n * getAttack()) / DivAtk);
		case 'A':
			return ((n * getAttack()) / DivAtk)/2;
		default:
			return (n * getAttack())/DivAtk;
		}
	}
}
