/* @author Rayane Chelihi Chelouche
 * @author 51257925X
 */
package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * The Class AWing.
 */
public class AWing extends Fighter {

	/** The Constant DivAtk. */
	static final int DivAtk = 300;
	
	/**
	 * Instantiates a new a wing.
	 *
	 * @param mother the mother
	 */
	public AWing(Ship mother) {
		super(mother);
		addVelocity(40);
		addAttack(5);
		addShield(-50);
		
	}
	
	/**
	 * Instantiates a new a wing.
	 *
	 * @param f the f
	 */
	public AWing(Fighter f) {
		super(f);
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	@Override
	public Fighter copy() {
		return new AWing(this);
		
		
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	@Override
	public char getSymbol() {
		return 'A';
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
		if(enemy.getSymbol() == 'b') {
			return 2*((n * getAttack()) / DivAtk);
		}else {
			return (n * getAttack()) / DivAtk;
		}
	}

}